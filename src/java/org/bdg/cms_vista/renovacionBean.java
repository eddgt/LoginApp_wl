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
import org.bdg.cms_dto.DetalleRenovacion;
import org.bdg.cms_conexion.Conexion;
import org.bdg.cms_buc.Query_C;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.bdg.base.Constantes;
import javax.faces.event.ActionEvent;
import org.bdg.session.BaseSession;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.bdg.cms_buc.Query_Editar;
import org.bdg.cms_buc.Query_Bitacora;
import org.bdg.cms_dto.DetalleVenta;
import org.bdg.cms_dto.Movimiento;
import org.bdg.database.ObtenerEstructura;
import org.bdg.exporters.ExcelConfig;
import org.bdg.exporters.ExcelHelper;
import org.bdg.utils.JsfUtil;
import org.primefaces.event.data.FilterEvent;
/**
 *
 * @author Daniel Mendez
 */
@ManagedBean(name = "renovacionBean")
@ViewScoped
public class renovacionBean extends BaseSession {
   
    private List<DetalleRenovacion> listaRenovaciones;
    private List<DetalleRenovacion> renovacionesSeleccionadas;
    private List<DetalleRenovacion> renovacionesFiltradas;
    
    private DetalleRenovacion renovacionSeleccionadaDetalle;
    private boolean blnActivoEdicionMBF=true;
    private boolean blnActivoSwitchEdicion=false;
    private boolean blnActivoEdicion=true; 
     
    ////////////////////////////////////////////////////////////////////
    private List<Coordinador> listaCoordinador;
    private List<Asesor> listaAsesors;
    private Coordinador coordinadorSelected;
    private Asesor asesorSelected;
    private int idCoordinador;
    private String nombreCoordinadorSelected;
    private int idAsesorSelelected;
    private List<Movimiento> listaMovimientoxVenta;
    
