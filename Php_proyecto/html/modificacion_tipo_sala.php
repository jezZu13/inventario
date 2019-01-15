<?php

    require("conexion.php");

    $id_tipo_sala=$_GET['id_tipo_sala'];
    $nombre_tipo_sala=$_POST['nombre_tipo_sala'];
    $descripcion_tipo_sala=$_POST['descripcion_tipo_sala'];

    if($id_tipo_sala!="2"){ //2 es el id_tipo_sala que corresponde a "Almacen".
        $query="UPDATE `tipo_sala` SET `nombre_tipo_sala` = '$nombre_tipo_sala', `descripcion_tipo_sala` = '$descripcion_tipo_sala' WHERE `id_tipo_sala` = '$id_tipo_sala' ;";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        header("Location:void.php?comprobar=cambio_correcto");
        mysqli_close($enlace);
    }
    else{
        header("Location:void.php?comprobar=nopermitir");
    }
?>


