package org.bdg.cms_vista;

/**
 *
 * @author oulloa
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class CheckboxView {
         
    private String[] selectedReports;   
    private List<String> reports;
     
    @PostConstruct
    public void init() {
        reports = new ArrayList<String>();
        reports.add("Reporte Movil");
        reports.add("Reporte de E1");
        reports.add("Reporte de Renovaciones Enlace Fijo");
        reports.add("Reporte de Fijos");
        reports.add("Reporte de Ventas en Agencias");
        reports.add("Reporte de Ventas de Soporte Junior");
        reports.add("Reporte Ventas Nuevas");
        reports.add("Reporte Upgrades y Migraciones");
        
    }
 
    public String[] getSelectedReports() {
        return selectedReports;
    }
 
    public void setSelectedReports(String[] selectedReports) {
        this.selectedReports = selectedReports;
    }
 
    public List<String> getReports() {
        return reports;
    }
}