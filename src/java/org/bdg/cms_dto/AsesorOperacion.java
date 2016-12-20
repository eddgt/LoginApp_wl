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
public class AsesorOperacion {
    private int idAsesor;
    private String nombre;
    private String usuario_dominio;

    public String getUsuario_dominio() {
        return usuario_dominio;
    }

    public void setUsuario_dominio(String usuario_dominio) {
        this.usuario_dominio = usuario_dominio;
    }
    
    

    public AsesorOperacion(int idAsesor, String nombre) {
        this.idAsesor = idAsesor;
        this.nombre = nombre;
    }

    public AsesorOperacion(){
    
    }
    
    public int getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(int idAsesor) {
        this.idAsesor = idAsesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
