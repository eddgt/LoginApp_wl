/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bdg.dto;

import com.bdg.base.Constantes;
import com.bdg.database.DataSource;
import com.bdg.database.Login;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author oulloa
 */
@ManagedBean(name = "mail")
@SessionScoped
public class Mail {
    private String to;
    private String cc;
    private String from;
    private String subject;
    private String message;
    private String userApp;
    private String userUpdated;
    private String pData;

    public String getUserApp() {
        return userApp;
    }

    public void setUserApp(String userApp) {
        this.userApp = userApp;
    }

    public String getUserUpdated() {
        return userUpdated;
    }

    public void setUserUpdated(String userUpdated) {
        this.userUpdated = userUpdated;
    }

    public String getpData() {
        return pData;
    }

    public void setpData(String pData) {
        this.pData = pData;
    }
    

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String Send(String mUsrTo, String mPlat,String mUsrApp, String mUpw, String mUsrAffected){
        Connection conn = null;
        try{            
            //conn = Conexion.getConexionLocal();//old method 11/10/2016            
            //conn = DataSource.getConexionDSLocal();//new method 12/10/2016
            //conn = DataSource.getConexionDSAmsys();//new method 12/10/2016
            conn = DataSource.getConexionDSAmsys();//new method 12/10/2016
            if(conn != null){
                
                //define my pkg dev local
                //String pkgProc = Constantes.DB_SCHEMA_NAME_TEST+"."+Constantes.DB_ORACLE_PACKAGE_TEST + "." + Constantes.DB_PROCESS_MAIL_TEST;
                
                //define my pkg dev remoto
                String pkgProc = Constantes.DB_SCHEMA_NAME_AMSYS_DEV+"."+Constantes.DB_ORACLE_PACKAGE_AMSYS_DEV + "." + Constantes.DB_PROCESS_MAIL_AMSYS_DEV;
                
                //define my pkg Produccion
                //String pkgProc = Constantes.DB_SCHEMA_NAME_AMSYS_PRO+"."+Constantes.DB_ORACLE_PACKAGE_AMSYS_PRO + "." + Constantes.DB_PROCESS_MAIL_AMSYS_PRO;
                
                String query = ("{CALL "+pkgProc+"('"+mUsrTo+"','"+mPlat+"','"+mUsrApp+"','"+mUpw+"','"+mUsrAffected+"')}");
                // Prepare to call the stored procedure 
                Statement stmt = conn.createStatement(); 
                
                boolean execute = stmt.execute(query); //especifico para Updates
                //System.out.println("PKG query: "+query);
                System.out.println("PKG query executing ");
                
                //close connection
                conn.close();                
                // Close the statement
                stmt.close();
                
                System.out.println("Mail Conn closed");
                
            }else{
                
            }            
        }
        catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{
            if(conn!=null){
                try {
                    conn.close();
                    System.out.println("Mail Conn closed");
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return "";
    }
    
    public String SendMailNavega(String mUsrTo, String mPlat,String mUsrApp, String mUpw, String mUsrAffected){
        Connection conn = null;
        try{            
            //conn = Conexion.getConexionLocal();//old method 11/10/2016            
            //conn = DataSource.getConexionDSLocal();//new method 12/10/2016
            //conn = DataSource.getConexionDSAmsys();//new method 12/10/2016
            conn = DataSource.getConexionDSAmsys();//new method 12/10/2016
            if(conn != null){
                
                //define my pkg dev local
                //String pkgProc = Constantes.DB_SCHEMA_NAME_TEST+"."+Constantes.DB_ORACLE_PACKAGE_TEST + "." + Constantes.DB_PROCESS_MAIL_TEST;
                
                //define my pkg dev remoto
                //String pkgProc = Constantes.DB_SCHEMA_NAME_AMSYS_DEV+"."+Constantes.DB_ORACLE_PACKAGE_AMSYS_DEV + "." + Constantes.DB_PROCESS_MAIL_AMSYS_DEV;
                
                //define my pkg Produccion
                //String pkgProc = Constantes.DB_SCHEMA_NAME_AMSYS_PRO+"."+Constantes.DB_ORACLE_PACKAGE_AMSYS_PRO + "." + Constantes.DB_PROCESS_MAIL_AMSYS_PRO;
                                
                String query = ("CALL APP_PR_PKG.send_Mail_Html('"+mUsrTo+"',  'AppNoReply@tigo.com.gt','Notificacion_credenciales','',  '<!DOCTYPE html>  <html>  <head><title>Notificacion Modificacion de Credenciales</title></head><body><br></br><p><a>Se ha desbloqueado el usuario: <font color=\"blue\"> "+mUsrAffected+" </font>y deberá ingresar al sistema con credenciales de dominio por ser usuario de tipo LDAP</b> </a></p> </body> </html>','mail.tigo.com.gt',25)");
                
                //System.out.println("PKG query: "+query);
                System.out.println("PKG query executing ");
                
                // Prepare to call the stored procedure 
                Statement stmt = conn.createStatement(); 
                
                boolean execute = stmt.execute(query); //especifico para Updates                
                
                //close connection
                conn.close();                
                // Close the statement
                stmt.close();                
                
                System.out.println("Mail Conn closed");
                
            }else{
                
            }            
        }
        catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{
            if(conn!=null){
                try {
                    conn.close();
                    System.out.println("Mail Conn closed");
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return "";
    }
    
    
    public void onSend(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Notification email sent");
        FacesContext.getCurrentInstance().addMessage(null, msg);        
    }  
}
