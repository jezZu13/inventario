<?php include('header.php'); ?>	
	<script>

		function modificarUsuario(id_usuario){
			window.open("form_usuario_mod.php?id_usuario="+id_usuario,"ModificacionUsuario", "width=800px, height=800px");	
		}

		function eliminarUsuario(id_usuario){
			if(!confirm("¡¡Se va a proceder a eliminar el registro seleccionado!!")){
      	return false;
      }
      else{
      	document.location="borrar_usuario.php?id_usuario="+id_usuario;
      return true;
      }
		}

		function cambiarPasswordUsuario(id_usuario){
				window.open("form_cambiar_password.php?id_usuario="+id_usuario,"CambiarPassword", "width=800px, height=800px");	
		}

	</script>
	<h1 class="text-center mt-4">Usuarios</h1>
	<?php
		
		require("conexion.php");

		$query="SELECT * FROM usuarios_app;";

		$result=mysqli_query($enlace,$query) 
			or die("Error");
		
			echo "<table class='table table-striped table-hover table-secondary text-center'>"; 

			echo "<thead class='encabezado_tabla'>";
			echo "<tr>";
			echo "<th colspan='3'>Acciones</th>";
			echo "<th>Nombre</th>";
			echo "<th>Rol</th>";
			echo "</tr>";
			echo "</thead>";

		while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
			echo "<tr>";
				?>
				<td width="5%"><a href='#' onclick="modificarUsuario('<?php echo $row['id_usuario'];?>');"><i class="fa fa-pencil iconos" title="Modificar"></i></a></td>
				
				<td width="5%"><a href='#' onclick="eliminarUsuario('<?php echo $row['id_usuario'];?>');"><i class="fa fa-trash iconos" title="Eliminar"></i></a></td>

				<td width="5%"><a href='#' onclick="cambiarPasswordUsuario('<?php echo $row['id_usuario'];?>');"><i class="fa fa-unlock-alt iconos" title="Cambiar contraseña"></i></a></td>

				<?php
				echo "<td>". $row['nombre_usuario'] ."</td>";
				echo "<td>". $row['rol_usuario'] ."</td>";
			echo "</tr>";
			}

			echo "</table>";

		mysqli_close($enlace);

	?>


</body>
</html>