package org.bdg.cms_vista;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.bdg.base.Constantes;
import org.bdg.database.Login;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jdk.nashorn.internal.runtime.Context;
import org.bdg.cms_buc.Query_C;
import org.bdg.cms_buc.Query_Vendedor;
import org.bdg.cms_conexion.Conexion;
import org.bdg.session.BaseSession;
import org.bdg.utils.JsfUtil;

/**
 *
 * @author oulloa
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends BaseSession implements Serializable {

    private String usuario;
    private String contrasena;
    private boolean sesionValida;
    private ArrayList<String> errorMessages;

    public void logIn() {
        try {
            Login login = new Login();
            String alias = usuario.split("@")[0];
            String loginRequest = login.requestLogIn(usuario, contrasena);
            //String ss = login.requestRol(usuario);

            if ((!loginRequest.equals(Constantes.LOGIN_FAIL) && alias.equals(loginRequest)) //|| usuario.equals("mcaballeros") || usuario.equals("bquinonez") || usuario.equals("olgarcia")
                    ) {
                String rolRequest = login.requestRol(usuario);

                if (rolRequest.equals(Constantes.ROL_VEND)) {
                    //this.setAtributosVendedor(alias,null);
                    this.setAbributoSession(Constantes.SS_USUARIO, alias);
                    this.setAbributoSession(Constantes.SS_ROL, rolRequest);
                    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                    //context.redirect(context.getRequestContextPath() + "/faces/ventasVendedor.xhtml");
                    context.redirect(context.getRequestContextPath() + "/faces/ReporteRenovaciones.xhtml");
                    this.setSesionValida(true);
                } else {
                    if (!rolRequest.equals(Constantes.ROL_NO_ROL)) {
                        this.setAbributoSession(Constantes.SS_USUARIO, alias);
                        this.setAbributoSession(Constantes.SS_ROL, rolRequest);
                        //Redireccionar a home.
                        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                        context.redirect(context.getRequestContextPath() + "/faces/ReporteRenovaciones.xhtml");
                        this.setSesionValida(true);
                    } else {
                        JsfUtil.addErrorMessage(Constantes.MSG_ERORR_LOGIN_NO_ROL);
                        this.setSesionValida(false);
                    }
                }

            } else {
                JsfUtil.addErrorMessage(Constantes.MSG_ERROR_LOGIN_CREDENCIALES);
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(Constantes.MSG_ERROR_GENERICO);
        }
    }

    public void setAtributosVendedor(String usuario, String nombreVend) {
        Connection conex = null;
        try {

            Conexion conec = new Conexion();
            conex = conec.getConexion2();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            String query = Query_Vendedor.obtenerInformacionAsesor(usuario, nombreVend);
            rs1 = st1.executeQuery(query);

            while (rs1.next()) {
                this.setAbributoSession(Constantes.ID_VEND, rs1.getString("NUMDOCUMENTO"));
                this.setAbributoSession(Constantes.VEND_NAME, rs1.getString("NOMPERSONA"));
                this.setAbributoSession(Constantes.VEND_COORDINADOR, rs1.getString("NOM_COORDINADOR"));
                this.setAbributoSession(Constantes.VEND_GERENTE, rs1.getString("NOM_GERENTE"));
                this.setAbributoSession(Constantes.SS_USUARIO, rs1.getString("USUARIO"));
            }

            this.setAbributoSession(Constantes.SS_ROL, Constantes.ROL_VEND);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conex.close();
            } catch (SQLException ex) {

            }
        }

    }

    public ArrayList<String> getErrorMessages() {
        return errorMessages;
    }

    public LoginBean() {
        this.errorMessages = new ArrayList<String>();
        if (this.usuario != null && this.contrasena != null) {
            this.logIn();
        }
    }

    public void showPendingMessages() throws IOException {

        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        for (String message : this.errorMessages) {
            JsfUtil.addErrorMessage(message);
        }

        System.out.println(" Usuario " + this.usuario);
        System.out.println(" resquestParams " + requestParams);
        //System.out.println(" size " + requestParams.entrySet().size());
        //System.out.println(" toString " + requestParams.entrySet().toString());

        /*
        Iterator entries = requestParams.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println("Key = " + key + ", Value = " + value);
        }*/

        
        //System.out.println(" user formParameter " + requestParams.getOrDefault("user", usuario));
        //System.out.println(" values " + requestParams.values());

        if (this.usuario != null && this.contrasena != null) {
            this.logIn();
        }
        this.errorMessages = new ArrayList<String>();
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

    public boolean isSesionValida() {
        return sesionValida;
    }

    public void setSesionValida(boolean sesionValida) {
        this.sesionValida = sesionValida;
    }

}
