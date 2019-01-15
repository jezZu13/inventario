<?php
    require("conexion.php");

    $contador=0;
    $id_ori_eq=$_GET['id_ori_eq'];

    $query_comprobar="SELECT * FROM dispositivos WHERE id_disp='".$id_disp."';";
    $result=mysqli_query($enlace,$query_comprobar);
    
    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
        $contador=$contador+1;
    }

    if($contador==0){
        $query="DELETE FROM `origen_equipo` WHERE `origen_equipo`.`id_ori_eq` = '".$id_ori_eq."';";
            
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        mysqli_close($enlace);
        header("Location:mantenimiento_origen.php");
    }
    else{
        header("Location:mantenimiento_origen.php?error=si");
    }
     
?>






