/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bdg.vista;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import com.bdg.sesion.BaseSession;
import com.bdg.base.Constantes;
import com.bdg.sesion.CerrarSesion;

/**
 *
 * @author oulloa
 */
@ManagedBean(name = "templateBean")
public class TemplateBean extends BaseSession{
    private String inicio;
    private String miclave; 
    private String navega;
    private String naf;
    private String amsys;
    private Boolean blnActivoRevertir;

   
    public TemplateBean(){
        
    }
    
    @PostConstruct
    public void init() {
        String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
        String rolUsuarioLogueado = this.getAtributoSession(Constantes.SS_ROL);
        
        this.setInicio("Inicio");
        //this.setMiclave("> Cambiar mi clave");
        this.setNavega("Contraseña Navega Plus");
        this.setNaf("Contraseña NAF");
        this.setAmsys("Contraeña Amsys");
        this.setBlnActivoRevertir(true);
        
        if(usuarioLogueado!=null && rolUsuarioLogueado!=null){
            if(rolUsuarioLogueado.equals(Constantes.ROL_ADMIN) || 
                    rolUsuarioLogueado.equals(Constantes.ROL_TIER2)
                    ){
                this.setBlnActivoRevertir(false);
            }         
        } 
        else{
            CerrarSesion ses = new CerrarSesion();
            ses.destroy();
        }        
    }
    
    public Boolean getBlnActivoRevertir() {
        return blnActivoRevertir;
    }

    public void setBlnActivoRevertir(Boolean blnActivoRevertir) {
        this.blnActivoRevertir = blnActivoRevertir;
    }
    
    
    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }
    
       public String getMiclave() {
        return miclave;
    }

    public void setMiclave(String miclave) {
        this.miclave = miclave;
    }

    public String getNavega() {
        return navega;
    }

    public void setNavega(String navega) {
        this.navega = navega;
    }

    public String getNaf() {
        return naf;
    }

    public void setNaf(String naf) {
        this.naf = naf;
    }

    public String getAmsys() {
        return amsys;
    }

    public void setAmsys(String amsys) {
        this.amsys = amsys;
    }

    
}
