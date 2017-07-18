package org.bdg.cms_vista;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.bdg.cms_buc.Query_C;
import org.bdg.cms_conexion.Conexion;
import org.bdg.cms_dto.DetalleBitacoraOperacion;

/**
 *
 * @author oulloa
 */
@ManagedBean(name = "bitacoraBean")
@ViewScoped
public class BitacoraOperaciones implements Serializable{

    String id;
    String periodo;
    String fechaInicio;
    String fechaFin;
    String usuario;
    String estado;
    private List<DetalleBitacoraOperacion> listaDetalleBitacora;
    
    public BitacoraOperaciones(){}

    public List<DetalleBitacoraOperacion> getListaDetalleBitacora() {
        List<DetalleBitacoraOperacion> listaBitacora = new ArrayList<DetalleBitacoraOperacion>();
        Connection conex = null;
        try{                
            Conexion conec = new Conexion();                
            conex = conec.getConexion2();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Query_C query = new Query_C();
            query.generarConsultaBitacoraOperacion();
            String ql = query.getConsulta_BitacoraOperacion();
            
            rs1 = st1.executeQuery(ql);
            int contador=0;
            while (rs1.next())
            {                
                DetalleBitacoraOperacion detalle = new DetalleBitacoraOperacion();
                detalle.setId((rs1.getString("ID")));
                detalle.setPeriodo((rs1.getString("PERIODO")));
                detalle.setFechaInicio((rs1.getString("FECHA_INICIO")));
                detalle.setFechaFin((rs1.getString("FECHA_FINAL")));
                detalle.setUsuario((rs1.getString("USUARIO")));
                detalle.setEstado((rs1.getString("ESTADO")));
                
                listaBitacora.add(detalle);
                this.listaDetalleBitacora = listaBitacora;
            }
        }catch(Exception e){
            String erss = e.toString();
            int i =0;
        }
        try {
            conex.close();
            System.out.println("Cerrando conexion... ");
        } catch (SQLException ex) {
            String ers = ex.toString();
        }
        return listaDetalleBitacora;
    }

    public void setListaDetalleBitacora(List<DetalleBitacoraOperacion> listaDetalleBitacora) {
        this.listaDetalleBitacora = listaDetalleBitacora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
