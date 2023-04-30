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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
import org.pedroarmas.bean.Cliente;
import org.pedroarmas.db.Conexion;
import org.pedroarmas.report.GenerarReporte;
import org.pedroarmas.system.Principal;


public class ClienteController implements Initializable{
    private Principal escenarioPrincipal;

    private ObservableList getCliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private enum operaciones {NUEVO, GUARDAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<Cliente> listaCliente;
    private DatePicker fecha;
    @FXML private TextField txtCodigoCliente;
    @FXML private TextField txtNombreCliente;
    @FXML private TextField txtApellidoCliente;
    @FXML private TextField txtDomicilioCliente;
    @FXML private TextField txtTelefonoCliente;
    @FXML private TextField txtCorreoElectronico;
    @FXML private GridPane grpFechaNacimiento;
    @FXML private TableView tblClientes;
    @FXML private TableColumn colCodigoCliente;
    @FXML private TableColumn colNombreCliente;
    @FXML private TableColumn colApellidoCliente;
    @FXML private TableColumn colDomicilioCliente;
    @FXML private TableColumn colFechaNacimiento;
    @FXML private TableColumn colTelefono;
    @FXML private TableColumn colCorreo;
    @FXML private Button btnNuevo;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnReporte;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
        fecha = new DatePicker(Locale.ENGLISH);        
    }
    
    
    public void cargarDatos(){
        tblClientes.setItems(getCliente());
        colCodigoCliente.setCellValueFactory(new PropertyValueFactory<Cliente,Integer>("codigoCliente"));
        colNombreCliente.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombreCliente"));
        colApellidoCliente.setCellValueFactory(new PropertyValueFactory<Cliente,String>("apellidoCliente"));
        colDomicilioCliente.setCellValueFactory(new PropertyValueFactory<Cliente,String>("domicilioCliente"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<Cliente,Date>("fechaNacimiento"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente,Integer>("telefonoCliente"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<Cliente,String>("correoCliente"));
    }
    
    public void seleccionarElemento(){
        txtCodigoCliente.setText(String.valueOf(((Cliente)tblClientes.getSelectionModel().getSelectedItem()).getCodigoCliente()));
        txtNombreCliente.setText(((Cliente)tblClientes.getSelectionModel().getSelectedItem()).getNombreCliente());
        txtApellidoCliente.setText(((Cliente)tblClientes.getSelectionModel().getSelectedItem()).getApellidoCliente());
        txtDomicilioCliente.setText(((Cliente)tblClientes.getSelectionModel().getSelectedItem()).getDomicilioCliente());
        fecha.selectedDateProperty().set(((Cliente)tblClientes.getSelectionModel().getSelectedItem()).getFechaNacimiento());
        txtTelefonoCliente.setText(String.valueOf(((Cliente)tblClientes.getSelectionModel().getSelectedItem()).getTelefonoCliente()));
        txtCorreoElectronico.setText(((Cliente)tblClientes.getSelectionModel().getSelectedItem()).getCorreoCliente());
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
        Cliente registro = new Cliente();
        registro.setNombreCliente(txtNombreCliente.getText());
        registro.setApellidoCliente(txtApellidoCliente.getText());
        registro.setDomicilioCliente(txtDomicilioCliente.getText());
        registro.setFechaNacimiento(fecha.getSelectedDate());
        registro.setTelefonoCliente(Integer.parseInt(txtTelefonoCliente.getText()));
        registro.setCorreoCliente(txtCorreoElectronico.getText());
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarCliente(?,?,?,?,?,?)}");
            procedimiento.setString(1, registro.getNombreCliente());
            procedimiento.setString(2, registro.getApellidoCliente());
            procedimiento.setString(3, registro.getDomicilioCliente());
            procedimiento.setDate(4, new java.sql.Date(registro.getFechaNacimiento().getTime()));
            procedimiento.setInt(5, registro.getTelefonoCliente());
            procedimiento.setString(6, registro.getCorreoCliente());
            procedimiento.execute();
            listaCliente.add(registro);            
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
                if (tblClientes.getSelectionModel().getSelectedItem() != null){
                    int respuesta = JOptionPane.showConfirmDialog(null,"¿Está seguro de eliminar el registro?","Eliminar Cliente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION){
                        try{
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarCliente(?)}");
                            procedimiento.setInt(1, ((Cliente)tblClientes.getSelectionModel().getSelectedItem()).getCodigoCliente());
                            procedimiento.execute();
                            listaCliente.remove(tblClientes.getSelectionModel().getSelectedIndex());
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
                if (tblClientes.getSelectionModel().getSelectedItem() != null){
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    btnNuevo.setDisable(true);
                    btnEliminar.setDisable(true);
                    tipoDeOperacion = operaciones.ACTUALIZAR;
                    activarControles();
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
                desactivarControles();
                limpiarControles();
                break;
            
        }
        
    }
    
    public void actualizar(){
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EditarCliente(?,?,?,?,?,?,?)}");
            Cliente registro = (Cliente)tblClientes.getSelectionModel().getSelectedItem();
            registro.setNombreCliente(txtNombreCliente.getText());
            registro.setApellidoCliente(txtApellidoCliente.getText());
            registro.setDomicilioCliente(txtDomicilioCliente.getText());
            registro.setFechaNacimiento(fecha.getSelectedDate());
            registro.setTelefonoCliente(Integer.parseInt(txtTelefonoCliente.getText()));
            registro.setCorreoCliente(txtCorreoElectronico.getText());
            procedimiento.setInt(1, registro.getCodigoCliente());
            procedimiento.setString(2, registro.getNombreCliente());
            procedimiento.setString(3, registro.getApellidoCliente());
            procedimiento.setString(4, registro.getDomicilioCliente());
            procedimiento.setDate(5, new java.sql.Date(registro.getFechaNacimiento().getTime()));
            procedimiento.setInt(6, registro.getTelefonoCliente());
            procedimiento.setString(7, registro.getCorreoCliente());
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
        parametros.put("codigoCliente", null);
        GenerarReporte.mostrarReporte("ReporteClientes.jasper", "Reporte de Clientes", parametros);
    }
    
    
    
    
    public void desactivarControles(){
        txtCodigoCliente.setEditable(false);
        txtNombreCliente.setEditable(false);
        txtApellidoCliente.setEditable(false);
        txtDomicilioCliente.setEditable(false);
        txtTelefonoCliente.setEditable(false);
        txtCorreoElectronico.setEditable(false);
    }
    
    public void activarControles(){
        txtCodigoCliente.setEditable(false);
        txtNombreCliente.setEditable(true);
        txtApellidoCliente.setEditable(true);
        txtDomicilioCliente.setEditable(true);
        txtTelefonoCliente.setEditable(true);
        txtCorreoElectronico.setEditable(true);
    }
    
    public void limpiarControles(){
        txtCodigoCliente.clear();
        txtNombreCliente.clear();
        txtApellidoCliente.clear();
        txtDomicilioCliente.clear();
        txtTelefonoCliente.clear();
        txtCorreoElectronico.clear();
        fecha.selectedDateProperty().setValue(null);
        tblClientes.getSelectionModel().clearSelection();        
    }
    
    
    
    

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    
    
}
