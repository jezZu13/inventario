<?php

    //Archivo de conexión a la base de datos
    require("conexion.php");

    //Variable de búsqueda
    $id_prod = $_POST['valorBusqueda'];

    //Realizamos la query
    $query="SELECT id_prod, foto_prod FROM producto WHERE id_prod='$id_prod';";

    $result=mysqli_query($enlace,$query) 
        or die("No se han podido cargar los productos");
    while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
        $foto=$row['foto_prod'];
    }
    //echo "hola";

    echo "<img src='".$foto."' alt='' class='img_modal'>";
    $fin="";
    echo $fin;
?>

