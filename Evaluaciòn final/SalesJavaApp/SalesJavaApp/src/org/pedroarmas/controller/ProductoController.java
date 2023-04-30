package org.pedroarmas.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.pedroarmas.bean.Categoria;
import org.pedroarmas.bean.Producto;
import org.pedroarmas.db.Conexion;
import org.pedroarmas.report.GenerarReporte;
import org.pedroarmas.system.Principal;



public class ProductoController implements Initializable{
    private Principal escenarioPrincipal;
    private enum operaciones {NUEVO, GUARDAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<Producto> listaProducto;
    private ObservableList<Categoria> listaCategoria;
    @FXML private TextField txtCodigoProducto;
    @FXML private TextField txtNombreProducto;
    @FXML private TextField txtPrecioProducto;
    @FXML private TextField txtStock;
    @FXML private ComboBox cmbCodigoCategoria;
    @FXML private TableView tblProductos;
    @FXML private TableColumn colCodigoProducto;
    @FXML private TableColumn colNombreProducto;
    @FXML private TableColumn colPrecioProducto;
    @FXML private TableColumn colStock;
    @FXML private TableColumn colCodigoCategoria;
    @FXML private Button btnNuevo;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnReporte;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();        
    }
    
    public void cargarDatos(){
        tblProductos.setItems(getProducto());
        colCodigoProducto.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("codigoProducto"));
        colNombreProducto.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
        colPrecioProducto.setCellValueFactory(new PropertyValueFactory<Producto, Double>("precioProducto"));
        colStock.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("stock"));
        colCodigoCategoria.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("codigoCategoria"));
        cmbCodigoCategoria.setItems(getCategoria());
    }
    
    public void seleccionarElemento(){
        txtCodigoProducto.setText(String.valueOf(((Producto)tblProductos.getSelectionModel().getSelectedItem()).getCodigoProducto()));
        txtNombreProducto.setText(((Producto)tblProductos.getSelectionModel().getSelectedItem()).getNombreProducto());
        txtPrecioProducto.setText(String.valueOf(((Producto)tblProductos.getSelectionModel().getSelectedItem()).getPrecioProducto()));
        txtStock.setText(String.valueOf(((Producto)tblProductos.getSelectionModel().getSelectedItem()).getStock()));
        cmbCodigoCategoria.getSelectionModel().select(buscarCategoria(((Producto)tblProductos.getSelectionModel().getSelectedItem()).getCodigoCategoria()));
    }
    
    public ObservableList<Producto> getProducto(){
        ArrayList<Producto> lista = new ArrayList<Producto>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarProductos}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()){
                lista.add(new Producto( resultado.getInt("codigoProducto"),
                                    resultado.getString("nombreProducto"),
                                    resultado.getDouble("precioProducto"),
                                    resultado.getInt("stock"),
                                    resultado.getInt("codigoCategoria")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return listaProducto = FXCollections.observableArrayList(lista);
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
    
    public Categoria buscarCategoria(int codigoCategoria){
        Categoria resultado = null;
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarCategoria(?)}");
            procedimiento.setInt(1, codigoCategoria);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()){
                resultado = new Categoria(registro.getInt("codigoCategoria"),
                                    registro.getString("nombreCategoria"),
                                    registro.getString("descripcionCategoria"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }           
        return resultado;
    }
    
    public void nuevo(){
        switch(tipoDeOperacion){
            case NINGUNO:
                activarControles();
                btnNuevo.setText("Guardar");
                btnEliminar.setText("Cancelar");
                btnEditar.setDisable(true);
                btnReporte.setDisable(true);
                tipoDeOperacion = operaciones.GUARDAR;
                break;
            case GUARDAR:
                guardar();
                desactivarControles();
                btnNuevo.setText("Nuevo");
                btnEliminar.setText("Eliminar");
                btnEditar.setDisable(false);
                btnReporte.setDisable(false);
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
                break;
        }
    }
    
    public void guardar(){
        Producto registro = new Producto();
        registro.setNombreProducto(txtNombreProducto.getText());
        registro.setPrecioProducto(Double.parseDouble(txtPrecioProducto.getText()));
        registro.setStock(Integer.parseInt(txtStock.getText()));
        registro.setCodigoCategoria(((Categoria)cmbCodigoCategoria.getSelectionModel().getSelectedItem()).getCodigoCategoria());
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarProducto(?,?,?,?)}");
            procedimiento.setString(1, registro.getNombreProducto());
            procedimiento.setDouble(2, registro.getPrecioProducto());
            procedimiento.setInt(3, registro.getStock());
            procedimiento.setInt(4, registro.getCodigoCategoria());
            procedimiento.execute();
            listaProducto.add(registro);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void eliminar(){
        switch(tipoDeOperacion){
            case GUARDAR:
                desactivarControles();
                limpiarControles();
                btnNuevo.setText("Nuevo");
                btnEliminar.setText("Eliminar");
                btnEditar.setDisable(false);
                btnReporte.setDisable(false);
                tipoDeOperacion = operaciones.NINGUNO;
                break;
            default:
                if (tblProductos.getSelectionModel().getSelectedItem() != null){
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?", "Eliminar Producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION){
                        try{
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarProducto(?)}");
                            procedimiento.setInt(1, ((Producto)tblProductos.getSelectionModel().getSelectedItem()).getCodigoProducto());
                            procedimiento.execute();
                            listaProducto.remove(tblProductos.getSelectionModel().getSelectedIndex());
                            limpiarControles();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Debe seleccionar un elemento");
                }
        }
    }
    
    public void editar(){
        switch(tipoDeOperacion){
            case NINGUNO:
                if (tblProductos.getSelectionModel().getSelectedItem() != null){
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    btnNuevo.setDisable(true);
                    btnEliminar.setDisable(true);
                    activarControles();
                    tipoDeOperacion = operaciones.ACTUALIZAR;
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
                }
                break;
                
            case ACTUALIZAR:
                actualizar();
                btnEditar.setText("Editar");
                btnReporte.setText("Reporte");
                btnNuevo.setDisable(false);
                btnEliminar.setDisable(false);
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
                limpiarControles();
                break;
        }
    }
    
    
    public void actualizar(){
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EditarProducto(?,?,?,?)}");
            Producto registro = (Producto)tblProductos.getSelectionModel().getSelectedItem();
            registro.setNombreProducto(txtNombreProducto.getText());
            registro.setPrecioProducto(Double.parseDouble(txtPrecioProducto.getText()));
            registro.setStock(Integer.parseInt(txtStock.getText()));
            procedimiento.setInt(1, registro.getCodigoProducto());
            procedimiento.setString(2, registro.getNombreProducto());
            procedimiento.setDouble(3, registro.getPrecioProducto());
            procedimiento.setInt(4, registro.getStock());
            procedimiento.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void generarReporte(){
        switch(tipoDeOperacion){
            case NINGUNO:
                imprimirReporte();
                break;
        }
    }
    
    public void imprimirReporte(){
        Map parametros = new HashMap();
        parametros.put("codigoProducto", null);
        GenerarReporte.mostrarReporte("ReporteProductos.jasper", "Reporte Pedido de Productos", parametros);
    }
    
    
    
    
    
    
    public void desactivarControles(){
        txtCodigoProducto.setEditable(false);
        txtNombreProducto.setEditable(false);
        txtPrecioProducto.setEditable(false);
        txtStock.setEditable(false);
        cmbCodigoCategoria.setDisable(true);
    }
    
    public void activarControles(){
        txtCodigoProducto.setEditable(false);
        txtNombreProducto.setEditable(true);
        txtPrecioProducto.setEditable(true);
        txtStock.setEditable(true);
        cmbCodigoCategoria.setDisable(false);
    }
    
    public void limpiarControles(){
        txtCodigoProducto.clear();
        txtNombreProducto.clear();
        txtPrecioProducto.clear();
        txtStock.clear();
        cmbCodigoCategoria.getSelectionModel().clearSelection();
        tblProductos.getSelectionModel().clearSelection();
    }
    
    
    

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    
    
    
    
}
