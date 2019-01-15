<?php include('header.php'); ?>	
	<script>

		function modificarFamiliaProducto(id_familia_prod){
			window.open("form_familia_prod_mod.php?id_familia_prod="+id_familia_prod,"ModificacionFamiliaProducto", "width=800px, height=800px");	
		}

		function eliminarFamiliaProducto(id_familia_prod){
			if(!confirm("¡¡Se va a proceder a eliminar el registro seleccionado!!")){
      	return false;
      }
      else{
      	document.location="borrar_familia_prod.php?id_familia_prod="+id_familia_prod;
      return true;
      }
		}
	</script>
	<section class="disp">
	<h1 class="text-center mt-4">Familia Producto</h1>
	<?php
		
		require("conexion.php");

		$query="SELECT * FROM familia_producto ORDER BY nombre_familia_prod;";

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
				?>
				<td width="5%"><a href='#' onclick="modificarFamiliaProducto('<?php echo $row['id_familia_prod'];?>');"><i class="fa fa-pencil iconos" title="Modificar"></i></a></td>
				
				<td width="5%"><a href='#' onclick="eliminarFamiliaProducto('<?php echo $row['id_familia_prod'];?>');"><i class="fa fa-trash iconos" title="Eliminar"></i></a></td>

				<?php
				echo "<td>". $row['nombre_familia_prod'] ."</td>";
				echo "<td>". $row['descripcion_familia_prod'] ."</td>";
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