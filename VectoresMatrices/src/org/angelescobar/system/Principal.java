/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
      Scanner sc = new Scanner(System.in);
      int limite;
      System.out.println("Ingrese el limite de tablas");
      limite = sc.nextInt();
      for (int i=1; i<=limite; i++){
          System.out.println("");
          System.out.println("");
        for (int j=1; j<=10; j++)
            System.out.println(i +  " * " + j + " = " + i * j);    
    }
        
        
        
        
        
        
//        int [] v = new int [5];
//       int [] [] m = new int [3] [3];
//       
//       for(int i=0; i<3; i++){
//           System.out.println("");
//           for (int j=0; j<3; j++){
//               System.out.println("|"+i+ "," +j+ "| ");
//           }
//       }
//        System.out.println("");
    }
//    
}
