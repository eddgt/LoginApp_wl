package org.bdg.cms_vista;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.bdg.base.Constantes;
import org.bdg.cms_buc.Query_Editar;
import org.bdg.cms_buc.Querys_Bitacora;
import org.bdg.cms_buc.Querys_C;
import org.bdg.cms_conexion.Conexion;
import org.bdg.cms_dto.AsesorOperacion;
import org.bdg.cms_dto.Comentario;
import org.bdg.cms_dto.CoordinadorOperacion;
import org.bdg.cms_dto.DetalleVentaOperacion;
import org.bdg.exporters.ExcelConfigRepOperacion;
import org.bdg.session.BaseSession;
import org.bdg.utils.JsfUtil;
import org.primefaces.event.data.FilterEvent;

/**
 *
 * @author oulloa
 */
@ManagedBean(name = "ventasOperacionBean")
@ViewScoped
public class VentasOperacionBean extends BaseSession implements Serializable{
    
    private List<DetalleVentaOperacion> listaVentasOperacion;
    private List<DetalleVentaOperacion> ventasOperacionSeleccionadas;
    private int idSelected;
    private DetalleVentaOperacion ventaOperacionSeleccionadaDetalle;
    private String nombreCoordinadorOperSelected;
    private String nombreCoordinadorOperVistaSelected;
    private String periodoSelected;
    private String comboSistemaSelected;
    private String comboFuenteSelected;
    private String comboTipoTranSelected;
    private String comboCodVentaSelected;
    private String comboNombreVentaSelected;
    private String comboModeloSelected;
    private String comboClasiMicSelected;
    private String comboProdTbSelected;
    private String comboAbVentaSelected;
    private String comboTipoOperacionSelected;
    private String comboPeriodoSelected;
    private int cantidadFilas;
    private float sumatoriaMontoVenta;    
    private float sumatoriaEnCargarMontoVenta;
    private float sumatoriaEnFiltrosMontoVenta;
    private List<CoordinadorOperacion> listaCoordinadorOperacion;
    private List<CoordinadorOperacion> listaCoordinadorOperacionVista;
    private List<DetalleVentaOperacion> listaCombosOperacionVista;
    private List<DetalleVentaOperacion> listaCombosFuente;
    private List<DetalleVentaOperacion> listaCombosTipoTran;
    private List<DetalleVentaOperacion> listaCombosCodVenta;    
    private List<DetalleVentaOperacion> listaCombosNombreVenta;
    private List<DetalleVentaOperacion> listaCombosModelo;    
    private List<DetalleVentaOperacion> listaCombosClasiMic;
    private List<DetalleVentaOperacion> listaCombosProdTb;
    private List<DetalleVentaOperacion> listaCombosAbVenta;
    private List<DetalleVentaOperacion> listaCombosTipoOperacion;
    private List<DetalleVentaOperacion> listaCombosPeriodo;
    private List<DetalleVentaOperacion> ventasFiltradas;
    private CoordinadorOperacion coordinadorOperSelected;/*variable lista coordinadores*/
    private CoordinadorOperacion coordinadorOperVistaSelected;
    private AsesorOperacion asesorOperSelected;
    private List<AsesorOperacion> listaAsesores;/*para variable lista asesores*/    
    private List<Comentario> listaComentarioxVenta;
    //private Map<Integer, String> listaAsesorOperacion;
    //private Map<String> listaAsesorOperacion;
    private List<AsesorOperacion> listaAsesorOperacion;
    private List<AsesorOperacion> listaAsesoresOperacionVista;/*para variable lista asesores form nueva venta*/
    private String nombreAsesorSelected;
    private String nombreAsesorSelectedVista;
    private boolean blnActivoSwitchEdicion=false;
    private boolean blnActivoEdicionValMonto=true;
    private boolean blnActivoEdicion=true;
    private boolean blnActivoOneSelected;
    private boolean blnActivoAsignarSelected=false;
    private String comentario;
    
    /*inicio Propiedades para insert*/

    private String abVenta;
    private String anexoPadre;
    private int aneIns;
    private int businessCd;
    private String businessName;
    private String businessType;
    private String categoria;
    private String clasiMic;
    private String cliente;
    private String clienteMic;
    private String clienteWholeSale;
    private int cntRguVenta;
    private int codCliente;
    private int codDistribuidor;
    private String codModelo;
    private String codPromocion;
    private int codVendedor;
    private String codVendedorNp;
    private int codVenta;
    private String coordinador;
    private String distribuidorAs400;
    private String ejecutivoVenta;
    private String etadoAnulacion;
    private Date fecha;
    private Date fechaAnulacion;
    private String fuente;
    private String gerente;
    private String idProductoAs400;
    private int mesesContrato;
    private String modelo;
    private String monedaVenta;
    private float montoVenta;
    private String nit;
    private String nombreVenta;
    private String productoTb;
    private String productoVenta;
    private String promocion;
    private String segCompanyRvn;
    private String segCompanySize;
    private String segCompanyType;
    private String sistema;
    private String susClasiMic;
    private String telefono;
    private String tipoCambio;
    private String tipoDispositivo;
    private String tipoOperacion;
    private String tipoTransaccion;
    private String tipoVenta;
    private int valorAbVenta;
    private String vendedorAs400;
    private String periodo;
    private String productoGlobal;
    /*fin propiedades insert*/

    public List<DetalleVentaOperacion> getVentasFiltradas() {
        return ventasFiltradas;
    }

    public void setVentasFiltradas(List<DetalleVentaOperacion> ventasFiltradas) {
        this.ventasFiltradas = ventasFiltradas;
    }
    
    public float getSumatoriaEnCargarFiltrosMontoVenta() {
        return sumatoriaEnFiltrosMontoVenta;
    }

    public void setSumatoriaEnCargarFiltrosMontoVenta(float sumatoriaEnFiltrosMontoVenta) {
        this.sumatoriaEnFiltrosMontoVenta = sumatoriaEnFiltrosMontoVenta;
    }  
    
    public float getSumatoriaEnCargarMontoVenta() {
        return sumatoriaEnCargarMontoVenta;
    }

    public void setSumatoriaEnCargarMontoVenta(float sumatoriaEnCargarMontoVenta) {
        this.sumatoriaEnCargarMontoVenta += sumatoriaEnCargarMontoVenta;
    }    
    
    public String getSumatoriaMontoVenta() {
        //this.sumatoriaMontoVenta = sumatoriaMontoVenta;        
        float valorSumatoria = 0;        
        
        if(this.sumatoriaEnCargarMontoVenta!=0){
            valorSumatoria = this.sumatoriaEnCargarMontoVenta;            
        }
                        
        if(this.sumatoriaEnFiltrosMontoVenta !=0){
            valorSumatoria = this.sumatoriaEnFiltrosMontoVenta;
        }
        
        this.sumatoriaMontoVenta=0;
        this.sumatoriaEnFiltrosMontoVenta=0;
        
        return this.retornarDecimal(valorSumatoria);
        //return sumatoriaMontoVenta;
    }

    public void setSumatoriaMontoVenta(String sumatoriaMontoVenta) {        
        if(!sumatoriaMontoVenta.equals("0") && Float.parseFloat(sumatoriaMontoVenta)!=0){
            this.sumatoriaMontoVenta += Float.parseFloat(sumatoriaMontoVenta);
        }       
    }        
    
    public void sumarMontoVentaFiltradas(FilterEvent filterEvent){                
        float sumatoria=0;
        //float sumatoriaValorEnlace=0;
        if(this.ventasFiltradas!=null){          
            for(int i=0; i< this.ventasFiltradas.size(); i++){                  
                sumatoria += Float.parseFloat(this.ventasFiltradas.get(i).getMontoVenta());
                //sumatoriaValorEnlace += Float.parseFloat(this.ventasFiltradas.get(i).getValorEnlace());
            }                      
        }
        this.cantidadFilas =0;
        this.sumatoriaEnCargarMontoVenta=0;        
        
        if(this.ventasFiltradas!=null){    
            this.cantidadFilas=ventasFiltradas.size();
        }        
        this.sumatoriaEnFiltrosMontoVenta=sumatoria;        
        
    }
    
    
    public String getComboSistemaSelected() {
        return comboSistemaSelected;
    }

    /*inicio Combos crear nueva operacion*/

    public int getCantidadFilas() {
        return cantidadFilas;
    }

    public void setCantidadFilas(int cantidadFilas) {
        this.cantidadFilas = cantidadFilas;
    }
    
    
    
