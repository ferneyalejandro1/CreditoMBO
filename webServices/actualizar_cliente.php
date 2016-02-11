<?php 
/**
 * Actualiza un cliente especificado por su identificador
 */

require 'Clientes.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    // Actualizar cliente
    $retorno = Clientes::update(
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
                'mensaje' => 'Actualización exitosa')
        );
    } else {
        // Código de falla
        print json_encode(
            array(
                'estado' => '2',
                'mensaje' => 'Actualización fallida')
        );
    }
}
 ?>