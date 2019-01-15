<?php

session_start();

if($_SESSION['autenticado']!="si"){
    header("Location: index.php");
    exit();
}

?>

<script>

function logOut(){
  document.location="cerrar_sesion.php";
}

</script>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/dataTables.bootstrap4.min.css">
<link href="css/mdb.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/estilos.css">
<link rel="shortcut icon" href="favicon.ico">

</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">

  <button class="navbar-toggler" data-toggle="collapse" data-target="#collapse_target">
    <span class="navbar-toggler-icon"></span>
  </button>
<div class="collapse navbar-collapse" id="collapse_target">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="inicio.php">Inicio</a>
        </li>
        <li class="nav-item dropdown">
          <span class="nav-link dropdown-toggle boton-nav" data-toggle="dropdown" data-target="dropdown_target" href="">
            Alta
            <span class="caret"></span>
          </span>
          <div class="dropdown-menu" aria-labelledby="dropdown_target">
            <a class="dropdown-item" href="#" onclick="abrirDisp();">Dispositivo</a>
            <a class="dropdown-item" href="#" onclick="abrirEdificio();">Edificio</a>
            <a class="dropdown-item" href="#" onclick="abrirEstados();">Estado</a>
            <a class="dropdown-item" href="#" onclick="abrirInc();">Incidencia</a>
            <a class="dropdown-item" href="#" onclick="abrirAM();">Marca</a>
            <a class="dropdown-item" href="#" onclick="abrirOrigen();">Origen</a>
            <a class="dropdown-item" href="#" onclick="abrirPedidos();">Pedido</a>
            <a class="dropdown-item" href="#" onclick="abrirP();">Producto</a>
            <a class="dropdown-item" href="#" onclick="abrirProv();">Proveedor</a>
            <a class="dropdown-item" href="#" onclick="abrirSala();">Sala</a>
            <a class="dropdown-item" href="#" onclick="abrirFamiliaProducto();">Tipo/familia de producto</a>
            <a class="dropdown-item" href="#" onclick="abrirTipoSala();">Tipo Sala</a> 
            <a class="dropdown-item" href="#" onclick="abrirUsuariosAPP();">Usuarios APP</a>
          </div>
        </li>
        <li class="nav-item dropdown">
          <span class="nav-link dropdown-toggle boton-nav" data-toggle="dropdown" data-target="dropdown_target" href="">
            Mantenimiento
            <span class="caret"></span>
          </span>
          <div class="dropdown-menu" aria-labelledby="dropdown_target">
            <a class="dropdown-item" href="mantenimiento_edificio.php">Edificios</a>
            <a class="dropdown-item" href="mantenimiento_estado.php">Estados</a>
            <a class="dropdown-item" href="mantenimiento_incidencias.php">Incidencias</a>
            <a class="dropdown-item" href="mantenimiento_marca.php">Marcas</a>
            <a class="dropdown-item" href="mantenimiento_origen.php">Orígenes</a>
            <a class="dropdown-item" href="mantenimiento_pedido.php">Pedidos</a>
            <a class="dropdown-item" href="mantenimiento_producto.php">Productos</a>
            <a class="dropdown-item" href="mantenimiento_proveedor.php">Proveedores</a>
            <a class="dropdown-item" href="mantenimiento_sala.php">Salas</a>
            <a class="dropdown-item" href="mantenimiento_familia_producto.php">Tipos/familias de productos</a>
            <a class="dropdown-item" href="mantenimiento_tipo_sala.php">Tipos Salas</a>
            <a class="dropdown-item" href="mantenimiento_usuarios_APP.php">Usuarios APP</a>
          </div>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="mantenimiento_dispositivo.php">Listado dispositivos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="informes.php">Informes</a>
        </li>
        <li class="nav-item dropdown">
          <span class="nav-link dropdown-toggle boton-nav" data-toggle="dropdown" data-target="dropdown_target" href="">
            Histórico
            <span class="caret"></span>
          </span>
          <div class="dropdown-menu" aria-labelledby="dropdown_target">
            <a class="dropdown-item" href="historico_ubicacion_dispositivo.php">Ubicación de dispositivos</a>
            <a class="dropdown-item" href="historico_estado_dispositivo.php">Estado de dispositivos</a>
          </div>
        </li>
      </ul>
      <div class="mx-auto">
          <a class="navbar-brand" href="inicio.php"><img src="img/titulo.png" alt="Titulo" class="img_titulo"></a>
      </div>
      <ul class="nav navbar-nav ml-auto">
        <li class="nav-item">
         <a class="nav-link mr-4" href="mantenimiento_usuarios_APP.php"><i class="mr-1 fa fa-user iconos_nav"></i> <?php echo $_SESSION['usuario'] ; ?></a>

        </li>
        <li class="nav-item">
          <a class="nav-link" href="#"><i onclick="logOut();" class="fa fa-sign-out iconos_nav"></i></a>
        </li>
      </ul>
    </nav>
</div>

  <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.min.js"></script>
  <script src="js/jquery.dataTables.min.js"></script>
  <script src="js/dataTables.bootstrap4.min.js"></script>
  <script type="text/javascript" src="js/materialize.min.js"></script>
  <script type="text/javascript" src="js/mdb.min.js"></script>
  <script src="js/scripts.js"></script>
  <script src="js/funciones.js"></script>
  <script type="text/javascript">
    $(document).ready(function() {
        $('#example').DataTable();
    } );
  </script>
  <script>
      document.addEventListener('DOMContentLoaded', function() {
      var elems = document.querySelectorAll('.modal');
      var options = document.querySelectorAll('option');
      var instances = M.Modal.init(elems, options);
      });
  </script>





