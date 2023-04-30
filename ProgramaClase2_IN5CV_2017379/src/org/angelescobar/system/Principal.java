package org.angelescobar.system;

import org.angelescobar.writers.Escritor;
import org.angelescobar.writers.EscritorNuevo;
import org.angelescobar.writers.EscritorPolimórfico;

/**
 *
 * @author angel
 */
public class Principal {

    
    public static void main(String[] args) {
        Escritor  es = new Escritor ();
        EscritorNuevo es2 = new EscritorNuevo();
        EscritorPolimórfico es3 = new EscritorPolimórfico();
        
        es.escribir("El primer escritor");
        es2.escribir("El segundo escritor");
        es2.escribeConAsteriscos("Lees esto con Asteriscos");
        es3.escribir("El tercer Escritor");
        
    }
    
}
