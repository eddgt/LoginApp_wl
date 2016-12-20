/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_dto;

/**
 *
 * @author morellana
 */
public class Venta {
    
    private String tipo_venta;
    private String cod_venta;
    private String nombre_venta;
    private String monto_venta;
    private String moneda_venta;
    private String cant_rgu_venta;
    private String categoria;
    private String producto;    
    private String activo;

    public String getTipo_venta() {
        return tipo_venta;
    }

    public void setTipo_venta(String tipo_venta) {
        this.tipo_venta = tipo_venta;
    }

    public String getCod_venta() {
        return cod_venta;
    }

    public void setCod_venta(String cod_venta) {
        this.cod_venta = cod_venta;
    }

    public String getNombre_venta() {
        return nombre_venta;
    }

    public void setNombre_venta(String nombre_venta) {
        this.nombre_venta = nombre_venta;
    }

    public String getMonto_venta() {
        return monto_venta;
    }

    public void setMonto_venta(String monto_venta) {
        this.monto_venta = monto_venta;
    }

    public String getCant_rgu_venta() {
        return cant_rgu_venta;
    }

    public void setCant_rgu_venta(String cant_rgu_venta) {
        this.cant_rgu_venta = cant_rgu_venta;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getMoneda_venta() {
        return moneda_venta;
    }

    public void setMoneda_venta(String moneda_venta) {
        this.moneda_venta = moneda_venta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
}
