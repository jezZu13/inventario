<?php include('header.php'); ?>	
	<script>

		function modificarEstado(id_estado){
			window.open("form_estado_mod.php?id_mod_estado="+id_estado,"ModificacionEstado", "width=800px, height=800px");	
		}

		function eliminarEstado(id_estado){
			if(!confirm("¡¡Se va a proceder a eliminar el registro seleccionado!!")){
      	return false;
      }
      else{
      	document.location="borrar_estado.php?id_estado="+id_estado;
      return true;
      }
		}

	</script>
	<section class="disp">
	<h1 class="text-center mt-4">Estado</h1>
	<?php
		
		require("conexion.php");

		$query="SELECT * FROM estado ORDER BY nombre_estado;";

		$result=mysqli_query($enlace,$query) 
			or die("Error");
		
			echo "<table class='table table-striped table-hover table-secondary text-center'>"; 
			
			echo "<thead class='encabezado_tabla'>";
			echo "<tr>";
			echo "<th colspan='2'>Acciones</th>";
			echo "<th>Nombre estado</th>";
			echo "<th>Descripción</th>";
			echo "</tr>";
			echo "</thead>";

		while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
			echo "<tr>";

				if( ($row['nombre_estado']=="En stock") || ($row['nombre_estado']=="Descatalogado")){
				?>
					<td></td>
					
					<td></td>

				<?php
				}
				else{
					?>
						<td width="5%"><a href='#' onclick="modificarEstado('<?php echo $row['id_estado'];?>');"><i class="fa fa-pencil iconos" title="Modificar"></i></a></td>
						
						<td width="5%"><a href='#' onclick="eliminarEstado('<?php echo $row['id_estado'];?>');"><i class="fa fa-trash iconos" title="Eliminar"></i></a></td>
	
					<?php
				}

				echo "<td>". $row['nombre_estado'] ."</td>";
				echo "<td>". $row['descripcion_estado'] ."</td>";
			echo "</tr>";
			}

			
			echo "</table>";

			if(isset($_GET['error'])){
				?>
				<script>alert("No se puede eliminar el registro puesto que tiene dispositivos asociados");</script>
				<?php
			}

			if(isset($_GET['comprobar'])){
				if($_GET['comprobar']=="nopermitir"){
					?>
					<script>alert("No se puede eliminar el registro puesto que tiene dispositivos asociados");</script>
					<?php
				}
			}

		mysqli_close($enlace);

	?>
</section>
</body>
</html>