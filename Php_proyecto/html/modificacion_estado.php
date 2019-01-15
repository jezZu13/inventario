<?php

    require("conexion.php");

    $id_estado=$_GET['id_estado'];
    $nombre_estado=$_POST['nombre_estado'];
    $descripcion=$_POST['descripcion'];

    if($id_estado!="18"){ //18 es el id_estado que corresponde a "En stock"
        $query="UPDATE `estado` SET `nombre_estado` = '$nombre_estado', `descripcion_estado` = '$descripcion' WHERE `estado`.`id_estado` = '$id_estado' ;";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        header("Location:void.php?comprobar=cambio_correcto");
        mysqli_close($enlace);
    }
    else{
        header("Location:void.php?comprobar=nopermitir");
    }
?>


