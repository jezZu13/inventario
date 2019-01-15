<?php
    $q = intval($_GET['q']);

    $enlace = mysqli_connect("localhost", "root", "", "qzr440");
    if (!$enlace) {
        die('Could not connect: ' . mysqli_error($enlace));
    }
    
    mysqli_select_db($enlace,"qzr440");
    $query="SELECT id_prod, nombre_prod FROM producto WHERE id_familia_prod='".$q."';";
    $result = mysqli_query($enlace,$query);

    echo "<label class=\"dispositivo_form\" for=\"\">Producto:</label>";
    echo "<select class=\"form-control\" name=\"producto\" id=\"\">";

    while($row = mysqli_fetch_array($result)) {
        echo "<option value=\"" .$row['id_prod']. "\">" .$row['nombre_prod']. "</option> ";
    }
    echo "</select>";

    mysqli_close($enlace);    
?>