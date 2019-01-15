<?php
        require("conexion.php");

        $sw=1; //Interruptor(switch) utilizado para salir del bucle de "comparar" en caso de que encuentre el pedido.
        $codigo_ped=$_POST['nref'];
        $fecha_pet_ped=$_POST['fecha_solicitud'];
        $id_prov=$_POST['proveedor'];


        //Las siguientes líneas de código se utilizan para comprobar si el pedido a dar de alta ya existe.
        $query_buscar="SELECT codigo_ped FROM pedido;";
        $result=mysqli_query($enlace,$query_buscar);
        while( ( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ) && ($sw==1) ){

            if(strcasecmp($row['codigo_ped'],$codigo_ped)==0) {
                $sw=0;
            }
        }
        //Fin comparación

        if($sw==1){//Si no existe con anterioridad, realizamos el insert en la BBDD.
                $query="INSERT INTO `pedido` (`codigo_ped`, `fecha_pet_ped`, `fecha_recepcion_ped`, `estado`, `id_prov`) VALUES ('$codigo_ped', '$fecha_pet_ped', NULL, '0', '$id_prov');"; //El 0 indica que el pedido está abierto y el 1 que el pedido está cerrado.
                mysqli_query($enlace,$query) 
                    or die (header("Location:void.php?comprobar=error"));
                //echo "Alta realizada"
                header("Location:producto_pedido.php?codigo_pedido=$codigo_ped");
            
        }
        else{
            //echo "Duplicado".
            header("Location:void.php?comprobar=duplicado");
        }

        mysqli_close($enlace);
?>


