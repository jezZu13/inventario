<?php

        require("conexion.php");

        $id_marca=$_GET['id_marca'];
        $nombre_marca=$_POST['nombre_marca'];
        $descripcion_marca=$_POST['descripcion_marca'];

        $query="UPDATE `marca` SET `nombre_marca` = '$nombre_marca', `descripcion_marca` = '$descripcion_marca' WHERE `marca`.`id_marca` = '$id_marca' ;";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        header("Location:void.php?comprobar=cambio_correcto");
        mysqli_close($enlace);
?>


