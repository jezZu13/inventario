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

        $id_producto=$_GET['id_mod_prod'];
        
        $query="select producto.id_prod, producto.nombre_prod, producto.descripcion_prod, producto.id_marca, producto.id_familia_prod, producto.foto_prod, marca.nombre_marca, familia_producto.nombre_familia_prod from producto, marca, familia_producto where (producto.id_marca=marca.id_marca) and (producto.id_familia_prod=familia_producto.id_familia_prod) and (id_prod=".$id_producto.");";

		$result=mysqli_query($enlace,$query) 
            or die("Error");
        
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            $nombre_prod=$row['nombre_prod'];
            $descripcion_prod=$row['descripcion_prod'];
            $id_marca=$row['id_marca'];
            $id_familia_prod=$row['id_familia_prod'];
            $nombre_marca=$row['nombre_marca'];
			$nombre_familia_prod=$row['nombre_familia_prod'];
			$foto=$row['foto_prod'];
        }
    ?>
    
	<form action="modificacion_producto.php?id_prod=<?php echo $id_producto;?>" class="form-container" method="post" enctype="multipart/form-data">
		<div class="card">
			<div class="encabezado">
				<div class="row caja_titulo">
					<h3 class="titulo">Modificar producto</h3>
				</div>
			</div>
			
			<div class="card-body mx-4 mt-4">

					<div>
						<label class="marca" for="">Marca</label>

						<?php
							require("conexion.php");

							$query="SELECT id_marca, nombre_marca FROM marca;";

							$result=mysqli_query($enlace,$query) 
								or die("No se han podido cargar las marcas");
							
							echo " <select name=\"marca\" id=\"\"> ";
							while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
								if($row['id_marca']==$id_marca){
									echo "<option selected value=\"" .$row['id_marca']. "\">" .$row['nombre_marca']. "</option> ";
								}
								else{
									echo "<option value=\"" .$row['id_marca']. "\">" .$row['nombre_marca']. "</option> ";
								}
							}
							echo " </select> ";
							
						?>

					</div>

					<div>
						<label for="">Tipo</label>
						<?php

							$query="SELECT id_familia_prod, nombre_familia_prod FROM familia_producto;";

							$result=mysqli_query($enlace,$query) 
								or die("No se han podido cargar los tipos de productos");
							
							echo " <select name=\"tipo\" id=\"\"> ";
							while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
								if($row['id_familia_prod']==$id_familia_prod){
									echo "<option selected value=\"" .$row['id_familia_prod']. "\">" .$row['nombre_familia_prod']. "</option> ";
								}
								else{
									echo "<option value=\"" .$row['id_familia_prod']. "\">" .$row['nombre_familia_prod']. "</option> ";
								}
							}
							echo " </select> ";
							
						?>
					</div>

					<div>
						<label for="">Nombre del producto</label>
						<input type="text" name="nombre_producto" required  value="<?php echo $nombre_prod; ?>">
					</div>

					<div class="pb-3">
						<label for="">Características</label>
						<input type="text" name="descripcion"  value="<?php echo $descripcion_prod; ?>">
					</div>

					<div class="pb-3">
						<label for="">Imagen</label>
						<?php

							if($foto!=NULL){
								echo "<input type='text' value='SI posee imagen' class='field left' readonly>";
							}
							else{
								echo "<input type='text' value='NO tiene vinculada ninguna imagen' class='field left' readonly>";
							}
						?>
					</div>

					<div class="pb-3">
						<input type="file" id="imagen" name="imagen" size="30" />
					</div>

					<div class="text-center mb-4">
						<button type="submit" value="Modificar" class="btn boton waves-effect waves-light">Confirmar</button>
					</div>

				</form>
			</div>
		</div>

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