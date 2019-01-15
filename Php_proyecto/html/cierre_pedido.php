<?php

        require("conexion.php");

        $contador=0;

        $id_ped=$_GET['id_ped'];

        $fecha_recepcion_ped=$_POST['fecha_recepcion_ped'];

        $query="UPDATE `pedido` SET `fecha_recepcion_ped` = '$fecha_recepcion_ped', `estado` = '1' WHERE `pedido`.`id_ped` = '$id_ped' ;";
        mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        //header("Location:void.php?comprobar=cambio_correcto");

        //Vamos a registrar automáticamente la cantidad de dispositivos correspondientes al pedido:
        $query="SELECT * FROM producto_en_pedido WHERE id_ped='$id_ped';";
        $result=mysqli_query($enlace,$query) 
            or die (header("Location:void.php?comprobar=error"));
        
        $i=0;
        $id_prod=array();
        $nunidades_prod_ped=array();
        $id_ori_eq=array();
        

        while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
            $id_prod[$i]=$row['id_prod'];
            $nunidades_prod_ped[$i]=$row['nunidades_prod_ped'];
            $id_ori_eq[$i]=$row['id_ori_eq'];
            $i=$i+1;
        }
        
        $i=0;
        while( $i < count($nunidades_prod_ped) ){
            while($contador<$nunidades_prod_ped[$i]){
                $queryDisp="INSERT INTO `dispositivo` (`id_disp`, `identificador_disp`, `sn_disp`, `fecha_alta_disp`, `fecha_baja_disp`, `MAC_disp`, `observaciones_disp`, `id_producto`, `id_ped`, `id_ori_eq`) VALUES (NULL, NULL, NULL, '$fecha_recepcion_ped', NULL, NULL, NULL, '".$id_prod[$i]."', '$id_ped', '".$id_ori_eq[$i]."');";
                mysqli_query($enlace,$queryDisp) 
                    or die (header("Location:void.php?comprobar=error"));
                
                //Con la siguiente línea obtenemos el ID del dispositivo que acabamos de dar de alta
                $id_disp=mysqli_insert_id($enlace);

                //Vamos a dar de alta el dispositivo en la tabla de "estado_dispositivo"
                    //En primer lugar obtenemos el ID del estado de los productos en stock
                    $query="SELECT id_estado from estado WHERE nombre_estado='En stock';";
                    $result=mysqli_query($enlace,$query) 
                        or die ("Error");
                    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
                        $id_estado=$row['id_estado'];
                    }
                    //Ahora vamos a programar el insert en la tabla
                    $query="INSERT INTO `estado_dispositivo` (`id_disp`, `id_estado`, `fecha_inicio_est_disp`, `fecha_fin_est_disp`) VALUES ('$id_disp', '$id_estado', '$fecha_recepcion_ped', NULL);";
                    $result=mysqli_query($enlace,$query) 
                        or die (header("Location:void.php?comprobar=error"));

                //Vamos a dar de alta el dispositivo en la tabla de "ubicacion_dispositivo"
                    //En primer lugar obtenemos el ID de la sala "Almacen"
                    $query="SELECT id_sala FROM sala WHERE nombre_sala='Almacen';";
                    $result=mysqli_query($enlace,$query) 
                        or die ("Error");
                    while($row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
                        $id_sala=$row['id_sala'];
                    }
                    //Ahora vamos a programar el insert en la tabla
                    $query="INSERT INTO `ubicacion_dispositivo` (`id_disp`, `id_sala`, `fecha_entrada_disp_sala`, `fecha_salida_disp_sala`) VALUES ('$id_disp', '$id_sala', '$fecha_recepcion_ped', NULL);";
                    $result=mysqli_query($enlace,$query) 
                        or die (header("Location:void.php?comprobar=error"));

                $contador=$contador+1;
            }
            $contador=0;
            $i=$i+1;
        }

        header("Location:void.php?comprobar=cierre_correcto");

        mysqli_close($enlace);
?>


