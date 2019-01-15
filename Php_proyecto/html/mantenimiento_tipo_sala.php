<?php include('header.php'); ?>	
	<script>

		function modificarTipoSala(id_tipo_sala){
			window.open("form_tipo_sala_mod.php?id_tipo_sala="+id_tipo_sala,"ModificacionTipoSala", "width=800px, height=800px");	
		}

		function eliminarTipoSala(id_tipo_sala){
			if(!confirm("¡¡Se va a proceder a eliminar el registro seleccionado!!")){
      	return false;
      }
      else{
      	document.location="borrar_tipo_sala.php?id_tipo_sala="+id_tipo_sala;
      return true;
      }
		}

	</script>
	<section class="disp">
	<h1 class="mt-4 text-center">Tipo Sala</h1>
	<?php
		
		require("conexion.php");

		$query="SELECT id_tipo_sala, nombre_tipo_sala, descripcion_tipo_sala FROM tipo_sala ORDER BY nombre_tipo_sala;";

		$result=mysqli_query($enlace,$query) 
			or die("Error");
		
			echo "<table class='table table-striped table-hover table-secondary text-center'>"; 

			echo "<thead class='encabezado_tabla'>";
			echo "<tr>";
			echo "<th colspan='2'>Acciones</th>";
			echo "<th>Nombre</th>";
			echo "<th>Descripción</th>";
			echo "</tr>";
			echo "</thead>";

		while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
			echo "<tr>";
			if(($row['nombre_tipo_sala']=="Almacén")||($row['nombre_tipo_sala']=="Planta de reciclaje")){
			?>
				<td></td>
			
				<td></td>
			<?php
			}
			else{
				?>
				<td width="5%"><a href='#' onclick="modificarTipoSala('<?php echo $row['id_tipo_sala'];?>');"><i class="fa fa-pencil iconos" title="Modificar"></i></a></td>
				
				<td width="5%"><a href='#' onclick="eliminarTipoSala('<?php echo $row['id_tipo_sala'];?>');"><i class="fa fa-trash iconos" title="Eliminar"></i></a></td>
			
				<?php
			}
				echo "<td>". $row['nombre_tipo_sala'] ."</td>";
				echo "<td>". $row['descripcion_tipo_sala'] ."</td>";
			echo "</tr>";
			}

			echo "</table>";

			if(isset($_GET['error'])){
				?>
				<script>alert("No se puede eliminar el registro puesto que existen dependencias");</script>
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