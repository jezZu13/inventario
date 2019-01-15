<?php include('header.php'); ?>	
	<script>
	
		function modificarEstado(id_ped){
			window.open("listar_detalle_pedido.php?id_ped="+id_ped,"_self");
		}
	</script>
	<section class="disp">
	<h1 class="text-center mt-4">Pedido</h1>
	<?php
		
		require("conexion.php");

		$query="SELECT pedido.*, proveedor.nombre_prov FROM pedido, proveedor WHERE pedido.id_prov=proveedor.id_prov;";

		$result=mysqli_query($enlace,$query) 
			or die("Error");
		
			echo "<table id='example' class='table table-striped table-hover table-secondary text-center'>"; 
			
			echo "<thead class='encabezado_tabla'>";
			echo "<tr>";
			echo "<th>Nº de referencia</th>";
			echo "<th>Estado</th>";
			echo "<th>Fecha solicitud</th>";
			echo "<th>Fecha recepción</th>";
			echo "<th>Proveedor</th>";
			echo "<th>Detalles y modificaciones</th>";
			echo "</tr>";
			echo "</thead>";
			echo "</tbody>";

		while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
			if($row['codigo_ped']!="Desconocido"){ //No queremos que el usuario vea que existe un pedido llamado "desconocido".
				echo "<tr>";
					echo "<td>". $row['codigo_ped'] ."</td>";
					if($row['estado']==0){
						echo "<td>En curso</td>";
					}
					else{
						echo "<td>Cerrado</td>";
					}
					
					echo "<td>". $row['fecha_pet_ped'] ."</td>";
					if($row['fecha_recepcion_ped']==NULL){
						echo "<td>Pendiente de entrega</td>";
					}
					else{
						echo "<td>". $row['fecha_recepcion_ped'] ."</td>";
					}
					echo "<td>". $row['nombre_prov'] ."</td>";
					?>
						<td width="10%"><a href='#' onclick="modificarEstado('<?php echo $row['id_ped'];?>');"><i class="fa fa-pencil iconos" title="Modificar"></i></a></td>

					<?php
				echo "</tr>";
			}
		}

			echo "</tbody>";
			echo "</table>";

			if(isset($_GET['error'])){
				?>
				<script>alert("No se puede eliminar el registro puesto que existen dependencias");</script>
				<?php
			}

		mysqli_close($enlace);

	?>

</section>	
</body>
</html>