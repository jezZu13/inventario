<?php
    require("conexion.php");

    $id_disp=$_GET['id_disp'];

    if(isset($_GET['id_ped'])){

        //Obtenemos el id del origen del equipo:
        $query="SELECT id_ori_eq FROM dispositivo WHERE id_disp='$id_disp';";
        $result=mysqli_query($enlace,$query) 
            or die (header("Error.No se ha ejecutado la query"));
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            $id_ori_eq=$row['id_ori_eq'];
        }

        $id_ped=$_GET['id_ped'];
        $id_prod=$_GET['id_prod'];

        $query="SELECT nunidades_prod_ped FROM producto_en_pedido WHERE id_ped='$id_ped' and id_prod='$id_prod' and id_ori_eq='$id_ori_eq';";
        $result=mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            $nunidades=$row['nunidades_prod_ped'];
        }
        $nunidades--;

        $query="UPDATE `producto_en_pedido` SET `nunidades_prod_ped` = '$nunidades' WHERE `producto_en_pedido`.`id_ped` = '$id_ped' AND `producto_en_pedido`.`id_prod` = '$id_prod' AND `producto_en_pedido`.`id_ori_eq` = '$id_ori_eq';";
        $nunidades=mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
    }

    //Hay que comprobar si el dispositivo tiene o ha tenido incidencias <asignada:></asignada:>
    $contador=0;
    $query="SELECT * FROM incidencia WHERE  id_disp='$id_disp';";
    $result=mysqli_query($enlace,$query) or die (header("Error.No se ha ejecutado la query"));
    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
        $contador=$contador+1;
    }

    if($contador==0){
        //Vamos a borrarlo de la tabla estado_dispositivo
        $query="DELETE FROM `estado_dispositivo` WHERE `estado_dispositivo`.`id_disp` = '$id_disp';";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        header("Location:mantenimiento_dispositivo.php");

        //Vamos a borrarlo de la tabla ubicacion_dispositivo
        $query="DELETE FROM `ubicacion_dispositivo` WHERE `ubicacion_dispositivo`.`id_disp` = '$id_disp';";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        header("Location:mantenimiento_dispositivo.php");

        //Ahora, por último, eliminamos el dispositivo de la tabla de dispositivos
        $query="DELETE FROM `dispositivo` WHERE `dispositivo`.`id_disp` = '$id_disp';";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        mysqli_close($enlace);
        header("Location:mantenimiento_dispositivo.php");
    }
    else{
        mysqli_close($enlace);
        header("Location:mantenimiento_dispositivo.php?error=si");
    }

    
     
?>






