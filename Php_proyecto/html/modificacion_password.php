<?php

        require("conexion.php");

        $id_usuario=$_GET['id_usuario'];
        $pass_usuario=$_POST['nueva_passwd'];

        $query="UPDATE `usuarios_app` SET `pass_usuario` = '$pass_usuario' WHERE `usuarios_app`.`id_usuario` = '$id_usuario' ;";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        header("Location:void.php?comprobar=cambio_correcto");
        mysqli_close($enlace);
?>


