<?php	
	require('conexion.php');
	
	$sw=1;
	$nombre_usuario = $_POST['nombre_usuario'];
    $pass_usuario = $_POST['pass_usuario'];
    $rol_usuario = $_POST['rol_usuario'];

	//Las siguientes líneas de código se utilizan para evitar duplicados
    $query_buscar="SELECT nombre_usuario FROM usuarios_app;";
    $result=mysqli_query($enlace,$query_buscar);
    while( ( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ) && ($sw==1) ){
        if(strcasecmp($row['nombre_usuario'],$nombre_usuario)==0) {
            $sw=0;
        }
    }
	
    if($sw==1){
    	$query="INSERT INTO `usuarios_app` (`nombre_usuario`,`pass_usuario`, `rol_usuario`) VALUES ('$nombre_usuario','$pass_usuario', '$rol_usuario');";
    	mysqli_query($enlace,$query) 
                or die (header("Location:void.php?comprobar=error"));
        header("Location:void.php?comprobar=correcto");
    }else{
		header("Location:void.php?comprobar=duplicado");
	}

    mysqli_close($enlace);
?>