package com.ferney.creditombo;

/**
 * Created by ferney on 01/28/2016.
 */
public class Cliente {
    String nombre, cedula, direccion, telefono, celular, otro, empresa;

    public Cliente(String nombre, String cedula, String direccion, String telefono, String celular, String otro, String empresa){
        this.nombre = nombre;
        this.cedula = cedula;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.otro = otro;
        this.empresa = empresa;
    }

    public String getCedula(String nombre){
        return cedula;
    }
}
