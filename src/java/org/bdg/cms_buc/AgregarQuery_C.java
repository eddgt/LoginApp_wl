/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_buc;

import org.bdg.base.Constantes;
import org.bdg.utils.JsfUtil;

public class AgregarQuery_C {
    private String consulta_TipoVenta = "";

    public String getConsulta_TipoVenta() {
        return consulta_TipoVenta;
    }

    public void setConsulta_TipoVenta(String consulta_TipoVenta) {
        this.consulta_TipoVenta = consulta_TipoVenta;
    }
    
    public String generarQueryTipoVenta(){
        String query = "select TIPTEL.IDTIPTELEFONIA AS Cod_TELEFONIA, TIPTEL.NOMTIPTELEFONIA AS NOMTELEFONIA"
                + " from COMISIONREG.TABCOTIPTELEFONIA TIPTEL"
                + " where UPPER(TIPTEL.NOMTIPTELEFONIA) LIKE 'TB%'"
                + " AND TIPTEL.IDTIPTELEFONIA IN (26, 27)";                       
        return query;
    }
    
    public String generarQueryTipoVentaRenovacion(){
        String query = "select TIPTEL.IDTIPTELEFONIA AS Cod_TELEFONIA, TIPTEL.NOMTIPTELEFONIA AS NOMTELEFONIA"
                + " from COMISIONREG.TABCOTIPTELEFONIA TIPTEL"
                + " where UPPER(TIPTEL.NOMTIPTELEFONIA) LIKE 'TB%'"
                + " AND TIPTEL.IDTIPTELEFONIA IN (13,14)";                       
        return query;
    }
    
    
}
