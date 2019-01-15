<?php

    //Archivo de conexión a la base de datos
    require("conexion.php");

 
    $id_familia=$_POST['valorBusqueda'];

    //$familia= strtolower($familia);
    
    /*
        $consulta="SELECT `id_familia_prod` from `familia_producto` where `nombre_familia_prod`='$familia';";
        $result1=mysqli_query($enlace,$consulta)or die($consulta);
        $id=mysqli_fetch_assoc($result1);
        $id=$id["id_familia_prod"];
    */

        $query="SELECT `id_prod`,`nombre_prod` FROM `producto` WHERE `id_familia_prod`='$id_familia';";
        
        $result = mysqli_query($enlace,$query) or die($id);

        echo "<label> Producto: </label>";
        echo "<select class='form-control' name='productos' id='productos' onchange='cargarDispositivo();' required>";
                echo "<option value=''>Seleccione un valor</option>";
            while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ) {
                echo "<option value='".$row['id_prod']."'>".$row['nombre_prod']."</option>";
            }
        
        echo "</select>";

        $fin="";
        echo $fin;
         
?>


