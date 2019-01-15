<?php include('header.php'); ?>	
	<script>

		function modificarMarca(id_marca){
			window.open("form_marca_mod.php?id_marca="+id_marca,"ModificacionMarca", "width=800px, height=800px");	
		}

		function eliminarMarca(id_marca){
			if(!confirm("¡¡Se va a proceder a eliminar el registro seleccionado!!")){
      	return false;
      }
      else{
      	document.location="borrar_marca.php?id_marca="+id_marca;
      return true;
      }
		}

	</script>
	<section class="disp">
	<h1 class="text-center mt-4">Marca</h1>
	<?php
		
		require("conexion.php");

		$query="select id_marca, nombre_marca, descripcion_marca from marca ORDER BY nombre_marca;";

		$result=mysqli_query($enlace,$query) 
			or die("Error");
		
			echo "<table class='table tablita table-striped table-hover table-secondary text-center'>"; 

			echo "<thead class='encabezado_tabla'>";
			echo "<tr>";
			echo "<th colspan='2'>Acciones</th>";
			echo "<th>Nombre</th>";
			echo "<th>Descripción</th>";
			echo "</tr>";
			echo "</thead>";

		while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
			echo "<tr>";
				?>
				<td width="5%"><a href='#' onclick="modificarMarca('<?php echo $row['id_marca'];?>');"><i class="fa fa-pencil iconos" title="Modificar"></i></a></td>
				
				<td width="5%"><a href='#' onclick="eliminarMarca('<?php echo $row['id_marca'];?>');"><i class="fa fa-trash iconos" title="Eliminar"></i></a></td>

				<?php
				echo "<td>". $row['nombre_marca'] ."</td>";
				echo "<td>". $row['descripcion_marca'] ."</td>";
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