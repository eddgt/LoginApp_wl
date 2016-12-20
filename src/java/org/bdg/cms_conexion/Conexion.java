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

public class Conexion {
    static Connection conex= null;
    public Connection getConexion()
    {    
        try
        {
            String driverClassName="oracle.jdbc.driver.OracleDriver";
            //Desarrollo
            String driverUrl="jdbc:oracle:thin:@172.30.13.65:1521:COMISIONES";            
            //Produccion
            //String driverUrl="jdbc:oracle:thin:@172.30.4.252:1521:COMISIONES";
            
            Class.forName(driverClassName);
            // Desarrollo
             conex = DriverManager.getConnection(driverUrl,"COMISIONGT", "COMISIONGT1");               
            // Producción
            //conex = DriverManager.getConnection(driverUrl,"COMISIONGT", "comisiongt$1");   
            System.out.println("Conexion exitosa");
            
        } catch(Exception e) {
            System.out.print("no se logro conección");
            e.printStackTrace();
        } 
        return conex;
    }
    public static void CloseConnection(Connection con)
    {
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
