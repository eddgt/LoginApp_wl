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
public class Querys_Vendedor {
    
    public static String obtenerInformacionAsesor(String usuario, String nombre){
        String prepare ="";
        
        if(usuario!=null){
            prepare = " UPPER(ASESOR.OBSPERSONA) = UPPER('" + usuario + "') ";
        }else{
            prepare = " UPPER(ASESOR.NOMPERSONA) = UPPER('" + nombre + "') ";
        }
        
        return "Select ti.idestcomdiaria, ASESOR.NUMDOCUMENTO, ASESOR.NOMPERSONA, ASESOR.OBSPERSONA USUARIO , ti.fecdesde"
                +",ti.fechasta, ti.IDTIPASESOR" 
                +",ti.idcoordinador, COOR.NomPersona NOM_COORDINADOR"
                +",ti.idsupervisor, ti.idteamanager, ti.idcanal" 
                +",GERENTE.NOMPERSONA AS NOM_GERENTE"
                + " From COMISIONREG.TABCOESTCOMDIARIA ti"
                + " LEFT JOIN COMISIONREG.TABCOPERSONA ASESOR  On(ASESOR.IDPERSONA = TI.IDPERSONA)"
                + " LEFT JOIN COMISIONREG.TABCOPERSONA COOR  On(COOR.IDPERSONA = ti.IDCOORDINADOR)"
                + " LEFT JOIN COMISIONREG.TABCOPERSONA GERENTE ON (ti.IDSUPERVISOR = GERENTE.IDPERSONA)"
                + " Where ti.idproregistro Not In( 20114, 20173, 20100 )"
                + " AND  ADD_MONTHS (SYSDATE, -1) BETWEEN TI.FECDESDE AND TI.FECHASTA"
                + " AND ("
                + prepare
                + "  )  "
                + " ORDER BY NOM_GERENTE, NOM_COORDINADOR, NOMPERSONA";
    }
}
