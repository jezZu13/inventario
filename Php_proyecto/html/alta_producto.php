<?php

        require("conexion.php");

        //*******************CAPTURAMOS LA IMAGEN PARA GUARDARLA******************
        // Recibo los datos de la imagen
        $nombre_img = $_FILES['imagen']['name'];
        $tipo = $_FILES['imagen']['type'];
        $tamano = $_FILES['imagen']['size'];
        $img_capturada=0;
        
        //Si existe imagen y tiene un tamaño correcto
        if ($nombre_img != NULL) {
            //indicamos los formatos que permitimos subir a nuestro servidor
            if ( ($_FILES["imagen"]["type"] == "image/gif") || ($_FILES["imagen"]["type"] == "image/jpeg") || ($_FILES["imagen"]["type"] == "image/jpg") || ($_FILES["imagen"]["type"] == "image/png") ){
                // Ruta donde se guardarán las imágenes que subamos
                
                //$directorio = $_SERVER['DOCUMENT_ROOT'].'/img/'; //Esta línea para el hosting.
                $directorio = $_SERVER['DOCUMENT_ROOT'].'/html/img/'; //Esta línea para local.
                
                //La ruta absoluta es "/var/www/vhost/majp.es/home/html/img/"

                // Muevo la imagen desde el directorio temporal a nuestra ruta indicada anteriormente
                move_uploaded_file($_FILES['imagen']['tmp_name'],$directorio.$nombre_img);
                // Guardamos la ruta (path) de la imagen en el servidor:
                $rutaimagen="img/".$nombre_img;
                $img_capturada=1;
            } 
                else 
                {
                //si no cumple con el formato
                echo "No se puede subir una imagen con ese formato";
                }
            } 
        else {
            //si existe la variable pero se pasa del tamaño permitido
            /*
            if($nombre_img !=NULL){
                echo "La imagen es demasiado grande "; 
            }
            */
        }
        //******************************FIN CARGA IMAGEN ***************************


        $sw=1; //Interruptor(switch) utilizado para salir del bucle de "comparar" en caso de que encuentre el producto en la tabla.
        $nombre_producto=$_POST['nombre_producto'];
        $marca=$_POST['marca'];
        $tipo=$_POST['tipo'];
        $descripcion=$_POST['caracteristicas'];

        //Las siguientes líneas de código se utilizan para comprobar si el producto a dar de alta ya existe. Para ello se comprueba que coincidan los campos de
        //nombre producto, tipo de producto y marca.
        $query_buscar="SELECT nombre_prod, id_marca, id_familia_prod FROM producto;";
        $result=mysqli_query($enlace,$query_buscar);
        while( ( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ) && ($sw==1) ){

            if( (strcasecmp($row['nombre_prod'],$nombre_producto)==0)&&(strcmp($row['id_marca'],$marca)==0)&&(strcmp($row['id_familia_prod'],$tipo)==0) ){
                $sw=0;
            }
        }
        //Fin comparación

        if($sw==1){//Si el producto no existe con anterioridad, realizamos el insert en la BBDD.
           
           if($img_capturada==1){//Si se ha cargado la imagen correctamente, realizamos el insert en la bbdd añadiendo el path (ruta) de la imagen.
                $query="INSERT INTO `producto` (`nombre_prod`, `descripcion_prod`, `id_marca`, `id_familia_prod`, `foto_prod`) VALUES ('$nombre_producto', '$descripcion', '$marca', '$tipo', '$rutaimagen');";
                mysqli_query($enlace,$query) 
                    or die (header("Location:void.php?comprobar=error"));
                //echo "El producto fue dado de alta.";
                header("Location:void.php?comprobar=correcto");
            }
            else{//Si NO se ha cargado la imagen correctamente, no almacenamos nada en el campo de "foto_prod"
                $query="INSERT INTO `producto` (`nombre_prod`, `descripcion_prod`, `id_marca`, `id_familia_prod`, `foto_prod`) VALUES ('$nombre_producto', '$descripcion', '$marca', '$tipo', NULL);";
                mysqli_query($enlace,$query) 
                    or die (header("Location:void.php?comprobar=error"));
                //echo "El producto fue dado de alta.";
                header("Location:void.php?comprobar=correcto_sin_imagen");
            }
        }
        else{//"Producto duplicado. No se ha registrado puesto que ya existe.";
            header("Location:void.php?comprobar=duplicado");
        }

        mysqli_close($enlace);
?>


