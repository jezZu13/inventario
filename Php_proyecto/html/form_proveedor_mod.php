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

        $id_proveedor=$_GET['id_mod_prov'];
        
        $query="SELECT * FROM proveedor WHERE id_prov=".$id_proveedor.";";

		$result=mysqli_query($enlace,$query) 
            or die("Error");
        
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            $nombre_prov=$row['nombre_prov'];
            $persona_contact_prov=$row['persona_contact_prov'];
            $telefono_prov=$row['telefono_prov'];
            $direccion_prov=$row['direccion_prov'];
            $email_prov=$row['email_prov'];
            $descripcion_prov=$row['descripcion_prov'];
        }
    ?>
    
	<form action="modificacion_proveedor.php?id_prov=<?php echo $id_proveedor;?>" class="form-container" method="post">
		<div class="card">
			<div class="encabezado">
				<div class="row caja_titulo">
					<h3 class="titulo">Modificar proveedor</h3>
				</div>
			</div>
			
			<div class="card-body mx-4 mt-4">

					<div>
						<label for="">Nombre del proveedor</label>
						<input type="text" name="nombre_proveedor" value="<?php echo $nombre_prov; ?>" required>
					</div>

					<div>
						<label for="">Nombre persona de contacto</label>
						<input type="text" name="persona_contacto"  value="<?php echo $persona_contact_prov; ?>">
					</div>

					<div>
						<label for="">Teléfono</label>
						<input type="text" name="telefono"  value="<?php echo $telefono_prov; ?>">
					</div>

					<div>
						<label for="">Dirección</label>
						<input type="text" name="direccion"  value="<?php echo $direccion_prov; ?>">
					</div>

					<div>
						<label for="">Email</label>
						<input type="text" name="email"  value="<?php echo $email_prov; ?>">
					</div>

					<div class="pb-3">
						<label for="">Descripción</label>
						<input type="text" name="descripcion"  value="<?php echo $descripcion_prov; ?>">				
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