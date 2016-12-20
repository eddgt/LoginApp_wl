/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_vista;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.bdg.cms_dto.Asesor;
import org.bdg.cms_dto.Coordinador;
import org.bdg.cms_dto.DetalleVenta;
import org.bdg.cms_conexion.Conexion;
import org.bdg.cms_buc.Querys_C;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import oracle.jdbc.driver.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;
import org.bdg.base.Constantes;
import javax.faces.event.ActionEvent;
import org.bdg.session.BaseSession;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.faces.context.ExternalContext;
import org.bdg.utils.JsfUtil;
import org.bdg.cms_dto.DetalleRenovacion;
/**
 *
 * @author Daniel Mendez
 */
@ManagedBean(name = "renovacionAsignarBean")
@ViewScoped
public class RenovacionAsignarBean extends BaseSession {
   
    
    ////////////////////////////////////////////////////////////////////
    private List<DetalleRenovacion> listaRenovaciones;
    private List<DetalleRenovacion> renovacionesSeleccionadas;
    private List<Coordinador> listaCoordinador;
    private List<Asesor> listaAsesor;
    private Coordinador coordinadorSelected;
    private Asesor asesorSelected;
    private int idCoordinador;
    private String nombreCoordinadorSelected;
    private int idAsesorSelelected;
    private String noAnexo;
    private String noTelefono;
    private String codCliente;
    private String nomCliente;    
    private boolean assignButtonDisabled;
    private String justificacion;

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }
   
    public boolean isAssignButtonDisabled() {
        return assignButtonDisabled;
    }

    public void setAssignButtonDisabled(boolean assignButtonDisabled) {
        this.assignButtonDisabled = assignButtonDisabled;
    }
    
    

    public List<DetalleRenovacion> getListaRenovaciones() {
        return listaRenovaciones;
    }

    public void setListaRenovaciones(List<DetalleRenovacion> listaRenovaciones) {
        this.listaRenovaciones = listaRenovaciones;
    }

    public List<DetalleRenovacion> getRenovacionesSeleccionadas() {
        return renovacionesSeleccionadas;
    }

    public void setRenovacionesSeleccionadas(List<DetalleRenovacion> renovacionesSeleccionadas) {
        this.renovacionesSeleccionadas = renovacionesSeleccionadas;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }    

    public String getNoAnexo() {
        return noAnexo;
    }

    public void setNoAnexo(String noAnexo) {
        this.noAnexo = noAnexo;
    }

    public String getNoTelefono() {
        return noTelefono;
    }

    public void setNoTelefono(String noTelefono) {
        this.noTelefono = noTelefono;
    }
    
    

    public int getIdAsesorSelelected() {
        return idAsesorSelelected;
    }

    public void setIdAsesorSelelected(int idAsesorSelelected) {
        this.idAsesorSelelected = idAsesorSelelected;
    }
    
    
    
    public String getNombreCoordinadorSelected() {
        return nombreCoordinadorSelected;
    }

    public void setNombreCoordinadorSelected(String nombreCoordinadorSelected) {
        this.nombreCoordinadorSelected = nombreCoordinadorSelected;
    }
    
    
    
    public int getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(int idCoordinador) {
        this.idCoordinador = idCoordinador;
    }
    
    
    public Asesor getAsesorSelected() {
        return asesorSelected;
    }

    public void setAsesorSelected(Asesor asesorSelected) {
        this.asesorSelected = asesorSelected;
    }

    public Coordinador getCoordinadorSelected() {
        return coordinadorSelected;
    }

    public void setCoordinadorSelected(Coordinador coordinadorSelected) {
        this.coordinadorSelected = coordinadorSelected;
    }
        //listaMunicipio = abc.listMunici
    public RenovacionAsignarBean() {
                
    }
    
    public void oneAsesorChange(){
        int idAsesorSeleccionado = this.getIdAsesorSelelected();
        if (idAsesorSeleccionado > 0){
            this.setAssignButtonDisabled(false);
        }else{
            this.setAssignButtonDisabled(true);
        }
        //this.listaVentas = this.getVentasCoordinador(idAsesorSeleccionado);          
    }
     
    public String retornarFecha(String fecha){
        if(!fecha.equals("")){
             try{
                String[] partesFecha = fecha.split(" ");
                if(partesFecha.length >0){
                      String[] creandoFormato = partesFecha[0].split("-");
                      String nuevaFecha = creandoFormato[2] +"/"+creandoFormato[1]+"/"+creandoFormato[0];
                      fecha = nuevaFecha;
                }                              
            }catch(Exception e){
                fecha="";
            }                       
        } 
        return fecha;
    }
     
       
    @PostConstruct
    public void init() {           
        
        coordinadorSelected = new Coordinador();
        asesorSelected = new Asesor();       
        ////////////////////////// 
        this.setNombreCoordinadorSelected(this.getAtributoSession(Constantes.SS_CoordinadorAsignar));
        System.out.println(this.getAtributoSession(Constantes.SS_CoordinadorAsignar));
        String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
        String rolUsuarioLogueado = this.getAtributoSession(Constantes.SS_ROL);
        this.listaAsesor = this.obtenerListadeAsesor(this.getNombreCoordinadorSelected());
        //listaCoordinador = this.obtenerListadeCoordinador();       
        this.setAssignButtonDisabled(true);
        if ((usuarioLogueado == null)||(rolUsuarioLogueado == null)){
            try{
               ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
               context.redirect(context.getRequestContextPath() + "/");
            }catch(Exception e){
            
            }
        }
    }
    
    public void btnClickAsignarRenovaciones(ActionEvent actionEvent) {
        Conexion conec = new Conexion();                
        Connection conex = conec.getConexion();
        int RegistroSeleccionados = 0;
        String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);

        String Observ = this.getJustificacion();
        RegistroSeleccionados = this.renovacionesSeleccionadas.size();
        
        if (Observ.equals("") == false){
            if (Observ.length()>0 && Observ.length()<= 150){
				for(int i=0; i< this.renovacionesSeleccionadas.size(); i++){
					DetalleRenovacion renovacionSeleccionada = this.renovacionesSeleccionadas.get(i);                     
					/////////////////           
					try{                
						Querys_C query = new Querys_C();
						String s = query.generar_Consulta_Asignar_Renovacion(Integer.parseInt(renovacionSeleccionada.getIdposventa()),
								this.getIdAsesorSelelected(),
								this.getAtributoSession(Constantes.SS_USUARIO));
						CallableStatement cstmt = conex.prepareCall(s);
						cstmt.executeQuery();                                

                                                String Idventa = renovacionSeleccionada.getIdposventa();
                                                query.generar_InsertaBitacoraJustificacion(Idventa, Observ, usuarioLogueado, "RE");
                                                String InsertBitacora = query.getConsulta_InsertaBitacoraJustificacion();
                                                CallableStatement bitacora = conex.prepareCall(InsertBitacora);
                                                bitacora.executeQuery();                                                          

					}catch(Exception e){
						e.printStackTrace();
					}finally{
					}
				}
				try {
						conex.close();
					} catch (SQLException ex) {
				}
				this.listaAsesor = this.obtenerListadeAsesor(this.getNombreCoordinadorSelected());
            }else{
                JsfUtil.addSuccessMessage(Constantes.MSG_ERROR_ASIGNA_TAMANIO);
            }            
        }else{
            JsfUtil.addSuccessMessage(Constantes.MSG_ERROR_ASIGNA);
		}                        
    }
    
    
    public void btnClickBuscarRenovaciones(ActionEvent actionEvent) {       
       this.listaRenovaciones = this.getRenovaciones();
    }
    
    public void btnClickRegresar(ActionEvent actionEvent){
        try{
          
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/renovacion.xhtml");           
        }catch(Exception e){
        
        }
        
    }
     
     
    public List<Coordinador> obtenerListadeCoordinador(){
        List<Coordinador> listaCoordinador = new ArrayList<Coordinador>();
        Connection conex = null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Querys_C query = new Querys_C();
            query.generar_Consulta_Coordinador_An();
                       
            rs1 = st1.executeQuery(query.getConsulta_CoordinadorAntigua());
            
            while (rs1.next())
            {
                Coordinador coor = new Coordinador();
                coor.setNombre((rs1.getString("NOMBRE")));
                coor.setIdCoordinador(Integer.parseInt(rs1.getString("ID")));
                listaCoordinador.add(coor);
            }            
        }catch(Exception e){ 
            
        }finally{
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }
        return listaCoordinador;
    }
    
    public List<Asesor> obtenerListadeAsesor(String nombreCoordinador){        
        List<Asesor> listaAsesor = new ArrayList<Asesor>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            Querys_C query = new Querys_C();  
            query.generar_Consulta_Vendedores(nombreCoordinador, this.getAtributoSession(Constantes.SS_ROL).equals("COORD"));
            rs1 = st1.executeQuery(query.getConsulta_Vendedores());
            
            while (rs1.next())
            {
                Asesor ases = new Asesor();
                ases.setNombre((rs1.getString("NOM_VENDEDOR")));
                ases.setIdAsesor(Integer.parseInt(rs1.getString("CODVEND")));
                listaAsesor.add(ases);
            }
            
        }catch(Exception e){        
        }finally{
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }        
        return listaAsesor;        
    }
     
    
    
    public List<DetalleRenovacion> getRenovaciones(){
        List<DetalleRenovacion> listaRenovaciones = new ArrayList<DetalleRenovacion>();
        try{                
            Conexion conec = new Conexion();                
            Connection conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Querys_C query = new Querys_C();
            
            String codCliente = this.getCodCliente();
            String nomCliente = this.getNomCliente();            
            String noFiltroTelefono = this.getNoTelefono();
            String noFiltroNexo = this.getNoAnexo();
            
            if(codCliente.equals("")){
                codCliente=null;
            }
            
            if(nomCliente.equals("")){
                nomCliente=null;
            }

            if(noFiltroTelefono.equals("")){
                noFiltroTelefono=null;
            }
            
            if(noFiltroNexo.equals("")){
                noFiltroNexo=null;
            }
            
            
            rs1 = st1.executeQuery(query.generar_Consulta_RenovacionXAsignar(codCliente, nomCliente, noFiltroTelefono, noFiltroNexo));
            while (rs1.next())
            {
                DetalleRenovacion detalle = new DetalleRenovacion();
                detalle.setFecha_venta(this.retornarFecha(rs1.getString("fecposventa")));
                detalle.setTipo_factura_subsidio((rs1.getString("tipo_factura_subsidio")));                 
                detalle.setTipo_subsidio(rs1.getString("tipo_subsidio"));
                detalle.setId_tipo_factura_subsidio(rs1.getString("idtipo_factura_subsidio"));
                detalle.setAnexo(rs1.getString("anexo"));
                detalle.setTelefono_instalacion(rs1.getString("telefono_instalacion"));
                detalle.setAgencia(rs1.getString("agencia"));
                detalle.setNom_agencia(rs1.getString("nom_agencia"));
                detalle.setNit(rs1.getString("no_cliente"));
                detalle.setNom_cliente(rs1.getString("nom_cliente"));
                detalle.setPromocion(rs1.getString("promocion"));
                detalle.setMeses_promocion(rs1.getString("meses_promocion"));
                detalle.setMbf_usd(rs1.getString("mbf_en_$usd"));
                detalle.setUpgrades_usd(rs1.getString("upgrades_$usd"));
                detalle.setTiempo_pendiente(rs1.getString("tiempo_pendiente"));
                detalle.setTarget(rs1.getString("target"));
                detalle.setSistema(rs1.getString("sistema"));
                detalle.setNumdocumento(rs1.getString("numdocumento"));
                detalle.setIdtipasesor(rs1.getString("idtipasesor"));
                detalle.setNomtipasesor(rs1.getString("nomtipasesor"));
                detalle.setIdtiptelefonia(rs1.getString("idtiptelefonia"));
                detalle.setNomtiptelefonia(rs1.getString("nomtiptelefonia"));
                detalle.setIdposventa(rs1.getString("idposventa"));
                listaRenovaciones.add(detalle);
            }
        }catch(Exception e){
        
            e.printStackTrace();
        }
                        
        System.out.println(listaRenovaciones);
        return listaRenovaciones;
    }
    
    public void onRowSelect(SelectEvent event) {
      
    }
 
    public void onRowUnselect(UnselectEvent event) {
       
    }
    
    
        public List<Coordinador> getListaCoordinador() {
        return listaCoordinador;
    }

    public void setListaCoordinador(List<Coordinador> listaCoordinador) {
        this.listaCoordinador = listaCoordinador;
    }

    public List<Asesor> getListaAsesor() {
        return listaAsesor;
    }

    public void setListaAsesor(List<Asesor> listaAsesor) {
        this.listaAsesor = listaAsesor;
    }

}
