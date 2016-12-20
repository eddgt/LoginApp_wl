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
public class TipoAsesor {
    private String idTipoAsesor;
    private String nombreTipoAsesor;

    public TipoAsesor()
    {
        
    }
    public String getIdTipoAsesor() {
        return idTipoAsesor;
    }

    public void setIdTipoAsesor(String idTipoAsesor) {
        this.idTipoAsesor = idTipoAsesor;
    }

    public String getNombreTipoAsesor() {
        return nombreTipoAsesor;
    }

    public void setNombreTipoAsesor(String nombreTipoAsesor) {
        this.nombreTipoAsesor = nombreTipoAsesor;
    }
    
    
}
