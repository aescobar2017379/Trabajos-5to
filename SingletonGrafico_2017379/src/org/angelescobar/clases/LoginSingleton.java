package org.angelescobar.clases;

import javax.swing.JFrame;
import org.angelescobar.system.Login;


public class LoginSingleton {
    
    private static JFrame log;
    private LoginSingleton(){
        
    }
     
    public static JFrame getinstance(){
        if(log == null)
            log = new Login();
        return log;
    }
}

