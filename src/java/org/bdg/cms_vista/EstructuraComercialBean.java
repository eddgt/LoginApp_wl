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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.bdg.cms_dto.EstructuraComercial;
import org.bdg.cms_dto.Persona;
import org.bdg.cms_dto.TipoAsesor;
import org.bdg.session.BaseSession;
import org.bdg.utils.JsfUtil;

/**
 *
 * @author morellana
 */
@ManagedBean(name = "estructuraComercialBean")
@ViewScoped
public class EstructuraComercialBean extends BaseSession {

    private List<EstructuraComercial> listaEstructura = new ArrayList<EstructuraComercial>();
    private EstructuraComercial estCom;
    private String correlativo;
    private String persona;
    private String gerente;
    private String supervisor;
    private String coordinador;
    private String tipoAsesorSel;
    private String codas400;
    private String usuarioNav;
    private Date fechdesde;
    private Date fechasta;

    private TipoAsesor tipoAsesor;

    private List<Persona> listaPersonasSupervisor = new ArrayList<Persona>();
    private List<Persona> listaPersonasGerentes = new ArrayList<Persona>();
    private List<Persona> listaPersonasCoordinador = new ArrayList<Persona>();
    private List<Persona> listaPersonasTodo = new ArrayList<Persona>();
    private List<TipoAsesor> listaTipoAsesor = new ArrayList<TipoAsesor>();

    @PostConstruct
    public void init() {
        try {
            
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();            
            this.setCorrelativo(myRequest.getParameter("idCorrelativo"));
            this.setTipoAsesorSel(myRequest.getParameter("idTipoAsesor"));
            this.setPersona(myRequest.getParameter("idPersona"));
            this.setCodas400(myRequest.getParameter("codAs400"));
            this.setUsuarioNav(myRequest.getParameter("usurioNav"));
            SimpleDateFormat fechaGeneralFormato = new SimpleDateFormat("dd/MM/yyyy");
            this.setFechdesde(fechaGeneralFormato.parse(myRequest.getParameter("fecdesde")));
            this.setFechasta(fechaGeneralFormato.parse(myRequest.getParameter("fechasta")));
            this.setSupervisor(myRequest.getParameter("idSupervisor"));
            this.setGerente(myRequest.getParameter("idTeamManager"));
            this.setCoordinador(myRequest.getParameter("idCoordinador"));

        } catch (Exception e) {
        }
    }

