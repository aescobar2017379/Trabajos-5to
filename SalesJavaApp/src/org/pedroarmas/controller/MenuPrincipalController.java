package org.pedroarmas.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.pedroarmas.system.Principal;

public class MenuPrincipalController implements Initializable {
    private Principal escenarioPrincipal;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void ventanaCliente(){
        escenarioPrincipal.ventanaCliente();
    }
    
    public void ventanaFormaPago(){
        escenarioPrincipal.ventanaFormaPago();
    }
    
    public void ventanaFactura(){
        escenarioPrincipal.ventanaFactura();
    }
    
    public void ventanaCategoria(){
        escenarioPrincipal.ventanaCategoria();
    }
    
    public void ventanaProducto(){
        escenarioPrincipal.ventanaProducto();
    }
    
    public void ventanaDetalleFactura(){
        escenarioPrincipal.ventanaDetalleFactura();
    }    
   
    
    public void ventanaUsuario(){
        escenarioPrincipal.ventanaUsuario();
    }
    
}
