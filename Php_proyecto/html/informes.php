<?php 

include('header.php'); 

?>

<script>

  function generarReport(numInforme){
    document.location="generar_informe.php?numInforme="+numInforme;
  }


</script>



<h1 class="pb-3 pt-3 text-center">Informes</h1>

<table id="example" class="table table-striped table-hover table-secondary text-center">

  <tr>
    <th>Tipo</th>
    <th></th>

  </tr>

  <tr>
    <td>Por fecha - listado</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('1');"></i></a></td>
  </tr>
  <tr>
    <td>Por fecha y tipos - cantidades</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('2');"></i></a></td>
  </tr>
  <tr>
    <td>Por fecha, proveedor y producto - cantidades</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('3');"></i></a></td>
  </tr>
  <tr>
    <td>Por edificio - listado</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('4');"></i></a></td>
  </tr>
  <tr>
    <td>Por edificio y ubicación - listado</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('5');"></i></a></td>
  </tr>
  <tr>
    <td>Por edificio y tipo - cantidades</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('6');"></i></a></td>
  </tr>
  <tr>
    <td>Por edificio, tipos y proveedores - cantidades</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('7');"></i></a></td>
  </tr>
  <tr>
    <td>Por edificio, tipos, proveedores y fecha - cantidades</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('8');"></i></a></td>
  </tr>
  <tr>
    <td>Por edificio, ubicación, tipos - cantidades</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('9');"></i></a></td>
  </tr>
  <tr>
    <td>Por ubicación - listado</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('10');"></i></a></td>
  </tr>
  <tr>
    <td>Por ubicación y tipo - cantidades</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('11');"></i></a></td>
  </tr>
  <tr>
    <td>Por tipo - listado</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('12');"></i></a></td>
  </tr>
  <tr>
    <td>Por tipo - cantidades</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('13');"></i></a></td>
  </tr>
  <tr>
  <tr>
    <td>Por tipo y proveedor - cantidades</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('14');"></i></a></td>
  </tr>
  <tr>
    <td>Por tipo y estado - cantidades</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('15');"></i></a></td>
  </tr>
  <tr>
    <td>Por producto - listado</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('16');"></i></a></td>
  </tr>
  <tr>
    <td>Por producto - cantidades</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('17');"></i></a></td>
  </tr>
  <tr>
    <td>Por producto y proveedor - cantidades</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('18');"></i></a></td>
  </tr>
  <tr>
    <td>Por producto y estado - cantidades</td>
    <td><a href="#"><i class="fa fa-file-text-o iconos" title="generar informe" onclick="generarReport('19');"></i></a></td>
  </tr>

</table>




</body>
</html>

