<?php 
/**
 * Obtiene el detalle de un credito especificado por
 * su identificador "cedulaCliente"
 */

require 'Credito.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    if (isset($_GET['cedulaCliente'])) {

        // Obtener parámetro cedulaCliente
        $parametro = $_GET['cedulaCliente'];

        // Tratar retorno
        $retorno = Credito::getById($parametro);


        if ($retorno) {

            $credito["estado"] = "1";
            $credito["credito"] = $retorno;
            // Enviar objeto json de la credito
            print json_encode($credito);
        } else {
            // Enviar respuesta de error general
            print json_encode(
                array(
                    'estado' => '2',
                    'mensaje' => 'No se obtuvo el registro'
                )
            );
        }

    } else {
        // Enviar respuesta de error
        print json_encode(
            array(
                'estado' => '3',
                'mensaje' => 'Se necesita un identificador'
            )
        );
    }
}
 ?>