<?php include('header.php'); ?>

	<script>

		function modificarProdEnPed(id_ped,id_prod,id_ori_eq,estado){
			if(estado==0){
				window.open("form_producto_pedido_mod.php?id_ped="+id_ped+"&id_prod="+id_prod+"&id_ori_eq="+id_ori_eq,"ModificacionEstado", "width=800px, height=800px");
			}
			else{
				alert("No se pueden realizar modificaciones de productos en pedidos cerrados");
			}
		}

		function eliminarProdEnPed(id_ped,id_prod,id_ori_eq,estado){
			if(estado==0){
				if(!confirm("¡¡Se va a proceder a eliminar el registro seleccionado!!")){
					return false;
				}
				else{
					document.location="borrar_producto_pedido.php?id_ped="+id_ped+"&id_prod="+id_prod+"&id_ori_eq="+id_ori_eq;
				return true;
				}
			}
			else{
				alert("No se pueden realizar modificaciones de productos en pedidos cerrados");
			}
		}

		function addProducto(id_ped,estado){
			if(estado==0){
				window.open("producto_pedido.php?id_pedido_retorno="+id_ped,"AltaProdEnPed", "width=800px, height=800px");
			}
			else{
				alert("No se pueden realizar modificaciones de productos en pedidos cerrados");
			}
		}

		function eliminarPedido(id_ped){
			document.location="borrar_pedido.php?id_ped="+id_ped;
		}

		function modificarDatosPedido(id_ped){
			window.open("modificarDatos_pedido.php?id_ped="+id_ped,"ModificacionEstado", "width=800px, height=800px");
		}

		function cerrarPedido(id_ped,estado){
			if(estado==0){
				window.open("form_cierre_pedido.php?id_ped="+id_ped,"CierrePedido", "width=800px, height=800px");
			}
			else{
				alert("El pedido ya está cerrado");
			}
		}

	</script>

	<?php
		
		require("conexion.php");

		$id_ped=$_GET['id_ped'];
		$costeTotal=0;

		$query="SELECT pedido.*, proveedor.nombre_prov FROM pedido, proveedor WHERE (pedido.id_prov=proveedor.id_prov) and (id_ped='".$id_ped."');";

		$result=mysqli_query($enlace,$query) 
			or die("Error");
		
		//Pintamos la primera tabla:
		
		while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
				echo "<h3 class='titulo-listar text-center'>Nº referencia: ".$row['codigo_ped']."</h3>";
				echo "<div class='container-fluid'>";
				echo "<div class='container tabla'>";
				echo "<table class='table table-striped table-hover table-dark'>";
				
				//Estado
				$estado=$row['estado'];
				if($estado==0){
					echo "<tr><td>Estado: En curso</td></tr>";
				}
					else{
						echo "<tr><td>Estado: Cerrado</td></tr>";
					}
				
				//Fecha solicitud
				echo "<tr><td>Fecha solicitud: ".$row['fecha_pet_ped']."</td></tr>";
				
				//Fecha recepcion
				if($row['fecha_recepcion_ped']==NULL){
					echo "<tr><td>Fecha recepción: Pendiente de entrega</td></tr>";
				}
					else{
						echo "<tr><td>Fecha recepción: ".$row['fecha_recepcion_ped']."</td></tr>";
					}
				
				//Coste Total:
				echo "<tr><td><label id='costeTotal'></label></td></tr>";
				
				//Proveedor
				echo "<tr><td>Proveedor: ".$row['nombre_prov']."</td></tr>";
			}
			echo "</table>";
			echo "</div>";
			echo "</div>";

			?>		
	
			<div class="text-center mb-4">
					<button class="btn boton waves-effect waves-light" onclick="modificarDatosPedido('<?php echo $id_ped; ?>');">Modificar datos del pedido</button>
					<button class="btn boton waves-effect waves-light" onclick="eliminarPedido('<?php echo $id_ped; ?>');">Eliminar pedido</button>
					<button class="btn boton waves-effect waves-light" onclick="cerrarPedido('<?php echo $id_ped; ?>','<?php echo $estado; ?>');">Cerrar pedido</button>
					<button class="btn boton waves-effect waves-light" onclick="addProducto('<?php echo $id_ped; ?>','<?php echo $estado; ?>');">Añadir productos en el pedido</button>
			</div>


	<?php

			$query2="select producto_en_pedido.*, producto.nombre_prod, origen_equipo.id_ori_eq, origen_equipo.nombre_ori_eq from producto_en_pedido, producto, origen_equipo where (producto_en_pedido.id_ori_eq=origen_equipo.id_ori_eq) and (producto_en_pedido.id_prod=producto.id_prod) and (id_ped='".$id_ped."');";
			$result2=mysqli_query($enlace,$query2) 
				or die("Error");
			
			//Pintamos la segunda tabla:
			echo "<table border='1' class='table text-center'>"; 
			
			echo "<tr>";
			echo "<th colspan='2'>Acciones</th>"; 
			echo "<th>Producto</th>";
			echo "<th>Origen</th>";
			echo "<th>Número de unidades</th>";
			echo "<th>Precio por unidad</th>";
			
			echo "</tr>";

		while($row2=mysqli_fetch_array($result2,MYSQLI_ASSOC) ){
			if($row2['nunidades_prod_ped']!=0){ //Si la cantidad de un producto en el pedido es distinto de 0, carga la fila.
				echo "<tr>";
				?>
				<td><a href='#' onclick="modificarProdEnPed('<?php echo $row2['id_ped'];?>','<?php echo $row2['id_prod'];?>','<?php echo $row2['id_ori_eq'];?>','<?php echo $estado; ?>');">Modificar</a></td>
				
				<td><a href='#' onclick="eliminarProdEnPed('<?php echo $row2['id_ped'];?>','<?php echo $row2['id_prod'];?>','<?php echo $row2['id_ori_eq'];?>','<?php echo $estado; ?>');">Eliminar</a></td>

				<?php
						echo "<td>". $row2['nombre_prod'] ."</td>";
						echo "<td>". $row2['nombre_ori_eq']."</td>";
						echo "<td>". $row2['nunidades_prod_ped'] ."</td>";
						echo "<td>". $row2['precio_por_unidad'] ."€</td>";
				echo "</tr>";
			}

			$costeTotal=$costeTotal+($row2['precio_por_unidad']*$row2['nunidades_prod_ped']);
			}
			echo "</table>";
			
			?>		
			
			<script>
				document.getElementById("costeTotal").innerHTML="Coste Total: "+<?php echo $costeTotal; ?>+"€";
			</script>

	<?php
		mysqli_close($enlace);

	?>

	
</body>
</html>