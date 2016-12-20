/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_vista;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.bdg.base.Constantes;
import org.bdg.cms_conexion.Conexion;
import org.bdg.cms_dto.CarteraManual;
import org.bdg.session.BaseSession;
import org.bdg.utils.JsfUtil;

/**
 *
 * @author morellana
 */
@ManagedBean(name = "carteraManualBean")
@ViewScoped
public class CarteraManualBean extends BaseSession {

    private List<CarteraManual> listaCarteraManual = new ArrayList<CarteraManual>();
    CarteraManual cartera;

    @PostConstruct
    public void init() {
        try {
            cartera = new CarteraManual();
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
            cartera.setId(myRequest.getParameter("id"));
            cartera.setAsesor_navega(myRequest.getParameter("asesor_navega"));
            cartera.setAsesor_en_AS400(myRequest.getParameter("asesor_en_AS400"));
            cartera.setGerente_nuevo_tb(myRequest.getParameter("gerente_nuevo_tb"));
            cartera.setCoordinador_nuevo_tb(myRequest.getParameter("coordinador_nuevo_tb"));
            cartera.setEjecutivo_nuevo_tb(myRequest.getParameter("ejecutivo_nuevo_tb"));
            cartera.setEstado(myRequest.getParameter("estado"));

        } catch (Exception e) {
        }

    }

    public List<CarteraManual> getListaCarteraManual() {

        CarteraManual cm;
        ResultSet rs1 = null;
        Connection conex = null;
        try {
            Conexion conec = new Conexion();
            conex = conec.getConexion();
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM TBBO_CARTERA_MANUAL");
            query.append(" order by id");

            Statement st1 = conex.createStatement();
            rs1 = st1.executeQuery(query.toString());

            while (rs1.next()) {
                cm = new CarteraManual();
                cm.setAsesor_en_AS400(rs1.getString("asesor_en_AS400"));
                cm.setAsesor_navega(rs1.getString("asesor_navega"));
                cm.setCoordinador_nuevo_tb(rs1.getString("coordinador_nuevo_tb"));
                cm.setEstado(rs1.getString("estado"));
                cm.setGerente_nuevo_tb(rs1.getString("gerente_nuevo_tb"));
                cm.setId(rs1.getString("id"));
                cm.setEjecutivo_nuevo_tb(rs1.getString("ejecutivo_nuevo_tb"));
                this.listaCarteraManual.add(cm);
            }
        } catch (SQLException e) {

        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaCarteraManual;
    }

    public void setListaCarteraManual(List<CarteraManual> listaCarteraManual) {
        this.listaCarteraManual = listaCarteraManual;
    }

    public void redireccionarNuevo() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/AgregarCarteraManual.xhtml");
        } catch (Exception e) {
        }

    }

    public void cancelarOperacion() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/CarteraManual.xhtml");
        } catch (Exception e) {
        }
    }

    public String urlEditar() {
        return "EditarCarteraManual.xhtml";
    }

    public void crearCarteraManual() {
        try {

            StringBuilder queryCrear = new StringBuilder();
            queryCrear.append("INSERT INTO TBBO_CARTERA_MANUAL");
            queryCrear.append("(ASESOR_NAVEGA,asesor_en_AS400,gerente_nuevo_tb,coordinador_nuevo_tb,ejecutivo_nuevo_tb,estado)");
            queryCrear.append("VALUES (");
            queryCrear.append("'").append(this.cartera.getAsesor_navega()).append("'");
            queryCrear.append(",'").append(this.cartera.getAsesor_en_AS400()).append("'");
            queryCrear.append(",'").append(this.cartera.getGerente_nuevo_tb()).append("'");
            queryCrear.append(",'").append(this.cartera.getCoordinador_nuevo_tb()).append("'");
            queryCrear.append(",'").append(this.cartera.getEjecutivo_nuevo_tb()).append("'");
            queryCrear.append(",'").append(this.cartera.getEstado()).append("'");
            queryCrear.append(")");

            Conexion conec = new Conexion();
            Connection conex = conec.getConexion();
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            rs1 = st1.executeQuery(queryCrear.toString());
            conex.close();
            JsfUtil.addSuccessMessage(Constantes.SS_AGREGAROK);

        } catch (SQLException e) {
            JsfUtil.addErrorMessage(Constantes.SS_AGREGARERROR);
        }
    }

    public void editarCarteraManual() {
        try {

            StringBuilder queryCrear = new StringBuilder();
            queryCrear.append("UPDATE TBBO_CARTERA_MANUAL");
            queryCrear.append(" SET");
            queryCrear.append(" ASESOR_NAVEGA='").append(this.cartera.getAsesor_navega()).append("'");
            queryCrear.append(",asesor_en_AS400='").append(this.cartera.getAsesor_en_AS400()).append("'");
            queryCrear.append(",gerente_nuevo_tb='").append(this.cartera.getGerente_nuevo_tb()).append("'");
            queryCrear.append(",coordinador_nuevo_tb='").append(this.cartera.getCoordinador_nuevo_tb()).append("'");
            queryCrear.append(",ejecutivo_nuevo_tb='").append(this.cartera.getEjecutivo_nuevo_tb()).append("'");
            queryCrear.append(",estado='").append(this.cartera.getEstado()).append("'");
            queryCrear.append(" WHERE id=").append(this.cartera.getId());

            Conexion conec = new Conexion();
            Connection conex = conec.getConexion();
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            rs1 = st1.executeQuery(queryCrear.toString());
            conex.close();
            JsfUtil.addSuccessMessage(Constantes.SS_ACTUALIZAROK);

        } catch (SQLException e) {
            JsfUtil.addErrorMessage(Constantes.SS_ACTUALIZARERROR);
        }

    }

    public void eliminarCarteraManual(String id) {
        try {
            StringBuilder queryCrear = new StringBuilder();
            queryCrear.append("DELETE TBBO_CARTERA_MANUAL");
            queryCrear.append(" WHERE");
            queryCrear.append(" ID=").append(id);

            Conexion conec = new Conexion();
            Connection conex = conec.getConexion();
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            rs1 = st1.executeQuery(queryCrear.toString());
            conex.close();
            JsfUtil.addSuccessMessage(Constantes.SS_ELIMINAROK);
        } catch (Exception e) {
            JsfUtil.addSuccessMessage(Constantes.SS_ELIMINARERROR);
        }

    }

    public CarteraManual getCartera() {
        return cartera;
    }

    public void setCartera(CarteraManual cartera) {
        this.cartera = cartera;
    }

}
