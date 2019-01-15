<?php

        require("conexion.php");

        $sw=1; //Interruptor(switch) utilizado para salir del bucle de "comparar" en caso de que encuentre el proveedor en la tabla.
        $nombre_proveedor=$_POST['nombre_proveedor'];
        $persona_contacto=$_POST['persona_contacto'];
        $telefono=$_POST['telefono'];
        $direccion=$_POST['direccion'];
        $email=$_POST['email'];
        $descripcion=$_POST['descripcion'];

        //Las siguientes líneas de código se utilizan para comprobar si el proveedor a dar de alta ya existe.
        $query_buscar="SELECT nombre_prov FROM proveedor;";
        $result=mysqli_query($enlace,$query_buscar);
        while( ( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ) && ($sw==1) ){

            if(strcasecmp($row['nombre_prov'],$nombre_proveedor)==0) {
                $sw=0;
            }
        }
        //Fin comparación

        if($sw==1){//Si no existe con anterioridad, realizamos el insert en la BBDD.
            $query="INSERT INTO `proveedor` (`nombre_prov`, `persona_contact_prov`, `telefono_prov`, `direccion_prov`, `email_prov`, `descripcion_prov`) VALUES ('$nombre_proveedor', '$persona_contacto', '$telefono', '$direccion', '$email', '$descripcion');";
            mysqli_query($enlace,$query) 
                or die (header("Location:void.php?comprobar=error"));
            //echo "Alta realizada"
            header("Location:void.php?comprobar=correcto");
        }
        else{
            //echo "Duplicado".
            header("Location:void.php?comprobar=duplicado");
        }

        mysqli_close($enlace);

?>

