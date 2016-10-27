package com.bdg.database;

import com.bdg.base.Constantes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author oulloa
 */
public class Conexion {

    public static Connection getConexionLocal(){
        try{
            Connection conn;
            String driverClassName=Constantes.DB_OJDBC_CLASSNAME;
            String driverUrl="jdbc:oracle:thin:@"+Constantes.DB_HOST_TEST+":"+Constantes.DB_SID_TEST;
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(driverUrl, Constantes.DB_USER_TEST, Constantes.DB_PASS_TEST);  
            System.out.println(" localhost NAF conexion exitosa");
            return conn;
        }catch(SQLException e){
            System.out.println(" localhost NAF Error de SQL al conectar" + e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println(" localhost NAF Error en el driver de conexion SQL " + e.getMessage());
        }
        return null;
    }    
    
    
    public static Connection getConexionNAF(){
        try{
            Connection conn;
            String driverClassName=Constantes.DB_OJDBC_CLASSNAME;
            String driverUrl="jdbc:oracle:thin:@"+Constantes.DB_HOST_NAF_PRO+":"+Constantes.DB_SID_NAF_PRO;
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(driverUrl, Constantes.DB_USER_NAF_PRO, Constantes.DB_PASS_NAF_PRO);  
            System.out.println(" NAF conexion exitosa");
            return conn;
        }catch(SQLException e){
            System.out.println(" NAF Error de SQL al conectar" + e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println(" NAF Error en el driver de conexion SQL " + e.getMessage());
        }
        return null;
    }

        public static Connection getConexionAmsys(){
        try{
            Connection conn;
            String driverClassName=Constantes.DB_OJDBC_CLASSNAME;
            String driverUrl="jdbc:oracle:thin:@"+Constantes.DB_HOST_AMSYS_PRO+":"+Constantes.DB_SID_AMSYS_PRO;
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(driverUrl, Constantes.DB_USER_AMSYS_PRO, Constantes.DB_PASS_AMSYS_PRO);
            System.out.println("Amsys conexion exitosa");
            return conn;
        }catch(SQLException e){
            System.out.println("Amsys Error de SQL al conectar" + e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println("Amsys Error en el driver de conexion SQL " + e.getMessage());
        }
        return null;
    }
        
        public static Connection getConexionAmsysDev(){
        try{
            Connection conn;
            String driverClassName=Constantes.DB_OJDBC_CLASSNAME;
            String driverUrl="jdbc:oracle:thin:@"+Constantes.DB_HOST_AMSYS_DEV+":"+Constantes.DB_SID_AMSYS_DEV;
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(driverUrl, Constantes.DB_USER_AMSYS_DEV, Constantes.DB_PASS_AMSYS_DEV);
            System.out.println("Amsys Dev conexion exitosa");
            return conn;
        }catch(SQLException e){
            System.out.println("Amsys Dev Error de SQL al conectar" + e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println("Amsys Dev Error en el driver de conexion SQL " + e.getMessage());
        }
        return null;
    }
        
      public static Connection getConexionNAFDev(){
        try{
            Connection conn;
            String driverClassName=Constantes.DB_OJDBC_CLASSNAME;
            String driverUrl="jdbc:oracle:thin:@"+Constantes.DB_HOST_NAF_DEV+":"+Constantes.DB_SID_NAF_DEV;
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(driverUrl, Constantes.DB_USER_NAF_DEV, Constantes.DB_PASS_NAF_DEV);  
            System.out.println(" NAF conexion exitosa");
            return conn;
        }catch(SQLException e){
            System.out.println(" NAF Error de SQL al conectar" + e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println(" NAF Error en el driver de conexion SQL " + e.getMessage());
        }
        return null;
    }
    
}