    public void setComboSistemaSelected(String comboSistemaSelected) {    
        this.comboSistemaSelected = comboSistemaSelected;
    }

    public String getComboFuenteSelected() {
        return comboFuenteSelected;
    }

    public void setComboFuenteSelected(String comboFuenteSelected) {
        this.comboFuenteSelected = comboFuenteSelected;
    }

    public List<DetalleVentaOperacion> getListaCombosTipoTran() {
        return listaCombosTipoTran;
    }

    public void setListaCombosTipoTran(List<DetalleVentaOperacion> listaCombosTipoTran) {
        this.listaCombosTipoTran = listaCombosTipoTran;
    }

    public String getComboTipoTranSelected() {
        return comboTipoTranSelected;
    }

    public void setComboTipoTranSelected(String comboTipoTranSelected) {
        this.comboTipoTranSelected = comboTipoTranSelected;
    }

    public List<DetalleVentaOperacion> getListaCombosCodVenta() {
        return listaCombosCodVenta;
    }

    public void setListaCombosCodVenta(List<DetalleVentaOperacion> listaCombosCodVenta) {
        this.listaCombosCodVenta = listaCombosCodVenta;
    }

    public String getComboCodVentaSelected() {
        return comboCodVentaSelected;
    }

    public void setComboCodVentaSelected(String comboCodVentaSelected) {
        this.comboCodVentaSelected = comboCodVentaSelected;
    }

    public List<DetalleVentaOperacion> getListaCombosNombreVenta() {
        return listaCombosNombreVenta;
    }

    public void setListaCombosNombreVenta(List<DetalleVentaOperacion> listaCombosNombreVenta) {
        this.listaCombosNombreVenta = listaCombosNombreVenta;
    }

    public String getComboNombreVentaSelected() {
        return comboNombreVentaSelected;
    }

    public void setComboNombreVentaSelected(String comboNombreVentaSelected) {
        this.comboNombreVentaSelected = comboNombreVentaSelected;
    }

    public List<DetalleVentaOperacion> getListaCombosModelo() {
        return listaCombosModelo;
    }

    public void setListaCombosModelo(List<DetalleVentaOperacion> listaCombosModelo) {
        this.listaCombosModelo = listaCombosModelo;
    }

    public String getComboModeloSelected() {
        return comboModeloSelected;
    }

    public void setComboModeloSelected(String comboModeloSelected) {
        this.comboModeloSelected = comboModeloSelected;
    }

    public List<DetalleVentaOperacion> getListaCombosClasiMic() {
        return listaCombosClasiMic;
    }

    public void setListaCombosClasiMic(List<DetalleVentaOperacion> listaCombosClasiMic) {
        this.listaCombosClasiMic = listaCombosClasiMic;
    }

    public String getComboClasiMicSelected() {
        return comboClasiMicSelected;
    }

    public void setComboClasiMicSelected(String comboClasiMicSelected) {
        this.comboClasiMicSelected = comboClasiMicSelected;
    }

    public List<DetalleVentaOperacion> getListaCombosProdTb() {
        return listaCombosProdTb;
    }

    public void setListaCombosProdTb(List<DetalleVentaOperacion> listaCombosProdTb) {
        this.listaCombosProdTb = listaCombosProdTb;
    }

    public String getComboProdTbSelected() {
        return comboProdTbSelected;
    }

    public void setComboProdTbSelected(String comboProdTbSelected) {
        this.comboProdTbSelected = comboProdTbSelected;
    }

    public List<DetalleVentaOperacion> getListaCombosAbVenta() {
        return listaCombosAbVenta;
    }

    public void setListaCombosAbVenta(List<DetalleVentaOperacion> listaCombosAbVenta) {
        this.listaCombosAbVenta = listaCombosAbVenta;
    }

    public String getComboAbVentaSelected() {
        return comboAbVentaSelected;
    }

    public void setComboAbVentaSelected(String comboAbVentaSelected) {
        this.comboAbVentaSelected = comboAbVentaSelected;
    }

    public List<DetalleVentaOperacion> getListaCombosTipoOperacion() {
        return listaCombosTipoOperacion;
    }

    public void setListaCombosTipoOperacion(List<DetalleVentaOperacion> listaCombosTipoOperacion) {
        this.listaCombosTipoOperacion = listaCombosTipoOperacion;
    }

    public String getComboTipoOperacionSelected() {
        return comboTipoOperacionSelected;
    }

    public void setComboTipoOperacionSelected(String comboTipoOperacionSelected) {
        this.comboTipoOperacionSelected = comboTipoOperacionSelected;
    }

    public List<DetalleVentaOperacion> getListaCombosPeriodo() {
        return listaCombosPeriodo;
    }

    public void setListaCombosPeriodo(List<DetalleVentaOperacion> listaCombosPeriodo) {
        this.listaCombosPeriodo = listaCombosPeriodo;
    }

    public String getComboPeriodoSelected() {
        return comboPeriodoSelected;
    }

    public void setComboPeriodoSelected(String comboPeriodoSelected) {
        this.comboPeriodoSelected = comboPeriodoSelected;
    }

    public String getPeriodoSelected() {
        return periodoSelected;
    }

    public void setPeriodoSelected(String periodoSelected) {
        this.periodoSelected = periodoSelected;
    }
    
    

    /*fin combos*/
    
    
    public List<DetalleVentaOperacion> getVentasOperacionSeleccionadas() {
        return ventasOperacionSeleccionadas;
    }

    public void setVentasOperacionSeleccionadas(List<DetalleVentaOperacion> ventasOperacionSeleccionadas) {
        this.ventasOperacionSeleccionadas = ventasOperacionSeleccionadas;
    }

    public List<DetalleVentaOperacion> getListaVentasOperacion() {
        return listaVentasOperacion;
    }

    public void setListaVentasOperacion(List<DetalleVentaOperacion> listaVentasOperacion) {
        this.listaVentasOperacion = listaVentasOperacion;
    }

    public int getIdSelected() {
        return idSelected;
    }

    public void setIdSelected(int idSelected) {
        this.idSelected = idSelected;
    }

    public String getNombreCoordinadorOperSelected() {
        return nombreCoordinadorOperSelected;
    }

    public void setNombreCoordinadorOperSelected(String nombreCoordinadorOperSelected) {
        this.nombreCoordinadorOperSelected = nombreCoordinadorOperSelected;
    }

    public List<CoordinadorOperacion> getListaCoordinadorOperacion() {
        return listaCoordinadorOperacion;
    }

    public void setListaCoordinadorOperacion(List<CoordinadorOperacion> listaCoordinadorOperacion) {
        this.listaCoordinadorOperacion = listaCoordinadorOperacion;
    }

    public CoordinadorOperacion getCoordinadorOperSelected() {
        return coordinadorOperSelected;
    }

    public void setCoordinadorOperSelected(CoordinadorOperacion coordinadorOperSelected) {
        this.coordinadorOperSelected = coordinadorOperSelected;
    }

    public AsesorOperacion getAsesorOperSelected() {
        return asesorOperSelected;
    }

    public void setAsesorOperSelected(AsesorOperacion asesorOperSelected) {
        this.asesorOperSelected = asesorOperSelected;
    }

    public List<AsesorOperacion> getListaAsesores() {
        return listaAsesores;
    }

    public void setListaAsesores(List<AsesorOperacion> listaAsesores) {
        this.listaAsesores = listaAsesores;
    }

    public List<AsesorOperacion> getListaAsesorOperacion() {
        return listaAsesorOperacion;
    }

    public void setListaAsesorOperacion(List<AsesorOperacion> listaAsesorOperacion) {
        this.listaAsesorOperacion = listaAsesorOperacion;
    }

    public String getNombreAsesorSelected() {
        return nombreAsesorSelected;           
    }

    public void setNombreAsesorSelected(String nombreAsesorSelected) {
        this.nombreAsesorSelected = nombreAsesorSelected;
        this.blnActivoAsignarSelected=false;/*Habilitar boton guardar asignacion Asesor*/
    }

    public boolean isBlnActivoSwitchEdicion() {
        return blnActivoSwitchEdicion;
    }

    public void setBlnActivoSwitchEdicion(boolean blnActivoSwitchEdicion) {
        this.blnActivoSwitchEdicion = blnActivoSwitchEdicion;
    }

    public boolean isBlnActivoEdicionValMonto() {
        return blnActivoEdicionValMonto;
    }

    public void setBlnActivoEdicionValMonto(boolean blnActivoEdicionValMonto) {
        this.blnActivoEdicionValMonto = blnActivoEdicionValMonto;
    }

