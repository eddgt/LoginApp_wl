/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_buc;

/**
 *
 * @author Admin_bdgsa
 */
public class Querys_Bitacora {
    private String consultaVitacoraxVenta;
    private String consultaBitacorxRenovacion;
    private String consultaBitacoraComentarioxVenta;

    public String getConsultaVitacoraxVenta(int numero) {
        this.consultaVitacoraxVenta=
        "SELECT "+
        "NVL(BIT.IDVENTA_POSVENTA, 0) as IdVentaBitacora,"+
        "NVL(BIT.FECHACAMBIO, TO_DATE('01/01/1999','dd/mm/yyyy')) AS FECHACAMBIO,"+
        "NVL(BIT.OBSERVACION, '-') AS JUSTIFICACION,"+
        "NVL(BIT.USUARIOMODIFICO, '-') AS USUARIO,"+
        "CASE BIT.TIPOVENTA"+
        " WHEN 'VN' THEN 'VENTA NUEVA'"+
        " WHEN 'RE' THEN 'RENOVACIÓN'"+
        " ELSE '-' "+
        " END TipoVenta"+
        " FROM COMISIONGT.BITACORAOBSERVACIONESWEB  BIT"+ 
        " WHERE BIT.IDVENTA_POSVENTA =" +  Integer.toString(numero)+
        " AND BIT.TIPOVENTA = 'VN' ORDER BY BIT.FECHACAMBIO DESC";    
        return consultaVitacoraxVenta;
    }

    
    public String getConsultaBitacorxRenovacion(String numero) {
        this.consultaBitacorxRenovacion=
         "SELECT "+
        "NVL(BIT.IDVENTA_POSVENTA, 0) as IdVentaBitacora,"+
        "NVL(BIT.FECHACAMBIO, TO_DATE('01/01/1999','dd/mm/yyyy')) AS FECHACAMBIO,"+
        "NVL(BIT.OBSERVACION, '-') AS JUSTIFICACION,"+
        "NVL(BIT.USUARIOMODIFICO, '-') AS USUARIO,"+
        "CASE BIT.TIPOVENTA"+
        " WHEN 'VN' THEN 'VENTA NUEVA'"+
        " WHEN 'RE' THEN 'RENOVACIÓN'"+
        " ELSE '-' "+
        " END TipoVenta"+
        " FROM COMISIONGT.BITACORAOBSERVACIONESWEB  BIT"+ 
        " WHERE BIT.IDVENTA_POSVENTA =" + (numero)+
        " AND BIT.TIPOVENTA = 'RE' ORDER BY BIT.FECHACAMBIO DESC ";    
        return consultaBitacorxRenovacion;
    }
    
        public String getConsultaBitacoraComentarioxVenta(int numero) {
        this.consultaBitacoraComentarioxVenta=
         "SELECT  a.* FROM TBBO_OPERACIONES_OBSERVACIONES a"+ 
        " WHERE a.ID =" + (numero);           
        return consultaBitacoraComentarioxVenta;
    }
      
      
    public void setConsultaVitacoraxVenta(String consultaVitacoraxVenta) {
        this.consultaVitacoraxVenta = consultaVitacoraxVenta;
    }  

    public void setConsultaBitacorxRenovacion(String consultaBitacorxRenovacion) {
        this.consultaBitacorxRenovacion = consultaBitacorxRenovacion;
    }   

    public void setConsultaBitacoraComentarioxVenta(String consultaBitacoraComentarioxVenta) {
        this.consultaBitacoraComentarioxVenta = consultaBitacoraComentarioxVenta;
    }    
    
    
}
