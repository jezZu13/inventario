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
	
	function openAltaProveedor(){
		window.open("proveedor.php", "_blank", "width=500px, height=750px");
	}
	</script>


</head>
<body>
	<form action="alta_pedido.php" class="form-container" method="post">
		<div class="card">
			<div class="encabezado">
				<div class="row caja_titulo">
					<h3 class="titulo">Alta pedido</h3>
				</div>
			</div>
			<div class="card-body mx-4 mt-4">

				<?php
					require("conexion.php");

					$query="SELECT id_ped FROM pedido ORDER BY id_ped;";
					$result=mysqli_query($enlace,$query);
					while($row=mysqli_fetch_array($result,MYSQLI_ASSOC)){
						$ultimo_codigo_ped=$row['id_ped'];
					}
					$ultimo_codigo_ped="REF00".($ultimo_codigo_ped+1);
				?>

					<div>
						<label for="">Número de referencia</label>
						<input type="text" name="nref" placeholder="Nº de ref." value="<?php echo $ultimo_codigo_ped; ?>" required>
					</div>

					<div>
						<label for="">Fecha de solicitud</label>
						<?php
							$query="SELECT current_date;";
							$result=mysqli_query($enlace,$query) or die("Error");
							while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
								$fecha_actual=$row['current_date'];
							}
						?>
						<input type="date" name="fecha_solicitud" value="<?php echo $fecha_actual; ?>" required>
					</div>

					<div class="pb-3">
						<label for="">Proveedor<i id="icon" class="fas fa-plus" onclick="openAltaProveedor();"></i></label>
						<?php
							

							$query="SELECT id_prov, nombre_prov FROM proveedor;";

							$result=mysqli_query($enlace,$query) 
								or die("No se han podido cargar los diferentes proveedores");
							
							echo "<select name='proveedor'>";
							while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
								if($row['nombre_prov']!="Desconocido"){
									echo "<option value='".$row['id_prov']."'>" .$row['nombre_prov']. "</option>";
								}
								
							}
							echo " </select> ";
							
							mysqli_close($enlace);
						?>
					</div>
					
					<div class="text-center mb-4">
						<button type="submit" value="Seguir" class="btn boton waves-effect waves-light">Confirmar</button>
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