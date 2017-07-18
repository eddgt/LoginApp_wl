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
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.bdg.cms_buc.Query_Editar;
import org.bdg.cms_buc.Query_Bitacora;
import org.bdg.utils.JsfUtil;
import org.primefaces.event.data.FilterEvent;
import org.bdg.cms_dto.DetalleBitacora;
import org.bdg.cms_dto.Movimiento;
import org.bdg.exporters.ExcelConfig;
import org.bdg.exporters.ExcelHelper;

/**
 *
 * @author Daniel Mendez
 */
@ManagedBean(name = "ventasVendedorBean")
@ViewScoped
public class VentasVendedorBean extends BaseSession {
   
    
    ////////////////////////////////////////////////////////////////////
    private List<DetalleVenta> listaVentas;
    private List<DetalleVenta> ventasSeleccionadas;
    private List<DetalleVenta> ventasFiltradas;
    private DetalleVenta ventaSeleccionadaDetalle;
    private List<Coordinador> listaCoordinador;
    private List<Asesor> listaAsesor;
    private List<Movimiento> listaMovimientoxVenta;
    private Coordinador coordinadorSelected;
    private Asesor asesorSelected;
    private int idCoordinador;
    private String nombreCoordinadorSelected;
    private int idAsesorSelelected;
    private boolean blnActivoDesAsignar;
    private boolean blnActivoAgregar;
    private boolean blnActivoReAsignar;
    private boolean blnActivoAceptarFinalizar;
    private boolean blnActivoOneSelected;
    private boolean blnActivoSwitchEdicion=false;
    private float sumatoriaCuotaBasica;
    private float sumatoriaEnFiltrosCuotaBasica;
    private float sumatoriaEnCargarCuotaBasica;
    private float sumatoriaValorEnlace;
    private float sumatoriaEnCargaValorEnlace;
    private int cantidadElementos;
    private boolean blnActivoEdicionValCuota =true;
    private boolean blnActivoEdicionValEnlace=true;
    private boolean blnActivoEdicion=true;
    private String valorEnlaceSelected;
    private String nombreVendedor;
    private String usuarioLogueado;

