<?php

        require("conexion.php");

        $id_ped=$_GET['id_ped'];

        $codigo_ped=$_POST['nref'];
        $fecha_pet_ped=$_POST['fecha_solicitud'];
        $id_prov=$_POST['proveedor'];


        $query="UPDATE `pedido` SET `codigo_ped` = '$codigo_ped', `fecha_pet_ped` = '$fecha_pet_ped', `id_prov` = '$id_prov' WHERE `pedido`.`id_ped` = '$id_ped' ;";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        //echo "Modificación realizada"
        header("Location:void.php?comprobar=cambio_correcto");
            
        

        mysqli_close($enlace);

?>


