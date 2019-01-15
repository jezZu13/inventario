<?php	
	require('conexion.php');
	
	$sw=1;
	$nombre_sala = $_POST['nombre_sala'];
    $usuario_sala = $_POST['usuario_sala'];
    $curso_asignado_sala = $_POST['curso_asignado_sala'];
	$descripcion_sala = $_POST['descripcion_sala'];
    $edif = $_POST['edif'];
    $tipo_sala = $_POST['tipo'];

	//Las siguientes líneas de código se utilizan para comprobar si el pedido a dar de alta ya existe.
    $query_buscar="SELECT nombre_sala, id_edif, id_tipo_sala FROM sala;";
    $result=mysqli_query($enlace,$query_buscar);
    while( ( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ) && ($sw==1) ){
        if( (strcasecmp($row['nombre_sala'],$nombre_sala)==0)&&(strcmp($row['id_tipo_sala'],$tipo_sala)==0)&&(strcmp($row['id_edif'],$edif)==0) ) {
            $sw=0;
        }
    }
	
    if($sw==1){
    	$query="INSERT INTO `sala` (`nombre_sala`,`usuario_sala`,`curso_asignado_sala`,`descripcion_sala`, `id_edif`, `id_tipo_sala`) VALUES ('$nombre_sala','$usuario_sala','$curso_asignado_sala','$descripcion_sala', '$edif', '$tipo_sala');";
    	mysqli_query($enlace,$query) 
                or die (header("Location:void.php?comprobar=error"));
        header("Location:void.php?comprobar=correcto");
    }else{
		header("Location:void.php?comprobar=duplicado");

	}

    mysqli_close($enlace);
?>

