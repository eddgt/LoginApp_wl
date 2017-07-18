package org.bdg.cms_vista;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import org.bdg.cms_buc.Query_C;
import org.bdg.database.Conexion;
import org.bdg.cms_dto.ReporteOperacion;
import org.bdg.session.BaseSession;

import javax.faces.bean.ManagedBean;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.bdg.base.Constantes;
import org.bdg.utils.JsfUtil;

import org.bdg.utils.Zip;

/**
 *
 * @author oulloa
 */
@ManagedBean(name = "repOperBean")
@ViewScoped
public class ReporteOperaciones extends BaseSession implements Serializable{
    
    private List<ReporteOperacion> listaReporte;
    private String sistema;
    private String fecha;
    private int idReporte;
    private String fuente;
    private String tipoTransaccion;
    private int codCliente;
    private String clienteMic;
    private String clienteWholesale;
    private String nit;
    private String telefono;
    private int aneIns;
    private int codVenta;
    private String nombreVenta;
    private int montoVenta;
    private String anexoPadre;
    private String ejecutivoVenta;
    private int codVendedor;
    private String coordinador;
    private String gerente;
    private int codDistribuidor;
    private String modelo;
    private String clasiMic;
    private String productoTb;
    private String codVendedorNp;
    private String cliente;
    private String vendedorAs400;
    private String distribuidorAs400;
    private String abVenta;
    private int mesesContrato;
    private int tipoCambio;
    private String tipoOperacion;
    private String productoGlobal;
    private String periodo;
    private boolean blnActivo=false;
    private String File;
    private float tasaCambio;
    private boolean blnTasa=false;
    
    
    
    public List<ReporteOperacion> getListaReporte() {
        return listaReporte;
    }
    
    public void setListaReporte(List<ReporteOperacion> listaReporte) {
        this.listaReporte = listaReporte;
    }
    
    public String getSistema() {
        return sistema;
    }
    
    public void setSistema(String sistema) {
        this.sistema = sistema;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public int getIdReporte() {
        return idReporte;
    }
    
    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }
    
    public String getFuente() {
        return fuente;
    }
    
    public void setFuente(String fuente) {
        this.fuente = fuente;
    }
    
