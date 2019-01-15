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
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

	<script>
	
		function openAltaTipoSala(){
			window.open("tipo_sala.php", "_blank", "width=500px, height=500px");
		}

		function openAltaEdificio(){
			window.open("edificio.php", "_blank", "width=500px, height=500px");
		}

	</script>

</head>
<body>
	<form action="alta_sala.php" class="form-container" method="post">
		<div class="card">
			<div class="encabezado">
				<div class="row caja_titulo">
					<h3 class="titulo">Alta nueva sala</h3>
				</div>
			</div>
			<div class="card-body mx-4 mr-4">
					<div>
						<label for="">Nombre</label>
						<input type="text" class="nombre" name="nombre_sala" required>
					</div>
					<div>
						<label for="">Usuario</label>
						<input type="text" class="user" name="usuario_sala">
					</div>
					<div>
						<label for="">Curso</label>
						<input type="text" class="curso" name="curso_asignado_sala">
					</div>
					<div>
						<label for="">Descripción</label>
						<input type="text" class="marca" name="descripcion_sala">
					</div>
					<div>
						<label for="">Edificio<i id="icon" class="fas fa-plus" onclick="openAltaEdificio();"></i></label>
						<?php
							require("conexion.php");
							$query="SELECT id_edif, nombre_edif FROM edificio";
							$result=mysqli_query($enlace,$query)
								or die("No se han podido cargar los edificios");
							echo "<select name=\"edif\">";
							while($row=mysqli_fetch_array($result, MYSQLI_ASSOC)){
								echo "<option value=\"" .$row['id_edif']."\">".$row['nombre_edif']."</option>";
							}
							echo "</select>";

						?>
					</div>
					<div class="pb-3">
						<label for="">Tipo de sala<i id="icon" class="fas fa-plus" onclick="openAltaTipoSala();"></i></label>
						<?php
							$query="SELECT id_tipo_sala, nombre_tipo_sala FROM tipo_sala";
							$result=mysqli_query($enlace,$query)
								or die("No se han podido cargar los tipos de sala");
							echo "<select name=\"tipo\">";
							while($row=mysqli_fetch_array($result, MYSQLI_ASSOC)){
								echo "<option value=\"" .$row['id_tipo_sala']."\">".$row['nombre_tipo_sala']."</option>";
							}
							echo "</select>";

							mysqli_close($enlace);
						?>
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