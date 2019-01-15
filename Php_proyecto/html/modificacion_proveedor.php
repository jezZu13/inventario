<?php

        require("conexion.php");

        $id_prov=$_GET['id_prov'];
        $nombre_proveedor=$_POST['nombre_proveedor'];
        $persona_contacto=$_POST['persona_contacto'];
        $telefono=$_POST['telefono'];
        $direccion=$_POST['direccion'];
        $email=$_POST['email'];
        $descripcion=$_POST['descripcion'];

        $query="UPDATE `proveedor` SET `nombre_prov` = '$nombre_proveedor', `persona_contact_prov` = '$persona_contacto', `telefono_prov` = '$telefono', `direccion_prov` = '$direccion', `email_prov` = '$email', `descripcion_prov` = '$descripcion' WHERE `proveedor`.`id_prov` = '$id_prov' ;";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        header("Location:void.php?comprobar=cambio_correcto");
        mysqli_close($enlace);
?>


