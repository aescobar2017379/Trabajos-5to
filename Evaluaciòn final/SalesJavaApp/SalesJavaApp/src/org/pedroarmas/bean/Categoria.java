package org.pedroarmas.bean;


public class Categoria {
    private int codigoUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String usuarioLogin;
    private String contrasena;
    
    
    public Categoria(int codigoUsuario, String nombreUsuario, String apellidoUsuario, String usuarioLogin, String contrasena) {
        this.codigoUsuario = codigoUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.usuarioLogin = usuarioLogin;
        this.contrasena = contrasena;
       
    }

    public Categoria(int aInt, String string, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    public int getCodigoUsuario (){
        return codigoUsuario;
    }
    
    public void setCodigoUsuario (){
        this.codigoUsuario = codigoUsuario;
    }
    
    public String getNombreUsuario (){
        return nombreUsuario;
    }
    
    public void setNombreUsuario (){
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getApellidoUsuario (){
        return apellidoUsuario;
    }
    
    public void setApellidoUsuario (){
        this.apellidoUsuario = apellidoUsuario;
    }
    
    public String getUsuarioLogin (){
        return usuarioLogin;
    }
    
    public void setUsuarioLogin (){
        this.usuarioLogin = usuarioLogin;
    }
    
    public String getContrasena (){
        return contrasena;
    }
    
    public void setContrasena (){
        this.contrasena = contrasena;
    }

    public int getCodigoCategoria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
