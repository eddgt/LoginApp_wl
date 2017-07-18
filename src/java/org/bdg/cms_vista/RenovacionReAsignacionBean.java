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
import org.bdg.cms_dto.DetalleRenovacion;
import org.bdg.cms_conexion.Conexion;
import org.bdg.cms_buc.Query_C;
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
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
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
@ManagedBean(name = "RenovacionReAsignacionBean")
@ViewScoped
public class RenovacionReAsignacionBean extends BaseSession {
   
    private List<DetalleRenovacion> listaRenovaciones;
    private List<DetalleRenovacion> renovacionesSeleccionadas;
    private List<DetalleRenovacion> renovacionesFiltradas;
    
    
    ////////////////////////////////////////////////////////////////////
    private List<Coordinador> listaCoordinador;
    private List<Asesor> listaAsesors;
    private Coordinador coordinadorSelected;
    private Asesor asesorSelected;
    private int idCoordinador;
    private String nombreCoordinadorSelected;
    private int idAsesorSelelected;
    private String idAsesorAnterior;    
    private boolean blnActivoDesAsignar;
    private boolean blnActivoAgregar;
    private boolean blnReAsignar;
    private boolean blnActivoAceptarFinalizar;
    private boolean disableCordinador;
    private int cantidadElementos;
    private String idRenovacionesReAsignar;
    private boolean blnRegresar;
    private String justificacion;
    private Map<Integer, String> listaAsesor;    
     
    public Map<Integer, String> getListaAsesor() {
        return listaAsesor;
    }

    public void setListaAsesor(Map<Integer, String> listaAsesor) {
        this.listaAsesor = listaAsesor;
    }
    
    public String getJustificacion() {
        return justificacion;
    }

    ///////////////////////// Filtros ////////////////
    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public List<DetalleRenovacion> getRenovacionesFiltradas() {
        return renovacionesFiltradas;
    }

    public void setRenovacionesFiltradas(List<DetalleRenovacion> renovacionesFiltradas) {
        this.renovacionesFiltradas = renovacionesFiltradas;
    }

    public String getIdRenovacionesReAsignar() {
        return idRenovacionesReAsignar;
    }

    public void setIdRenovacionesReAsignar(String idRenovacionesReAsignar) {
        this.idRenovacionesReAsignar = idRenovacionesReAsignar;
    }

    public boolean isBlnRegresar() {
        return blnRegresar;
    }

    public void setBlnRegresar(boolean blnRegresar) {
        this.blnRegresar = blnRegresar;
    }

    public boolean isBlnReAsignar() {
        return blnReAsignar;
    }

    public void setBlnReAsignar(boolean blnReAsignar) {
        this.blnReAsignar = blnReAsignar;
    }

    public String getIdAsesorAnterior() {
        return idAsesorAnterior;
    }

    public void setIdAsesorAnterior(String idAsesorAnterior) {
        this.idAsesorAnterior = idAsesorAnterior;
    }
    
    public int getCantidadElementos() {
        return cantidadElementos;
    }

    public void setCantidadElementos(int cantidadElementos) {
        this.cantidadElementos = cantidadElementos;
    }
    
     
    public String retornarDecimal(float numero){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return df.format(numero);        
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

    public RenovacionReAsignacionBean() {
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
            this.setBlnActivoAceptarFinalizar(false);
            this.setBlnReAsignar(false);
        }else{
            this.setBlnActivoAceptarFinalizar(true);
            this.setBlnReAsignar(true);
            /*this.listaRenovaciones=null;*/
        }
    }
     
     
    @PostConstruct
    public void init() {
        coordinadorSelected = new Coordinador();
        asesorSelected = new Asesor();       
        ////////////////////////// 
        this.setNombreCoordinadorSelected(this.getAtributoSession(Constantes.SS_CoordinadorAsignar));        
        this.setIdAsesorAnterior(this.getAtributoSession(Constantes.SS_CoordinadorActualId)); 
        this.setIdRenovacionesReAsignar(this.getAtributoSession(Constantes.SS_RenovacionesReAsignar));
        String idRe = this.getIdRenovacionesReAsignar();
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
            for (Coordinador c : listaCoordinador){
                if (c.getUsuario_dominio().equals(usuarioLogueado.toUpperCase())){
                    this.setNombreCoordinadorSelected(c.getNombre());
                    break;
                }
            }             
            this.oneCoordinadorChange();
            if (rolUsuarioLogueado.equals("COORD")){
                this.setBlnActivoAceptarFinalizar(true);
                this.setBlnReAsignar(true);
                this.setDisableCordinador(true);
                this.setBlnActivoAgregar(false);
            }
            String idsRenovacionesReAsignar = this.getIdRenovacionesReAsignar();
            this.listaRenovaciones = this.getRenovacionesReAsignar(idsRenovacionesReAsignar); 
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
            
        }finally{
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        return listaCoordinador;
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
            query.generar_Consulta_Vendedores(nombreCoordinador, this.getAtributoSession(Constantes.SS_ROL).equals("COORD"));
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
        }finally{
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }
        this.cantidadElementos = listaRenovaciones.size();                
        return listaRenovaciones;
    }

    public List<DetalleRenovacion> getRenovacionesReAsignar(String idsRenovaciones){
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
            logger.info(query.generar_Consulta_ReAsignaRenovacion(idsRenovaciones));
            rs1 = st1.executeQuery(query.generar_Consulta_ReAsignaRenovacion(idsRenovaciones));
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
        }finally{
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }
        this.cantidadElementos = listaRenovaciones.size();                
        return listaRenovaciones;
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
        
