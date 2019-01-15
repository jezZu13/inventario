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

        $id_ori_eq=$_GET['id_ori_eq'];
        
        $query="SELECT id_ori_eq, nombre_ori_eq, descripcion_ori_eq FROM origen_equipo WHERE `origen_equipo`.`id_ori_eq` = '$id_ori_eq';";

		$result=mysqli_query($enlace,$query) 
            or die("Error");
        
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            $nombre_ori_eq=$row['nombre_ori_eq'];
            $descripcion_ori_eq=$row['descripcion_ori_eq'];
        }
    ?>
    
	<form action="modificacion_origen.php?id_ori_eq=<?php echo $id_ori_eq;?>" class="form-container" method="post">
		<div class="card">
			<div class="encabezado">
				<div class="row caja_titulo">
					<h3 class="titulo">Modificar origen</h3>
				</div>
			</div>
			
			<div class="card-body mx-4 mt-4">
					<div>
						<label for="">Nombre de origen</label>
						<input type="text" name="nombre_ori_eq"  value="<?php echo $nombre_ori_eq; ?>">
					</div>

					<div>
						<label for="">Descripción</label>
						<input type="text" name="descripcion_ori_eq"  value="<?php echo $descripcion_ori_eq; ?>">
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