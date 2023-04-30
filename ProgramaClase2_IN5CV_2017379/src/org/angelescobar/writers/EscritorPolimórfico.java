package org.angelescobar.writers;

public class EscritorPolimórfico extends Escritor{
    
    public EscritorPolimórfico (){
        
    }
    public static void escribir (String texto){
        Escritor.escribir (texto.toUpperCase ());
    }
}