    public List<EstructuraComercial> getListaEstructura() {
        EstructuraComercial est;
        ResultSet rs1 = null;
        Connection conex = null;
        try {
            Conexion conec = new Conexion();
            conex = conec.getConexion2();
            StringBuilder query = new StringBuilder();
            query.append("select e.idpersona, p.nompersona, sup.nompersona as nomsupervisor,man.nompersona as nommanager,coor.nompersona as nomcoordinador, E.IDTIPASESOR,A.NOMTIPASESOR, E.FECDESDE, E.FECHASTA, E.IDESTCOMDIARIA AS idCorrelativo ,e.COD_AS400,e.USUARIONAV,e.IDSUPERVISOR,e.IDTEAMANAGER,e.IDCOORDINADOR");
            query.append(" from COMISIONREG.TABCOESTCOMDIARIA e");
            query.append(" INNER JOIN COMISIONREG.TABCOPERSONA p");
            query.append(" on ( p.idpersona = e.idpersona)");
            query.append(" INNER JOIN COMISIONREG.TABCOTIPASESOR A");
            query.append(" ON (E.IDTIPASESOR = A.IDTIPASESOR AND A.obstipasesor in ('EJECUTIVO DE VENTAS DE CORPORATIVO','EJECUTIVO DE VENTAS DEL CORPORATIVO') AND A.idestado = 1 )  ");
            query.append(" INNER JOIN COMISIONREG.TABCOPERSONA sup on ( sup.idpersona = e.IDSUPERVISOR)");
            query.append(" INNER JOIN COMISIONREG.TABCOPERSONA man on ( man.idpersona = e.IDTEAMANAGER)");
            query.append(" INNER JOIN COMISIONREG.TABCOPERSONA coor on ( coor.idpersona = e.IDCOORDINADOR)");
            query.append(" where e.idtipasesor in (select idtipasesor from comisionreg.tabcotipasesor where obstipasesor in ('EJECUTIVO DE VENTAS DE CORPORATIVO','EJECUTIVO DE VENTAS DEL CORPORATIVO') and idestado = 1)");
            query.append(" and sysdate between E.fecdesde and E.fechasta");
            query.append(" order by p.nompersona");
            
            Statement st1 = conex.createStatement();
            rs1 = st1.executeQuery(query.toString());

            while (rs1.next()) {
                est = new EstructuraComercial();
                est.setCorrelativo(rs1.getString("idCorrelativo"));
                est.setIdTipoAsesor(rs1.getString("IDTIPASESOR"));
                est.setNombreTipoAsesor(rs1.getString("NOMTIPASESOR"));
                est.setIdPersona(rs1.getString("idpersona"));
                est.setNombrePersona(rs1.getString("nompersona"));
                est.setCodAs400(rs1.getString("COD_AS400"));
                est.setUsurioNav(rs1.getString("USUARIONAV"));
                SimpleDateFormat fechaGeneralFormato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                //rs1.getString("FECDESDE") = (java.lang.String) "2016-06-01 00:00:00.0"
                Date fechaInicial = fechaGeneralFormato.parse(rs1.getString("FECDESDE"));
                Date fechaFinal = (fechaGeneralFormato.parse(rs1.getString("FECHASTA")));
                est.setFecdesde(fechaInicial);
                est.setFechasta(fechaFinal);
                est.setIdSupervisor(rs1.getString("IDSUPERVISOR"));
                est.setNombreSupervisor(rs1.getString("nomsupervisor"));
                est.setIdTeamManager(rs1.getString("IDTEAMANAGER"));
                est.setNombreManager(rs1.getString("nommanager"));
                est.setIdCoordinador(rs1.getString("IDCOORDINADOR"));
                est.setNombreCoordinador(rs1.getString("nomcoordinador"));
                this.listaEstructura.add(est);
            }
        } catch (SQLException e) {

        } catch (ParseException px) {
            System.err.println(px.getMessage());
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }

        return listaEstructura;
    }