    private boolean blnActivoDesAsignar;
    private boolean blnActivoAgregar;
    private boolean blnActivoAceptarFinalizar;
    private boolean disableCordinador;
    private boolean blnActivoReAsignar;
    private float SumatoriaMBFUSCarga;
    private float SumatoriaMBF;
    private float SumatoriaUPGRADE;  
    private float SumatoriaMBFUSFiltro;
    private float SumatoriaUPGRADEUSCarga;
    private float SumatoriaUPGRADEUSFiltro;
    private int cantidadElementos;
    private String idMoneda;
    private Map<Integer, String> listaAsesor;    
     
     

    
    public String getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }
    
    
    
    public boolean isBlnActivoSwitchEdicion() {
        return blnActivoSwitchEdicion;
    }

    public void setBlnActivoSwitchEdicion(boolean blnActivoSwitchEdicion) {
        this.blnActivoSwitchEdicion = blnActivoSwitchEdicion;
    }

    
    
    public boolean isBlnActivoEdicionMBF() {
        return blnActivoEdicionMBF;
    }

    public void setBlnActivoEdicionMBF(boolean blnActivoEdicionMBF) {
        this.blnActivoEdicionMBF = blnActivoEdicionMBF;
    }

    public boolean isBlnActivoEdicion() {
        return blnActivoEdicion;
    }

    public void setBlnActivoEdicion(boolean blnActivoEdicion) {
        this.blnActivoEdicion = blnActivoEdicion;
    }

    
    
    public DetalleRenovacion getRenovacionSeleccionadaDetalle() {
        return renovacionSeleccionadaDetalle;
    }

    public void setRenovacionSeleccionadaDetalle(DetalleRenovacion renovacionSeleccionadaDetalle) {
        this.renovacionSeleccionadaDetalle = renovacionSeleccionadaDetalle;
        this.blnActivoEdicion=true;
        this.blnActivoSwitchEdicion=false;
        this.blnActivoEdicionMBF=true;       
        this.listaMovimientoxVenta =obtenerListaxVentaMovimiento(renovacionSeleccionadaDetalle.getIdposventa()); 
        this.idMoneda="USD*";       
    }

    
    public List<Movimiento> obtenerListaxVentaMovimiento(String identificador){
        List<Movimiento> mmoLista = new ArrayList<Movimiento>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion2();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Query_Bitacora query = new Query_Bitacora();
            String querys= query.getConsultaBitacorxRenovacion(identificador);
            rs1 = st1.executeQuery(querys);
            
                
            while (rs1.next())
            {
                Movimiento mol = new Movimiento();
                mol.setFechaCambio((rs1.getString("FECHACAMBIO")));
                mol.setJustificacion((rs1.getString("JUSTIFICACION")));
                mol.setUsuario((rs1.getString("USUARIO")));
                mol.setIdVentaBitacora(Integer.parseInt(rs1.getString("IdVentaBitacora")));
                mmoLista.add(mol);
            }         
            
            if(mmoLista.isEmpty()){
                Movimiento mol = new Movimiento();
                mol.setFechaCambio(("No hay movimientos"));
                mol.setJustificacion("--");
                mol.setUsuario(("--"));
                mmoLista.add(mol);
            }
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
       
        return mmoLista;
    }
    
    public List<Movimiento> getListaMovimientoxVenta() {
        return listaMovimientoxVenta;
    }

    public void setListaMovimientoxVenta(List<Movimiento> listaMovimientoxVenta) {
        this.listaMovimientoxVenta = listaMovimientoxVenta;
    }
    
    
    ///////////////////////// Filtros ////////////////

    public List<DetalleRenovacion> getRenovacionesFiltradas() {
        return renovacionesFiltradas;
    }

    public void setRenovacionesFiltradas(List<DetalleRenovacion> renovacionesFiltradas) {
        this.renovacionesFiltradas = renovacionesFiltradas;
    }
        
    public int getCantidadElementos() {
        return cantidadElementos;
    }

    public void setCantidadElementos(int cantidadElementos) {
        this.cantidadElementos = cantidadElementos;
    }
    
    public String getSumatoriaMBF() {
        float valorSumatoria = 0;
        
        
        if(this.SumatoriaMBFUSCarga!=0){
            valorSumatoria = this.SumatoriaMBFUSCarga;            
        }
                        
        if(this.SumatoriaMBFUSFiltro !=0){
            valorSumatoria = this.SumatoriaMBFUSFiltro;
        }
        
        this.SumatoriaMBFUSCarga=0;
        this.SumatoriaMBFUSFiltro=0;
        
        return this.retornarDecimal(valorSumatoria);       
       
    }
    
    
    
    public void cambiarEstadoEdicion() {
        if(this.blnActivoSwitchEdicion){           
            this.blnActivoEdicionMBF=false;
             this.blnActivoEdicion=false;
        }else{
            this.blnActivoEdicion=true;
            this.blnActivoEdicionMBF=true;
            
        }
    }
    
    
    public void btnGuardarValor(ActionEvent actionEvent){
        
        try{
            
            Conexion conec = new Conexion();
            Connection conex = conec.getConexion2();
            String errorSQL="";
            String query = Query_Editar.getQueryEditarMonto();
            try{
                
                int renovacionSelect = Integer.parseInt((this.renovacionSeleccionadaDetalle.getIdposventa()));
                String montoOperacion;
                CallableStatement cstmt = conex.prepareCall(query);
                cstmt.registerOutParameter(1,  java.sql.Types.INTEGER);
                //String idVenta, String numeroOperacion, String modulo, String monto)
                if(!this.blnActivoEdicionMBF){
                    montoOperacion = this.renovacionSeleccionadaDetalle.getMbf_usd();
                    cstmt.setInt(2, renovacionSelect);
                    cstmt.setInt(3, 1);
                    cstmt.setString(4, "RENOVACIÓN");
                    cstmt.setFloat(5, Float.parseFloat(montoOperacion));
                    cstmt.setString(6, this.idMoneda);
                }
                
                cstmt.execute();
                int valorRetorno = cstmt.getInt(1);
                cstmt.close();
                
                if(valorRetorno!=0){
                    JsfUtil.addSuccessMessage(Constantes.MSG_SUCCESS_CAMBIARMONTOR);
                    this.blnActivoEdicion=true;
                    this.blnActivoSwitchEdicion=false;
                    
                }else{
                    JsfUtil.addSuccessMessage("Error al actualizar el monto");
                }
                
            }catch(Exception e){
                errorSQL = e.toString();
                JsfUtil.addSuccessMessage("Error al actualizar el monto");
            }
            
            try {
                conex.close();
                //mensaje de asignado correctamente
                
            } catch (SQLException ex) {
                
            }
            this.listaRenovaciones = this.getRenovacionesCoordinador(this.idAsesorSelelected);
            //this.listaRenovaciones = this.getre(this.idAsesorSelelected);
        }catch(SQLException ex){
            Logger.getLogger(renovacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }   
     
    
    
    
    public String retornarDecimal(float numero){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return df.format(numero);        
    }
    

    public void setSumatoriaMBF(float SumatoriaMBF) {
        this.SumatoriaMBF = SumatoriaMBF;
    }

    public String getSumatoriaUPGRADE(){
        float valorSumatoria = 0;                
        if(this.SumatoriaUPGRADEUSCarga!=0){
            valorSumatoria = this.SumatoriaUPGRADEUSCarga;            
        }
                        
        if(this.SumatoriaUPGRADEUSFiltro !=0){
            valorSumatoria = this.SumatoriaUPGRADEUSFiltro;
        }
        
        this.SumatoriaUPGRADEUSCarga=0;
        this.SumatoriaUPGRADEUSFiltro=0;
        
        return this.retornarDecimal(valorSumatoria);    
    }

    public void setSumatoriaUPGRADE(float SumatoriaUPGRADE) {
        this.SumatoriaUPGRADE = SumatoriaUPGRADE;
    }
    
    
    public float getSumatoriaMBFUSCarga() {
        return SumatoriaMBFUSCarga;
    }

    public void setSumatoriaMBFUSCarga(float SumatoriaMBFUSCarga) {
        this.SumatoriaMBFUSCarga += SumatoriaMBFUSCarga;
    }

    public float getSumatoriaMBFUSFiltro() {
        return SumatoriaMBFUSFiltro;
    }

    public void setSumatoriaMBFUSFiltro(float SumatoriaMBFUSFiltro) {
        this.SumatoriaMBFUSFiltro = SumatoriaMBFUSFiltro;
    }

    public float getSumatoriaUPGRADEUSCarga() {
        return SumatoriaUPGRADEUSCarga;
    }

    public void setSumatoriaUPGRADEUSCarga(float SumatoriaUPGRADEUSCarga) {
        this.SumatoriaUPGRADEUSCarga += SumatoriaUPGRADEUSCarga;
    }

    public float getSumatoriaUPGRADEUSFiltro() {
        return SumatoriaUPGRADEUSFiltro;
    }

    public void setSumatoriaUPGRADEUSFiltro(float SumatoriaUPGRADEUSFiltro) {
        this.SumatoriaUPGRADEUSFiltro = SumatoriaUPGRADEUSFiltro;
    }
    
    public void sumarCostosFiltrados(FilterEvent filterEvent){                
        float sumatoriaUPGRADE=0;
        float sumatoriaMBF=0;
        
        if(this.renovacionesFiltradas!=null){          
            for(int i=0; i< this.renovacionesFiltradas.size(); i++){                  
                sumatoriaUPGRADE += Float.parseFloat(this.renovacionesFiltradas.get(i).getUpgrades_usd());
                sumatoriaMBF += Float.parseFloat(this.renovacionesFiltradas.get(i).getMbf_usd());
            }                      
        }
        
        this.cantidadElementos =0;  
        this.SumatoriaUPGRADEUSCarga = 0;                
        this.SumatoriaMBFUSCarga = 0;
        
        if(this.renovacionesFiltradas!=null){    
            this.cantidadElementos=renovacionesFiltradas.size();
        }        
        this.SumatoriaMBFUSFiltro=sumatoriaMBF;
        this.SumatoriaUPGRADEUSFiltro = sumatoriaUPGRADE;        
    }
    
    
    ////////////////////////////////////777
    
    public boolean isDisableCordinador() {
        return disableCordinador;
    }

    public void setDisableCordinador(boolean disableCordinador) {
        this.disableCordinador = disableCordinador;
    }
    

    public boolean isBlnActivoDesAsignar() {
        return blnActivoDesAsignar;
    }

    public void setBlnActivoDesAsignar(boolean blnActivoDesAsignar) {
        this.blnActivoDesAsignar = blnActivoDesAsignar;
    }

    public boolean isBlnActivoAgregar() {
        return blnActivoAgregar;
    }

    public void setBlnActivoAgregar(boolean blnActivoAgregar) {
        this.blnActivoAgregar = blnActivoAgregar;
    }

    public boolean isBlnActivoReAsignar() {
        return blnActivoReAsignar;
    }

    public void setBlnActivoReAsignar(boolean blnActivoReAsignar) {
        this.blnActivoReAsignar = blnActivoReAsignar;
    }

    
    public boolean isBlnActivoAceptarFinalizar() {
        return blnActivoAceptarFinalizar;
    }

    public void setBlnActivoAceptarFinalizar(boolean blnActivoAceptarFinalizar) {
        this.blnActivoAceptarFinalizar = blnActivoAceptarFinalizar;
    }
    
    

    public List<DetalleRenovacion> getRenovacionesSeleccionadas() {
        return renovacionesSeleccionadas;
    }

    public void setRenovacionesSeleccionadas(List<DetalleRenovacion> renovacionesSeleccionadas) {
        this.renovacionesSeleccionadas = renovacionesSeleccionadas;
    }

    public List<DetalleRenovacion> getListaRenovaciones() {
        return listaRenovaciones;
    }

    public void setListaRenovaciones(List<DetalleRenovacion> listaRenovaciones) {
        this.listaRenovaciones = listaRenovaciones;
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

    public String retornarFechaCompleta(String fecha){
        if(!fecha.equals("")){
             try{
                String[] partesFecha = fecha.split(" ");
                if(partesFecha.length >0){
                      String[] creandoFormato = partesFecha[0].split("-");
                      String nuevaFecha = creandoFormato[2] +"/"+creandoFormato[1]+"/"+creandoFormato[0] + " "+ partesFecha[1];
                      fecha = nuevaFecha;
                }                              
            }catch(Exception e){
                fecha="-";
            }                       
        } 
        return fecha;
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

    public renovacionBean() {
    }
    
    public void oneCoordinadorChange() {        
        String nombreCoordinadorFiltro = this.getNombreCoordinadorSelected();
        if (nombreCoordinadorFiltro != null){
            this.listaAsesor = this.obtenerListadeAsesor(nombreCoordinadorFiltro);
            this.setBlnActivoAgregar(false);
        }else{
            this.setBlnActivoAgregar(true);
        }
        System.out.println("disabled" + this.isBlnActivoAgregar());
        //listaMunicipio = abc.listMunicipiosByDepto(departamentoSelected);
    }
    
    public void oneAsesorChange(){
        int idAsesorSeleccionado = this.getIdAsesorSelelected();
        Logger logger = Logger.getLogger(getClass().getName());
        if (idAsesorSeleccionado > 0){
            this.listaRenovaciones = this.getRenovacionesCoordinador(idAsesorSeleccionado);
            this.setBlnActivoAceptarFinalizar(false);
            this.setBlnActivoDesAsignar(false);
            this.setBlnActivoReAsignar(false);
        }else{
            this.setBlnActivoAceptarFinalizar(true);
            this.listaRenovaciones=null;
            this.setBlnActivoDesAsignar(true);
            this.setBlnActivoReAsignar(true);
        }
    }
     
     
    @PostConstruct
    public void init() {
        coordinadorSelected = new Coordinador();
        asesorSelected = new Asesor();       
        ////////////////////////// 
        String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
        String rolUsuarioLogueado = this.getAtributoSession(Constantes.SS_ROL);
        if ((usuarioLogueado == null)||(rolUsuarioLogueado == null)){
            try{
               ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
               context.redirect(context.getRequestContextPath() + "/");
            }catch(Exception e){
            
            }
        }else{
            listaCoordinador = this.obtenerListadeCoordinador(); 
            if (rolUsuarioLogueado.equals("COORD")){
                for (Coordinador c : listaCoordinador){
                    if (c.getUsuario_dominio().equals(usuarioLogueado.toUpperCase())){
                        this.setNombreCoordinadorSelected(c.getNombre());
                        this.setDisableCordinador(true);
                        this.oneCoordinadorChange();
                        this.setBlnActivoAgregar(false);
                        this.setBlnActivoReAsignar(true);
                        break;
                    }
                }
            }else{
                this.setBlnActivoAgregar(true);
            }
            this.setBlnActivoAceptarFinalizar(true);
            this.setBlnActivoDesAsignar(true);
            this.setBlnActivoReAsignar(true);
        }
    }
    
    public void btnClickAsignarRenovaciones(ActionEvent actionEvent){
        try{
            String nombre = this.getNombreCoordinadorSelected();
            this.setAbributoSession(Constantes.SS_CoordinadorAsignar, nombre);
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/renovacionAsignar.xhtml");           
        }catch(Exception e){
        
        }
        
    }
    
    public void btnClickCongelar(ActionEvent actionEvent){
        try{            
            this.setAbributoSession(Constantes.SS_VendedorCongelar,  this.listaAsesor.get( this.getIdAsesorSelelected()));
            this.setAbributoSession(Constantes.SS_VendedorCongelarId, this.getIdAsesorSelelected()+"");
            this.setAbributoSession(Constantes.SS_PaginaFinalizaVentaRenovacion,"RENOVACIONES");                                  
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/congelar.xhtml");           
        }catch(Exception e){
        e.printStackTrace();
        }
        
    }
    
    public void btnClickDesasignarRenovaciones(ActionEvent actionEvent) {
        try {
            Conexion conec = new Conexion();
            Connection conex = conec.getConexion2();
            for(int i=0; i< this.renovacionesSeleccionadas.size(); i++){
                DetalleRenovacion renovacionSeleccionada = this.renovacionesSeleccionadas.get(i);
                /////////////////
                try{
                    Query_C query = new Query_C();
                    String s = query.generar_Consulta_Desasignar_Renovacion(Integer.parseInt(renovacionSeleccionada.getIdposventa()),
                            this.getAtributoSession(Constantes.SS_USUARIO));
                    CallableStatement cstmt = conex.prepareCall(s);
                    cstmt.executeQuery();
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            try {
                conex.close();
                JsfUtil.addSuccessMessage(Constantes.MSG_SUCCESS_DESASIGNA_RENOVACION);//mensaje success
            } catch (SQLException ex) {
            }
            int idAsesorSeleccionado = this.getIdAsesorSelelected();
            this.listaRenovaciones = this.getRenovacionesCoordinador(idAsesorSelelected);
        } catch (SQLException ex) {
            Logger.getLogger(renovacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
     
    public List<Coordinador> obtenerListadeCoordinador(){
        List<Coordinador> listaCoordinador = new ArrayList<Coordinador>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion2();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Query_C query = new Query_C();
            query.generar_ConsultaCoordRenovacion(this.getAtributoSession(Constantes.SS_ROL).equals("COORD"));
            String mi =query.getConsulta_CoordinadorAntigua();
            
            rs1 = st1.executeQuery(query.getConsulta_CoordinadorAntigua());
            
            while (rs1.next())
            {
                Coordinador coor = new Coordinador();
                coor.setNombre((rs1.getString("NOMBRE")));
                coor.setIdCoordinador(Integer.parseInt(rs1.getString("ID")));
                coor.setUsuario_dominio(rs1.getString("USUARIO_DOMINIO"));
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
    
    
    public void customXLSProceso(Object document) {
        ExcelConfig config = new ExcelConfig();
        boolean estadoSeleccionAsesor=false;
        String nombreAsesor=null;
        
        if(this.idAsesorSelelected>0){
            nombreAsesor = this.listaAsesor.get(this.idAsesorSelelected);
            estadoSeleccionAsesor=true;
        }
                
        config.configurarExcelRenovacion(document, estadoSeleccionAsesor, nombreAsesor);
    }
     
    
    
     
    public Map<Integer, String> obtenerListadeAsesor(String nombreCoordinador){        
        listaAsesor = new LinkedHashMap<Integer,String>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion2();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            Query_C query = new Query_C();  
            query.generar_Consulta_VendedoresRenovacion(nombreCoordinador, this.getAtributoSession(Constantes.SS_ROL).equals("COORD"));
            String c = query.getConsulta_Vendedores();
            rs1 = st1.executeQuery(query.getConsulta_Vendedores());
            
            while (rs1.next())
            {
                listaAsesor.put(Integer.parseInt(rs1.getString("CODVEND")),(rs1.getString("NOM_VENDEDOR")));         
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }     
        return listaAsesor;        
    }
    
    public List<DetalleRenovacion> getRenovacionesCoordinador(int idAsesorVentas){
        List<DetalleRenovacion> listaRenovaciones = new ArrayList<DetalleRenovacion>();
        Connection conex = null;
        try{                
            Logger logger = Logger.getLogger(getClass().getName());
            logger.info("Llamando query renovacion con id asesor ventas");
            Conexion conec = new Conexion();                
            conex = conec.getConexion2();
            System.out.println("conexion exitosasdfa");
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Query_C query = new Query_C();
            logger.info(query.generar_Consulta_Renovacion(idAsesorVentas));
            rs1 = st1.executeQuery(query.generar_Consulta_Renovacion(idAsesorVentas));
            logger.info("Se ejecutó el query");
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
                detalle.setNit(rs1.getString("Nit"));
                detalle.setNo_cliente(rs1.getString("no_cliente"));
                detalle.setNom_cliente(rs1.getString("nom_cliente"));
                detalle.setPromocion(rs1.getString("promocion"));
                detalle.setMeses_promocion(rs1.getString("meses_promocion"));
                detalle.setMbf_usd(rs1.getString("mbf_en_$usd"));
                detalle.setUpgrades_usd(rs1.getString("upgrades_$usd"));
                this.setSumatoriaMBFUSCarga(Float.parseFloat(rs1.getString("mbf_en_$usd")));
                this.setSumatoriaUPGRADEUSCarga(Float.parseFloat(rs1.getString("upgrades_$usd")));                
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
        }finally{
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }
        this.cantidadElementos = listaRenovaciones.size();                
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

    public Map<Integer, String> getListaAsesor() {
        return listaAsesor;
    }

    public void setListaAsesor(Map<Integer, String> listaAsesor) {
        this.listaAsesor = listaAsesor;
    }

   
    

    public void btnClickReAsignarRenovaciones(ActionEvent actionEvent){
	String idVentas = "";
        String nombre = this.getNombreCoordinadorSelected();
        this.setAbributoSession(Constantes.SS_CoordinadorAsignar, nombre); /*Nombre del Coordinador seleccionado*/
        for(int i=0; i< this.renovacionesSeleccionadas.size(); i++){
            DetalleRenovacion RenovacionesSeleccioanda = this.renovacionesSeleccionadas.get(i);                     
            /////////////////           
            try{
                if (idVentas.equals("")){
                        idVentas = String.valueOf(RenovacionesSeleccioanda.getIdposventa());
                }
                else
                {
                        idVentas = idVentas + ", " + RenovacionesSeleccioanda.getIdposventa();
                }						
								
            }catch(Exception e){
                
				}            
        }
        try {
                //mensaje de asignado correctamente
                if (idVentas.equals("")){
                    JsfUtil.addSuccessMessage(Constantes.MSG_ERROR_REASIGNACION_RENOVA);
                }
                else{
                    String idAsesor = Integer.toString(this.getIdAsesorSelelected());
                    this.setAbributoSession(Constantes.SS_CoordinadorActualId, idAsesor); /*Id del asesor seleccionado*/
                    this.setAbributoSession(Constantes.SS_RenovacionesReAsignar, idVentas); /*Ventas Seleccionadas para ser Re-Asignadas*/
                    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                    context.redirect(context.getRequestContextPath() + "/faces/RenovacionReAsignar.xhtml");
                }
            } catch (Exception ex) {
                            }
    } 
     
    
}
