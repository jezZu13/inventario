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
		$id_ped=$_GET['id_ped'];
	?>
<form action="modificacion_pedido.php?id_ped=<?php echo $id_ped; ?>" class="form-container" method="post">
		<div class="card">
			<div class="encabezado">
				<div class="row caja_titulo">
				<h3 class="titulo">Modificación datos de un pedido</h3>
				</div>
			</div>
			<div class="card-body mx-4 mt-4">
				
					<?php
						require("conexion.php");

						$queryPedido="SELECT * FROM pedido WHERE id_ped='".$id_ped."';";
						$resultPedido=mysqli_query($enlace,$queryPedido) 
							or die("Error en la carta de los datos del pedido");

						while($rowPedido=mysqli_fetch_array($resultPedido,MYSQLI_ASSOC) ){
							$codigo_ped=$rowPedido['codigo_ped'];
							$fecha_pet_ped=$rowPedido['fecha_pet_ped'];
							$id_prov=$rowPedido['id_prov'];
						}
					?>

					<div>
						<label for="">Número de referencia</label>
						<input type="text" name="nref" value="<?php echo $codigo_ped; ?>" required>
					</div>

					<div>
						<label for="">Fecha de solicitud</label>
						<input type="date" name="fecha_solicitud" value="<?php echo $fecha_pet_ped; ?>"  required>
					</div>
					
					

					<div class="pb-3">
						<label for="">Proveedor</label>
						<?php
							
							//Se carga el combo de proveedores.
							$query="SELECT id_prov, nombre_prov FROM proveedor;";

							$result=mysqli_query($enlace,$query) 
								or die("No se han podido cargar los diferentes proveedores");
							
							echo "<select name='proveedor'>";
							while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
								if($row['nombre_prov']!="Desconocido"){
									if($row['id_prov']==$id_prov){
										echo "<option selected value='".$row['id_prov']."'>" .$row['nombre_prov']. "</option>";
									}
									else{
										echo "<option value='".$row['id_prov']."'>" .$row['nombre_prov']. "</option>";
									}
								}
							}
							echo " </select> ";
							
							mysqli_close($enlace);
						?>
					</div>
					
					<div class="text-center mb-4">
						<button type="submit" value="Modificar" class="btn boton waves-effect waves-light">Confirmar</button>
					</div>
					

				</form>
			</div>
		</div>

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