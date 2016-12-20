/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.database;

import org.bdg.base.Constantes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jarevalo
 */
public class Login {
    
    private final String COL_NAME_LOGIN = "login";
    private final String COL_NAME_ROL = "rol";
    
    public String requestLogIn(String usuario, String contrasena){
        Connection conn = null;
        try{
            conn  = Conexion.getConexion();
            Statement stmt;
            ResultSet rset;
            if(conn != null){
                String query = "SELECT " + Constantes.DB_SCHEME_NAME+"."+Constantes.DB_ORACLE_PACKAGE + "." + Constantes.DB_FUNCTION_LOGIN +
                        "('"+usuario+"','"+contrasena+"') AS "+COL_NAME_LOGIN+" FROM DUAL";
                stmt = conn.createStatement();
                rset = stmt.executeQuery(query);                
                while(rset.next()){
                    Object obj = rset.getNString(COL_NAME_LOGIN);
                    return obj.toString();
                }
            }else{
                
            }
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }finally{
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
    
    public String requestRol(String usuario){
        try{
            Connection conn  = Conexion.getConexion();
            Statement stmt;
            ResultSet rset;
            if(conn != null){
                String query = "SELECT " + Constantes.DB_SCHEME_NAME+"."+Constantes.DB_ORACLE_PACKAGE + "." + Constantes.DB_FUNCTION_ROL_USUARIO +
                        "('"+usuario+"') AS "+COL_NAME_ROL+" FROM DUAL";
                
                stmt = conn.createStatement();
                rset = stmt.executeQuery(query);
                while(rset.next()){
                    Object objResult = rset.getNString(COL_NAME_ROL);
                    System.out.println("ROL: "+objResult.toString());
                    return objResult.toString();                    
                }
            }else{
                
            }
        }catch(Exception e){
            
        }
        return "";
    }
    
}
