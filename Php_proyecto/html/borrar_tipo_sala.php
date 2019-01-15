<?php
    require("conexion.php");

    $contador=0;
    $id_tipo_sala=$_GET['id_tipo_sala'];

    $query_comprobar="SELECT * FROM sala WHERE id_sala='".$id_sala."';";
    $result=mysqli_query($enlace,$query_comprobar);
    
    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
        $contador=$contador+1;
    }

    if($id_tipo_sala!="2"){ //2 es el id_tipo_sala que corresponde a "Almacen".
        if($contador==0){
            $query="DELETE FROM `tipo_sala` WHERE `tipo_sala`.`id_tipo_sala` = '".$id_tipo_sala."';";
                
            mysqli_query($enlace,$query) 
                or die (header("Location:void.php?comprobar=error"));
            mysqli_close($enlace);
            header("Location:mantenimiento_tipo_sala.php");
        }
        else{
            header("Location:mantenimiento_tipo_sala.php?error=si");
        }
    }
    else{
        header("Location:mantenimiento_tipo_sala.php?comprobar=nopermitir");
    }
?>






