<?php
    require("conexion.php");

    $contador=0;
    $id_sala=$_GET['id_sala'];

    $query_comprobar="SELECT * FROM dispositivos WHERE id_disp='".$id_disp."';";
    $result=mysqli_query($enlace,$query_comprobar);
    
    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
        $contador=$contador+1;
    }

    if($contador==0){
        $query="DELETE FROM `sala` WHERE `sala`.`id_sala` = '".$id_sala."';";
            
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        mysqli_close($enlace);
        header("Location:mantenimiento_sala.php");
    }
    else{
        header("Location:mantenimiento_sala.php?error=si");
    }
     
?>






