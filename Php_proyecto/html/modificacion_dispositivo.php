<?php

        require("conexion.php");

        $id_disp=$_GET['id_disp'];
        $id_estado_actual=$_GET['id_estado_actual'];
        $id_sala_actual=$_GET['id_sala_actual'];
        $codigo_ped=$_GET['codigo_ped'];

        $identificador_disp=$_POST['identificador_disp'];
        $sn_disp=$_POST['sn_disp'];

        if(isset($_POST['fecha_alta_disp'])){
            $fecha_alta_disp=$_POST['fecha_alta_disp'];
        }
        
        $MAC_disp=$_POST['MAC_disp'];
        $observaciones_disp=$_POST['observaciones_disp'];

        if(isset($_POST['id_prod'])){
            $id_prod=$_POST['id_prod'];
        }

        if(isset($_POST['id_ori_eq'])){
            $id_ori_eq=$_POST['id_ori_eq'];
        }
        
        $id_estado_new=$_POST['id_estado_new'];
        $id_sala_new=$_POST['id_sala_new'];

        //Obtener la fecha de hoy
        $query="select current_date;";
        $result=mysqli_query($enlace,$query) 
            or die ("Error");
        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC)){
            $fecha_actual=$row['current_date'];
        }

        if($codigo_ped=="Desconocido"){
            //Podemos hacer todos las modificaciones.
            $query="UPDATE `dispositivo` SET `identificador_disp` = '$identificador_disp', `sn_disp` = '$sn_disp', `fecha_alta_disp` = '$fecha_alta_disp', `MAC_disp` = '$MAC_disp', `observaciones_disp` = '$observaciones_disp', `id_producto` = '$id_prod', `id_ori_eq` = '$id_ori_eq' WHERE `dispositivo`.`id_disp` = '$id_disp' ;";
            mysqli_query($enlace,$query) 
                or die (header("Location:void.php?comprobar=error"));
        }
        else{
            //Algunas modificaciones NO se pueden hacer.
            $query="UPDATE `dispositivo` SET `identificador_disp` = '$identificador_disp', `sn_disp` = '$sn_disp', `MAC_disp` = '$MAC_disp', `observaciones_disp` = '$observaciones_disp' WHERE `dispositivo`.`id_disp` = '$id_disp' ;";
            mysqli_query($enlace,$query) 
                or die (header("Location:void.php?comprobar=error"));
        }

        if($id_estado_actual!=$id_estado_new){
            //Actualizamos el estado del dispositivo

            //Primero finalizamos el estado actual
            $query="UPDATE `estado_dispositivo` SET `fecha_fin_est_disp` = '$fecha_actual' WHERE `estado_dispositivo`.`id_disp` = '$id_disp' AND `estado_dispositivo`.`id_estado` = '$id_estado_actual';";
            mysqli_query($enlace,$query) 
                or die (header("Location:void.php?comprobar=error"));

            //Después damos de alta al dispositivo con su nuevo estado
            $query="INSERT INTO `estado_dispositivo` (`id_disp`, `id_estado`, `fecha_inicio_est_disp`, `fecha_fin_est_disp`) VALUES ('$id_disp', '$id_estado_new', '$fecha_actual', NULL)";
            mysqli_query($enlace,$query) 
                or die (header("Location:void.php?comprobar=error"));
        }

        if($id_sala_actual!=$id_sala_new){
            //Actualizamos la ubicación del dispositivo

            //Primero finalizamos la ubicación actual del dispostivo
            $query="UPDATE `ubicacion_dispositivo` SET `fecha_salida_disp_sala` = '$fecha_actual' WHERE `ubicacion_dispositivo`.`id_disp` = '$id_disp' AND `ubicacion_dispositivo`.`id_sala` = '$id_sala_actual';";
            mysqli_query($enlace,$query) 
                or die (header("Location:void.php?comprobar=error"));

            //Después damos de alta al dispositivo en su nueva ubicacion
            $query="INSERT INTO `ubicacion_dispositivo` (`id_disp`, `id_sala`, `fecha_entrada_disp_sala`, `fecha_salida_disp_sala`) VALUES ('$id_disp', '$id_sala_new', '$fecha_actual', NULL)";
            mysqli_query($enlace,$query) 
                or die (header("Location:void.php?comprobar=error"));
        }

        header("Location:void.php?comprobar=cambio_correcto");
        mysqli_close($enlace);

?>


