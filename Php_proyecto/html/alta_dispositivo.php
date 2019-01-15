<?php

    require("conexion.php");

    $sw=1; //Interruptor(switch) utilizado para salir del bucle de "comparar" en caso de que encuentre el dispositivo en la tabla.

    $identificador_disp=$_POST['identificador_disp'];
    $sn_disp=$_POST['sn_disp'];
    $fecha_alta_disp=$_POST['fecha_alta_disp'];
    $fecha_baja_disp=NULL; //La fecha de baja originalmente tiene que ser NULL.
    $MAC_disp=$_POST['MAC_disp'];
    $observaciones_disp=$_POST['observaciones_disp'];
    $id_prod=$_POST['producto'];
    $id_ped="1"; //Siempre se da de alta con el pedido "Desconocido". El ID de dicho tipo de pedido es el "1".
    $id_ori_eq=$_POST['origen_equipo'];
    $cantidad=$_POST['cantidad'];
    $i=0;

    /*
        Las siguientes líneas de código se utilizan para comprobar si el dispositivo a dar de alta ya existe. Para ello se comprueba si ya existe un dispositivo
        con el mismo serial number.
    */
    $query_buscar="SELECT sn_disp FROM dispositivo;";
    $result=mysqli_query($enlace,$query_buscar);

    if($sn_disp!=""){
        while( ( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ) && ($sw==1) ){
            if(strcasecmp($row['sn_disp'],$sn_disp)==0) {
                $sw=0;
            }
        }
    }
    //Fin comparación

    if($sw==1){//Si el dispositivo no existe con anterioridad, realizamos el insert en la BBDD.

        while($i<$cantidad){ //Damos de alta el numero de dispositivos que se hayan especificado.

            $query="INSERT INTO `dispositivo` (`id_disp`, `identificador_disp`, `sn_disp`, `fecha_alta_disp`, `fecha_baja_disp`, `MAC_disp`, `observaciones_disp`, `id_producto`, `id_ped`, `id_ori_eq`) VALUES (NULL, '$identificador_disp', '$sn_disp', '$fecha_alta_disp', NULL, '$MAC_disp', '$observaciones_disp', '$id_prod', '$id_ped', '$id_ori_eq');";
            $result=mysqli_query($enlace,$query) 
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
                $query="INSERT INTO `estado_dispositivo` (`id_disp`, `id_estado`, `fecha_inicio_est_disp`, `fecha_fin_est_disp`) VALUES ('$id_disp', '$id_estado', '$fecha_alta_disp', NULL);";
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
                $query="INSERT INTO `ubicacion_dispositivo` (`id_disp`, `id_sala`, `fecha_entrada_disp_sala`, `fecha_salida_disp_sala`) VALUES ('$id_disp', '$id_sala', '$fecha_alta_disp', NULL);";
                $result=mysqli_query($enlace,$query) 
                    or die (header("Location:void.php?comprobar=error"));
            
                $i=$i+1;
        }

        //FIN
        mysqli_close($enlace);
        header("Location:void.php?comprobar=correcto");
    }
    else{
        //echo "Dispositivo duplicado. No se registra puesto que ya existe.";
        mysqli_close($enlace);
        header("Location:void.php?comprobar=duplicado");
    }

    
?>

