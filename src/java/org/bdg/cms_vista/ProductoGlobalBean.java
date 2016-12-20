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
import org.bdg.cms_dto.ProductoGlobal;
import org.bdg.session.BaseSession;
import org.bdg.utils.JsfUtil;

/**
 *
 * @author morellana
 */
@ManagedBean(name = "productoGlobalBean")
@ViewScoped
public class ProductoGlobalBean extends BaseSession {

    private List<ProductoGlobal> listaProductoGlobal = new ArrayList<ProductoGlobal>();
    ProductoGlobal producto = new ProductoGlobal();

    @PostConstruct
    public void init() {
        try {
            producto = new ProductoGlobal();
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
            producto.setProducto_global(myRequest.getParameter("producto_global"));
            producto.setProducto_tb(myRequest.getParameter("producto_tb"));
            producto.setProductoglobalOriginal(myRequest.getParameter("producto_global"));
            producto.setProductotbOriginal(myRequest.getParameter("producto_tb"));

        } catch (Exception e) {
        }

    }

    public List<ProductoGlobal> getListaProductoGlobal() {

        ProductoGlobal prod;
        ResultSet rs1 = null;
        Connection conex = null;
        try {
            Conexion conec = new Conexion();
            conex = conec.getConexion();
            StringBuilder query = new StringBuilder();
            query.append("select * ");
            query.append(" from TBBO_PRODUCTO_GLOBAL pg");
            Statement st1 = conex.createStatement();
            rs1 = st1.executeQuery(query.toString());

            while (rs1.next()) {
                prod = new ProductoGlobal();
                prod.setProducto_global(rs1.getString("producto_global"));
                prod.setProducto_tb(rs1.getString("producto_tb"));
                this.listaProductoGlobal.add(prod);
            }
        } catch (SQLException e) {

        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }

        return listaProductoGlobal;
    }

    public void setListaProductoGlobal(List<ProductoGlobal> listaProductoGlobal) {
        this.listaProductoGlobal = listaProductoGlobal;
    }

    public void redireccionarNuevo() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/AgregarProductoGlobal.xhtml");
        } catch (Exception e) {
        }

    }

    public void cancelarOperacion() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/ProductoGlobal.xhtml");
        } catch (Exception e) {
        }
    }

    public String urlEditar() {
        return "EditarProductoGlobal.xhtml";
    }

    public void crearProducto() {
        try {

            StringBuilder queryCrear = new StringBuilder();
            queryCrear.append("INSERT INTO TBBO_PRODUCTO_GLOBAL");
            queryCrear.append("(PRODUCTO_TB,PRODUCTO_GLOBAL)");
            queryCrear.append("VALUES (");
            queryCrear.append("'").append(this.producto.getProducto_tb()).append("'");
            queryCrear.append(",'").append(this.producto.getProducto_global()).append("'");
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

    public void editarProducto() {
        try {
            StringBuilder queryCrear = new StringBuilder();
            queryCrear.append("UPDATE TBBO_PRODUCTO_GLOBAL");
            queryCrear.append(" SET ");
            queryCrear.append(" PRODUCTO_TB ='").append(this.producto.getProducto_tb()).append("'");
            queryCrear.append(",PRODUCTO_GLOBAL='").append(this.producto.getProducto_global()).append("'");
            queryCrear.append(" WHERE ");
            queryCrear.append("  PRODUCTO_TB ='").append(this.producto.getProductotbOriginal()).append("'");
            queryCrear.append("  AND PRODUCTO_GLOBAL='").append(this.producto.getProductoglobalOriginal()).append("'");          

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

    public void eliminarProducto(String productoGlobal,String productoTB) {
        try {
            StringBuilder queryCrear = new StringBuilder();
            queryCrear.append("DELETE TBBO_PRODUCTO_GLOBAL");
            queryCrear.append(" WHERE ");
            queryCrear.append("  PRODUCTO_TB ='").append(productoTB).append("'");
            queryCrear.append("  AND PRODUCTO_GLOBAL='").append(productoGlobal).append("'");
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

    public ProductoGlobal getProducto() {
        return producto;
    }

    public void setProducto(ProductoGlobal producto) {
        this.producto = producto;
    }

}
