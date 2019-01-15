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
	<form action="alta_proveedor.php" class="form-container" method="post">
		<div class="card">
			<div class="encabezado">
				<div class="row caja_titulo">
					<h3 class="titulo">Alta proveedor</h3>
				</div>
			</div>
			<div class="card-body mx-4 mt-4">
					<div>
						<label for="">Nombre del proveedor</label>
						<input type="text" name="nombre_proveedor" required placeholder="Nombre de la empresa">
					</div>

					<div>
						<label for="">Nombre persona de contacto</label>
						<input type="text" name="persona_contacto" placeholder="nombre y apellidos">
					</div>

					<div>
						<label for="">Teléfono</label>
						<input type="text" name="telefono" placeholder="nº de telefono">
					</div>

					<div>
						<label for="">Dirección</label>
						<input type="text" name="direccion" placeholder="calle, número, ciudad, provincia, pais">
					</div>

					<div>
						<label for="">Email</label>
						<input type="text" name="email" placeholder="email@ejemplo.com">
					</div>

					<div class="pb-3">
						<label for="">Descripción</label>
						<input type="text" name="descripcion" placeholder="descripcion">				
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