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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.pedroarmas.bean.DetalleFactura;
import org.pedroarmas.bean.Factura;
import org.pedroarmas.bean.Producto;
import org.pedroarmas.db.Conexion;
import org.pedroarmas.system.Principal;


public class DetalleFacturaController implements Initializable{
    private Principal escenarioPrincipal;
    private enum operaciones {NUEVO, GUARDAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<DetalleFactura> listaDetalleFactura;
    private ObservableList<Producto> listaProducto;
    private ObservableList<Factura> listaFactura;
    @FXML private TextField txtCodigoDetalleFactura;
    @FXML private TextField txtCantidad;
    @FXML private TextField txtPrecio;
    @FXML private ComboBox cmbNumeroFactura;
    @FXML private ComboBox cmbCodigoProducto;
    @FXML private TableView tblDetalleFacturas;
    @FXML private TableColumn colCodigoDetalleFactura;
    @FXML private TableColumn colNumeroFactura;
    @FXML private TableColumn colCantidad;
    @FXML private TableColumn colPrecio;
    @FXML private TableColumn colCodigoProducto;
    @FXML private Button btnNuevo;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnReporte;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }
    
    public void cargarDatos(){
        tblDetalleFacturas.setItems(getDetalleFactura());
        colCodigoDetalleFactura.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Integer>("codigoDetalleFactura"));
        colNumeroFactura.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Integer>("numeroFactura"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Integer>("cantidad"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Double>("precio"));
        colCodigoProducto.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Integer>("codigoProducto"));
        cmbNumeroFactura.setItems(getFactura());
        cmbCodigoProducto.setItems(getProducto());
    }
    
    public void seleccionarElemento(){
        txtCodigoDetalleFactura.setText(String.valueOf(((DetalleFactura)tblDetalleFacturas.getSelectionModel().getSelectedItem()).getCodigoDetalleFactura()));
        cmbNumeroFactura.getSelectionModel().select(buscarFactura(((DetalleFactura)tblDetalleFacturas.getSelectionModel().getSelectedItem()).getNumeroFactura()));
        txtCantidad.setText(String.valueOf(((DetalleFactura)tblDetalleFacturas.getSelectionModel().getSelectedItem()).getCantidad()));
        txtPrecio.setText(String.valueOf(((DetalleFactura)tblDetalleFacturas.getSelectionModel().getSelectedItem()).getPrecio()));
        cmbCodigoProducto.getSelectionModel().select(buscarProducto(((DetalleFactura)tblDetalleFacturas.getSelectionModel().getSelectedItem()).getCodigoProducto()));
    }
    
    
    public ObservableList<DetalleFactura> getDetalleFactura(){
        ArrayList<DetalleFactura> lista = new ArrayList<DetalleFactura>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarDetallesFacturas}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()){
                lista.add(new DetalleFactura( resultado.getInt("codigoDetalleFactura"),
                                    resultado.getInt("numeroFactura"),
                                    resultado.getInt("cantidad"),
                                    resultado.getDouble("precio"),
                                    resultado.getInt("codigoProducto")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaDetalleFactura = FXCollections.observableArrayList(lista);
    }
    
    
    public ObservableList<Factura> getFactura(){
        ArrayList<Factura> lista = new ArrayList<Factura>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarFacturas}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()){
                lista.add(new Factura( resultado.getInt("numeroFactura"),
                                resultado.getDate("fechaFactura"),
                                resultado.getInt("codigoCliente"),
                                resultado.getInt("codigoFormaPago")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaFactura = FXCollections.observableArrayList(lista);
    }
    
    public Factura buscarFactura(int numeroFactura){
        Factura resultado = null;
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarFactura(?)}");
            procedimiento.setInt(1, numeroFactura);
            ResultSet registro = procedimiento.executeQuery();
            while(registro.next()){
                resultado = new Factura(registro.getInt("numeroFactura"),
                                    registro.getDate("fechaFactura"),
                                    registro.getInt("codigoCliente"),
                                    registro.getInt("codigoFormaPago"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultado;
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
    
    public Producto buscarProducto(int codigoProducto){
        Producto resultado = null;
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarProducto(?)}");
            procedimiento.setInt(1, codigoProducto);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()){
                resultado = new Producto( registro.getInt("codigoProducto"),
                                    registro.getString("nombreProducto"),
                                    registro.getDouble("precioProducto"),
                                    registro.getInt("stock"),
                                    registro.getInt("codigoCategoria"));
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
                limpiarControles();
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
        DetalleFactura registro = new DetalleFactura();
        registro.setNumeroFactura(((Factura)cmbNumeroFactura.getSelectionModel().getSelectedItem()).getNumeroFactura());
        registro.setCantidad(Integer.parseInt(txtCantidad.getText()));
        registro.setPrecio(Double.parseDouble(txtPrecio.getText()));
        registro.setCodigoProducto(((Producto)cmbCodigoProducto.getSelectionModel().getSelectedItem()).getCodigoProducto());
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarDetalleFactura(?,?,?,?)}");
            procedimiento.setInt(1, registro.getNumeroFactura());
            procedimiento.setInt(2, registro.getCantidad());
            procedimiento.setDouble(3, registro.getPrecio());
            procedimiento.setInt(4, registro.getCodigoProducto());
            procedimiento.execute();
            listaDetalleFactura.add(registro);            
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
                if (tblDetalleFacturas.getSelectionModel().getSelectedItem() != null){
                    int respuesta = JOptionPane.showConfirmDialog(null,"¿Está seguro de eliminar el registro?", "Eliminar Detalle de Factura", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(respuesta == JOptionPane.YES_OPTION){
                        try{
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarDetalleFactura(?)}");
                            procedimiento.setInt(1,((DetalleFactura)tblDetalleFacturas.getSelectionModel().getSelectedItem()).getCodigoDetalleFactura());
                            procedimiento.execute();
                            listaDetalleFactura.remove(tblDetalleFacturas.getSelectionModel().getSelectedIndex());
                            limpiarControles();                            
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
                }
        }
    }
    
    public void editar(){
        switch(tipoDeOperacion){
            case NINGUNO:
                if (tblDetalleFacturas.getSelectionModel().getSelectedItem() != null){
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    btnNuevo.setDisable(true);
                    btnEliminar.setDisable(true);
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
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EditarDetalleFactura(?,?,?)}");
            DetalleFactura registro = (DetalleFactura)tblDetalleFacturas.getSelectionModel().getSelectedItem();
            registro.setCantidad(Integer.parseInt(txtCantidad.getText()));
            registro.setPrecio(Double.parseDouble(txtPrecio.getText()));
            procedimiento.setInt(1, registro.getCodigoDetalleFactura());
            procedimiento.setInt(2, registro.getCantidad());
            procedimiento.setDouble(3, registro.getPrecio());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    public void desactivarControles(){
        txtCodigoDetalleFactura.setEditable(false);
        txtCantidad.setEditable(false);
        txtPrecio.setEditable(false);
        cmbNumeroFactura.setDisable(true);
        cmbCodigoProducto.setDisable(true);
    }
    
    public void activarControles(){
        txtCodigoDetalleFactura.setEditable(false);
        txtCantidad.setEditable(true);
        txtPrecio.setEditable(true);
        cmbNumeroFactura.setDisable(false);
        cmbCodigoProducto.setDisable(false);
    }
    
    public void limpiarControles(){
        txtCodigoDetalleFactura.clear();
        txtCantidad.clear();
        txtPrecio.clear();
        cmbNumeroFactura.getSelectionModel().clearSelection();
        cmbCodigoProducto.getSelectionModel().clearSelection();
    }
    

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    
    
    
}
