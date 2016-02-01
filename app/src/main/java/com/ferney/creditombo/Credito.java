package com.ferney.creditombo;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by ferney on 01/28/2016.
 */
public class Credito {
    int valor, interes, cuotas;
    String cedula, numero;
    String fecha;


    public Credito(int valor, int interes, int cuotas, String numero, String fecha, String cedula){
        this.valor = valor;
        this.interes = interes;
        this.cuotas = cuotas;
        this.fecha = fecha;
        this.cedula = cedula;
        this.numero = numero;
    }
}
