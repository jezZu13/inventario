<?php
    require("conexion.php");

    $id_ped=$_GET['id_ped'];
    $id_prod=$_GET['id_prod'];
    $id_ori_eq=$_GET['id_ori_eq'];
    
    $query="DELETE FROM `producto_en_pedido` WHERE `producto_en_pedido`.`id_ped` = '".$id_ped."' AND `producto_en_pedido`.`id_prod` = '".$id_prod."' AND `producto_en_pedido`.`id_ori_eq` = '".$id_ori_eq."';";
            
    mysqli_query($enlace,$query) 
        or die (header("Location:void.php?comprobar=error"));
    mysqli_close($enlace);
    header("Location:mantenimiento_pedido.php");

     
?>






