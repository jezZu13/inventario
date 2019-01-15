
<?php
//$enlace = mysqli_connect("217.76.131.28", "qzr440", "jezZu1311", "qzr440");
$enlace = mysqli_connect("localhost", "root", "", "qzr440");
mysqli_set_charset($enlace, "utf8");

if (!$enlace) {
    echo "Error: No se pudo conectar a MySQL." . PHP_EOL;
    echo "errno de depuración: " . mysqli_connect_errno() . PHP_EOL;
    echo "error de depuración: " . mysqli_connect_error() . PHP_EOL;
    exit;
}


?>