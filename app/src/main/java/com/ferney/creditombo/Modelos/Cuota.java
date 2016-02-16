package com.ferney.creditombo.Modelos;

import java.util.Date;

/**
 * Created by ferney on 01/28/2016.
 */
public class Cuota {
    private int  valor, numero;
    private String idCredito, idCuota, fecha;
    private Boolean pendiente;

    public Cuota(String id, int valor, int numero, String idCredito, String fecha, Boolean pendiente){
        this.idCuota = id;
        this.valor = valor;
        this.numero = numero;
        this.idCredito = idCredito;
        this.fecha = fecha;
        this.pendiente = pendiente;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(String idCredito) {
        this.idCredito = idCredito;
    }

    public String getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(String idCuota) {
        this.idCuota = idCuota;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Boolean getPendiente() {
        return pendiente;
    }

    public void setPendiente(Boolean pendiente) {
        this.pendiente = pendiente;
    }
}
