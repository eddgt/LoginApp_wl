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
            //conn  = Conexion.getConexionAmsys();
            //conn  = Conexion.getConexionLocal();//old method 11/10/16
            conn = DataSource.getConexionDSLocal();//new method 12/10/16
            //conn = DataSource.getConexionDSAmsys();//new method 12/10/16
            
            Statement stmt;
            ResultSet rset;
            if(conn != null){
                
                //AMBIENTE TEST LOCAL                
                /*
                String query = "SELECT " + Constantes.DB_SCHEMA_NAME_TEST+"."+Constantes.DB_ORACLE_PACKAGE_TEST + "." + Constantes.DB_FUNCTION_LOGIN_TEST +
                        "('"+usuario+"','"+contrasena+"') AS "+COL_NAME_LOGIN+" FROM DUAL";
                
                */
                //AMSYS DEV
                
                String query = "SELECT " + Constantes.DB_SCHEMA_NAME_AMSYS_DEV+"."+Constantes.DB_ORACLE_PACKAGE_AMSYS_DEV + "." + Constantes.DB_FUNCTION_LOGIN_AMSYS_DEV +
                        "('"+usuario+"','"+contrasena+"') AS "+COL_NAME_LOGIN+" FROM DUAL";
                
                stmt = conn.createStatement();
                rset = stmt.executeQuery(query);
                //System.out.println("mi query: "+query);
                while(rset.next()){
                    Object obj = rset.getNString(COL_NAME_LOGIN);
                    return obj.toString();
                    
                }
                conn.close();
                stmt.close();
                rset.close();
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
            
            //Connection conn  = Conexion.getConexionAmsys();
            //Connection conn  = Conexion.getConexionLocal();//old method 11/10/16
            //Connection conn  = DataSource.getConexionDSLocal();//old method 11/10/16
            Connection conn  = DataSource.getConexionDSAmsys();//old method 11/10/16
            Statement stmt;
            ResultSet rset;
            if(conn != null){
                
                //Ambiente Dev Local
                /*
                String query = "SELECT " + Constantes.DB_SCHEMA_NAME_TEST+"."+Constantes.DB_ORACLE_PACKAGE_TEST + "." + Constantes.DB_FUNCTION_ROL_USUARIO_TEST +
                        "('"+usuario+"') AS "+COL_NAME_ROL+" FROM DUAL";
                */
                
                //Ambiente Dev Remoto
                String query = "SELECT " + Constantes.DB_SCHEMA_NAME_AMSYS_DEV+"."+Constantes.DB_ORACLE_PACKAGE_AMSYS_DEV + "." + Constantes.DB_FUNCTION_ROL_USUARIO_AMSYS_DEV +
                        "('"+usuario+"') AS "+COL_NAME_ROL+" FROM DUAL";
                
                //Ambiente Produccion 
                /*String query = "SELECT " + Constantes.DB_SCHEMA_NAME_AMSYS_PRO+"."+Constantes.DB_ORACLE_PACKAGE_AMSYS_PRO + "." + Constantes.DB_FUNCTION_ROL_USUARIO_AMSYS_PRO +
                        "('"+usuario+"') AS "+COL_NAME_ROL+" FROM DUAL";
                */
                        
                //System.out.println("PKG: "+query);
                stmt = conn.createStatement();
                rset = stmt.executeQuery(query);
                while(rset.next()){
                    Object objResult = rset.getNString(COL_NAME_ROL);
                    return objResult.toString();
                    
                }
                conn.close();
                stmt.close();
                rset.close();
            }else{
                
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return "";
    }
    
}