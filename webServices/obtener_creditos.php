<?php 
/**
 * Obtiene todas los creditos de la base de datos
 */
require 'Credito.php';

if ($_SERVER['REQUEST_METHOD']=='GET') {
	//manejar peticion GET
	$creditos =  Credito::getAll();

	if ($creditos) {
		$datos["estado"] = 1;
		$datos["creditos"]= $creditos;
		print json_encode($datos);
	}else{
		print json_encode(array("estado" => 2, "mensaje" => "Ha ocurrido un error"));
	}
}
 ?>