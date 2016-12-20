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
public class DetalleBitacora {
    private int idBitacora;
    private int idVenta_PosVenta;
    private String FechaCambio;
    private String Observacion;
    private String UsuarioModifico;
    private String TipoVenta;


    public DetalleBitacora(int idBitacora, int idVenta_PosVenta, String FechaCambio, String Observacion, String UsuarioModifico, String TipoVenta) {
        this.idBitacora = idBitacora;
        this.idVenta_PosVenta = idVenta_PosVenta;
        this.FechaCambio = FechaCambio;
        this.Observacion = Observacion;
        this.UsuarioModifico = UsuarioModifico;
        this.TipoVenta = TipoVenta;	
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
