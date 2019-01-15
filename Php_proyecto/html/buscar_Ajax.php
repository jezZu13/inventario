<?php

    session_start();

    if($_SESSION['autenticado']!="si"){
        header("Location: index.php");
        exit();
    }

?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Proyecto - Inventario</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="css/materialize.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    

<?php

    //Archivo de conexión a la base de datos
    require("conexion.php");

    //Variable de búsqueda
    $id_familia_prod = $_POST['valorBusqueda'];
    $id_marca = $_POST['valorBusqueda2'];

    //Realizamos la query

    $query="SELECT id_prod, nombre_prod FROM producto WHERE id_familia_prod='$id_familia_prod' and id_marca='$id_marca';";

    $result=mysqli_query($enlace,$query) 
        or die("No se han podido cargar los productos");
    
    echo " <label for=''>Producto</label><i id='icon' class='fas fa-plus' onclick='openAltaProducto();'></i>";
    echo " <select class='form-control' name='producto' id='' required> ";
    while( $row=mysqli_fetch_array($result,MYSQLI_ASSOC) ){
        echo "<option value='".$row['id_prod']. "'>" .$row['nombre_prod']. "</option>";
    }
    echo " </select> ";
    
    //Devolvemos el mensaje para fin.
    $fin="";
    echo $fin;
?>

    <script src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
    <script src="js/scripts.js"></script>
    <script src="js/funciones.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
        var elems = document.querySelectorAll('select');
        var options = document.querySelectorAll('option');
        var instances = M.FormSelect.init(elems, options);
        });
    </script>

</body>
</html>

