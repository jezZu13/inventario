<?php	
	require('conexion.php');
	
	$sw=1;
	$nombre_familia_prod = $_POST['nombre_familia_prod'];
    $descripcion_familia_prod = $_POST['descripcion_familia_prod'];

	//Las siguientes líneas de código se utilizan para evitar duplicados
    $query_buscar="SELECT nombre_familia_prod FROM familia_producto;";
    $result=mysqli_query($enlace,$query_buscar);
    while( ( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ) && ($sw==1) ){
        if(strcasecmp($row['nombre_familia_prod'],$nombre_familia_prod)==0) {
            $sw=0;
        }
    }
	
    if($sw==1){
    	$query="INSERT INTO `familia_producto` (`nombre_familia_prod`,`descripcion_familia_prod`) VALUES ('$nombre_familia_prod','$descripcion_familia_prod');";
    	mysqli_query($enlace,$query) 
                or die (header("Location:void.php?comprobar=error"));
        header("Location:void.php?comprobar=correcto");
    }else{
		header("Location:void.php?comprobar=duplicado");
	}

    mysqli_close($enlace);
?>