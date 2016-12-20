/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_dto;

/**
 *
 * @author Admin_bdgsa
 */
public class Movimiento {
    private String fechaCambio;
    private String justificacion;
    private int idVentaBitacora;
    private String usuario;

    public String getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(String fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public int getIdVentaBitacora() {
        return idVentaBitacora;
    }

    public void setIdVentaBitacora(int idVentaBitacora) {
        this.idVentaBitacora = idVentaBitacora;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
    
}
