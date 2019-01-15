<?php

        require("conexion.php");

        $sw=1; //Interruptor(switch) utilizado para salir del bucle de "comparar" en caso de que encuentre el producto en la tabla.
        $id_ped=$_POST['pedido'];
        $id_prod=$_POST['producto'];
        $nunidades_prod_ped=$_POST['nunidades'];
        $precio_por_unidad=$_POST['preciounidad'];
        $id_ori_eq=$_POST['origen_equipo'];

        //Las siguientes líneas de código se utilizan para comprobar si el registro está duplicado.
        $query_buscar="SELECT id_ped, id_prod, id_ori_eq FROM producto_en_pedido;";
        $result=mysqli_query($enlace,$query_buscar);
        while( ( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ) && ($sw==1) ){

            if( (strcasecmp($row['id_ped'],$id_ped)==0) && (strcmp($row['id_prod'],$id_prod)==0) && (strcmp($row['id_ori_eq'],$id_ori_eq)==0) ){
                $sw=0;
            }
        }
        //Fin comparación

        if($sw==1){//Si no hay duplicados, realizamos el insert en la BBDD.
            $query="INSERT INTO `producto_en_pedido` (`id_ped`, `id_prod`, `nunidades_prod_ped`, `precio_por_unidad`, `id_ori_eq`) VALUES ('$id_ped', '$id_prod', '$nunidades_prod_ped', '$precio_por_unidad', '$id_ori_eq');";
            mysqli_query($enlace,$query)
                or die (header("Location:void.php?comprobar=error"));
            //echo "El producto fue dado de alta.";
            if(isset($_POST['botonSeguir'])){
                header("Location:producto_pedido.php?id_pedido_retorno=$id_ped");
            }
            else{
                header("Location:void.php?comprobar=correcto");
            }
            
        }
        else{
            //echo "Producto duplicado. No se ha registrado puesto que ya existe.";
            if(isset($_POST['botonSeguir'])){
                header("Location:producto_pedido.php?id_pedido_retorno=$id_ped&comprobar=duplicado");
            }
            else{
                header("Location:void.php?comprobar=duplicado");
            }
        }

        mysqli_close($enlace);
?>



