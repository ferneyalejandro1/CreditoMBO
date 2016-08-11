<?php
/**
 * Insertar una nueva cuota en la base de datos
 */

require 'Cuota.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    // Insertar credito
    $retorno = Cuota::insert(
        $body['idCuota'],//6 ff
        $body['numero'],//3
        $body['fecha'],//5
        $body['valor'],//4
        $body['idCredito'],
        $body['pendiente']);
        

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