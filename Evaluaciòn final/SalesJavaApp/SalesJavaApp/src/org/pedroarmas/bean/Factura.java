package org.pedroarmas.bean;

import java.util.Date;


public class Factura {
    private int numeroFactura;
    private Date fechaFactura;
    private int codigoCliente;
    private int codigoFormaPago;

    public Factura() {
    }

    public Factura(int numeroFactura, Date fechaFactura, int codigoCliente, int codigoFormaPago) {
        this.numeroFactura = numeroFactura;
        this.fechaFactura = fechaFactura;
        this.codigoCliente = codigoCliente;
        this.codigoFormaPago = codigoFormaPago;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoFormaPago() {
        return codigoFormaPago;
    }

    public void setCodigoFormaPago(int codigoFormaPago) {
        this.codigoFormaPago = codigoFormaPago;
    }
    
    public String toString(){
        return getNumeroFactura() + " | " + getFechaFactura();
    }
    
    
    
    
    
}
