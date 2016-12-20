/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_dto;

/**
 *
 * @author Daniel Mendez
 */
public class DetalleVenta {
    private int idVenta;
    private int idCliente;
    private String NIT;
    private String nombreCliente;
    private String fecha;
    private String noInstalacion;
    private String telefono;
    private String anexoNum;
    private String tipoProd;
    private String tipoTransaccion;
    private String plazo;
    private String moneda;
    private String cuotaBasica;
    private String valorEnlace;
    private String origen;
    private String producto;
    private int idBitacora;
    private int idVenta_PosVenta;
    private String FechaCambio;
    private String Observacion;
    private String UsuarioModifico;
    private String TipoVenta;    

    public DetalleVenta(int idVenta, int idCliente, String NIT, String nombreCliente, String fecha, String noInstalacion, String telefono, String anexoNum, String tipoProd, String tipoTransaccion, String plazo, String moneda, String cuotaBasica, String valorEnlace, String origen, String producto, int idBitacora, int idVenta_PosVenta, String FechaCambio, String Observacion, String UsuarioModifico, String TipoVenta) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.NIT = NIT;
        this.nombreCliente = nombreCliente;
        this.fecha = fecha;
        this.noInstalacion = noInstalacion;
        this.telefono = telefono;
        this.anexoNum = anexoNum;
        this.tipoProd = tipoProd;
        this.tipoTransaccion = tipoTransaccion;
        this.plazo = plazo;
        this.moneda = moneda;
        this.cuotaBasica = cuotaBasica;
        this.valorEnlace = valorEnlace;
        this.origen = origen;
        this.producto = producto;
        this.idBitacora = idBitacora;
        this.idVenta_PosVenta = idVenta_PosVenta;
        this.FechaCambio = FechaCambio;
        this.Observacion = Observacion;
        this.UsuarioModifico = UsuarioModifico;
        this.TipoVenta = TipoVenta;	
        
    }
    
    public DetalleVenta(){    
    }
    
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

  

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoProd() {
        return tipoProd;
    }

    public void setTipoProd(String tipoProd) {
        this.tipoProd = tipoProd;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getNoInstalacion() {
        return noInstalacion;
    }

    public void setNoInstalacion(String noInstalacion) {
        this.noInstalacion = noInstalacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAnexoNum() {
        return anexoNum;
    }

    public void setAnexoNum(String anexoNum) {
        this.anexoNum = anexoNum;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getCuotaBasica() {
        return cuotaBasica;
    }

    public void setCuotaBasica(String cuotaBasica) {
        this.cuotaBasica = cuotaBasica;
    }

    public String getValorEnlace() {
        return valorEnlace;
    }

    public void setValorEnlace(String valorEnlace) {
        this.valorEnlace = valorEnlace;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
     
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }    
    public int getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(int idBitacora) {
        this.idBitacora = idBitacora;
    }

    public int getIdVenta_PosVenta() {
        return idVenta_PosVenta;
    }

    public void setIdVenta_PosVenta(int idVenta_PosVenta) {
        this.idVenta_PosVenta = idVenta_PosVenta;
    }

    public String getFechaCambio() {
        return FechaCambio;
    }

    public void setFechaCambio(String FechaCambio) {
        this.FechaCambio = FechaCambio;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public String getUsuarioModifico() {
        return UsuarioModifico;
    }

    public void setUsuarioModifico(String UsuarioModifico) {
        this.UsuarioModifico = UsuarioModifico;
    }

    public String getTipoVenta() {
        return TipoVenta;
    }

    public void setTipoVenta(String TipoVenta) {
        this.TipoVenta = TipoVenta;
    }

}
