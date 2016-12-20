/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_vista;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.bdg.cms_dto.Asesor;
import org.bdg.cms_dto.Coordinador;
import org.bdg.cms_dto.DetalleVenta;
import org.bdg.cms_conexion.Conexion;
import org.bdg.cms_buc.Querys_C;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import oracle.jdbc.driver.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;
import org.bdg.base.Constantes;
import javax.faces.event.ActionEvent;
import org.bdg.session.BaseSession;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import org.bdg.utils.JsfUtil;
/**
 *
 * @author Daniel Mendez
 */
@ManagedBean(name = "revertirBean")
@ViewScoped
public class RevertirBean extends BaseSession {
   
    
    ////////////////////////////////////////////////////////////////////
    private List<DetalleVenta> listaVentas;
    private List<DetalleVenta> ventasSeleccionadas;
    private List<Coordinador> listaCoordinador;
    private List<Asesor> listaAsesor;
    private Coordinador coordinadorSelected;
    private Asesor asesorSelected;
    private int idCoordinador;
    private String nombreCoordinadorSelected;
    private int idAsesorSelelected;
   
    
   
    public int getIdAsesorSelelected() {
        return idAsesorSelelected;
    }

    public void setIdAsesorSelelected(int idAsesorSelelected) {
        this.idAsesorSelelected = idAsesorSelelected;
    }
    
    
    
    public String getNombreCoordinadorSelected() {
        return nombreCoordinadorSelected;
    }

    public void setNombreCoordinadorSelected(String nombreCoordinadorSelected) {
        this.nombreCoordinadorSelected = nombreCoordinadorSelected;
    }
    
    
    
    public int getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(int idCoordinador) {
        this.idCoordinador = idCoordinador;
    }
    
    
    public Asesor getAsesorSelected() {
        return asesorSelected;
    }

    public void setAsesorSelected(Asesor asesorSelected) {
        this.asesorSelected = asesorSelected;
    }

    public Coordinador getCoordinadorSelected() {
        return coordinadorSelected;
    }

    public void setCoordinadorSelected(Coordinador coordinadorSelected) {
        this.coordinadorSelected = coordinadorSelected;
    }
        //listaMunicipio = abc.listMunici
    public RevertirBean() {
                
    }
    
    public void oneCoordinadorChange() {        
        String nombreCoordinadorFiltro = this.getNombreCoordinadorSelected();
        this.listaAsesor = this.obtenerListadeAsesor(nombreCoordinadorFiltro);
        //listaMunicipio = abc.listMunicipiosByDepto(departamentoSelected);
    }
    
    public void oneAsesorChange(){
        int idAsesorSeleccionado = this.getIdAsesorSelelected();
        //this.listaVentas = this.getVentasCoordinador(idAsesorSeleccionado);          
    }
     
     
    @PostConstruct
    public void init() {           
        coordinadorSelected = new Coordinador();
        asesorSelected = new Asesor();       
        ////////////////////////// 
        
        String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
        String rolUsuarioLogueado = this.getAtributoSession(Constantes.SS_ROL);
        
        if(usuarioLogueado!=null && rolUsuarioLogueado!=null && (rolUsuarioLogueado.equals(Constantes.ROL_ADMIN) 
           || rolUsuarioLogueado.equals(Constantes.ROL_PLANIFICADOR))){            
            this.listaCoordinador= this.obtenerListadeCoordinador();
            
        }else{
           try{
              ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
              context.redirect(context.getRequestContextPath() + "/");
           }catch(Exception e){

           }
        }
    }
    
    
    public void btnClickBuscarVentas(ActionEvent actionEvent) {       
       this.listaVentas = this.getVentas();
    }
    