    public boolean isBlnActivoOneSelected() {
        return blnActivoOneSelected;
    }

    public void setBlnActivoOneSelected(boolean blnActivoOneSelected) {
        this.blnActivoOneSelected = blnActivoOneSelected;
    }
    
        public boolean isBlnActivoEdicion() {
        return blnActivoEdicion;
    }

    public void setBlnActivoEdicion(boolean blnActivoEdicion) {
        this.blnActivoEdicion = blnActivoEdicion;
    }

    public boolean isBlnActivoAsignarSelected() {
        return blnActivoAsignarSelected;
    }

    public void setBlnActivoAsignarSelected(boolean blnActivoAsignarSelected) {
        this.blnActivoAsignarSelected = blnActivoAsignarSelected;
        this.blnActivoAsignarSelected=true;
    }    

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public List<Comentario> getListaComentarioxVenta() {
        return listaComentarioxVenta;
    }

    public void setListaComentarioxVenta(List<Comentario> listaComentarioxVenta) {
        this.listaComentarioxVenta = listaComentarioxVenta;
    }

    public String getAbVenta() {
        return abVenta;
 }    

    public void setAbVenta(String abVenta) {
        this.abVenta = abVenta;
    }

    public String getAnexoPadre() {
        return anexoPadre;
    }

    public void setAnexoPadre(String anexoPadre) {
        this.anexoPadre = anexoPadre;
    }

    public int getAneIns() {
        return aneIns;
    }

    public void setAneIns(int aneIns) {
        this.aneIns = aneIns;
    }

    public int getBusinessCd() {
        return businessCd;
    }

