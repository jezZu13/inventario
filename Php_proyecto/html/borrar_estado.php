<?php
    require("conexion.php");

    $contador=0;
    $id_estado=$_GET['id_estado'];

    $query_comprobar="SELECT * FROM estado_dispositivo WHERE id_estado='".$id_estado."';";
    $result=mysqli_query($enlace,$query_comprobar);
    
    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
        $contador=$contador+1;
    }

    if($id_estado!="18"){ //18 es el id_estado que corresponde a "En stock"
        if($contador==0){
            $query="DELETE FROM `estado` WHERE `estado`.`id_estado` = '".$id_estado."';";
                
            mysqli_query($enlace,$query) 
                or die (header("Location:void.php?comprobar=error"));
            mysqli_close($enlace);
            header("Location:mantenimiento_estado.php");
        }
        else{
            header("Location:mantenimiento_estado.php?error=si");
        }
    }
    else{
        header("Location:mantenimiento_estado.php?comprobar=nopermitir");
    }
?>






