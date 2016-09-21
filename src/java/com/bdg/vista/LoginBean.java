/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bdg.vista;
import com.bdg.base.Constantes;
import com.bdg.database.Login;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import com.bdg.sesion.BaseSession;
import com.bdg.utils.JsfUtil;

/**
 *
 * @author oulloa
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends BaseSession{
    private String usuario;
    private String contrasena;
    
    public void logIn(){
        try {
            Login login = new Login();
            String alias = usuario.split("@")[0];
            System.out.print("\nalias: " +alias.toString());
            String loginRequest = login.requestLogIn(usuario, contrasena);
            System.out.print("\nloginRequest: " +loginRequest.toString());
            if(!loginRequest.equals(Constantes.LOGIN_FAIL) && alias.equals(loginRequest)){
                String rolRequest = login.requestRol(usuario);
                if(!rolRequest.equals(Constantes.ROL_NO_ROL)){
                    this.setAbributoSession(Constantes.SS_USUARIO, alias);
                    this.setAbributoSession(Constantes.SS_ROL, rolRequest);
                    //Redireccionar a home.
                    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                    context.redirect(context.getRequestContextPath() + "/faces/welcomePrimefaces.xhtml");                                       
                }else{
                    JsfUtil.addErrorMessage(Constantes.MSG_ERORR_LOGIN_NO_ROL);
                }
            }else{
                JsfUtil.addErrorMessage(Constantes.MSG_ERROR_LOGIN_CREDENCIALES);
            }
        }catch(Exception e){
           JsfUtil.addErrorMessage(Constantes.MSG_ERROR_GENERICO);
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
}
