/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_buc;

/**
 *
 * @author Daniel Méndez
 */
public class Query_Editar {
    private String queryEditarMonto;
    private String queryEditarMontoOperacion;

    public static String getQueryEditarMonto() {
        return "{?=call ActualizaMonVen_Ren (?,?,?,?,?)}";
    }

    public void setQueryEditarMonto(String queryEditarMonto) {
        this.queryEditarMonto = queryEditarMonto;
    }   
    
    /*Editar Monto Operacion de Ventas*/
    public static String getQueryEditarMontoOperacion() {
        return "{?=call ActualizaMonVen_Oper(?,?)}";
    }

    public void setQueryEditarMontoOperacion(String queryEditarMontoOperacion) {
        this.queryEditarMontoOperacion = queryEditarMontoOperacion;
    }   
  
    
}
