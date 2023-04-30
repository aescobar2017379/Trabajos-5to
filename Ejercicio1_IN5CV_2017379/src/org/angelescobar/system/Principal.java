/*
    Nombre: Angel Gabriel Escobar Arevalo
    Codigo Técnico: IN5CV
    Carné: 2017379
    fecha de creación: 22/03/2022
    fecha de modificación:
 */
package org.angelescobar.system;

/**
 *
 * @author angel
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int a, b, temp;
        a = 2;
        b = 3;
        System.out.println(a + "" + b);
        temp = a;
        a = temp;
        b= temp;
        System.out.println(a+""+b);
    }
    
}