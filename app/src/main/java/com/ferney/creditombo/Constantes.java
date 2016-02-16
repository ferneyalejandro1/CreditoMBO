package com.ferney.creditombo;

/**
 * Created by ferney on 02/13/2016.
 */
/**
 * Clase que contiene los códigos usados en "I Wish" para
 * mantener la integridad en las interacciones entre actividades
 * y fragmentos
 */
public class Constantes {
    /**
     * Transición Home -> Detalle
     */
    public static final int CODIGO_DETALLE = 100;

    /**
     * Transición Detalle -> Actualización
     */
    public static final int CODIGO_ACTUALIZACION = 101;

    /**
     * Puerto que utilizas para la conexión.
     * Dejalo en blanco si no has configurado esta carácteristica.
     */
    private static final String PUERTO_HOST = "";

    /**
     * Dirección IP de genymotion o AVD
     */
    private static final String IP = "";
    /**
     * URLs del Web Service
     */
    public static final String GET = IP + PUERTO_HOST + "/webServices/obtener_clientes.php";
    public static final String GET_BY_ID = IP + PUERTO_HOST + "/webServices/obtener_detalle.php";
    public static final String UPDATE = IP + PUERTO_HOST + "/webServices/actualizar_cliente.php";
    public static final String DELETE = IP + PUERTO_HOST + "/webServices/eliminar_cliente.php";
    public static final String INSERT = IP + PUERTO_HOST + "/webServices/insertar_cliente.php";

    /**
     * Clave para el valor extra que representa al identificador de un cliente
     */
    public static final String EXTRA_ID = "IDEXTRA";

}
