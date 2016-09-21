package com.bdg.database;

import com.bdg.base.Constantes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jarevalo
 */
public class ConexionSqlSvr {
    
    public static Connection getConexion(){
        try{
            Connection conn;
            String driverClassName=Constantes.DB_OJDBC_CLASSNAME_SQLSRVR;
            String driverUrl="jdbc:sqlserver://"+Constantes.DB_HOST_SQLSRVR+"\\"+Constantes.DB_INSTANCE_NAME_SQLSRVR+";databaseName="+Constantes.DB_NAME_SQLSRVR+"";
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(driverUrl, Constantes.DB_USER_SQLSRVR, Constantes.DB_PASS_SQLSRVR);  
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