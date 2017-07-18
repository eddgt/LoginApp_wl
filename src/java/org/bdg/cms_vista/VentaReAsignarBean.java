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
import org.bdg.exporters.ExcelConfig;
import org.bdg.exporters.ExcelHelper;
import org.bdg.utils.JsfUtil;
import org.primefaces.event.data.FilterEvent;
/**
 *
 * @author Daniel Mendez
 */
@ManagedBean(name = "ventaReAsignarBean")
@ViewScoped
public class VentaReAsignarBean extends BaseSession {
   
    
    ////////////////////////////////////////////////////////////////////
    private List<DetalleVenta> listaVentas;
    private List<DetalleVenta> ventasSeleccionadas;
    private List<DetalleVenta> ventasFiltradas;
    
    private List<Coordinador> listaCoordinador;
    private List<Asesor> listaAsesors;
    private Map<Integer, String> listaAsesor;    
  
    private Coordinador coordinadorSelected;
    private Asesor asesorSelected;
    private int idCoordinador;
    private String nombreCoordinadorSelected;
    private int idAsesorSelelected;
    private String idAsesorAnterior;
    private boolean blnReAsignar;
    private boolean blnReAsignarSuperior;
    private boolean blnRegresar;
    private boolean blnActivoAceptarFinalizar;
    private boolean blnActivoOneSelected;
    private float sumatoriaCuotaBasica;
    private float sumatoriaEnFiltrosCuotaBasica;
    private float sumatoriaEnCargarCuotaBasica;
    private float sumatoriaValorEnlace;
    private float sumatoriaEnCargaValorEnlace;
    private int cantidadElementos;
    private String idVentasReAsignar;    
    private String justificacion;

    public Map<Integer, String> getListaAsesor() {
        return listaAsesor;
    }

