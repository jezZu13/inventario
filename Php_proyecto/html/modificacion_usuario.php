<?php

        require("conexion.php");

        $id_usuario=$_GET['id_usuario'];
        $nombre_usuario=$_POST['nombre_usuario'];
        $rol_usuario=$_POST['rol_usuario'];

        $query="UPDATE `usuarios_app` SET `nombre_usuario` = '$nombre_usuario', `rol_usuario` = '$rol_usuario' WHERE `usuarios_app`.`id_usuario` = '$id_usuario' ;";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        header("Location:void.php?comprobar=cambio_correcto");
        mysqli_close($enlace);
?>


