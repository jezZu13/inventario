<?php

    session_start();

    if($_SESSION['autenticado']!="si"){
        header("Location: index.php");
        exit();
    }

?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Proyecto - Invetario</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="css/materialize.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

    <?php
        require("conexion.php");

        //RECOGEMOS LOS DATOS Y COMPROBAMOS QUE NO ESTÉ CERRADA YA LA INCIDENCIA     
        $incidencia=$_GET['reg'];
        $fecha_apertura=$_GET['apertura'];
        $dispositivo=$_GET['id_disp'];
        $fecha_cierre=$_GET['cierre'];
        $serial=$_GET['serial'];
        $descripcion=$_GET['descripcion'];
        if (!empty($fecha_cierre)|| $fecha_cierre==' ') {
            header("Location: mantenimiento_incidencias.php?cierre=0");
        }

        //BUSCAMOS EL ESTADO EN EL QUE ESTÁ EL DISPOSITIVO
        $sql="SELECT `id_estado` from `estado_dispositivo` where `id_disp`='$dispositivo' and `fecha_fin_est_disp` is null";
        $result=mysqli_query($enlace, $sql);
        while($fields=mysqli_fetch_assoc($result)){
            $id_estado_actual=$fields['id_estado'];
        }

        //BUSCAMOS LA SALA EN LA QUE ESTÁ EL DISPOSITIVO
        $sql="SELECT `id_sala` from `ubicacion_dispositivo` where `id_disp`='$dispositivo' and `fecha_salida_disp_sala` is null;";
        $result=mysqli_query($enlace, $sql);
        while($fields=mysqli_fetch_assoc($result)){
            $id_sala_actual=$fields['id_sala'];
        }

    ?>

    <form action="update_incidencia.php?numero_inci=<?php echo $incidencia; ?>&id_disp=<?php echo $dispositivo; ?>&id_estado_actual=<?php echo $id_estado_actual; ?>&id_sala_actual=<?php echo $id_sala_actual; ?>" method="POST" class="form-container">

        <div class="card">
                <div class="encabezado">
                    <div class="row caja_titulo">
                        <h3 class="titulo">Cierre de incidencia: <?php echo $incidencia ?></h3>		
                    </div>
                </div>

                <div class="card-body mx-4 mt-4">

                    <div>
                        <label for="">Dispositivo</label>
                        <input type="text" name="serial" id="serial" value="<?php echo $serial?>" disabled>
                        <input type="text" name="serial" id="serial" value="<?php echo $serial?>" hidden>
                    </div>

                    <div>
                        <label for="">Fecha de Alta</label>
                        <input type="text" name="alta" id="alta" value="<?php echo $fecha_apertura?>" disabled>
                    </div>

                    <div>
                        <label for="">Descripción</label>
                        <textarea name="descripcion" id="" cols="30" rows="10" maxlength=255 disabled><?php echo $descripcion; ?></textarea>
                    </div>

                    <div>
                        <?php
                            $sql="select current_date;";
                            $query=mysqli_query($enlace,$sql);
                            while($fields=mysqli_fetch_assoc($query)){
                                $fecha_actual=$fields['current_date'];
                            }
                        ?>
                        <label for="">Fecha Cierrre:</label>
                        <input type="date" name="cierre" id="cierre" value="<?php echo $fecha_actual; ?>" required>
                    </div>

                    <div>
                        <label for="">Solución:</label>
                        <textarea name="solucion" id="" cols="30" rows="10" maxlength=255 required></textarea>
                    </div>

                    <div>
                        <label for="">Estado:</label>
                        <select name="estado_nuevo" id="estado_nuevo" required>
                            <?php

                            //SELECCIONAMOS LOS ESTADOS:
                            $sql="SELECT `nombre_estado`, `id_estado` from `estado`;";
                        
                            $result=mysqli_query($enlace,$sql);

                            while($reg=mysqli_fetch_assoc($result)){
                                if($reg['id_estado']==$id_estado_actual){
                                    echo "<option value='".$reg['id_estado']."' selected>".$reg['nombre_estado']."</option>";
                                }
                                else{
                                    echo "<option value='".$reg['id_estado']."'>".$reg['nombre_estado']."</option>";
                                }
                            }
                            ?>
                        </select>
                    </div>

                    <div class="pb-3">
                        <label for="">Ubicación:</label>
                        <select name="ubicacion" id="ubicacion" required>
                            <?php

                            //SELECCIONAMOS LAS UBICACIONES:
                            $sql="SELECT `nombre_sala`, `id_sala` from `sala`;";
                            $result=mysqli_query($enlace,$sql);
                            while($reg=mysqli_fetch_assoc($result)){
                                if($reg['id_sala']==$id_sala_actual){
                                    echo "<option value='".$reg['id_sala']."' selected>".$reg['nombre_sala']."</option>";
                                }
                                else{
                                    echo "<option value='".$reg['id_sala']."'>".$reg['nombre_sala']."</option>";
                                }

                            }
                            ?>
                        </select>
                    </div>

                    <div class="text-center mb-4">
                        <input type="submit" value="Cerrar Incidencia" class="btn boton waves-effect waves-light">
                    </div>

                </div>
        </div>
    </form>

    <script src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="js/popper.min.js"></script>
 	<script src="js/bootstrap.min.js"></script>
 	<script type="text/javascript" src="js/materialize.min.js"></script>
	<script src="js/scripts.js"></script>
	<script src="js/funciones.js"></script>
	<script>
        document.addEventListener('DOMContentLoaded', function() {
        var elems = document.querySelectorAll('select');
        var options = document.querySelectorAll('option');
        var instances = M.FormSelect.init(elems, options);
        });
    </script>

<?php
    mysqli_close($enlace);
?>

</body>
</html>