    public String getTipoTransaccion() {
        return tipoTransaccion;
    }
    
    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }
    
    public int getCodCliente() {
        return codCliente;
    }
    
    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }
    
    public String getClienteMic() {
        return clienteMic;
    }
    
    public void setClienteMic(String clienteMic) {
        this.clienteMic = clienteMic;
    }
    
    public String getClienteWholesale() {
        return clienteWholesale;
    }
    
    public void setClienteWholesale(String clienteWholesale) {
        this.clienteWholesale = clienteWholesale;
    }
    
    public String getNit() {
        return nit;
    }
    
    public void setNit(String nit) {
        this.nit = nit;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public int getAneIns() {
        return aneIns;
    }
    
    public void setAneIns(int aneIns) {
        this.aneIns = aneIns;
    }
    
    public int getCodVenta() {
        return codVenta;
    }
    
    public void setCodVenta(int codVenta) {
        this.codVenta = codVenta;
    }
    
    public String getNombreVenta() {
        return nombreVenta;
    }
    
    public void setNombreVenta(String nombreVenta) {
        this.nombreVenta = nombreVenta;
    }
    
    public int getMontoVenta() {
        return montoVenta;
    }
    
    public void setMontoVenta(int montoVenta) {
        this.montoVenta = montoVenta;
    }
    
    public String getAnexoPadre() {
        return anexoPadre;
    }
    
    public void setAnexoPadre(String anexoPadre) {
        this.anexoPadre = anexoPadre;
    }
    
    public String getEjecutivoVenta() {
        return ejecutivoVenta;
    }
    
    public void setEjecutivoVenta(String ejecutivoVenta) {
        this.ejecutivoVenta = ejecutivoVenta;
    }
    
    public int getCodVendedor() {
        return codVendedor;
    }
    
    public void setCodVendedor(int codVendedor) {
        this.codVendedor = codVendedor;
    }
    
    public String getCoordinador() {
        return coordinador;
    }
    
    public void setCoordinador(String coordinador) {
        this.coordinador = coordinador;
    }
    
    public String getGerente() {
        return gerente;
    }
    
    public void setGerente(String gerente) {
        this.gerente = gerente;
    }
    
    public int getCodDistribuidor() {
        return codDistribuidor;
    }
    
    public void setCodDistribuidor(int codDistribuidor) {
        this.codDistribuidor = codDistribuidor;
    }
    
    public String getModelo() {
        return modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public String getClasiMic() {
        return clasiMic;
    }
    
    public void setClasiMic(String clasiMic) {
        this.clasiMic = clasiMic;
    }
    
    public String getProductoTb() {
        return productoTb;
    }
    
    public void setProductoTb(String productoTb) {
        this.productoTb = productoTb;
    }
    
    public String getCodVendedorNp() {
        return codVendedorNp;
    }
    
    public void setCodVendedorNp(String codVendedorNp) {
        this.codVendedorNp = codVendedorNp;
    }
    
    public String getCliente() {
        return cliente;
    }
    
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    public String getVendedorAs400() {
        return vendedorAs400;
    }
    
    public void setVendedorAs400(String vendedorAs400) {
        this.vendedorAs400 = vendedorAs400;
    }
    
    public String getDistribuidorAs400() {
        return distribuidorAs400;
    }
    
    public void setDistribuidorAs400(String distribuidorAs400) {
        this.distribuidorAs400 = distribuidorAs400;
    }
    
    public String getAbVenta() {
        return abVenta;
    }
    
    public void setAbVenta(String abVenta) {
        this.abVenta = abVenta;
    }
    
    public int getMesesContrato() {
        return mesesContrato;
    }
    
    public void setMesesContrato(int mesesContrato) {
        this.mesesContrato = mesesContrato;
    }
    
    public int getTipoCambio() {
        return tipoCambio;
    }
    
    public void setTipoCambio(int tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
    
    public String getTipoOperacion() {
        return tipoOperacion;
    }
    
    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }
    
    public String getProductoGlobal() {
        return productoGlobal;
    }
    
    public void setProductoGlobal(String productoGlobal) {
        this.productoGlobal = productoGlobal;
    }
    
    public String getPeriodo() {
        return periodo;
    }
    
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    public boolean isBlnActivo() {
        return blnActivo;
    }
    
    public void setBlnActivo(boolean blnActivo) {
        this.blnActivo = blnActivo;
        this.blnActivo=true;
    }

    public boolean isBlnTasa() {
        return blnTasa;
    }

    public void setBlnTasa(boolean blnTasa) {
        this.blnTasa = blnTasa;
    }        

    public float getTasaCambio() {
        return tasaCambio;
    }

    public void setTasaCambio(float tasaCambio) {
        this.tasaCambio = tasaCambio;
    }
    
    @PostConstruct
    public void init() {
        this.blnActivo=true;/*inhabilitar por default*/
        this.blnTasa=true;/*inhabilitar por default*/
    }
    
    
    public List<ReporteOperacion> getReporteOperacion(String periodo){
        List<ReporteOperacion> listaReporte = new ArrayList<ReporteOperacion>();
        Connection conex = null;
        //this.cantidadFilas =0;
        try{
            Conexion conec = new Conexion();
            conex = conec.getConexion2();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Query_C query = new Query_C();
            query.generar_Consulta_Reporte(periodo);
            String ql = query.getConsulta_DetalleReporte();
            //rs1 = st1.executeQuery(query.getConsulta_InformacionVentasOperacion());
            rs1 = st1.executeQuery(ql);
            int contador=0;
            while (rs1.next())
            {
                ReporteOperacion reporte = new ReporteOperacion();
                reporte.setId(rs1.getInt("ID"));
                reporte.setSistema(rs1.getString("SISTEMA"));
                reporte.setFuente(rs1.getString("FUENTE"));
                reporte.setTipoTransaccion(rs1.getString("TIPO_TRANSACCION"));
                reporte.setFecha(rs1.getString("FECHA"));
                reporte.setCodCliente(rs1.getInt("COD_CLIENTE"));
                reporte.setClienteMic(rs1.getString("CLIENTE_MIC"));
                reporte.setClienteWholesale(rs1.getString("CLIENTE_WHOLESALE"));
                reporte.setNit(rs1.getString("NIT"));
                reporte.setTelefono(rs1.getString("TELEFONO"));
                reporte.setAneIns(rs1.getInt("ANE_INS"));
                reporte.setCodVenta(rs1.getInt("COD_VENTA"));
                reporte.setNombreVenta(rs1.getString("NOMBRE_VENTA"));
                reporte.setMontoVenta(rs1.getInt("MONTO_VENTA"));
                reporte.setAnexoPadre(rs1.getString("ANEXO_PADRE"));
                reporte.setEjecutivoVenta(rs1.getString("EJECUTIVO_VENTA"));
                reporte.setCodVendedor(rs1.getInt("COD_VENDEDOR"));
                reporte.setCoordinador(rs1.getString("COORDINADOR"));
                reporte.setGerente(rs1.getString("GERENTE"));
                reporte.setCodDistribuidor(rs1.getInt("COD_DISTRIBUIDOR"));
                reporte.setModelo(rs1.getString("MODELO"));
                reporte.setClasiMic(rs1.getString("CLASI_MIC"));
                reporte.setProductoTb(rs1.getString("PRODUCTO_TB"));
                reporte.setCodVendedorNp(rs1.getString("COD_VENDEDOR_NP"));
                reporte.setCliente(rs1.getString("CLIENTE"));
                reporte.setVendedorAs400(rs1.getString("VENDEDOR_AS400"));
                reporte.setDistribuidorAs400(rs1.getString("DISTRIBUIDOR_AS400"));
                reporte.setAbVenta(rs1.getString("AB_VENTA"));
                reporte.setMesesContrato(rs1.getInt("MESES_CONTRATO"));
                reporte.setTipoCambio(rs1.getInt("TIPO_CAMBIO"));
                reporte.setTipoOperacion(rs1.getString("TIPO_OPERACION"));
                reporte.setProductoGlobal(rs1.getString("PRODUCTO_GLOBAL"));
                reporte.setPeriodo(rs1.getString("PERIODO"));
                listaReporte.add(reporte);
                contador++;
            }
            System.out.println("contador: "+contador);
        }catch(Exception e){
            String erss = e.toString();
            int i =0;
        }
        try {
            
            conex.close();
            System.out.println("Cerrando conexion.. getVentasOperacion");
        } catch (SQLException ex) {
            String ers = ex.toString();
        }
        //this.cantidadFilas = listaVentasOperacion.size();
        return listaReporte;
    }
    
    public void btnClickGetReporte(){
        this.listaReporte=this.getReporteOperacion(this.periodo);
    }
    
    /*Metodo pra procesar las peticiones de descarga Excel*/
    public void postProcessXLS(Object document) {
        XSSFWorkbook wb = (XSSFWorkbook) document;
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow header = sheet.getRow(0);
        
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            XSSFCell cell = header.getCell(i);
            cell.setCellStyle(cellStyle);
        }
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
    
    /*Metodo crear mi reporte*/
    public void crearReporte() throws FileNotFoundException, SQLException, IOException  {
        
        Connection conex = null;
        Conexion conec = new Conexion();
        conex = conec.getConexion2();
        String periodoSeleccionado = this.periodo;
        Statement statement = conex.createStatement();
        ResultSet resultSet = statement
                .executeQuery("SELECT * FROM VIEW_TBBO_OPERACIONES WHERE PERIODO = '"+periodoSeleccionado+"'");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook
                .createSheet("mySheet1");
        XSSFRow row=spreadsheet.createRow(1);
        XSSFCell cell;
        cell=row.createCell(1);
        cell.setCellValue("ID");
        cell=row.createCell(2);
        cell.setCellValue("SISTEMA");
        cell=row.createCell(3);
        cell.setCellValue("FUENTE");
        cell=row.createCell(4);
        cell.setCellValue("TIPO_TRANSACCION");
        cell=row.createCell(5);
        cell.setCellValue("FECHA");
        cell=row.createCell(6);
        cell.setCellValue("COD_CLIENTE");
        cell=row.createCell(7);
        cell.setCellValue("CLIENTE_MIC");
        cell=row.createCell(8);
        cell.setCellValue("CLIENTE_WHOLESALE");
        cell=row.createCell(9);
        cell.setCellValue("NIT");
        cell=row.createCell(10);
        cell.setCellValue("TELEFONO");
        cell=row.createCell(11);
        cell.setCellValue("ANE_INS");
        cell=row.createCell(12);
        cell.setCellValue("COD_VENTA");
        cell=row.createCell(13);
        cell.setCellValue("NOMBRE_VENTA");
        cell=row.createCell(14);
        cell.setCellValue("MONTO_VENTA");
        cell=row.createCell(15);
        cell.setCellValue("ANEXO_PADRE");
        cell=row.createCell(16);
        cell.setCellValue("EJECUTIVO_VENTA");
        cell=row.createCell(17);
        cell.setCellValue("COD_VENDEDOR");
        cell=row.createCell(18);
        cell.setCellValue("COORDINADOR");
        cell=row.createCell(19);
        cell.setCellValue("GERENTE");
        cell=row.createCell(20);
        cell.setCellValue("COD_DISTRIBUIDOR");
        cell=row.createCell(21);
        cell.setCellValue("MODELO");
        cell=row.createCell(22);
        cell.setCellValue("CLASI_MIC");
        cell=row.createCell(23);
        cell.setCellValue("PRODUCTO_TB");
        cell=row.createCell(24);
        cell.setCellValue("COD_VENDEDOR_NP");
        cell=row.createCell(25);
        cell.setCellValue("CLIENTE");
        cell=row.createCell(26);
        cell.setCellValue("VENDEDOR_AS400");
        cell=row.createCell(27);
        cell.setCellValue("DISTRIBUIDOR_AS400");
        cell=row.createCell(28);
        cell.setCellValue("AB_VENTA");
        cell=row.createCell(29);
        cell.setCellValue("MESES_CONTRATO");
        cell=row.createCell(30);
        cell.setCellValue("TIPO_CAMBIO");
        cell=row.createCell(31);
        cell.setCellValue("TIPO_OPERACION");
        cell=row.createCell(32);
        cell.setCellValue("PRODUCTO_GLOBAL");
        cell=row.createCell(33);
        cell.setCellValue("PERIODO");
        
        int i=2;
        while(resultSet.next())
        {
            row=spreadsheet.createRow(i);
            cell=row.createCell(1);
            cell.setCellValue(resultSet.getString("ID"));
            cell=row.createCell(2);
            cell.setCellValue(resultSet.getString("SISTEMA"));
            cell=row.createCell(3);
            cell.setCellValue(resultSet.getString("FUENTE"));
            cell=row.createCell(4);
            cell.setCellValue(resultSet.getString("TIPO_TRANSACCION"));
            cell=row.createCell(5);
            cell.setCellValue(resultSet.getString("FECHA"));
            cell=row.createCell(6);
            cell.setCellValue(resultSet.getString("COD_CLIENTE"));
            cell=row.createCell(7);
            cell.setCellValue(resultSet.getString("CLIENTE_MIC"));
            cell=row.createCell(8);
            cell.setCellValue(resultSet.getString("CLIENTE_WHOLESALE"));
            cell=row.createCell(9);
            cell.setCellValue(resultSet.getString("NIT"));
            cell=row.createCell(10);
            cell.setCellValue(resultSet.getString("TELEFONO"));
            cell=row.createCell(11);
            cell.setCellValue(resultSet.getString("ANE_INS"));
            cell=row.createCell(12);
            cell.setCellValue(resultSet.getString("COD_VENTA"));
            cell=row.createCell(13);
            cell.setCellValue(resultSet.getString("NOMBRE_VENTA"));
            cell=row.createCell(14);
            cell.setCellValue(resultSet.getString("MONTO_VENTA"));
            cell=row.createCell(15);
            cell.setCellValue(resultSet.getString("ANEXO_PADRE"));
            cell=row.createCell(16);
            cell.setCellValue(resultSet.getString("EJECUTIVO_VENTA"));
            cell=row.createCell(17);
            cell.setCellValue(resultSet.getString("COD_VENDEDOR"));
            cell=row.createCell(18);
            cell.setCellValue(resultSet.getString("COORDINADOR"));
            cell=row.createCell(19);
            cell.setCellValue(resultSet.getString("GERENTE"));
            cell=row.createCell(20);
            cell.setCellValue(resultSet.getString("COD_DISTRIBUIDOR"));
            cell=row.createCell(21);
            cell.setCellValue(resultSet.getString("MODELO"));
            cell=row.createCell(22);
            cell.setCellValue(resultSet.getString("CLASI_MIC"));
            cell=row.createCell(23);
            cell.setCellValue(resultSet.getString("PRODUCTO_TB"));
            cell=row.createCell(24);
            cell.setCellValue(resultSet.getString("COD_VENDEDOR_NP"));
            cell=row.createCell(25);
            cell.setCellValue(resultSet.getString("CLIENTE"));
            cell=row.createCell(26);
            cell.setCellValue(resultSet.getString("VENDEDOR_AS400"));
            cell=row.createCell(27);
            cell.setCellValue(resultSet.getString("DISTRIBUIDOR_AS400"));
            cell=row.createCell(28);
            cell.setCellValue(resultSet.getString("AB_VENTA"));
            cell=row.createCell(29);
            cell.setCellValue(resultSet.getString("MESES_CONTRATO"));
            cell=row.createCell(30);
            cell.setCellValue(resultSet.getString("TIPO_CAMBIO"));
            cell=row.createCell(31);
            cell.setCellValue(resultSet.getString("TIPO_OPERACION"));
            cell=row.createCell(32);
            cell.setCellValue(resultSet.getString("PRODUCTO_GLOBAL"));
            cell=row.createCell(33);
            cell.setCellValue(resultSet.getString("PERIODO"));
                        
            i++;
        }
        File myfile;
        FileOutputStream out = new FileOutputStream(
                myfile = new File("../temp/ReporteXPeriodo.xlsx")
        );
        
        workbook.write(out);
        out.close();
        workbook.close();
        System.out.println("ReporteXPeriodo.xlsx written successfully");
        
        /*habilitar boton descargar*/
        this.blnActivo=false;
    }
    
    public void crearRepRenovMovil(String nombreReporte, String periodo) throws FileNotFoundException, SQLException, IOException  {
        
        Connection conex = null;
        Conexion conec = new Conexion();
        conex = conec.getConexion2();
        //String reportesSeleccionados = CheckboxView.
        Statement statement = conex.createStatement();
        ResultSet resultSet = statement
                .executeQuery("SELECT * FROM VIEW_TBBO_RENOVACIONES_MOVIL WHERE PERIODO='"+periodo+"'");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook
                .createSheet("RenovacionesMovil");
        XSSFRow row=spreadsheet.createRow(1);
        XSSFCell cell; 
        cell=row.createCell(1); cell.setCellValue("FECHA"); 
        cell=row.createCell(2); cell.setCellValue("NO_CLIENTE");
        cell=row.createCell(3); cell.setCellValue("NOMBRE_CLIENTE");
        cell=row.createCell(4); cell.setCellValue("TIPO_CLIENTE_POSTPAGO");
        cell=row.createCell(5); cell.setCellValue("NIT");
        cell=row.createCell(6); cell.setCellValue("TIPO_FACTURA_SUBSIDIO");
        cell=row.createCell(7); cell.setCellValue("ANULADA");
        cell=row.createCell(8); cell.setCellValue("TIPO_SUBSIDIO");
        cell=row.createCell(9); cell.setCellValue("CODIGO_TIPO_SUBSIDIO");
        cell=row.createCell(10); cell.setCellValue("TELEFONO");
        cell=row.createCell(11); cell.setCellValue("ANEXO");
        cell=row.createCell(12); cell.setCellValue("AGENCIA");
        cell=row.createCell(13); cell.setCellValue("NOMBRE_AGENCIA");
        cell=row.createCell(14); cell.setCellValue("CLASIFICACION_MIC");
        cell=row.createCell(15); cell.setCellValue("PLAN_VOZ");
        cell=row.createCell(16); cell.setCellValue("CUOTA_BASICA");
        cell=row.createCell(17); cell.setCellValue("PLAN_GPRS");
        cell=row.createCell(18); cell.setCellValue("CUOTA_GPRS");
        cell=row.createCell(19); cell.setCellValue("CUOTA_SERVICIO_DATOS");
        cell=row.createCell(20); cell.setCellValue("PLAN_SMS");
        cell=row.createCell(21); cell.setCellValue("CUOTA_SMS");
        cell=row.createCell(22); cell.setCellValue("REVENUE_TOTAL");
        cell=row.createCell(23); cell.setCellValue("CONSUMO_PROMEDIO");
        cell=row.createCell(24); cell.setCellValue("UNIDADES_SUBSIDIO");
        cell=row.createCell(25); cell.setCellValue("PLAN_PTT");
        cell=row.createCell(26); cell.setCellValue("CUOTA_PTT");
        cell=row.createCell(27); cell.setCellValue("PROMOCION");
        cell=row.createCell(28); cell.setCellValue("MESES_PROMOCION");
        cell=row.createCell(29); cell.setCellValue("FECHA_SUBSIDIO_ANT");
        cell=row.createCell(30); cell.setCellValue("PERIODO");
        cell=row.createCell(31); cell.setCellValue("EJECUTIVO");
        cell=row.createCell(32); cell.setCellValue("COORDINADOR");
        cell=row.createCell(33); cell.setCellValue("GERENTE");
        cell=row.createCell(34); cell.setCellValue("MBF");
        
        int i=2;
        while(resultSet.next())
        {
            row=spreadsheet.createRow(i);
            cell=row.createCell(1);  cell.setCellValue(resultSet.getString("FECHA"));
            cell=row.createCell(2);  cell.setCellValue(resultSet.getString("NO_CLIENTE"));
            cell=row.createCell(3);  cell.setCellValue(resultSet.getString("NOMBRE_CLIENTE"));
            cell=row.createCell(4);  cell.setCellValue(resultSet.getString("TIPO_CLIENTE_POSTPAGO"));
            cell=row.createCell(5);  cell.setCellValue(resultSet.getString("NIT"));
            cell=row.createCell(6);  cell.setCellValue(resultSet.getString("TIPO_FACTURA_SUBSIDIO"));
            cell=row.createCell(7);  cell.setCellValue(resultSet.getString("ANULADA"));
            cell=row.createCell(8);  cell.setCellValue(resultSet.getString("TIPO_SUBSIDIO"));
            cell=row.createCell(9);  cell.setCellValue(resultSet.getString("CODIGO_TIPO_SUBSIDIO"));
            cell=row.createCell(10);  cell.setCellValue(resultSet.getString("TELEFONO"));
            cell=row.createCell(11);  cell.setCellValue(resultSet.getString("ANEXO"));
            cell=row.createCell(12);  cell.setCellValue(resultSet.getString("AGENCIA"));
            cell=row.createCell(13);  cell.setCellValue(resultSet.getString("NOMBRE_AGENCIA"));
            cell=row.createCell(14);  cell.setCellValue(resultSet.getString("CLASIFICACION_MIC"));
            cell=row.createCell(15);  cell.setCellValue(resultSet.getString("PLAN_VOZ"));
            cell=row.createCell(16);  cell.setCellValue(resultSet.getString("CUOTA_BASICA"));
            cell=row.createCell(17);  cell.setCellValue(resultSet.getString("PLAN_GPRS"));
            cell=row.createCell(18);  cell.setCellValue(resultSet.getString("CUOTA_GPRS"));
            cell=row.createCell(19);  cell.setCellValue(resultSet.getString("CUOTA_SERVICIO_DATOS"));
            cell=row.createCell(20);  cell.setCellValue(resultSet.getString("PLAN_SMS"));
            cell=row.createCell(21);  cell.setCellValue(resultSet.getString("CUOTA_SMS"));
            cell=row.createCell(22);  cell.setCellValue(resultSet.getString("REVENUE_TOTAL"));
            cell=row.createCell(23);  cell.setCellValue(resultSet.getString("CONSUMO_PROMEDIO"));
            cell=row.createCell(24);  cell.setCellValue(resultSet.getString("UNIDADES_SUBSIDIO"));
            cell=row.createCell(25);  cell.setCellValue(resultSet.getString("PLAN_PTT"));
            cell=row.createCell(26);  cell.setCellValue(resultSet.getString("CUOTA_PTT"));
            cell=row.createCell(27);  cell.setCellValue(resultSet.getString("PROMOCION"));
            cell=row.createCell(28);  cell.setCellValue(resultSet.getString("MESES_PROMOCION"));
            cell=row.createCell(29);  cell.setCellValue(resultSet.getString("FECHA_SUBSIDIO_ANT"));
            cell=row.createCell(30);  cell.setCellValue(resultSet.getString("PERIODO"));
            cell=row.createCell(31);  cell.setCellValue(resultSet.getString("EJECUTIVO"));
            cell=row.createCell(32);  cell.setCellValue(resultSet.getString("COORDINADOR"));
            cell=row.createCell(33);  cell.setCellValue(resultSet.getString("GERENTE"));
            cell=row.createCell(34);  cell.setCellValue(resultSet.getString("MBF"));
            
            i++;
        }
        File myfile;
        String TMP_DIR = System.getProperty("java.io.tmpdir")+"/reports/";
        FileOutputStream out = new FileOutputStream(
                myfile = new File(TMP_DIR+nombreReporte+".xlsx")
        );
        
        workbook.write(out);
        out.close();
        workbook.close();
        System.out.println(nombreReporte+".xlsx written successfully");
        
        /*habilitar boton descargar*/
        this.blnActivo=false;
        
        /*
        CheckboxView cbv = new CheckboxView();
        String[] reportes = cbv.getSelectedReports();
        */
    }
    
    public void crearRepRenovE1(String nombreReporte, String periodo) throws FileNotFoundException, SQLException, IOException  {
        
        Connection conex = null;
        Conexion conec = new Conexion();
        conex = conec.getConexion2();
        //String reportesSeleccionados = CheckboxView.
        Statement statement = conex.createStatement();
        ResultSet resultSet = statement
                .executeQuery("SELECT * FROM VIEW_TBBO_RENOVACIONES_E1 WHERE PERIODO='"+periodo+"'");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook
                .createSheet("RenovacionesE1");
        XSSFRow row=spreadsheet.createRow(1);
        XSSFCell cell; 
        /*Crear el encabezado*/
        cell=row.createCell(1); cell.setCellValue("ANE_INS");
        cell=row.createCell(2); cell.setCellValue("TELEFONO");
        cell=row.createCell(3); cell.setCellValue("COD_CLIENTE");
        cell=row.createCell(4); cell.setCellValue("COD_VENDEDOR_NP");
        cell=row.createCell(5); cell.setCellValue("NIT");
        cell=row.createCell(6); cell.setCellValue("CLIENTE");
        cell=row.createCell(7); cell.setCellValue("FECHA");
        cell=row.createCell(8); cell.setCellValue("MONTO_VENTA");
        cell=row.createCell(9); cell.setCellValue("MESES_CONTRATO");
        cell=row.createCell(10); cell.setCellValue("TIPO_OPERACION");
        cell=row.createCell(11); cell.setCellValue("PERIODO");
        
        int i=2;
        while(resultSet.next())
        {
            row=spreadsheet.createRow(i);
            /*Insertar cada linea */
            cell=row.createCell(1);  cell.setCellValue(resultSet.getString("ANE_INS"));
            cell=row.createCell(2);  cell.setCellValue(resultSet.getString("TELEFONO"));
            cell=row.createCell(3);  cell.setCellValue(resultSet.getString("COD_CLIENTE"));
            cell=row.createCell(4);  cell.setCellValue(resultSet.getString("COD_VENDEDOR_NP"));
            cell=row.createCell(5);  cell.setCellValue(resultSet.getString("NIT"));
            cell=row.createCell(6);  cell.setCellValue(resultSet.getString("CLIENTE"));
            cell=row.createCell(7);  cell.setCellValue(resultSet.getString("FECHA"));
            cell=row.createCell(8);  cell.setCellValue(resultSet.getString("MONTO_VENTA"));
            cell=row.createCell(9);  cell.setCellValue(resultSet.getString("MESES_CONTRATO"));
            cell=row.createCell(10);  cell.setCellValue(resultSet.getString("TIPO_OPERACION"));
            cell=row.createCell(11);  cell.setCellValue(resultSet.getString("PERIODO"));

            i++;
        }
        File myfile;
        String TMP_DIR = System.getProperty("java.io.tmpdir")+"/reports/";
        FileOutputStream out = new FileOutputStream(
                myfile = new File(TMP_DIR+nombreReporte+".xlsx")
        );
        
        workbook.write(out);
        out.close();
        System.out.println(nombreReporte+".xlsx written successfully");
        
        /*habilitar boton descargar*/
        this.blnActivo=false;
        
        /*
        CheckboxView cbv = new CheckboxView();
        String[] reportes = cbv.getSelectedReports();
        */
    }    
    
    public void crearRepRenovFijo(String nombreReporte, String periodo) throws FileNotFoundException, SQLException, IOException  {
        
        Connection conex = null;
        Conexion conec = new Conexion();
        conex = conec.getConexion2();
        //String reportesSeleccionados = CheckboxView.
        Statement statement = conex.createStatement();
        ResultSet resultSet = statement
                .executeQuery("SELECT * FROM VIEW_TBBO_RENOVACIONES_FIJO WHERE PERIODO='"+periodo+"'");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook
                .createSheet("RenovacionesFijo");
        XSSFRow row=spreadsheet.createRow(1);
        XSSFCell cell; 
            cell=row.createCell(1); cell.setCellValue("CODEMPRESA");
            cell=row.createCell(2); cell.setCellValue("EJECUTIVO");
            cell=row.createCell(3); cell.setCellValue("NIT");
            cell=row.createCell(4); cell.setCellValue("CODCLIENTE");
            cell=row.createCell(5); cell.setCellValue("NOMBRE");
            cell=row.createCell(6); cell.setCellValue("NOMBREPRODUCTO");
            cell=row.createCell(7); cell.setCellValue("INSTALACION");
            cell=row.createCell(8); cell.setCellValue("IDORDEN");
            cell=row.createCell(9); cell.setCellValue("NOMBREPROCESO");
            cell=row.createCell(10); cell.setCellValue("DESCRIPCION");
            cell=row.createCell(11); cell.setCellValue("ESPRUEBA");
            cell=row.createCell(12); cell.setCellValue("FECHA_INICIOPRUEBA");
            cell=row.createCell(13); cell.setCellValue("FECHA_FININSTALACION");
            cell=row.createCell(14); cell.setCellValue("FECHA_INICIOFACTURA");
            cell=row.createCell(15); cell.setCellValue("FECHABAJA");
            cell=row.createCell(16); cell.setCellValue("MONEDA");
            cell=row.createCell(17); cell.setCellValue("DIFERENCIALINSTALACION");
            cell=row.createCell(18); cell.setCellValue("MESCONTRATO");
            cell=row.createCell(19); cell.setCellValue("MONTOMENSUAL");
            cell=row.createCell(20); cell.setCellValue("DESCRIPCIONTRANSACCION");
            cell=row.createCell(21); cell.setCellValue("PERIODO");

        
        int i=2;
        while(resultSet.next())
        {
            row=spreadsheet.createRow(i);
            cell=row.createCell(1);  cell.setCellValue(resultSet.getString("CODEMPRESA"));
            cell=row.createCell(2);  cell.setCellValue(resultSet.getString("EJECUTIVO"));
            cell=row.createCell(3);  cell.setCellValue(resultSet.getString("NIT"));
            cell=row.createCell(4);  cell.setCellValue(resultSet.getString("CODCLIENTE"));
            cell=row.createCell(5);  cell.setCellValue(resultSet.getString("NOMBRE"));
            cell=row.createCell(6);  cell.setCellValue(resultSet.getString("NOMBREPRODUCTO"));
            cell=row.createCell(7);  cell.setCellValue(resultSet.getString("INSTALACION"));
            cell=row.createCell(8);  cell.setCellValue(resultSet.getString("IDORDEN"));
            cell=row.createCell(9);  cell.setCellValue(resultSet.getString("NOMBREPROCESO"));
            cell=row.createCell(10);  cell.setCellValue(resultSet.getString("DESCRIPCION"));
            cell=row.createCell(11);  cell.setCellValue(resultSet.getString("ESPRUEBA"));
            cell=row.createCell(12);  cell.setCellValue(resultSet.getString("FECHA_INICIOPRUEBA"));
            cell=row.createCell(13);  cell.setCellValue(resultSet.getString("FECHA_FININSTALACION"));
            cell=row.createCell(14);  cell.setCellValue(resultSet.getString("FECHA_INICIOFACTURA"));
            cell=row.createCell(15);  cell.setCellValue(resultSet.getString("FECHABAJA"));
            cell=row.createCell(16);  cell.setCellValue(resultSet.getString("MONEDA"));
            cell=row.createCell(17);  cell.setCellValue(resultSet.getString("DIFERENCIALINSTALACION"));
            cell=row.createCell(18);  cell.setCellValue(resultSet.getString("MESCONTRATO"));
            cell=row.createCell(19);  cell.setCellValue(resultSet.getString("MONTOMENSUAL"));
            cell=row.createCell(20);  cell.setCellValue(resultSet.getString("DESCRIPCIONTRANSACCION"));
            cell=row.createCell(21);  cell.setCellValue(resultSet.getString("PERIODO"));

            
            i++;
        }
        File myfile;
        String TMP_DIR = System.getProperty("java.io.tmpdir")+"/reports/";
        FileOutputStream out = new FileOutputStream(
                myfile = new File(TMP_DIR+nombreReporte+".xlsx")
        );
        
        workbook.write(out);
        out.close();
        System.out.println(nombreReporte+".xlsx written successfully");
        
        /*habilitar boton descargar*/
        this.blnActivo=false;
        
        /*
        CheckboxView cbv = new CheckboxView();
        String[] reportes = cbv.getSelectedReports();
        */
    }    
    
    public void crearRepRenovENFijo(String nombreReporte, String periodo) throws FileNotFoundException, SQLException, IOException  {
        
        Connection conex = null;
        Conexion conec = new Conexion();
        conex = conec.getConexion2();
        //String reportesSeleccionados = CheckboxView.
        Statement statement = conex.createStatement();
        ResultSet resultSet = statement
                .executeQuery("SELECT * FROM VIEW_TBBO_RENOVACIONES_EN_FIJO WHERE PERIODO='"+periodo+"'");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook
                .createSheet("RenovacionesENFijo");
        XSSFRow row=spreadsheet.createRow(1);
        XSSFCell cell; 
        /*Crear encabezado*/
            cell=row.createCell(1); cell.setCellValue("ANE_INS");
            cell=row.createCell(2); cell.setCellValue("TELEFONO");
            cell=row.createCell(3); cell.setCellValue("MONTO_VENTA");
            cell=row.createCell(4); cell.setCellValue("MESES_CONTRATO");
            cell=row.createCell(5); cell.setCellValue("FECHA");
            cell=row.createCell(6); cell.setCellValue("COD_CLIENTE");
            cell=row.createCell(7); cell.setCellValue("COD_VENDEDOR_NP");
            cell=row.createCell(8); cell.setCellValue("NIT");
            cell=row.createCell(9); cell.setCellValue("CLIENTE");
            cell=row.createCell(10); cell.setCellValue("EJECUTIVO_VENTA");
            cell=row.createCell(11); cell.setCellValue("TIPO_OPERACION");
            cell=row.createCell(12); cell.setCellValue("PERIODO");


        
        int i=2;
        
        /*Insertar Lineas*/
        while(resultSet.next())
        {
            row=spreadsheet.createRow(i);
            cell=row.createCell(1);  cell.setCellValue(resultSet.getString("ANE_INS"));
            cell=row.createCell(2);  cell.setCellValue(resultSet.getString("TELEFONO"));
            cell=row.createCell(3);  cell.setCellValue(resultSet.getString("MONTO_VENTA"));
            cell=row.createCell(4);  cell.setCellValue(resultSet.getString("MESES_CONTRATO"));
            cell=row.createCell(5);  cell.setCellValue(resultSet.getString("FECHA"));
            cell=row.createCell(6);  cell.setCellValue(resultSet.getString("COD_CLIENTE"));
            cell=row.createCell(7);  cell.setCellValue(resultSet.getString("COD_VENDEDOR_NP"));
            cell=row.createCell(8);  cell.setCellValue(resultSet.getString("NIT"));
            cell=row.createCell(9);  cell.setCellValue(resultSet.getString("CLIENTE"));
            cell=row.createCell(10);  cell.setCellValue(resultSet.getString("EJECUTIVO_VENTA"));
            cell=row.createCell(11);  cell.setCellValue(resultSet.getString("TIPO_OPERACION"));
            cell=row.createCell(12);  cell.setCellValue(resultSet.getString("PERIODO"));            
            
            i++;
        }
        File myfile;
        String TMP_DIR = System.getProperty("java.io.tmpdir")+"/reports/";
        FileOutputStream out = new FileOutputStream(
                myfile = new File(TMP_DIR+nombreReporte+".xlsx")
        );
        
        workbook.write(out);
        out.close();        
        workbook.close();
        System.out.println(nombreReporte+".xlsx written successfully");
        
        /*habilitar boton descargar*/
        this.blnActivo=false;
        
        /*
        CheckboxView cbv = new CheckboxView();
        String[] reportes = cbv.getSelectedReports();
        */
    }    
        
    public void crearRepRenovMovAG(String nombreReporte, String periodo) throws FileNotFoundException, SQLException, IOException  {
        
        Connection conex = null;
        Conexion conec = new Conexion();
        conex = conec.getConexion2();
        //String reportesSeleccionados = CheckboxView.
        Statement statement = conex.createStatement();
        ResultSet resultSet = statement
                .executeQuery("SELECT * FROM VIEW_TBBO_RENOVACIONES_MOV_AG WHERE PERIODO='"+periodo+"'");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook
                .createSheet("RenovacionesMovAG");
        XSSFRow row=spreadsheet.createRow(1);
        XSSFCell cell; 
        /*Crear encabezado*/
        cell=row.createCell(1); cell.setCellValue("CATEGORIA");
        cell=row.createCell(2); cell.setCellValue("FUENTE");
        cell=row.createCell(3); cell.setCellValue("COD_CLIENTE");
        cell=row.createCell(4); cell.setCellValue("CLIENTE");
        cell=row.createCell(5); cell.setCellValue("TELEFONO");
        cell=row.createCell(6); cell.setCellValue("ANE_INS");
        cell=row.createCell(7); cell.setCellValue("EJECUTIVO_VENTA");
        cell=row.createCell(8); cell.setCellValue("TIPO_OPERACION");
        cell=row.createCell(9); cell.setCellValue("PERIODO");
        
        int i=2;
        
        /*Insertar Lineas*/
        while(resultSet.next())
        {
            row=spreadsheet.createRow(i);
            cell=row.createCell(1);  cell.setCellValue(resultSet.getString("CATEGORIA"));
            cell=row.createCell(2);  cell.setCellValue(resultSet.getString("FUENTE"));
            cell=row.createCell(3);  cell.setCellValue(resultSet.getString("COD_CLIENTE"));
            cell=row.createCell(4);  cell.setCellValue(resultSet.getString("CLIENTE"));
            cell=row.createCell(5);  cell.setCellValue(resultSet.getString("TELEFONO"));
            cell=row.createCell(6);  cell.setCellValue(resultSet.getString("ANE_INS"));
            cell=row.createCell(7);  cell.setCellValue(resultSet.getString("EJECUTIVO_VENTA"));
            cell=row.createCell(8);  cell.setCellValue(resultSet.getString("TIPO_OPERACION"));
            cell=row.createCell(9);  cell.setCellValue(resultSet.getString("PERIODO"));
            
            i++;
        }
        File myfile;
        String TMP_DIR = System.getProperty("java.io.tmpdir")+"/reports/";
        FileOutputStream out = new FileOutputStream(
                myfile = new File(TMP_DIR+nombreReporte+".xlsx")
        );
        
        workbook.write(out);
        out.close();
        workbook.close();
        System.out.println(nombreReporte+".xlsx written successfully");
        
        /*habilitar boton descargar*/
        this.blnActivo=false;
        
        /*
        CheckboxView cbv = new CheckboxView();
        String[] reportes = cbv.getSelectedReports();
        */
    }    
    
    public void crearRepRenovSopJR(String nombreReporte, String periodo) throws FileNotFoundException, SQLException, IOException  {
        
        Connection conex = null;
        Conexion conec = new Conexion();
        conex = conec.getConexion2();
        //String reportesSeleccionados = CheckboxView.
        Statement statement = conex.createStatement();
        ResultSet resultSet = statement
                .executeQuery("SELECT * FROM VIEW_TBBO_RENOVACIONES_SOP_JR WHERE PERIODO='"+periodo+"'");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook
                .createSheet("RenovacionesSopJR");
        XSSFRow row=spreadsheet.createRow(1);
        XSSFCell cell; 
        
        /*Crear encabezado*/
        cell=row.createCell(1); cell.setCellValue("ANE_INS");
        cell=row.createCell(2); cell.setCellValue("TELEFONO");
        cell=row.createCell(3); cell.setCellValue("MONTO_VENTA");
        cell=row.createCell(4); cell.setCellValue("MESES_CONTRATO");
        cell=row.createCell(5); cell.setCellValue("FECHA");
        cell=row.createCell(6); cell.setCellValue("COD_CLIENTE");
        cell=row.createCell(7); cell.setCellValue("COD_VENDEDOR_NP");
        cell=row.createCell(8); cell.setCellValue("NIT");
        cell=row.createCell(9); cell.setCellValue("CLIENTE");
        cell=row.createCell(10); cell.setCellValue("EJECUTIVO_VENTA");
        cell=row.createCell(11); cell.setCellValue("TIPO_OPERACION");
        cell=row.createCell(12); cell.setCellValue("PERIODO");


        int i=2;
        
        /*Insertar Lineas*/
        while(resultSet.next())
        {
            row=spreadsheet.createRow(i);
            cell=row.createCell(1);  cell.setCellValue(resultSet.getString("ANE_INS"));
            cell=row.createCell(2);  cell.setCellValue(resultSet.getString("TELEFONO"));
            cell=row.createCell(3);  cell.setCellValue(resultSet.getString("MONTO_VENTA"));
            cell=row.createCell(4);  cell.setCellValue(resultSet.getString("MESES_CONTRATO"));
            cell=row.createCell(5);  cell.setCellValue(resultSet.getString("FECHA"));
            cell=row.createCell(6);  cell.setCellValue(resultSet.getString("COD_CLIENTE"));
            cell=row.createCell(7);  cell.setCellValue(resultSet.getString("COD_VENDEDOR_NP"));
            cell=row.createCell(8);  cell.setCellValue(resultSet.getString("NIT"));
            cell=row.createCell(9);  cell.setCellValue(resultSet.getString("CLIENTE"));
            cell=row.createCell(10);  cell.setCellValue(resultSet.getString("EJECUTIVO_VENTA"));
            cell=row.createCell(11);  cell.setCellValue(resultSet.getString("TIPO_OPERACION"));
            cell=row.createCell(12);  cell.setCellValue(resultSet.getString("PERIODO"));

            i++;
        }
        
        
        File myfile;
        String TMP_DIR = System.getProperty("java.io.tmpdir")+"/reports/";
        FileOutputStream out = new FileOutputStream(
                myfile = new File(TMP_DIR+nombreReporte+".xlsx")
        );
        
        workbook.write(out);        
        out.close();
        workbook.close();
        System.out.println(nombreReporte+".xlsx written successfully");
        
        /*habilitar boton descargar*/
        this.blnActivo=false;
        
        statement.close();
        resultSet.close();        
        conex.close();
        
        /*
        CheckboxView cbv = new CheckboxView();
        String[] reportes = cbv.getSelectedReports();
        */
    }    
    
    public void crearRepOperaciones(String nombreReporte, String periodo) throws FileNotFoundException, SQLException, IOException  {        
        Connection conex = null;
        Conexion conec = new Conexion();
        conex = conec.getConexion2();
        //String reportesSeleccionados = CheckboxView.
        Statement statement = conex.createStatement();
        ResultSet resultSet = statement
                .executeQuery("SELECT * FROM VIEW_TBBO_OPERACIONES WHERE PERIODO = '"+periodo+"'");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook
                .createSheet("OperacionesVenta");
        XSSFRow row=spreadsheet.createRow(1);
        XSSFCell cell; 
        /*Crear encabezado*/
        cell=row.createCell(1);        cell.setCellValue("ID");
        cell=row.createCell(2);        cell.setCellValue("SISTEMA");
        cell=row.createCell(3);        cell.setCellValue("FUENTE");
        cell=row.createCell(4);        cell.setCellValue("TIPO_TRANSACCION");
        cell=row.createCell(5);        cell.setCellValue("FECHA");
        cell=row.createCell(6);        cell.setCellValue("COD_CLIENTE");
        cell=row.createCell(7);        cell.setCellValue("CLIENTE_MIC");
        cell=row.createCell(8);        cell.setCellValue("CLIENTE_WHOLESALE");
        cell=row.createCell(9);        cell.setCellValue("NIT");
        cell=row.createCell(10);        cell.setCellValue("TELEFONO");
        cell=row.createCell(11);        cell.setCellValue("ANE_INS");
        cell=row.createCell(12);        cell.setCellValue("COD_VENTA");
        cell=row.createCell(13);        cell.setCellValue("NOMBRE_VENTA");
        cell=row.createCell(14);        cell.setCellValue("MONTO_VENTA");
        cell=row.createCell(15);        cell.setCellValue("ANEXO_PADRE");
        cell=row.createCell(16);        cell.setCellValue("EJECUTIVO_VENTA");
        cell=row.createCell(17);        cell.setCellValue("COD_VENDEDOR");
        cell=row.createCell(18);        cell.setCellValue("COORDINADOR");
        cell=row.createCell(19);        cell.setCellValue("GERENTE");
        cell=row.createCell(20);        cell.setCellValue("COD_DISTRIBUIDOR");
        cell=row.createCell(21);        cell.setCellValue("MODELO");
        cell=row.createCell(22);        cell.setCellValue("CLASI_MIC");
        cell=row.createCell(23);        cell.setCellValue("PRODUCTO_TB");
        cell=row.createCell(24);        cell.setCellValue("COD_VENDEDOR_NP");
        cell=row.createCell(25);        cell.setCellValue("CLIENTE");
        cell=row.createCell(26);        cell.setCellValue("VENDEDOR_AS400");
        cell=row.createCell(27);        cell.setCellValue("DISTRIBUIDOR_AS400");
        cell=row.createCell(28);        cell.setCellValue("AB_VENTA");
        cell=row.createCell(29);        cell.setCellValue("MESES_CONTRATO");        
        cell=row.createCell(30);        cell.setCellValue("TIPO_CAMBIO");        
        cell=row.createCell(31);        cell.setCellValue("TIPO_OPERACION");        
        cell=row.createCell(32);        cell.setCellValue("PRODUCTO_GLOBAL");        
        cell=row.createCell(33);        cell.setCellValue("PERIODO");
        
        int i=2;
        
        /*Insertar Lineas*/
        while(resultSet.next())
        {
            row=spreadsheet.createRow(i);
            cell=row.createCell(1);             cell.setCellValue(resultSet.getString("ID"));
            cell=row.createCell(2);            cell.setCellValue(resultSet.getString("SISTEMA"));
            cell=row.createCell(3);            cell.setCellValue(resultSet.getString("FUENTE"));
            cell=row.createCell(4);            cell.setCellValue(resultSet.getString("TIPO_TRANSACCION"));
            cell=row.createCell(5);            cell.setCellValue(resultSet.getString("FECHA"));
            cell=row.createCell(6);            cell.setCellValue(resultSet.getString("COD_CLIENTE"));
            cell=row.createCell(7);            cell.setCellValue(resultSet.getString("CLIENTE_MIC"));
            cell=row.createCell(8);            cell.setCellValue(resultSet.getString("CLIENTE_WHOLESALE"));
            cell=row.createCell(9);            cell.setCellValue(resultSet.getString("NIT"));
            cell=row.createCell(10);            cell.setCellValue(resultSet.getString("TELEFONO"));
            cell=row.createCell(11);            cell.setCellValue(resultSet.getString("ANE_INS"));
            cell=row.createCell(12);            cell.setCellValue(resultSet.getString("COD_VENTA"));
            cell=row.createCell(13);            cell.setCellValue(resultSet.getString("NOMBRE_VENTA"));
            cell=row.createCell(14);            cell.setCellValue(resultSet.getString("MONTO_VENTA"));
            cell=row.createCell(15);            cell.setCellValue(resultSet.getString("ANEXO_PADRE"));
            cell=row.createCell(16);            cell.setCellValue(resultSet.getString("EJECUTIVO_VENTA"));
            cell=row.createCell(17);            cell.setCellValue(resultSet.getString("COD_VENDEDOR"));
            cell=row.createCell(18);            cell.setCellValue(resultSet.getString("COORDINADOR"));
            cell=row.createCell(19);            cell.setCellValue(resultSet.getString("GERENTE"));
            cell=row.createCell(20);            cell.setCellValue(resultSet.getString("COD_DISTRIBUIDOR"));
            cell=row.createCell(21);            cell.setCellValue(resultSet.getString("MODELO"));
            cell=row.createCell(22);            cell.setCellValue(resultSet.getString("CLASI_MIC"));
            cell=row.createCell(23);            cell.setCellValue(resultSet.getString("PRODUCTO_TB"));
            cell=row.createCell(24);            cell.setCellValue(resultSet.getString("COD_VENDEDOR_NP"));
            cell=row.createCell(25);            cell.setCellValue(resultSet.getString("CLIENTE"));
            cell=row.createCell(26);            cell.setCellValue(resultSet.getString("VENDEDOR_AS400"));
            cell=row.createCell(27);            cell.setCellValue(resultSet.getString("DISTRIBUIDOR_AS400"));
            cell=row.createCell(28);            cell.setCellValue(resultSet.getString("AB_VENTA"));
            cell=row.createCell(29);            cell.setCellValue(resultSet.getString("MESES_CONTRATO"));
            cell=row.createCell(30);            cell.setCellValue(resultSet.getString("TIPO_CAMBIO"));
            cell=row.createCell(31);            cell.setCellValue(resultSet.getString("TIPO_OPERACION"));
            cell=row.createCell(32);            cell.setCellValue(resultSet.getString("PRODUCTO_GLOBAL"));
            cell=row.createCell(33);            cell.setCellValue(resultSet.getString("PERIODO"));
            
            i++;
        }
        File myfile;
        String TMP_DIR = System.getProperty("java.io.tmpdir")+"/reports/";
        FileOutputStream out = new FileOutputStream(
                myfile = new File(TMP_DIR+nombreReporte+".xlsx")
        );
        
        workbook.write(out);
        out.close();
        workbook.close();
        System.out.println(nombreReporte+".xlsx written successfully");
        
        /*habilitar boton descargar*/
        this.blnActivo=false;
    
    }
    
    public void getTempDir(){
        String TMP_DIR = System.getProperty("java.io.tmpdir");
        System.out.println(TMP_DIR);
    }
    
    public void crearRepVentasNuevas(String nombreReporte, String periodo) throws FileNotFoundException, SQLException, IOException  {        
        Connection conex = null;
        Conexion conec = new Conexion();
        conex = conec.getConexion2();
        //String reportesSeleccionados = CheckboxView.
        Statement statement = conex.createStatement();
        ResultSet resultSet = statement
                //.executeQuery("SELECT * FROM VIEW_TBBO_VENTAS_NUEVAS WHERE PERIODO = '"+periodo+"' AND ROWNUM<=10");
                .executeQuery("SELECT * FROM VIEW_TBBO_VENTAS_NUEVAS WHERE PERIODO = '"+periodo+"'");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook
                .createSheet("OperacionesVentasNuevas");
        XSSFRow row=spreadsheet.createRow(1);
        XSSFCell cell; 
        /*Crear encabezado*/
            cell=row.createCell(1); cell.setCellValue("FECHA");
            cell=row.createCell(2); cell.setCellValue("FECHA_ANULACION");
            cell=row.createCell(3); cell.setCellValue("ESTADO_ANULACION");
            cell=row.createCell(4); cell.setCellValue("BUSINESS_CD");
            cell=row.createCell(5); cell.setCellValue("BUSINESS_NAME");
            cell=row.createCell(6); cell.setCellValue("GERENTE");
            cell=row.createCell(7); cell.setCellValue("COORDINADOR");
            cell=row.createCell(8); cell.setCellValue("EJECUTIVO_VENTA");
            cell=row.createCell(9); cell.setCellValue("SISTEMA");
            cell=row.createCell(10); cell.setCellValue("PROMOCION");
            cell=row.createCell(11); cell.setCellValue("TIPO_TRANSACCION");
            cell=row.createCell(12); cell.setCellValue("COD_VENDEDOR_NP");
            cell=row.createCell(13); cell.setCellValue("COD_VENDEDOR");
            cell=row.createCell(14); cell.setCellValue("VENDEDOR_AS400");
            cell=row.createCell(15); cell.setCellValue("COD_DISTRIBUIDOR");
            cell=row.createCell(16); cell.setCellValue("DISTRIBUIDOR_AS400");
            cell=row.createCell(17); cell.setCellValue("FUENTE");
            cell=row.createCell(18); cell.setCellValue("NIT");
            cell=row.createCell(19); cell.setCellValue("COD_CLIENTE");
            cell=row.createCell(20); cell.setCellValue("CLIENTE");
            cell=row.createCell(21); cell.setCellValue("ANE_INS");
            cell=row.createCell(22); cell.setCellValue("TELEFONO");
            cell=row.createCell(23); cell.setCellValue("AB_VENTA");
            cell=row.createCell(24); cell.setCellValue("MONEDA_VENTA");
            cell=row.createCell(25); cell.setCellValue("MONTO_VENTA");
            cell=row.createCell(26); cell.setCellValue("MONTO_VENTA_USD");
            cell=row.createCell(27); cell.setCellValue("CLIENTE_MIC");
            cell=row.createCell(28); cell.setCellValue("CLIENTE_WHOLESALE");
            cell=row.createCell(29); cell.setCellValue("ANEXO_PADRE");
            cell=row.createCell(30); cell.setCellValue("TIPO_VENTA");
            cell=row.createCell(31); cell.setCellValue("COD_VENTA");
            cell=row.createCell(32); cell.setCellValue("NOMBRE_VENTA");
            cell=row.createCell(33); cell.setCellValue("PRODUCTO_VENTA");
            cell=row.createCell(34); cell.setCellValue("COD_MODELO");
            cell=row.createCell(35); cell.setCellValue("MODELO");
            cell=row.createCell(36); cell.setCellValue("CATEGORIA");
            cell=row.createCell(37); cell.setCellValue("ID_PRODUCTO_AS400");
            cell=row.createCell(38); cell.setCellValue("CLASI_MIC");
            cell=row.createCell(39); cell.setCellValue("PRODUCTO_TB");
            cell=row.createCell(40); cell.setCellValue("GERENTE_CONFIRMADO");
            cell=row.createCell(41); cell.setCellValue("COORDINADOR_CONFIRMADO");
            cell=row.createCell(42); cell.setCellValue("EJECUTIVO_VENTA_CONFIRMADO");
            cell=row.createCell(43); cell.setCellValue("ANIO");
            cell=row.createCell(44); cell.setCellValue("MES");
            cell=row.createCell(45); cell.setCellValue("DIA");
            cell=row.createCell(46); cell.setCellValue("MESES_CONTRATO");
            cell=row.createCell(47); cell.setCellValue("TIPO_CAMBIO");
            cell=row.createCell(48); cell.setCellValue("PRODUCTO_GLOBAL");
            cell=row.createCell(49); cell.setCellValue("PERIODO");
        
        int i=2;
        
        /*Insertar Lineas*/
        while(resultSet.next())
        {
            row=spreadsheet.createRow(i);
            
            cell=row.createCell(1); cell.setCellValue(resultSet.getString("FECHA"));
            cell=row.createCell(2); cell.setCellValue(resultSet.getString("FECHA_ANULACION"));
            cell=row.createCell(3); cell.setCellValue(resultSet.getString("ESTADO_ANULACION"));
            cell=row.createCell(4); cell.setCellValue(resultSet.getString("BUSINESS_CD"));
            cell=row.createCell(5); cell.setCellValue(resultSet.getString("BUSINESS_NAME"));
            cell=row.createCell(6); cell.setCellValue(resultSet.getString("GERENTE"));
            cell=row.createCell(7); cell.setCellValue(resultSet.getString("COORDINADOR"));
            cell=row.createCell(8); cell.setCellValue(resultSet.getString("EJECUTIVO_VENTA"));
            cell=row.createCell(9); cell.setCellValue(resultSet.getString("SISTEMA"));
            cell=row.createCell(10); cell.setCellValue(resultSet.getString("PROMOCION"));
            cell=row.createCell(11); cell.setCellValue(resultSet.getString("TIPO_TRANSACCION"));
            cell=row.createCell(12); cell.setCellValue(resultSet.getString("COD_VENDEDOR_NP"));
            cell=row.createCell(13); cell.setCellValue(resultSet.getString("COD_VENDEDOR"));
            cell=row.createCell(14); cell.setCellValue(resultSet.getString("VENDEDOR_AS400"));
            cell=row.createCell(15); cell.setCellValue(resultSet.getString("COD_DISTRIBUIDOR"));
            cell=row.createCell(16); cell.setCellValue(resultSet.getString("DISTRIBUIDOR_AS400"));
            cell=row.createCell(17); cell.setCellValue(resultSet.getString("FUENTE"));
            cell=row.createCell(18); cell.setCellValue(resultSet.getString("NIT"));
            cell=row.createCell(19); cell.setCellValue(resultSet.getString("COD_CLIENTE"));
            cell=row.createCell(20); cell.setCellValue(resultSet.getString("CLIENTE"));
            cell=row.createCell(21); cell.setCellValue(resultSet.getString("ANE_INS"));
            cell=row.createCell(22); cell.setCellValue(resultSet.getString("TELEFONO"));
            cell=row.createCell(23); cell.setCellValue(resultSet.getString("AB_VENTA"));
            cell=row.createCell(24); cell.setCellValue(resultSet.getString("MONEDA_VENTA"));
            cell=row.createCell(25); cell.setCellValue(resultSet.getString("MONTO_VENTA"));
            cell=row.createCell(26); cell.setCellValue(resultSet.getString("MONTO_VENTA_USD"));
            cell=row.createCell(27); cell.setCellValue(resultSet.getString("CLIENTE_MIC"));
            cell=row.createCell(28); cell.setCellValue(resultSet.getString("CLIENTE_WHOLESALE"));
            cell=row.createCell(29); cell.setCellValue(resultSet.getString("ANEXO_PADRE"));
            cell=row.createCell(30); cell.setCellValue(resultSet.getString("TIPO_VENTA"));
            cell=row.createCell(31); cell.setCellValue(resultSet.getString("COD_VENTA"));
            cell=row.createCell(32); cell.setCellValue(resultSet.getString("NOMBRE_VENTA"));
            cell=row.createCell(33); cell.setCellValue(resultSet.getString("PRODUCTO_VENTA"));
            cell=row.createCell(34); cell.setCellValue(resultSet.getString("COD_MODELO"));
            cell=row.createCell(35); cell.setCellValue(resultSet.getString("MODELO"));
            cell=row.createCell(36); cell.setCellValue(resultSet.getString("CATEGORIA"));
            cell=row.createCell(37); cell.setCellValue(resultSet.getString("ID_PRODUCTO_AS400"));
            cell=row.createCell(38); cell.setCellValue(resultSet.getString("CLASI_MIC"));
            cell=row.createCell(39); cell.setCellValue(resultSet.getString("PRODUCTO_TB"));
            cell=row.createCell(40); cell.setCellValue(resultSet.getString("GERENTE_CONFIRMADO"));
            cell=row.createCell(41); cell.setCellValue(resultSet.getString("COORDINADOR_CONFIRMADO"));
            cell=row.createCell(42); cell.setCellValue(resultSet.getString("EJECUTIVO_VENTA_CONFIRMADO"));
            cell=row.createCell(43); cell.setCellValue(resultSet.getString("ANIO"));
            cell=row.createCell(44); cell.setCellValue(resultSet.getString("MES"));
            cell=row.createCell(45); cell.setCellValue(resultSet.getString("DIA"));
            cell=row.createCell(46); cell.setCellValue(resultSet.getString("MESES_CONTRATO"));
            cell=row.createCell(47); cell.setCellValue(resultSet.getString("TIPO_CAMBIO"));
            cell=row.createCell(48); cell.setCellValue(resultSet.getString("PRODUCTO_GLOBAL"));
            cell=row.createCell(49); cell.setCellValue(resultSet.getString("PERIODO"));
            
            i++;
        }
        File myfile;
        String TMP_DIR = System.getProperty("java.io.tmpdir")+"/reports/";
        FileOutputStream out = new FileOutputStream(
                
                myfile = new File(TMP_DIR+nombreReporte+".xlsx")
        );
        
        workbook.write(out);
        out.close();
        workbook.close();
        System.out.println(nombreReporte+".xlsx written successfully");
        
        /*habilitar boton descargar*/
        this.blnActivo=false;
    
    }

    public void crearRepUpgradesMig(String nombreReporte, String periodo) throws FileNotFoundException, SQLException, IOException  {
        
        Connection conex = null;
        Conexion conec = new Conexion();
        conex = conec.getConexion2();
        //String reportesSeleccionados = CheckboxView.
        Statement statement = conex.createStatement();
        ResultSet resultSet = statement
                .executeQuery("SELECT * FROM VIEW_TBBO_UPGRADES_MIGRACION WHERE PERIODO='"+periodo+"'");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook
                .createSheet("UpgradesMigracion");
        XSSFRow row=spreadsheet.createRow(1);
        XSSFCell cell; 
        /*Crear encabezado*/
            cell=row.createCell(1);
            cell.setCellValue("FECHA");
            cell=row.createCell(2);
            cell.setCellValue("HORA");
            cell=row.createCell(3);
            cell.setCellValue("TIPO_PLAN");
            cell=row.createCell(4);
            cell.setCellValue("TRANSACCION_DET");
            cell=row.createCell(5);
            cell.setCellValue("TIPO_CLIENTE");
            cell=row.createCell(6);
            cell.setCellValue("COD_CLIENTE");
            cell=row.createCell(7);
            cell.setCellValue("NIT");
            cell=row.createCell(8);
            cell.setCellValue("CLIENTE");
            cell=row.createCell(9);
            cell.setCellValue("TIPO_CAMBIO");
            cell=row.createCell(10);
            cell.setCellValue("ANEXO");
            cell=row.createCell(11);
            cell.setCellValue("TELEFONO");
            cell=row.createCell(12);
            cell.setCellValue("PRUEBA_E1");
            cell=row.createCell(13);
            cell.setCellValue("CANTIDAD");
            cell=row.createCell(14);
            cell.setCellValue("COD_PLAN_ANT");
            cell=row.createCell(15);
            cell.setCellValue("PLAN_ANT");
            cell=row.createCell(16);
            cell.setCellValue("MONEDA_ANT");
            cell=row.createCell(17);
            cell.setCellValue("CUOTA_BASICA_ANT");
            cell=row.createCell(18);
            cell.setCellValue("COD_PLAN_ACT");
            cell=row.createCell(19);
            cell.setCellValue("PLAN_ACT");
            cell=row.createCell(20);
            cell.setCellValue("MONEDA_ACT");
            cell=row.createCell(21);
            cell.setCellValue("CUOTA_BASICA_ACT");
            cell=row.createCell(22);
            cell.setCellValue("DIFERENCIAL_Q");
            cell=row.createCell(23);
            cell.setCellValue("DIFERENCIAL_$");
            cell=row.createCell(24);
            cell.setCellValue("MESES_CONTRATO");
            cell=row.createCell(25);
            cell.setCellValue("FECHA_EFECTIVO");
            cell=row.createCell(26);
            cell.setCellValue("FECHA_INICIO");
            cell=row.createCell(27);
            cell.setCellValue("USUARIO");
            cell=row.createCell(28);
            cell.setCellValue("TIPO_OPERACION");
            cell=row.createCell(29);
            cell.setCellValue("PERIODO");
        
        int i=2;
        
        /*Insertar Lineas*/
        while(resultSet.next())
        {
            row=spreadsheet.createRow(i);
            
            cell=row.createCell(1);  cell.setCellValue(resultSet.getString("FECHA"));
            cell=row.createCell(2);  cell.setCellValue(resultSet.getString("HORA"));
            cell=row.createCell(3);  cell.setCellValue(resultSet.getString("TIPO_PLAN"));
            cell=row.createCell(4);  cell.setCellValue(resultSet.getString("TRANSACCION_DET"));
            cell=row.createCell(5);  cell.setCellValue(resultSet.getString("TIPO_CLIENTE"));
            cell=row.createCell(6);  cell.setCellValue(resultSet.getString("COD_CLIENTE"));
            cell=row.createCell(7);  cell.setCellValue(resultSet.getString("NIT"));
            cell=row.createCell(8);  cell.setCellValue(resultSet.getString("CLIENTE"));
            cell=row.createCell(9);  cell.setCellValue(resultSet.getString("TIPO_CAMBIO"));
            cell=row.createCell(10);  cell.setCellValue(resultSet.getString("ANEXO"));
            cell=row.createCell(11);  cell.setCellValue(resultSet.getString("TELEFONO"));
            cell=row.createCell(12);  cell.setCellValue(resultSet.getString("PRUEBA_E1"));
            cell=row.createCell(13);  cell.setCellValue(resultSet.getString("CANTIDAD"));
            cell=row.createCell(14);  cell.setCellValue(resultSet.getString("COD_PLAN_ANT"));
            cell=row.createCell(15);  cell.setCellValue(resultSet.getString("PLAN_ANT"));
            cell=row.createCell(16);  cell.setCellValue(resultSet.getString("MONEDA_ANT"));
            cell=row.createCell(17);  cell.setCellValue(resultSet.getString("CUOTA_BASICA_ANT"));
            cell=row.createCell(18);  cell.setCellValue(resultSet.getString("COD_PLAN_ACT"));
            cell=row.createCell(19);  cell.setCellValue(resultSet.getString("PLAN_ACT"));
            cell=row.createCell(20);  cell.setCellValue(resultSet.getString("MONEDA_ACT"));
            cell=row.createCell(21);  cell.setCellValue(resultSet.getString("CUOTA_BASICA_ACT"));
            cell=row.createCell(22);  cell.setCellValue(resultSet.getString("DIFERENCIAL_Q"));
            cell=row.createCell(23);  cell.setCellValue(resultSet.getString("DIFERENCIAL_$"));
            cell=row.createCell(24);  cell.setCellValue(resultSet.getString("MESES_CONTRATO"));
            cell=row.createCell(25);  cell.setCellValue(resultSet.getString("FECHA_EFECTIVO"));
            cell=row.createCell(26);  cell.setCellValue(resultSet.getString("FECHA_INICIO"));
            cell=row.createCell(27);  cell.setCellValue(resultSet.getString("USUARIO"));
            cell=row.createCell(28);  cell.setCellValue(resultSet.getString("TIPO_OPERACION"));
            cell=row.createCell(29);  cell.setCellValue(resultSet.getString("PERIODO"));
            
            i++;
        }
        File myfile;
        String TMP_DIR = System.getProperty("java.io.tmpdir")+"/reports/";
        FileOutputStream out = new FileOutputStream(
                myfile = new File(TMP_DIR+nombreReporte+".xlsx")
        );
        
        workbook.write(out);
        out.close();
        workbook.close();
        System.out.println(nombreReporte+".xlsx written successfully");
        
        /*habilitar boton descargar*/
        this.blnActivo=false;
        
        /*
        CheckboxView cbv = new CheckboxView();
        String[] reportes = cbv.getSelectedReports();
        */
    }    
    
    /*METODO QUE CREA TODOS LOS REPORTES DE ACUERDO A LOS SELECCIONADOS*/
    public void generaReportes(String[] reports){
        
        try {
            /*Vaciar la carpeta reports*/
            cleanDir();
            
            System.out.println("Leyendo reportes = "+Arrays.toString(reports));
            
            /*Recorrer la lista de reportes seleccionados*/
            for( int j = 0; j < reports.length; j++){
                String element = reports[j];
                System.out.println( "Imprimiendo elemento: "+ element );
                if(element.equals("Reporte Movil")){
                    element="Reporte_Movil";
                    try {
                        /*Metodo Crear reporte personalizado*/
                        crearRepRenovMovil(element, this.periodo);
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                 if(element.equals("Reporte de E1")){
                     element="Reporte_de_E1";
                    try {
                        /*Metodo Crear reporte personalizado*/
                        crearRepRenovE1(element, this.periodo);
                    } catch (SQLException ex) {
                        Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                 
                 if(element.equals("Reporte de Renovaciones Enlace Fijo")){
                     element="Reporte_Renovaciones_Enlace_Fijo";
                    try {
                        /*Metodo Crear reporte personalizado*/
                        crearRepRenovENFijo(element, this.periodo);
                    } catch (SQLException ex) {
                        Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                 
                if(element.equals("Reporte de Fijos")){
                    element="Reporte_de_Fijos";
                    try {
                        /*Metodo Crear reporte personalizado*/
                        crearRepRenovFijo(element, this.periodo);
                    } catch (SQLException ex) {
                        Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if(element.equals("Reporte de Ventas en Agencias")){
                    element="Reporte_Ventas_en_Agencias";
                    try {
                        /*Metodo Crear reporte personalizado*/
                        crearRepRenovMovAG(element, this.periodo);
                    } catch (SQLException ex) {
                        Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
               
                if(element.equals("Reporte de Ventas de Soporte Junior")){
                    element="Reporte_Ventas_de_Soporte_Junior";
                    try {
                        /*Metodo Crear reporte personalizado*/
                        crearRepRenovSopJR(element, this.periodo);
                    } catch (SQLException ex) {
                        Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if(element.equals("Reporte Ventas Nuevas")){
                    element="Reporte_Ventas_Nuevas";
                    //getTempDir();
                    try {
                        /*Metodo Crear reporte personalizado*/
                        //crearRepOperaciones(element, this.periodo);//ventas nuevas old
                        crearRepVentasNuevas(element, this.periodo);//ventas nuevas new                        
                    } catch (SQLException ex) {
                        Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if(element.equals("Reporte Upgrades y Migraciones")){
                    element="Reporte_Upgrades_Migracion";
                    try {
                        /*Metodo Crear reporte personalizado*/
                        crearRepUpgradesMig(element, this.periodo);
                    } catch (SQLException ex) {
                        Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }                
                /*
                else{
                    System.out.println( "NADA para generar en el reporte" );
                }
                */
                //reports[j] = "\"../temp/"+reports[j]+".xlsx"+"\"";
            }            
            /*Crear zip con los archivos*/
                Thread.sleep(3000);//pausa
                doZip();//crear zip                
                } catch (InterruptedException ex) {
                    Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }
    
    /*Metodo descargar reporte*/
    public void download() throws IOException{
        
        File file = new File("/temp//ReporteXPeriodo.xlsx");
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        HttpServletResponse response =
                (HttpServletResponse) facesContext.getExternalContext().getResponse();
        
        response.reset();
        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=ReporteXPeriodo.xlsx");
        
        OutputStream responseOutputStream = response.getOutputStream();
        
        InputStream fileInputStream = new FileInputStream(file);
        
        byte[] bytesBuffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(bytesBuffer)) > 0)
        {
            responseOutputStream.write(bytesBuffer, 0, bytesRead);
        }
        
        responseOutputStream.flush();
        
        fileInputStream.close();
        responseOutputStream.close();
        
        facesContext.responseComplete();
    }
    
    public void downloadOne(String myFile) throws IOException{
        
        File file = new File("/temp//"+myFile+".xlsx");
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        HttpServletResponse response =
                (HttpServletResponse) facesContext.getExternalContext().getResponse();
        
        response.reset();
        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+myFile+".xlsx");
        
        OutputStream responseOutputStream = response.getOutputStream();
        
        InputStream fileInputStream = new FileInputStream(file);
        
        byte[] bytesBuffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(bytesBuffer)) > 0)
        {
            responseOutputStream.write(bytesBuffer, 0, bytesRead);
        }
        
        responseOutputStream.flush();
        
        fileInputStream.close();
        responseOutputStream.close();
        
        facesContext.responseComplete();
    }
    
    public void downloadReports(String[] seleccionados) throws IOException{               
        
        for( int r = 0; r < seleccionados.length; r++){
            String element = seleccionados[r];
            
            downloadOne(element);//descargar cada archivo
            
            System.out.println( "\nDescargando Repote ... "+ element );            
                
       }
    }
    
   public void downloadZipReport() {
       
    String TMP_DIR = System.getProperty("java.io.tmpdir");
    File file = new File(TMP_DIR+"/Reportes.zip");
    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  

    response.setHeader("Content-Disposition", "attachment;filename=Reportes.zip");  
    response.setContentLength((int) file.length());  
    ServletOutputStream out = null;  
    try {  
        FileInputStream input = new FileInputStream(file);  
        byte[] buffer = new byte[2048];  
        out = response.getOutputStream();  
        int i = 0;  
        while ((i = input.read(buffer)) != -1) {  
            out.write(buffer);  
            out.flush();  
        }  
        FacesContext.getCurrentInstance().getResponseComplete();  
    } catch (IOException err) {  
        err.getMessage();
    } finally {  
        try {  
            if (out != null) {  
                out.close();  
            }  
        } catch (IOException err) {  
            err.getMessage();
        }  
    }  

    }
   
   public void descargarUnoxUno(String[] archivos){
        for (String archivo : archivos) {
//            descargaUnArchivo(archivo);
            System.out.println("intentando descarga "+archivo);
        }
   }
   
   public void createTheZip(String[] files) {
		String TMP_DIR = System.getProperty("java.io.tmpdir")+"/reports/";
		String zipFile = TMP_DIR+"/reports.zip";		
		String[] srcFiles = { Arrays.toString(files) };
		
		try {
			
			// create byte buffer
			byte[] buffer = new byte[1024];
			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			
			for (int i=0; i < srcFiles.length; i++) {
				
				File srcFile = new File(srcFiles[i]);
				FileInputStream fis = new FileInputStream(srcFile);

				// begin writing a new ZIP entry, positions the stream to the start of the entry data
				zos.putNextEntry(new ZipEntry(srcFile.getName()));
				
				int length;
				while ((length = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, length);
				}

				zos.closeEntry();
				// close the InputStream
				fis.close();
				
			}
			// close the ZipOutputStream
			zos.close();
                        System.out.println("ZIP was created! ") ;
			
		}
		catch (IOException ioe) {
			System.out.println("Error creating zip file: " + ioe);
		}
		
	}
   
    public void dowloadZip() throws IOException{       
        
        
            /*downloadTest(element);*/
            String TMP_DIR = System.getProperty("java.io.tmpdir");
            File file = new File(TMP_DIR+"/reports.zip");
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            
            HttpServletResponse response =
                    (HttpServletResponse) facesContext.getExternalContext().getResponse();            
            
            response.resetBuffer();
            response.setHeader("Content-Type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=reports.zip");
                
            OutputStream responseOutputStream = response.getOutputStream();
            InputStream fileInputStream = new FileInputStream(file);

            byte[] bytesBuffer = new byte[2048];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(bytesBuffer)) > 0)
                {
                    responseOutputStream.write(bytesBuffer, 0, bytesRead);
                }

            responseOutputStream.flush();

            fileInputStream.close();
            responseOutputStream.close();

            facesContext.responseComplete();                   
        
    }
    
   public  void deleteFiles(String[] reports) {
      
       try{
      
           for (String report : reports) {
               String TMP_DIR = System.getProperty("java.io.tmpdir")+"/reports/";
               String tempFile = TMP_DIR + report;
               //Delete if tempFile exists
               File fileTemp = new File(tempFile);
               if (fileTemp.exists()){
                   fileTemp.delete();
                   System.out.println("Deleting reports generated.. "+report);
               }
           }
      }catch(Exception e){
          e.getMessage();
      }
   }
   
   /*METODO PARA EMPAQUETAR ZIP DE LA CARPETA REPORTS*/
   public void doZip(){
        try {
            //ZipUtil.pack(new File("../temp/reports/"), new File("../temp/reports.zip"));
            
            Zip myzip = new Zip();
            String TMP_DIR = System.getProperty("java.io.tmpdir");
            myzip.zipDir(TMP_DIR+"/Reportes.zip", TMP_DIR+"/reports/");
            
        } catch (Exception ex) {
            Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   /*VACIAR LA CARPETA REPORTS*/
   public void cleanDir(){
        try {
            String TMP_DIR = System.getProperty("java.io.tmpdir")+"/reports/";
            FileUtils.cleanDirectory(new File(TMP_DIR));
            System.out.println("Cleaning temp reports.. ");
        } catch (IOException ex) {
            Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
       
   /*CONSULTA TASA DE CAMBIO*/
   public void consultaTasa(){
       if (this.periodo!=null){
           try {
               this.blnTasa=true;
               Connection conex = null;
               Conexion conec = new Conexion();
               conex = conec.getConexion2();
               //String reportesSeleccionados = CheckboxView.
               Statement statement = conex.createStatement();
               ResultSet resultSet = statement
                       .executeQuery(Query_C.getConsulta_TasaCambio(this.periodo));
               while(resultSet.next()){
                   try {
                       this.tasaCambio=resultSet.getFloat("TASA_CAMBIO");
                   } catch (SQLException ex) {
                       Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
               resultSet.close();
               statement.close();
               conex.close();
           } catch (SQLException ex) {
               Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
           }
           this.blnTasa=false;
           
       }
   }  
   
   public void actualizaTasa(){
       if (this.periodo!=null && this.tasaCambio!=0.0){
            try{
                Conexion conec = new Conexion();
                Connection conex = conec.getConexion2();
                try{
                    Query_C query = new Query_C();
                    String generado = query.getConsulta_ActualizaTasaCambio(this.tasaCambio, this.periodo);
                    //System.out.println("calling "+generado);
                    CallableStatement cstmt = conex.prepareCall(generado);
                    cstmt.executeQuery();
                    JsfUtil.addSuccessMessage("Se actualizó la Tasa de Cambio!");//mensaje success
                    this.blnTasa=true;//deshabilitar tasa textBox
                }catch (SQLException ex) {
                    Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                conex.close();
            }catch (SQLException ex) {
               Logger.getLogger(ReporteOperaciones.class.getName()).log(Level.SEVERE, null, ex);
           }  
        }
       else{
           JsfUtil.addErrorMessage("Tasa cambio no puede ser 0");//mensaje success
       }
       
   }
   
    public void btnClickEjecutaProcPeriodo(ActionEvent actionEvent) {
       try{
           String periodo = this.getPeriodo();
            if(periodo!=null){
                Connection conex2 = null;
                Conexion conec = new Conexion();
                conex2 = conec.getConexion2();
                try{
                    Query_C query = new Query_C();
                    /*llamar consulta que ejecuta proceso por periodo*/
                    String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
                    query.generar_Consulta_EjecutaProceso(periodo, usuarioLogueado);
                    CallableStatement cstmt = conex2.prepareCall(query.getEjecutar_Proceso());  
                    System.out.println("Ejecutando: "+query.getEjecutar_Proceso());
                    cstmt.executeQuery();                
                    //cstmt.close();
                                              
                }catch(Exception e){
                    e.printStackTrace();
                    System.out.println("ERROR: "+e.getMessage());
                }
                finally{
                    try {
                        conex2.close();
                        //mensaje de asignado correctamente
                        JsfUtil.addSuccessMessage("Proceso Finalizado Exitosamente!!");
                    } catch (SQLException ex) {
                        ex.getMessage();
                        }
                }
            }
       }
       catch (Exception ex) {
           ex.getMessage();
           }
    }    
}
