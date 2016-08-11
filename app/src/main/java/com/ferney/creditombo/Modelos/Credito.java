package com.ferney.creditombo.Modelos;

/**
 * Created by ferney on 01/28/2016.
 */
public class Credito {
    private int valor, interes, nroCuotas;
    private String cedulaCliente, idCredito, fecha;


    public Credito(int valor, int interes, int nroCuotas, String idCredito, String fecha, String cedulaCliente){
        this.valor = valor;
        this.interes = interes;
        this.nroCuotas = nroCuotas;
        this.idCredito = idCredito;
        this.fecha = fecha;
        this.cedulaCliente = cedulaCliente;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getInteres() {
        return interes;
    }

    public void setInteres(int interes) {
        this.interes = interes;
    }

    public int getCuotas() {
        return nroCuotas;
    }

    public void setCuotas(int cuotas) {
        this.nroCuotas = cuotas;
    }

    public String getCedula() {
        return cedulaCliente;
    }

    public void setCedula(String cedula) {
        this.cedulaCliente = cedula;
    }

    public String getNumero() {
        return idCredito;
    }

    public void setNumero(String numero) {
        this.idCredito = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
