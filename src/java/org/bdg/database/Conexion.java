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

/**
 *
 * @author jarevalo
 */
public class Conexion {
    
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
    
}
