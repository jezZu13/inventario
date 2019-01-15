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

</head>
<body>
	<form action="alta_marca.php" method="post">
	    <div class="card">

	        <div class="encabezado">
	            <div class="row caja_titulo">
	                <h3 class="titulo">Alta Nueva Marca</h3>
	            </div>
	        </div>

	        <div class="card-body mx-4 mt-4">

	            <div>
	                <label for="">Nombre</label>
	                <input type="text" name="marca" required="">
	            </div>

	            <div class="pb-3">
	                <label for="">Descripción</label>
	                <input type="text" name="desc">
	            </div>

	            <div class="text-center mb-4">
	            	  <button class="btn boton waves-effect waves-light" type="submit" name="action" onclick="retornar();">Confirmar
					  </button>
	            </div>

	        </div>

	    </div>
    </form>
            
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/popper.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
	<script src="js/funciones.js"></script>

</body>
</html>