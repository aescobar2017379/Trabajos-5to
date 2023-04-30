
package com.angelescobar.sistema;

import com.angelescobar.dominio.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author angel
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBMaven");
       EntityManager em = emf.createEntityManager();
       Persona p = em.find(Persona.class, 2);
       System.out.println(p);
       em.close();
       emf.close();
        

    }
    
}
