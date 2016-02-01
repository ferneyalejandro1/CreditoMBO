package com.ferney.creditombo;

import java.util.Date;

/**
 * Created by ferney on 01/28/2016.
 */
public class Cuota {
    int  valor, numero;
    String idCredito, idCuota, fecha;
    Boolean pendiente;

    public Cuota(String id, int valor, int numero, String idCredito, String fecha, Boolean pendiente){
        this.idCuota = id;
        this.valor = valor;
        this.numero = numero;
        this.idCredito = idCredito;
        this.fecha = fecha;
        this.pendiente = pendiente;
    }
}
