/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_dto;

import java.util.List;

/**
 *
 * @author oulloa
 */
public class DetalleBitacoraOperacion {
    
    String id;
    String periodo;
    String fechaInicio;
    String fechaFin;
    String usuario;
    String estado;  
    
    public DetalleBitacoraOperacion(){    
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public DetalleBitacoraOperacion(String id, String periodo, String fechaInicio, String fechaFin, String usuario, String estado) {
        this.id = id;
        this.periodo = periodo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuario = usuario;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "DetalleBitacoraOperacion{" + "id=" + id + ", periodo=" + periodo + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", usuario=" + usuario + ", estado=" + estado + '}';
    }
    
    
}
