<?php

session_start();

unset($_SESSION['usuario']);
unset($_SESSION['clave']);
unset($_SESSION['autenticado']);

session_destroy();

header("Location: index.php");

exit;

?>