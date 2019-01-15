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
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	<script>
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

		function openAltaMarca(){
			window.open("marca.php", "_blank", "width=500px, height=500px");
		}

		function openAltaTipoProducto(){
			window.open("familia_producto.php", "_blank", "width=500px, height=500px");
		}

		function openAltaProducto(){
			window.open("producto.php", "_blank", "width=500px, height=700px");
		}

		function openAltaOrigen(){
			window.open("origen.php", "_blank", "width=500px, height=500px");
		}

		function comprobarCantidad(){
			var cantidad=document.getElementById("cantidad").value;
			
			if(document.getElementById("cantidad").value!=""){

				if((isNaN(cantidad)==false)&&(cantidad>0)){
					cantidad=parseInt(cantidad);
					document.getElementById("cantidad").value=cantidad;
				}
				else{
					alert("En el campo cantidad solo puede ingresar números positivos mayores de 0");
					document.getElementById("cantidad").value="";
				}
			}
			else{
				alert("Le informamos que es obligatorio especificar una cantidad antes de confirmar el alta");
			}
		}

	</script>

</head>
<body>
	<form action="alta_dispositivo.php" class="form-container" method="post">
				<div class="card">
					<div class="encabezado">
						<div class="row caja_titulo">
							<h3 class="titulo">Alta de nuevo dispositivo</h3>
						</div>
					</div>
					<div class="card-body mx-4 mt-4">
						<div>
							<!-- label for="">Marca<a href="marca.php" target="_blank"><i id="icon" class="fas fa-plus"></i></a></label> -->
							<label for="">Marca<i id="icon" class="fas fa-plus" onclick="openAltaMarca();"></i></label> 
							<?php
								require("conexion.php");

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
							<label for="">Tipo<i id="icon" class="fas fa-plus" onclick="openAltaTipoProducto();"></i></label>
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
							<?php
							/*
								$query="SELECT id_prod, nombre_prod FROM producto;";

								$result=mysqli_query($enlace,$query) 
									or die("No se han podido cargar los productos");
								
								echo " <select name='producto' id=''> ";
								while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
									echo "<option value='".$row['id_prod']. "'>" .$row['nombre_prod']. "</option>";
								}
								echo " </select> ";
							*/
							?>
						</div>

						<div>
							<label for="">Origen del dispositivo<i id="icon" class="fas fa-plus" onclick="openAltaOrigen();"></i></label>
							<?php
							
								$query="SELECT id_ori_eq, nombre_ori_eq FROM origen_equipo;";

								$result=mysqli_query($enlace,$query) 
									or die("No se han podido cargar los origenes");
								
								echo " <select name='origen_equipo' id=''> ";
								while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
									echo "<option value='".$row['id_ori_eq']. "'>" .$row['nombre_ori_eq']. "</option>";
								}
								echo " </select> ";
							
							?>
						</div>

					<div>
						<label class="pedido_form" for="">Identificador</label>
						<input type="text" name="identificador_disp" placeholder="Identificador">
					</div>

					<div>
						<label class="pedido_form" for="">Serial Number</label>
						<input type="text" name="sn_disp" placeholder="SN">
					</div>

					<div>
						<label class="pedido_form" for="">Fecha de alta</label>
						<?php
							$query="SELECT current_date;";
							$result=mysqli_query($enlace,$query) 
								or die("No se han podido cargar la fecha actual");
							while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
								$fecha_actual=$row['current_date'];
							}
						?>
						<input type="date" name="fecha_alta_disp" value="<?php echo $fecha_actual; ?>" required>
					</div>

					<div>
						<label class="pedido_form" for="">MAC Address</label>
						<input type="text" name="MAC_disp" placeholder="MAC Address">
					</div>

					<div class="pb-3">
						<label class="pedido_form" for="">Observaciones</label>
						<input type="text" name="observaciones_disp" placeholder="Observaciones">
					</div>

					<div class="pb-3">
						<label class="pedido_form" for="">Cantidad</label>
						<input type="text" name="cantidad" id="cantidad" required placeholder="Introduzca el número de elementos que se darán de alta" onfocusout="comprobarCantidad();">
					</div>
					
					<div class="text-center mb-4">
						<button class="btn boton waves-effect waves-light" type="submit">Confirmar</button>
					</div>
				</div>
			</div>
		</form>



	<?php
		mysqli_close($enlace);
	?>

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