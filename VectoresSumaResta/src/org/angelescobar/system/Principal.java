/*
Nombre: Angel Gabriel Escobar Arevalo 
Código Técnico: IN5CV
carné
fecha de creación: 31/03/2022
fecha de modificación: 06/04/2022
 */
package org.angelescobar.system;

import java.util.Scanner;

/**
 *
 * @author angel
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            
        int [] v = new int [5];
        v [0] = 3;
        v [1] = 7;
        v [2] = 9;
        v [3] = 0;
        v [4] = 4;
        
        System.out.println(v[0] + "" + v[1] + "" + v[2] + "" + v[3] + "" + v[4]);
        
        int mayor = v[0], sumatoria = 0;
        for(int cont = 0; cont<5; cont++) {
            if (v[cont]> mayor)
            mayor = v[cont];
            sumatoria = sumatoria + v[cont];
           
        }
        System.out.println("El mayor de todos es " + mayor);
        System.out.println("La suma de todos los números es " + sumatoria);
        
        // Ingresar valores al vector pidiendo al usuario 
        
        Scanner dato = new Scanner(System.in);
        for (int cont = 0; cont<5; cont++){
            System.out.println("Ingrese un número para vector en posición" + cont + "->");
            v[cont] = dato .nextInt();
            
        }
        for (int cont=0; cont<5; cont++){
        System.out.println("Valor en posición" + cont + "es:" + v[cont]);
    }
        
        System.out.println("");
        System.out.println("");
        
        for (int cont = 0; cont< v.length; cont++)
            System.out.println("Valores " + cont + "es:" + v[cont]);
        
        
        System.out.println("El Mayor de todos es " + mayor);
        System.out.println("La resta de todos los números es " + sumatoria);
        
        
        
        }
        
    }

    