package com.bdg.database;

/**
 *
 * @author oulloa
 */
import com.bdg.base.Constantes;
import java.sql.*;
import java.util.*;
import javax.naming.*;

public class DataSource {
    
    public static Connection getConexionDSLocal() throws NamingException{
        
        Context ctx = null;
        Hashtable ht = new Hashtable();
        ht.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");        
        ht.put(Context.PROVIDER_URL,Constantes.WLAPP_LINK);
        Connection conn = null;
        //Statement stmt = null;
        //ResultSet rs = null;
        
        try {
            ctx = new InitialContext(ht);
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup ("ora-local");
            conn = ds.getConnection();            
            System.out.println(" Oracle Local DS Connection ok");
            return conn;
            
     }
      catch ( SQLException e) {
        // a failure occurred
        System.out.println(" Ocurrio una Excepcion: "+e.getMessage());
      }

        return null;
    }
   
    public static Connection getConexionDSNaf() throws NamingException{
        
        Context ctx = null;
        Hashtable ht = new Hashtable();
        ht.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
        ht.put(Context.PROVIDER_URL,Constantes.WLAPP_LINK);
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
        ht.put(Context.PROVIDER_URL,Constantes.WLAPP_LINK);
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
        ht.put(Context.PROVIDER_URL,Constantes.WLAPP_LINK);
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
        ht.put(Context.PROVIDER_URL,Constantes.WLAPP_LINK);
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
