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

        $id_sala=$_GET['id_sala'];
        
        $query="SELECT id_tipo_sala, id_edif, id_sala, nombre_sala, usuario_sala, curso_asignado_sala, descripcion_sala FROM sala WHERE `sala`.`id_sala` = '$id_sala';";


		$result=mysqli_query($enlace,$query) 
            or die("Error");
        
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            $nombre_sala=$row['nombre_sala'];
            $usuario_sala=$row['usuario_sala'];
            $curso_asignado_sala=$row['curso_asignado_sala'];
            $descripcion_sala=$row['descripcion_sala'];
            $id_edif = $row['id_edif'];
    		$id_tipo_sala = $row['id_tipo_sala'];
        }
    ?>
    
	<form action="modificacion_sala.php?id_sala=<?php echo $id_sala;?>" class="form-container" method="post">
		
		<div class="card">
			<div class="encabezado">
				<div class="row caja_titulo">
					<h3 class="titulo">Modificar origen</h3>
				</div>
			</div>
			
			<div class="card-body mx-4 mt-4">
					<div>
						<label for="">Nombre de Sala</label>
						<input type="text" name="nombre_sala"  value="<?php echo $nombre_sala; ?>">
					</div>

					<div>
						<label for="">Usuario</label>
						<input type="text" name="usuario_sala"  value="<?php echo $usuario_sala; ?>">
					</div>

					<div>
						<label for="">Curso asignado</label>
						<input type="text" name="curso_asignado_sala"  value="<?php echo $curso_asignado_sala; ?>">
					</div>

					<div>
						<label for="">Descripción</label>
						<input type="text" name="descripcion_sala"  value="<?php echo $descripcion_sala; ?>">
					</div>

					<div>
						<label for="">Tipo de sala</label>
						<?php
							$query="SELECT id_tipo_sala, nombre_tipo_sala FROM tipo_sala";
							$result=mysqli_query($enlace,$query)
								or die("No se han podido cargar los tipos de sala");
							echo "<select name=\"tipo_sala\">";
							while($row=mysqli_fetch_array($result, MYSQLI_ASSOC)){
								if($row['id_tipo_sala']==$id_tipo_sala){
									echo "<option selected value=\"" .$row['id_tipo_sala']."\">".$row['nombre_tipo_sala']."</option>";
								}else{
									echo "<option value=\"" .$row['id_tipo_sala']."\">".$row['nombre_tipo_sala']."</option>";
								}
							}
							echo "</select>";
						?>
					</div>

					<div class="pb-3">
						<label for="">Edificio</label>
						<?php
							require("conexion.php");
							$query="SELECT id_edif, nombre_edif FROM edificio";
							$result=mysqli_query($enlace,$query)
								or die("No se han podido cargar los edificios");
							echo "<select name=\"edif\">";
							while($row=mysqli_fetch_array($result, MYSQLI_ASSOC)){
								if($row['id_edif']==$id_edif){
									echo "<option selected value=\"" .$row['id_edif']."\">".$row['nombre_edif']."</option>";
								}else{
									echo "<option value=\"" .$row['id_edif']."\">".$row['nombre_edif']."</option>";
								}
								
							}
							echo "</select>";

						?>
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