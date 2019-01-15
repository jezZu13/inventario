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

	<link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/materialize.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/estilos.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
</head>
<body>

    <?php
        require("conexion.php");

        $id_usuario=$_GET['id_usuario'];
        
        $query="SELECT * FROM usuarios_app WHERE `usuarios_app`.`id_usuario` = '$id_usuario';";

		$result=mysqli_query($enlace,$query) 
            or die("Error");
        
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            $nombre_usuario=$row['nombre_usuario'];
            $pass_actual_usuario=$row['pass_usuario'];
        }
    ?>
    
	<form action="modificacion_password.php?id_usuario=<?php echo $id_usuario;?>" class="form-container" method="post">
		<div class="card">
			<div class="encabezado">
				<div class="row caja_titulo">
					<h3 class="titulo">Cambiar password</h3>
				</div>
			</div>
			
			<div class="card-body mx-4 mt-4">
				
					<div>
						<label for="">Nombre de usuario</label>
						<input type="text" name="nombre_usuario"  value="<?php echo $nombre_usuario; ?>">
					</div>

					<div class="pb-3">
						<label class="producto_form" for="">Nueva contraseña:</label>
						<input type="password" name="nueva_passwd"  value="">
					</div>
					<div class="text-center mb-4">
						<button type="submit" class="btn boton waves-effect waves-light">Confirmar</button>
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