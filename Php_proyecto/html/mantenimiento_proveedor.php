<?php include('header.php'); ?>	
	<script>

		function modificarProv(id_proveedor){
			window.open("form_proveedor_mod.php?id_mod_prov="+id_proveedor,"ModificacionProveedor", "width=800px, height=800px");	
		}

		function eliminarProv(id_proveedor){
			if(!confirm("¡¡Se va a proceder a eliminar el registro seleccionado!!")){
      	return false;
      }
      else{
      	document.location="borrar_proveedor.php?id_prov="+id_proveedor;
      return true;
      }
		}

	</script>
	<section class="disp">
	<h1 class="text-center mt-4">Proveedor</h1>
	<?php
		
		require("conexion.php");

		$query="SELECT * FROM proveedor ORDER BY nombre_prov;";

		$result=mysqli_query($enlace,$query) 
			or die("Error");
		
			echo "<table class='table table-striped table-hover table-secondary text-center'>"; 
			
			echo "<thead class='encabezado_tabla'>";
			echo "<tr>";
			echo "<th colspan='2'>Acciones</th>";
			echo "<th>Nombre</th>";
			echo "<th>Persona de contacto</th>";
			echo "<th>Teléfono</th>";
			echo "<th>Dirección</th>";
			echo "<th>Email</th>";
			echo "<th>Descripción</th>";
			echo "</tr>";
			echo "</thead>";

		while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
			echo "<tr>";
				
				if($row['nombre_prov']!="Desconocido"){
				?>
				
				<td><a href='#' onclick="modificarProv('<?php echo $row['id_prov'];?>');"><i class="fa fa-pencil iconos" title="Modificar"></i></a></td>
				
				<td><a href='#' onclick="eliminarProv('<?php echo $row['id_prov'];?>');"><i class="fa fa-trash iconos" title="Eliminar"></i></a></td>

				<?php
				
					echo "<td>". $row['nombre_prov'] ."</td>";
					echo "<td>". $row['persona_contact_prov'] ."</td>";
					echo "<td>". $row['telefono_prov'] ."</td>";
					echo "<td>". $row['direccion_prov'] ."</td>";
					echo "<td>". $row['email_prov'] ."</td>";
					echo "<td>". $row['descripcion_prov'] ."</td>";
				}
			echo "</tr>";
			}

			
			echo "</table>";

		mysqli_close($enlace);

	?>

</section>
</body>
</html>