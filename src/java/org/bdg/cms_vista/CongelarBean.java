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
/**
 *
 * @author Daniel Mendez
 */
@ManagedBean(name = "congelarBean")
@ViewScoped
public class CongelarBean extends BaseSession {
   
    
    private String asesorSelected;
    private int asesorSelectedId;

    public int getAsesorSelectedId() {
        return asesorSelectedId;
    }

    public void setAsesorSelectedId(int asesorSelectedId) {
        this.asesorSelectedId = asesorSelectedId;
    }
    

    public String getAsesorSelected() {
        return asesorSelected;
    }

    public void setAsesorSelected(String asesorSelected) {
        this.asesorSelected = asesorSelected;
    }
    

        //listaMunicipio = abc.listMunici
    public CongelarBean() {
       
    }
    
    public void btnClickRegresar(ActionEvent actionEvent){
        try{
          
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/");           
        }catch(Exception e){        
        }        
    }
    
    public void btnClickCongelar(ActionEvent actionEvent){
        String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
        String rolUsuarioLogueado = this.getAtributoSession(Constantes.SS_ROL);
        String paginaOrigen = this.getAtributoSession(Constantes.SS_PaginaFinalizaVentaRenovacion);
        Conexion conec = new Conexion();                
        Connection conex = conec.getConexion();
        try{                
            Querys_C query = new Querys_C();
            String q = "";
            if (rolUsuarioLogueado.equals("COORD")){
                if (paginaOrigen.equals("VENTAS")){
                    q = query.generar_Consulta_CoordinadorFinalizarVenta(this.getAsesorSelectedId(),usuarioLogueado);                    
                }else{
                    q = query.generar_Consulta_CoordinadorFinalizarRenovacion(this.getAsesorSelectedId(),usuarioLogueado);                    
                }
            }else{
                    if (paginaOrigen.equals("VENTAS")){
                        q = query.generar_Consulta_PlanningFinalizarVenta(this.getAsesorSelectedId(),usuarioLogueado);                 
                    }else{                        
                        q = query.generar_Consulta_PlanningFinalizarRenovacion(this.getAsesorSelectedId(),usuarioLogueado);                    
                    }
            }
            CallableStatement cstmt = conex.prepareCall(q);
            cstmt.executeQuery();                                

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }   
        try{
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/ventasCoordinador.xhtml");           
        }catch(Exception e){
        }
        
    }
  
     
    @PostConstruct
    public void init() {
        ////////////////////////// 
        String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
        String rolUsuarioLogueado = this.getAtributoSession(Constantes.SS_ROL);
        
       
        if(usuarioLogueado!=null && rolUsuarioLogueado!=null){
            this.setAsesorSelected(this.getAtributoSession(Constantes.SS_VendedorCongelar));
            this.setAsesorSelectedId(Integer.parseInt(this.getAtributoSession(Constantes.SS_VendedorCongelarId)));
            System.out.println(this.getAsesorSelected());
        }else{
            try{
               ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
               context.redirect(context.getRequestContextPath() + "/");
            }catch(Exception e){
            
            }
             
        }
      
    }
     
}
