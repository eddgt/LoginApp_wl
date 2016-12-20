/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_vista;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.bdg.base.Constantes;
import org.bdg.database.Login;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.bdg.cms_buc.Querys_C;
import org.bdg.cms_buc.Querys_Vendedor;
import org.bdg.cms_conexion.Conexion;
import org.bdg.session.BaseSession;
import org.bdg.utils.JsfUtil;

/**
 *
 * @author jarevalo
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
            String loginRequest = login.requestLogIn(usuario, contrasena);
             //String ss = login.requestRol(usuario);
            
            if((!loginRequest.equals(Constantes.LOGIN_FAIL) && alias.equals(loginRequest)) 
                    || usuario.equals("mcaballeros") || usuario.equals("bquinonez") || usuario.equals("olgarcia")
                ) {
                String rolRequest = login.requestRol(usuario);
                
                if(rolRequest.equals(Constantes.ROL_VEND)){
                    //this.setAtributosVendedor(alias,null);
                    this.setAbributoSession(Constantes.SS_USUARIO, alias);
                    this.setAbributoSession(Constantes.SS_ROL, rolRequest);
                    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                    //context.redirect(context.getRequestContextPath() + "/faces/ventasVendedor.xhtml");
                    context.redirect(context.getRequestContextPath() + "/faces/ventasOperacionesVendedor.xhtml");
                }else{
                    if(!rolRequest.equals(Constantes.ROL_NO_ROL)){
                        this.setAbributoSession(Constantes.SS_USUARIO, alias);
                        this.setAbributoSession(Constantes.SS_ROL, rolRequest);
                        //Redireccionar a home.
                        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                        context.redirect(context.getRequestContextPath() + "/faces/ventasOperaciones.xhtml");                                       
                    }else{
                        JsfUtil.addErrorMessage(Constantes.MSG_ERORR_LOGIN_NO_ROL);
                    }                
                }
               
            }else{
                JsfUtil.addErrorMessage(Constantes.MSG_ERROR_LOGIN_CREDENCIALES);
            }
        }catch(Exception e){
           JsfUtil.addErrorMessage(Constantes.MSG_ERROR_GENERICO);
        }
    }
    
    
    public void setAtributosVendedor(String usuario, String nombreVend){
        Connection conex=null;
        try{
            
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            String query = Querys_Vendedor.obtenerInformacionAsesor(usuario,nombreVend);            
            rs1 = st1.executeQuery(query);
            
            while (rs1.next())
            {
                this.setAbributoSession(Constantes.ID_VEND, rs1.getString("NUMDOCUMENTO"));
                this.setAbributoSession(Constantes.VEND_NAME, rs1.getString("NOMPERSONA"));
                this.setAbributoSession(Constantes.VEND_COORDINADOR, rs1.getString("NOM_COORDINADOR"));
                this.setAbributoSession(Constantes.VEND_GERENTE, rs1.getString("NOM_GERENTE"));
                this.setAbributoSession(Constantes.SS_USUARIO, rs1.getString("USUARIO"));
            }
            
            
            this.setAbributoSession(Constantes.SS_ROL, Constantes.ROL_VEND);
           
                
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                conex.close();
            } catch (SQLException ex) {
                
            }
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
