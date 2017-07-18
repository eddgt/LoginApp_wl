/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_vista;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.bdg.session.BaseSession;

/**
 *
 * @author Admin_bdgsa
 
 */
@ManagedBean(name = "templateBeanVend")
@ViewScoped
public class TemplateBeanVend extends BaseSession{
    private String inicio;
    private String venta="Ventas";
    private String renovaciones="Renovaciónes";
   

   
    public TemplateBeanVend(){
        
    }
    
    @PostConstruct
    public void init() {
         
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

    
}
