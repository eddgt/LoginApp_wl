package com.bdg.database;

import com.bdg.base.Constantes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jarevalo
 */
public class ConexionLocal {
    
    public static Connection getConexionLocal(){
        try{
            Connection conn;
            String driverClassName=Constantes.DB_OJDBC_CLASSNAME;
            String driverUrl="jdbc:oracle:thin:@"+Constantes.DB_HOST_TEST+":"+Constantes.DB_SID_TEST;
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(driverUrl, Constantes.DB_USER_TEST, Constantes.DB_PASS_TEST);  
            System.out.println("conexion exitosa");
            return conn;
        }catch(SQLException e){
            System.out.println("Error de SQL al conectar" + e.getMessage().toString());
        }catch(ClassNotFoundException e){
            System.out.println("Error en el driver de conexion SQL " + e.getMessage().toString());
        }
        return null;
    }
    
}