    public void setBusinessCd(int businessCd) {
        this.businessCd = businessCd;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getClasiMic() {
        return clasiMic;
    }

    public void setClasiMic(String clasiMic) {
        this.clasiMic = clasiMic;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getClienteMic() {
        return clienteMic;
    }

    public void setClienteMic(String clienteMic) {
        this.clienteMic = clienteMic;
    }

    public String getClienteWholeSale() {
        return clienteWholeSale;
    }

    public void setClienteWholeSale(String clienteWholeSale) {
        this.clienteWholeSale = clienteWholeSale;
    }

    public int getCntRguVenta() {
        return cntRguVenta;
    }

    public void setCntRguVenta(int cntRguVenta) {
        this.cntRguVenta = cntRguVenta;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {        
        this.codCliente = codCliente;
    }

    public int getCodDistribuidor() {
        return codDistribuidor;
    }

    public void setCodDistribuidor(int codDistribuidor) {
        this.codDistribuidor = codDistribuidor;
    }

    public String getCodModelo() {
        return codModelo;
    }

    public void setCodModelo(String codModelo) {
        this.codModelo = codModelo;
    }

    public String getCodPromocion() {
        return codPromocion;
    }

    public void setCodPromocion(String codPromocion) {
        this.codPromocion = codPromocion;
    }

    public int getCodVendedor() {
        return codVendedor;
    }

    public void setCodVendedor(int codVendedor) {
        this.codVendedor = codVendedor;
    }

    public String getCodVendedorNp() {
        return codVendedorNp;
    }

    public void setCodVendedorNp(String codVendedorNp) {
        this.codVendedorNp = codVendedorNp;
    }

    public int getCodVenta() {
        return codVenta;
    }

    public void setCodVenta(int codVenta) {
        this.codVenta = codVenta;
    }

    public String getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(String coordinador) {
        this.coordinador = coordinador;
    }

    public String getDistribuidorAs400() {
        return distribuidorAs400;
    }

    public void setDistribuidorAs400(String distribuidorAs400) {
        this.distribuidorAs400 = distribuidorAs400;
    }

    public String getEjecutivoVenta() {
        return ejecutivoVenta;
    }

    public void setEjecutivoVenta(String ejecutivoVenta) {
        this.ejecutivoVenta = ejecutivoVenta;
    }

    public String getEtadoAnulacion() {
        return etadoAnulacion;
    }

    public void setEtadoAnulacion(String etadoAnulacion) {
        this.etadoAnulacion = etadoAnulacion;
    }

    public Date getFecha() {
        return fecha; 
        
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getGerente() {
        return gerente;
    }

    public void setGerente(String gerente) {
        this.gerente = gerente;
    }

    public String getIdProductoAs400() {
        return idProductoAs400;
    }

    public void setIdProductoAs400(String idProductoAs400) {
        this.idProductoAs400 = idProductoAs400;
    }

    public int getMesesContrato() {
        return mesesContrato;
    }

    public void setMesesContrato(int mesesContrato) {
        this.mesesContrato = mesesContrato;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMonedaVenta() {
        return monedaVenta;
    }

    public void setMonedaVenta(String monedaVenta) {
        this.monedaVenta = monedaVenta;
    }

    public float getMontoVenta() {
        return montoVenta;
    }

    public void setMontoVenta(float montoVenta) {
        this.montoVenta = montoVenta;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombreVenta() {
        return nombreVenta;
    }

    public void setNombreVenta(String nombreVenta) {
        this.nombreVenta = nombreVenta;
    }

    public String getProductoTb() {
        return productoTb;
    }

    public void setProductoTb(String productoTb) {
        this.productoTb = productoTb;
    }

    public String getProductoVenta() {
        return productoVenta;
    }

    public void setProductoVenta(String productoVenta) {
        this.productoVenta = productoVenta;
    }

    public String getPromocion() {
        return promocion;
    }

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }

    public String getSegCompanyRvn() {
        return segCompanyRvn;
    }

    public void setSegCompanyRvn(String segCompanyRvn) {
        this.segCompanyRvn = segCompanyRvn;
    }

    public String getSegCompanySize() {
        return segCompanySize;
    }

    public void setSegCompanySize(String segCompanySize) {
        this.segCompanySize = segCompanySize;
    }

    public String getSegCompanyType() {
        return segCompanyType;
    }

    public void setSegCompanyType(String segCompanyType) {
        this.segCompanyType = segCompanyType;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getSusClasiMic() {
        return susClasiMic;
    }

    public void setSusClasiMic(String susClasiMic) {
        this.susClasiMic = susClasiMic;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public int getValorAbVenta() {
        return valorAbVenta;
    }

    public void setValorAbVenta(int valorAbVenta) {
        this.valorAbVenta = valorAbVenta;
    }

    public String getVendedorAs400() {
        return vendedorAs400;
    }

    public void setVendedorAs400(String vendedorAs400) {
        this.vendedorAs400 = vendedorAs400;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getProductoGlobal() {
        return productoGlobal;
    }

    public String getNombreCoordinadorOperVistaSelected() {
        return nombreCoordinadorOperVistaSelected;
    }

    public void setNombreCoordinadorOperVistaSelected(String nombreCoordinadorOperVistaSelected) {
        this.nombreCoordinadorOperVistaSelected = nombreCoordinadorOperVistaSelected;
    }

    public CoordinadorOperacion getCoordinadorOperVistaSelected() {
        return coordinadorOperVistaSelected;
    }

    public void setCoordinadorOperVistaSelected(CoordinadorOperacion coordinadorOperVistaSelected) {
        this.coordinadorOperVistaSelected = coordinadorOperVistaSelected;
    }

    public String getNombreAsesorSelectedVista() {
        return nombreAsesorSelectedVista;
    }

    public void setNombreAsesorSelectedVista(String nombreAsesorSelectedVista) {
        this.nombreAsesorSelectedVista = nombreAsesorSelectedVista;
    }

    /*inicio objeto insert nueva venta*/
    public void setProductoGlobal(String productoGlobal) {    
        this.productoGlobal = productoGlobal;
    }

    /*fin objeto insert nueva venta*/
    
    
    /*inicio objeto coordinadores y asesores para combos form nueva venta*/
    
    public List<CoordinadorOperacion> getListaCoordinadorOperacionVista() {
        
        //listaCoordinadorOperacionVista= obtenerCoordinadoresOperacionVista();
        
        return listaCoordinadorOperacionVista;
    }

    public void setListaCoordinadorOperacionVista(List<CoordinadorOperacion> listaCoordinadorOperacionVista) {
        this.listaCoordinadorOperacionVista = listaCoordinadorOperacionVista;
    }

    public List<DetalleVentaOperacion> getListaCombosOperacionVista() {
        return listaCombosOperacionVista;
    }

    public void setListaCombosOperacionVista(List<DetalleVentaOperacion> listaCombosOperacionVista) {
        this.listaCombosOperacionVista = listaCombosOperacionVista;
    }

    public List<DetalleVentaOperacion> getListaCombosFuente() {
        return listaCombosFuente;
    }

    public void setListaCombosFuente(List<DetalleVentaOperacion> listaCombosFuente) {
        this.listaCombosFuente = listaCombosFuente;
    }
    
    

    public List<AsesorOperacion> getListaAsesoresOperacionVista() {
        return listaAsesoresOperacionVista;
    }

    public void setListaAsesoresOperacionVista(List<AsesorOperacion> listaAsesoresOperacionVista) {
        this.listaAsesoresOperacionVista = listaAsesoresOperacionVista;
    }
    
    
    
    
    /*fin*/

    
    
    /*Funcion para el boton Mostrar Grid*/
    public void btnClickMostrarGridOperacion(ActionEvent actionEvent) {
        /*Traer las ventas y mostrarlas en el grid*/
        this.sumatoriaEnCargarMontoVenta = 0;
        this.sumatoriaEnFiltrosMontoVenta=0;
        System.out.println("Boton Mostrar Operacion");
        this.listaVentasOperacion = this.getVentasOperacion();
    }    
    
    
        public void cambiarEstadoEdicion() {
        if(this.blnActivoSwitchEdicion){
            //float cuotaBasica = Float.parseFloat(this.ventaOperacionSeleccionadaDetalle.getId());
            //float valMonto = Float.parseFloat(this.ventaOperacionSeleccionadaDetalle.getMontoVenta());
            float valMonto = Float.parseFloat(this.ventaOperacionSeleccionadaDetalle.getMontoVenta());
            
            if(valMonto>=0){
                this.blnActivoEdicionValMonto=false;                
            }
            
            this.blnActivoEdicion=false;
        }else{
            this.blnActivoEdicion=true;
            this.blnActivoEdicionValMonto=true;
        }
    }

          
      public void oneAsesorOperChange(){
        String asesorSeleccionado = this.getNombreAsesorSelected();
        String periodoSeleccionado = this.getComboPeriodoSelected();
        //String asesorSeleccionado = "Edgar Haroldo Milian Reyes";
        if(asesorSeleccionado!=null){
            this.sumatoriaEnFiltrosMontoVenta =0;
            this.sumatoriaEnCargarMontoVenta =0;
            this.listaVentasOperacion = this.getVentasOperacionXAsesor(asesorSeleccionado, periodoSeleccionado);
            
            //this.setBlnActivoAceptarFinalizar(false);
            //this.setBlnActivoDesAsignar(false);
            //this.setblnActivoReAsignar(false);
            //this.setBlnActivoAgregar(false);
        }else{
            //this.setBlnActivoAceptarFinalizar(true);
            //this.setBlnActivoAgregar(true);
            this.listaVentasOperacion=null;
            //this.setBlnActivoDesAsignar(true);
            //this.setblnActivoReAsignar(true);
        }   
    }
      
         public void onePeriodoOperChange(){
        String asesorSeleccionado = this.getNombreAsesorSelected();
        String periodoSeleccionado = this.getComboPeriodoSelected();
        //String asesorSeleccionado = "Edgar Haroldo Milian Reyes";
        if(asesorSeleccionado!=null){
            this.sumatoriaEnFiltrosMontoVenta =0;
            this.sumatoriaEnCargarMontoVenta =0;
            this.listaVentasOperacion = this.getVentasOperacionXAsesor(asesorSeleccionado, periodoSeleccionado);
            
            //this.setBlnActivoAceptarFinalizar(false);
            //this.setBlnActivoDesAsignar(false);
            //this.setblnActivoReAsignar(false);
            //this.setBlnActivoAgregar(false);
        }else{
            //this.setBlnActivoAceptarFinalizar(true);
            //this.setBlnActivoAgregar(true);
            this.listaVentasOperacion=null;
            //this.setBlnActivoDesAsignar(true);
            //this.setblnActivoReAsignar(true);
        }   
    }  
      
    
      public void oneAsesorOperVistaChange(){
        String asesorSeleccionado = this.getNombreAsesorSelectedVista();
        //String asesorSeleccionado = "Edgar Haroldo Milian Reyes";
        if(asesorSeleccionado!=null){
            //this.sumatoriaEnFiltrosCuotaBasica =0;
            //this.sumatoriaEnCargarCuotaBasica =0;
            this.listaCoordinadorOperacionVista = this.obtenerCoordinadoresOperacionVista(asesorSeleccionado);
            
            //this.setBlnActivoAceptarFinalizar(false);
            //this.setBlnActivoDesAsignar(false);
            //this.setblnActivoReAsignar(false);
            //this.setBlnActivoAgregar(false);
        }else{
            //this.setBlnActivoAceptarFinalizar(true);
            //this.setBlnActivoAgregar(true);
            this.listaCoordinadorOperacionVista=null;
            //this.setBlnActivoDesAsignar(true);
            //this.setblnActivoReAsignar(true);
        }   
    }  
      
      public List<DetalleVentaOperacion> getVentasOperacion1(){   System.out.println("test"); 
      /*test corregir bug de 4 ejecuciones de metodo que causa lentitud*/
      List<DetalleVentaOperacion> listaVentasOperacion = new ArrayList<DetalleVentaOperacion>();
      this.listaVentasOperacion=null;
      return listaVentasOperacion;      
      }  
      
    public List<DetalleVentaOperacion> getVentasOperacion(){        
        List<DetalleVentaOperacion> listaVentasOperacion = new ArrayList<DetalleVentaOperacion>();
        Connection conex = null;
        this.cantidadFilas =0;
        try{                
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Querys_C query = new Querys_C();
            query.generar_Consulta_Parametros_Operacion();
            String ql = query.getConsulta_InformacionVentasOperacion();
            //rs1 = st1.executeQuery(query.getConsulta_InformacionVentasOperacion());
            rs1 = st1.executeQuery(ql);
            int contador=0;
            while (rs1.next())
            {                
                DetalleVentaOperacion detalle = new DetalleVentaOperacion();
                detalle.setId((rs1.getInt("ID")));
                detalle.setAneIns((rs1.getInt("ANE_INS")));
                detalle.setCategoria((rs1.getString("CATEGORIA")));
                detalle.setCliente((rs1.getString("CLIENTE")));
                detalle.setAbVenta((rs1.getString("AB_VENTA")));
                detalle.setAnexoPadre((rs1.getString("ANEXO_PADRE")));
                detalle.setBusinessCd((rs1.getInt("BUSINESS_CD")));
                detalle.setBusinessName((rs1.getString("BUSINESS_NAME")));
                detalle.setBusinessType((rs1.getString("BUSINESS_TYPE")));
                detalle.setClasiMic((rs1.getString("CLASI_MIC")));
                detalle.setClienteMic((rs1.getString("CLIENTE_MIC")));
                detalle.setClienteWholeSale((rs1.getString("CLIENTE_WHOLESALE")));
                detalle.setNit((rs1.getString("NIT")));
                detalle.setMontoVenta((rs1.getString("MONTO_VENTA")));
                this.setSumatoriaEnCargarMontoVenta(Float.parseFloat(rs1.getString("MONTO_VENTA")));
                detalle.setCntRguVenta((rs1.getInt("CNT_RGU_VENTA")));
                detalle.setCodCliente((rs1.getInt("COD_CLIENTE")));
                detalle.setCodDistribuidor((rs1.getInt("COD_DISTRIBUIDOR")));
                detalle.setCodModelo((rs1.getString("COD_MODELO")));
                detalle.setCodPromocion((rs1.getString("COD_PROMOCION")));
                detalle.setCodVendedor((rs1.getInt("COD_VENDEDOR")));
                detalle.setBusinessName((rs1.getString("COD_VENDEDOR_NP")));
                detalle.setCodVenta((rs1.getInt("COD_VENTA")));
                detalle.setCoordinador((rs1.getString("COORDINADOR")));
                detalle.setDistribuidorAs400((rs1.getString("DISTRIBUIDOR_AS400")));
                detalle.setEjecutivoVenta((rs1.getString("EJECUTIVO_VENTA")));
                detalle.setGerente((rs1.getString("GERENTE")));
                detalle.setPeriodo((rs1.getString("PERIODO")));
                detalle.setProductoGlobal((rs1.getString("PRODUCTO_GLOBAL")));                
                listaVentasOperacion.add(detalle);
            }
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
        this.cantidadFilas = listaVentasOperacion.size();
        return listaVentasOperacion;
    }
    
    /*Filtrar ventas operacion por asesor seleccionado*/
    public List<DetalleVentaOperacion> getVentasOperacionXAsesor(String asesor, String periodo){
        List<DetalleVentaOperacion> listaVentasOperacion = new ArrayList<DetalleVentaOperacion>();
        Connection conex = null;
        this.cantidadFilas =0;
        try{                
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            //ResultSet rs = null;
            Statement st = conex.createStatement();
            
            Querys_C query = new Querys_C();
            query.generar_Consulta_Parametros_Operacion_XAsesor(asesor, periodo);
            //String ql = query.getConsulta_InformacionVentasOperacionXAsesor();
            ResultSet rs = st.executeQuery(query.getConsulta_InformacionVentasOperacionXAsesor());
            //ResultSet rs = st.executeQuery(ql);
            //int contador=0;
            while (rs.next())
            {                
                DetalleVentaOperacion det = new DetalleVentaOperacion();
                det.setId((rs.getInt("ID")));
                det.setAneIns((rs.getInt("ANE_INS")));
                det.setSistema((rs.getString("SISTEMA")));
                det.setFuente((rs.getString("FUENTE")));
                det.setFecha((rs.getString("FECHA")));
                det.setCategoria((rs.getString("CATEGORIA")));
                det.setCliente((rs.getString("CLIENTE")));
                det.setAbVenta((rs.getString("AB_VENTA")));
                det.setAnexoPadre((rs.getString("ANEXO_PADRE")));
                det.setBusinessCd((rs.getInt("BUSINESS_CD")));
                det.setBusinessName((rs.getString("BUSINESS_NAME")));
                det.setBusinessType((rs.getString("BUSINESS_TYPE")));
                det.setClasiMic((rs.getString("CLASI_MIC")));
                det.setClienteMic((rs.getString("CLIENTE_MIC")));
                det.setClienteWholeSale((rs.getString("CLIENTE_WHOLESALE")));
                det.setNit((rs.getString("NIT")));
                det.setMontoVenta((rs.getString("MONTO_VENTA")));
                this.setSumatoriaEnCargarMontoVenta(Float.parseFloat(rs.getString("MONTO_VENTA")));
                det.setCntRguVenta((rs.getInt("CNT_RGU_VENTA")));
                det.setCodCliente((rs.getInt("COD_CLIENTE")));
                det.setCodDistribuidor((rs.getInt("COD_DISTRIBUIDOR")));
                det.setCodModelo((rs.getString("COD_MODELO")));
                det.setModelo((rs.getString("MODELO")));
                det.setCodPromocion((rs.getString("COD_PROMOCION")));
                det.setCodVendedor((rs.getInt("COD_VENDEDOR")));
                det.setBusinessName((rs.getString("COD_VENDEDOR_NP")));
                det.setCodVenta((rs.getInt("COD_VENTA")));
                det.setCoordinador((rs.getString("COORDINADOR")));
                det.setDistribuidorAs400((rs.getString("DISTRIBUIDOR_AS400")));
                det.setEjecutivoVenta((rs.getString("EJECUTIVO_VENTA")));
                det.setGerente((rs.getString("GERENTE")));
                det.setPeriodo((rs.getString("PERIODO")));                
                det.setProductoGlobal((rs.getString("PRODUCTO_GLOBAL"))); 
                det.setMesesContrato((rs.getInt("MESES_CONTRATO")));
                det.setTipoCambio((rs.getString("TIPO_CAMBIO")));
                listaVentasOperacion.add(det);
            }
        }catch(Exception e){
            String erss = e.toString();
            int i =0;
        }
        try {
            conex.close();
            System.out.println("Cerrando conexion..getVentasOp x Ases");
        } catch (SQLException ex) {
            String ers = ex.toString();
        }
        this.cantidadFilas = listaVentasOperacion.size();
        return listaVentasOperacion;
    }
    
    
    public List<Comentario> obtenerListaxVentaComentarioBitacora(int idVenta){
        List<Comentario> comLista = new ArrayList<Comentario>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Querys_Bitacora query = new Querys_Bitacora();
            String querys= query.getConsultaBitacoraComentarioxVenta(idVenta);
            rs1 = st1.executeQuery(querys);
            
                
            while (rs1.next())
            {
                Comentario mol = new Comentario();
                mol.setFechaCambio((rs1.getString("FECHA")));
                mol.setComentario((rs1.getString("OBSERVACIONES")));/*Comentarios*/
                mol.setUsuario((rs1.getString("USUARIO")));
                mol.setIdVenta(Integer.parseInt(rs1.getString("ID")));
                comLista.add(mol);
            }         
            
            if(comLista.isEmpty()){
                Comentario mol = new Comentario();
                mol.setFechaCambio(("No hay Bitacora"));
                mol.setComentario("--");
                mol.setUsuario(("--"));
                comLista.add(mol);
            }
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
       
        return comLista;
    }

    
    /*Borrar asesor de Ventas Operacion Asignadas*/
    public void btnClickBorrarAsesor(ActionEvent actionEvent) {
        
        Conexion conec = new Conexion();                
        Connection conex = conec.getConexion();
        for(int i=0; i< this.ventasOperacionSeleccionadas.size(); i++){
            /*Obtener el ID seleccionado del grid*/
            DetalleVentaOperacion ventaOperacionSeleccionada = this.ventasOperacionSeleccionadas.get(i);                     
            /////////////////           
            try{                
                Querys_C query = new Querys_C();
                /*llamar consulta Borrar Asesor y enviarle el id de la venta para borrar*/
                query.generar_Consulta_BorrarAsesorVentaOperacion(ventaOperacionSeleccionada.getId());
                CallableStatement cstmt = conex.prepareCall(query.getBorrar_AsesorVentaOperacion());
                cstmt.executeQuery();                
                                              
            }catch(Exception e){
                
            }            
        }
        try {
            conex.close();
             //mensaje de borrado correctamente
            JsfUtil.addSuccessMessage(Constantes.MSG_SUCCESS_BORRA_VENTA_OPERACION);
        } catch (SQLException ex) {
        }
        int idSeleccionado = this.getIdSelected();
        //this.listaVentasOperacion = this.getVentasOperacionXId(idSelected);        
        /*Traer toda la lista y mostrarla en Grid*/
        System.out.println("Boton Borrar Asesor");
        this.listaVentasOperacion = this.getVentasOperacion1();
    }  
    

    
    /*ASIGNAR Asesor a Ventas Operacion */
    public void btnClickEjecutaProcPeriodo(ActionEvent actionEvent) {
        
        String periodo = this.getPeriodo();
        if(periodo!=null){
        
        Conexion conec = new Conexion();                
        Connection conex = conec.getConexion();        
            
            try{                
                Querys_C query = new Querys_C();
                /*llamar consulta que ejecuta proceso por periodo*/
                query.generar_Consulta_EjecutaProceso(periodo);
                CallableStatement cstmt = conex.prepareCall(query.getEjecutar_Proceso());                
                cstmt.executeQuery();                
                cstmt.close();
                                              
            }catch(Exception e){
                
            }                
           
        try {
            conex.close();
             //mensaje de asignado correctamente
            JsfUtil.addSuccessMessage("Proceso Finalizado Exitosamente!!");
        } catch (SQLException ex) {
        }
    }
    }    
    
    public DetalleVentaOperacion getVentaOperacionSeleccionadaDetalle() {
        return ventaOperacionSeleccionadaDetalle;
    }

    public void setVentaOperacionSeleccionadaDetalle(DetalleVentaOperacion ventaOperacionSeleccionadaDetalle) {
          this.blnActivoEdicion=true;/*Habilitar boton*/
        this.blnActivoSwitchEdicion=false;
        /*Bitacora de movimientos comentario*/
        this.listaComentarioxVenta =obtenerListaxVentaComentarioBitacora(ventaOperacionSeleccionadaDetalle.getId());
        this.ventaOperacionSeleccionadaDetalle = ventaOperacionSeleccionadaDetalle;
        //this.idMoneda="USD*";   
        
    }
    
    public void btnGuardarValorOperacion(ActionEvent actionEvent){
         
        Conexion conec = new Conexion();                
        Connection conex = conec.getConexion();
        String errorSQL="";
        String query = Query_Editar.getQueryEditarMontoOperacion();
        try{                
           
            int ventaSelect = ((this.ventaOperacionSeleccionadaDetalle.getId()));
            String montoOperacion;
            CallableStatement cstmt = conex.prepareCall(query);         
            cstmt.registerOutParameter(1,  java.sql.Types.INTEGER);
            //String idVenta, String numeroOperacion, String modulo, String monto) 
            if(!this.blnActivoEdicionValMonto){
                montoOperacion = this.ventaOperacionSeleccionadaDetalle.getMontoVenta();
                cstmt.setInt(2, ventaSelect);                             
                cstmt.setFloat(3, Float.parseFloat(montoOperacion));  
                
            }
            
            cstmt.execute();       
            /*lienas afectadas*/
            int valorRetorno = cstmt.getInt(1);
            cstmt.close();
            /*Si lineas afectadas diferente de 0*/ 
            if(valorRetorno!=0){
                /*Dar mensaje de cmabio realizado ok*/
                JsfUtil.addSuccessMessage(Constantes.MSG_SUCCESS_CAMBIARMONTO);
                this.blnActivoEdicion=true;
                this.blnActivoSwitchEdicion=false;
                this.blnActivoEdicionValMonto=true;
                //this.blnActivoEdicionValEnlace=true;
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
        this.listaVentasOperacion = this.getVentasOperacionXAsesor(this.nombreAsesorSelected, this.comboPeriodoSelected);
        
    }
    
    
      public void oneCoordinadorOperChange() {        
        String nombreCoordinadorOperFiltro = this.getNombreCoordinadorOperSelected();
        if(nombreCoordinadorOperFiltro!=null){
            this.listaAsesorOperacion = this.obtenerListadeAsesorOperacion(nombreCoordinadorOperFiltro);
            //this.setBlnActivoAgregar(false);//desactiva boton
        }else{
            //this.setBlnActivoAgregar(true);//activa boton
        }
        //listaMunicipio = abc.listMunicipiosByDepto(departamentoSelected);
    }
     

    
    @PostConstruct
    public void init() {           
        coordinadorOperSelected = new CoordinadorOperacion();
        asesorOperSelected = new AsesorOperacion();
        coordinadorOperVistaSelected = new CoordinadorOperacion();
        ////////////////////////// 
        String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
        String rolUsuarioLogueado = this.getAtributoSession(Constantes.SS_ROL);
        listaCombosPeriodo=this.obtenerComboPeriodo();
        listaCoordinadorOperacion = this.obtenerListadeCoordinadorOperacion();            
        listaAsesorOperacion=this.obtenerListadeVendedorOperacion(usuarioLogueado);
        /*combo inicial agregar venta*/
        //listaCoordinadorOperacionVista = this.obtenerCoordinadoresOperacionVista();
        
       
        if(usuarioLogueado!=null && rolUsuarioLogueado!=null){
            if(rolUsuarioLogueado.equals("COORD")){
                for(int i =0; i< listaCoordinadorOperacion.size(); i++){
                    String usuarioDom = listaCoordinadorOperacion.get(i).getUsuario_dominio();
                    if(usuarioDom.equals(usuarioLogueado)){
                        this.setBlnActivoOneSelected(true);/*deshabilita boton*/
                        //this.setBlnActivoAgregar(false);
                        //this.setblnActivoReAsignar(true);
                        this.setNombreCoordinadorOperSelected(listaCoordinadorOperacion.get(i).getNombre());                        
                        /*obtiene lista de asesores de acuerdo a coordinador seleccionado*/
                        this.listaAsesorOperacion = this.obtenerListadeAsesorOperacion(listaCoordinadorOperacion.get(i).getNombre());
                        break;
                    }
                }
            }
            if(rolUsuarioLogueado.equals("VENDE")){
                for(int i =0; i< listaAsesorOperacion.size(); i++){
                    String usuarioDom = listaAsesorOperacion.get(i).getUsuario_dominio();
                    if(usuarioDom.equals(usuarioLogueado)){
                        this.setBlnActivoOneSelected(true);/*deshabilita boton*/
                        //this.setBlnActivoAgregar(false);
                        //this.setblnActivoReAsignar(true);
                        this.setNombreAsesorSelected(listaAsesorOperacion.get(i).getNombre());                        
                        /*obtiene lista de asesores de acuerdo a usuario vendedor logueado y lo selecciona por default*/
                        this.listaAsesorOperacion = this.obtenerListadeVendedorOperacion(usuarioDom);
                        break;
                    }
                this.setBlnActivoOneSelected(true);/*deshabilita boton combo*/
            }
            }
            /*Si es ADMIN muestra combo coordinador*/
            this.setBlnActivoOneSelected(false);
            //this.setBlnActivoAgregar(true);
            //this.setBlnActivoDesAsignar(true);
            //this.setblnActivoReAsignar(true);
        
        }else{
                try{
                    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                    context.redirect(context.getRequestContextPath() + "/");
                    }catch(Exception e){            
                }             
        }      
    }
    
    
   public List<CoordinadorOperacion> obtenerListadeCoordinadorOperacion(){
        List<CoordinadorOperacion> listaCoordinadorOperacion = new ArrayList<CoordinadorOperacion>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Querys_C query = new Querys_C();
            query.generar_Consulta_Coordinador_Oper();
                       
            rs1 = st1.executeQuery(query.getConsulta_CoordinadorOperacion());
            
            while (rs1.next())
            {
                CoordinadorOperacion coor = new CoordinadorOperacion();
                coor.setNombre((rs1.getString("NOMBRE")));
                //coor.setIdCoordinador(Integer.parseInt(rs1.getString("ID")));
                coor.setUsuario_dominio(rs1.getString("COORDINADOR_USER"));
                listaCoordinadorOperacion.add(coor);
            }            
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaCoordinadorOperacion;
    }
    
    
    
    public List<AsesorOperacion> obtenerListadeAsesorOperacion(String nombreCoordinadorOperacion){
        List<AsesorOperacion> listaAsesorOperacion = new ArrayList<AsesorOperacion>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Querys_C query = new Querys_C();
            //query.generar_Consulta_Coordinador_Oper();
            query.generar_Consulta_Asesor_Oper(nombreCoordinadorOperacion);/*Filtrar asesores por coordinador*/
                       
            rs1 = st1.executeQuery(query.getConsulta_AsesorOperacion());
            
            while (rs1.next())
            {
                AsesorOperacion ases = new AsesorOperacion();
                ases.setNombre((rs1.getString("NOMBRE")));
                //coor.setIdCoordinador(Integer.parseInt(rs1.getString("ID")));
                //coor.setUsuario_dominio(rs1.getString("USUARIO_DOMINIO"));
                listaAsesorOperacion.add(ases);
            }            
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaAsesorOperacion;
    }
    
    public List<AsesorOperacion> obtenerListadeVendedorOperacion(String user){
        List<AsesorOperacion> listaAsesorOperacion = new ArrayList<AsesorOperacion>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Querys_C query = new Querys_C();
            //query.generar_Consulta_Coordinador_Oper();
            query.generar_Consulta_Vendedor_Oper(user);/*Filtrar asesores por coordinador*/
                       
            rs1 = st1.executeQuery(query.getConsulta_VendedorOperacion());
            
            while (rs1.next())
            {
                AsesorOperacion ases = new AsesorOperacion();
                ases.setNombre((rs1.getString("NOMBRE")));
                //coor.setIdCoordinador(Integer.parseInt(rs1.getString("ID")));
                ases.setUsuario_dominio(rs1.getString("USUARIO_USER"));
                listaAsesorOperacion.add(ases);
            }            
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaAsesorOperacion;
    }
    
    
    public List<DetalleVentaOperacion> obtenerComboSistema(){
      List<DetalleVentaOperacion> listaCombosOperacionVista = new ArrayList<DetalleVentaOperacion>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Querys_C query = new Querys_C();
            //query.generar_Consulta_Coordinador_Oper();
            query.generar_Consulta_Combo_Sistema();/*Filtrar asesores por coordinador*/
                       
            rs1 = st1.executeQuery(query.getConsulta_ComboSistema());
            
            while (rs1.next())
            {
                DetalleVentaOperacion dtv = new DetalleVentaOperacion();
                dtv.setSistema((rs1.getString("SISTEMA")));               
                listaCombosOperacionVista.add(dtv);
            }            
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaCombosOperacionVista;
    }
    
    
    /*Crear Nueva Operacion de venta*/
    
    public void cargarCombos(){
        /*Carga inicial de combos agregarVentaOperacion.xhtml*/
        listaCombosOperacionVista=this.obtenerComboSistema();
        listaCombosTipoTran=this.obtenerComboTipoTran();
        listaCombosCodVenta=this.obtenerComboCodVenta();
        listaCombosNombreVenta=this.obtenerComboNombreVenta();
        listaCombosModelo=this.obtenerComboModelo();
        listaCombosClasiMic=this.obtenerComboClasiMic();
        listaCombosProdTb=this.obtenerComboProdTb();
        listaCombosAbVenta=this.obtenerComboAbVenta();
        listaCombosTipoOperacion=this.obtenerComboTipoOperacion();
        listaAsesoresOperacionVista = this.obtenerAsesoresOperacionVista();
    }
    
        public void btnAgregarNuevo_click() {
        
        try {
            SimpleDateFormat fechaGeneralFormato = new SimpleDateFormat("dd/MM/yyyy");
            StringBuilder queryCrear = new StringBuilder();
            queryCrear.append("INSERT INTO COMISIONGT.TBBO_OPERACIONES");
            queryCrear.append("(SISTEMA, FUENTE, TIPO_TRANSACCION, FECHA, COD_CLIENTE, CLIENTE_MIC,"
                    + "CLIENTE_WHOLESALE, NIT, TELEFONO, ANE_INS, COD_VENTA, NOMBRE_VENTA, MONTO_VENTA, "
                    + "ANEXO_PADRE, EJECUTIVO_VENTA, COD_VENDEDOR, COORDINADOR, GERENTE, COD_DISTRIBUIDOR, "
                    + "MODELO, CLASI_MIC, PRODUCTO_TB, COD_VENDEDOR_NP, CLIENTE, VENDEDOR_AS400, DISTRIBUIDOR_AS400, AB_VENTA, "
                    + "MESES_CONTRATO, TIPO_CAMBIO,TIPO_OPERACION,  PRODUCTO_GLOBAL )");
            queryCrear.append("VALUES (");
            queryCrear.append("'" + this.comboSistemaSelected + "'");
            if(this.fuente==null){
                queryCrear.append(",null" );
            }
            else{
                queryCrear.append(",'" + this.fuente + "'");
            }
            
            queryCrear.append(",'" + this.comboTipoTranSelected + "'");
            queryCrear.append(",TO_DATE('" + fechaGeneralFormato.format(this.fecha) + "','DD-MM-YYYY')");
            if(this.codCliente==0){
                queryCrear.append(",null" );
            }else{
                queryCrear.append("," + this.codCliente ); 
            }            
            queryCrear.append(",'" + this.clienteMic + "'");            
            if(this.clienteWholeSale==null){
                queryCrear.append(",null" );
            }
            else{
                queryCrear.append(",'" + this.clienteWholeSale + "'");
            }
            queryCrear.append(",'" + this.nit + "'");
            queryCrear.append(",'" + this.telefono + "'");
            queryCrear.append("," + this.aneIns );
            if(this.comboCodVentaSelected==""){
                queryCrear.append(",null" );
            }
            else{
                queryCrear.append("," + this.comboCodVentaSelected );
            }
            queryCrear.append(",'" + this.comboNombreVentaSelected + "'");
            queryCrear.append(",'" + this.montoVenta + "'");
            queryCrear.append(",'" + this.anexoPadre + "'");
            queryCrear.append(",'" + this.nombreAsesorSelectedVista + "'");
            if(this.codVendedor==0){
                queryCrear.append(",null" );
            }
            else{
                queryCrear.append("," + this.codVendedor );
            }            
            queryCrear.append(",'" + this.nombreCoordinadorOperVistaSelected + "'");
            queryCrear.append(",'" + this.gerente + "'");
            if(this.codDistribuidor==0){
                queryCrear.append(",null" );
            }
            else{
                queryCrear.append("," + this.codDistribuidor );
            }            
            queryCrear.append(",'" + this.comboModeloSelected + "'");
            queryCrear.append(",'" + this.comboClasiMicSelected + "'");
            queryCrear.append(",'" + this.comboProdTbSelected + "'");
            queryCrear.append(",'" + this.codVendedorNp + "'");
            queryCrear.append(",'" + this.cliente + "'");
            queryCrear.append(",'" + this.vendedorAs400 + "'");            
            queryCrear.append(",'" + this.distribuidorAs400 + "'");
            queryCrear.append(",'" + this.comboAbVentaSelected + "'");
            if(this.mesesContrato==0){
               queryCrear.append(",null" ); 
            }
            else{
                queryCrear.append("," + this.mesesContrato );
            }            
            queryCrear.append(",'" + this.tipoCambio +"'");
            queryCrear.append(",'" + this.comboTipoOperacionSelected + "'");
            queryCrear.append(",'" + this.productoGlobal + "'");
            
            queryCrear.append(")");

            Conexion conec = new Conexion();
            Connection conex = conec.getConexion();
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            System.out.println(queryCrear.toString());
            rs1 = st1.executeQuery(queryCrear.toString());            
            conex.close();
            //JsfUtil.addSuccessMessage(Constantes.SS_AGREGAROK);
            JsfUtil.addSuccessMessage("Venta agregada OK");

        } catch (SQLException e) {
            //JsfUtil.addErrorMessage(Constantes.SS_AGREGARERROR);
            JsfUtil.addErrorMessage("Error "+e.getMessage());
            //JsfUtil.addErrorMessage("Error al agregar Venta nueva");
        }
    }

    
        public void redireccionarNuevo() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/agregarVentaOperacion.xhtml");
        } catch (Exception e) {
        }

    }

    public void cancelarOperacion() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/ventasOperaciones.xhtml");
        } catch (Exception e) {
        }
    }
    
        public String formatoFecha(Date fecha) {
        SimpleDateFormat fechaGeneralFormato = new SimpleDateFormat("dd/MM/yyyy");
        return (fechaGeneralFormato.format(fecha));
    }
    
        
        /*Para uso en agregarVentaOperacion.xhtml*/
        public List<CoordinadorOperacion> obtenerCoordinadoresOperacionVista(String asesor){
        List<CoordinadorOperacion> listaCoordinadorOperacionVista = new ArrayList<CoordinadorOperacion>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Querys_C query = new Querys_C();
            query.generar_Consulta_Coordinador_Vista(asesor);
                       
            rs1 = st1.executeQuery(query.getConsulta_CoordinadorOperacionVista());
            
            while (rs1.next())
            {
                CoordinadorOperacion coor = new CoordinadorOperacion();
                coor.setNombre((rs1.getString("NOMBRE")));
                coor.setGerente(rs1.getString("GERENTE"));
                //coor.setUsuario_dominio(rs1.getString("USUARIO_DOMINIO"));
                listaCoordinadorOperacionVista.add(coor);
            }
            st1.close();
            rs1.close();
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaCoordinadorOperacionVista;
    }
        
    /*Para uso en agregarVentaOperacion.xhtml*/
        public List<AsesorOperacion> obtenerAsesoresOperacionVista(){
        List<AsesorOperacion> listaAsesoresOperacionVista = new ArrayList<AsesorOperacion>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            String miUsuario = this.getAtributoSession(Constantes.SS_USUARIO);
            String miRol = this.getAtributoSession(Constantes.SS_ROL);
            
            Querys_C query = new Querys_C();
            query.generar_Consulta_Asesor_Vista(miUsuario, miRol);
                       
            rs1 = st1.executeQuery(query.getConsulta_AsesorOperacionVista());
            
            while (rs1.next())
            {
                AsesorOperacion coor = new AsesorOperacion();
                coor.setNombre((rs1.getString("NOMBRE")));
                //coor.setIdCoordinador(Integer.parseInt(rs1.getString("ID")));
                //coor.setUsuario_dominio(rs1.getString("USUARIO_DOMINIO"));
                listaAsesoresOperacionVista.add(coor);
            }
            st1.close();
            rs1.close();
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaAsesoresOperacionVista;
    }
        
    public List<DetalleVentaOperacion> obtenerComboFuente(){
      List<DetalleVentaOperacion> listaCombosFuente = new ArrayList<DetalleVentaOperacion>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            //Querys_C query = new Querys_C();            
            //query.generar_Consulta_Combo_Sistema();/*Filtrar asesores por coordinador*/
            String query = "SELECT DISTINCT(FUENTE) FUENTE FROM TBBO_OPERACIONES WHERE FUENTE IS NOT NULL;";
                       
            rs1 = st1.executeQuery(query);
            
            while (rs1.next())
            {
                DetalleVentaOperacion dtv = new DetalleVentaOperacion();
                dtv.setFuente((rs1.getString("FUENTE")));               
                listaCombosFuente.add(dtv);
            }            
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaCombosFuente;
    }
    
    public List<DetalleVentaOperacion> obtenerComboTipoTran(){
      List<DetalleVentaOperacion> listaCombosTipoTran = new ArrayList<DetalleVentaOperacion>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            //Querys_C query = new Querys_C();            
            //query.generar_Consulta_Combo_Sistema();/*Filtrar asesores por coordinador*/
            String query = "SELECT DISTINCT(tipo_transaccion) TRAN FROM TBBO_OPERACIONES WHERE tipo_transaccion IS NOT NULL order by 1";
                       
            rs1 = st1.executeQuery(query);
            
            while (rs1.next())
            {
                DetalleVentaOperacion dtv = new DetalleVentaOperacion();
                dtv.setTipoTransaccion((rs1.getString("TRAN")));               
                listaCombosTipoTran.add(dtv);
            }            
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaCombosTipoTran;
    }
    
    public List<DetalleVentaOperacion> obtenerComboCodVenta(){
      List<DetalleVentaOperacion> listaCombosCodVenta = new ArrayList<DetalleVentaOperacion>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            //Querys_C query = new Querys_C();            
            //query.generar_Consulta_Combo_Sistema();/*Filtrar asesores por coordinador*/
            String query = "SELECT DISTINCT(cod_venta) cod FROM TBBO_OPERACIONES order by 1";
                       
            rs1 = st1.executeQuery(query);
            
            while (rs1.next())
            {
                DetalleVentaOperacion dtv = new DetalleVentaOperacion();
                dtv.setCodVenta((rs1.getInt("cod")));               
                listaCombosCodVenta.add(dtv);
            }            
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaCombosCodVenta;
    }
    
    public List<DetalleVentaOperacion> obtenerComboNombreVenta(){
      List<DetalleVentaOperacion> listaCombosNombreVenta = new ArrayList<DetalleVentaOperacion>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            //Querys_C query = new Querys_C();            
            //query.generar_Consulta_Combo_Sistema();/*Filtrar asesores por coordinador*/
            String query = "SELECT DISTINCT(NOMBRE_VENTA) NOMBRE FROM TBBO_OPERACIONES  WHERE NOMBRE_VENTA IS NOT NULL order by 1";
                       
            rs1 = st1.executeQuery(query);
            
            while (rs1.next())
            {
                DetalleVentaOperacion dtv = new DetalleVentaOperacion();
                dtv.setNombreVenta((rs1.getString("NOMBRE")));               
                listaCombosNombreVenta.add(dtv);
            }            
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaCombosNombreVenta;
    }
    
    public List<DetalleVentaOperacion> obtenerComboModelo(){
      List<DetalleVentaOperacion> listaCombosModelo = new ArrayList<DetalleVentaOperacion>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            //Querys_C query = new Querys_C();            
            //query.generar_Consulta_Combo_Sistema();/*Filtrar asesores por coordinador*/
            String query = "SELECT MODELO FROM TBBO_OPERACIONES GROUP BY MODELO ORDER BY 1";
                       
            rs1 = st1.executeQuery(query);
            
            while (rs1.next())
            {
                DetalleVentaOperacion dtv = new DetalleVentaOperacion();
                dtv.setModelo((rs1.getString("modelo")));               
                listaCombosModelo.add(dtv);
            }            
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaCombosModelo;
    }
    
    
    public List<DetalleVentaOperacion> obtenerComboClasiMic(){
      List<DetalleVentaOperacion> listaCombosClasiMic = new ArrayList<DetalleVentaOperacion>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            //Querys_C query = new Querys_C();            
            //query.generar_Consulta_Combo_Sistema();/*Filtrar asesores por coordinador*/
            String query = "SELECT CLASI_MIC FROM TBBO_OPERACIONES GROUP BY CLASI_MIC ORDER BY 1";
                       
            rs1 = st1.executeQuery(query);
            
            while (rs1.next())
            {
                DetalleVentaOperacion dtv = new DetalleVentaOperacion();
                dtv.setClasiMic((rs1.getString("CLASI_MIC")));               
                listaCombosClasiMic.add(dtv);
            }            
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaCombosClasiMic;
    }
    
    public List<DetalleVentaOperacion> obtenerComboProdTb(){
      List<DetalleVentaOperacion> listaCombosProdTb = new ArrayList<DetalleVentaOperacion>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            //Querys_C query = new Querys_C();            
            //query.generar_Consulta_Combo_Sistema();/*Filtrar asesores por coordinador*/
            String query = "SELECT PRODUCTO_TB FROM TBBO_OPERACIONES GROUP BY PRODUCTO_TB ORDER BY 1";
                       
            rs1 = st1.executeQuery(query);
            
            while (rs1.next())
            {
                DetalleVentaOperacion dtv = new DetalleVentaOperacion();
                dtv.setProductoTb((rs1.getString("PRODUCTO_TB")));               
                listaCombosProdTb.add(dtv);
            }            
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaCombosProdTb;
    }
    
    public List<DetalleVentaOperacion> obtenerComboAbVenta(){
      List<DetalleVentaOperacion> listaCombosAbVenta = new ArrayList<DetalleVentaOperacion>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            //Querys_C query = new Querys_C();            
            //query.generar_Consulta_Combo_Sistema();/*Filtrar asesores por coordinador*/
            String query = "SELECT AB_VENTA FROM TBBO_OPERACIONES GROUP BY AB_VENTA ORDER BY 1";
                       
            rs1 = st1.executeQuery(query);
            
            while (rs1.next())
            {
                DetalleVentaOperacion dtv = new DetalleVentaOperacion();
                dtv.setAbVenta((rs1.getString("AB_VENTA")));
                listaCombosAbVenta.add(dtv);
            }      
            st1.close();
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
            
        } catch (SQLException ex) {
        }
        return listaCombosAbVenta;
    }
    
    public List<DetalleVentaOperacion> obtenerComboTipoOperacion(){
      List<DetalleVentaOperacion> listaCombosTipoOperacion = new ArrayList<DetalleVentaOperacion>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            //Querys_C query = new Querys_C();            
            //query.generar_Consulta_Combo_Sistema();/*Filtrar asesores por coordinador*/
            String query = "SELECT TIPO_OPERACION FROM TBBO_OPERACIONES GROUP BY TIPO_OPERACION ORDER BY 1";
                       
            rs1 = st1.executeQuery(query);
            
            while (rs1.next())
            {
                DetalleVentaOperacion dtv = new DetalleVentaOperacion();
                dtv.setTipoOperacion((rs1.getString("TIPO_OPERACION")));
                listaCombosTipoOperacion.add(dtv);
            }      
            st1.close();
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
            
        } catch (SQLException ex) {
        }
        return listaCombosTipoOperacion;
    }
    
    public List<DetalleVentaOperacion> obtenerComboPeriodo(){
      List<DetalleVentaOperacion> listaCombosPeriodo = new ArrayList<DetalleVentaOperacion>();
        Connection conex=null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            //Querys_C query = new Querys_C();            
            //query.generar_Consulta_Combo_Sistema();/*Filtrar asesores por coordinador*/
            String query = "SELECT PERIODO FROM TBBO_OPERACIONES WHERE PERIODO IS NOT NULL GROUP BY PERIODO ORDER BY 1 DESC ";
            
            //String query = "SELECT '112016' PERIODO FROM DUAL ";
                       
            rs1 = st1.executeQuery(query);
            
            while (rs1.next())
            {
                DetalleVentaOperacion dtv = new DetalleVentaOperacion();
                dtv.setPeriodo((rs1.getString("PERIODO")));
                listaCombosPeriodo.add(dtv);
            }      
        }catch(Exception e){ 
            
        }
        try {
            conex.close();
            
        } catch (SQLException ex) {
        }
        return listaCombosPeriodo;
    }
    
      public String retornarDecimal(float numero){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return df.format(numero);        
    }
    
      
        public void XLSProceso(Object document) {
        ExcelConfigRepOperacion config = new ExcelConfigRepOperacion();
        boolean estadoSeleccionAsesor=false;
        String nombreAsesor=null;
        String asesorSeleccionado = this.getNombreAsesorSelected();
        if(asesorSeleccionado!=null){
            nombreAsesor = asesorSeleccionado;
            estadoSeleccionAsesor=true;
        }
                
        config.configurarExcelOperacion(document, estadoSeleccionAsesor, nombreAsesor);
    
    }
      
        
}
