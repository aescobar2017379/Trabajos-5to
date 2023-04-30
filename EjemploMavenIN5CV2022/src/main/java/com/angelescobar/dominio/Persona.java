
package com.angelescobar.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name="Persona")
public class Persona {
    @Id
    @Column (name="codigoPersona")
    private int codigoPersona;
    @Column 
    private String nombrePersona;
    @Column 
    private String apellidoPersona;

    public Persona() {
    }

    public Persona(int codigoPersona, String nombrePersona, String apellidoPersona) {
        this.codigoPersona = codigoPersona;
        this.nombrePersona = nombrePersona;
        this.apellidoPersona = apellidoPersona;
    }

    public int getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(int codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    @Override
    public String toString() {
        return "Persona{" + "codigoPersona=" + codigoPersona + ", nombrePersona=" + nombrePersona + ", apellidoPersona=" + apellidoPersona + '}';
    }
    
    
    
}
