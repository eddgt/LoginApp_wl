/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_dto;

import java.util.Date;

/**
 *
 * @author morellana
 */
public class EstructuraComercial {
    private String correlativo;
    private String idTipoAsesor;  
    private String nombreTipoAsesor;
    private String idPersona;
    private String nombrePersona;
    private String codAs400;
    private String usurioNav;
    private Date fecdesde;
    private Date fechasta;
    private String idSupervisor;
    private String nombreSupervisor;   
    private String idTeamManager;
    private String nombreManager;
    private String idCoordinador;
    private String nombreCoordinador;

    public EstructuraComercial()
    {
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }
    
     public String getNombreSupervisor() {
        return nombreSupervisor;
    }

    public void setNombreSupervisor(String nombreSupervisor) {
        this.nombreSupervisor = nombreSupervisor;
    }
    public String getNombreTipoAsesor() {
        return nombreTipoAsesor;
    }

    public void setNombreTipoAsesor(String nombreAsesor) {
        this.nombreTipoAsesor = nombreAsesor;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

   

    public String getNombreManager() {
        return nombreManager;
    }

    public void setNombreManager(String nombreManager) {
        this.nombreManager = nombreManager;
    }

    public String getNombreCoordinador() {
        return nombreCoordinador;
    }

    public void setNombreCoordinador(String nombreCoordinador) {
        this.nombreCoordinador = nombreCoordinador;
    }    
    
    public String getIdTipoAsesor() {
        return idTipoAsesor;
    }

    public void setIdTipoAsesor(String idTipoAsesor) {
        this.idTipoAsesor = idTipoAsesor;
    }  

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getCodAs400() {
        return codAs400;
    }

    public void setCodAs400(String codAs400) {
        this.codAs400 = codAs400;
    }

    public String getUsurioNav() {
        return usurioNav;
    }

    public void setUsurioNav(String usurioNav) {
        this.usurioNav = usurioNav;
    }

    public Date getFecdesde() {
        return fecdesde;
    }

    public void setFecdesde(Date fecdesde) {
        this.fecdesde = fecdesde;
    }

    public Date getFechasta() {
        return fechasta;
    }

    public void setFechasta(Date fechasta) {
        this.fechasta = fechasta;
    }
    public String getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(String idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    public String getIdTeamManager() {
        return idTeamManager;
    }

    public void setIdTeamManager(String idTeamManager) {
        this.idTeamManager = idTeamManager;
    }

    public String getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(String idCoordinador) {
        this.idCoordinador = idCoordinador;
    }
    
    
            
            
}
