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

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/materialize.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/estilos.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	
	<script>
		function cerrar(){
			this.close();
		}

		function openAltaMarca(){
			window.open("marca.php", "_blank", "width=500px, height=500px");
		}

		function openAltaProducto(){
			window.open("producto.php", "_blank", "width=500px, height=700px");
		}

		function openAltaOrigen(){
			window.open("origen.php", "_blank", "width=500px, height=500px");
		}

		function openAltaFamilia(){
			window.open("familia_producto.php", "_blank", "width=500px, height=500px");
		}

		function buscar() {
			
			var textoBusqueda = $("select#familia_producto").val();
			var textoBusqueda2 = $("select#marca").val();
			
			if ((textoBusqueda != "")||(textoBusqueda2 != "")) {
				$.post("buscar_Ajax.php", {valorBusqueda: textoBusqueda, valorBusqueda2:textoBusqueda2}, function(fin) {
					$("#producto_div").html(fin);
				}); 
			} else { 
				//("#producto_div").html('');
			};
		};
	
	</script>

</head>
<body>

	<form action="alta_producto_pedido.php" class="form-container" method="post">
		<div class="card">
			<div class="encabezado">
				<div class="row caja_titulo">
					<h3 class="titulo">Añadir producto en pedido</h3>
				</div>
			</div>
			
			<div class="card-body mx-4 mt-4">

					<div>
						<label for="">Número referencia pedido</label>
						<?php
							require("conexion.php");

							if(isset($_GET['comprobar'])){
								?>
								<script>
									alert("Duplicado");
								</script>
								<?php
							}

							if(isset($_GET['id_pedido_retorno'])){
								$id_pedido=$_GET['id_pedido_retorno'];
								$query="SELECT id_ped, codigo_ped  FROM pedido WHERE id_ped='".$id_pedido."';";
							}
								else if(isset($_GET['codigo_pedido'])){
									$codigo_pedido_actual=$_GET['codigo_pedido'];
									$query="SELECT id_ped, codigo_ped  FROM pedido WHERE codigo_ped='".$codigo_pedido_actual."';";
								}

							$result=mysqli_query($enlace,$query) 
								or die("No se han podido cargar los diferentes proveedores");
							
							echo "<select name='pedido'>";
							while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
								echo "<option value='".$row['id_ped']."'>" .$row['codigo_ped']. "</option>";
							}
							echo " </select> ";
							
						?>
					</div>
					
					<div>
						<label for="">Marca<i id="icon" class="fas fa-plus" onclick="openAltaMarca();"></i></label>
							<?php
								$query="SELECT id_marca, nombre_marca FROM marca;";

								$result=mysqli_query($enlace,$query) 
									or die("No se han podido cargar las marcas");
									
								echo " <select name='marca' id='marca' onchange='buscar();'>";
									echo "<option value='' selected placeholder='Seleccione el tipo'> </option>";
									while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
										echo "<option value='".$row['id_marca']."'>".$row['nombre_marca']."</option>";
								}
								echo " </select> ";	
							?>
						</div>

					<div>
						<label for="">Tipo<i id="icon" class="fas fa-plus" onclick="openAltaFamilia();"></i></label>
							<?php
								$query="SELECT id_familia_prod, nombre_familia_prod FROM familia_producto;";

								$result=mysqli_query($enlace,$query) 
									or die("No se han podido cargar los tipos de productos");
									
								echo " <select name='familia_producto' id='familia_producto' onchange='buscar();'> ";
									echo "<option value='' selected placeholder='Seleccione el tipo'> </option>";
									while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
										echo "<option value='".$row['id_familia_prod']."'>".$row['nombre_familia_prod']. "</option>";
								}
								echo " </select> ";
							?>
					</div>

					<div id="producto_div">
							<label for="">Producto<i id="icon" class="fas fa-plus" onclick="openAltaProducto();"></i></label>
							<select name="producto" id="" required></select>
					</div>

					<div>
						<label for="">Origen<i id="icon" class="fas fa-plus" onclick="openAltaOrigen();"></i></label>
							<?php
								$query="SELECT id_ori_eq, nombre_ori_eq FROM origen_equipo;";

								$result=mysqli_query($enlace,$query) 
									or die("No se han podido cargar los tipos de productos");
									
								echo " <select name='origen_equipo'> ";
									while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
										echo "<option value='".$row['id_ori_eq']."'>".$row['nombre_ori_eq']. "</option>";
								}
								echo " </select> ";
							?>
					</div>					

					<div>
						<label for="">Nº de unidades por producto</label>
						<input value="0" type="number" name="nunidades">
					</div>

					<div class="pb-3">
						<label for="">Precio por unidad</label>
						<input value="0" type="number" name="preciounidad">
					</div>

					<div class="text-center mb-4">
						<button type="submit" name="botonSeguir" class="btn boton waves-effect waves-light">Registrar y añadir más</button>
						<button type="submit" name="botonFin" class="btn boton waves-effect waves-light">Registrar y finalizar</button>
						<!--<input type="button" value="Finalizar SIN registrar" onclick="cerrar();" class="btn btn-danger"> -->
					</div>

				</form>
			</div>
		</div>

	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/popper.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
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