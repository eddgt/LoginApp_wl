/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_vista;
import javax.annotation.PostConstruct;
import org.bdg.base.Constantes;
import org.bdg.database.Login;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.bdg.session.BaseSession;
import org.bdg.utils.JsfUtil;

/**
 *
 * @author jarevalo
 */
@ManagedBean(name = "templateBean")
public class TemplateBean extends BaseSession{
    private String inicio;
    private String venta;
    private String renovaciones;
    private String revertir;
    private String procesos;
    private String operacionesVentas;
    private String ventasNuevas="Agregar venta";
    private String renovacionNuevas="Agregar renovación";
    private Boolean blnActivoRevertir;
    private Boolean blnActivoProcesos;
    private Boolean blnActivoCatalogos;
    private Boolean blnActivoVentasNuevas=true;
    private Boolean blnActivoRenovacionNuevas=true;
    private Boolean blnPermitidoAgregar =true;
    private Boolean blnPermitirverCatalogos;    
    private Boolean blnPermitirverCatalogosPersona =true;
    private Boolean blnPermitirverCatalogosEstructura =true;
    private Boolean blnPermitirverCatalogosProductoGlobal =true;    
    private Boolean blnPermitirverOperaciones =true;
    
    private String lblTextoMenuCatalogos="Catalogos";
    private String lblTextoMenuCatalogoPersona="Personas";
    private String lblTextoMenuCatalogoEstructura="Estructura Comercial";
    private String lblTextoMenuCatalogoProductoGlobal="Producto Global";
    private String lblTextoMenuCatalogoCarteraManual="Configuración Manual";
    private String lblTextoMenuCatalogoVenta="Parámetros de Ventas";

    public Boolean getBlnPermitirverOperaciones() {
        return blnPermitirverOperaciones;
    }

    public void setBlnPermitirverOperaciones(Boolean blnPermitirverOperaciones) {
        this.blnPermitirverOperaciones = blnPermitirverOperaciones;
    }

    public Boolean getBlnActivoCatalogos() {
        return blnActivoCatalogos;
    }

    public void setBlnActivoCatalogos(Boolean blnActivoCatalogos) {
        this.blnActivoCatalogos = blnActivoCatalogos;
    }    
    
    public String getLblTextoMenuCatalogos() {
        return lblTextoMenuCatalogos;
    }

    public void setLblTextoMenuCatalogos(String lblTextoMenuCatalogos) {
        this.lblTextoMenuCatalogos = lblTextoMenuCatalogos;        
    }

    public String getLblTextoMenuCatalogoPersona() {
        return lblTextoMenuCatalogoPersona;
    }

    public void setLblTextoMenuCatalogoPersona(String lblTextoMenuCatalogoPersona) {
        this.lblTextoMenuCatalogoPersona = lblTextoMenuCatalogoPersona;
    }

    public String getLblTextoMenuCatalogoEstructura() {
        return lblTextoMenuCatalogoEstructura;
    }

    public void setLblTextoMenuCatalogoEstructura(String lblTextoMenuCatalogoEstructura) {
        this.lblTextoMenuCatalogoEstructura = lblTextoMenuCatalogoEstructura;
    }

    public String getLblTextoMenuCatalogoProductoGlobal() {
        return lblTextoMenuCatalogoProductoGlobal;
    }

    public void setLblTextoMenuCatalogoProductoGlobal(String lblTextoMenuCatalogoProductoGlobal) {
        this.lblTextoMenuCatalogoProductoGlobal = lblTextoMenuCatalogoProductoGlobal;
    }

    public Boolean getBlnPermitirverCatalogosPersona() {
        return blnPermitirverCatalogosPersona;
    }

    public void setBlnPermitirverCatalogosPersona(Boolean blnPermitirverCatalogosPersona) {
        this.blnPermitirverCatalogosPersona = blnPermitirverCatalogosPersona;
    }

    public Boolean getBlnPermitirverCatalogosEstructura() {
        return blnPermitirverCatalogosEstructura;
    }

    public void setBlnPermitirverCatalogosEstructura(Boolean blnPermitirverCatalogosEstructura) {
        this.blnPermitirverCatalogosEstructura = blnPermitirverCatalogosEstructura;
    }

    public Boolean getBlnPermitirverCatalogosProductoGlobal() {
        return blnPermitirverCatalogosProductoGlobal;
    }

    public void setBlnPermitirverCatalogosProductoGlobal(Boolean blnPermitirverCatalogosProductoGlobal) {
        this.blnPermitirverCatalogosProductoGlobal = blnPermitirverCatalogosProductoGlobal;
    }
    
    

    public Boolean getBlnPermitirverCatalogos() {
        return blnPermitirverCatalogos;
    }

    public void setBlnPermitirverCatalogos(Boolean blnPermitirverCatalogos) {
        this.blnPermitirverCatalogos = blnPermitirverCatalogos;
    }

    public String getOperacionesVentas() {
        return operacionesVentas;
    }

