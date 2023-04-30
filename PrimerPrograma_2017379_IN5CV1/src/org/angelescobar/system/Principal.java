/*
    Nombre: Angel Gabriel Escobar Arevalo
    Codigo Técnico: IN5CV
    Carné: 2017379
    fecha de creación: 18/03/2022
    fecha de modificación:
 */
package org.angelescobar.system;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author angel
 */
public class Principal {
    public static void main(String[] args) {
        System.out.println("Angel Escobar");
//        JOptionPane.showMessageDialog(null, "Angel Escobar");
        Scanner sc = new Scanner(System.in); // constructor nulo 
        // tipo de dato id
        int num1, num2;  
//        num1 = 3;
//        num2 = 5;
//        result = num1 + num2;
//        //System.out.println("La suma es" + String.valueOf(result));
//        // casteo es convertir un tipo de dato a otro
//        System.out.println("ingrese el primer número"); //sout tab
//        num1 = sc.nextInt();
//        System.out.println(num1);
//        System.out.println("INgrese el segundo número");
//        num2 = sc.nextInt();
//        result = num1 + num2;
//        System.out.println("La suma es"+ String.valueOf(result));

        num1 = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese un número"));
        num2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el segundo número"));
        JOptionPane.showMessageDialog(null, "LA suma es: "+ String.valueOf(num1+num2));
    
    }
    
} 
