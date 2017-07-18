/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.database;

import org.bdg.base.Constantes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author jarevalo
 */
public class Conexion {
    Connection dbConn = null;
    
    
    public static Connection getConexion(){
        
        try{
            Connection conn;
            String driverClassName=Constantes.DB_OJDBC_CLASSNAME;
            String driverUrl="jdbc:oracle:thin:@"+Constantes.DB_HOST+":"+Constantes.DB_SID;
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(driverUrl, Constantes.DB_USER, Constantes.DB_PASS);  
            System.out.println("conexion exitosa");
            return conn;
        }catch(SQLException e){
            System.out.println("Error de SQL al conectar");
        }catch(ClassNotFoundException e){
            System.out.println("Error en el driver de conexion SQL");
        }
        return null;
    }
    
     public  Connection getConexion2() throws SQLException{    
        try {
            Logger LOGGER = Logger.getLogger( Conexion.class.getName() );
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/comisiones");
            Connection conn = ds.getConnection();
            //System.out.println("Connecting to jdbc/comisiones");
            LOGGER.log( Level.FINE, "Connecting to jdbc/comisiones" );
            return conn;
        } catch (NamingException ex) {
            Logger.getLogger(org.bdg.cms_conexion.Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*return null if there is not connection*/
        return null;
    }
    
}
