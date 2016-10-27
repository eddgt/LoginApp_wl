package com.bdg.vista;

/**
 *
 * @author oulloa
 */
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.event.CloseEvent;
 
@ManagedBean (name = "dialog")
public class DialogConfirmView {
 
    public void confirmNavega() {
        //addMessage("Informacion", "Intentando hacer el cambio");
        
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}