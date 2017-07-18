/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.session;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 *
 * @author UsuarioBDG
 */
@ManagedBean(name = "cerrarSesion")
@ViewScoped
public class CerrarSesion extends BaseSession implements Serializable {

    private String nombre;
    private String correo;
    private String pass;
    private String codigo;
    private String alias;
    private String departamento;
    private int ldapId;

    private String clave;
   
   /* @PostConstruct
    public void init() {           
        this.destroy();        
    }
    */
    /* public void ingresar() {
        try {

            this.initSession();
            ServiceClient service = new ServiceClient();
            UserInformationResponse resp = service.requestUserInfo(100, alias, pass);
            if (resp != null) {

                if (resp.getErrorMessage() != null && resp.getErrorMessage().equals("success")) {

                    List<AdmUsuario> usuarios = abc.findUsuarioByUsuario(alias);
                    if (usuarios != null && usuarios.size() > 0) {
                        if (usuarios.get(0).getEstado().compareTo(BigInteger.ONE) == 0) {
                             //  accesosPadres = abc.listaAccesosPadresByRol(usuarios.get(0).getAdmRolxusuarioList().get(0)., (short) 2);
                               
                            List<AdmRolxusuario> roles = usuarios.get(0).getAdmRolxusuarioList();
                            for (AdmRolxusuario role : roles) {

                                for (AdmAccesosxrol roleacceso : role.getRolid().getAdmAccesosxrolList()) {
                                    if (roleacceso.getAccesoid().getSitio() == 2) {
                                        this.setAbributoSession("pass", pass);
                                        this.setAbributoSession("usuario", alias);
                                        this.setAbributoSession("codigo", String.valueOf(ldapId));
                                        this.setAbributoSession("nombre", resp.getFirt() + " " + resp.getLast());
                                        this.setAbributoSession("correo", resp.getEmail());
                                        this.setAbributoSession("departamento", resp.getDepartment());
                                        this.setAbributoSession("rol", role.getRolid().getRolid().toString());
                                        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                                        context.redirect(context.getRequestContextPath() + "/index.xhtml");
                                        break;
                                    }
                                }

                            }
                        } else {
                            JsfUtil.addErrorMessage("El usuario se encuentra bloqueado");
                        }

                    }

                } else {
                    JsfUtil.addErrorMessage("Código, Usuario o cantraseña inválida");
                }
            } else {
                JsfUtil.addErrorMessage("Ocurrió un error con el servicio de autenticación");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JsfUtil.addErrorMessage("Ocurrió un error, por favor contactar con el administrador");
        }

    }*/
    
    public void destroy(){
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            
            context.redirect(context.getRequestContextPath() + "/");
            
            System.out.println("CERRANDO SESION ......... ");
            this.destroySession();
            
        } catch (IOException ex) {
            Logger.getLogger(CerrarSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ingresarPrueba() {
        try {
            this.setAbributoSession("usuario", "dsalas");
            this.setAbributoSession("nombre", "usuario de prueba");
            this.setAbributoSession("correo", "usuario@prueba.com");
            this.setAbributoSession("departamento", "depto prueba");
            this.setAbributoSession("rol", "1");
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CerrarSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getLdapId() {
        return ldapId;
    }

    public void setLdapId(int ldapId) {
        this.ldapId = ldapId;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
