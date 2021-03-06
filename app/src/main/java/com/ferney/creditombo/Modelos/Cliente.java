package com.ferney.creditombo.Modelos;

/**
 * Created by ferney on 01/28/2016.
 */
public class Cliente {
    private String nombre, cedula, direccion, telefono, celular, otroTel, empresa;

    public Cliente(String cedula, String nombre, String direccion, String telefono, String celular, String otro, String empresa){
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.otroTel = otro;
        this.empresa = empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getOtro() {
        return otroTel;
    }

    public void setOtro(String otro) {
        this.otroTel = otro;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * Compara los atributos de dos clientes
     * @param cliente Cliente externo
     * @return true si son iguales, false si hay cambios
     */
    public Boolean compararCon(Cliente cliente){
        return this.nombre.compareTo(cliente.nombre) == 0 &&
                this.cedula.compareTo(cliente.cedula) == 0 &&
                this.direccion.compareTo(cliente.direccion) == 0 &&
                this.celular.compareTo(cliente.celular) == 0 &&
                this.telefono.compareTo(cliente.telefono) == 0 &&
                this.otroTel.compareTo(cliente.otroTel) == 0 &&
                this.empresa.compareTo(cliente.empresa) == 0;
    }
}
