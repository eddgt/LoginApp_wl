/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bdg.dto;

import com.bdg.base.Constantes;
import com.bdg.database.Conexion;
import com.bdg.database.Login;
import com.bdg.vista.OrderBean;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
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
            conn = Conexion.getConexionLocal();            
            if(conn != null){
                
                //define my pkg
                String pkgProc = Constantes.DB_SCHEMA_NAME_TEST+"."+Constantes.DB_ORACLE_PACKAGE_TEST + "." + Constantes.DB_PROCESS_MAIL_TEST;                
                
                String query = ("{CALL "+pkgProc+"('"+mUsrTo+"','"+mPlat+"','"+mUsrApp+"','"+mUpw+"','"+mUsrAffected+"')}");
                // Prepare to call the stored procedure 
                Statement stmt = conn.createStatement(); 
                
                boolean execute = stmt.execute(query); //especifico para Updates
                System.out.println("CALL query: "+query);
                
                // Close the statement
                stmt.close();
                
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
