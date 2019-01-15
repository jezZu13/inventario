<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/mdb.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/estilos.css">

<?php

    //Archivo de conexión a la base de datos
    require("conexion.php");

    //Variable de búsqueda
    $id_disp = $_POST['valorBusqueda'];

    //Realizamos la query
    $query="SELECT dispositivo.*, producto.nombre_prod, pedido.id_ped, pedido.codigo_ped, origen_equipo.nombre_ori_eq, estado_dispositivo.id_estado, estado.nombre_estado, estado_dispositivo.fecha_fin_est_disp, ubicacion_dispositivo.id_sala, sala.nombre_sala, marca.id_marca, marca.nombre_marca, familia_producto.nombre_familia_prod, producto.foto_prod FROM dispositivo, producto, pedido, origen_equipo, estado_dispositivo, estado, ubicacion_dispositivo, sala, marca, familia_producto WHERE (dispositivo.id_producto=producto.id_prod) && (dispositivo.id_ped=pedido.id_ped) && (dispositivo.id_ori_eq=origen_equipo.id_ori_eq) && (estado_dispositivo.id_disp=dispositivo.id_disp) && (estado_dispositivo.id_estado=estado.id_estado) && (estado_dispositivo.fecha_fin_est_disp is NULL) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.fecha_salida_disp_sala is NULL)&&(ubicacion_dispositivo.id_sala=sala.id_sala) && (marca.id_marca=producto.id_marca) && (producto.id_familia_prod=familia_producto.id_familia_prod) && (dispositivo.id_disp='$id_disp')";

    $result=mysqli_query($enlace,$query) 
        or die("No se han podido cargar los productos");
    while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
        $id_disp=$row['identificador_disp'];
        $sn_disp=$row['sn_disp'];
        $fecha_alta_disp=$row['fecha_alta_disp'];
        $fecha_baja_disp=$row['fecha_baja_disp'];
        $MAC_disp=$row['MAC_disp'];
        $observaciones_disp=$row['observaciones_disp'];
        $nombre_prod=$row['nombre_prod'];
        $nombre_familia_prod=$row['nombre_familia_prod'];
        $nombre_marca=$row['nombre_marca'];
        $codigo_ped=$row['codigo_ped'];
        $nombre_ori_eq=$row['nombre_ori_eq'];
        $nombre_estado=$row['nombre_estado'];
        $nombre_sala=$row['nombre_sala'];
        $foto=$row['foto_prod'];
    }
    
    if($fecha_baja_disp==null){
        $fecha_baja_disp="Dispositivo activo";
    }

    if($codigo_ped=="Desconocido"){
        $codigo_ped="";
    }


    echo "<table class='table table-striped table-sm table-hover table-secondary'>";
        echo "<tr>";
            echo "<td> Identificador </td>";
            echo "<td>".$id_disp."</td>";
        echo "</tr>";
        echo "<tr>";
            echo "<td> SN </td>";
            echo "<td>".$sn_disp."</td>";
        echo "</tr>";
        echo "<tr>";
            echo "<td> Fecha alta </td>";
            echo "<td>".$fecha_alta_disp."</td>";
        echo "</tr>";
        echo "<tr>";
            echo "<td> Fecha baja </td>";
            echo "<td>".$fecha_baja_disp."</td>";
        echo "</tr>";
        echo "<tr>";
            echo "<td> MAC </td>";
            echo "<td>".$MAC_disp."</td>";
        echo "</tr>";
        echo "<tr>";
            echo "<td> Observaciones </td>";
            echo "<td>".$observaciones_disp."</td>";
        echo "</tr>";
        echo "<tr>";
            echo "<td> Producto </td>";
            echo "<td>".$nombre_prod."</td>";
        echo "</tr>";
        echo "<tr>";
            echo "<td> Tipo de producto </td>";
            echo "<td>".$nombre_familia_prod."</td>";
        echo "</tr>";
        echo "<tr>";
            echo "<td> Marca </td>";
            echo "<td>".$nombre_marca."</td>";
        echo "</tr>";
        echo "<tr>";
            echo "<td> Pedido </td>";
            echo "<td>".$codigo_ped."</td>";
        echo "</tr>";
        echo "<tr>";
            echo "<td> Origen </td>";
            echo "<td>".$nombre_ori_eq."</td>";
        echo "</tr>";
        echo "<tr>";
            echo "<td> Estado </td>";
            echo "<td>".$nombre_estado."</td>";
        echo "</tr>";
        echo "<tr>";
            echo "<td> Sala </td>";
            echo "<td>".$nombre_sala."</td>";
        echo "</tr>";
    echo "</table>";
    echo "<div class='text-center'>";
    echo "<img src='".$foto."' alt='' class='img_modal'>";
    echo "</div>";
    $fin="";
    echo $fin;
?>

