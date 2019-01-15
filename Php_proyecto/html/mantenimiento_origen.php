<?php include('header.php'); ?>	
	<script>

		function modificarOrigen(id_ori_eq){
			window.open("form_origen_mod.php?id_ori_eq="+id_ori_eq,"ModificacionOrigen", "width=800px, height=800px");	
		}

		function eliminarOrigen(id_ori_eq){
			if(!confirm("¡¡Se va a proceder a eliminar el registro seleccionado!!")){
      	return false;
      }
      else{
      	document.location="borrar_origen.php?id_ori_eq="+id_ori_eq;
      return true;
      }
		}

	</script>
	<section class="disp">
	<h1 class="text-center mt-4">Origen</h1>
	<?php
		
		require("conexion.php");

		$query="select id_ori_eq, nombre_ori_eq, descripcion_ori_eq from origen_equipo order by nombre_ori_eq;";

		$result=mysqli_query($enlace,$query) 
			or die("Error");
		
			echo "<table class='table table-striped table-hover table-secondary text-center'>"; 
			
			echo "<thead class='encabezado_tabla'>";
			echo "<tr>";
			echo "<th colspan='2'>Acciones</th>";
			echo "<th>Nombre</th>";
			echo "<th>Descripcion</th>";
			echo "</tr>";
			echo "</thead>";

		while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
			echo "<tr>";
				?>
				<td width="5%"><a href='#' onclick="modificarOrigen('<?php echo $row['id_ori_eq'];?>');"><i class="fa fa-pencil iconos" title="Modificar"></i></a></td>
				
				<td width="5%"><a href='#' onclick="eliminarOrigen('<?php echo $row['id_ori_eq'];?>');"><i class="fa fa-trash iconos" title="Eliminar"></i></a></td>

				<?php
				echo "<td>". $row['nombre_ori_eq'] ."</td>";
				echo "<td>". $row['descripcion_ori_eq'] ."</td>";
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