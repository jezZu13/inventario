<?php include('header.php'); ?>
<section class="disp">
	<?php
		
		require("conexion.php");

		$query="SELECT ubicacion_dispositivo.*, dispositivo.id_disp, dispositivo.identificador_disp, dispositivo.sn_disp, dispositivo.fecha_alta_disp, dispositivo.id_producto, producto.nombre_prod, sala.nombre_sala FROM ubicacion_dispositivo, dispositivo, producto, sala WHERE (ubicacion_dispositivo.id_disp=dispositivo.id_disp)&&(dispositivo.id_producto=producto.id_prod)&&(ubicacion_dispositivo.id_sala=sala.id_sala) ORDER BY dispositivo.id_disp, ubicacion_dispositivo.fecha_entrada_disp_sala;";

		$result=mysqli_query($enlace,$query) 
			or die("Error");

		echo "<h1 class='pb-3 pt-3 text-center'>Histórico - Ubicación de dispositivos</h1>";
		
		echo "<table id='example' class='table table-striped table-hover table-secondary text-center'>"; 
				echo "<thead class='encabezado_tabla'>";
					echo "<tr>";
						echo "<th>Identificador dispositivo</th>";
						echo "<th>Serial Number</th>";
						echo "<th>Producto</th>";
						echo "<th>Sala</th>";
						echo "<th>Fecha entrada en sala</th>";
						echo "<th>Fecha salida de la sala</th>";
					echo "</tr>";
			echo "</thead>";

		echo "<tbody>";
		while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
			echo "<tr>";

				echo "<td>". $row['identificador_disp'] ."</td>";
				echo "<td>". $row['sn_disp'] ."</td>";
				echo "<td>". $row['nombre_prod'] ."</td>";
				echo "<td>". $row['nombre_sala'] ."</td>";
				
				if(($row['fecha_entrada_disp_sala']==NULL)||($row['fecha_entrada_disp_sala']=="0000-00-00")){
					echo "<td>Desconocido</td>";
				}
				else{
					echo "<td>". $row['fecha_entrada_disp_sala'] ."</td>";
				}
				
				if(($row['fecha_salida_disp_sala']==NULL)||($row['fecha_salida_disp_sala']=="0000-00-00")){
					echo "<td>Permanece</td>";
				}
				else{
					echo "<td>". $row['fecha_salida_disp_sala'] ."</td>";
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