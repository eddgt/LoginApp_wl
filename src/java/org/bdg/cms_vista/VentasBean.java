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
import java.text.SimpleDateFormat;
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
import org.bdg.cms_dto.Venta;
import org.bdg.session.BaseSession;
import org.bdg.utils.JsfUtil;

/**
 *
 * @author morellana
 */
@ManagedBean(name = "ventasBean")
@ViewScoped
public class VentasBean extends BaseSession {

    private List<Venta> listaVenta = new ArrayList<Venta>();
    private Venta venta;
    @PostConstruct
    public void init() {
        try {
            venta= new Venta();
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
            venta.setCod_venta(myRequest.getParameter("cod_venta"));
            venta.setTipo_venta(myRequest.getParameter("tipo_venta"));
            venta.setNombre_venta(myRequest.getParameter("nombre_venta"));
            venta.setMonto_venta(myRequest.getParameter("monto_venta"));
            venta.setCant_rgu_venta(myRequest.getParameter("cant_rgu_venta"));
            venta.setProducto(myRequest.getParameter("producto"));
            venta.setActivo(myRequest.getParameter("activo"));
            venta.setCategoria(myRequest.getParameter("categoria"));
            venta.setMoneda_venta(myRequest.getParameter("moneda_venta"));
            

        } catch (Exception e) {
        }
    }

    public List<Venta> getListaVenta() {
        Venta v;
        ResultSet rs1 = null;
        Connection conex = null;
        try {
            Conexion conec = new Conexion();
            conex = conec.getConexion2();
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM TBBO_VENTAS_PARAM");
            query.append(" order by cod_venta");

            Statement st1 = conex.createStatement();
            rs1 = st1.executeQuery(query.toString());

            while (rs1.next()) {
                v = new Venta();
                v.setCod_venta(rs1.getString("cod_venta"));
                v.setTipo_venta(rs1.getString("tipo_venta"));
                v.setNombre_venta(rs1.getString("nombre_venta"));
                v.setMonto_venta(rs1.getString("monto_venta"));
                v.setCant_rgu_venta(rs1.getString("cant_rgu_venta"));
                v.setProducto(rs1.getString("producto"));
                v.setActivo(rs1.getString("activo"));
                v.setCategoria(rs1.getString("categoria"));
                v.setMoneda_venta(rs1.getString("moneda_venta"));
                
                this.listaVenta.add(v);
            }
        } catch (SQLException e) {

        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        
        return listaVenta;
    }

    public void setListaVenta(List<Venta> listaVenta) {
        this.listaVenta = listaVenta;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
     public void redireccionarNuevo() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/AgregarVenta.xhtml");
        } catch (Exception e) {
        }

    }

    public void cancelarOperacion() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/Ventas.xhtml");
        } catch (Exception e) {
        }
    }

    public String urlEditar() {
        return "EditarVenta.xhtml";
    }
    
    public void crearVenta()
    {
         try {

            StringBuilder queryCrear = new StringBuilder();
            queryCrear.append("INSERT INTO TBBO_VENTAS_PARAM");
            queryCrear.append("(tipo_venta,nombre_venta,monto_venta,moneda_venta,cant_rgu_venta,categoria,producto,activo)");
            queryCrear.append("VALUES (");
            queryCrear.append("'").append(this.venta.getTipo_venta()).append("'");
            queryCrear.append(",'").append(this.venta.getNombre_venta()).append("'");
            queryCrear.append(",'").append(this.venta.getMonto_venta()).append("'");
            queryCrear.append(",'").append(this.venta.getMoneda_venta()).append("'");
            queryCrear.append(",'").append(this.venta.getCant_rgu_venta()).append("'");
            queryCrear.append(",'").append(this.venta.getCategoria()).append("'");
            queryCrear.append(",'").append(this.venta.getProducto()).append("'");
            queryCrear.append(",'").append(this.venta.getActivo()).append("'");            
            queryCrear.append(")");

            Conexion conec = new Conexion();
            Connection conex = conec.getConexion2();
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            rs1 = st1.executeQuery(queryCrear.toString());
            conex.close();
            JsfUtil.addSuccessMessage(Constantes.SS_AGREGAROK);

        } catch (SQLException e) {
            JsfUtil.addErrorMessage(Constantes.SS_AGREGARERROR);
        }
        
    }
    public void editarVenta()
    {
         try {
            StringBuilder queryCrear = new StringBuilder();
            queryCrear.append("UPDATE TBBO_VENTAS_PARAM");            
            queryCrear.append(" SET");
            queryCrear.append(" tipo_venta='").append(this.venta.getTipo_venta()).append("'");
            queryCrear.append(",nombre_venta='").append(this.venta.getNombre_venta()).append("'");
            queryCrear.append(",monto_venta='").append(this.venta.getMonto_venta()).append("'");
            queryCrear.append(",moneda_venta='").append(this.venta.getMoneda_venta()).append("'");
            queryCrear.append(",cant_rgu_venta=").append(this.venta.getCant_rgu_venta());
            queryCrear.append(",categoria='").append(this.venta.getCategoria()).append("'");
            queryCrear.append(",producto='").append(this.venta.getProducto()).append("'");
            queryCrear.append(",activo='").append(this.venta.getActivo()).append("'");                        

            Conexion conec = new Conexion();
            Connection conex = conec.getConexion2();
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            rs1 = st1.executeQuery(queryCrear.toString());
            conex.close();
            JsfUtil.addSuccessMessage(Constantes.SS_AGREGAROK);

        } catch (SQLException e) {
            JsfUtil.addErrorMessage(Constantes.SS_AGREGARERROR);
        }
        
    }
    public void eliminarVenta(String codVenta)
    {
         try {
            StringBuilder queryCrear = new StringBuilder();
            queryCrear.append("DELETE TBBO_VENTAS_PARAM");
            queryCrear.append(" WHERE");
            queryCrear.append(" COD_VENTA=").append(codVenta);
            Conexion conec = new Conexion();
            Connection conex = conec.getConexion2();
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            rs1 = st1.executeQuery(queryCrear.toString());
            conex.close();
            JsfUtil.addSuccessMessage(Constantes.SS_ELIMINAROK);
        } catch (Exception e) {
            JsfUtil.addSuccessMessage(Constantes.SS_ELIMINARERROR);
        }
    }
    
    
}
