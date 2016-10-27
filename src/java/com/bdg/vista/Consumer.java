package com.bdg.vista;

import ws.Respuesta;
import ws.Service1;
import ws.Service1Soap;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author oulloa
 */

@javax.faces.bean.ManagedBean(name = "consumer")
@SessionScoped
public class Consumer {
    private String uname;
    private String upwd;
    private String usrws;
    private String pwdws;
    
    public Consumer() {       
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUsrws() {
        return usrws;
    }

    public void setUsrws(String usrws) {
        this.usrws = usrws;
    }

    public String getPwdws() {
        return pwdws;
    }

    public void setPwdws(String pwdws) {
        this.pwdws = pwdws;
    }
    
            
    public void call(ActionEvent actionEvent){
        
        try {
            
            ws.CambioClave cc = new ws.CambioClave();
            cc.setAppclave("pruebas");
            cc.setAppuser("pruebas");
            cc.setWsclave(upwd);
            cc.setWsuser(uname);
            
            ws.CambioClaveResponse ccr = new ws.CambioClaveResponse();
            ccr.getCambioClaveResult();
            
            Service1 ss = new Service1();
            ss.getPorts();
            Service1Soap service1Soap = ss.getService1Soap();
            service1Soap.cambioClave("pruebas", "pruebas", uname, upwd);            
       
            //ccr.getCambioClaveResult().toString();
            Respuesta r = new Respuesta();
            r.getMensaje();
            
            } 
        catch (Exception ex) {
	// TODO handle custom exceptions here
            System.out.println("ERROR: al tratar de aplicar el cambio " + ex.getMessage());
        }
        
    }
    
}
