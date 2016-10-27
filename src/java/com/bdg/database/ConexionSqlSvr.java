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
            //String driverUrl="jdbc:sqlserver://"+Constantes.DB_HOST_SQLSRVR_LOCAL+"\\"+Constantes.DB_INSTANCE_NAME_SQLSRVR_LOCAL+";databaseName="+Constantes.DB_NAME_SQLSRVR_LOCAL+"";
            String driverUrl="jdbc:sqlserver://"+Constantes.DB_HOST_SQLSRVR_PRO+";databaseName="+Constantes.DB_NAME_SQLSRVR_PRO+"";
            Class.forName(driverClassName);
            //conn = DriverManager.getConnection(driverUrl, Constantes.DB_USER_SQLSRVR_LOCAL, Constantes.DB_PASS_SQLSRVR_LOCAL);  
            conn = DriverManager.getConnection(driverUrl, Constantes.DB_USER_SQLSRVR_PRO, Constantes.DB_PASS_SQLSRVR_PRO);
            System.out.println(" Navega+ conexion exitosa");
            return conn;
        }catch(SQLException e){
            //System.out.println("my string jdbc:sqlserver://"+Constantes.DB_HOST_SQLSRVR_LOCAL+"\\"+Constantes.DB_INSTANCE_NAME_SQLSRVR_LOCAL+";databaseName="+Constantes.DB_NAME_SQLSRVR_LOCAL+"");
            //System.out.println("STRING jdbc:sqlserver://"+Constantes.DB_HOST_SQLSRVR_DEV+";databaseName="+Constantes.DB_NAME_SQLSRVR_DEV+" "+Constantes.DB_USER_SQLSRVR_DEV + " "+Constantes.DB_PASS_SQLSRVR_DEV);
            System.out.println(" Navega+ Error de SQL al conectar " + e.getMessage());
            System.out.println(" Navega+ Error de SQL al conectar, Causa: " + e.getCause().toString());
        }catch(ClassNotFoundException e){
            System.out.println(" Navega+ Error en el driver de conexion SQL " + e.getMessage());
        }
        
        return null;
    }
    
    public static Connection getConexionDev(){
        try{
            Connection conn;
            String driverClassName=Constantes.DB_OJDBC_CLASSNAME_SQLSRVR;
            //String driverUrl="jdbc:sqlserver://"+Constantes.DB_HOST_SQLSRVR_LOCAL+"\\"+Constantes.DB_INSTANCE_NAME_SQLSRVR_LOCAL+";databaseName="+Constantes.DB_NAME_SQLSRVR_LOCAL+"";
            String driverUrl="jdbc:sqlserver://"+Constantes.DB_HOST_SQLSRVR_DEV+";databaseName="+Constantes.DB_NAME_SQLSRVR_DEV+"";
            Class.forName(driverClassName);
            //conn = DriverManager.getConnection(driverUrl, Constantes.DB_USER_SQLSRVR_LOCAL, Constantes.DB_PASS_SQLSRVR_LOCAL);  
            conn = DriverManager.getConnection(driverUrl, Constantes.DB_USER_SQLSRVR_DEV, Constantes.DB_PASS_SQLSRVR_DEV);
            System.out.println(" Navega+ conexion exitosa");
            return conn;
        }catch(SQLException e){
            //System.out.println("my string jdbc:sqlserver://"+Constantes.DB_HOST_SQLSRVR_LOCAL+"\\"+Constantes.DB_INSTANCE_NAME_SQLSRVR_LOCAL+";databaseName="+Constantes.DB_NAME_SQLSRVR_LOCAL+"");
            //System.out.println("STRING jdbc:sqlserver://"+Constantes.DB_HOST_SQLSRVR_DEV+";databaseName="+Constantes.DB_NAME_SQLSRVR_DEV+" "+Constantes.DB_USER_SQLSRVR_DEV + " "+Constantes.DB_PASS_SQLSRVR_DEV);
            System.out.println(" Navega+ Error de SQL al conectar " + e.getMessage());
            System.out.println(" Navega+ Error de SQL al conectar, Causa: " + e.getCause().toString());
        }catch(ClassNotFoundException e){
            System.out.println(" Navega+ Error en el driver de conexion SQL " + e.getMessage());
        }
        
        return null;
    }
    
}