<?php
    require("conexion.php");

    $id_prod=$_GET['id_prod'];

    $contador_disp=0;
    $contador_prod_en_ped=0;

    //Comprobación 1:
    $query_comprobar_dispositivos="SELECT * FROM dispositivo WHERE id_producto='".$id_prod."';";
    $result1=mysqli_query($enlace,$query_comprobar_dispositivos);
    
    while($row1=mysqli_fetch_array($result1,MYSQLI_ASSOC) ){
        $contador_disp=$contador_disp+1;
    }

    //Comprobación 2:
    $query_comprobar_prod_en_ped="SELECT * FROM producto_en_pedido WHERE id_prod='".$id_prod."';";
    $result2=mysqli_query($enlace,$query_comprobar_prod_en_ped);
    
    while($row2=mysqli_fetch_array($result2,MYSQLI_ASSOC) ){
        $contador_prod_en_ped=$contador_prod_en_ped+1;
    }

    if(($contador_disp==0)&&($contador_prod_en_ped==0)){
        $query="DELETE FROM `producto` WHERE `producto`.`id_prod` = '".$id_prod."';";
            
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        header("Location:mantenimiento_producto.php");
        mysqli_close($enlace);
    }
    else{
        header("Location:mantenimiento_producto.php?error=si");
        mysqli_close($enlace);
    }
     
?>






