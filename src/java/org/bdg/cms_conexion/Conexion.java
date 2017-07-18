/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_conexion;

/**
 *
 * @author Daniel Mendez
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.bdg.base.Constantes;

public class Conexion {
    static Connection conex= null;
    static Connection conex2= null;
    static Connection dbConn = null;
    
    public Connection getConexion(){    
        try
        {
            String driverClassName="oracle.jdbc.driver.OracleDriver";
            //Desarrollo
            String driverUrl="jdbc:oracle:thin:@"+Constantes.DB_HOST+":"+Constantes.DB_SID;
            //Produccion
            //String driverUrl="jdbc:oracle:thin:@172.30.4.252:1521:COMISIONES";
            
            Class.forName(driverClassName);
            // Desarrollo
             conex = DriverManager.getConnection(driverUrl,Constantes.DB_USER, Constantes.DB_PASS);
            // Producción
            //conex = DriverManager.getConnection(driverUrl,"COMISIONGT", "comisiongt$1");   
            System.out.println("Conexion exitosa HOST65");
            
        } catch(Exception e) {
            System.out.print("no se logro conección");
            e.getMessage();
        } 
        return conex;
    }
       
    
    public static void CloseConnection(Connection con)
    {
        try {
            con.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    
    public Connection getConexionOld(){    
        try{
            System.out.println("Conexion exitosa HOST17 inic");
            String driverClassName="oracle.jdbc.driver.OracleDriver";
            //Desarrollo
            String driverUrl="jdbc:oracle:thin:@"+Constantes.DB_HOST2+":"+Constantes.DB_SID;
            //Produccion
            //String driverUrl="jdbc:oracle:thin:@172.30.4.252:1521:COMISIONES";
            
            Class.forName(driverClassName);
            // Desarrollo
             conex2 = DriverManager.getConnection(driverUrl,Constantes.DB_USER2, Constantes.DB_PASS2);
            // Producción
            //conex = DriverManager.getConnection(driverUrl,"COMISIONGT", "comisiongt$1");   
            System.out.println("Conexion exitosa HOST17");
            
        } catch(ClassNotFoundException e) {
            System.out.print("no se logro conección");
            e.getMessage();
        } catch (SQLException e) {
            System.out.print("no se logro conección");
            e.getMessage();
        } 
        return conex2;
    }
    
    
    public Connection getConexion2() throws SQLException{    
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/comisiones");
            Connection conn = ds.getConnection();
            System.out.println("Conectando con jdbc/comisiones");
            return conn;
        } catch (NamingException ex) {
            Logger.getLogger(org.bdg.cms_conexion.Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*return null if there is not connection*/
        return null;
    }
}
