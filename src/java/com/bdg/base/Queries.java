package com.bdg.base;

/**
 *
 * @author oulloa
 */

import com.bdg.base.Constantes;
import com.bdg.utils.JsfUtil;

public class Queries {
    public String qryUsuariosNaf;
    public String qryUsuariosNavega;
    public String qryUsuariosAmsys;
    public String qryUsuariosTest;
    public String qryUsuariosApp;

    public String getQryUsuariosNaf() {
        return qryUsuariosNaf;
    }

    public void setQryUsuariosNaf(String qryUsuariosNaf) {
        this.qryUsuariosNaf = qryUsuariosNaf;
    }

    public String getQryUsuariosNavega() {
        return qryUsuariosNavega;
    }

    public void setQryUsuariosNavega(String qryUsuariosNavega) {
        this.qryUsuariosNavega = qryUsuariosNavega;
    }

    public String getQryUsuariosAmsys() {
        return qryUsuariosAmsys;
    }

    public void setQryUsuariosAmsys(String qryUsuariosAmsys) {
        this.qryUsuariosAmsys = qryUsuariosAmsys;
    }

    public String getQryUsuariosTest() {
        return qryUsuariosTest;
    }

    public void setQryUsuariosTest(String qryUsuariosTest) {
        this.qryUsuariosTest = qryUsuariosTest;
    }

    public String getQryUsuariosApp() {
        return qryUsuariosApp;
    }

    public void setQryUsuariosApp(String qryUsuariosApp) {
        this.qryUsuariosApp = qryUsuariosApp;
    }
    
    public String consultaUsuariosApp(){
        String consulta = new StringBuilder()
                .append("SELECT A.ID, A.USERNAME , A.ESTADO, C.NOMBRE PERFIL, A.FECHA_BAJA FROM TB_USUARIOS A ")
                .append("INNER JOIN TB_PERFILES_USUARIO B ON A.USERNAME=B.USERNAME ")
                .append("INNER JOIN TB_PERFILES C ON C.ID_PERFIL=B.ID_PERFIL ")
                .append("WHERE A.ESTADO=1 AND A.FECHA_BAJA>SYSDATE " ).toString();
        return consulta;
    }    
    
}
