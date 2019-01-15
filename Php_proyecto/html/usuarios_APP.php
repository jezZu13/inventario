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
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/materialize.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/estilos.css">

	<script>
		$(document).ready(function() {
	    	$('.mdb-select').material_select();
		});
	</script>


</head>
<body>

<section class="form-simple">
	<form action="alta_usuarios_APP.php" method="post">
	    <div class="card">
	        <div class="encabezado">
	            <div class="row caja_titulo">
	                <h3 class="titulo">Alta de usuarios para el acceso a la aplicación</h3>
	            </div>
	        </div>
	        <div class="card-body mx-4 mt-4">
	            <div>
	                <label for="">Nombre</label>
	                <input type="text" name="nombre_usuario" required>
	            </div>

	            <div>
	                <label for="">Password</label>
	                <input type="password" name="pass_usuario" required>
	            </div>
				<div class="pb-3"">
					<select name="rol_usuario" id="">
						<option value="administrador">Administrador</option>
						<option value="basico">Básico</option>
					</select>
				</div>

	            <div class="text-center mb-4">
	            	<button type="submit" value="Confirmar" class="btn boton waves-effect waves-light">Confirmar</button>
	            </div>
	        </div>
	    </div>
    </form>

</section>
            
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/popper.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
	<script src="js/funciones.js"></script>

</body>
</html>