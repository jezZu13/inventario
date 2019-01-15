<?php include('header.php'); ?>

	
	<script type="text/javascript">

		function modificarDisp(id_disp,codigo_ped){
			if(codigo_ped=="Desconocido"){
				window.open("form_dispositivo_mod.php?id_mod_disp="+id_disp,"ModificacionDispositivo", "width=800px, height=900px");
			}
			else{
				window.open("form_dispositivo_mod_reducido.php?id_mod_disp="+id_disp,"ModificacionDispositivo", "width=800px, height=800px");
			}
				
		}

		function eliminarDisp(id_disp,id_ped,codigo_ped,id_prod){
			if(!confirm("¡¡Se va a proceder a eliminar el registro seleccionado!!")){
				return false;
			}
			else{
				if(codigo_ped!="Desconocido"){
					document.location="borrar_dispositivo.php?id_disp="+id_disp+"&id_ped="+id_ped+"&id_prod="+id_prod;
				}
				else{
					document.location="borrar_dispositivo.php?id_disp="+id_disp;
				}
			return true;
			}
		}

		function darDeBajaDisp(id_disp,id_sala,id_estado){
			alert("El dispositivo se ha dado de baja");
			document.location="baja_dispositivo.php?id_disp="+id_disp+"&id_sala_actual="+id_sala+"&id_estado_actual="+id_estado;
		}

		function darDeBajaHecho(){
			alert("El dispositivo ya esta dado de baja");
			
		}

		
		function cargarInfo(id_disp) {
			$.post("modal_disp_Ajax.php", {valorBusqueda: id_disp,}, function(fin) {
				$("#carga_info_div").html(fin);
			}); 
		};


	</script>

	<section class="disp">
	<?php
		
		require("conexion.php");

		//$query="SELECT dispositivo.*, producto.nombre_prod, pedido.id_ped, pedido.codigo_ped, origen_equipo.nombre_ori_eq, estado_dispositivo.id_estado, estado.nombre_estado, estado_dispositivo.fecha_fin_est_disp, ubicacion_dispositivo.id_sala, sala.nombre_sala FROM dispositivo, producto, pedido, origen_equipo, estado_dispositivo, estado, ubicacion_dispositivo, sala WHERE (dispositivo.id_producto=producto.id_prod) && (dispositivo.id_ped=pedido.id_ped) && (dispositivo.id_ori_eq=origen_equipo.id_ori_eq) && (estado_dispositivo.id_disp=dispositivo.id_disp) && (estado_dispositivo.id_estado=estado.id_estado) && (estado_dispositivo.fecha_fin_est_disp is NULL) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.fecha_salida_disp_sala is NULL)&&(ubicacion_dispositivo.id_sala=sala.id_sala) ORDER BY producto.nombre_prod;";
		//$query="SELECT dispositivo.*, producto.nombre_prod, pedido.id_ped, pedido.codigo_ped, origen_equipo.nombre_ori_eq, estado_dispositivo.id_estado, estado.nombre_estado, estado_dispositivo.fecha_fin_est_disp, ubicacion_dispositivo.id_sala, sala.nombre_sala, marca.id_marca, marca.nombre_marca, familia_producto.nombre_familia_prod FROM dispositivo, producto, pedido, origen_equipo, estado_dispositivo, estado, ubicacion_dispositivo, sala, marca, familia_producto WHERE (dispositivo.id_producto=producto.id_prod) && (dispositivo.id_ped=pedido.id_ped) && (dispositivo.id_ori_eq=origen_equipo.id_ori_eq) && (estado_dispositivo.id_disp=dispositivo.id_disp) && (estado_dispositivo.id_estado=estado.id_estado) && (estado_dispositivo.fecha_fin_est_disp is NULL) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.fecha_salida_disp_sala is NULL)&&(ubicacion_dispositivo.id_sala=sala.id_sala) && (marca.id_marca=producto.id_marca) && (producto.id_familia_prod=familia_producto.id_familia_prod) ORDER BY producto.nombre_prod;";
		$query="SELECT dispositivo.*, producto.nombre_prod, pedido.id_ped, pedido.codigo_ped, origen_equipo.nombre_ori_eq, estado_dispositivo.id_estado, estado.nombre_estado, estado_dispositivo.fecha_fin_est_disp, ubicacion_dispositivo.id_sala, sala.nombre_sala, marca.id_marca, marca.nombre_marca, familia_producto.nombre_familia_prod, edificio.nombre_edif FROM dispositivo, producto, pedido, origen_equipo, estado_dispositivo, estado, ubicacion_dispositivo, sala, marca, familia_producto, edificio WHERE (dispositivo.id_producto=producto.id_prod) && (dispositivo.id_ped=pedido.id_ped) && (dispositivo.id_ori_eq=origen_equipo.id_ori_eq) && (estado_dispositivo.id_disp=dispositivo.id_disp) && (estado_dispositivo.id_estado=estado.id_estado) && (estado_dispositivo.fecha_fin_est_disp is NULL) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.fecha_salida_disp_sala is NULL)&&(ubicacion_dispositivo.id_sala=sala.id_sala) && (marca.id_marca=producto.id_marca) && (producto.id_familia_prod=familia_producto.id_familia_prod) && (sala.id_edif=edificio.id_edif) ORDER BY producto.nombre_prod;";

		$result=mysqli_query($enlace,$query) 
			or die("Error");
		?>

		<h1 class="text-center">Dispositivos</h1>
		<table id="example" class="table table-responsive table-striped table-hover table-secondary text-center"> 
			
		<thead class='encabezado_tabla'>
		<tr>
			<th colspan='4'>Acciones</th>
			<th>Identificador</th>
			<th>SN</th>
			<th>Fecha alta</th>
			<th>Fecha baja</th>
			<th>Producto</th>
			<th>Tipo de producto</th>
			<th>Marca</th>
			<th>Pedido</th>
			<th>Origen</th>
			<th>Estado</th>
			<th>Sala</th>
			<th>Edificio</th>

		</tr>
		</thead>
		<tbody>
	<?php
		while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
			echo "<tr>";
				?>
				<td><a href='#' onclick="modificarDisp('<?php echo $row['id_disp'];?>','<?php echo $row['codigo_ped'];?>');"><i class="fa fa-pencil iconos" title="Modificar"></i></a></td>
				
				<?php
					if($row['fecha_baja_disp']==NULL){
						?>
							<td><a href='#' onclick="darDeBajaDisp('<?php echo $row['id_disp'];?>','<?php echo $row['id_sala'];?>','<?php echo $row['id_estado'];?>');"><i class="fa fa-close iconos" title="Dar de baja"></i></a></td>
						<?php

					}
					else{
						?>
							<td><a href="" onclick="darDeBajaHecho();"><i class="fa fa-close iconos" title="Dar de baja"></i></a></td>
						<?php
					}
				?>

				
				<td><a href='#' onclick="eliminarDisp('<?php echo $row['id_disp'];?>','<?php echo $row['id_ped'];?>','<?php echo $row['codigo_ped'];?>','<?php echo $row['id_producto'];?>');"><i class="fa fa-trash iconos" title="Eliminar"></i></a></td>

				<td> <a href='#'> <i data-target="#exampleModalCenter" onclick="cargarInfo('<?php echo $row['id_disp'];?>');" data-toggle="modal" class="fa fa-info iconos" title="Ver detalles"></i></a></td>

				<!-- Modal -->
				<div class="modal pruebesita fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
				  <div class="modal-dialog modal-dialog-centered" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLongTitle">Información adicional</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body">
						<div id="carga_info_div"></div>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-sm boton waves-effect waves-light" data-dismiss="modal">Cerrar</button>
				      </div>
				    </div>
				  </div>
				</div>
								

				<?php

				//IDENTIFICADOR:
				if(($row['identificador_disp']==NULL)||($row['identificador_disp']=="")){
					echo "<td> <label>Sin asignar</label> </td>";
				}
				else{
					echo "<td>". $row['identificador_disp'] ."</td>";
				}
				

				//SERIAL NUMBER:
				if(($row['sn_disp']==NULL)||($row['sn_disp']=="")){
					echo "<td> <label>Sin definir</label> </td>";
				}
				else{
					echo "<td>". $row['sn_disp'] ."</td>";
				}
				
				
				//FECHA ALTA:
				if(($row['fecha_alta_disp']==NULL)||($row['fecha_alta_disp']=="0000-00-00")){
					echo "<td> <label>Desconocida</label> </td>";
				}
				else{
					echo "<td>". $row['fecha_alta_disp'] ."</td>";
				}
				
				//FECHA BAJA:
				if($row['fecha_baja_disp']==NULL){
					echo "<td> <label>Dispositivo activo</label> </td>";
				}
				else{
					echo "<td>". $row['fecha_baja_disp'] ."</td>";
				}
				
								
				echo "<td>". $row['nombre_prod'] ."</td>";
				echo "<td>". $row['nombre_familia_prod'] ."</td>";
				echo "<td>". $row['nombre_marca'] ."</td>";
				

				//Pedido:
				if($row['codigo_ped']=="Desconocido"){
					echo "<td></td>"; //Si el pedido es "desconocido", pintamos vacio.
				}
				else{
					echo "<td>". $row['codigo_ped'] ."</td>";
				}
				
				echo "<td>". $row['nombre_ori_eq'] ."</td>";
				echo "<td>". $row['nombre_estado'] ."</td>";
				echo "<td>". $row['nombre_sala'] ."</td>";
				echo "<td>". $row['nombre_edif'] ."</td>";
				
			echo "</tr>";
			}
		?>
		</tbody>
		</table>
		</section>
	<?php

		if(isset($_GET['error'])){
			?>
				<script>alert("No se puede eliminar el registro puesto que existen dependencias");</script>
			<?php
		}

		mysqli_close($enlace);

	?>
	<script src="js/scripts.js"></script>
    <script>
    	document.addEventListener('DOMContentLoaded', function() {
    	var elems = document.querySelectorAll('.modal');
    	var options = document.querySelectorAll('option');
    	var instances = M.Modal.init(elems, options);
  		});
    </script>


</body>
</html>