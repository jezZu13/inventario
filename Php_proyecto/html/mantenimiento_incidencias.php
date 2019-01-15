<?php include('header.php'); ?>

<script>

function cargarInfo(numero_inc, fecha_cierre) {
			$.post("modal_inci_Ajax.php", {valorBusqueda: numero_inc, valorBusqueda2: fecha_cierre,}, function(fin) {
				$("#carga_info_div").html(fin);
			});
		};

</script>

<?php
        echo "<h1 class='text-center mt-4'>Incidencias</h1>";

        require('conexion.php');
        //-----------------LISTADO-----------------//
        //$sql="SELECT dispositivo.id_disp, dispositivo.identificador_disp, dispositivo.sn_disp, incidencia.*, sala.nombre_sala, sala.id_sala, ubicacion_dispositivo.id_sala FROM dispositivo, incidencia, sala, ubicacion_dispositivo WHERE (dispositivo.id_disp=incidencia.id_disp) && (sala.id_sala=ubicacion_dispositivo.id_sala) && (dispositivo.id_disp=ubicacion_dispositivo.id_disp) && (ubicacion_dispositivo.fecha_salida_disp_sala is NULL);";
        $sql="SELECT dispositivo.id_disp, dispositivo.identificador_disp, dispositivo.sn_disp, incidencia.*, sala.nombre_sala, sala.id_sala, ubicacion_dispositivo.id_sala, estado.nombre_estado FROM dispositivo, incidencia, sala, ubicacion_dispositivo, estado, estado_dispositivo WHERE (dispositivo.id_disp=incidencia.id_disp) && (sala.id_sala=ubicacion_dispositivo.id_sala) && (dispositivo.id_disp=ubicacion_dispositivo.id_disp) && (ubicacion_dispositivo.fecha_salida_disp_sala is NULL) && (estado_dispositivo.id_disp=dispositivo.id_disp) && (estado_dispositivo.id_estado=estado.id_estado) && (estado_dispositivo.fecha_fin_est_disp is NULL) ORDER BY incidencia.numero_inc;";

        $result=mysqli_query($enlace,$sql);
        

            //Recuperar los nombres de los campos para las tablas
            echo "<table class='table table-striped table-hover table-secondary text-center'>";   
                echo "<thead class='encabezado_tabla'>";
                    echo "<tr>";
                        echo "<th colspan='3'>Acciones</th>";
                        echo "<th>Id de Incidencia</th>";
                        echo "<th>Fecha de Apertura</th>";
                        echo "<th>Fecha de Cierre</th>";
                        echo "<th>Identificador Dispositivo</th>";
                        echo "<th>Número de Serie</th>";
                        echo "<th>Estado actual</th>";
                        echo "<th>Ubicación actual</th>";
                    echo "</tr>";
                echo "</thead>";

            while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
                echo "<tr>";
            ?>
                    <td> <a href='#'> <i data-target="#exampleModalCenter" onclick="cargarInfo('<?php echo $row['numero_inc']?>','<?php echo $row['fecha_cierre_inc'] ?>');" data-toggle="modal" class="fa fa-info iconos" title="Ver detalles"></i> </a> </td>
                    <td> <a href='#' onclick="editar('<?php echo $row['numero_inc']?>','<?php echo $row['fecha_cierre_inc']?>','<?php echo $row['descripcion_inc']?>');"> <i class="fa fa-edit iconos" title="Editar descripción"></i> </a> </td> 
                    <td> <a href='#' onclick="cerrar('<?php echo $row['numero_inc']?>','<?php echo $row['fecha_apertura_inc']?>','<?php echo $row['fecha_cierre_inc']?>','<?php echo $row['id_disp']?>','<?php echo $row['sn_disp']?>','<?php echo $row['descripcion_inc']?>');"><i class="fa fa-close iconos" title="Cerrar"></i></a></td>
            <?php
                    echo "<td>". $row['numero_inc'] ."</td>";
                    echo "<td>". $row['fecha_apertura_inc'] ."</td>";
                    echo "<td>". $row['fecha_cierre_inc'] ."</td>";
                    echo "<td>". $row['identificador_disp'] ."</td>";
                    echo "<td>". $row['sn_disp'] ."</td>";
                    echo "<td>". $row['nombre_estado'] ."</td>";
                    echo "<td>". $row['nombre_sala'] ."</td>";
                echo "</tr>";
                }
            echo"</table>";          
            ?>

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
            <!--Fin modal -->

<script>
      var n_reg='';
        /*
        function eliminar(id_inci, apertura,cierre, id_disp, ubicacion){
            
            if(!confirm("¿Desea borrar la incidencia "+ id_inci +"?")){
                document.location="mantenimiento_incidencias.php";
                return false;
            }else{
                document.location="borrar_incidencia.php?reg="+id_inci+"&apertura="+apertura+"&cierre="+cierre+"&id_disp="+id_disp+"&ubicacion="+ubicacion;
                return true;
            }
        }
        */
        function cerrar(id_inci, apertura, cierre, id_disp, serial, descripcion_inc) {
            //document.location="cerrar_incidencia.php?reg="+id_inci+"&apertura="+apertura+"&cierre="+cierre+"&id_disp="+id_disp+"&serial="+serial;
            
                if(cierre==""){
                    window.open("cerrar_incidencia.php?reg="+id_inci+"&apertura="+apertura+"&cierre="+cierre+"&id_disp="+id_disp+"&serial="+serial+"&descripcion="+descripcion_inc, "CerrarIncidencia", "width=800px, height=800px");	
                }
                else{
                    alert("La incidencia está cerrada ya.");
                }
        }

        function editar(id_inci, cierre, descripcion_inc){

            if(cierre==""){
                    window.open("form_editar_incidencia.php?id_inci="+id_inci+"&descripcion_inc="+descripcion_inc, "Editar Incidencia", "width=800px, height=800px");	
                }
                else{
                    alert("La incidencia está cerrada y no se puede editar.");
                }
        }
</script>

    <?php
        mysqli_close($enlace);
    ?>

</body>
</html>