function abrirInc(){
	window.open("alta_incidencias.1.php","AltaIncidencia","width=800px, height=800px");
}

function abrirAM(){
	window.open("marca.php","AltaMarca","width=800px, height=800px");	
}

function abrirP(){
	var ventana=window.open("producto.php","AltaProducto", "width=800px, height=800px");	
}

function abrirDisp(){
	var paginadisp=window.open("dispositivo.php","AltaDispositivo", "width=800px, height=800px");	
}

function abrirProv(){
	window.open("proveedor.php","AltaProveedor", "width=800px, height=800px");	
}

function abrirEstados(){
	window.open("estado.php","AltaEstado", "width=800px, height=800px");	
}

function abrirPedidos(){
	window.open("pedido.php","AltaPedido", "width=800px, height=800px");	
}

function abrirOrigen(){
	window.open("origen.php","AltaOrigen", "width=800px, height=800px");	
}

function abrirSala(){
	window.open("sala.php","AltaSala", "width=800px, height=800px");	
}

function abrirTipoSala(){
	window.open("tipo_sala.php","AltaTipoSala", "width=800px, height=800px");	
}

function abrirEdificio(){
	window.open("edificio.php","AltaEdificio", "width=800px, height=800px");	
}

function abrirFamiliaProducto(){
	window.open("familia_producto.php","AltaFamiliaProducto", "width=800px, height=800px");	
}

function abrirUsuariosAPP(){
	window.open("usuarios_APP.php","AltaUsuariosAPP", "width=800px, height=800px");	
}

// Material Select Initialization
$(document).ready(function() {
$('.mdb-select').material_select();
});

    document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('select');
    var options = document.querySelectorAll('option');
    var instances = M.FormSelect.init(elems, options);
    });
