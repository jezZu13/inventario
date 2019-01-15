<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Proyecto - Invetario</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="css/materialize.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body class="login">
    <div class="container-login center_align card px-4 py-4">
        <div class="text-center">
            <img src="img/logo_login.png" alt="Logotipo" class="img_logo text-center">    
        </div>
        
        <form action="confirm_login.php" method="POST">
            <div class="input-field">
                <label for="" class="dLogin">Usuario</label>
                <input type="text" name="login_u" required>
            </div>
            <div class="input-field">
                <label for="">Contraseña</label>
                <input type="password" name="login_p" required>
            </div>
            <div class="pb-3">
                <label for="">Rol</label>
                <select name="login_r" required>
                    <option value="administrador">Administrador</option>
                    <option value="basico">Usuario</option>
                </select>
            </div>
            <div class="text-center">
                <button type="submit" value="Ingresar" class="btn boton waves-effect waves-light">Confirmar</button>
            </div>
        </form>
        <?php
    
        if(isset($_GET['acceso'])){
            $usuario=$_GET['acceso'];
            if($usuario==1){
                echo "</br><div class='text-center'><span class='boton_error btn-sm'>Datos Erróneos</span></div>";
            }else{
                echo "</br><div class='text-center'><span class='boton_error btn-sm'>Datos Erróneos</span></div>";
            }
        }
    
    ?>
    </div>
    
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/popper.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
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


