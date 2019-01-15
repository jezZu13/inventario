<?php

        require("conexion.php");

        $id_familia_prod=$_GET['id_familia_prod'];
        $nombre_familia_prod=$_POST['nombre_familia_prod'];
        $descripcion_familia_prod=$_POST['descripcion_familia_prod'];

        $query="UPDATE `familia_producto` SET `nombre_familia_prod` = '$nombre_familia_prod', `descripcion_familia_prod` = '$descripcion_familia_prod' WHERE `familia_producto`.`id_familia_prod` = '$id_familia_prod' ;";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        header("Location:void.php?comprobar=cambio_correcto");
        mysqli_close($enlace);
?>


