<?php include('header.php'); ?>

	<section class="disp">
	<?php
		
		require("conexion.php");

		$query="SELECT estado_dispositivo.*, dispositivo.id_disp, dispositivo.identificador_disp, dispositivo.sn_disp, dispositivo.fecha_alta_disp, dispositivo.id_producto, producto.nombre_prod, estado.nombre_estado FROM estado_dispositivo, dispositivo, producto, estado WHERE (estado_dispositivo.id_disp=dispositivo.id_disp)&&(dispositivo.id_producto=producto.id_prod)&&(estado_dispositivo.id_estado=estado.id_estado) ORDER BY dispositivo.id_disp, estado_dispositivo.fecha_inicio_est_disp;";

		$result=mysqli_query($enlace,$query) 
			or die("Error");

		echo "<h1 class='pb-3 pt-3 text-center'>Histórico - Estado de dispositivos</h1>";
		
			echo "<table id='example' class='table table-striped table-hover table-secondary text-center'>"; 
			echo "<thead class='encabezado_tabla'>";
			echo "<tr>";
			echo "<th>Identificador dispositivo</th>";
			echo "<th>Serial Number</th>";
			echo "<th>Producto</th>";
			echo "<th>Estado</th>";
			echo "<th>Fecha inicio estado</th>";
			echo "<th>Fecha fin estado</th>";
			echo "</tr>";
			echo "</thead>";
			echo "<tbody>";

		while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
			echo "<tr>";

				echo "<td>". $row['identificador_disp'] ."</td>";
				echo "<td>". $row['sn_disp'] ."</td>";
				echo "<td>". $row['nombre_prod'] ."</td>";
				echo "<td>". $row['nombre_estado'] ."</td>";
				
				if(($row['fecha_inicio_est_disp']==NULL)||($row['fecha_inicio_est_disp']=="0000-00-00")){
					echo "<td>Desconocido</td>";
				}
				else{
					echo "<td>". $row['fecha_inicio_est_disp'] ."</td>";
				}
				
				if(($row['fecha_fin_est_disp']==NULL)||($row['fecha_fin_est_disp']=="0000-00-00")){
					echo "<td>Permanece</td>";
				}
				else{
					echo "<td>". $row['fecha_fin_est_disp'] ."</td>";
				}
				
			echo "</tr>";
			}
			echo "</tbody>";
			echo "</table>";

		mysqli_close($enlace);

	?>
	</section>

	
</body>
</html>