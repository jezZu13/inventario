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

		$id_ped=$_GET['id_ped'];
		$id_prod=$_GET['id_prod'];
		$id_ori_eq=$_GET['id_ori_eq'];
        
        $query="SELECT producto_en_pedido.*, producto.nombre_prod, origen_equipo.nombre_ori_eq FROM producto_en_pedido, producto, origen_equipo WHERE (producto_en_pedido.id_ori_eq=origen_equipo.id_ori_eq) and (producto_en_pedido.id_prod=producto.id_prod) and (id_ped='".$id_ped."') and (producto_en_pedido.id_prod='".$id_prod."') and (producto_en_pedido.id_ori_eq='".$id_ori_eq."');";
		


		$result=mysqli_query($enlace,$query) 
            or die("Error");
        
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            $nombre_producto=$row['nombre_prod'];
			$nunidades=$row['nunidades_prod_ped'];
			$preciounidad=$row['precio_por_unidad'];
			$nombre_ori_eq=$row['nombre_ori_eq'];
        }
    ?>
    
	<form action="modificacion_producto_pedido.php?id_ped=<?php echo $id_ped;?>&id_prod=<?php echo $id_prod;?>" class="form-container" method="post">
		<div class="card">
			<div class="encabezado">
				<div class="row caja_titulo">
					<h3 class="titulo">Modificar producto en pedido</h3>		
				</div>
			</div>
			
			<div class="card-body mx-4 mt-4">

					<div>
						<label for="">Producto</label>
						<select name="nombre_producto">
							<option value="<?php echo $id_prod; ?>"><?php echo $nombre_producto; ?></option>
						</select>
					</div>

					<div>
						<label for="">Origen</label>
						<select name="id_ori_eq">
							<option value="<?php echo $id_ori_eq; ?>"><?php echo $nombre_ori_eq; ?></option>
						</select>
					</div>

					<div>
						<label for="">Nº unidades</label>
						<input type="text" name="nunidades"  value="<?php echo $nunidades; ?>">
					</div>

					<div>
						<label for="">Precio por unidad</label>
						<input type="text" name="preciounidad"  value="<?php echo $preciounidad; ?>">
					</div>

					<div class="text-center mb-4">
						<button type="submit" value="Modificar" class="btn boton waves-effect waves-light">Confirmar</button>
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