package calculadorabásica;
import java.util.*;
public class CalculadoraBásica {


    public static void main(String[] args) {
         Scanner obj=new Scanner (System.in);
         int val1,val2,resultado,respuesta;
         
         System.out.println("Ingresa el primer numero");
         val1=obj.nextInt();
         System.out.println("Ingresa el segundo numero");
         val2=obj.nextInt();
         System.out.println("Bienvenido");
         System.out.println("seleccione una opción");
         System.out.println("1_Suma 2._Resta 3._Multiplicación 4._Division");
         respuesta=obj.nextInt();
         
         switch(respuesta){
             case 1:
                 resultado=val1+val2;
                 System.out.println("La Suma es igual a: "+resultado);
              break;
             case 2:
                 resultado=val1-val2;
                 System.out.println("La Resta es igual a: "+resultado);
              break;
             case 3:
                 resultado=val1*val2;
                 System.out.println("La Multiplicación es igual a: "+resultado);
              break;
             case 4:
                 resultado=val1/val2;
                 System.out.println("La División es igual a: "+resultado);
              break;
         }
    }
    
}
