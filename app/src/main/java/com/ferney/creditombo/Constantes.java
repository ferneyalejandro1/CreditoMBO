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
     * Transición Home -> Formulario
     */
    public static final int CODIGO_NUEVO = 100;

    /**
     * Transición Formulario -> Detalle
     */
    public static final int CODIGO_GUARDAR = 101;

    /**
     * Transición Home -> Detalle
     */
    public static final int CODIGO_DETALLE  = 102;

    /**
     * Puerto que utilizas para la conexión.
     * Dejalo en blanco si no has configurado esta carácteristica.
     */
    private static final String PUERTO_HOST = ":8080";

    /**
     * Dirección IP de genymotion o AVD
     */
    private static final String IP = "10.0.3.2";
    /**
     * URLs del Web Service
     */
    public static final String GET_CLIENTES = "http://" + IP + PUERTO_HOST + "/creditoMBO/obtener_clientes.php";
    public static final String GET_CLIENTE_BY_ID = "http://" + IP + PUERTO_HOST + "/creditoMBO/obtener_detalle.php";
    public static final String GET_CLIENTE_BY_NAME = "http://" + IP + PUERTO_HOST + "/creditoMBO/obtener_cliente_por_nombre.php";
    public static final String UPDATE_CLIENTE = "http://" + IP + PUERTO_HOST + "/creditoMBO/actualizar_cliente.php";
    public static final String DELETE_CLIENTE = "http://" + IP + PUERTO_HOST + "/creditoMBO/eliminar_cliente.php";
    public static final String INSERT_CLIENTE = "http://" + IP + PUERTO_HOST + "/creditoMBO/insertar_cliente.php";

    public static final String GET_CREDITOS = "http://" + IP + PUERTO_HOST + "/creditoMBO/obtener_creditos.php";
    public static final String GET_CREDITO_BY_ID = "http://" + IP + PUERTO_HOST + "/creditoMBO/obtener_detalle_credito.php";
    public static final String UPDATE_CREDITO = "http://" + IP + PUERTO_HOST + "/creditoMBO/actualizar_credito.php";
    public static final String DELETE_CREDITO = "http://" + IP + PUERTO_HOST + "/creditoMBO/eliminar_credito.php";
    public static final String INSERT_CREDITO = "http://" + IP + PUERTO_HOST + "/creditoMBO/insertar_credito.php";

    public static final String GET_CUOTAS = "http://" + IP + PUERTO_HOST + "/creditoMBO/obtener_cuotas.php";
    public static final String GET_CUOTAS_BY_CLIENTE = "http://" + IP + PUERTO_HOST + "/creditoMBO/obtener_cuotas_por_cliente.php";
    public static final String UPDATE_CUOTA = "http://" + IP + PUERTO_HOST + "/creditoMBO/actualizar_cuota.php";
    public static final String DELETE_CUOTA = "http://" + IP + PUERTO_HOST + "/creditoMBO/eliminar_cuota.php";
    public static final String INSERT_CUOTA = "http://" + IP + PUERTO_HOST + "/creditoMBO/insertar_cuota.php";

    /**
     * Clave para el valor extra que representa al identificador de un cliente
     */
    public static final String EXTRA_ID = "IDEXTRA";

}
