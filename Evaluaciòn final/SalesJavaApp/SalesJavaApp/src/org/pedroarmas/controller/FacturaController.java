package org.pedroarmas.controller;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
import org.pedroarmas.bean.Cliente;
import org.pedroarmas.bean.Factura;
import org.pedroarmas.bean.FormaPago;
import org.pedroarmas.db.Conexion;
import org.pedroarmas.report.GenerarReporte;
import org.pedroarmas.system.Principal;


public class FacturaController implements Initializable{
    private Principal escenarioPrincipal;
    private enum operaciones {NUEVO, GUARDAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<Factura> listaFactura;
    private ObservableList<Cliente> listaCliente;
    private ObservableList<FormaPago> listaFormaPago;
    private DatePicker fechaFactura;
    @FXML private TextField txtNumeroFactura;
    @FXML private GridPane grpFechaFactura;
    @FXML private ComboBox cmbCodigoCliente;
    @FXML private ComboBox cmbCodigoFormaPago;
    @FXML private TableView tblFacturas;
    @FXML private TableColumn colNumeroFactura;
    @FXML private TableColumn colFechaFactura;
    @FXML private TableColumn colCodigoCliente;
    @FXML private TableColumn colCodigoFormaPago;
    @FXML private Button btnNuevo;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnReporte;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
        fechaFactura = new DatePicker(Locale.ENGLISH);
        fechaFactura.setDateFormat(new SimpleDateFormat("yyy-MM-dd"));
        fechaFactura.getCalendarView().todayButtonTextProperty().set("Today");
        fechaFactura.getCalendarView().setShowWeeks(false);
        grpFechaFactura.add(fechaFactura, 3, 0);
        fechaFactura.getStylesheets().add("/org/pedroarmas/resource/DatePicker.css");
    }
    
