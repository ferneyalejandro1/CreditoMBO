<?php
/**
 * Insertar un nuevo credito en la base de datos
 */

require 'Credito.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    // Insertar credito
    $retorno = Credito::insert(
        $body['valor'],//4
        $body['interes'],//2
        $body['nroCuotas'],//3
        $body['idCredito'],
        $body['fecha'],//5
        $body['cedulaCliente']);//6 ff

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
} ?>