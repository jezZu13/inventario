<?php
    require("conexion.php");

    $numero_inc=$_GET['numero_inci'];
    $id_disp=$_GET['id_disp'];
    $id_estado_actual=$_GET['id_estado_actual'];
    $id_sala_actual=$_GET['id_sala_actual'];

    $fecha_cierre_inc=$_POST['cierre'];
    $solucion_inc=$_POST['solucion'];
    $id_estado_nuevo=$_POST['estado_nuevo'];
    $id_sala_nueva=$_POST['ubicacion'];

    //Hay que comprobar si ha cambiado el estado.
    if($id_estado_actual!=$id_estado_nuevo){
        //Primero cerramos el estado actual:
        $query="UPDATE `estado_dispositivo` SET `fecha_fin_est_disp`='$fecha_cierre_inc' WHERE `estado_dispositivo`.`id_disp`=$id_disp AND `estado_dispositivo`.`fecha_fin_est_disp` is NULL;";
        mysqli_query($enlace,$query) or die (header("Location:void.php?comprobar=error"));
        //Despues le damos de alta con el nuevo estado:
        $query="INSERT INTO `estado_dispositivo`(`id_disp`, `id_estado`, `fecha_inicio_est_disp`) VALUES ('$id_disp','$id_estado_nuevo','$fecha_cierre_inc');";
        mysqli_query($enlace,$query) or die (header("Location:void.php?comprobar=error"));
    }

    //Hay que comprobar si ha cambiado la ubicacion
    if($id_sala_actual!=$id_sala_nueva){
        //Primero "sacamos" al equipo de la sala actual:
        $query="UPDATE `ubicacion_dispositivo` SET `fecha_salida_disp_sala`='$fecha_cierre_inc' WHERE `ubicacion_dispositivo`.`id_disp`=$id_disp AND `ubicacion_dispositivo`.`fecha_salida_disp_sala` is NULL;";
        mysqli_query($enlace,$query) or die (header("Location:void.php?comprobar=error"));
        //Despues le damos de alta en la nueva sala:
        $query="INSERT INTO `ubicacion_dispositivo`(`id_disp`, `id_sala`, `fecha_entrada_disp_sala`) VALUES ('$id_disp','$id_sala_nueva','$fecha_cierre_inc');";
        mysqli_query($enlace,$query) or die (header("Location:void.php?comprobar=error"));
    }

    //Cerramos la incidencia
    $sql="UPDATE `incidencia` SET `fecha_cierre_inc`='$fecha_cierre_inc', `solucion_inc`='$solucion_inc' WHERE `incidencia`.`numero_inc`=$numero_inc;";
    mysqli_query($enlace,$sql) or die (header("Location:void.php?comprobar=error"));

    mysqli_close($enlace);

    header("Location:void.php?comprobar=correcto");
?>