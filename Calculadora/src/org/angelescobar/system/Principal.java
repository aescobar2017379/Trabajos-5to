/*
nombre: Angel Gabriel Escobar Arevalo
codigo T�cnico; IN5CV
carn�: 2017379
fecha de creaci�n: 24/03/2022
fecha de modificaci�n:  
 */
package org.angelescobar.system;

import javax.swing.JOptionPane;
import org.angelescobar.bean.Division;
import org.angelescobar.bean.Multiplicacion;
import org.angelescobar.bean.Resta;
import org.angelescobar.bean.Suma;


/**
 *
 * @author angel
 */
public class Principal {

    private static int opcion;

        
    public static void main(String[] args) {
        
        byte option=0;
        
        do {
           option=Byte.parseByte(JOptionPane.showInputDialog(
           "MENU PRINCIPAL \n"
         +  "1. Suma\n"
         + "2. Resta\n"
         + "3. Multiplicación\n"
         + "4. División\n"
         + "5. Salir\n"
         + "Elija su opcion"));
            int opcion = 0;
          
            
        
            switch(opcion){
                case 1:
                   JOptionPane.showMessageDialog(null, "Suma");
                   Suma suma = new Suma(0,0);
          suma.setNum1(Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese un valor")));
          suma.setNum2(Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el segundo valor")));
          JOptionPane.showMessageDialog(null, "La suma es:"+ String.valueOf(suma.sumatoria(suma.getNum1() ,suma.getNum2())));
                    break;
                   
                case 2:
                    JOptionPane.showMessageDialog(null, "Resta");
                     Resta resta = new Resta(0,0);
          resta.setNum1(Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese un valor")));
          resta.setNum2(Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el segundo valor")));
          JOptionPane.showMessageDialog(null, "La resta es:"+ String.valueOf(resta.residuo(resta.getNum1() ,resta.getNum2())));
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Multiplicación");
                    Multiplicacion multiplicacion = new Multiplicacion(0,0);
          multiplicacion.setNum1(Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese un valor")));
          multiplicacion.setNum2(Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el segundo valor")));
          JOptionPane.showMessageDialog(null, "La multiplicacion es:"+ String.valueOf(multiplicacion.Producto(multiplicacion.getNum1() ,multiplicacion.getNum2())));
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "División");
                    Division division = new Division(0,0);
          division.setNum1(Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese un valor")));
          division.setNum2(Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el segundo valor")));
          JOptionPane.showMessageDialog(null, "La division es:"+ String.valueOf(division.cociente(division.getNum1() ,division.getNum2())));
                    break;
                case 5:
                    opcion=5;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "OPcion invalida");
                    break;
           
                }
            
                }while (opcion!=5);
                System.exit(0);
                
                    
          
         
          
           
          

    }
}
    
    
