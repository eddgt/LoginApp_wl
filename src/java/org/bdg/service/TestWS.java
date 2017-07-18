/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.service;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.bdg.base.Constantes;
import org.bdg.database.Login;
import org.bdg.session.BaseSession;

/**
 *
 * @author oulloa
 */
@WebService(serviceName = "TestWS")
public class TestWS extends BaseSession {

    /**
     * This is a sample web service operation
     *
     * @param arg01
     * @param arg02
     * @return    
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "usr") String arg01, @WebParam(name = "pwd") String arg02 ) {
        if ("Test".equals(arg01)) {
            //redireccionar("/faces/ReporteOperaciones.xhtml");
            return "Hello User " + arg01;
        } else {
            //redireccionar("/");
            return "Hello unauthorized user " + arg01;
        }        

    }

    @Override
    public void redireccionar(String url) {
        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect(url);
        } catch (IOException ex) {
            System.out.println("" + ex.getMessage());
        }
    }

    public String Login(String usr, String psw) {

        try {

            Login login = new Login();
            String alias = usr.split("@")[0];
            String loginRequest = login.requestLogIn(usr, psw);

            if ((!loginRequest.equals(Constantes.LOGIN_FAIL) && alias.equals(loginRequest))) {
                String rolRequest = login.requestRol(usr);

                if (rolRequest.equals(Constantes.ROL_VEND)) {
                    this.setAbributoSession(Constantes.SS_USUARIO, alias);
                    this.setAbributoSession(Constantes.SS_ROL, rolRequest);
                    /*ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                    context.redirect(context.getRequestContextPath() + "/faces/bitacoraOperaciones.xhtml");*/
                    System.out.println("alias: "+alias);
                    return "USUARIO_AUTORIZADO_LDAP";
                    

                } else {
                    if (!rolRequest.equals(Constantes.ROL_NO_ROL)) {
                        this.setAbributoSession(Constantes.SS_USUARIO, alias);
                        this.setAbributoSession(Constantes.SS_ROL, rolRequest);
                        //Redireccionar a home.
                        /*ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                        context.redirect(context.getRequestContextPath() + "/faces/bitacoraOperaciones.xhtml");*/
                        System.out.println("alias: "+alias);
                        return "USUARIO_AUTORIZADO_LDAP";

                    } else {
                        //JsfUtil.addErrorMessage(Constantes.MSG_ERORR_LOGIN_NO_ROL);
                        return "USUARIO_NO_AUTORIZADO_LDAP";
                    }
                }
            } else {
                //JsfUtil.addErrorMessage(Constantes.MSG_ERROR_LOGIN_CREDENCIALES);
                return "USUARIO_NO_AUTORIZADO_LDAP";
            }
        } catch (Exception e) {
            //JsfUtil.addErrorMessage(Constantes.MSG_ERROR_GENERICO);
            return "EXCEPCION_LOGIN_LDAP_" + usr;
        }
    }
}