    public void redireccionarNuevo() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/AgregarEstructuraComercial.xhtml");
        } catch (Exception e) {
        }

    }

    public void cancelarOperacion() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/EstructuraComercial.xhtml");
        } catch (Exception e) {
        }
    }

    public void agregarEstructura() {
        try {
            SimpleDateFormat fechaGeneralFormato = new SimpleDateFormat("dd/MM/yyyy");
            String fechaInicial = (fechaGeneralFormato.format(this.fechdesde));
            String fechaFinal = (fechaGeneralFormato.format(this.fechasta));

            StringBuilder queryCrear = new StringBuilder();
            queryCrear.append("INSERT INTO COMISIONREG.TABCOESTCOMDIARIA");
            queryCrear.append("(IDESTCOMDIARIA, IDPERSONA, IDALIADO, IDCANAL, IDREGION, IDCARGO, IDCODPOS, IDCODSUBPOS, IDTIPASESOR, IDEJECUTIVO, IDSUPERVISOR, IDDIRECTOR, IDRESCANAL, IDRESNACIONAL, IDTEAMANAGER, IDSUBTEAM, IDCAPACITADOR, IDCOORDINADOR, IDDIRCOMERCIAL,  FECDESDE, FECHASTA,COD_AS400,USUARIONAV,IDPROREGISTRO)");
            queryCrear.append("VALUES ( (SELECT MAX(IDESTCOMDIARIA) + 1 FROM COMISIONREG.TABCOESTCOMDIARIA)");
            queryCrear.append(",").append(this.persona);
            queryCrear.append(",0");
            queryCrear.append(",1");
            queryCrear.append(",0");
            queryCrear.append(",0");
            queryCrear.append(",0");
            queryCrear.append(",0");
            queryCrear.append(",'").append(this.tipoAsesorSel).append("'");
            queryCrear.append(",0");
            queryCrear.append(",'").append(this.supervisor).append("'");
            queryCrear.append(",0");
            queryCrear.append(",1252");
            queryCrear.append(",0");
            queryCrear.append(",'").append(this.gerente).append("'");
            queryCrear.append(",0");
            queryCrear.append(",0");
            queryCrear.append(",'").append(this.coordinador).append("'");
            queryCrear.append(",0");
            queryCrear.append(",'").append(fechaInicial).append("'");
            queryCrear.append(",'").append(fechaFinal).append("'");
            queryCrear.append(",'").append(this.codas400).append("'");
            queryCrear.append(",'").append(this.usuarioNav).append("'");
            queryCrear.append(",2013");
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

    public void setListaEstructura(List<EstructuraComercial> listaEstructura) {
        this.listaEstructura = listaEstructura;
    }

    public EstructuraComercial getEstCom() {
        return estCom;
    }

    public void setEstCom(EstructuraComercial estCom) {
        this.estCom = estCom;
    }

    public String urlEditar() {
        return "EditarEstructuraComercial.xhtml";
    }

    public String concatenar(String val1, String separador, String val2) {
        return val1 + separador + val2;
    }

    public List<TipoAsesor> obtenerListadeTipoAsesor() {        
        Connection conex = null;
        try {
            Conexion conec = new Conexion();
            conex = conec.getConexion2();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            StringBuilder query = new StringBuilder();
            query.append("SELECT TIPASE.idtipasesor, TIPASE.NOMTIPASESOR");
            query.append(" FROM comisionreg.tabcotipasesor TIPASE");
            query.append(" WHERE TIPASE.obstipasesor in ('EJECUTIVO DE VENTAS DE CORPORATIVO','EJECUTIVO DE VENTAS DEL CORPORATIVO')");
            query.append(" and TIPASE.idestado = 1");
            query.append(" order by nomtipasesor");
            rs1 = st1.executeQuery(query.toString());

            while (rs1.next()) {
                TipoAsesor tipoases = new TipoAsesor();
                tipoases.setNombreTipoAsesor((rs1.getString("NOMTIPASESOR")));
                tipoases.setIdTipoAsesor(rs1.getString("idtipasesor"));
                listaTipoAsesor.add(tipoases);
            }

        } catch (Exception e) {
        } finally {
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }
        return listaTipoAsesor;
    }    
    public List<Persona> obtenerPersonaSupervisor() {
        Persona per;
        ResultSet rs1 = null;
        Connection conex = null;
        try {
            Conexion conec = new Conexion();
            conex = conec.getConexion2();

            StringBuilder query = new StringBuilder();
            query.append("Select distinct per.idpersona,per.nompersona,t.IDSUPERVISOR");
            query.append(" From (");
            query.append(" select p.idpersona AS COD_CMS,e.IDSUPERVISOR,e.IDTEAMANAGER,e.IDCOORDINADOR");
            query.append(" from COMISIONREG.TABCOESTCOMDIARIA e");
            query.append(" INNER JOIN COMISIONREG.TABCOPERSONA p");
            query.append(" on ( p.idpersona = e.idpersona)");
            query.append(" where e.idtipasesor in (select idtipasesor from comisionreg.tabcotipasesor where obstipasesor in ('EJECUTIVO DE VENTAS DE CORPORATIVO','EJECUTIVO DE VENTAS DEL CORPORATIVO')");
            query.append(" and idestado = 1)");
            query.append(" and sysdate between E.fecdesde and E.fechasta");
            query.append(" order by nompersona");
            query.append(" )t");
            query.append(" INNER JOIN COMISIONREG.TABCOPERSONA per on (t.IDSUPERVISOR=per.idpersona)  ");
            query.append("  WHERE per.idestado=1");

            Statement st1 = conex.createStatement();
            rs1 = st1.executeQuery(query.toString());
            while (rs1.next()) {
                per = new Persona();
                per.setNompersona(rs1.getString("nompersona"));                
                per.setIdPersona(rs1.getString("idpersona"));
                listaPersonasSupervisor.add(per);
            }

        } catch (Exception e) {

        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }

       return listaPersonasSupervisor;
    }
    
    public List<Persona> obtenerPersonaGerentes() {
        Persona per;
        ResultSet rs1 = null;
        Connection conex = null;
        try {
            Conexion conec = new Conexion();
            conex = conec.getConexion2();

            StringBuilder query = new StringBuilder();
            query.append("Select distinct per.idpersona,per.nompersona,t.IDTEAMANAGER");
            query.append(" From (");
            query.append(" select p.idpersona AS COD_CMS,e.IDSUPERVISOR,e.IDTEAMANAGER,e.IDCOORDINADOR");
            query.append(" from COMISIONREG.TABCOESTCOMDIARIA e");
            query.append(" INNER JOIN COMISIONREG.TABCOPERSONA p");
            query.append(" on ( p.idpersona = e.idpersona)");
            query.append(" where e.idtipasesor in (select idtipasesor from comisionreg.tabcotipasesor where obstipasesor in ('EJECUTIVO DE VENTAS DE CORPORATIVO','EJECUTIVO DE VENTAS DEL CORPORATIVO')");
            query.append(" and idestado = 1)");
            query.append(" and sysdate between E.fecdesde and E.fechasta");
            query.append(" order by nompersona");
            query.append(" )t");
            query.append(" INNER JOIN COMISIONREG.TABCOPERSONA per on (t.IDTEAMANAGER=per.idpersona)  ");
            query.append("  WHERE per.idestado=1");

            Statement st1 = conex.createStatement();
            rs1 = st1.executeQuery(query.toString());
            while (rs1.next()) {
                per = new Persona();
                per.setNompersona(rs1.getString("nompersona"));                
                per.setIdPersona(rs1.getString("idpersona"));
                listaPersonasGerentes.add(per);
            }

        } catch (Exception e) {

        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }

       return listaPersonasGerentes;
    }
    
    
     public List<Persona> obtenerPersonaCoordinador() {
        Persona per;
        ResultSet rs1 = null;
        Connection conex = null;
        try {
            Conexion conec = new Conexion();
            conex = conec.getConexion2();

            StringBuilder query = new StringBuilder();
            query.append("Select distinct per.idpersona,per.nompersona,t.IDCOORDINADOR");
            query.append(" From (");
            query.append(" select p.idpersona AS COD_CMS,e.IDSUPERVISOR,e.IDTEAMANAGER,e.IDCOORDINADOR");
            query.append(" from COMISIONREG.TABCOESTCOMDIARIA e");
            query.append(" INNER JOIN COMISIONREG.TABCOPERSONA p");
            query.append(" on ( p.idpersona = e.idpersona)");
            query.append(" where e.idtipasesor in (select idtipasesor from comisionreg.tabcotipasesor where obstipasesor in ('EJECUTIVO DE VENTAS DE CORPORATIVO','EJECUTIVO DE VENTAS DEL CORPORATIVO')");
            query.append(" and idestado = 1)");
            query.append(" and sysdate between E.fecdesde and E.fechasta");
            query.append(" order by nompersona");
            query.append(" )t");
            query.append(" INNER JOIN COMISIONREG.TABCOPERSONA per on (t.IDCOORDINADOR=per.idpersona)  ");
            query.append("  WHERE per.idestado=1");

            Statement st1 = conex.createStatement();
            rs1 = st1.executeQuery(query.toString());
            while (rs1.next()) {
                per = new Persona();
                per.setNompersona(rs1.getString("nompersona"));                
                per.setIdPersona(rs1.getString("idpersona"));
                listaPersonasCoordinador.add(per);
            }

        } catch (Exception e) {

        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }

       return listaPersonasCoordinador;
    }

    /**
     * Retorna todos los registros de la tabla TABCOPERSONA cuando el estado = 1
     *
     * @return
     */
    public List<Persona> getListaPersonasTodo() {
        PersonasBean per = new PersonasBean();
        listaPersonasTodo = per.getListaPersonasTodo();
        return listaPersonasTodo;
    }

    public void setListaPersonasTodo(List<Persona> listaPersonasTodo) {
        this.listaPersonasTodo = listaPersonasTodo;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getGerente() {
        return gerente;
    }

    public void setGerente(String gerente) {
        this.gerente = gerente;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(String coordinador) {
        this.coordinador = coordinador;
    }

    public String getTipoAsesorSel() {
        return tipoAsesorSel;
    }

    public void setTipoAsesorSel(String tipoAsesorSel) {
        this.tipoAsesorSel = tipoAsesorSel;
    }

    public String getCodas400() {
        return codas400;
    }

    public void setCodas400(String codas400) {
        this.codas400 = codas400;
    }

    public String getUsuarioNav() {
        return usuarioNav;
    }

    public void setUsuarioNav(String usuarioNav) {
        this.usuarioNav = usuarioNav;
    }

    public Date getFechdesde() {
        return fechdesde;
    }

    public void setFechdesde(Date fechdesde) {
        this.fechdesde = fechdesde;
    }

    public Date getFechasta() {
        return fechasta;
    }

    public void setFechasta(Date fechasta) {
        this.fechasta = fechasta;
    }

    public TipoAsesor getTipoAsesor() {
        return tipoAsesor;
    }

    public void setTipoAsesor(TipoAsesor tipoAsesor) {
        this.tipoAsesor = tipoAsesor;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public void editarEstructuraComercial() {
        try {
            SimpleDateFormat fechaGeneralFormato = new SimpleDateFormat("dd/MM/yyyy");
            String fechaInicial = (fechaGeneralFormato.format(this.fechdesde));
            String fechaFinal = (fechaGeneralFormato.format(this.fechasta));

            StringBuilder queryCrear = new StringBuilder();
            queryCrear.append("UPDATE COMISIONREG.TABCOESTCOMDIARIA");
            queryCrear.append(" SET IDPERSONA ='").append(this.persona).append("'");
            queryCrear.append(",IDTIPASESOR ='").append(this.tipoAsesorSel).append("'");
            queryCrear.append(",IDSUPERVISOR ='").append(this.supervisor).append("'");
            queryCrear.append(",IDTEAMANAGER='").append(this.gerente).append("'");
            queryCrear.append(",IDCOORDINADOR='").append(this.coordinador).append("'");
            queryCrear.append(",FECDESDE='").append(fechaInicial).append("'");
            queryCrear.append(",FECHASTA='").append(fechaFinal).append("'");
            queryCrear.append(",COD_AS400='").append(this.codas400).append("'");
            queryCrear.append(",USUARIONAV='").append(this.usuarioNav).append("'");
            queryCrear.append(" where IDESTCOMDIARIA=").append(this.correlativo);

            Conexion conec = new Conexion();
            Connection conex = conec.getConexion2();
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            rs1 = st1.executeQuery(queryCrear.toString());
            conex.close();
            JsfUtil.addSuccessMessage(Constantes.SS_ACTUALIZAROK);

        } catch (SQLException e) {
            JsfUtil.addErrorMessage(Constantes.SS_ACTUALIZARERROR);
        }

    }

    public String formatoFecha(Date fecha) {
        SimpleDateFormat fechaGeneralFormato = new SimpleDateFormat("dd/MM/yyyy");
        return (fechaGeneralFormato.format(fecha));
    }

    public List<Persona> getListaPersonasSupervisor() {
        listaPersonasSupervisor=obtenerPersonaSupervisor();
        return listaPersonasSupervisor;
    }

    public void setListaPersonasSupervisor(List<Persona> listaPersonasSupervisor) {
        this.listaPersonasSupervisor = listaPersonasSupervisor;
    }

    public List<Persona> getListaPersonasGerentes() {
        listaPersonasGerentes=obtenerPersonaGerentes();
        return listaPersonasGerentes;
    }

    public void setListaPersonasGerentes(List<Persona> listaPersonasGerentes) {
        this.listaPersonasGerentes = listaPersonasGerentes;
    }

    public List<Persona> getListaPersonasCoordinador() 
    {
        listaPersonasCoordinador= obtenerPersonaCoordinador();
        
        return listaPersonasCoordinador;
    }

    public void setListaPersonasCoordinador(List<Persona> listaPersonasCoordinador) {
        this.listaPersonasCoordinador = listaPersonasCoordinador;
    }

    public List<TipoAsesor> getListaTipoAsesor() {
        listaTipoAsesor=obtenerListadeTipoAsesor();
        return listaTipoAsesor;
    }

    public void setListaTipoAsesor(List<TipoAsesor> listaTipoAsesor) {
        this.listaTipoAsesor = listaTipoAsesor;
    }
    
    

}
