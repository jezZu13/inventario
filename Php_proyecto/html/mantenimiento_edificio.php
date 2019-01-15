<?php include('header.php'); ?>	
	<script>

		function modificarEdificio(id_edif){
			window.open("form_edificio_mod.php?id_edif="+id_edif,"ModificacionEdificio", "width=800px, height=800px");	
		}

		function eliminarEdificio(id_edif){
			if(!confirm("¡¡Se va a proceder a eliminar el registro seleccionado!!")){
      	return false;
      }
      else{
      	document.location="borrar_edificio.php?id_edif="+id_edif;
      return true;
      }
		}

	</script>
	<section class="disp">
	<h1 class="text-center mt-4">Edificio</h1>
	<?php
		
		require("conexion.php");

		$query="select id_edif, nombre_edif, descripcion_edif from edificio order by nombre_edif;";

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
			echo "<tbody>";
		while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
			echo "<tr>";
				?>
				<td width="5%"><a href='#' onclick="modificarEdificio('<?php echo $row['id_edif'];?>');"><i class="fa fa-pencil iconos" title="Modificar"></i></a></td>
				
				<td width="5%"><a href='#' onclick="eliminarEdificio('<?php echo $row['id_edif'];?>');"><i class="fa fa-trash iconos" title="Eliminar"></i></a></td>

				<?php
				echo "<td>". $row['nombre_edif'] ."</td>";
				echo "<td>". $row['descripcion_edif'] ."</td>";
			echo "</tr>";
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