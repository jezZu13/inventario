<?php

    session_start();

    if($_SESSION['autenticado']!="si"){
        header("Location: index.php");
        exit();
    }

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>

<?php

if(isset($_GET['comprobar'])){
    $comprobar=$_GET['comprobar'];
    
    if($comprobar=="correcto"){
?>
        <script>
            alert("Alta realizada satisfactoriamente");
        </script>
<?php
    }
    if($comprobar=="duplicado"){
?>
        <script>
            alert("Duplicado");
        </script>
<?php
    }
    if($comprobar=="error"){
?>
        <script>
            alert("Error.");
        </script>
<?php
    }
    if($comprobar=="cambio_correcto"){
?>
        <script>
            alert("Modificación realizada satisfactoriamente.");
        </script>
<?php
    }
    if($comprobar=="delete_correcto"){
?>
        <script>
            alert("El registro se ha eliminado satisfactoriamente.");
        </script>
<?php
    }
    if($comprobar=="cierre_correcto"){
?>
        <script>
            alert("El cierre se ha realizado satisfactoriamente.");
        </script>
<?php
    }
    if($comprobar=="nopermitir"){
?>
        <script>
            alert("No es posible realizar esta acción sobre este elemento puesto que es usado por el sitema.");
        </script>
<?php
    }
    if($comprobar=="correcto_sin_imagen"){
?>
        <script>
            alert("Alta realizada satisfactoriamente.\n\nNo obstante, le informamos que NO se ha cargado ninguna imagen.");
        </script>
<?php
    } 
    if($comprobar=="cambio_correcto_sin_imagen"){
?>
        <script>
            alert("Modificación realizada satisfactoriamente.\n\nNo obstante, le informamos que NO se ha modificado la imagen del producto en cuestión.");
        </script>
<?php
    }
}

?>

<script>
    this.close();
</script>
    
</body>
</html>