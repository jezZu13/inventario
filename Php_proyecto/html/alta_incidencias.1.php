<?php

    session_start();

    if($_SESSION['autenticado']!="si"){
        header("Location: index.php");
        exit();
    }

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Proyecto - Inventario</title>

	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link href="css/materialize.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/estilos.css">


    <script type="text/javascript">

        /*
        function CargarProductos(familia) {
            $('#familia').ready(function() {
            $.ajax({
                    type: "POST",
                    url: "busca_productos.php",
                    data: 'familia='+familia,
                    success: function(response)
                    {
                        $('#producto_div select').html(response).fadeIn(); //Asi estaba
                    }
            });

        });
        }
        */

        function cargarProductos() {
			
			var textoBusqueda = $("select#familia").val();
			
			if (textoBusqueda != "") {
				$.post("busca_productos.php", {valorBusqueda: textoBusqueda}, function(fin) {
					$("#producto_div").html(fin);
				}); 
			} else { 
				//("#producto_div").html('');
			};
		};

        function cargarDispositivo() {
			
			var textoBusqueda = $("select#productos").val();
			
			if (textoBusqueda != "") {
				$.post("busca_dispositivos.php", {valorBusqueda: textoBusqueda}, function(fin) {
					$("#dispositivo_div").html(fin);
				}); 
			} else { 
				//("#producto_div").html('');
			};
		};

        /*
        function CargarDispositivo(producto) {
            $('#productos').ready(function() {
            $.ajax({
                    type: "POST",
                    url: "busca_dispositivos.php",
                    data: 'producto='+producto,
                    success: function(response)
                    {
                        $('#dispositivo_div select').html(response).fadeIn();
                    }
            });

        });
        }
        */

    </script>

</head>
<body>
    
<form action="insert_incidencia.php" class="form-container" method="post">

    <div class="card">
        <div class="encabezado">
            <div class="row caja_titulo">
                <h3 class="titulo">Alta incidencia</h3>
            </div>
        </div>
        
        <div class="card-body mx-4 mt-4">
            
            <div>
                <?php
                    require('conexion.php');
                    $sql="select current_date;";
                    $query=mysqli_query($enlace,$sql);
                    while($fields=mysqli_fetch_assoc($query)){
                        $fecha_actual=$fields['current_date'];
                    }
                ?>
                <label for="">Fecha de apertura:</label>
                <input type="date" name="apertura" value="<?php echo $fecha_actual; ?>" required>
                
            </div>

            <div>
                <label for="">Seleccione Tipo de Producto:</label>
                <select name="familia" id="familia" onchange="cargarProductos();" required>
                    <option value="">Elija un Tipo de Producto</option>
                    <?php
                        //-----------------LISTADO-----------------//
                        $sql= "SELECT `id_familia_prod`,`nombre_familia_prod` FROM `familia_producto`;";
                        
                        $query=mysqli_query($enlace,$sql);

                        while($fields=mysqli_fetch_assoc($query)){
                            echo "<option value='".$fields['id_familia_prod']."'>".$fields['nombre_familia_prod']."</option>";
                        }
                    ?>
                </select>
            </div>


            
            <div id="producto_div">
                <label>Producto: </label>
                <select name="productos" id="productos" onchange="cargarDispositivo();" required></select>
            </div>
              
                
            <div id="dispositivo_div">
                <label>Dispositivo: </label>
                <select name="dispositivos" id="dispositivos" required></select>
            </div>
            
            <div>
                <label for="">Descripción: </label>
                <!-- <input type="text" name="descripcion" id=""> -->
                <textarea name="descripcion" id="" cols="30" rows="10" maxlength=255 required></textarea>
            </div>

            <div>
                <select name="estado" id="">
                        <option value="">Seleccione Estado</option>
                        <?php
                        
                        //-----------------LISTADO-----------------//
                        $sql= "SELECT `id_estado`,`nombre_estado` FROM `estado`";
                        
                        $query=mysqli_query($enlace,$sql);

                        while($fields=mysqli_fetch_assoc($query)){
                            $id=$fields["id_estado"];
                            echo"<option value='$id'>".$fields["nombre_estado"]."</option>";
                        
                        }
                    ?>
                </select>
            </div>

            <div>
            <label>Ubicación Destino</label>
                <select name="ubicacion" id="ubicacion">
                    <option value="">Seleccione Ubicación Destino</option>
                    <?php
                        
                        //-----------------LISTADO-----------------//
                        $sql= "SELECT `id_sala`,`nombre_sala` FROM `sala` order by `curso_asignado_sala` ASC";
                        
                        $query=mysqli_query($enlace,$sql);

                        while($fields=mysqli_fetch_assoc($query)){
                            $id=$fields["id_sala"];
                            echo"<option value='$id'>".$fields['nombre_sala']."</option>";
                        
                        }
                    ?>
                </select>
            </div>
            
            <div class="text-center mb-4">
                <input type="submit" value="Alta" class="btn boton waves-effect waves-light">
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


<?php //CONTROL DE ÉXITO O FRACASO DE ALTA --> Uso el auxiliar 'alta'
        if(isset($_GET['alta'])){
            $alta=$_GET['alta'];
            
            if ($alta==1) {
                echo "<div><span class='alert alert-warning text-center'> Existe INCONGRUENCIA en la B.B. D.D. con la NUEVA UBICACIÓN del dispositivo </span></div>";
            }else if($alta==2){
                echo "<div><span class='alert alert-warning text-center'> Existe INCONGRUENCIA en la B.B. D.D. con el NUEVO ESTADO del dispositivo</span></div>"; 
            }else if($alta==3){
                echo "<div><span class='alert alert-warning text-center'> Error al crear la incidencia, revise si no está duplicada.</span></div>";
            }else{
                echo "<div> <span class='alert alert-success text-center'> Incidencia Creada con éxito </span> </div>";
            }
        }
        
        mysqli_close($enlace);
        
?>

</body>
</html>