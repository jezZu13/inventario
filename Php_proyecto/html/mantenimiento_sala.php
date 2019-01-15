<?php include('header.php'); 

ini_set( 'default_charset', 'UTF-8' );
?>	
	<script>

		function modificarSala(id_sala){
			window.open("form_sala_mod.php?id_sala="+id_sala,"ModificacionSala", "width=800px, height=800px");	
		}

		function eliminarSala(id_sala){
			if(!confirm("¡¡Se va a proceder a eliminar el registro seleccionado!!")){
      	return false;
      }
      else{
      	document.location="borrar_sala.php?id_sala="+id_sala;
      return true;
      }
		}

	</script>
	<section class="disp">
	<h1 class="mt-4 text-center">Sala</h1>
	<?php
		
		require("conexion.php");

		$query="SELECT edificio.id_edif, edificio.nombre_edif, tipo_sala.id_tipo_sala, tipo_sala.nombre_tipo_sala, sala.id_sala, sala.nombre_sala, sala.usuario_sala, sala.curso_asignado_sala, sala.descripcion_sala FROM sala, edificio, tipo_sala where edificio.id_edif = sala.id_edif and tipo_sala.id_tipo_sala = sala.id_tipo_sala ORDER BY nombre_sala;";

		$result=mysqli_query($enlace,$query) 
			or die("Error");
		
			echo "<table class='table table-striped table-hover table-secondary text-center'>"; 

			echo "<thead class='encabezado_tabla'>";
			echo "<tr>";
			echo "<th colspan='2'>Acciones</th>";
			echo "<th>Nombre Sala</th>";
			echo "<th>Usuario Sala</th>";
			echo "<th>Curso asignado</th>";
			echo "<th>Descripción</th>";
			echo "<th>Edificio</th>";
			echo "<th>Tipo Sala</th>";
			echo "</tr>";
			echo "</thead>";

		while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
			echo "<tr>";
				if(($row['nombre_sala']=="Almacén")||($row['nombre_sala']=="Basura")){
				?>
					<td></td>
				
					<td></td>
				<?php
				}
				else{
					?>
						<td width="5%"><a href='#' onclick="modificarSala('<?php echo $row['id_sala'];?>');"><i class="fa fa-pencil iconos" title="Modificar"></i></a></td>
					
						<td width="5%"><a href='#' onclick="eliminarSala('<?php echo $row['id_sala'];?>');"><i class="fa fa-trash iconos" title="Eliminar"></i></a></td>
					<?php
				}
				echo "<td>". $row['nombre_sala'] ."</td>";
				echo "<td>". $row['usuario_sala'] ."</td>";
				echo "<td>". $row['curso_asignado_sala'] ."</td>";
				echo "<td>". $row['descripcion_sala'] ."</td>";
				echo "<td>". $row['nombre_edif'] ."</td>";
				echo "<td>". $row['nombre_tipo_sala'] ."</td>";
			echo "</tr>";
			}

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