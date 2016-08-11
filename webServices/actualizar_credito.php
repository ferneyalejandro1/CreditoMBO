<?php
/**
 * Actualiza un credito especificado por su identificador
 */

require 'Credito.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    // Actualizar meta
    $retorno = Credito::update(
        $body['cedulaCliente'],
        $body['fecha'],
        $body['interes'],
        $body['monto'],
        $body['nroCuotas']);

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