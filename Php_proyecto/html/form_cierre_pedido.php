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

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/materialize.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/estilos.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
</head>
<body>

	<?php
		$id_ped=$_GET['id_ped'];
		require("conexion.php");

		$queryPedido="SELECT * FROM pedido WHERE id_ped='".$id_ped."';";
		$resultPedido=mysqli_query($enlace,$queryPedido) 
			or die("Error en la carga de los datos del pedido");

		while($rowPedido=mysqli_fetch_array($resultPedido,MYSQLI_ASSOC) ){
			$codigo_ped=$rowPedido['codigo_ped'];
			$fecha_recepcion_ped=$rowPedido['fecha_recepcion_ped'];
		}

		$resultFechaActual=mysqli_query($enlace,"SELECT current_date;") 
			or die("Error en la carta de la fecha actual");
		while($rowfecha=mysqli_fetch_array($resultFechaActual,MYSQLI_ASSOC) ){
			$fechaActual=$rowfecha['current_date'];
		}
	?>

	<form action="cierre_pedido.php?id_ped=<?php echo $id_ped; ?>" class="form-container" method="post">
		<div class="card">
			<div class="encabezado">
				<div class="row caja_titulo">
					<h3 class="titulo">Cierre del pedido <?php echo "\"$codigo_ped\""; ?></h3>		
				</div>
			</div>
			
			<div class="card-body mx-4 mt-4">

					<div class="pb-3">
						<label for="">Fecha de recepción:</label>
						<input type="date" name="fecha_recepcion_ped" value="<?php echo $fechaActual; ?>"  required>
					</div>
					
					<?php
						$queryOriEqui="SELECT * FROM origen_equipo;";
						$resultOriEqui=mysqli_query($enlace,$queryOriEqui) 
							or die("Error en la carga de los datos del pedido");
					?>
					
					<div class="text-center mb-4">
						<button class="btn boton waves-effect waves-light" type="submit">Confirmar Cierre</button>
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