<?php
    require("conexion.php");

    $contador=0;
    $id_marca=$_GET['id_marca'];

    $query_comprobar="SELECT * FROM producto WHERE id_prod='".$id_prod."';";
    $result=mysqli_query($enlace,$query_comprobar);
    
    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
        $contador=$contador+1;
    }

    if($contador==0){
        $query="DELETE FROM `marca` WHERE `marca`.`id_marca` = '".$id_marca."';";
            
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        mysqli_close($enlace);
        header("Location:mantenimiento_marca.php");
    }
    else{
        header("Location:mantenimiento_marca.php?error=si");
    }
?>





