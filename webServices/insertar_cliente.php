<?php 
/**
 * Insertar un nuevo cliente en la base de datos
 */

require 'Clientes.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    // Insertar cliente
    $retorno = Clientes::insert(
        $body['cedula'],
        $body['nombre'],
        $body['direccion'],
        $body['telefono'],
        $body['celular'],
        $body['otro'],
        $body['empresa']);

    if ($retorno) {
        // Código de éxito
        print json_encode(
            array(
                'estado' => '1',
                'mensaje' => 'Creación exitosa')
        );
    } else {
        // Código de falla
        print json_encode(
            array(
                'estado' => '2',
                'mensaje' => 'Creación fallida')
        );
    }
}
 ?>