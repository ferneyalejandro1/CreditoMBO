package com.ferney.creditombo.Modelos;

/**
 * Created by ferney on 01/28/2016.
 */
public class Cuota {
    private int  valor, pendiente;
    private String idCredito, idCuota, fecha, numero;

    public Cuota(String idCuota, String numero, String fecha, int valor, String idCredito, int pendiente){
        this.idCuota = idCuota;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String  numero) {
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

    public int getPendiente() {
        return pendiente;
    }

    public void setPendiente(int pendiente) {
        this.pendiente = pendiente;
    }
}
