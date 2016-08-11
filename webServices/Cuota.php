<?php 
/**
 * Representa el la estructura de los clientes
 * almacenados en la base de datos
 */
require 'Database.php';

class Cuota
{
	
	function __construct()
	{
	}

	/**
     * Retorna en la fila especificada de la tabla 'cuotas'
     *
     * @param $idCuota Identificador del registro
     * @return array Datos del registro
     */
    public static function getAll()
    {
        $consulta = "SELECT * FROM cuotas";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }

     /**
     * Obtiene los campos de una cuota con un identificador
     * determinado
     *
     * @param $idCuota Identificador de la cuota
     * @return mixed
     */
    public static function getById($idCuota)
    {
        // Consulta de la meta
        $consulta = "SELECT nroPrestamo,
                            numero,
                            fecha,
                            valor,
                            pendiente
                            FROM cuotas
                            WHERE idCuota = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($idCuota));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }

     /**
     * Obtiene los campos fecha y valor de las cuotas de un prestamo
     * determinado
     *
     * @param $nroPrestamo credito deseado
     * @return mixed
     */
    public static function getByPrestamo($nroPrestamo)
    {
        // Consulta de la meta
        $consulta = "SELECT  fecha,
                             valor
                             FROM cuotas
                             WHERE nroPrestamo like '?'";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($nombre));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }

    /**
     * Actualiza un registro de la bases de datos basado
     * en los nuevos valores relacionados con un identificador
     *
     * @param $idCuota      identificador
     * @param $numero      	nuevo numero
     * @param $fecha 	   	nueva fecha
     * @param $valor 	   	nueva valor
     * @param $idCredito   	nueva idCredito
     * @param $pendiente    nueva pendiente
     */
    public static function update(
        $idCuota,
        $numero,
        $fecha,
        $valor,
        $idCredito,
        $pendiente
    )
    {
        // Creando consulta UPDATE
        $consulta = "UPDATE cuotas" .
            " SET idCuota=?, numero=?, fecha=?, valor=?, idCredito=?, pendiente=? " .
            "WHERE idCuota=?";

        // Preparar la sentencia
        $cmd = Database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($idCuota, $numero, $fecha, $valor, $idCredito, $pendiente));

        return $cmd;
    }

    /**
     * Insertar una nueva cuota
     *
     * @param $idCuota      identificador
     * @param $numero      	nuevo numero
     * @param $fecha 	   	nueva fecha
     * @param $valor 	   	nueva valor
     * @param $idCredito   	nueva idCredito
     * @param $pendiente    nueva pendiente
     * @return PDOStatement
     */
    public static function insert(
    	$idCuota,
        $numero,
        $fecha,
        $valor,
        $idCredito,
        $pendiente){
        // Sentencia INSERT
        $comando = "INSERT INTO cuotas ( idCuota, numero, fecha, valor, idCredito, pendiente) VALUES( ?,?,?,?,?,?)";
        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(
            array(
            	$idCuota,
                $numero,
                $fecha,
                $valor,
                $idCredito,
                $pendiente
            )
        );

    }

    /**
     * Eliminar el registro con el identificador especificado
     *
     * @param $idCuota identificador del cliente
     * @return bool Respuesta de la eliminación
     */
    public static function delete($idCuota)
    {
        // Sentencia DELETE
        $comando = "DELETE FROM cuotas WHERE idCuota = ?";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(array($cedula));
    }
}
 ?>