//        HSSFWorkbook wb = (HSSFWorkbook) document;
//        HSSFSheet sheet = wb.getSheetAt(0);
//       
//        
//        HSSFCellStyle style = wb.createCellStyle();
//        HSSFCellStyle styleEntero = wb.createCellStyle();
//        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
//        styleEntero.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
//        
//        List<String> nombreColumnas = new ArrayList<String>();        
//        sheet.shiftRows(0, sheet.getPhysicalNumberOfRows(), 2);
//        //Crear primeras filas
//        Row row0 = sheet.createRow((short)0);
//        Row row1 = sheet.createRow((short)1);
//        /*Row row2 = sheet.createRow((short)2);*/
//        
//        row0.createCell(0).setCellValue("Coordinador: ");
//        if(this.coordinadorSelected!=null && !this.coordinadorSelected.equals("")){
//            row0.createCell(1).setCellValue(this.nombreCoordinadorSelected);
//        }
//        row0.createCell(2).setCellValue("Usuario: " + this.getAtributoSession(Constantes.SS_USUARIO));
//        
//        
//        row1.createCell(0).setCellValue("Asesor: ");
//        if(this.idAsesorSelelected!=0){
//            row1.createCell(1).setCellValue(this.listaAsesor.get(this.idAsesorSelelected));
//            row1.createCell(2).setCellValue("Codigo: " +  this.idAsesorSelelected);
//        }
//       
//        /* for(int i=0; i < nombreColumnas.size();i++) {
//           row2.createCell(i).setCellValue(nombreColumnas.get(i));
//        }*/
//        HSSFRow header = sheet.getRow(2);        
//        int numeroCell = header.getPhysicalNumberOfCells();        
//        for(int noC=0; noC <numeroCell ;noC++) {
//            sheet.autoSizeColumn(noC);
//        }
//        
//        int numeroInicial= 3;
//        int numberOfRows= sheet.getPhysicalNumberOfRows();
//        
//        
//        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
//            HSSFCell cell = header.getCell(i);
//            String nombreColumna = cell.getStringCellValue();
//           
//            if(nombreColumna.equals("MBF en US$")){
//                ExcelHelper.ArreglarColumnasNumber(style, nombreColumna, numeroInicial,numberOfRows, sheet,i);
//            }
//            
//            if(nombreColumna.equals("Upgrades US$")){
//                ExcelHelper.ArreglarColumnasNumber(style, nombreColumna, numeroInicial,numberOfRows, sheet,i);
//            }
//
//             if(nombreColumna.equals("Anexo")){
//                ExcelHelper.ArreglarColumnasNumber(styleEntero, nombreColumna, numeroInicial,numberOfRows, sheet,i);
//            }  
//            
//            if(nombreColumna.equals("Telefono Instalación")){
//               ExcelHelper.ArreglarColumnasNumber(styleEntero, nombreColumna, numeroInicial,numberOfRows, sheet,i);
//            }  
//            
//            if(nombreColumna.equals("Teléfono")){
//               ExcelHelper.ArreglarColumnasNumber(styleEntero, nombreColumna, numeroInicial,numberOfRows, sheet,i);
//            }    
//            
//        }
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

  
    
    public void btnClickReAsignarRenovaciones(ActionEvent actionEvent){
        try {
            Conexion conec = new Conexion();
            Connection conex = conec.getConexion2();
            String IdAsesorAnterior = this.getIdAsesorAnterior();
            String idAsesorSeleccionado = Integer.toString(this.getIdAsesorSelelected());
            String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
            
            int RegistroSeleccionados = 0;
            String Observ = this.getJustificacion();
            
            RegistroSeleccionados = this.renovacionesSeleccionadas.size();
            
            if (Observ.equals("") == false){
                if (Observ.length()>0 && Observ.length()<= 150){
                    if (this.renovacionesSeleccionadas.size()> 0)
                    {   for(int i=0; i< this.renovacionesSeleccionadas.size(); i++){
                        DetalleRenovacion RenovacionSeleccionada = this.renovacionesSeleccionadas.get(i);
                        /////////////////
                        try{
                            Query_C query = new Query_C();
                            query.generar_Consulta_ReAsignaRenovacion(Integer.parseInt(RenovacionSeleccionada.getIdposventa()), idAsesorAnterior, idAsesorSeleccionado, usuarioLogueado);
                            CallableStatement cstmt = conex.prepareCall(query.getConsulta_ReAsignaRenovacion());
                            cstmt.executeQuery();
                            
                            String Idventa = RenovacionSeleccionada.getIdposventa();
                            query.generar_InsertaBitacoraJustificacion(Idventa, Observ, usuarioLogueado, "RE");
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
                    //this.listaRenovaciones = this.getRenovacionesCoordinador(this.getIdAsesorSelelected());
                    try{ // Redirecciona a la pagina de renovaciones
                        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                        context.redirect(context.getRequestContextPath() + "/faces/renovacion.xhtml");
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
            Logger.getLogger(RenovacionReAsignacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }	
     }         
   
    public void btnClickRegresar(ActionEvent actionEvent){
        try{          
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/renovacion.xhtml");           
        }catch(Exception e){
        
        }    
    }
    
}
