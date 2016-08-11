<?php 
/**
 * Representa el la estructura de los creditos
 * almacenados en la base de datos
 */
require 'Database.php';

class Credito
{
	function __construct()
	{
	}

	/**
     * Retorna en la fila especificada de la tabla 'prestamos'
     *
     * @param $idCredito Identificador del registro
     * @return array Datos del registro
     */
	public static function getAll(){
		$consulta = "SELECT * FROM prestamos";
		try {
			//preparar sentencia
			$comando = Database::getInstance()->getDb()->prepare($consulta);
			//ejecutar sentencia preparada
			$comando->execute();

			return $comando->fetchAll(PDO::FETCH_ASSOC);
		} catch (PDOException $e) {
			return false;
		}
	}

	/**
     * Obtiene los campos de una meta con un identificador
     * determinado
     *
     * @param $idMeta Identificador de la meta
     * @return mixed
     */
	public static function getById($cedulaCliente){
		//consulta del credito
		$consulta = "SELECT nroPrestamo,
							fecha,
							monto,
							interes,
							nroCuotas
							FROM prestamos
							WHERE cedulaCliente = ?";
		try {
			//preparar la sentencia
			$comando = Database::getInstance()->getDb()->prepare($consulta);
			//ejecutar la sentencia preparada
			$comando->execute(array($cedulaCliente));
			//capturar primera fila del resultado
			$row = $comando->fetch(PDO::FETCH_ASSOC);
			return $row;

		} catch (PDOException $e) {
			return -1;
		}
	}

	/**
     * Actualiza un registro de la bases de datos basado
     * en los nuevos valores relacionados con un identificador
     *
     * @param $cedulaCliente      	identificador
     * @param $fecha      			nuevo fecha
     * @param $interes 				nueva interes
     * @param $monto    			nueva monto
     * @param $nroCuotas   			nueva nroCuotas
     */
	public static function update(
		$cedulaCliente,
		$fecha,
		$interes,
		$monto,
		$nroCuotas){
		//creando consulta UPDATE
		$consulta = "UPDATE prestamos SET fecha=?, interes=?, monto=?, nroCuotas=?";
		//preparar la sentencia
		$comando = Database::getInstance()->getDb()->prepare($consulta);
		//relacionar y ejecutar la sentencia
		$comando->execute(array($fecha, $interes, $monto, $nroCuotas, $cedulaCliente));

		return $comando;
	}

	/**
     * Insertar una nueva meta
     * @param $monto   				monto del nuevo credito
     * @param $interes    			interes del nuevo credito
     * @param $nroCuotas   			nroCuotas del nuevo credito
     * @param $nroPrestamo			nroPrestamo del nuevo prestamo
     * @param $fecha 				fecha del nuevo credito    
     * @param $cedulaCliente      	cedulaCliente del nuevo credito
     * @return PDOStatement
     */
	public static function insert(
		$valor,
		$interes,
		$nroCuotas,
		$idCredito,
		$fecha,
		$cedulaCliente){
		//sentencia INSERT
		$consulta = "INSERT INTO prestamos (valor, interes, nroCuotas, idCredito, fecha, cedulaCliente) VALUES(?,?,?,?,?,?)";
		//preparar la sentencia
		$comando = Database::getInstance()->getDb()->prepare($consulta);

		return $comando->execute(array( $valor,
										$interes,
										$nroCuotas,
										$idCredito,
										$fecha,
										$cedulaCliente));
	}

	/**
     * Eliminar el registro con el identificador especificado
     *
     * @param $nroPrestamo identificador del credito
     * @return bool Respuesta de la eliminación
     */
	public static function delete($nroPrestamo){
		//sentencia DELETE
		$consulta = "DELETE FROM prestamos WHERE nroPrestamo=?"
		//preparar la sentencia
		$comando = Database::getInstance()->getDb()->prepare($consulta);

		return $comando->execute(array($nroPrestamo));
	}
}
 ?>