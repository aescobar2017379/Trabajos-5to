package org.pedroarmas.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.pedroarmas.bean.Categoria;
import org.pedroarmas.db.Conexion;
import org.pedroarmas.system.Principal;


public class CategoriaController implements Initializable{
    private Principal escenarioPrincipal;

    public static class tblCategorias {

        private static void setItems(ObservableList<Categoria> categoria) {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        public static Object getSelectionModel() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        public tblCategorias() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    }

    public static class colNombreCategoria {

        public static void setCellValueFactory(PropertyValueFactory<Categoria, String> propertyValueFactory) {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        public colNombreCategoria() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    }

    public static class colDescripcionCategoria {

        public static void setCellValueFactory(PropertyValueFactory<Categoria, String> propertyValueFactory) {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        public colDescripcionCategoria() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    }

    public static class txtCodigoCategoria {

        public static void setEditable(boolean b) {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        public static void clear() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        public txtCodigoCategoria() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    }

    public static class txtNombreCategoria {

        public static void setEditable(boolean b) {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        public static void clear() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        public txtNombreCategoria() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    }

    public static class txtDescripcionCategoria {

        public static void clear() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        public static void setEditable(boolean b) {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        public txtDescripcionCategoria() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    }

    public static class tblCategoria {

        public static Object getSelectionModel() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        public tblCategoria() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    }

    public static class colCodigoCategoria {

        public static void setCellValueFactory(PropertyValueFactory<Categoria, Integer> propertyValueFactory) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public colCodigoCategoria() {
        }

    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    private enum operaciones {NUEVO, GUARDAR, ELIMINAR, EDITAR, ACTUALIZAR,CANCELAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<Categoria> listaCategoria;    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
        
    }
    
    public void cargarDatos(){
        tblCategorias.setItems(getCategoria());
        colCodigoCategoria.setCellValueFactory(new PropertyValueFactory<Categoria, Integer>("codigoCategoria"));
        colNombreCategoria.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nombreCategoria"));
        colDescripcionCategoria.setCellValueFactory(new PropertyValueFactory<Categoria, String>("descripcionCategoria"));
    }
    
    public ObservableList<Categoria> getCategoria(){
        ArrayList<Categoria> lista = new ArrayList<Categoria>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarCategorias}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()){
                lista.add(new Categoria ( resultado.getInt("codigoCategoria"),
                                resultado.getString("nombreCategoria"),
                                resultado.getString("descripcionCategoria")));
            }
                    
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaCategoria = FXCollections.observableArrayList(lista);
    }
    
    public void desactivarControles(){
        txtCodigoCategoria.setEditable(false);
        txtNombreCategoria.setEditable(false);
        txtDescripcionCategoria.setEditable(false);
    }
    
    public void activarControles(){
        txtCodigoCategoria.setEditable(false);
        txtNombreCategoria.setEditable(true);
        txtDescripcionCategoria.setEditable(true);
        
    }
    
    public void limpiarControles(){
        txtCodigoCategoria.clear();
        txtNombreCategoria.clear();
        txtDescripcionCategoria.clear();
    
        
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    
    
}