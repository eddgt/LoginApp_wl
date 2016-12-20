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
public class Coordinador {
    private int idCoordinador;
    private String nombre;
    private String usuario_dominio;

    public String getUsuario_dominio() {
        return usuario_dominio;
    }

    public void setUsuario_dominio(String usuario_dominio) {
        this.usuario_dominio = usuario_dominio;
    }
    
    
     public Coordinador(int idCoordinador, String nombre) {
        this.idCoordinador = idCoordinador;
        this.nombre = nombre;
    }

    public Coordinador(){
    
    }

    public int getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(int idCoordinador) {
        this.idCoordinador = idCoordinador;
    }
     

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
