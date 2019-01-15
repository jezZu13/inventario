<?php
    require("conexion.php");

    $id_disp=$_GET['id_disp'];
    $id_sala_actual=$_GET['id_sala_actual'];
    $id_estado_actual=$_GET['id_estado_actual'];

    //Obtener la fecha de hoy
    $query="select current_date;";
    $result=mysqli_query($enlace,$query) 
        or die ("Error");
    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC)){
        $fecha_actual=$row['current_date'];
    }

    $query="UPDATE `dispositivo` SET `fecha_baja_disp` = '$fecha_actual' WHERE `dispositivo`.`id_disp` = '$id_disp' ;";
    mysqli_query($enlace,$query) 
        or die (header("Location:void.php?comprobar=error"));

    //CAMBIO DE UBICACIÓN AUTOMÁTICA
        //Primero finalizamos la ubicación actual del dispostivo
        $query="UPDATE `ubicacion_dispositivo` SET `fecha_salida_disp_sala` = '$fecha_actual' WHERE `ubicacion_dispositivo`.`id_disp` = '$id_disp' AND `ubicacion_dispositivo`.`id_sala` = '$id_sala_actual';";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));

        //Después damos de alta al dispositivo en su nueva ubicacion
        $query="INSERT INTO `ubicacion_dispositivo` (`id_disp`, `id_sala`, `fecha_entrada_disp_sala`, `fecha_salida_disp_sala`) VALUES ('$id_disp', '1', '$fecha_actual', NULL)"; //Donde 1 el el id_sala llamada "Basura".
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));

    //CAMBIO DE ESTADO AUTOMATICO
        //Primero finalizamos el estado actual
        $query="UPDATE `estado_dispositivo` SET `fecha_fin_est_disp` = '$fecha_actual' WHERE `estado_dispositivo`.`id_disp` = '$id_disp' AND `estado_dispositivo`.`id_estado` = '$id_estado_actual';";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));

        //Después damos de alta al dispositivo con su nuevo estado
        $query="INSERT INTO `estado_dispositivo` (`id_disp`, `id_estado`, `fecha_inicio_est_disp`, `fecha_fin_est_disp`) VALUES ('$id_disp', '1', '$fecha_actual', NULL)"; //Donde 1 el el id_estado llamado "Descatalogado".
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));

    mysqli_close($enlace);
    header("Location:mantenimiento_dispositivo.php");
     
?>







