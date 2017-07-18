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
import org.bdg.cms_buc.Query_C;
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
@ManagedBean(name = "procesosBean")
@ViewScoped
public class ProcesosBean extends BaseSession {
   
    
    ////////////////////////////////////////////////////////////////////
    private Coordinador coordinadorSelected;
    private Asesor asesorSelected;
    private int idAsesorSelelected;
   
    
    public ProcesosBean() {
                
    }
    
    @PostConstruct
    public void init() {           
        coordinadorSelected = new Coordinador();
        asesorSelected = new Asesor();       
        ////////////////////////// 
        
        String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
        String rolUsuarioLogueado = this.getAtributoSession(Constantes.SS_ROL);
        
    }
    
    
    public void btnClickEjecutarAjusteVentas(ActionEvent actionEvent) {       
        try{       
            Conexion conec = new Conexion();
            Connection conex = conec.getConexion2();
            try{
                Query_C query = new Query_C();
                query.generar_Consulta_AjusteVentas_TigoSpace();
                CallableStatement cstmt = conex.prepareCall(query.getConsulta_CargaAjusteVentas());
                cstmt.executeQuery();
                conex.close();
            }catch(Exception e){
                JsfUtil.addSuccessMessage(Constantes.SS_MSG_ERROR_AJUSTEVENTAS);
            }
            JsfUtil.addSuccessMessage(Constantes.SS_MSG_SUCCESS_AJUSTEVENTAS);
        }catch(SQLException ex){
            Logger.getLogger(ProcesosBean.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }
    
    public void btnClickEjecutarCargaPreliminar(ActionEvent actionEvent) {       
        try{       
            Conexion conec = new Conexion();
            Connection conex = conec.getConexion2();
            try{
                Query_C query = new Query_C();
                query.generar_Consulta_CargaPreliminarVN();
                CallableStatement cstmt = conex.prepareCall(query.getConsulta_CargaPreliminarVN());
                cstmt.executeQuery();
                query.generar_Consulta_CargaDataPreliminar();
                cstmt = conex.prepareCall(query.getConsulta_CargaDataPreliminar());
                cstmt.executeQuery();
                conex.close();
            }catch(Exception e){
                JsfUtil.addSuccessMessage(Constantes.SS_MSG_ERROR_DATAPRELIMINAR);
            }
            JsfUtil.addSuccessMessage(Constantes.SS_MSG_SUCCESS_DATAPRELIMINAR);
            
        }catch(SQLException ex){
            Logger.getLogger(ProcesosBean.class.getName()).log(Level.SEVERE, null, ex);
        }            
       
    }
}