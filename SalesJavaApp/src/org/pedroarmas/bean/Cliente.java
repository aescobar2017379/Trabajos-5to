package org.pedroarmas.bean;

import java.util.Date;


public class Cliente {
    private int codigoCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String domicilioCliente;
    private Date fechaNacimiento;
    private int telefonoCliente;
    private String correoCliente;

    public Cliente() {
    }

    public Cliente(int codigoCliente, String nombreCliente, String apellidoCliente, String domicilioCliente, Date fechaNacimiento, int telefonoCliente, String correoCliente) {
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.domicilioCliente = domicilioCliente;
        this.fechaNacimiento = fechaNacimiento;
        this.telefonoCliente = telefonoCliente;
        this.correoCliente = correoCliente;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getDomicilioCliente() {
        return domicilioCliente;
    }

    public void setDomicilioCliente(String domicilioCliente) {
        this.domicilioCliente = domicilioCliente;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(int telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

//    @Override
//    public String toString() {
//        return "Cliente{" + "codigoCliente=" + codigoCliente + ", nombreCliente=" + nombreCliente + ", apellidoCliente=" + apellidoCliente + '}';
//    }
    
    public String toString(){
        return getCodigoCliente()+ " | " + getNombreCliente() + ", " + getApellidoCliente();
    }
    
    

    
    
    
}
