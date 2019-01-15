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

        $id_disp=$_GET['id_mod_disp'];
        
		$query="SELECT dispositivo.*, producto.nombre_prod, pedido.codigo_ped, origen_equipo.nombre_ori_eq, estado_dispositivo.id_estado, estado.nombre_estado, estado_dispositivo.fecha_fin_est_disp, ubicacion_dispositivo.id_sala, sala.nombre_sala FROM dispositivo, producto, pedido, origen_equipo, estado_dispositivo, estado, ubicacion_dispositivo, sala WHERE (dispositivo.id_producto=producto.id_prod) && (dispositivo.id_ped=pedido.id_ped) && (dispositivo.id_ori_eq=origen_equipo.id_ori_eq) && (estado_dispositivo.id_disp=dispositivo.id_disp) && (estado_dispositivo.id_estado=estado.id_estado) && (estado_dispositivo.fecha_fin_est_disp is NULL) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.fecha_salida_disp_sala is NULL)&&(ubicacion_dispositivo.id_sala=sala.id_sala)&&(dispositivo.id_disp=$id_disp);";

		$result=mysqli_query($enlace,$query) 
            or die("Error");
        
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
			$identificador_disp=$row['identificador_disp'];
			$sn_disp=$row['sn_disp'];
			$fecha_alta_disp=$row['fecha_alta_disp'];
			$MAC_disp=$row['MAC_disp'];
			$observaciones_disp=$row['observaciones_disp'];
			$id_producto=$row['id_producto'];
			$id_ori_eq=$row['id_ori_eq'];
			$codigo_ped=$row['codigo_ped']; //Lo necesitamos para realizar las comprobaciones en el fichero modificación_producto.php
			$id_estado=$row['id_estado'];
			$id_sala=$row['id_sala'];
		}
		
    ?>
    

	<form action="modificacion_dispositivo.php?id_disp=<?php echo $id_disp;?>&id_estado_actual=<?php echo $id_estado;?>&id_sala_actual=<?php echo $id_sala;?>&codigo_ped=<?php echo $codigo_ped;?>" class="form-container" method="post">
		<div class="card">
			<div class="encabezado">
				<div class="row caja_titulo">
					<h3 class="titulo">Modificar dispositivo</h3>
				</div>
			</div>
			
			<div class="card-body mx-4 mt-4">

					<div>
						<label for="">Identificador</label>
						<input type="text" name="identificador_disp" value="<?php echo $identificador_disp; ?>">
					</div>

					<div>
						<label for="">Serial Number</label>
						<input type="text" name="sn_disp"  value="<?php echo $sn_disp; ?>">
					</div>

					<div>
						<label for="">Fecha de alta</label>
						<input type="date" name="fecha_alta_disp"  value="<?php echo $fecha_alta_disp; ?>">
					</div>

					<div>
						<label for="">MAC Address</label>
						<input type="text" name="MAC_disp"  value="<?php echo $MAC_disp; ?>">
					</div>

					<div>
						<label for="">Observaciones</label>
						<input type="text" name="observaciones_disp"  value="<?php echo $observaciones_disp; ?>">
					</div>

					<div>
						<label for="">Producto</label>
						<?php
							$query="SELECT id_prod, nombre_prod FROM producto;";

							$result=mysqli_query($enlace,$query) 
								or die("No se han podido cargar los productos");
							
							echo " <select name=\"id_prod\" id=\"\"> ";
							while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
								if($row['id_prod']==$id_producto){
									echo "<option selected value=\"" .$row['id_prod']. "\">" .$row['nombre_prod']. "</option> ";
								}
								else{
									echo "<option value=\"" .$row['id_prod']. "\">" .$row['nombre_prod']. "</option> ";
								}
							}
							echo " </select> ";
						?>
					</div>

					<div>
						<label for="">Origen del dispositivo</label>
						<?php
							$query="SELECT id_ori_eq, nombre_ori_eq FROM origen_equipo;";

							$result=mysqli_query($enlace,$query) 
								or die("No se han podido cargar los datos");
							
							echo " <select name=\"id_ori_eq\" id=\"\"> ";
							while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
								if($row['id_ori_eq']==$id_ori_eq){
									echo "<option selected value=\"" .$row['id_ori_eq']. "\">" .$row['nombre_ori_eq']. "</option> ";
								}
								else{
									echo "<option value=\"" .$row['id_ori_eq']. "\">" .$row['nombre_ori_eq']. "</option> ";
								}
							}
							echo " </select> ";
						?>
					</div>

					<div>
						<label for="">Estado actual</label>
						<?php
							$query="SELECT id_estado, nombre_estado FROM estado;";

							$result=mysqli_query($enlace,$query) 
								or die("No se han podido cargar los datos");
							
							echo " <select name=\"id_estado_new\" id=\"\"> ";
							while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
								if($row['id_estado']==$id_estado){
									echo "<option selected value=\"" .$row['id_estado']. "\">" .$row['nombre_estado']. "</option> ";
								}
									else if($row['id_estado']!="16"){ //El id_estado 16 es "descatalogado". No queremos que lo pueda seleccionar el usuario en este caso.
										echo "<option value=\"" .$row['id_estado']. "\">" .$row['nombre_estado']. "</option> ";
									}
							}
							echo " </select> ";
						?>
					</div>

					<div class="pb-3">
						<label for="">Ubicación actual</label>
						<?php
							$query="SELECT id_sala, nombre_sala FROM sala;";

							$result=mysqli_query($enlace,$query) 
								or die("No se han podido cargar los datos");
							
							echo " <select name=\"id_sala_new\" id=\"\"> ";
							while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
								if($row['id_sala']==$id_sala){
									echo "<option selected value=\"" .$row['id_sala']. "\">" .$row['nombre_sala']. "</option> ";
								}
									else if($row['id_sala']!="7"){//El id_sala 7 es "basura". No queremos que lo pueda seleccionar el usuario en este caso.
										echo "<option value=\"" .$row['id_sala']. "\">" .$row['nombre_sala']. "</option> ";
									}
							}
							echo " </select> ";
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