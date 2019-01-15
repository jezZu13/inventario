<?php	
	require('conexion.php');
	
	$sw=1;
	$nombre = $_POST['nombre'];
	$desc = $_POST['desc'];

	//Las siguientes líneas de código se utilizan para comprobar si el pedido a dar de alta ya existe.
    $query_buscar="SELECT nombre_edif FROM edificio;";
    $result=mysqli_query($enlace,$query_buscar);
    while( ( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ) && ($sw==1) ){
        if(strcasecmp($row['nombre_edif'],$nombre)==0) {
            $sw=0;
        }
    }
	
    if($sw==1){
    	$query="INSERT INTO `edificio` (`nombre_edif`,`descripcion_edif`) VALUES ('$nombre','$desc');";
    	mysqli_query($enlace,$query) 
                or die (header("Location:void.php?comprobar=error"));
        header("Location:void.php?comprobar=correcto");
    }else{
		header("Location:void.php?comprobar=duplicado");

	}

    mysqli_close($enlace);
?>