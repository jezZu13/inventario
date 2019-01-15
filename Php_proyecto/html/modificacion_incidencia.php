<?php

        require("conexion.php");

        $id_inci=$_GET['id_inci'];
        $descripcion=$_POST['descripcion'];

   
        $query="UPDATE `incidencia` SET `descripcion_inc` = '$descripcion' WHERE `incidencia`.`numero_inc` = '$id_inci' ;";
        
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        header("Location:void.php?comprobar=cambio_correcto");
        mysqli_close($enlace);
?>


