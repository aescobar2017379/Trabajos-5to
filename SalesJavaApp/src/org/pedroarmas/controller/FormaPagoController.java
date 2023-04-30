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
import javax.swing.JOptionPane;
import org.pedroarmas.bean.FormaPago;
import org.pedroarmas.db.Conexion;
import org.pedroarmas.system.Principal;


public class FormaPagoController implements Initializable{
    private Principal escenarioPrincipal;
    private enum operaciones {NUEVO, GUARDAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<FormaPago> listaFormaPago;
    @FXML private TextField txtCodigoFormaPago;
    @FXML private TextField txtNombrePago;
    @FXML private TextField txtOtrosDetalles;
    @FXML private TableView tblFormasPagos;
    @FXML private TableColumn colCodigoFormaPago;
    @FXML private TableColumn colNombrePago;
    @FXML private TableColumn colOtrosDetalles;
    @FXML private Button btnNuevo;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnReporte;    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }
    
    
    public void cargarDatos(){
        tblFormasPagos.setItems(getFormaPago());
        colCodigoFormaPago.setCellValueFactory(new PropertyValueFactory<FormaPago, Integer>("codigoFormaPago"));
        colNombrePago.setCellValueFactory(new PropertyValueFactory<FormaPago, String>("nombrePago"));
        colOtrosDetalles.setCellValueFactory(new PropertyValueFactory<FormaPago, String>("otrosDetalles"));
        
    }
    
    public void seleccionarElemento(){
        txtCodigoFormaPago.setText(String.valueOf(((FormaPago)tblFormasPagos.getSelectionModel().getSelectedItem()).getCodigoFormaPago()));
        txtNombrePago.setText(((FormaPago)tblFormasPagos.getSelectionModel().getSelectedItem()).getNombrePago());
        txtOtrosDetalles.setText(((FormaPago)tblFormasPagos.getSelectionModel().getSelectedItem()).getOtrosDetalles());
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
        FormaPago registro = new FormaPago();
        registro.setNombrePago(txtNombrePago.getText());
        registro.setOtrosDetalles(txtOtrosDetalles.getText());
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarFormaPago(?,?)}");
            procedimiento.setString(1, registro.getNombrePago());
            procedimiento.setString(2, registro.getOtrosDetalles());
            procedimiento.execute();
            listaFormaPago.add(registro);
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
                if(tblFormasPagos.getSelectionModel().getSelectedItem() != null){
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?", "Eliminar Forma de Pago", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION){
                        try{
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarFormaPago(?)}");
                            procedimiento.setInt(1, ((FormaPago)tblFormasPagos.getSelectionModel().getSelectedItem()).getCodigoFormaPago());
                            procedimiento.execute();//no existe el dato en MySQL
                            listaFormaPago.remove(tblFormasPagos.getSelectionModel().getSelectedIndex());
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
                if (tblFormasPagos.getSelectionModel().getSelectedItem() != null){
                    btnEditar.setText("Acutalizar");
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
                btnEditar.setText("Edtiar");
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
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EditarFormaPago(?,?,?)}");
            FormaPago registro = (FormaPago)tblFormasPagos.getSelectionModel().getSelectedItem();
            registro.setNombrePago(txtNombrePago.getText());
            registro.setOtrosDetalles(txtOtrosDetalles.getText());
            procedimiento.setInt(1, registro.getCodigoFormaPago());
            procedimiento.setString(2,registro.getNombrePago());
            procedimiento.setString(3, registro.getOtrosDetalles());
            procedimiento.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    public void desactivarControles(){
        txtCodigoFormaPago.setEditable(false);
        txtNombrePago.setEditable(false);
        txtOtrosDetalles.setEditable(false);
    }
    
    public void activarControles(){
        txtCodigoFormaPago.setEditable(false);
        txtNombrePago.setEditable(true);
        txtOtrosDetalles.setEditable(true);
    }
    
    public void limpiarControles(){
        txtCodigoFormaPago.clear();
        txtNombrePago.clear();
        txtOtrosDetalles.clear();
        tblFormasPagos.getSelectionModel().clearSelection();
        
    }
    

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    
    
    
}
