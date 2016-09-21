/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bdg.vista;
import com.bdg.base.Constantes;
import com.bdg.base.Queries;
import com.bdg.database.Conexion;
import com.bdg.database.ConexionLocal;
import com.bdg.database.ConexionSqlSvr;
import com.bdg.dto.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.bdg.sesion.BaseSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

/**
 *
 * @author oulloa
 */
@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean extends BaseSession{
    private String id;
    private String usuario;
    private String contrasena;
    private List<Usuario> listaUsuariosApp;
    
    
    public UsuarioBean() {
       
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public List<Usuario> getListaUsuariosApp() {
        return listaUsuariosApp;
    }

    public void setListaUsuariosApp(List<Usuario> listaUsuariosApp) {
        this.listaUsuariosApp = listaUsuariosApp;
    }
    
    public void btnClickCambiarNAF(ActionEvent actionEvent){
	//Connection conn1 = null;
        try{
            Connection conn1  = Conexion.getConexionLocal();
            
            Statement stmt1;
            Statement stmt2;
            ResultSet rset;
            if(conn1 != null){
                String query1 =Constantes.QRY_BUSCAR_USUARIO_AMSYS+"UPPER('"+usuario+"')";
                stmt1 = conn1.createStatement();
                rset = stmt1.executeQuery(query1); 
                rset.next();
                int count = rset.getInt(1);
                //System.out.println("query: "+query1);                
                if (count>0){                    
                    try{
                        Connection conn2 = Conexion.getConexionLocal();
                        
                        String query2 = "CALL " + Constantes.DB_SCHEMA_NAME_TEST+"."+Constantes.DB_ORACLE_PACKAGE_TEST + 
                        "." + Constantes.DB_PROCESS_PWD +
                        "('"+usuario+"','"+contrasena+"')";//NO COLOCAR ; AL FINAL DEL QUERY
                                                
                    //System.out.println(query);
                    stmt2 = conn2.createStatement();
                    //rset = stmt.executeQuery(query);//especifico para Selects
                    int executeUpdate = stmt2.executeUpdate(query2); //especifico para Updates
                    System.out.println("executeUpdate: "+executeUpdate);
                    System.out.println("getFetchSize: "+stmt2.getFetchSize());                
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("NAF - Se modifico el usuario "+usuario+" exitosamente!"));
                    conn1.close();
                    conn2.close();
                    }
                    catch(Exception e){
                    System.out.println("Error: " + e.getMessage().toString());            
                    }                                   
                }
                else{
                    FacesMessage msg = new FacesMessage("NAF - No se encontro el usuario: "+usuario);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    }
            }
            else{
            }
            
         }catch(Exception e){
            System.out.println("Error: " + e.getMessage().toString());
            }        
    }

    public void btnClickCambiarAmsys(ActionEvent actionEvent){
	//Connection conn1 = null;
        try{
            Connection conn1  = Conexion.getConexionLocal();
            
            Statement stmt1;
            Statement stmt2;
            ResultSet rset;
            if(conn1 != null){
                String query1 ="SELECT COUNT(*) FROM TEST.TEST_USUARIOS WHERE USERNAME ='"+usuario+"'";
                stmt1 = conn1.createStatement();
                rset = stmt1.executeQuery(query1); 
                rset.next();
                int count = rset.getInt(1);
                //System.out.println("query: "+query1);
                //System.out.println("rset: "+rset.toString());
                if (count>0){                    
                    try{
                        Connection conn2 = Conexion.getConexionLocal();
                        String query2 = "CALL " + Constantes.DB_SCHEMA_NAME_TEST+"."+Constantes.DB_ORACLE_PACKAGE_TEST + 
                        "." + Constantes.DB_PROCESS_PWD_TEST +
                        "('"+usuario+"','"+contrasena+"')";//NO COLOCAR ; AL FINAL DEL QUERY
                
                    //System.out.println(query);
                    stmt2 = conn2.createStatement();
                    //rset = stmt.executeQuery(query);//especifico para Selects
                    int executeUpdate = stmt2.executeUpdate(query2); //especifico para Updates
                    System.out.println("executeUpdate: "+executeUpdate);
                    System.out.println("getFetchSize: "+stmt2.getFetchSize());                
                    FacesMessage msg = new FacesMessage("Amsys - Se cambio la contraseña del usuario: "+usuario);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    conn1.close();
                    conn2.close();
                    }
                    catch(Exception e){
                    System.out.println("Error: " + e.getMessage().toString());            
                    }                                   
                }
                else{
                    FacesMessage msg = new FacesMessage("Amsys - No se encontro el usuario: "+usuario);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    }
            }
            else{
            }
            
         }catch(Exception e){
            System.out.println("Error: " + e.getMessage().toString());
            }        
    }
     
     public void btnClickCambiarNavega(ActionEvent actionEvent){
	
        try{
            Connection conn1  = ConexionSqlSvr.getConexion();
            
            Statement stmt1;
            Statement stmt2;
            ResultSet rset;
            if(conn1 != null){
                String query1 ="SELECT COUNT(*) FROM [testdb].[dbo].[ctl_empleado] WHERE [codUsuario] ='"+usuario+"'";
                stmt1 = conn1.createStatement();
                rset = stmt1.executeQuery(query1); 
                rset.next();
                int count = rset.getInt(1);
                
                if (count>0){                    
                    try{
                        Connection conn2 = ConexionSqlSvr.getConexion();
                        String query2 = "UPDATE [testdb].[dbo].[ctl_empleado]  SET [palabraClave]= '"+contrasena+"' WHERE [codUsuario] ='"+usuario+"'";//NO COLOCAR ; AL FINAL DEL QUERY
                                    
                    stmt2 = conn2.createStatement();
                    System.out.println(" Rows updated :"+ stmt2.getUpdateCount());
                    //rset = stmt.executeQuery(query);//especifico para Selects
                    int executeUpdate = stmt2.executeUpdate(query2); //especifico para Updates
                    System.out.println(executeUpdate);
                    System.out.println(stmt2.getFetchSize());                
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Navega - Se modifico el usuario "+usuario+" exitosamente!"));
                    conn1.close();
                    conn2.close();
                    }
                    catch(Exception e){
                    System.out.println("Error: " + e.getMessage().toString());            
                    }                                   
                }
                else{
                    FacesMessage msg = new FacesMessage("Navega - No se encontro el usuario: "+usuario);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    }
            }
            else{
            }
            
         }catch(Exception e){
            System.out.println("Error: " + e.getMessage().toString());
            }        
    }
     
     public List<Usuario> obtenerUsuariosApp(){        
        List<Usuario> listaUsuariosApp = new ArrayList<Usuario>();
        Connection conex = null;
        try{            
            conex = Conexion.getConexionLocal();
            ResultSet rs = null;
            Statement st = conex.createStatement();
            Queries query = new Queries();  
            query.consultaUsuariosApp();
            rs = st.executeQuery(query.getQryUsuariosApp());
            
            while (rs.next())
            {
                Usuario us = new Usuario();
                us.setIdUsuario(Integer.parseInt(rs.getString("ID")));
                us.setNombreUsuario((rs.getString("USERNAME")));
                listaUsuariosApp.add(us);
            }
            
        }
        catch(Exception e){        
        }
        finally{
                try {
                    conex.close();
                } 
                catch (SQLException ex) {
                }
            }    
        return listaUsuariosApp;
    }
    
}