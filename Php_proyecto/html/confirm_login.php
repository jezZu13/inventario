<?php
    require('conexion.php');
    //RECOGIDA DE DATOS
    if (isset($_POST['login_u'])) {
        if (empty($_POST['login_u'])) {
            header("Location:index.php?acceso=1");
        }
        $usuario=$_POST['login_u'];
    }
    if (isset($_POST['login_p'])) {
        if (empty($_POST['login_p'])) {
            header("Location:index.php?acceso=1");
        }
        $password=$_POST['login_p'];
    }
    if (isset($_POST['login_r'])) {
        // DEBUG --> echo $_POST['login_r'];
        if (empty($_POST['login_r'])) {
           header("Location:index.php?acceso=1");
        }
        $rol=$_POST['login_r'];
    }
    //CONSULTA EXISTENCIA USUARIO+ROL
    $sql="SELECT * FROM `usuarios_app` where `nombre_usuario`='$usuario' AND `rol_usuario`='$rol';";
    $query=mysqli_query($enlace,$sql);
   $result=mysqli_num_rows($query);
   if($result==0){
        header("Location:index.php?acceso=1");
    }else{ //EXISTE USUARIO CON ROL--> VERIFICAR PASSWORD
        $reg=mysqli_fetch_assoc($query);

        if(strcmp($reg['pass_usuario'],$password)==0){
            //redirigir a la página de inicio
            session_start();
            $_SESSION['usuario']=$_REQUEST['login_u'];
            $_SESSION['clave']=$_REQUEST['login_p'];
            $_SESSION['autenticado']="si";
            header("Location:inicio.php?p=inicio");
        }else{
            //FALLO PASSWORD
            header("Location:index.php?acceso=1");
        }
      
    }
    

?>