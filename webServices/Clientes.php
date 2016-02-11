<?php 
/**
 * Representa el la estructura de las metas
 * almacenadas en la base de datos
 */
require 'Database.php';

class Cliente
{
	
	function __construct()
	{
	}

	/**
     * Retorna en la fila especificada de la tabla 'clientes'
     *
     * @param $cedula Identificador del registro
     * @return array Datos del registro
     */
    public static function getAll()
    {
        $consulta = "SELECT * FROM Clientes";
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
     * Obtiene los campos de un cliente con un identificador
     * determinado
     *
     * @param $cedula Identificador del cliente
     * @return mixed
     */
    public static function getById($cedula)
    {
        // Consulta de la meta
        $consulta = "SELECT cedula,
                            nombre,
                             direccion,
                             telefono,
                             celular,
                             otro,
                             empresa,
                             FROM Clientes
                             WHERE cedula = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($cedula));
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
     * @param $cedula      identificador
     * @param $nombre      nuevo nombre
     * @param $direccion   nueva direccion
     * @param $telefono    nueva telefono
     * @param $celular     nueva celular
     * @param $otro        nueva otro telefono
     * @param $empresa     nueva empresa
     */
    public static function update(
        $cedula,
        $nombre,
        $direccion,
        $telefono,
        $celular,
        $otro
        $empresa
    )
    {
        // Creando consulta UPDATE
        $consulta = "UPDATE Clientes" .
            " SET nombre=?, direccion=?, telefono=?, celular=?, otro=?, empresa=? " .
            "WHERE cedula=?";

        // Preparar la sentencia
        $cmd = Database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($nombre, $direccion, $telefono, $celular, $otro, $empresa, $cedula));

        return $cmd;
    }

    /**
     * Insertar un nuevo cliente
     *
     * @param $nombre      	nombre del nuevo registro
     * @param $direccion 	direccion del nuevo registro
     * @param $telefono  	telefono del nuevo registro
     * @param $celular   	celular del nuevo registro
     * @param $otro   		otro telefono del nuevo registro
     * @param $empresa   		empresa del nuevo registro
     * @return PDOStatement
     */
    public static function insert(
    	$cedula
        $nombre,
        $direccion,
        $telefono,
        $celular,
        $otro
        $empresa
    )
    {
        // Sentencia INSERT
        $comando = "INSERT INTO Clientes ( " .
            "cedula," .
            "nombre," .
            " direccion," .
            " telefono," .
            " celular," .
            " otro, " .
            " empresa)" .
            " VALUES( ?,?,?,?,?,?,?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(
            array(
            	$cedula,
                $nombre,
                $direccion,
                $telefono,
                $celular,
                $otro,
                $empresa
            )
        );

    }

    /**
     * Eliminar el registro con el identificador especificado
     *
     * @param $cedula identificador del cliente
     * @return bool Respuesta de la eliminación
     */
    public static function delete($cedula)
    {
        // Sentencia DELETE
        $comando = "DELETE FROM Clientes WHERE cedula=?";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(array($cedula));
    }
}
?>