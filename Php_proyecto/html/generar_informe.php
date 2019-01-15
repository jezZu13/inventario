<?php 

include('header.php'); 

?>

<script language="javascript">
    $(document).ready(function() {
        $(".botonExcel").click(function(event) {
            $("#datos_a_enviar").val( $("<div>").append( $("#example").eq(0).clone()).html());
            $("#FormularioExportacion").submit();
        });
    });
</script>

<?php

require("conexion.php");

$numInforme=$_GET['numInforme'];

switch($numInforme){
    case 1:
            $query="SELECT dispositivo.*, producto.nombre_prod, pedido.codigo_ped, origen_equipo.nombre_ori_eq, estado.nombre_estado, estado_dispositivo.fecha_fin_est_disp, sala.nombre_sala, marca.nombre_marca, familia_producto.nombre_familia_prod, edificio.nombre_edif, year(fecha_alta_disp) as anio FROM dispositivo, producto, pedido, origen_equipo, estado_dispositivo, estado, ubicacion_dispositivo, sala, marca, familia_producto, edificio WHERE (ubicacion_dispositivo.fecha_salida_disp_sala is NULL) && (estado_dispositivo.fecha_fin_est_disp is NULL) && (dispositivo.id_producto=producto.id_prod) && (dispositivo.id_ped=pedido.id_ped) && (dispositivo.id_ori_eq=origen_equipo.id_ori_eq) && (estado_dispositivo.id_disp=dispositivo.id_disp) && (estado_dispositivo.id_estado=estado.id_estado) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.id_sala=sala.id_sala) && (marca.id_marca=producto.id_marca) && (producto.id_familia_prod=familia_producto.id_familia_prod) && (sala.id_edif=edificio.id_edif) order by anio desc, fecha_alta_disp desc;";
            
            $result=mysqli_query($enlace,$query) 
                or die("Error");
	
            ?>
            
            <h1 class="text-center">Dispositivos ordenados por año de adquisición</h1>
            <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
            
            <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

		    <table id="example" class="table table-striped table-hover table-secondary text-center" border="1">
                <tr>
                    <th>Año de adquisición</th>
                    <th>Fecha alta</th>
                    <th>Identificador</th>
                    <th>SN</th>
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

                <?php
                    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
                        echo "<tr>";

                            echo "<td>". $row['anio'] ."</td>";

                            //FECHA ALTA:
                             if(($row['fecha_alta_disp']==NULL)||($row['fecha_alta_disp']=="0000-00-00")){
                                echo "<td> <label>Desconocida</label> </td>";
                            }
                            else{
                                echo "<td>". $row['fecha_alta_disp'] ."</td>";
                            }                           

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
            </table>
            
            <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />

            </form>
            <?php
    break;

    case 2:
            $query="select count(*) as cantidad, familia_producto.nombre_familia_prod, year(fecha_alta_disp) as anio FROM dispositivo, producto, familia_producto WHERE (dispositivo.id_producto=producto.id_prod) && (producto.id_familia_prod=familia_producto.id_familia_prod) group by familia_producto.id_familia_prod, year(fecha_alta_disp) order by anio desc;";
           
            $result=mysqli_query($enlace,$query) 
                or die("Error");

            ?>
            <h1 class="text-center">Cantidad de dispositivos agrupados por año y tipo </h1>

            <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
            
            <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

            <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
                <tr>
                    <th>Año de adquisición</th>
                    <th>Tipo de producto</th>
                    <th>Cantidad</th>
                </tr>
            <?php
            
            while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
                echo "<tr>";
                    echo "<td>". $row['anio'] ."</td>";
                    echo "<td>". $row['nombre_familia_prod'] ."</td>";
                    echo "<td>". $row['cantidad'] ."</td>";      
                echo "</tr>";
            }
            ?>
            </table>

            <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />

            </form>

            <?php
    break;


    case 3:
            $query="SELECT count(*) as cantidad, producto.nombre_prod, proveedor.nombre_prov, year(fecha_alta_disp) as anio FROM dispositivo, pedido, proveedor, producto WHERE (pedido.id_ped=dispositivo.id_ped) && (pedido.id_prov=proveedor.id_prov) && (dispositivo.id_producto=producto.id_prod) GROUP BY producto.id_prod, proveedor.id_prov, anio ORDER BY anio desc;";
           
            $result=mysqli_query($enlace,$query) 
                or die("Error");

            ?>
            <h1 class="text-center"> Cantidad de dispositivos agrupados por año, proveedor que los ha distribuido y producto</h1>

            <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
            
            <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

            <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
                <tr>
                    <th>Año de adquisición</th>
                    <th>Proveedor</th>
                    <th>Producto</th>
                    <th>Cantidad</th> 
                </tr>
            <?php
            
            while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
                echo "<tr>";
                    echo "<td>". $row['anio'] ."</td>";
                    echo "<td>". $row['nombre_prov'] ."</td>";
                    echo "<td>". $row['nombre_prod'] ."</td>";
                    echo "<td>". $row['cantidad'] ."</td>";
                echo "</tr>";
            }
            ?>
            </table>
            
            <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />

            </form>

            <?php
    break;

    case 4:
            $query="SELECT dispositivo.*, producto.nombre_prod, pedido.codigo_ped, origen_equipo.nombre_ori_eq, estado.nombre_estado, estado_dispositivo.fecha_fin_est_disp, sala.nombre_sala, marca.nombre_marca, familia_producto.nombre_familia_prod, edificio.nombre_edif, year(fecha_alta_disp) as anio FROM dispositivo, producto, pedido, origen_equipo, estado_dispositivo, estado, ubicacion_dispositivo, sala, marca, familia_producto, edificio WHERE (ubicacion_dispositivo.fecha_salida_disp_sala is NULL) && (estado_dispositivo.fecha_fin_est_disp is NULL) && (dispositivo.id_producto=producto.id_prod) && (dispositivo.id_ped=pedido.id_ped) && (dispositivo.id_ori_eq=origen_equipo.id_ori_eq) && (estado_dispositivo.id_disp=dispositivo.id_disp) && (estado_dispositivo.id_estado=estado.id_estado) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.id_sala=sala.id_sala) && (marca.id_marca=producto.id_marca) && (producto.id_familia_prod=familia_producto.id_familia_prod) && (sala.id_edif=edificio.id_edif) order by nombre_edif asc;";
            
            $result=mysqli_query($enlace,$query) 
                or die("Error");
	
            ?>
            <h1 class="text-center">Dispositivos ordenados por edificio</h1>

            <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
            
            <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

		    <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
                <tr>
                    <th>Edificio</th>
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
                    <th>Año de adquisición</th>
                </tr>

                <?php
                    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
                        echo "<tr>";

                            echo "<td>". $row['nombre_edif'] ."</td>";
                            

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
                            
                            echo "<td>". $row['anio'] ."</td>";

                        echo "</tr>";
                    }
                ?>
            </table>

            <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />
            </form>

            <?php
    break;

    case 5:
            $query="SELECT dispositivo.*, producto.nombre_prod, pedido.codigo_ped, origen_equipo.nombre_ori_eq, estado.nombre_estado, estado_dispositivo.fecha_fin_est_disp, sala.nombre_sala, marca.nombre_marca, familia_producto.nombre_familia_prod, edificio.nombre_edif, year(fecha_alta_disp) as anio FROM dispositivo, producto, pedido, origen_equipo, estado_dispositivo, estado, ubicacion_dispositivo, sala, marca, familia_producto, edificio WHERE (ubicacion_dispositivo.fecha_salida_disp_sala is NULL) && (estado_dispositivo.fecha_fin_est_disp is NULL) && (dispositivo.id_producto=producto.id_prod) && (dispositivo.id_ped=pedido.id_ped) && (dispositivo.id_ori_eq=origen_equipo.id_ori_eq) && (estado_dispositivo.id_disp=dispositivo.id_disp) && (estado_dispositivo.id_estado=estado.id_estado) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.id_sala=sala.id_sala) && (marca.id_marca=producto.id_marca) && (producto.id_familia_prod=familia_producto.id_familia_prod) && (sala.id_edif=edificio.id_edif) order by nombre_edif asc, nombre_sala asc;";
            
            $result=mysqli_query($enlace,$query) 
                or die("Error");
	
            ?>
            <h1 class="text-center">Dispositivos ordenados por edificio y ubicación</h1>

            <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
            
            <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

		    <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
                <tr>
                    <th>Edificio</th>
                    <th>Sala</th>
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
                    <th>Año de adquisición</th>
                </tr>

                <?php
                    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
                        echo "<tr>";

                            echo "<td>". $row['nombre_edif'] ."</td>";
                            echo "<td>". $row['nombre_sala'] ."</td>";
                            

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
                            echo "<td>". $row['anio'] ."</td>";

                        echo "</tr>";
                    }
                ?>
            </table>

            <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />

            </form>
            <?php
    break;

    case 6:
            $query="SELECT count(*) as cantidad, familia_producto.nombre_familia_prod, edificio.nombre_edif FROM dispositivo, producto, ubicacion_dispositivo, sala, familia_producto, edificio WHERE (ubicacion_dispositivo.fecha_salida_disp_sala is NULL) && (dispositivo.id_producto=producto.id_prod) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.id_sala=sala.id_sala) && (producto.id_familia_prod=familia_producto.id_familia_prod) && (sala.id_edif=edificio.id_edif) GROUP BY familia_producto.id_familia_prod, edificio.id_edif ORDER BY nombre_edif;";
           
            $result=mysqli_query($enlace,$query) 
                or die("Error");

            ?>
            <h1 class="text-center"> Cantidad de dispositivos agrupados por edificio y tipo</h1>

            <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
            
            <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

            <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
                <tr>
                    <th>Edificio</th>
                    <th>Tipo de producto</th>
                    <th>Cantidad</th>
                </tr>
            <?php
            
            while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
                echo "<tr>";
                    echo "<td>". $row['nombre_edif'] ."</td>";
                    echo "<td>". $row['nombre_familia_prod'] ."</td>";
                    echo "<td>". $row['cantidad'] ."</td>";
                echo "</tr>";
            }
            ?>
            </table>

            <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />
            </form>

            <?php
    break;


    case 7:
        $query="SELECT count(*) as cantidad, familia_producto.nombre_familia_prod, proveedor.nombre_prov, edificio.nombre_edif FROM dispositivo, producto, ubicacion_dispositivo, sala, pedido, proveedor, familia_producto, edificio WHERE (ubicacion_dispositivo.fecha_salida_disp_sala is NULL) && (dispositivo.id_producto=producto.id_prod) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.id_sala=sala.id_sala) && (producto.id_familia_prod=familia_producto.id_familia_prod) && (sala.id_edif=edificio.id_edif) && (dispositivo.id_ped=pedido.id_ped) && (pedido.id_prov=proveedor.id_prov) GROUP BY familia_producto.id_familia_prod, proveedor.id_prov, edificio.id_edif ORDER BY nombre_edif;";
    
        $result=mysqli_query($enlace,$query) 
            or die("Error");

        ?>
        <h1 class="text-center"> Cantidad de dispositivos agrupados por edificio, proveedor y tipo</h1>

        <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
        <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

        <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
            <tr>
                <th>Edificio</th>
                <th>Proveedor</th>
                <th>Tipo de producto</th>
                <th>Cantidad</th>
            </tr>
        <?php
        
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            echo "<tr>";
                echo "<td>". $row['nombre_edif'] ."</td>";
                echo "<td>". $row['nombre_prov'] ."</td>";
                echo "<td>". $row['nombre_familia_prod'] ."</td>";
                echo "<td>". $row['cantidad'] ."</td>";
            echo "</tr>";
        }
        ?>
        </table>

        <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />
        </form>

        <?php
    break;


    case 8:
    $query="SELECT count(*) as cantidad, familia_producto.nombre_familia_prod, proveedor.nombre_prov, edificio.nombre_edif, year(fecha_alta_disp) as anio FROM dispositivo, producto, ubicacion_dispositivo, sala, pedido, proveedor, familia_producto, edificio WHERE (ubicacion_dispositivo.fecha_salida_disp_sala is NULL) && (dispositivo.id_producto=producto.id_prod) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.id_sala=sala.id_sala) && (producto.id_familia_prod=familia_producto.id_familia_prod) && (sala.id_edif=edificio.id_edif) && (dispositivo.id_ped=pedido.id_ped) && (pedido.id_prov=proveedor.id_prov) GROUP BY familia_producto.id_familia_prod, proveedor.id_prov, edificio.id_edif, anio ORDER BY nombre_edif asc, anio desc;";

    $result=mysqli_query($enlace,$query) 
        or die("Error");

    ?>
    <h1 class="text-center"> Cantidad de dispositivos agrupados por edificio, año, proveedor y tipo</h1>

    <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
            
    <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

    <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
        <tr>
            <th>Edificio</th>
            <th>Año</th>
            <th>Proveedor</th>
            <th>Tipo de producto</th>
            <th>Cantidad</th>
        </tr>
    <?php

    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
        echo "<tr>";
            echo "<td>". $row['nombre_edif'] ."</td>";
            echo "<td>". $row['anio'] ."</td>";
            echo "<td>". $row['nombre_prov'] ."</td>";
            echo "<td>". $row['nombre_familia_prod'] ."</td>";
            echo "<td>". $row['cantidad'] ."</td>";
        echo "</tr>";
    }
    ?>
    </table>
    
    <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />
    </form>

    <?php
    break;

    case 9:
        $query="SELECT count(*) as cantidad, edificio.nombre_edif, sala.nombre_sala, familia_producto.nombre_familia_prod FROM dispositivo, producto, ubicacion_dispositivo, sala, familia_producto, edificio WHERE (ubicacion_dispositivo.fecha_salida_disp_sala is NULL) && (dispositivo.id_producto=producto.id_prod) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.id_sala=sala.id_sala) && (producto.id_familia_prod=familia_producto.id_familia_prod) && (sala.id_edif=edificio.id_edif) GROUP BY edificio.id_edif, sala.id_sala, familia_producto.id_familia_prod ORDER BY edificio.nombre_edif asc, sala.nombre_sala asc;";

        $result=mysqli_query($enlace,$query) 
            or die("Error");

        ?>
        <h1 class="text-center"> Cantidad de dispositivos agrupados por edificio, ubicación y tipo</h1>

        <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
        <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

        <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
            <tr>
                <th>Edificio</th>
                <th>Ubicación</th>
                <th>Tipo de producto</th>
                <th>Cantidad</th>
            </tr>
        <?php

        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            echo "<tr>";
                echo "<td>". $row['nombre_edif'] ."</td>";
                echo "<td>". $row['nombre_sala'] ."</td>";
                echo "<td>". $row['nombre_familia_prod'] ."</td>";
                echo "<td>". $row['cantidad'] ."</td>";
            echo "</tr>";
        }
        ?>
        </table>

        <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />
        </form>

        <?php
    break;

    case 10:
            $query="SELECT dispositivo.*, producto.nombre_prod, pedido.codigo_ped, origen_equipo.nombre_ori_eq, estado.nombre_estado, estado_dispositivo.fecha_fin_est_disp, sala.nombre_sala, marca.nombre_marca, familia_producto.nombre_familia_prod, edificio.nombre_edif, year(fecha_alta_disp) as anio FROM dispositivo, producto, pedido, origen_equipo, estado_dispositivo, estado, ubicacion_dispositivo, sala, marca, familia_producto, edificio WHERE (ubicacion_dispositivo.fecha_salida_disp_sala is NULL) && (estado_dispositivo.fecha_fin_est_disp is NULL) && (dispositivo.id_producto=producto.id_prod) && (dispositivo.id_ped=pedido.id_ped) && (dispositivo.id_ori_eq=origen_equipo.id_ori_eq) && (estado_dispositivo.id_disp=dispositivo.id_disp) && (estado_dispositivo.id_estado=estado.id_estado) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.id_sala=sala.id_sala) && (marca.id_marca=producto.id_marca) && (producto.id_familia_prod=familia_producto.id_familia_prod) && (sala.id_edif=edificio.id_edif) order by sala.nombre_sala asc;";
            
            $result=mysqli_query($enlace,$query) 
                or die("Error");
	
            ?>
            <h1 class="text-center">Dispositivos ordenados por sala</h1>

            <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
            
            <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

		    <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
                <tr>
                    <th>Sala</th>
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
                    <th>Edificio</th>
                    <th>Año de adquisición</th>
                </tr>

                <?php
                    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
                        echo "<tr>";

                            echo "<td>". $row['nombre_sala'] ."</td>";
                            

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
                            
                            echo "<td>". $row['nombre_edif'] ."</td>";
                            echo "<td>". $row['anio'] ."</td>";

                        echo "</tr>";
                    }
                ?>

            </table>

            <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />
            </form>

            <?php
    break;

    case 11:
        $query="SELECT count(*) as cantidad, familia_producto.nombre_familia_prod, sala.nombre_sala FROM dispositivo, producto, ubicacion_dispositivo, sala, familia_producto WHERE (ubicacion_dispositivo.fecha_salida_disp_sala is NULL) && (dispositivo.id_producto=producto.id_prod) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.id_sala=sala.id_sala) && (producto.id_familia_prod=familia_producto.id_familia_prod) GROUP BY familia_producto.id_familia_prod, sala.id_sala ORDER BY sala.nombre_sala;";
    
        $result=mysqli_query($enlace,$query) 
            or die("Error");

        ?>
        <h1 class="text-center"> Cantidad de dispositivos agrupados por ubicación y tipo</h1>

        <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>  
        <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

        <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
            <tr>
                <th>Sala</th>
                <th>Tipo de producto</th>
                <th>Cantidad</th>
            </tr>
        <?php
        
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            echo "<tr>";
                echo "<td>". $row['nombre_sala'] ."</td>";
                echo "<td>". $row['nombre_familia_prod'] ."</td>";
                echo "<td>". $row['cantidad'] ."</td>";
            echo "</tr>";
        }
        ?>
        </table>

        <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />
        </form>

        <?php
    break;

    case 12:
            $query="SELECT dispositivo.*, producto.nombre_prod, pedido.codigo_ped, origen_equipo.nombre_ori_eq, estado.nombre_estado, estado_dispositivo.fecha_fin_est_disp, sala.nombre_sala, marca.nombre_marca, familia_producto.nombre_familia_prod, edificio.nombre_edif, year(fecha_alta_disp) as anio FROM dispositivo, producto, pedido, origen_equipo, estado_dispositivo, estado, ubicacion_dispositivo, sala, marca, familia_producto, edificio WHERE (ubicacion_dispositivo.fecha_salida_disp_sala is NULL) && (estado_dispositivo.fecha_fin_est_disp is NULL) && (dispositivo.id_producto=producto.id_prod) && (dispositivo.id_ped=pedido.id_ped) && (dispositivo.id_ori_eq=origen_equipo.id_ori_eq) && (estado_dispositivo.id_disp=dispositivo.id_disp) && (estado_dispositivo.id_estado=estado.id_estado) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.id_sala=sala.id_sala) && (marca.id_marca=producto.id_marca) && (producto.id_familia_prod=familia_producto.id_familia_prod) && (sala.id_edif=edificio.id_edif) order by familia_producto.nombre_familia_prod asc;";
            
            $result=mysqli_query($enlace,$query) 
                or die("Error");
	
            ?>
            <h1 class="text-center">Dispositivos ordenados por tipo</h1>

            <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
            <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

		    <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
                <tr>
                    
                    <th>Tipo de producto</th>
                    <th>Identificador</th>
                    <th>SN</th>
                    <th>Fecha alta</th>
                    <th>Fecha baja</th>
                    <th>Producto</th>
                    <th>Marca</th>
                    <th>Pedido</th>
                    <th>Origen</th>
                    <th>Estado</th>
                    <th>Sala</th>
                    <th>Edificio</th>
                    <th>Año de adquisición</th>
                </tr>

                <?php
                    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
                        echo "<tr>";

                            
                            echo "<td>". $row['nombre_familia_prod'] ."</td>";

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
                            echo "<td>". $row['anio'] ."</td>";

                        echo "</tr>";
                    }
                ?>

            </table>

            <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />
            </form>

            <?php
    break;

    case 13:
        $query="select count(*) as cantidad, familia_producto.nombre_familia_prod FROM dispositivo, producto, familia_producto WHERE (dispositivo.id_producto=producto.id_prod) && (producto.id_familia_prod=familia_producto.id_familia_prod) group by familia_producto.id_familia_prod;";
    
        $result=mysqli_query($enlace,$query) 
            or die("Error");

        ?>
        <h1 class="text-center">Cantidad de dispositivos agrupados por tipo </h1>

        <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
        <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

        <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
            <tr>
                <th>Tipo de producto</th>
                <th>Cantidad</th>
            </tr>
        <?php
        
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            echo "<tr>";
                echo "<td>". $row['nombre_familia_prod'] ."</td>";
                echo "<td>". $row['cantidad'] ."</td>";      
            echo "</tr>";
        }
        ?>
        </table>

        <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />
        </form>

        <?php
    break;


    case 14:
        $query="SELECT count(*) as cantidad, familia_producto.nombre_familia_prod, proveedor.nombre_prov FROM dispositivo, producto, pedido, proveedor, familia_producto WHERE (dispositivo.id_producto=producto.id_prod) && (producto.id_familia_prod=familia_producto.id_familia_prod) && (dispositivo.id_ped=pedido.id_ped) && (pedido.id_prov=proveedor.id_prov) GROUP BY familia_producto.id_familia_prod, proveedor.id_prov ORDER BY familia_producto.nombre_familia_prod;";
    
        $result=mysqli_query($enlace,$query) 
            or die("Error");

        ?>
        <h1 class="text-center"> Cantidad de dispositivos agrupados por tipo y proveedor</h1>

        <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
        <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

        <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
            <tr>
                <th>Tipo de producto</th>
                <th>Proveedor</th>
                <th>Cantidad</th>
            </tr>
        <?php
        
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            echo "<tr>";
                echo "<td>". $row['nombre_familia_prod'] ."</td>";
                echo "<td>". $row['nombre_prov'] ."</td>";
                echo "<td>". $row['cantidad'] ."</td>";
            echo "</tr>";
        }
        ?>
        </table>

        <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />
        </form>

        <?php
    break;

    case 15:
        $query="SELECT count(*) as cantidad, estado.nombre_estado, familia_producto.nombre_familia_prod FROM dispositivo, producto, estado_dispositivo, estado, familia_producto WHERE (estado_dispositivo.fecha_fin_est_disp is NULL) && (dispositivo.id_producto=producto.id_prod) && (estado_dispositivo.id_disp=dispositivo.id_disp) && (estado_dispositivo.id_estado=estado.id_estado) && (producto.id_familia_prod=familia_producto.id_familia_prod) GROUP BY estado.id_estado, familia_producto.id_familia_prod ORDER BY familia_producto.id_familia_prod;";

        $result=mysqli_query($enlace,$query) 
            or die("Error");

        ?>
        <h1 class="text-center"> Cantidad de dispositivos agrupados por tipo y estado</h1>

        <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
        <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

        <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
            <tr>
                <th>Tipo de producto</th>
                <th>Estado</th>
                <th>Cantidad</th>
            </tr>
        <?php

        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            echo "<tr>";
                echo "<td>". $row['nombre_familia_prod'] ."</td>";
                echo "<td>". $row['nombre_estado'] ."</td>";
                echo "<td>". $row['cantidad'] ."</td>";
            echo "</tr>";
        }
        ?>
        </table>

        <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />
        </form>

        <?php
    break;

    case 16:
            $query="SELECT dispositivo.*, producto.nombre_prod, pedido.codigo_ped, origen_equipo.nombre_ori_eq, estado.nombre_estado, estado_dispositivo.fecha_fin_est_disp, sala.nombre_sala, marca.nombre_marca, familia_producto.nombre_familia_prod, edificio.nombre_edif, year(fecha_alta_disp) as anio FROM dispositivo, producto, pedido, origen_equipo, estado_dispositivo, estado, ubicacion_dispositivo, sala, marca, familia_producto, edificio WHERE (ubicacion_dispositivo.fecha_salida_disp_sala is NULL) && (estado_dispositivo.fecha_fin_est_disp is NULL) && (dispositivo.id_producto=producto.id_prod) && (dispositivo.id_ped=pedido.id_ped) && (dispositivo.id_ori_eq=origen_equipo.id_ori_eq) && (estado_dispositivo.id_disp=dispositivo.id_disp) && (estado_dispositivo.id_estado=estado.id_estado) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.id_sala=sala.id_sala) && (marca.id_marca=producto.id_marca) && (producto.id_familia_prod=familia_producto.id_familia_prod) && (sala.id_edif=edificio.id_edif) order by producto.nombre_prod asc;";
            
            $result=mysqli_query($enlace,$query) 
                or die("Error");
	
            ?>
            <h1 class="text-center">Dispositivos ordenados por producto</h1>

            <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
            <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

		    <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
                <tr>
                    
                    <th>Producto</th>
                    <th>Identificador</th>
                    <th>SN</th>
                    <th>Fecha alta</th>
                    <th>Fecha baja</th>
                    <th>Tipo de producto</th>
                    <th>Marca</th>
                    <th>Pedido</th>
                    <th>Origen</th>
                    <th>Estado</th>
                    <th>Sala</th>
                    <th>Edificio</th>
                    <th>Año de adquisición</th>
                </tr>

                <?php
                    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
                        echo "<tr>";

                            
                            echo "<td>". $row['nombre_prod'] ."</td>";

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
                            echo "<td>". $row['anio'] ."</td>";

                        echo "</tr>";
                    }
                ?>

            </table>

            <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />
            </form>

            <?php
    break;

    case 17:
        $query="select count(*) as cantidad, producto.nombre_prod FROM dispositivo, producto WHERE (dispositivo.id_producto=producto.id_prod) GROUP BY producto.id_prod ORDER BY producto.nombre_prod;";

        $result=mysqli_query($enlace,$query) 
            or die("Error");

        ?>
        <h1 class="text-center">Cantidad de dispositivos agrupados por producto </h1>

        <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
        <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

        <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
            <tr>
                <th>Producto</th>
                <th>Cantidad</th>
            </tr>
        <?php
        
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            echo "<tr>";
                echo "<td>". $row['nombre_prod'] ."</td>";
                echo "<td>". $row['cantidad'] ."</td>";      
            echo "</tr>";
        }
        ?>
        </table>

        <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />
        </form>

        <?php
    break;


    case 18:
        $query="SELECT count(*) as cantidad, producto.nombre_prod, proveedor.nombre_prov FROM dispositivo, producto, pedido, proveedor WHERE (dispositivo.id_producto=producto.id_prod) && (dispositivo.id_ped=pedido.id_ped) && (pedido.id_prov=proveedor.id_prov) GROUP BY producto.id_prod, proveedor.id_prov ORDER BY producto.nombre_prod;";

        $result=mysqli_query($enlace,$query) 
            or die("Error");

        ?>
        <h1 class="text-center">Cantidad de dispositivos agrupados por producto y proveedor</h1>

        <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
        <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

        <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
            <tr>
                <th>Producto</th>
                <th>Proveedor</th>
                <th>Cantidad</th>
            </tr>
        <?php
        
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            echo "<tr>";
                echo "<td>". $row['nombre_prod'] ."</td>";
                echo "<td>". $row['nombre_prov'] ."</td>";
                echo "<td>". $row['cantidad'] ."</td>";
            echo "</tr>";
        }
        ?>
        </table>

        <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />
        </form>

        <?php
    break;
    

    case 19:
        $query="SELECT count(*) as cantidad, estado.nombre_estado, producto.nombre_prod FROM dispositivo, producto, estado_dispositivo, estado WHERE (estado_dispositivo.fecha_fin_est_disp is NULL) && (dispositivo.id_producto=producto.id_prod) && (estado_dispositivo.id_disp=dispositivo.id_disp) && (estado_dispositivo.id_estado=estado.id_estado) GROUP BY estado.id_estado, producto.id_prod ORDER BY producto.nombre_prod;";

        $result=mysqli_query($enlace,$query) 
            or die("Error");

        ?>
        <h1 class="text-center"> Cantidad de dispositivos agrupados por producto y estado</h1>

        <a href="#" class="botonExcel"> <i class="fa fa-table" title="Dar de baja"></i>Exportar a excel </a>
        <form action="ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion">

        <table border="1" id="example" class="table table-striped table-hover table-secondary text-center"> 
            <tr>
                <th>Producto</th>
                <th>Estado</th>
                <th>Cantidad</th>
            </tr>
        <?php

        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            echo "<tr>";
                echo "<td>". $row['nombre_prod'] ."</td>";
                echo "<td>". $row['nombre_estado'] ."</td>";
                echo "<td>". $row['cantidad'] ."</td>";
            echo "</tr>";
        }
        ?>
        </table>

        <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />
        </form>

        <?php
    break;

}

?>



</body>
</html>