<?php

        require("conexion.php");

        $sw=1; //Interruptor(switch) utilizado para salir del bucle de "comparar" en caso de que encuentre el estado en la tabla.
        $nombre_estado=$_POST['nombre_estado'];
        $descripcion=$_POST['descripcion'];

        //Las siguientes líneas de código se utilizan para comprobar si el estado a dar de alta ya existe.
        $query_buscar="SELECT nombre_estado FROM estado;";
        $result=mysqli_query($enlace,$query_buscar);
        while( ( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ) && ($sw==1) ){

            if(strcasecmp($row['nombre_estado'],$nombre_estado)==0) {
                $sw=0;
            }
        }
        //Fin comparación

        if($sw==1){//Si no existe con anterioridad, realizamos el insert en la BBDD.
            $query="INSERT INTO `estado` (`nombre_estado`, `descripcion_estado`) VALUES ('$nombre_estado', '$descripcion');";
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

