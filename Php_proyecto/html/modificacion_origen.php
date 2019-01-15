<?php

        require("conexion.php");

        $id_ori_eq=$_GET['id_ori_eq'];
        $nombre_ori_eq=$_POST['nombre_ori_eq'];
        $descripcion_ori_eq=$_POST['descripcion_ori_eq'];

        $query="UPDATE `origen_equipo` SET `nombre_ori_eq` = '$nombre_ori_eq', `descripcion_ori_eq` = '$descripcion_ori_eq' WHERE `origen_equipo`.`id_ori_eq` = '$id_ori_eq' ;";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        header("Location:void.php?comprobar=cambio_correcto");
        mysqli_close($enlace);
?>


