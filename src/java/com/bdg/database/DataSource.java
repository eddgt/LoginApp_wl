package com.bdg.database;

/**
 *
 * @author oulloa
 */
import java.sql.*;
import java.util.*;
import javax.naming.*;

public class DataSource {
    
    public static Connection getConexionDSLocal() throws NamingException{
        
        Context ctx = null;
        Hashtable ht = new Hashtable();
        ht.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
        ht.put(Context.PROVIDER_URL,"t3://172.22.11.79:7001/ResetService/");
        Connection conn = null;
        //Statement stmt = null;
        //ResultSet rs = null;
        
        try {
            ctx = new InitialContext(ht);
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup ("ora-local");
            conn = ds.getConnection();            
            System.out.println(" Oracle Local DS Connection ok");
            return conn;
            
            /*
            // You can now use the conn object to create 
            //  Statements and retrieve result sets:
            
            stmt = conn.createStatement();
            stmt.execute("select * from test.tb_usuarios");
            rs = stmt.getResultSet();             
            
             while (rs.next()) {
                //get first result
                 System.out.println(rs.getString(1)+" "+rs.getString(2))  ;//coloumn 1
                 }

            
            //Close JDBC objects as soon as possible
            stmt.close();
            stmt=null;
            
            conn.close();
            conn=null;
            */
            
     }
      catch ( SQLException e) {
        // a failure occurred
        System.out.println(" Ocurrio una Excepcion: "+e.getMessage());
      }
    /*finally {
            try { 
                ctx.close(); 
                } catch (Exception e) {
                    System.out.println(" Ocurrio una Excepcion al cerrar ctx: "+e.getMessage());
                }
                  try { 
                      if (rs != null) rs.close(); 
                            } catch (Exception e) {  
                                System.out.println(" Ocurrio una Excepcion al cerrar rs: "+e.getMessage());
                            }
                  try { 
                      if (stmt != null) stmt.close();
                            } catch (Exception e) {
                                System.out.println(" Ocurrio una Excepcion al cerrar stmt: "+e.getMessage());
                            }
                  try { 
                      if (conn != null) conn.close(); 
                      } catch (Exception e) {
                          System.out.println(" Ocurrio una Excepcion al cerrar conexion: "+e.getMessage());
                      }
    }*/
        return null;
    }
   
    public static Connection getConexionDSNaf() throws NamingException{
        
        Context ctx = null;
        Hashtable ht = new Hashtable();
        ht.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
        //ht.put(Context.PROVIDER_URL,"t3://172.22.11.79:7001/ResetService/");
        ht.put(Context.PROVIDER_URL,"t3://172.24.240.20:8080/ResetService/");
        Connection conn = null;
        
        try {
            ctx = new InitialContext(ht);
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup ("nafdev-ds");
            conn = ds.getConnection();            
            System.out.println(" NAF DS Connection ok");
            return conn;
     }
      catch ( SQLException e) {
        // a failure occurred
        System.out.println(" Ocurrio una Excepcion: "+e.getMessage());
      }
        return null;
    }
    
     public static Connection getConexionDSAmsys() throws NamingException{
        
        Context ctx = null;
        Hashtable ht = new Hashtable();
        ht.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
        //ht.put(Context.PROVIDER_URL,"t3://172.22.11.79:7001/ResetService/");
        ht.put(Context.PROVIDER_URL,"t3://172.24.240.20:8080/ResetService/");
        Connection conn = null;
        
        try {
            ctx = new InitialContext(ht);
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup ("amsysdev-ds");
            conn = ds.getConnection();            
            System.out.println(" AMSYS DS Connection ok");
            return conn;
     }
      catch ( SQLException e) {
        // a failure occurred
        System.out.println(" Ocurrio una Excepcion: "+e.getMessage());
      }
        return null;
    }

     public static Connection getConexionDSAmsys2() throws NamingException{
        
        Context ctx = null;
        Hashtable ht = new Hashtable();
        ht.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
        //ht.put(Context.PROVIDER_URL,"t3://172.22.11.79:7001/ResetService/");
        ht.put(Context.PROVIDER_URL,"t3://172.24.240.20:8080/ResetService/");
        Connection conn = null;
        
        try {
            ctx = new InitialContext(ht);
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup ("amsyspro-ds");
            conn = ds.getConnection();            
            System.out.println(" AMSYS DS Connection ok");
            return conn;
     }
      catch ( SQLException e) {
        // a failure occurred
        System.out.println(" Ocurrio una Excepcion: "+e.getMessage());
      }
        return null;
    }     
     
     
    public static Connection getConexionDSNavega() throws NamingException{
        
        Context ctx = null;
        Hashtable ht = new Hashtable();
        ht.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
        //ht.put(Context.PROVIDER_URL,"t3://172.22.11.79:7001/ResetService/");
        ht.put(Context.PROVIDER_URL,"t3://172.24.240.20:8080/ResetService/");
        Connection conn = null;
        
        try {
            ctx = new InitialContext(ht);
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup ("navegadev-ds");
            conn = ds.getConnection();            
            System.out.println(" NAVEGA DS Connection ok");
            return conn;
     }
      catch ( SQLException e) {
        // a failure occurred
        System.out.println(" Ocurrio una Excepcion: "+e.getMessage());
      }
        return null;
    }    
}
