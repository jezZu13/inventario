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


        $id_prod=$_GET['id_prod'];

        $id_marca=$_POST['marca'];
        //$id_familia_prod=$_POST['familia'];
        $id_familia_prod=$_POST['tipo'];
        $nombre_prod=$_POST['nombre_producto'];
        $descripcion_prod=$_POST['descripcion'];


        if($img_capturada==1){//Si se ha cargado la imagen correctamente, realizamos el insert en la bbdd añadiendo el path (ruta) de la imagen.

            $query="UPDATE `producto` SET `nombre_prod` = '$nombre_prod', `descripcion_prod` = '$descripcion_prod', `id_marca` = '$id_marca', `id_familia_prod` = '$id_familia_prod', `foto_prod` = '$rutaimagen' WHERE `producto`.`id_prod` = '$id_prod' ;";
            mysqli_query($enlace,$query) 
                or die (header("Location:void.php?comprobar=error"));
            header("Location:void.php?comprobar=cambio_correcto");
        }
        else{

            $query="UPDATE `producto` SET `nombre_prod` = '$nombre_prod', `descripcion_prod` = '$descripcion_prod', `id_marca` = '$id_marca', `id_familia_prod` = '$id_familia_prod' WHERE `producto`.`id_prod` = '$id_prod' ;";
            mysqli_query($enlace,$query) 
                or die (header("Location:void.php?comprobar=error"));
            header("Location:void.php?comprobar=cambio_correcto_sin_imagen");
        }


        mysqli_close($enlace);
?>


