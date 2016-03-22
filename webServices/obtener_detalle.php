<?php 
/**
 * Obtiene el detalle de un cliente especificado por
 * su identificador "cedula"
 */

require 'Clientes.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    if (isset($_GET['cedula'])) {

        // Obtener parámetro cedula
        $parametro = $_GET['cedula'];

        // Tratar retorno
        $retorno = Clientes::getById($parametro);


        if ($retorno) {

            $cliente["estado"] = "1";
            $cliente["cliente"] = $retorno;
            // Enviar objeto json de la cliente
            print json_encode($cliente);
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
                'mensaje' => 'Se necesita una cedula'
            )
        );
    }
}
 ?>