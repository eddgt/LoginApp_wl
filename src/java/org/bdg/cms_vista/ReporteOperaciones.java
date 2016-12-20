/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
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
import org.bdg.cms_buc.Querys_C;
import org.bdg.cms_conexion.Conexion;
import org.bdg.cms_dto.ReporteOperacion;
import org.bdg.session.BaseSession;

import javax.faces.bean.ManagedBean;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


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
    
    
    @PostConstruct
    public void init() {
        this.blnActivo=true;/*inhabilitar por default*/
    }
    
    
    public List<ReporteOperacion> getReporteOperacion(String periodo){
        List<ReporteOperacion> listaReporte = new ArrayList<ReporteOperacion>();
        Connection conex = null;
        //this.cantidadFilas =0;
        try{
            Conexion conec = new Conexion();
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Querys_C query = new Querys_C();
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
    
    public void crearReporte() throws FileNotFoundException, SQLException, IOException  {
        
        Connection conex = null;
        Conexion conec = new Conexion();
        conex = conec.getConexion();
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
        System.out.println(
                "excelexport.xlsx written successfully");
        
        /*habilitar boton descargar*/
        this.blnActivo=false;
    }
    
    public void download() throws IOException{
        
        File file = new File("../temp/ReporteXPeriodo.xlsx");
        
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
    
}
