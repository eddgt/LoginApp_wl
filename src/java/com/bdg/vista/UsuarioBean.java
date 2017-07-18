package com.bdg.vista;
import com.bdg.base.Constantes;
import com.bdg.base.Queries;
import com.bdg.database.Conexion;
import com.bdg.database.DataSource;
import com.bdg.dto.Mail;
import com.bdg.dto.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.bdg.sesion.BaseSession;
import com.bdg.utils.JsfUtil;
import com.bdg.utils.PasswordValidator;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import ws.Service1;
import ws.Service1Soap;

/**
 *
 * @author oulloa
 */
@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean extends BaseSession implements Serializable{
//public class UsuarioBean implements Serializable{    
    private String id;
    private String usuario;
    private String usuarioModif;
    private String contrasena;
    private List<Usuario> listaUsuariosApp;
    String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
    
    
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
    
    public String getUsuarioModif() {
        return usuarioModif;
    }

    public void setUsuarioModif(String usuarioModif) {
        this.usuarioModif = usuarioModif;
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
    
    // *** PROCESO NAF ***
    public void btnClickCambiarNAF(ActionEvent actionEvent){
	//Connection conn1 = null;
        DialogConfirmView dl = new DialogConfirmView();
        dl.confirmNavega();
        try{
            System.out.println("date :" + getFechaActual());
            //Connection conn1  = Conexion.getConexionNAFDev();//old method 11/10/2016
            Connection conn1  = DataSource.getConexionDSNaf();//new method 11/10/2016
            Statement stmt1;
            Statement stmt2;
            ResultSet rset;
            if(conn1 != null){
                String query1 =Constantes.QRY_USR_ACTIVO_NAF+"UPPER('"+usuarioModif+"')";
                stmt1 = conn1.createStatement();
                rset = stmt1.executeQuery(query1); 
                rset.next();
                int count = rset.getInt(1);
                //System.out.println("query: "+query1);                
                if (count>0){                    
                    try{
                        PasswordValidator pv = new PasswordValidator();
                        if(pv.validate(contrasena)==true){
                        //Connection conn2  = Conexion.getConexionNAFDev();//old method 11/10/2016
                        Connection conn2  = DataSource.getConexionDSNaf();//new method 11/10/2016
                        /*
                        String query2 = "CALL "+Constantes.DB_SCHEMA_NAME_NAF_PRO+"."+Constantes.DB_ORACLE_PACKAGE_NAF_PRO+
                        "." + Constantes.DB_PROCESS_PWD_NAF_PRO+
                        "('"+usuarioModif+"','"+contrasena+"')";//NO COLOCAR ; AL FINAL DEL QUERY
                        */
                        String query2 = "CALL "+Constantes.DB_SCHEMA_NAME_NAF_DEV+"."+Constantes.DB_ORACLE_PACKAGE_NAF_DEV+
                        "." + Constantes.DB_PROCESS_PWD_NAF_DEV+
                        "('"+usuarioModif+"','"+contrasena+"')";//NO COLOCAR ; AL FINAL DEL QUERY
                    
                    stmt2 = conn2.createStatement();
                    
                    boolean execute = stmt2.execute(query2); //especifico para Updates
                    //System.out.println("CALL execute: "+query2);
                    
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("NAF - Se modifico el usuario: "+usuarioModif+" exitosamente, IMPORTANTE: Notificar nuevo passowrd: "+contrasena));
                    
                    Mail ma = new Mail();                    
                    //System.out.println("Sendin mail to users..");                    
                    //usuario afectado
                    //ma.Send(usuarioModif+"@tigo.com.gt","NAF", usuarioModif, contrasena, usuarioLogueado);
                    
                    System.out.println("Enviando mail userApp..");                    
                    //usario realiza cambio
                    ma.Send(usuarioLogueado+"@tigo.com.gt","NAF", usuarioModif, contrasena, usuarioLogueado);
                    conn1.close();
                    stmt1.close();
                    rset.close();
                    System.out.println("Naf Conn1 closed");
                    conn2.close();
                    stmt2.close();
                    
                    resetValues();
                    System.out.println("Naf Conn2 closed");
                        }else{
                            JsfUtil.addErrorMessage("NAF Password debe contener 8 caracteres, Mayúsculas y Signo especial");
                            conn1.close();
                            stmt1.close();
                            rset.close();
                            System.out.println("Naf Conn1 closed");
                            resetValues();
                        }
                    }
                    catch(Exception e){
                    System.out.println("Error: " + e.getMessage());
                    }                                   
                }
                else{
                    JsfUtil.addErrorMessage("NAF Usuario sin acceso, favor contactar a Administrador!");
                    conn1.close();
                    stmt1.close();
                    rset.close();
                    System.out.println("Naf Conn1 closed");
                    resetValues();
                    
                    }
            }
            else{
            }
            
         }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            }        
    }

    // *** PROCESO AMSYS ***
    public void btnClickCambiarAmsys(ActionEvent actionEvent){
	DialogConfirmView dl = new DialogConfirmView();
        dl.confirmNavega();
        try{
            System.out.println("date :" + getFechaActual());
            //Connection conn1  = Conexion.getConexionAmsysDev();//old conn method without ds weblogic
            Connection conn1  = DataSource.getConexionDSAmsys();
            
            Statement stmt1;
            Statement stmt2;
            ResultSet rset;
            if(conn1 != null){
                String query1 =Constantes.QRY_USR_ACTIVO_AMSYS+ "upper('"+usuarioModif+"')";
                stmt1 = conn1.createStatement();
                rset = stmt1.executeQuery(query1); 
                rset.next();
                int count = rset.getInt(1);
                
                if (count>0){                    
                    try{
                        PasswordValidator pv = new PasswordValidator();
                        if(pv.validate(contrasena)==true){
                        //Connection conn2  = Conexion.getConexionAmsysDev();//old conn method without ds weblogic
                        Connection conn2  = DataSource.getConexionDSAmsys();
                        /*
                        String query2 = "CALL " + Constantes.DB_SCHEMA_NAME_AMSYS_PRO+"."+Constantes.DB_ORACLE_PACKAGE_AMSYS_PRO + 
                        "." + Constantes.DB_PROCESS_PWD_AMSYS_PRO +                        
                        "('"+usuarioModif+"','E','"+contrasena+"','"+usuarioLogueado+"')";//NO COLOCAR ; AL FINAL DEL QUERY
                        */
                        String query2 = "CALL " + Constantes.DB_SCHEMA_NAME_AMSYS_DEV+"."+Constantes.DB_ORACLE_PACKAGE_AMSYS_DEV + 
                        "." + Constantes.DB_PROCESS_PWD_AMSYS_DEV +
                        "('"+usuarioModif+"','E','"+contrasena+"','"+usuarioLogueado+"')";//NO COLOCAR ; AL FINAL DEL QUERY
                
                    //System.out.println(query2);
                    stmt2 = conn2.createStatement();
                    
                    int executeUpdate = stmt2.executeUpdate(query2); //especifico para Updates                    
                    
                    FacesMessage msg = new FacesMessage("Amsys - Se cambio la contraseña del usuario: "+usuarioModif+" exitosamente, IMPORTANTE: Notificar nuevo passowrd: "+contrasena);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    
                     //usario realiza cambio
                    Mail ma = new Mail();                    
                    System.out.println("Enviando mail Amsys user..");
                    ma.Send(usuarioLogueado+"@tigo.com.gt","AMSYS", usuarioModif, contrasena, usuarioLogueado);
                    
                    //System.out.println("Sendin mail to modified user..");                    
                    //usuario afectado
                    //ma.Send(usuarioModif+"@tigo.com.gt","AMSYS", usuarioModif, contrasena, usuarioLogueado);
                    
                    //resetForm();
                     
                    conn1.close();
                    stmt1.close();
                    rset.close();
                    System.out.println("Am Conn1 closed");
                    conn2.close();
                    stmt2.close();
                    System.out.println("Am Conn2 closed");
                    
                    resetValues();
                        }
                        else{
                            JsfUtil.addErrorMessage("AMSYS Password debe contener 8 caracteres, Mayúsculas y Signo especial"); 
                            conn1.close();
                            stmt1.close();
                            rset.close();
                            System.out.println("Am Conn1 closed");
                            resetValues();
                         
                        }
                    }
                    catch(Exception e){
                    System.out.println("Error: " + e.getMessage());
                    }                                   
                }
                else{
                    //pageRefresh();
                    
                    conn1.close();
                    stmt1.close();
                    rset.close();
                    JsfUtil.addErrorMessage("No se encontro usuario: "+usuarioModif);
                    System.out.println("Am Conn1 closed");
                    resetValues();
                    }
            }
            else{
            }
            
         }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            }        
    }
     
    public void resetForm() {
        RequestContext.getCurrentInstance().reset("myform:mypanel");
    }
    
    // *** PROCESO NAVEGA PLUS ***
    public void btnClickCambiarNavega(ActionEvent actionEvent){
	DialogConfirmView dl = new DialogConfirmView();
        dl.confirmNavega();
        try{
            System.out.println("date :" + getFechaActual());
            //Connection conn1  = ConexionSqlSvr.getConexionDev();//old method 11/10/2016
            Connection conn1  = DataSource.getConexionDSNavega();//new method 12/10/2016
            Statement stmt1;            
            ResultSet rset;
            if(conn1 != null){
                
                String query1 ="SELECT COUNT(*) FROM CTL_EMPLEADO a INNER JOIN SEG_EMPLEADO_TIPOAUTENTICACION b ON a.CodUsuario=b.CodUsuario and b.Activo='S' and b.CodTipoAut=1 and a.CodUsuario='"+usuarioModif+"'";
                stmt1 = conn1.createStatement();
                rset = stmt1.executeQuery(query1); 
                rset.next();
                int count = rset.getInt(1);
                
                //Si el usuario se encuentra activo y es de tipo usuario navega
                if (count==1){                    
                    try{
                        PasswordValidator pv = new PasswordValidator();
                        if(pv.validate(contrasena)==true){
                            
                            Service1 ss = new Service1();
                            ss.getPorts();
                            Service1Soap service1Soap = ss.getService1Soap();
                            service1Soap.cambioClave(Constantes.WSERVICE_USR, Constantes.WSERVICE_PWD, usuarioModif, contrasena);
                            
                            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Navega - Se modifico: "+usuarioModif+" exitosamente!"));
                            JsfUtil.addSuccessMessage("Navega+ Se modifico: "+usuarioModif+" exitosamente, IMPORTANTE: Notificar nuevo passowrd: "+contrasena);
                    
                    
                            Mail ma = new Mail();                    
                            //System.out.println("Sendin mail to users..");                    
                            //usuario afectado
                            //ma.Send(usuarioModif+"@tigo.com.gt","NAVEGA+", usuarioModif, contrasena, usuarioLogueado);
                    
                            System.out.println("Enviando mail userApp..");                    
                            //usario realiza cambio
                            ma.Send(usuarioLogueado+"@tigo.com.gt","NAVEGA+", usuarioModif, contrasena, usuarioLogueado);
                        }
                        else{
                            JsfUtil.addErrorMessage("NAVEGA+ Password debe contener 8 caracteres, Mayúsculas y Signo especial");
                            conn1.close();
                            stmt1.close();
                            rset.close();
                            resetValues();
                        }
                    }
                    catch(Exception e){
                    System.out.println("Error: " + e.getMessage());            
                    }   
                    
                }
                else{
                   try{
                        //Connection conn2  = ConexionSqlSvr.getConexionDev();//old method 11/10/16
                        Connection conn2  = DataSource.getConexionDSNavega();//new method 12/10/16
                        Statement stmt2;            
                        ResultSet rset2;
                        String query2 ="SELECT count(*) FROM CTL_EMPLEADO a INNER JOIN SEG_EMPLEADO_TIPOAUTENTICACION b ON a.CodUsuario=b.CodUsuario and b.Activo='S' and a.ActivoInactivo ='N' and b.CodTipoAut=2 and b.FechaTemporal is null and b.ClaveTemporal is null and a.Bloqueado='N' and a.CodUsuario='"+usuarioModif+"'";
                        stmt2 = conn2.createStatement();
                        rset2 = stmt2.executeQuery(query2); 
                        rset2.next();
                        int count2 = rset2.getInt(1);
                        
                        //Si el usuario esta INACTIVO
                        if (count2==1){  
                            
                            JsfUtil.addErrorMessage("Usuario Inactivo, favor verificar autorizacion en Websox ");//([ActivoInactivo]='N')
                            conn2.close();
                            rset2.close();
                            stmt2.close();
                            System.out.println("Conn2 closed");
                        }
                        else{
                            try{
                                //Connection conn3  = ConexionSqlSvr.getConexionDev();//old method 11/10/2016
                                Connection conn3  = DataSource.getConexionDSNavega();//new method 12/10/2016
                                Statement stmt3;            
                                ResultSet rset3;
                                //String query3 ="SELECT COUNT(1) FROM CTL_EMPLEADO a INNER JOIN SEG_EMPLEADO_TIPOAUTENTICACION b ON a.CodUsuario=b.CodUsuario and b.Activo='S' and b.CodTipoAut=2 and b.FechaTemporal is null and b.ClaveTemporal is null and a.Bloqueado='S' and a.NumeroIntentos=0 and a.CodUsuario='"+usuarioModif+"'";//old
                                String query3 ="SELECT COUNT(*) FROM CTL_EMPLEADO a INNER JOIN SEG_EMPLEADO_TIPOAUTENTICACION b ON a.CodUsuario=b.CodUsuario AND a.ActivoInactivo='S' and b.Activo='S' and b.CodTipoAut=2 and a.CodUsuario='"+usuarioModif+"'";
                                
                                stmt3 = conn3.createStatement();
                                rset3 = stmt3.executeQuery(query3); 
                                rset3.next();
                                int count3 = rset3.getInt(1);
                                
                                //si el usuario es de tipo LDAP y esta activo
                                if (count3==1){
                                    try{
                                        PasswordValidator pv = new PasswordValidator();
                                        if(pv.validate(contrasena)==true){
                                            Service1 ss2 = new Service1();
                                            ss2.getPorts();
                                            Service1Soap service1Soap = ss2.getService1Soap();
                                            service1Soap.cambioClave(Constantes.WSERVICE_USR, Constantes.WSERVICE_PWD, usuarioModif, contrasena);
                                            
                                            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Navega - Se modifico: "+usuarioModif+" exitosamente!"));
                                            JsfUtil.addSuccessMessage("Se ha desbloqueado el usuario: "+usuarioModif+" y deberá ingresar al sistema con credenciales de dominio por ser usuario de tipo LDAP");
                                            
                                            //JsfUtil.addErrorMessage("Favor usar credenciales de Dominio para ingresar a Navega+");
                                            
                                            /*
                                            String package=" begin APP_PR_PKG.send_Mail_Html("'"+usuarioLogueado+"'",'AppNoReply@tigo.com.gt','Notificacion_credenciales','',  '<!DOCTYPE html>  <html>  <head><title>Notificacion Modificacion de Credenciales</title></head><body><br></br><p><a>Se ha desbloqueado el usuario: xxxxx '' Password de acceso para la plataforma: plataforma  para el usuario:<b>user_updated</b> </a></p>                  <p><a><font color=\"blue\">Nuevo password: <b> passw_xx </b></font></a></p> <p><a><font color=\"blue\">Usuario que aplica los cambios: <b> user_tier2 </b></font></a></p></body> </html>','mail.tigo.com.gt',25); end";
                                                    
                                            Statement stmt4 = conn3.createStatement();
                                            rset4 = stmt4.executeQuery(package); 
                                            */
                                            Mail thismail = new Mail();
                                            thismail.SendMailNavega(usuarioLogueado+"@tigo.com.gt","NAVEGA+", usuarioLogueado, contrasena, usuarioModif);
                                            
                                            conn3.close();
                                            stmt3.close();
                                            rset3.close();
                                            System.out.println("Conn3 closed");
                                            
                                            /*
                                            Mail ma = new Mail();                                            
                                            System.out.println("Enviando mail userApp..");                                            
                                            //usario realiza cambio
                                            ma.Send(usuarioLogueado+"@tigo.com.gt","NAVEGA+", usuarioModif, contrasena, usuarioLogueado);
                                            */
                                }
                                        else{
                                            JsfUtil.addErrorMessage("NAVEGA+ Password debe contener 8 caracteres, Mayúsculas y Signo especial");
                                            conn1.close();
                                            stmt1.close();
                                            rset.close();
                                            resetValues();
                                            }
                                    }
                                    catch (Exception ex){ex.getMessage();}                                    
                                    
                                    }
                                else{
                                    JsfUtil.addErrorMessage("No se encontro usuario: "+usuarioModif);
                                    conn3.close();
                                    stmt3.close();
                                    rset3.close();
                                    resetValues();
                                }
                            }
                            catch(Exception e){
                                System.out.println(" Err conn3: "+e.getMessage());
                                }                            
                        }
                        conn2.close();
                        rset2.close();
                        stmt2.close();
                        System.out.println("Conn2 closed");
                        resetValues();
                    }
                   catch(Exception e){System.out.println("Error "+e.toString());}
                    }
                conn1.close();
                stmt1.close();
                rset.close();
                System.out.println("N+ Conn1 closed");
                resetValues();
                 
            }
            else{
            }
         
         }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            }     
        
       
    }
     
    public List<Usuario> obtenerUsuariosApp(){        
        List<Usuario> listaUsuariosApp = new ArrayList<Usuario>();
        Connection conex = null;
        try{
            System.out.println("date :" + getFechaActual());
            conex = Conexion.getConexionLocal();
            ResultSet rs = null;
            Statement st = conex.createStatement();
            Queries query = new Queries();  
            query.consultaUsuariosApp();
            System.out.println("query.consultaUsuariosApp() -> "+query.consultaUsuariosApp());
            rs = st.executeQuery(query.getQryUsuariosApp());
            
            while (rs.next())
            {
                Usuario us = new Usuario();
                us.setIdUsuario(Integer.parseInt(rs.getString("ID")));
                us.setNombreUsuario((rs.getString("USERNAME")));
                listaUsuariosApp.add(us);
                System.out.println("us.toString() -> "+us.toString());
            }
            
        }
        catch(Exception e){        
        }
        finally{
                try {
                    conex.close();
                    System.out.println("Conn closed");
                } 
                catch (SQLException ex) {
                }
            }    
        return listaUsuariosApp;
    }
     
    public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return formateador.format(ahora);
    }
    
    public String resetValues(){
        
        //javascript execute from bean
        /*
        RequestContext.getCurrentInstance().execute("javascript:location.reload(true);");
        RequestContext.getCurrentInstance().execute("javascript:function resetForm(id) {    $('#'+id).each(function(){        this.reset();    });}");
        */
        
        resetUsuario();      
        resetContrasena();      
        return null;
        }
    
    public String resetUsuario(){
            usuarioModif=null;
            return null;                     
        
    }
    
    public String resetContrasena(){
        contrasena=null;
            return null;                     
    }
     
    private List<Usuario> filtered;
    public List<Usuario> getFiltered() { return filtered; }
    public void setFiltered(List<Usuario> filtered) { this.filtered = filtered; }    
}