    public void setListaAsesor(Map<Integer, String> listaAsesor) {
        this.listaAsesor = listaAsesor;
    }
    
    
    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }
    
    
    public String getIdAsesorAnterior() {
        return idAsesorAnterior;
    }

    public void setIdAsesorAnterior(String idAsesorAnterior) {
        this.idAsesorAnterior = idAsesorAnterior;
    }

    public String getIdVentasReAsignar() {
        return idVentasReAsignar;
    }

    public void setIdVentasReAsignar(String idVentasReAsignar) {
        this.idVentasReAsignar = idVentasReAsignar;
    }

    public boolean isBlnReAsignar() {
        return blnReAsignar;
    }

    public void setBlnReAsignar(boolean blnReAsignar) {
        this.blnReAsignar = blnReAsignar;
    }

    public boolean isBlnReAsignarSuperior() {
        return blnReAsignarSuperior;
    }

    public void setBlnReAsignarSuperior(boolean blnReAsignarSuperior) {
        this.blnReAsignarSuperior = blnReAsignarSuperior;
    }
    
    public boolean isBlnRegresar() {
        return blnRegresar;
    }

    public void setBlnRegresar(boolean blnRegresar) {
        this.blnRegresar = blnRegresar;
    }

    public boolean isBlnActivoOneSelected() {
        return blnActivoOneSelected;
    }

    public void setBlnActivoOneSelected(boolean blnActivoOneSelected) {
        this.blnActivoOneSelected = blnActivoOneSelected;
    }

    
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
    public VentaReAsignarBean() {
       
    }
    
    public void oneCoordinadorChange() {        
        String nombreCoordinadorFiltro = this.getNombreCoordinadorSelected();
        if(nombreCoordinadorFiltro!=null){
            this.listaAsesor = this.obtenerListadeAsesor(nombreCoordinadorFiltro);
            this.setBlnReAsignar(false);
        }else{
            this.setBlnReAsignar(true);
        }
        //listaMunicipio = abc.listMunicipiosByDepto(departamentoSelected);
    }
    
    public void oneAsesorChange(){
        int idAsesorSeleccionado = this.getIdAsesorSelelected();
        if(idAsesorSeleccionado>0){
            this.setBlnReAsignar(false);
            this.setBlnActivoAceptarFinalizar(false);
        }else{
            this.setBlnActivoAceptarFinalizar(true);
            this.setBlnReAsignar(true);
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
        this.setIdAsesorAnterior(this.getAtributoSession(Constantes.SS_CoordinadorActualId)); 
        this.setIdVentasReAsignar(this.getAtributoSession(Constantes.SS_VentasReAsignar));
        String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
        String rolUsuarioLogueado = this.getAtributoSession(Constantes.SS_ROL);
        listaCoordinador = this.obtenerListadeCoordinador();        
       
        if(usuarioLogueado!=null && rolUsuarioLogueado!=null){
            for(int i =0; i< listaCoordinador.size(); i++){
                if(listaCoordinador.get(i).getUsuario_dominio().equals(usuarioLogueado.toUpperCase())){
                    this.setNombreCoordinadorSelected(listaCoordinador.get(i).getNombre());
                    this.listaAsesor = this.obtenerListadeAsesor(listaCoordinador.get(i).getNombre());
                    break;
                }
            }
            this.oneCoordinadorChange();
            if(rolUsuarioLogueado.equals("COORD")){
                this.setBlnActivoOneSelected(true);
            }
            this.setBlnReAsignar(true);
            this.setBlnReAsignarSuperior(true);
            this.setBlnActivoAceptarFinalizar(true);
            String idsVentasReAsignar = this.getIdVentasReAsignar();
            this.listaVentas = this.getVentasReAsignar(idsVentasReAsignar);                            
        }else{
            try{
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                context.redirect(context.getRequestContextPath() + "/");
                }catch(Exception e){            
            }             
        }      
    }
    
    public void btnClickReAsignarVentas(ActionEvent actionEvent){
        try {
            Conexion conec = new Conexion();
            Connection conex = conec.getConexion2();
            String IdAsesorAnterior = this.getIdAsesorAnterior();
            String idAsesorSeleccionado = Integer.toString(this.getIdAsesorSelelected());
            String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
            
            int RegistroSeleccionados = 0;
            String Observ = this.getJustificacion();
            
            RegistroSeleccionados = this.ventasSeleccionadas.size();
            
            if (Observ.equals("") == false){
                if (Observ.length()>0 && Observ.length()<= 150){
                    if (this.ventasSeleccionadas.size()> 0)
                    {   for(int i=0; i< this.ventasSeleccionadas.size(); i++){
                        DetalleVenta ventaSeleccionada = this.ventasSeleccionadas.get(i);
                        /////////////////
                        try{
                            Query_C query = new Query_C();
                            query.generar_Consulta_ReAsignaVenta(ventaSeleccionada.getIdVenta(), idAsesorAnterior, idAsesorSeleccionado, usuarioLogueado);
                            CallableStatement cstmt = conex.prepareCall(query.getConsulta_ReAsignaVenta());
                            cstmt.executeQuery();
                            
                            String Idventa = Integer.toString(ventaSeleccionada.getIdVenta());
                            query.generar_InsertaBitacoraJustificacion(Idventa, Observ, usuarioLogueado, "VN");
                            String InsertBitacora = query.getConsulta_InsertaBitacoraJustificacion();
                            CallableStatement bitacora = conex.prepareCall(InsertBitacora);
                            bitacora.executeQuery();
                        }catch(Exception e){
                        }
                    }
                    try {
                        conex.close();
                        //mensaje de asignado correctamente
                        JsfUtil.addSuccessMessage(Constantes.MSG_SUCCESS_REASIGNA_VENTA);
                    } catch (SQLException ex) {
                    }
                    //this.listaVentas = this.getVentasCoordinador(idAsesorSelelected);
                    //this.setJustificacion("");
                    try{ // Redirecciona a la pagina de renovaciones
                        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                        context.redirect(context.getRequestContextPath() + "/faces/ventasCoordinador.xhtml");
                    }catch(Exception e){
                    }
                    }
                    else{
                        try {
                            conex.close();
                            //mensaje de asignado correctamente
                            JsfUtil.addSuccessMessage(Constantes.MSG_ERROR_REASIGNACION);
                        } catch (SQLException ex) {
                        }
                        
                    }
                }else{
                    JsfUtil.addSuccessMessage(Constantes.MSG_ERROR_ASIGNA_TAMANIO);
                }
            }else{
                JsfUtil.addSuccessMessage(Constantes.MSG_ERROR_ASIGNA);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentaReAsignarBean.class.getName()).log(Level.SEVERE, null, ex);
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
    
     public Map<Integer, String> obtenerListadeAsesor(String nombreCoordinador){          
        listaAsesor = new LinkedHashMap<Integer,String>();
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
                 listaAsesor.put(Integer.parseInt(rs1.getString("CODVEND")),(rs1.getString("NOM_VENDEDOR")));                       
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
                detalle.setValorEnlace((rs1.getString("VALOR_ENLACE")));
                detalle.setIdVenta((Integer.parseInt(rs1.getString("IDVENTA"))));
                detalle.setOrigen((rs1.getString("Origen")));
                detalle.setProducto((rs1.getString("Producto")));
                listaVentas.add(detalle);
            }
        }catch(Exception e){
        
        
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        this.cantidadElementos = listaVentas.size();
        return listaVentas;
    }

        public List<DetalleVenta> getVentasReAsignar(String idsVentasReAsignar){
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
            //query.generar_Consulta_VentasReAsignar(idsVentasReAsignar);
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
                detalle.setValorEnlace((rs1.getString("VALOR_ENLACE")));
                detalle.setIdVenta((Integer.parseInt(rs1.getString("IDVENTA"))));
                detalle.setOrigen((rs1.getString("Origen")));
                detalle.setProducto((rs1.getString("Producto")));
                listaVentas.add(detalle);
            }
        }catch(Exception e){
        
        
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        this.cantidadElementos = listaVentas.size();
        return listaVentas;
    }
        
    public void btnClickRegresar(ActionEvent actionEvent){
        try{          
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/ventasCoordinador.xhtml");           
        }catch(Exception e){
        
        }    
    }
    
    public List<Coordinador> getListaCoordinador() {
        return listaCoordinador;
    }

    public void setListaCoordinador(List<Coordinador> listaCoordinador) {
        this.listaCoordinador = listaCoordinador;
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
    
    
    public void customXLSProceso(Object document) {
       ExcelConfig config = new ExcelConfig();
        boolean estadoSeleccionAsesor=false;
        String nombreAsesor=null;
        
        if(this.idAsesorSelelected>0){
            nombreAsesor = this.listaAsesor.get(this.idAsesorSelelected);
            estadoSeleccionAsesor=true;
        }
                
        config.configurarExcelVentas(document, estadoSeleccionAsesor, nombreAsesor);
    }
    
    
    
}


