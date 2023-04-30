/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pedroarmas.system;

import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.pedroarmas.controller.CategoriaController;
import org.pedroarmas.controller.ClienteController;
import org.pedroarmas.controller.DetalleFacturaController;
import org.pedroarmas.controller.FacturaController;
import org.pedroarmas.controller.FormaPagoController;
import org.pedroarmas.controller.LoginController;
import org.pedroarmas.controller.MenuPrincipalController;
import org.pedroarmas.controller.ProductoController;
import org.pedroarmas.controller.UsuarioController;

/**
 *
 * @author Pedro Armas
 */
public class Principal extends Application {    
    private Stage escenarioPrincipal;
    private Scene escena;
    
    @Override
    public void start(Stage escenarioPrincipal) throws IOException {
        this.escenarioPrincipal = escenarioPrincipal;
        this.escenarioPrincipal.setTitle("Sales Java App");        
        ventanaLogin();
        escenarioPrincipal.show();
        
    }
    
    
    
    public void ventanaUsuario(){
        try{
            UsuarioController usuarioController = (UsuarioController)cambiarEscena("UsuarioView.fxml",678,400);
            usuarioController.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    public void menuPrincipal(){
        try{
            MenuPrincipalController menuPrincipal = (MenuPrincipalController)cambiarEscena("MenuPrincipalView.fxml",527,400);
            menuPrincipal.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaCliente(){
        try{
            ClienteController clienteController = (ClienteController)cambiarEscena("ClienteView.fxml",1008,400);
            clienteController.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaFormaPago(){
        try{
            FormaPagoController formaPagoController = (FormaPagoController)cambiarEscena("FormaPagoView.fxml",834,400);
            formaPagoController.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaFactura(){
        try{
            FacturaController facturaController = (FacturaController)cambiarEscena("FacturaView.fxml",964,400);
            facturaController.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaCategoria(){
        try{
            CategoriaController categoriaController = (CategoriaController)cambiarEscena("CategoriaView.fxml",964,400);
            categoriaController.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaProducto(){
        try{
            ProductoController productoController = (ProductoController)cambiarEscena("ProductoView.fxml",1063,400);
            productoController.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaDetalleFactura(){
        try{
            DetalleFacturaController detalleFacturaController = (DetalleFacturaController)cambiarEscena("DetalleFacturaView.fxml",964, 400);
            detalleFacturaController.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    public Initializable cambiarEscena(String fxml, int ancho, int alto) throws Exception{
        Initializable resultado = null;
        FXMLLoader cargadorFXML = new FXMLLoader();
        InputStream archivo = Principal.class.getResourceAsStream(PAQUETE_VISTA+fxml);
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        cargadorFXML.setLocation(Principal.class.getResource(PAQUETE_VISTA+fxml));        
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.sizeToScene();
        resultado = (Initializable)cargadorFXML.getController();
        return resultado;
    }
    
}
