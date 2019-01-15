<?php

    require('conexion.php');

    $fecha_apertura_inc=$_POST['apertura'];
    $id_disp=$_POST['dispositivos'];
    $descripcion_inc=$_POST['descripcion'];
    $id_estado=$_POST['estado'];
    $id_sala=$_POST['ubicacion'];


    //Hay que comprobar si se ha cambiado el estado, y si ha cambiado actualizarlo
    if($id_estado!=''){
        //Primero cerramos el estado actual:
        $query="UPDATE `estado_dispositivo` SET `fecha_fin_est_disp`='$fecha_apertura_inc' WHERE `estado_dispositivo`.`id_disp`=$id_disp AND `estado_dispositivo`.`fecha_fin_est_disp` is NULL;";
        mysqli_query($enlace,$query) or die (header("Location:void.php?comprobar=error"));
        //Despues le damos de alta con el nuevo estado:
        $query="INSERT INTO `estado_dispositivo`(`id_disp`, `id_estado`, `fecha_inicio_est_disp`) VALUES ('$id_disp','$id_estado','$fecha_apertura_inc');";
        mysqli_query($enlace,$query) or die (header("Location:void.php?comprobar=error"));
    }

    //Hay que comprobar si se ha cambiado la sala, y si ha cambiado actualizarla.
    if($id_sala!=''){
        //Primero "sacamos" al equipo de la sala actual:
        $query="UPDATE `ubicacion_dispositivo` SET `fecha_salida_disp_sala`='$fecha_apertura_inc' WHERE `ubicacion_dispositivo`.`id_disp`=$id_disp AND `ubicacion_dispositivo`.`fecha_salida_disp_sala` is NULL;";
        mysqli_query($enlace,$query) or die (header("Location:void.php?comprobar=error"));
        //Despues le damos de alta en la nueva sala:
        $query="INSERT INTO `ubicacion_dispositivo`(`id_disp`, `id_sala`, `fecha_entrada_disp_sala`) VALUES ('$id_disp','$id_sala','$fecha_apertura_inc');";
        mysqli_query($enlace,$query) or die (header("Location:void.php?comprobar=error"));
    }

    //Generamos la incidencia:
    $query="INSERT INTO `incidencia` (`fecha_apertura_inc`,`descripcion_inc`,`id_disp`) VALUES ('$fecha_apertura_inc','$descripcion_inc','$id_disp');";
    mysqli_query($enlace,$query) or die (header("Location:void.php?comprobar=error"));

    mysqli_close($enlace);

    header("Location:void.php?comprobar=correcto");

?>