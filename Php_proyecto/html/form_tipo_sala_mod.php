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

        $id_tipo_sala=$_GET['id_tipo_sala'];
        
        $query="SELECT id_tipo_sala, nombre_tipo_sala, descripcion_tipo_sala FROM tipo_sala WHERE `tipo_sala`.`id_tipo_sala` = '$id_tipo_sala';";

		$result=mysqli_query($enlace,$query) 
            or die("Error");
        
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            $nombre_tipo_sala=$row['nombre_tipo_sala'];
            $descripcion_tipo_sala=$row['descripcion_tipo_sala'];
        }
    ?>
    
	<form action="modificacion_tipo_sala.php?id_tipo_sala=<?php echo $id_tipo_sala;?>" class="form-container" method="post">
		<div class="card">
			<div class="encabezado">
				<div class="row caja_titulo">
					<h3 class="titulo">Modificar Tipo Sala</h3>		
				</div>
			</div>
			
			<div class="card-body mx-4 mt-4">
					<div>
						<label for="">Nombre de Tipo Sala</label>
						<input type="text" name="nombre_tipo_sala"  value="<?php echo $nombre_tipo_sala; ?>">
					</div>

					<div class="pb-3">
						<label for="">Descripción</label>
						<input type="text" name="descripcion_tipo_sala"  value="<?php echo $descripcion_tipo_sala; ?>">
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