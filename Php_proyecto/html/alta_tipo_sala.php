<?php	
	require('conexion.php');
	
	$sw=1;
	$nombre = $_POST['nombre'];
	$desc = $_POST['desc'];

	//Las siguientes líneas de código se utilizan para comprobar si el pedido a dar de alta ya existe.
    $query_buscar="SELECT nombre_tipo_sala FROM tipo_sala;";
    $result=mysqli_query($enlace,$query_buscar);
    while( ( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ) && ($sw==1) ){
        if(strcasecmp($row['nombre_tipo_sala'],$nombre)==0) {
            $sw=0;
        }
    }
	
    if($sw==1){
    	$query="INSERT INTO `tipo_sala` (`nombre_tipo_sala`,`descripcion_tipo_sala`) VALUES ('$nombre','$desc');";
    	mysqli_query($enlace,$query) 
                or die (header("Location:void.php?comprobar=error"));
        header("Location:void.php?comprobar=correcto");
    }else{
		header("Location:void.php?comprobar=duplicado");

	}

    mysqli_close($enlace);
?>