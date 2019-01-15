<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/mdb.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/estilos.css">

<?php

    //Archivo de conexión a la base de datos
    require("conexion.php");

    //Variable de búsqueda
    $numero_inc = $_POST['valorBusqueda'];
    $fecha_cierre = $_POST['valorBusqueda2'];

    //Realizamos la query
    $query="select incidencia.solucion_inc, descripcion_inc from incidencia where numero_inc='$numero_inc';";

    $result=mysqli_query($enlace,$query) 
        or die("No se han podido cargar los productos");

    while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
    
        echo "<table class='table table-striped table-sm table-hover table-secondary'>";
        echo "<thead class='encabezado_tabla'>";
            echo "<tr>";
                echo "<th> Descripción </th>";
            echo "</tr>";
        echo "</thead>";
            echo "<tr>";
                echo "<td>". $row['descripcion_inc'] ."</td>";
            echo "</tr>";
        echo "</table>";

        if(($fecha_cierre!="")||($fecha_cierre!=NULL)){
        echo "<table class='table table-striped table-sm table-hover table-secondary' style='margin-top:50px;'>";
        echo "<thead class='encabezado_tabla'>";
            echo "<tr>";
                echo "<th> Solución </th>";
            echo "</tr>";
        echo "</thead>";
            echo "<tr>";
                echo "<td>". $row['solucion_inc'] ."</td>";
            echo "</tr>";
        echo "</table>";
        }
    }

    mysqli_close($enlace);

    $fin="";
    echo $fin;
?>

