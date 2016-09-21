package com.bdg.database;

import com.bdg.base.Constantes;
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
            //conn  = Conexion.getConexion();
            conn  = Conexion.getConexionLocal();
            //conn  = Conexion.getConexionAmsys();
            Statement stmt;
            ResultSet rset;
            if(conn != null){
                //DB TEST
                String query = "SELECT " + Constantes.DB_SCHEMA_NAME_TEST+"."+Constantes.DB_ORACLE_PACKAGE_TEST + "." + Constantes.DB_FUNCTION_LOGIN_TEST +
                        "('"+usuario+"','"+contrasena+"') AS "+COL_NAME_LOGIN+" FROM DUAL";
                
                //DB AMSYS
                /*String query = "SELECT " + Constantes.DB_SCHEMA_NAME_AMSYS+"."+Constantes.DB_ORACLE_PACKAGE_AMSYS + "." + Constantes.DB_FUNCTION_LOGIN_AMSYS +
                        "('"+usuario+"','"+contrasena+"') AS "+COL_NAME_LOGIN+" FROM DUAL";*/
                
                stmt = conn.createStatement();
                rset = stmt.executeQuery(query);
                System.out.println("mi query: "+query);
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
            Connection conn  = Conexion.getConexionLocal();
            //Connection conn  = Conexion.getConexionAmsys();
            Statement stmt;
            ResultSet rset;
            if(conn != null){
                String query = "SELECT " + Constantes.DB_SCHEMA_NAME_TEST+"."+Constantes.DB_ORACLE_PACKAGE_TEST + "." + Constantes.DB_FUNCTION_ROL_USUARIO_TEST +
                        "('"+usuario+"') AS "+COL_NAME_ROL+" FROM DUAL";
                
                stmt = conn.createStatement();
                rset = stmt.executeQuery(query);
                while(rset.next()){
                    Object objResult = rset.getNString(COL_NAME_ROL);
                    return objResult.toString();
                    
                }
            }else{
                
            }
        }catch(Exception e){
            System.out.println(e.getMessage().toString());
        }
        return "";
    }
    
}