    public void setOperacionesVentas(String operacionesVentas) {
        this.operacionesVentas = operacionesVentas;
    }

    public Boolean getBlnPermitidoAgregar() {
        return blnPermitidoAgregar;
    }

    public void setBlnPermitidoAgregar(Boolean blnPermitidoAgregar) {
        this.blnPermitidoAgregar = blnPermitidoAgregar;
    }
    
    
    public String getVentasNuevas() {
        return ventasNuevas;
    }

    public void setVentasNuevas(String ventasNuevas) {
        this.ventasNuevas = ventasNuevas;
    }

    public String getRenovacionNuevas() {
        return renovacionNuevas;
    }

    public void setRenovacionNuevas(String renovacionNuevas) {
        this.renovacionNuevas = renovacionNuevas;
    }

    public Boolean getBlnActivoVentasNuevas() {
        return blnActivoVentasNuevas;
    }

    public void setBlnActivoVentasNuevas(Boolean blnActivoVentasNuevas) {
        this.blnActivoVentasNuevas = blnActivoVentasNuevas;
    }

    public Boolean getBlnActivoRenovacionNuevas() {
        return blnActivoRenovacionNuevas;
    }

    public void setBlnActivoRenovacionNuevas(Boolean blnActivoRenovacionNuevas) {
        this.blnActivoRenovacionNuevas = blnActivoRenovacionNuevas;
    }
    
    
   
    public TemplateBean(){
        
    }
    
    @PostConstruct
    public void init() {
        String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
        String rolUsuarioLogueado = this.getAtributoSession(Constantes.SS_ROL);
        
        this.setInicio("Inicio");
        this.setVenta("Ventas");
        this.setRenovaciones("Renovaciones");
        this.setRevertir("Revertir");
        this.setBlnActivoRevertir(true);
        this.setProcesos("Procesos");
        this.setOperacionesVentas("Operaciones");
        this.setBlnActivoProcesos(true);
        this.setBlnPermitidoAgregar(false);
        this.setBlnActivoVentasNuevas(false);
        this.setBlnActivoRenovacionNuevas(false);
        this.setBlnPermitirverCatalogos(true);
        
        if(usuarioLogueado!=null && rolUsuarioLogueado!=null){
            if(rolUsuarioLogueado.equals(Constantes.ROL_ADMIN) || 
                    rolUsuarioLogueado.equals(Constantes.ROL_PLANIFICADOR)
                    ){
                this.setBlnActivoRevertir(false);
                this.setBlnActivoProcesos(false);
                this.setBlnPermitirverCatalogos(false);
                this.setBlnPermitirverOperaciones(false);/*permite ver opcion*/
                //this.lblTextoMenuCatalogos = lblTextoMenuCatalogos;
            } 
            else {
                this.setBlnPermitirverCatalogos(true);
                this.setBlnPermitirverOperaciones(true);
                /*this.lblTextoMenuCatalogos = null;
                this.lblTextoMenuCatalogoCarteraManual = null;
                this.lblTextoMenuCatalogoPersona = null;
                this.lblTextoMenuCatalogoProductoGlobal = null;
                this.lblTextoMenuCatalogoVenta = null;
                this.lblTextoMenuCatalogoEstructura = null;*/
                }
        }       
    }
    
    public Boolean getBlnActivoRevertir() {
        return blnActivoRevertir;
    }

    public void setBlnActivoRevertir(Boolean blnActivoRevertir) {
        this.blnActivoRevertir = blnActivoRevertir;
    }

    public Boolean getBlnActivoProcesos() {
        return blnActivoProcesos;
    }

    public void setBlnActivoProcesos(Boolean blnActivoProcesos) {
        this.blnActivoProcesos = blnActivoProcesos;
    }    
    
    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getVenta() {
        return venta;
    }

    public void setVenta(String venta) {
        this.venta = venta;
    }

    public String getRenovaciones() {
        return renovaciones;
    }

    public void setRenovaciones(String renovaciones) {
        this.renovaciones = renovaciones;
    }

    public String getRevertir() {
        return revertir;
    }

    public void setRevertir(String revertir) {
        this.revertir = revertir;
    }

    public String getProcesos() {
        return procesos;
    }

    public void setProcesos(String procesos) {
        this.procesos = procesos;
    }

    public String getLblTextoMenuCatalogoCarteraManual() {
        return lblTextoMenuCatalogoCarteraManual;
    }

    public void setLblTextoMenuCatalogoCarteraManual(String lblTextoMenuCatalogoCarteraManual) {
        this.lblTextoMenuCatalogoCarteraManual = lblTextoMenuCatalogoCarteraManual;
    }

    public String getLblTextoMenuCatalogoVenta() {
        return lblTextoMenuCatalogoVenta;
    }

    public void setLblTextoMenuCatalogoVenta(String lblTextoMenuCatalogoVenta) {
        this.lblTextoMenuCatalogoVenta = lblTextoMenuCatalogoVenta;
    }
    
}
