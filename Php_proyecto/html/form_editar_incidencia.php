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

        $id_inci=$_GET['id_inci'];
        $descripcion_inc=$_GET['descripcion_inc'];

    ?>


    <form action="modificacion_incidencia.php?id_inci=<?php echo $id_inci;?>" method="POST" class="form-container">

        <div class="card">
                <div class="encabezado">
                    <div class="row caja_titulo">
                        <h3 class="titulo">Editar incidencia: <?php echo "$id_inci"; ?> </h3>		
                    </div>
                </div>

                <div class="card-body mx-4 mt-4">

                    <div>
                        <label for="">Descripcion</label>
                        <textarea name="descripcion" id="descripcion" cols="30" rows="10" maxlength=255 required> <?php echo $descripcion_inc; ?> </textarea>
                    </div>

                    <div class="text-center mb-4">
                        <input type="submit" value="Modificar" class="btn boton waves-effect waves-light">
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