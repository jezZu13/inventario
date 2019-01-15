<?php
    require("conexion.php");

    $contador=0;
    $id_familia_prod=$_GET['id_familia_prod'];

    $query_comprobar="SELECT * FROM producto WHERE id_familia_prod='".$id_familia_prod."';";
    $result=mysqli_query($enlace,$query_comprobar);
    
    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
        $contador=$contador+1;
    }

    if($contador==0){
        $query="DELETE FROM `familia_producto` WHERE `familia_producto`.`id_familia_prod` = '".$id_familia_prod."';";
            
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        mysqli_close($enlace);
        header("Location:mantenimiento_familia_producto.php");
    }
    else{
        header("Location:mantenimiento_familia_producto.php?error=si");
    }
?>