    public void btnRevertirPLANNINGCORD(ActionEvent actionEvent) {  
        Conexion conec = new Conexion();                
        Connection conex = conec.getConexion();
        try{              
            Querys_C query = new Querys_C();
            query.generarConsultaRevertirPlanningVerificadosCORDPLANNIN(Integer.toString(this.getIdAsesorSelelected()), this.getAtributoSession(Constantes.SS_USUARIO));
            String generado = query.getConsulta_RevertirPlanningVerificadosCORDPLANNING();
            CallableStatement cstmt = conex.prepareCall(generado);
            cstmt.executeQuery();  
            JsfUtil.addSuccessMessage(Constantes.SS_MSG_SUCCESS_RREVERSION_FINALIZACION);//mensaje success
        }catch(Exception e){
            
        }  
        this.listaCoordinador= this.obtenerListadeCoordinador();
        this.listaAsesor = this.obtenerListadeAsesor(this.getNombreCoordinadorSelected());
    }
    
     
    public void btnClickAsignarVentas(ActionEvent actionEvent) {       
        Conexion conec = new Conexion();                
        Connection conex = conec.getConexion();
        for(int i=0; i< this.ventasSeleccionadas.size(); i++){
            DetalleVenta ventaSeleccioanda = this.ventasSeleccionadas.get(i);                     
            /////////////////           
            try{                
                if(this.getIdAsesorSelelected()>0){
                    Querys_C query = new Querys_C();
                    query.generar_Consulta_AsignarAsesorVenta(
                        ventaSeleccioanda.getIdVenta(),this.idAsesorSelelected, "fvillatoro"
                    );
                    String queryAsignar = query.getAsignarAsesor_Venta();
                    CallableStatement cstmt = conex.prepareCall(queryAsignar);
                    cstmt.executeQuery();   
                }
                                              
            }catch(Exception e){
                
            }            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        this.listaVentas = this.getVentas();
    }
            
    
    public void btnClickDesasignarVentas(ActionEvent actionEvent) {
        Conexion conec = new Conexion();                
        Connection conex = conec.getConexion();
        for(int i=0; i< this.ventasSeleccionadas.size(); i++){
            DetalleVenta ventaSeleccioanda = this.ventasSeleccionadas.get(i);                     
            /////////////////           
            try{                
                Querys_C query = new Querys_C();
                query.generar_Consulta_CambioEstado(ventaSeleccioanda.getIdVenta());
                CallableStatement cstmt = conex.prepareCall(query.getModificar_Estado());
                cstmt.executeQuery();                                
                                              
            }catch(Exception e){
                
            }            
        }
        int idAsesorSeleccionado = this.getIdAsesorSelelected();
        try {
            conex.close();
        } catch (SQLException ex) {
        }
       // this.listaVentas = this.getVentasCoordinador(idAsesorSelelected);
    }
    
    
    public void btnClickRegresar(ActionEvent actionEvent){
        try{
          
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/ventasCoordinador.xhtml");           
        }catch(Exception e){
        
        }
        
    }
     
     
    public List<Coordinador> obtenerListadeCoordinador(){
        List<Coordinador> listaCoordinador = new ArrayList<Coordinador>();
        Connection conex = null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Querys_C query = new Querys_C();
            query.generar_Consulta_Coordinador_An();
                       
            rs1 = st1.executeQuery(query.getConsulta_CoordinadorAntigua());
            
            while (rs1.next())
            {
                Coordinador coor = new Coordinador();
                coor.setNombre((rs1.getString("NOMBRE")));
                coor.setIdCoordinador(Integer.parseInt(rs1.getString("ID")));
                listaCoordinador.add(coor);
            }            
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaCoordinador;
    }
    
    public List<Asesor> obtenerListadeAsesor(String nombreCoordinador){        
        List<Asesor> listaAsesor = new ArrayList<Asesor>();
        Connection conex = null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            Querys_C query = new Querys_C();  
            query.generarConsultaRevisarPlanningVerificadosCORDPLANNIN(nombreCoordinador);
            rs1 = st1.executeQuery(query.getConsulta_RevisarPlanningVerificadosCORDPLANNING());
            
            while (rs1.next())
            {
                Asesor ases = new Asesor();
                ases.setNombre((rs1.getString("NOM_VENDEDOR")));
                ases.setIdAsesor(Integer.parseInt(rs1.getString("CODVEND")));
                listaAsesor.add(ases);
            }
            
        }catch(Exception e){        
        }  
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaAsesor;        
    }
     
    
    
    public List<DetalleVenta> getVentas(){
        List<DetalleVenta> listaVentas = new ArrayList<DetalleVenta>();
        try{                
           
        }catch(Exception e){
       
        }
                        
        return listaVentas;
    }
    
    public void onRowSelect(SelectEvent event) {
      
    }
 
    public void onRowUnselect(UnselectEvent event) {
       
    }
    
    
        public List<Coordinador> getListaCoordinador() {
        return listaCoordinador;
    }

    public void setListaCoordinador(List<Coordinador> listaCoordinador) {
        this.listaCoordinador = listaCoordinador;
    }

    public List<Asesor> getListaAsesor() {
        return listaAsesor;
    }

    public void setListaAsesor(List<Asesor> listaAsesor) {
        this.listaAsesor = listaAsesor;
    }
    
    public List<DetalleVenta> getVentasSeleccionadas() {
        return ventasSeleccionadas;
    }

    public void setVentasSeleccionadas(List<DetalleVenta> ventasSeleccionadas) {
        this.ventasSeleccionadas = ventasSeleccionadas;
    }
    
    
    public List<DetalleVenta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<DetalleVenta> listaVentas) {
        this.listaVentas = listaVentas;
    }
     
    
    
}