    public void cargarDatos(){
        tblFacturas.setItems(getFactura());
        colNumeroFactura.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("numeroFactura"));
        colFechaFactura.setCellValueFactory(new PropertyValueFactory<Factura, Date>("fechaFactura"));
        colCodigoCliente.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("codigoCliente"));
        colCodigoFormaPago.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("codigoFormaPago"));
        cmbCodigoCliente.setItems(getCliente());
        cmbCodigoFormaPago.setItems(getFormaPago());
    }
    
    public void seleccionarElemento(){
        txtNumeroFactura.setText(String.valueOf(((Factura)tblFacturas.getSelectionModel().getSelectedItem()).getNumeroFactura()));
        fechaFactura.selectedDateProperty().set(((Factura)tblFacturas.getSelectionModel().getSelectedItem()).getFechaFactura());
        cmbCodigoCliente.getSelectionModel().select(buscarCliente(((Factura)tblFacturas.getSelectionModel().getSelectedItem()).getCodigoCliente()));
        cmbCodigoFormaPago.getSelectionModel().select(buscarFormaPago(((Factura)tblFacturas.getSelectionModel().getSelectedItem()).getCodigoFormaPago()));
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
    
    public ObservableList<Cliente> getCliente(){
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarClientes}");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
                lista.add(new Cliente( resultado.getInt("codigoCliente"),
                                    resultado.getString("nombreCliente"),
                                    resultado.getString("apellidoCliente"),
                                    resultado.getString("domicilioCliente"),
                                    resultado.getDate("fechaNacimiento"),
                                    resultado.getInt("telefonoCliente"),
                                    resultado.getString("correoCliente")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }        
        return listaCliente = FXCollections.observableArrayList(lista);
    }
    
    public Cliente buscarCliente(int codigoCliente){
        Cliente resultado = null;
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarCliente(?)}");
            procedimiento.setInt(1, codigoCliente);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()){
                resultado = new Cliente(registro.getInt("codigoCliente"),
                                    registro.getString("nombreCliente"),
                                    registro.getString("apellidoCliente"),
                                    registro.getString("domicilioCliente"),
                                    registro.getDate("fechaNacimiento"),
                                    registro.getInt("telefonoCliente"),
                                    registro.getString("correoCliente"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }        
        return resultado;
    }
    
    
    public ObservableList<FormaPago> getFormaPago(){
        ArrayList<FormaPago> lista = new ArrayList<FormaPago>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarFormasPagos}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()){
                lista.add(new FormaPago( resultado.getInt("codigoFormaPago"),
                                    resultado.getString("nombrePago"),
                                    resultado.getString("otrosDetalles")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaFormaPago = FXCollections.observableArrayList(lista);
    }
    
    public FormaPago buscarFormaPago (int codigoFormaPago){
        FormaPago resultado = null;
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarFormaPago(?)}");
            procedimiento.setInt(1, codigoFormaPago);
            ResultSet registro = procedimiento.executeQuery();
            while(registro.next()){
                resultado = new FormaPago(registro.getInt("codigoFormaPago"),
                                    registro.getString("nombrePago"),
                                    registro.getString("otrosDetalles"));
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
        Factura registro = new Factura();
        registro.setNumeroFactura(Integer.parseInt(txtNumeroFactura.getText()));
        registro.setFechaFactura(fechaFactura.getSelectedDate());
        registro.setCodigoCliente(((Cliente)cmbCodigoCliente.getSelectionModel().getSelectedItem()).getCodigoCliente());
        registro.setCodigoFormaPago(((FormaPago)cmbCodigoFormaPago.getSelectionModel().getSelectedItem()).getCodigoFormaPago());
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarFactura(?,?,?,?)}");
            procedimiento.setInt(1, registro.getNumeroFactura());
            procedimiento.setDate(2, new java.sql.Date(registro.getFechaFactura().getTime()));
            procedimiento.setInt(3, registro.getCodigoCliente());
            procedimiento.setInt(4, registro.getCodigoFormaPago());
            procedimiento.execute();
            listaFactura.add(registro);
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
                if (tblFacturas.getSelectionModel().getSelectedItem() != null){
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de elmiminar el registrp?", "Eliminar Factura", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION){
                        try{
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarFactura(?)}");
                            procedimiento.setInt(1, ((Factura)tblFacturas.getSelectionModel().getSelectedItem()).getNumeroFactura());
                            procedimiento.execute();
                            listaFactura.remove(tblFacturas.getSelectionModel().getSelectedIndex());
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
                if (tblFacturas.getSelectionModel().getSelectedItem() != null){
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    btnNuevo.setDisable(true);
                    btnEliminar.setDisable(true);
                    activarControles();
                    tipoDeOperacion = operaciones.ACTUALIZAR;
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar u elemento");
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
        }
    }
    
    public void reporte(){
        switch(tipoDeOperacion){
            case NINGUNO:
                imprimirReporte();
                break;            
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnNuevo.setDisable(false);                
                btnEliminar.setDisable(false);
                btnEditar.setText("Editar");
                btnReporte.setText("Reporte");
                tipoDeOperacion = operaciones.NINGUNO;
                break;
                
        }
    }
    
    public void imprimirReporte(){
        Map parametros = new HashMap();
        int numFactura = ((Factura)tblFacturas.getSelectionModel().getSelectedItem()).getNumeroFactura();
        parametros.put("numFactura", numFactura);
        GenerarReporte.mostrarReporte("ReporteFactura.jasper", "Factura", parametros);
    }
    
    
    
    public void actualizar(){
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EditarFactura(?,?)}");
            Factura registro = (Factura)tblFacturas.getSelectionModel().getSelectedItem();
            registro.setFechaFactura(fechaFactura.getSelectedDate());
            procedimiento.setInt(1, registro.getNumeroFactura());
            procedimiento.setDate(2, new java.sql.Date(registro.getFechaFactura().getTime()));
            procedimiento.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
        
    public void desactivarControles(){
        txtNumeroFactura.setEditable(false);
        grpFechaFactura.setDisable(false);
        cmbCodigoCliente.setDisable(false);
        cmbCodigoFormaPago.setDisable(false);
    }
    
    public void activarControles(){
        txtNumeroFactura.setEditable(true);
        grpFechaFactura.setDisable(false);
        cmbCodigoCliente.setDisable(false);
        cmbCodigoFormaPago.setDisable(false);
    }
    
    public void limpiarControles(){
        txtNumeroFactura.clear();
        cmbCodigoCliente.getSelectionModel().clearSelection();
        cmbCodigoFormaPago.getSelectionModel().clearSelection();
        tblFacturas.getSelectionModel().clearSelection();
    }
    
    
    
    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void menuPrincipal(){
        escenarioPrincipal.menuPrincipal();
    }
    
    
    
}
