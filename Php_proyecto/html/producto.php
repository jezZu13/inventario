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
	<title>Proyecto - Invetario</title>

	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link href="css/materialize.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/estilos.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

	<script>
	
		function openAltaMarca(){
			window.open("marca.php", "_blank", "width=500px, height=500px");
		}

		function openAltaFamiliaProducto(){
			window.open("familia_producto.php", "_blank", "width=500px, height=500px");
		}

	</script>


</head>
<body>
	<form action="alta_producto.php" class="form-container" method="post" enctype="multipart/form-data">
		<div class="card">
			<div class="encabezado">
				<div class="row caja_titulo">
					<h3 class="titulo">Alta de un nuevo producto</h3>
				</div>
			</div>

			<div class="card-body mx-4 mt-4">

					<div>
						<label for="">Marca <i id="icon" class="fas fa-plus" onclick="openAltaMarca();"></i></label>

						<?php
							require("conexion.php");

							$query="SELECT id_marca, nombre_marca FROM marca;";

							$result=mysqli_query($enlace,$query) 
								or die("No se han podido cargar las marcas");
							
							echo " <select name=\"marca\" id=\"\"> ";
							while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
								echo "<option value=\"" .$row['id_marca']. "\">" .$row['nombre_marca']. "</option> ";
							}
							echo " </select> ";
							
						?>

					</div>

					<div>
						<label class="producto_form" for="">Tipo<i id="icon" class="fas fa-plus" onclick="openAltaFamiliaProducto();"></i></label>
						<?php

							$query="SELECT id_familia_prod, nombre_familia_prod FROM familia_producto;";

							$result=mysqli_query($enlace,$query) 
								or die("No se han podido cargar los tipos de productos");
							
							echo " <select name=\"tipo\" id=\"\"> ";
							while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
								echo "<option value=\"" .$row['id_familia_prod']. "\">" .$row['nombre_familia_prod']. "</option> ";
							}
							echo " </select> ";
							
							mysqli_close($enlace);
						?>
					</div>

					<div>
						<label for="">Nombre del producto</label>
						<input type="text" name="nombre_producto" required placeholder="Nombre del producto">
					</div>

					<div class="pb-3">
						<label for="">Características</label>
						<input type="text" name="caracteristicas" placeholder="Caracteristicas">				
					</div>
					
					<div class="pb-3">
						<label for="">Imagen</label> <br>
						<input type="file" id="imagen" name="imagen" size="30" />
					</div>

					<div class="text-center mb-4">
						<button type="submit" value="Confirmar" class="btn boton waves-effect waves-light">Confirmar</button>
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

</body>
</html>