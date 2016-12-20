/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_vista;

/**
 *
 * @author BDGSA
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.bdg.cms_dto.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.bdg.base.Constantes;
import org.bdg.cms_conexion.Conexion;
import org.bdg.session.BaseSession;
import org.bdg.utils.JsfUtil;

@ManagedBean(name = "personasBean")
@ViewScoped
public class PersonasBean extends BaseSession {

    private List<Persona> personas;
    private List<Persona> listaPersonasTodo;
    private String nombre;
    private String documento;
    private String usuario;
    private String idPersona;

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @PostConstruct
    public void init() {

        this.setPersonas(getPersonas());
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession mySession = myRequest.getSession();
        this.idPersona = myRequest.getParameter("idPersona");
        this.nombre = myRequest.getParameter("nombre");
        this.documento = myRequest.getParameter("documento");
        this.usuario = myRequest.getParameter("usuarioDominio");       

    }

    public List<Persona> getPersonas() {
        Persona per;
        this.personas = new ArrayList<Persona>();
        ResultSet rs1 = null;
        Connection conex = null;
        try {
            Conexion conec = new Conexion();
            conex = conec.getConexion();

            StringBuilder query = new StringBuilder();
            query.append("select p.idpersona AS COD_CMS, p.numdocumento as Cod_WEBSOX, p.nompersona, p.idestado, p.obspersona");
            query.append(" from COMISIONREG.TABCOESTCOMDIARIA e");
            query.append(" INNER JOIN COMISIONREG.TABCOPERSONA p");
            query.append(" on ( p.idpersona = e.idpersona)");
            query.append(" where e.idtipasesor in (select idtipasesor from comisionreg.tabcotipasesor where obstipasesor in ('EJECUTIVO DE VENTAS DE CORPORATIVO','EJECUTIVO DE VENTAS DEL CORPORATIVO')");
            query.append(" and idestado = 1)");
            query.append(" and sysdate between E.fecdesde and E.fechasta");
            query.append(" order by nompersona");
            Statement st1 = conex.createStatement();
            rs1 = st1.executeQuery(query.toString());
            while (rs1.next()) {
                per = new Persona();
                per.setNompersona(rs1.getString("nompersona"));
                per.setIdnumdocumento(rs1.getString("Cod_WEBSOX"));
                per.setObspersona(rs1.getString("Obspersona"));
                per.setIdPersona(rs1.getString("COD_CMS"));
                this.personas.add(per);
            }

        } catch (Exception e) {

        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return personas;
    }

    public List<Persona> getListaPersonasTodo() {
         Persona per;
        this.listaPersonasTodo = new ArrayList<Persona>();
        ResultSet rs1 = null;
        Connection conex = null;
        try {
            Conexion conec = new Conexion();
            conex = conec.getConexion();

            StringBuilder query = new StringBuilder();
            query.append("select p.idpersona AS COD_CMS, p.nompersona, p.idestado");
            query.append(" from COMISIONREG.TABCOPERSONA p");            
            query.append(" where idestado = 1");            
            query.append(" order by nompersona");
            Statement st1 = conex.createStatement();
            rs1 = st1.executeQuery(query.toString());
            while (rs1.next()) {
                per = new Persona();
                per.setNompersona(rs1.getString("nompersona"));                                
                per.setIdPersona(rs1.getString("COD_CMS"));
                this.listaPersonasTodo.add(per);
            }

        } catch (Exception e) {

        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        
        return listaPersonasTodo;
    }

    public void setListaPersonasTodo(List<Persona> listaPersonasTodo) {
        this.listaPersonasTodo = listaPersonasTodo;
    }

  
    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public void btnAgregar_click() {
        try {

            StringBuilder queryCrear = new StringBuilder();
            queryCrear.append("INSERT INTO COMISIONREG.TABCOPERSONA");
            queryCrear.append("(IDPERSONA, NOMPERSONA, IDESTADO, IDCLAREFERENCIA, IDTIPREFERENCIA, IDTIPASESOR, IDTIPDOCUMENTO, NUMDOCUMENTO, OBSPERSONA, IDPROREGISTRO)");
            queryCrear.append("VALUES ((SELECT MAX(IDPERSONA)+1 FROM COMISIONREG.TABCOPERSONA)");
            queryCrear.append(",'" + this.nombre.toString() + "'");
            queryCrear.append(",1");
            queryCrear.append(",0");
            queryCrear.append(",0");
            queryCrear.append(",1");
            queryCrear.append(",3");
            queryCrear.append(",'" + this.documento.toString() + "'");
            queryCrear.append(",'" + this.usuario.toString() + "'");
            queryCrear.append(",2007");
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

    public void editarPersona() {
        try {

            StringBuilder queryCrear = new StringBuilder();
            queryCrear.append("UPDATE COMISIONREG.TABCOPERSONA PER");
            queryCrear.append(" SET PER.NOMPERSONA = NVL('").append(this.nombre).append("', PER.NOMPERSONA)");
            queryCrear.append(", PER.NUMDOCUMENTO = NVL('").append(this.documento).append("',PER.NUMDOCUMENTO)");
            queryCrear.append(", OBSPERSONA = NVL('").append(this.usuario).append("',OBSPERSONA)");
            queryCrear.append(" WHERE  PER.IDPERSONA =").append(this.idPersona);            
            Conexion conec = new Conexion();
            Connection conex = conec.getConexion();
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            rs1 = st1.executeQuery(queryCrear.toString());          
            JsfUtil.addSuccessMessage(Constantes.SS_ACTUALIZAROK);
            conex.close();

        } catch (SQLException e) {
            JsfUtil.addErrorMessage(Constantes.SS_ACTUALIZARERROR);
        }

    }

    public void redireccionarNuevo() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/AgregarPersona.xhtml");
        } catch (Exception e) {
        }

    }

    public void cancelarOperacion() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/personas.xhtml");
        } catch (Exception e) {
        }
    }

    public String urlEditar() {

        return "EditarPersona.xhtml";
    }

}
