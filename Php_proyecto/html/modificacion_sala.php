<?php

        require("conexion.php");

        $id_sala=$_GET['id_sala'];
        $nombre_sala=$_POST['nombre_sala'];
        $descripcion_sala=$_POST['descripcion_sala'];
        $usuario_sala=$_POST['usuario_sala'];
        $curso_asignado_sala=$_POST['curso_asignado_sala'];
        $edif = $_POST['edif'];
        $tipo_sala = $_POST['tipo_sala'];


        $query="UPDATE `sala` SET `nombre_sala` = '$nombre_sala', `usuario_sala` = '$usuario_sala', `curso_asignado_sala` = '$curso_asignado_sala', `descripcion_sala` = '$descripcion_sala', `id_tipo_sala` = '$tipo_sala', `id_edif` = '$edif' WHERE `sala`.`id_sala` = '$id_sala' ;";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        header("Location:void.php?comprobar=cambio_correcto");
        mysqli_close($enlace);
?>


