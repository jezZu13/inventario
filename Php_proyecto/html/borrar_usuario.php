<?php
    require("conexion.php");

    $id_usuario=$_GET['id_usuario'];

    $query="DELETE FROM `usuarios_app` WHERE `usuarios_app`.`id_usuario` = '".$id_usuario."';";
            
    mysqli_query($enlace,$query) 
        or die (header("Location:void.php?comprobar=error"));
    mysqli_close($enlace);
    header("Location:mantenimiento_usuarios_APP.php"); 
?>






