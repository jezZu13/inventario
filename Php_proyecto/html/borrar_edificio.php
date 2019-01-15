<?php
    require("conexion.php");

    $contador=0;
    $id_edif=$_GET['id_edif'];

    $query_comprobar="SELECT * FROM sala WHERE id_sala='".$id_sala."';";
    $result=mysqli_query($enlace,$query_comprobar);
    
    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
        $contador=$contador+1;
    }

    if($contador==0){
        $query="DELETE FROM `edificio` WHERE `edificio`.`id_edif` = '".$id_edif."';";
            
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        mysqli_close($enlace);
        header("Location:mantenimiento_edificio.php");
    }
    else{
        header("Location:mantenimiento_edificio.php?error=si");
    }
     
?>






