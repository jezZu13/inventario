<?php
    require("conexion.php");

    $contador1=0;
    $contador2=0;
    $id_ped=$_GET['id_ped'];

    $query_comprobar="SELECT * FROM producto_en_pedido WHERE id_ped='".$id_ped."';";
    $result=mysqli_query($enlace,$query_comprobar);
    
    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
        $contador1=$contador1+1;
    }

    $query_comprobar="SELECT * FROM dispositivo WHERE id_ped='".$id_ped."';";
    $result=mysqli_query($enlace,$query_comprobar);

    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
        $contador2=$contador2+1;
    }

    if(($contador1==0)&&($contador2==0)){
        $query="DELETE FROM `pedido` WHERE `pedido`.`id_ped` = '".$id_ped."';";
                
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        mysqli_close($enlace);
        header("Location:mantenimiento_pedido.php");
    }
    else{
        header("Location:mantenimiento_pedido.php?error=si");
    }
    

?>







