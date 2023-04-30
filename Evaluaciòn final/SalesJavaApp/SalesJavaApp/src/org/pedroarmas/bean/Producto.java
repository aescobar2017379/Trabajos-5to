package org.pedroarmas.bean;

public class Producto {
    private int codigoProducto;
    private String nombreProducto;
    private double precioProducto;
    private int stock;
    private int codigoCategoria;

    public Producto() {
    }

    public Producto(int codigoProducto, String nombreProducto, double precioProducto, int stock, int codigoCategoria) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.stock = stock;
        this.codigoCategoria = codigoCategoria;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(int codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }
    
    public String toString(){
        return getCodigoProducto() + " | " + getNombreProducto() + " | " + getStock();
    }
    
    
    

    
    
    
    
    
}
