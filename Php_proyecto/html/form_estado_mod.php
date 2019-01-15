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
</head>
<body>

    <?php
        require("conexion.php");

        $id_estado=$_GET['id_mod_estado'];
        
        $query="SELECT * FROM estado WHERE id_estado=".$id_estado.";";

		$result=mysqli_query($enlace,$query) 
            or die("Error");
        
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            $nombre_estado=$row['nombre_estado'];
            $descripcion_estado=$row['descripcion_estado'];
        }
    ?>
    

	<form action="modificacion_estado.php?id_estado=<?php echo $id_estado;?>" class="form-container" method="post">
		<div class="card">
			<div class="encabezado">
				<div class="row caja_titulo">
					<h3 class="titulo">Modificar estado</h3>
				</div>
			</div>
			
			<div class="card-body mx-4 mt-4">

					<div>
						<label for="">Nombre estado</label>
						<input type="text" name="nombre_estado" value="<?php echo $nombre_estado; ?>" required>
					</div>

					<div>
						<label for="">Descripción</label>
						<input type="text" name="descripcion"  value="<?php echo $descripcion_estado; ?>">
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