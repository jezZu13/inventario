<?php

        require("conexion.php");

        $id_edif=$_GET['id_edif'];
        $nombre_edif=$_POST['nombre_edif'];
        $descripcion_edif=$_POST['descripcion_edif'];

        $query="UPDATE `edificio` SET `nombre_edif` = '$nombre_edif', `descripcion_edif` = '$descripcion_edif' WHERE `edificio`.`id_edif` = '$id_edif' ;";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        header("Location:void.php?comprobar=cambio_correcto");
        mysqli_close($enlace);
?>


