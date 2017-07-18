/*
 * Clase base para la administración de Sesión de usuario
 * Administración de sesión basada en Faces Context 2.0
 * Sistemas Web Sobre Java Server Faces 2.0
 *
 */
package org.bdg.session;


import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class BaseSession {
     
    public void initSession() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        } catch (Exception ex) {
            System.out.println("Error Finalizando Sesión" + ex.getMessage());
        }
    }

    public HttpSession getSession() {
        try {
            return ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false));
        } catch (Exception ex) {
            System.out.println("Error Obtenieno Sesión" + ex.getMessage());
            return null;
        }
    }

    public String getSessionID() {
        try {
            return ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getId();
        } catch (Exception ex) {
            System.out.println("Error Obteniendo ID de Sesión" + ex.getMessage());
            return null;
        }
    }

    public String getAtributoSession(String name) {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            //System.out.println(fc);
            ExternalContext ec = fc.getExternalContext();
            //System.out.println(ec);
            HttpSession hs = (HttpSession) ec.getSession(false);
            //System.out.println(hs);
            fc.responseComplete();
            return (String) hs.getAttribute(name);
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("-String- Error Agregando atributo a la Sesión" + ex.getMessage());
            return null;
        }
    }

    public Object getAtributoSessionObject(String name) {
        try {
            //return  ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute(name);
            FacesContext fc = FacesContext.getCurrentInstance();
            //System.out.println(fc);
            ExternalContext ec = fc.getExternalContext();
            //System.out.println(ec);
            HttpSession hs = (HttpSession) ec.getSession(false);
            //System.out.println(hs);
            return hs.getAttribute(name);
        } catch (Exception ex) {
            System.out.println("-Object- Error Agregando atributo a la Sesión" + ex.getMessage());
            return null;
        }
    }

    public void setAbributoSession(String name, String value) {
        try {
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).setAttribute(name, value);            
        } catch (Exception ex) {
            System.out.println("-void set- Error Cargando atributo a la Sesión" + ex.getMessage());
        }
    }

      public void setAbributoSession(String name, Object value) {
        try {
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).setAttribute(name, value);
        } catch (Exception ex) {
            System.out.println("-void set- obj Error Cargando atributo a la Sesión" + ex.getMessage());
        }
    }

    public void destroySession() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        } catch (Exception ex) {
            System.out.println("Error Finalizando Sesión" );//+ ex.getMessage());
        }
    }

    public void redireccionar(String url) {
        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect(url);
        } catch (IOException ex) {
            System.out.println("" + ex.getStackTrace());
        }
    }

    public void validarSesion(String usuario) throws IOException {
        
        String aux_usuario = usuario != null ? usuario : "";
        if (aux_usuario.length() == 0) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/");
            //return;
        }
    }
    
}
