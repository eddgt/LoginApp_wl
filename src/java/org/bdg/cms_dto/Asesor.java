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
public class Asesor {
    private int idAsesor;
    private String nombre;

    public Asesor(int idAsesor, String nombre) {
        this.idAsesor = idAsesor;
        this.nombre = nombre;
    }

    public Asesor(){
    
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
