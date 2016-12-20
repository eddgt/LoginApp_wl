/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_dto;

/**
 *
 * @author oulloa
 */
public class DetalleVentaOperacion {    
private int id;
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
private String fecha;
private String fechaAnulacion;
private String fuente;
private String gerente;
private String idProductoAs400;
private int mesesContrato;
private String modelo;
private String monedaVenta;
private String montoVenta;
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


public DetalleVentaOperacion(int aneIns, String categoria, String cliente) {
        this.aneIns=aneIns;
        this.categoria=categoria;
        this.cliente=cliente;
    }


    /*This empty propertie is necesary for use by Bean constructor*/
  public DetalleVentaOperacion(){    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setProductoGlobal(String productoGlobal) {
        this.productoGlobal = productoGlobal;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(String fechaAnulacion) {
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

    public String getMontoVenta() {
        return montoVenta;
    }

    public void setMontoVenta(String montoVenta) {
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

    
}
