/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bdg.vista;

import com.bdg.database.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author oulloa
 */
@ManagedBean(name = "beands")
public class TestBean {
    
    public void testButton() throws SQLException{
        
        try{
        
        Statement stmt = null;
        ResultSet rs = null;
        
        DataSource ds = new DataSource();
        Connection conn = ds.getConexionDSLocal();
    
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
        }
        catch(Exception e) {
                    System.out.println(" Ocurrio una Excepcion al cerrar sql: "+e.getMessage());
                }
        
    }
    
     public void testButtonNaf() throws SQLException{
        
        try{
        
        Statement stmt = null;
        ResultSet rs = null;
        
        DataSource ds = new DataSource();
        Connection conn = ds.getConexionDSNaf();
    
        stmt = conn.createStatement();
        stmt.execute("SELECT USERNAME, ACCOUNT_STATUS FROM dba_users WHERE username IN('MGRODASP','JLDONIS')");
        rs = stmt.getResultSet();
        while (rs.next()) {
            //get first result
            System.out.println(rs.getString(1)+" "+rs.getString(2))  ;//coloumn 1
            }
            
            //Close JDBC objects as soon as possible
            stmt.close();
            stmt=null;
            conn.close();
        }
        catch(Exception e){
                    System.out.println(" Ocurrio una Excepcion al cerrar sql: "+e.getMessage());
                }
    }
     
     public void testButtonAmsys() throws SQLException{
        
        try{
        
        Statement stmt = null;
        ResultSet rs = null;
        
        DataSource ds = new DataSource();
        Connection conn = ds.getConexionDSNaf();
    
        stmt = conn.createStatement();
        stmt.execute("SELECT USERNAME, ACCOUNT_STATUS FROM dba_users WHERE username IN('MGRODASP','JLDONIS')");
        rs = stmt.getResultSet();
        while (rs.next()) {
            //get first result
            System.out.println(rs.getString(1)+" "+rs.getString(2))  ;//coloumn 1
            }
            
            //Close JDBC objects as soon as possible
            stmt.close();
            stmt=null;
            conn.close();
        }
        catch(Exception e){
                    System.out.println(" Amsys - Excepcion SQL: "+e.getMessage());
                }
    }

      public void testButtonNavega() throws SQLException{
        
        try{
        
        Statement stmt = null;
        ResultSet rs = null;
        
        DataSource ds = new DataSource();
        Connection conn = ds.getConexionDSNavega();
    
        stmt = conn.createStatement();
        stmt.execute("SELECT TOP(5) CodUsuario, Nombres FROM CTL_EMPLEADO WHERE ActivoInactivo='S'");
        rs = stmt.getResultSet();
        while (rs.next()) {
            //get first result
            System.out.println(rs.getString(1)+" "+rs.getString(2))  ;//coloumn 1
            }
            
            //Close JDBC objects as soon as possible
            stmt.close();
            stmt=null;
            conn.close();
        }
        catch(Exception e){
                    System.out.println(" Navega - Excepcion SQL: "+e.getMessage());
                }
    }    
     
}