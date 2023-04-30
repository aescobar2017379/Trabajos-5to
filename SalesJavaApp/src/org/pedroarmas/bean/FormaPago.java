package org.pedroarmas.bean;

public class FormaPago {
    private int codigoFormaPago;
    private String nombrePago;
    private String otrosDetalles;

    public FormaPago() {
    }

    public FormaPago(int codigoFormaPago, String nombrePago, String otrosDetalles) {
        this.codigoFormaPago = codigoFormaPago;
        this.nombrePago = nombrePago;
        this.otrosDetalles = otrosDetalles;
    }

    public int getCodigoFormaPago() {
        return codigoFormaPago;
    }

    public void setCodigoFormaPago(int codigoFormaPago) {
        this.codigoFormaPago = codigoFormaPago;
    }

    public String getNombrePago() {
        return nombrePago;
    }

    public void setNombrePago(String nombrePago) {
        this.nombrePago = nombrePago;
    }

    public String getOtrosDetalles() {
        return otrosDetalles;
    }

    public void setOtrosDetalles(String otrosDetalles) {
        this.otrosDetalles = otrosDetalles;
    }
    
    public String toString(){
        return getCodigoFormaPago() + " | " + getNombrePago();
    }
    
    
}
