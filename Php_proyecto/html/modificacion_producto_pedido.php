<?php

        require("conexion.php");

        $id_ped=$_GET['id_ped'];
        $id_prod=$_GET['id_prod'];

        $nombre_producto=$_POST['nombre_producto'];
        $nunidades=$_POST['nunidades'];
        $preciounidad=$_POST['preciounidad'];
        $id_ori_eq=$_POST['id_ori_eq'];

        $query="UPDATE `producto_en_pedido` SET `nunidades_prod_ped` = '$nunidades', `precio_por_unidad` = '$preciounidad' WHERE `producto_en_pedido`.`id_ped` = $id_ped AND `producto_en_pedido`.`id_prod` = $id_prod AND `producto_en_pedido`.`id_ori_eq` = $id_ori_eq;";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        header("Location:void.php?comprobar=cambio_correcto");
        mysqli_close($enlace);
?>


