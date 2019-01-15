<?php

    //Archivo de conexión a la base de datos
    require("conexion.php");

 
        $idproducto=$_POST['valorBusqueda'];

        $query="SELECT `id_disp`,`sn_disp` FROM `dispositivo` WHERE `id_producto`='$idproducto';";
        
        $result = mysqli_query($enlace,$query) or die($query);

        echo "<label> Dispositivo: </label>";

        echo "<select class='form-control' name='dispositivos' id='dispositivos' required>";

        //echo '<option value="0">Seleccionar</option>';
        while($fila = mysqli_fetch_assoc($result)) {
            if($fila["sn_disp"]!=''){
                echo "<option value='".$fila['id_disp']."'>".$fila["sn_disp"]."</option>";
            }
        }

        echo "</select>";

        $fin="";
        echo $fin;
   
?>