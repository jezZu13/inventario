<?php
    require("conexion.php");

    $id_prov=$_GET['id_prov'];
 

    $query="DELETE FROM `proveedor` WHERE `proveedor`.`id_prov` = '".$id_prov."';";
        
    mysqli_query($enlace,$query) 
        or die (header("Location:void.php?comprobar=error"));
    mysqli_close($enlace);
    header("Location:mantenimiento_proveedor.php");     
?>