    public String getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(String usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }
    
    

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }
   
    public boolean isBlnActivoEdicionValCuota() {
        return blnActivoEdicionValCuota;
    }

    public void setBlnActivoEdicionValCuota(boolean blnActivoEdicionValCuota) {
        this.blnActivoEdicionValCuota = blnActivoEdicionValCuota;
    }

    public boolean isBlnActivoEdicionValEnlace() {
        return blnActivoEdicionValEnlace;
    }

    public void setBlnActivoEdicionValEnlace(boolean blnActivoEdicionValEnlace) {
        this.blnActivoEdicionValEnlace = blnActivoEdicionValEnlace;
    }

    
    
    
    public List<Movimiento> getListaMovimientoxVenta() {
        return listaMovimientoxVenta;
    }

    public void setListaMovimientoxVenta(List<Movimiento> listaMovimientoxVenta) {
        this.listaMovimientoxVenta = listaMovimientoxVenta;
    }
    
    
    public void cambiarEstadoEdicion() {
        if(this.blnActivoSwitchEdicion){
            float cuotaBasica = Float.parseFloat(this.ventaSeleccionadaDetalle.getCuotaBasica());
            float valEnlace = Float.parseFloat(this.ventaSeleccionadaDetalle.getValorEnlace());
            
            if(cuotaBasica>0){
                this.blnActivoEdicionValCuota=false;
            }
            
            if(valEnlace>0){
                this.blnActivoEdicionValEnlace=false;
            }
            this.blnActivoEdicion=false;
        }else{
            this.blnActivoEdicion=true;
            this.blnActivoEdicionValCuota=true;
            this.blnActivoEdicionValEnlace=true;
        }
    }

    public boolean isBlnActivoSwitchEdicion() {
        return blnActivoSwitchEdicion;
    }

    public void setBlnActivoSwitchEdicion(boolean blnActivoSwitchEdicion) {
        this.blnActivoSwitchEdicion = blnActivoSwitchEdicion;
    }

    public String getValorEnlaceSelected() {
        return valorEnlaceSelected;
    }

    public void setValorEnlaceSelected(String valorEnlaceSelected) {
        this.valorEnlaceSelected = valorEnlaceSelected;
    }
    
    
            
    public boolean isBlnActivoEdicion() {
        return blnActivoEdicion;
    }

    public void setBlnActivoEdicion(boolean blnActivoEdicion) {
        this.blnActivoEdicion = blnActivoEdicion;
    }

    
    public DetalleVenta getVentaSeleccionadaDetalle() {
        return ventaSeleccionadaDetalle;
    }

    public void setVentaSeleccionadaDetalle(DetalleVenta ventaSeleccionadaDetalle) {
        this.blnActivoEdicion=true;
        this.blnActivoSwitchEdicion=false;
        this.blnActivoEdicionValCuota=true;
        this.blnActivoEdicionValEnlace=true;
        this.listaMovimientoxVenta =obtenerListaxVentaMovimiento(ventaSeleccionadaDetalle.getIdVenta());
        this.ventaSeleccionadaDetalle = ventaSeleccionadaDetalle;
    }

    
    public List<Movimiento> obtenerListaxVentaMovimiento(int identificadorVenta){
        List<Movimiento> mmoLista = new ArrayList<Movimiento>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion2();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Query_Bitacora query = new Query_Bitacora();
            String querys= query.getConsultaVitacoraxVenta(identificadorVenta);
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
    
    
    
    private List<DetalleBitacora> listaBitacora;
    
    public int getCantidadElementos() {
        return cantidadElementos;
    }

    public void setCantidadElementos(int cantidadElementos) {
        this.cantidadElementos = cantidadElementos;
    }
    
    
    
    public float getSumatoriaEnCargaValorEnlace() {
        return sumatoriaEnCargaValorEnlace;
    }

    public void setSumatoriaEnCargaValorEnlace(float sumatoriaEnCargaValorEnlace) {
        this.sumatoriaEnCargaValorEnlace += sumatoriaEnCargaValorEnlace;
    }

    public String getSumatoriaValorEnlace() {
        float valorEnlaceSumatoria =0;
        
        if(this.sumatoriaEnCargaValorEnlace!=0){
            valorEnlaceSumatoria = this.sumatoriaEnCargaValorEnlace;            
        }
                        
        if(this.sumatoriaValorEnlace !=0){
            valorEnlaceSumatoria = this.sumatoriaValorEnlace;
        }
        
        this.sumatoriaValorEnlace=0;
        this.sumatoriaEnCargaValorEnlace=0;
    
        return this.retornarDecimal(valorEnlaceSumatoria);
    }

    public void setSumatoriaValorEnlace(float sumatoriaValorEnlace) {
        this.sumatoriaValorEnlace = sumatoriaValorEnlace;
    }
    
    
    
    public List<DetalleVenta> getVentasFiltradas() {
        return ventasFiltradas;
    }

    public void setVentasFiltradas(List<DetalleVenta> ventasFiltradas) {
        this.ventasFiltradas = ventasFiltradas;
    }

    
    
    public boolean isBlnActivoOneSelected() {
        return blnActivoOneSelected;
    }

    public void setBlnActivoOneSelected(boolean blnActivoOneSelected) {
        this.blnActivoOneSelected = blnActivoOneSelected;
    }

    public float getSumatoriaEnCargarCuotaBasica() {
        return sumatoriaEnCargarCuotaBasica;
    }

    public void setSumatoriaEnCargarCuotaBasica(float sumatoriaEnCargarCuotaBasica) {
        this.sumatoriaEnCargarCuotaBasica += sumatoriaEnCargarCuotaBasica;
    }

    public String getSumatoriaCuotaBasica() {       
        float valorSumatoria = 0;
        
        
        if(this.sumatoriaEnCargarCuotaBasica!=0){
            valorSumatoria = this.sumatoriaEnCargarCuotaBasica;            
        }
                        
        if(this.sumatoriaEnFiltrosCuotaBasica !=0){
            valorSumatoria = this.sumatoriaEnFiltrosCuotaBasica;
        }
        
        this.sumatoriaCuotaBasica=0;
        this.sumatoriaEnFiltrosCuotaBasica=0;
        
        return this.retornarDecimal(valorSumatoria);       
    }
    
    
    public void customXLSProceso(Object document) {
        ExcelConfig config = new ExcelConfig();           
        String nombreVendedor = this.getAtributoSession(Constantes.VEND_NAME);                                                  
        config.configurarExcelVentas(document, true, nombreVendedor);
    }
    
    
    
    public void sumarCuotaBasicaFiltradas(FilterEvent filterEvent){                
        float sumatoria=0;
        float sumatoriaValorEnlace=0;
        if(this.ventasFiltradas!=null){          
            for(int i=0; i< this.ventasFiltradas.size(); i++){                  
                sumatoria += Float.parseFloat(this.ventasFiltradas.get(i).getCuotaBasica());
                sumatoriaValorEnlace += Float.parseFloat(this.ventasFiltradas.get(i).getValorEnlace());
            }                      
        }
        this.cantidadElementos =0;
        this.sumatoriaEnCargarCuotaBasica=0;
        this.sumatoriaEnCargaValorEnlace = 0;
        
        if(this.ventasFiltradas!=null){    
            this.cantidadElementos=ventasFiltradas.size();
        }        
        this.sumatoriaEnFiltrosCuotaBasica=sumatoria;
        this.sumatoriaValorEnlace = sumatoriaValorEnlace;
        
    }
    

    public void setSumatoriaCuotaBasica(String sumatoriaCuotaBasica) {
        if(!sumatoriaCuotaBasica.equals("0") && Float.parseFloat(sumatoriaCuotaBasica)!=0){
            this.sumatoriaCuotaBasica += Float.parseFloat(sumatoriaCuotaBasica);
        }       
    }  
          
    public void setBlnActivoDesAsignar(boolean blnActivoDesAsignar) {
        this.blnActivoDesAsignar = blnActivoDesAsignar;
    }
    
    public boolean isBlnActivoDesAsignar(){
        return blnActivoDesAsignar;
    }
            
            
    public boolean isBlnActivoAgregar() {
        return blnActivoAgregar;
    }

    public void setBlnActivoAgregar(boolean blnActivoAgregar) {
        this.blnActivoAgregar = blnActivoAgregar;
    }

    public boolean isblnActivoReAsignar() {
        return blnActivoReAsignar;
    }

    public void setblnActivoReAsignar(boolean blnActivoReAsignar) {
        this.blnActivoReAsignar = blnActivoReAsignar;
    }
    
    public boolean isBlnActivoAceptarFinalizar() {
        return blnActivoAceptarFinalizar;
    }

    public void setBlnActivoAceptarFinalizar(boolean blnActivoAceptarFinalizar) {
        this.blnActivoAceptarFinalizar = blnActivoAceptarFinalizar;
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
    public VentasVendedorBean() {
       
    }
    
    public void oneCoordinadorChange() {        
        String nombreCoordinadorFiltro = this.getNombreCoordinadorSelected();
        if(nombreCoordinadorFiltro!=null){
            this.listaAsesor = this.obtenerListadeAsesor(nombreCoordinadorFiltro);
            this.setBlnActivoAgregar(false);
        }else{
            this.setBlnActivoAgregar(true);
        }
        //listaMunicipio = abc.listMunicipiosByDepto(departamentoSelected);
    }
    
    public void oneAsesorChange(){
        int idAsesorSeleccionado = this.getIdAsesorSelelected();
        if(idAsesorSeleccionado>0){
            this.sumatoriaEnFiltrosCuotaBasica =0;
            this.sumatoriaEnCargarCuotaBasica =0;
            this.listaVentas = this.getVentasCoordinador(idAsesorSeleccionado);
        
        }else{
            
            this.listaVentas=null;
            
        }   
    }
     
    
    public String retornarDecimal(float numero){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return df.format(numero);        
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
                fecha="-";
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

    @PostConstruct
    public void init() {           
        coordinadorSelected = new Coordinador();
        asesorSelected = new Asesor();       
        ////////////////////////// 
        String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
        String nombreVendedor = this.getAtributoSession(Constantes.VEND_NAME);
        String rolUsuarioLogueado = this.getAtributoSession(Constantes.SS_ROL);
        String codVendedor = this.getAtributoSession(Constantes.ID_VEND);
        listaCoordinador = this.obtenerListadeCoordinador();        
        this.nombreVendedor = nombreVendedor;
        this.usuarioLogueado = usuarioLogueado;
        if(usuarioLogueado!=null && rolUsuarioLogueado!=null && codVendedor!=null){
           this.idAsesorSelelected = Integer.parseInt(codVendedor);
           this.oneAsesorChange();
           this.setBlnActivoOneSelected(true);
        }else{
            try{
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                context.redirect(context.getRequestContextPath() + "/");
                }catch(Exception e){            
            }             
        }      
    }
    
    public void btnClickAsignarVentas(ActionEvent actionEvent){
        try{
            String nombre = this.getNombreCoordinadorSelected();
            this.setAbributoSession(Constantes.SS_CoordinadorAsignar, nombre);
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/ventasAsignar.xhtml");    
            JsfUtil.addSuccessMessage(Constantes.MSG_SUCCESS_ASIGNA_VENTA);
        }catch(Exception e){
            e.getMessage();
        }
        
    }     

    
     public void btnGuardarValor(ActionEvent actionEvent){
         
        try{
            
            Conexion conec = new Conexion();
            Connection conex = conec.getConexion2();
            String errorSQL="";
            String query = Query_Editar.getQueryEditarMonto();
            try{
                
                int ventaSelect = ((this.ventaSeleccionadaDetalle.getIdVenta()));
                String montoOperacion;
                CallableStatement cstmt = conex.prepareCall(query);
                cstmt.registerOutParameter(1,  java.sql.Types.INTEGER);
                //String idVenta, String numeroOperacion, String modulo, String monto)
                if(!this.blnActivoEdicionValCuota){
                    montoOperacion = this.ventaSeleccionadaDetalle.getCuotaBasica();
                    cstmt.setInt(2, ventaSelect);
                    cstmt.setInt(3, 1);
                    cstmt.setString(4, "VENTA");
                    cstmt.setFloat(5, Float.parseFloat(montoOperacion));
                }
                
                if(!this.blnActivoEdicionValEnlace){
                    montoOperacion = this.ventaSeleccionadaDetalle.getValorEnlace();
                    cstmt.setInt(2, ventaSelect);
                    cstmt.setInt(3, 2);
                    cstmt.setString(4, "VENTA");
                    cstmt.setFloat(5, Float.parseFloat(montoOperacion));
                }
                
                cstmt.execute();
                int valorRetorno = cstmt.getInt(1);
                cstmt.close();
                
                if(valorRetorno!=0){
                    JsfUtil.addSuccessMessage(Constantes.MSG_SUCCESS_CAMBIARMONTO);
                    this.blnActivoEdicion=true;
                    this.blnActivoSwitchEdicion=false;
                    this.blnActivoEdicionValCuota=true;
                    this.blnActivoEdicionValEnlace=true;
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
        }catch(SQLException ex){
            Logger.getLogger(VentasVendedorBean.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }   
     
    public void btnClickReAsignarVentas(ActionEvent actionEvent){
	String idVentas = "";
        String nombre = this.getNombreCoordinadorSelected();
        this.setAbributoSession(Constantes.SS_CoordinadorAsignar, nombre); /*Nombre del Coordinador seleccionado*/
        for(int i=0; i< this.ventasSeleccionadas.size(); i++){
            DetalleVenta ventaSeleccioanda = this.ventasSeleccionadas.get(i);                     
            /////////////////           
            try{
                if (idVentas.equals("")){
                        idVentas = String.valueOf(ventaSeleccioanda.getIdVenta());
                }
                else
                {
                        idVentas = idVentas + ", " + ventaSeleccioanda.getIdVenta();
                }						
								
            }catch(Exception e){
                
				}            
        }
        try {
                //mensaje de asignado correctamente
                if (idVentas.equals("")){
                    JsfUtil.addSuccessMessage(Constantes.MSG_ERROR_REASIGNACION);
                }
                else{
                    String idAsesor = Integer.toString(this.getIdAsesorSelelected());
                    this.setAbributoSession(Constantes.SS_CoordinadorActualId, idAsesor); /*Id del asesor seleccionado*/
                    this.setAbributoSession(Constantes.SS_VentasReAsignar, idVentas); /*Ventas Seleccionadas para ser Re-Asignadas*/
                    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                    context.redirect(context.getRequestContextPath() + "/faces/ventaReAsignar.xhtml");
                }
            } catch (Exception ex) {
                            }
    } 
    
    public void btnClickCongelar(ActionEvent actionEvent){
        try{
            for (Asesor a: this.getListaAsesor()){
                if(a.getIdAsesor()==this.getIdAsesorSelelected()){
                    this.setAbributoSession(Constantes.SS_VendedorCongelar, a.getNombre());
                    this.setAbributoSession(Constantes.SS_VendedorCongelarId, a.getIdAsesor()+"");
                    this.setAbributoSession(Constantes.SS_PaginaFinalizaVentaRenovacion,"VENTAS");
                    System.out.println(a.getIdAsesor());
                    break;
                }
            }
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/congelar.xhtml");           
        }catch(Exception e){
        e.printStackTrace();
        }
        
    }
       
       
    public void btnClickDesasignarVentas(ActionEvent actionEvent) {
        try {
            Conexion conec = new Conexion();
            Connection conex = conec.getConexion2();
            for(int i=0; i< this.ventasSeleccionadas.size(); i++){
                DetalleVenta ventaSeleccioanda = this.ventasSeleccionadas.get(i);
                /////////////////
                try{
                    Query_C query = new Query_C();
                    query.generar_Consulta_CambioEstado(ventaSeleccioanda.getIdVenta());
                    CallableStatement cstmt = conex.prepareCall(query.getModificar_Estado());
                    cstmt.executeQuery();
                    
                }catch(Exception e){
                    
                }
            }
            try {
                conex.close();
                //mensaje de asignado correctamente
                JsfUtil.addSuccessMessage(Constantes.MSG_SUCCESS_DESASIGNA_VENTA);
            } catch (SQLException ex) {
            }
            int idAsesorSeleccionado = this.getIdAsesorSelelected();
            this.listaVentas = this.getVentasCoordinador(idAsesorSelelected);
        } catch (SQLException ex) {
            Logger.getLogger(VentasVendedorBean.class.getName()).log(Level.SEVERE, null, ex);
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
            query.generar_Consulta_Coordinador_An();
                       
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
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaCoordinador;
    }
    
    public List<Asesor> obtenerListadeAsesor(String nombreCoordinador){        
        List<Asesor> listaAsesor = new ArrayList<Asesor>();
        Connection conex = null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion2();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            Query_C query = new Query_C();  
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
            String error = e.toString();
        }finally{
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }
        return listaAsesor;        
    }
     
    
    
    public List<DetalleVenta> getVentasCoordinador(int idAsesortoVentas){
        List<DetalleVenta> listaVentas = new ArrayList<DetalleVenta>();
        Connection conex = null;
        this.cantidadElementos =0;
        try{                
            Conexion conec = new Conexion();                
            conex = conec.getConexion2();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Query_C query = new Query_C();
            query.generar_Consulta_Parametros(idAsesortoVentas);
            String ql = query.getConsulta_InformacionVentas();
            rs1 = st1.executeQuery(query.getConsulta_InformacionVentas());
            int contador=0;
            while (rs1.next())
            {                
                DetalleVenta detalle = new DetalleVenta();
                detalle.setIdCliente((rs1.getInt("CodCliente")));
                detalle.setNIT((rs1.getString("NIT")));
                detalle.setNombreCliente((rs1.getString("nom_cliente")));
                detalle.setFecha(this.retornarFecha(rs1.getString("FECHA")));                 
                String noInstalacion = rs1.getString(("NO_INSTALACION"));
                if(noInstalacion!=null){
                    detalle.setNoInstalacion((rs1.getString(("NO_INSTALACION"))));
                }else{
                    detalle.setNoInstalacion((""));
                }                
                detalle.setTelefono(rs1.getString("NUMTELEFONO"));
                detalle.setAnexoNum(rs1.getString(("ANEXO_NUM")));
                detalle.setTipoProd(rs1.getString("TIPO_PRODUCTO"));
                detalle.setTipoTransaccion(rs1.getString("TIPO_TRANSACCION"));
                detalle.setPlazo((rs1.getString(("PLAZO"))));
                detalle.setMoneda(rs1.getString(("MONEDA")));
                detalle.setCuotaBasica((rs1.getString("CUOTA_BASICA")));                
                this.setSumatoriaEnCargarCuotaBasica(Float.parseFloat(rs1.getString("CUOTA_BASICA")));
                this.setSumatoriaEnCargaValorEnlace(Float.parseFloat(rs1.getString("VALOR_ENLACE")));               
                detalle.setValorEnlace(rs1.getString("VALOR_ENLACE"));
                detalle.setIdVenta(Integer.parseInt(rs1.getString("IDVENTA")));
                detalle.setOrigen(rs1.getString("Origen"));
                detalle.setProducto(rs1.getString("Producto"));
                                        
                listaVentas.add(detalle);
            }
        }catch(Exception e){
            String erss = e.toString();
            int i =0;
        }
        try {
            conex.close();
        } catch (SQLException ex) {
            String ers = ex.toString();
        }
        this.cantidadElementos = listaVentas.size();
        return listaVentas;
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
    
    public List<DetalleVenta> getVentasSeleccionadas() {
        return ventasSeleccionadas;
    }

    public void setVentasSeleccionadas(List<DetalleVenta> ventasSeleccionadas) {
        this.ventasSeleccionadas = ventasSeleccionadas;
    }
    
    
    public List<DetalleVenta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<DetalleVenta> listaVentas) {
        this.listaVentas = listaVentas;
    }

    public List<DetalleBitacora> getListaBitacora() {
        return listaBitacora;
    }

    public void setListaBitacora(List<DetalleBitacora> listaBitacora) {
        this.listaBitacora = listaBitacora;
    }
        
}
