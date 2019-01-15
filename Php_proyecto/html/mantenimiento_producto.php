<?php include('header.php'); ?>	
	<script>

		function modificarProd(id_prod){
			window.open("form_producto_mod.php?id_mod_prod="+id_prod,"ModificacionProducto", "width=800px, height=800px");	
		}

		function eliminarProd(id_prod){
			if(!confirm("¡¡Se va a proceder a eliminar el registro seleccionado!!")){
      	return false;
      }
      else{
      	document.location="borrar_producto.php?id_prod="+id_prod;
      return true;
      }
		}


		function cargarFoto(id_prod) {
						
			$.post("modal_Ajax.php", {valorBusqueda: id_prod,}, function(fin) {
				$("#carga_img_div").html(fin);
			}); 
	

		};

	</script>
	<section class="disp">
	<h1 class="text-center mt-4">Producto</h1>
	<?php
		
		require("conexion.php");

		$query="select producto.id_prod, producto.nombre_prod, producto.descripcion_prod, producto.id_marca, producto.id_familia_prod, marca.nombre_marca, familia_producto.nombre_familia_prod from producto, marca, familia_producto where (producto.id_marca=marca.id_marca) and (producto.id_familia_prod=familia_producto.id_familia_prod) ORDER BY marca.nombre_marca, familia_producto.nombre_familia_prod, producto.nombre_prod;";

		$result=mysqli_query($enlace,$query) 
			or die("Error");
		
			echo "<table class='table table-striped table-hover table-secondary text-center'>"; 
			
			echo "<thead class='encabezado_tabla'>";
			echo "<tr>";
			echo "<th colspan='3'>Acciones</th>";
			echo "<th>Marca</th>";
			echo "<th>Tipo</th>";
			echo "<th>Nombre</th>";
			echo "<th>Características</th>";
			echo "</tr>";
			echo "</thead>";

		while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
			echo "<tr>";
				?>
				<td><a href='#' onclick="modificarProd('<?php echo $row['id_prod'];?>');"><i class="fa fa-pencil iconos" title="Modificar"></i></a></td>
				
				<td><a href='#' onclick="eliminarProd('<?php echo $row['id_prod'];?>');"><i class="fa fa-trash iconos" title="Eliminar"></i></a></td>

				<td> <a href='#'> <i class="fa fa-picture-o iconos" title="Ver imagen" onclick="cargarFoto('<?php echo $row['id_prod'];?>');" data-target="#exampleModalCenter" data-toggle="modal" ></i></a></td>

				<!-- Modal -->
				<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
				  <div class="modal-dialog modal-dialog-centered" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLongTitle">Imagen</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body text-center">
									<div id="carga_img_div"></div>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-sm boton waves-effect waves-light" data-dismiss="modal">Cerrar</button>
				      </div>
				    </div>
				  </div>
				</div>

				<?php
				echo "<td>". $row['nombre_marca'] ."</td>";
				echo "<td>". $row['nombre_familia_prod'] ."</td>";
				echo "<td>". $row['nombre_prod'] ."</td>";
				echo "<td>". $row['descripcion_prod'] ."</td>";
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