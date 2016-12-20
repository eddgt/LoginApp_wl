/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_vista;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.bdg.cms_dto.Asesor;
import org.bdg.cms_dto.Coordinador;
import org.bdg.cms_dto.DetalleVenta;
import org.bdg.cms_conexion.Conexion;
import org.bdg.cms_buc.Querys_C;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import oracle.jdbc.driver.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;
import org.bdg.base.Constantes;
import javax.faces.event.ActionEvent;
import org.bdg.session.BaseSession;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.faces.context.ExternalContext;
import org.bdg.utils.JsfUtil;

/**
 *
 * @author Daniel Mendez
 */
@ManagedBean(name = "ventasAsignarBean")
@ViewScoped
public class VentasAsignarBean extends BaseSession {
   
    
    ////////////////////////////////////////////////////////////////////
    private List<DetalleVenta> listaVentas;
    private List<DetalleVenta> ventasSeleccionadas;
    private List<Coordinador> listaCoordinador;
    private List<Asesor> listaAsesor;
    private Coordinador coordinadorSelected;
    private Asesor asesorSelected;
    private int idCoordinador;
    private String nombreCoordinadorSelected;
    private int idAsesorSelelected;
    private String noAnexo;
    private String noTelefono;
    private String noInstalacion;
    private String NomCliente;
    private String CodCliente;
    private String justificacion;
    private boolean blnActivoOneSelected;

    public boolean isBlnActivoOneSelected() {
        return blnActivoOneSelected;
    }

    public void setBlnActivoOneSelected(boolean blnActivoOneSelected) {
        this.blnActivoOneSelected = blnActivoOneSelected;
    }
    
    

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getNomCliente() {
        return NomCliente;
    }

    public void setNomCliente(String NomCliente) {
        this.NomCliente = NomCliente;
    }

    public String getCodCliente() {
        return CodCliente;
    }

    public void setCodCliente(String CodCliente) {
        this.CodCliente = CodCliente;
    }

    public String getNoAnexo() {
        return noAnexo;
    }

    public void setNoAnexo(String noAnexo) {
        this.noAnexo = noAnexo;
    }

    public String getNoTelefono() {
        return noTelefono;
    }

    public void setNoTelefono(String noTelefono) {
        this.noTelefono = noTelefono;
    }

    public String getNoInstalacion() {
        return noInstalacion;
    }

    public void setNoInstalacion(String noInstalacion) {
        this.noInstalacion = noInstalacion;
    }
    
    

    public int getIdAsesorSelelected() {
        return idAsesorSelelected;
    }

    public void setIdAsesorSelelected(int idAsesorSelelected) {
        this.idAsesorSelelected = idAsesorSelelected;
    }
    
    
    
    public String getNombreCoordinadorSelected() {
        return nombreCoordinadorSelected;
    }

    public void setNombreCoordinadorSelected(String nombreCoordinadorSelected) {
        this.nombreCoordinadorSelected = nombreCoordinadorSelected;
    }
    
    
    
    public int getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(int idCoordinador) {
        this.idCoordinador = idCoordinador;
    }
    
    
    public Asesor getAsesorSelected() {
        return asesorSelected;
    }

    public void setAsesorSelected(Asesor asesorSelected) {
        this.asesorSelected = asesorSelected;
    }

    public Coordinador getCoordinadorSelected() {
        return coordinadorSelected;
    }

    public void setCoordinadorSelected(Coordinador coordinadorSelected) {
        this.coordinadorSelected = coordinadorSelected;
    }
        //listaMunicipio = abc.listMunici
    public VentasAsignarBean() {
                
    }
    
    public void oneCoordinadorChange() {        
        String nombreCoordinadorFiltro = this.getNombreCoordinadorSelected();
        this.listaAsesor = this.obtenerListadeAsesor(nombreCoordinadorFiltro);
        //listaMunicipio = abc.listMunicipiosByDepto(departamentoSelected);
    }
    
    public void oneAsesorChange(){
        int idAsesorSeleccionado = this.getIdAsesorSelelected();
        //this.listaVentas = this.getVentasCoordinador(idAsesorSeleccionado);          
    }
    
     public String retornarFecha(String fecha){
        if(!fecha.equals("")){
             try{
                String[] partesFecha = fecha.split(" ");
                if(partesFecha.length >0){
                      String[] creandoFormato = partesFecha[0].split("-");
                      String nuevaFecha = creandoFormato[2] +"/"+creandoFormato[1]+"/"+creandoFormato[0];
                      fecha = nuevaFecha;
                }                              
            }catch(Exception e){
                fecha="";
            }                       
        } 
        return fecha;
    }
     
     
    @PostConstruct
    public void init() {           
        coordinadorSelected = new Coordinador();
        asesorSelected = new Asesor();       
        ////////////////////////// 
        //this.setNombreCoordinadorSelected(this.getAtributoSession(Constantes.SS_CoordinadorAsignar));
        String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
        String rolUsuarioLogueado = this.getAtributoSession(Constantes.SS_ROL);
        listaCoordinador = this.obtenerListadeCoordinador(); 
        
        if(usuarioLogueado!=null && rolUsuarioLogueado!=null){
            
             if(rolUsuarioLogueado.equals("COORD")){
                for(int i =0; i< listaCoordinador.size(); i++){
                    String usuarioDom = listaCoordinador.get(i).getUsuario_dominio();
                    if(usuarioDom!=null){
                        if(usuarioDom.equals(usuarioLogueado.toUpperCase())){
                            this.setBlnActivoOneSelected(true);                       
                            this.setNombreCoordinadorSelected(listaCoordinador.get(i).getNombre());
                            this.listaAsesor = this.obtenerListadeAsesor(listaCoordinador.get(i).getNombre());
                            break;
                        }
                    }
                  
                }
            }                        
        }else{
           try{
              ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
              context.redirect(context.getRequestContextPath() + "/");
           }catch(Exception e){

           }
        }        
        //listaCoordinador = this.obtenerListadeCoordinador();       
    }
    
    
    public void btnClickBuscarVentas(ActionEvent actionEvent) {       
       this.listaVentas = this.getVentas();
    }
    
     
    public void btnClickAsignarVentas(ActionEvent actionEvent) {       
        int RegistroSeleccionados = 0;
        String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
        String Observ = this.getJustificacion();
        Conexion conec = new Conexion();                
        Connection conex = conec.getConexion();
        RegistroSeleccionados = this.ventasSeleccionadas.size();
        
        if (Observ.equals("") == false){
            if (Observ.length()>0 && Observ.length()<= 150){
                for(int i=0; i< this.ventasSeleccionadas.size(); i++){
                    DetalleVenta ventaSeleccionada = this.ventasSeleccionadas.get(i);                     
                    /////////////////           
                    try{                
                         JsfUtil.addSuccessMessage(Constantes.MSG_SUCCESS_ASIGNA_VENTA);//mensaje success
                        if(this.getIdAsesorSelelected()>0){
                            Querys_C query = new Querys_C();
                            query.generar_Consulta_AsignarAsesorVenta(ventaSeleccionada.getIdVenta(),this.idAsesorSelelected, usuarioLogueado);
                            String queryAsignar = query.getAsignarAsesor_Venta();
                            CallableStatement cstmt = conex.prepareCall(queryAsignar);
                            cstmt.executeQuery();  

                            String Idventa = Integer.toString(ventaSeleccionada.getIdVenta());
                            query.generar_InsertaBitacoraJustificacion(Idventa, Observ, usuarioLogueado, "VN");
                            String InsertBitacora = query.getConsulta_InsertaBitacoraJustificacion();
                            CallableStatement bitacora = conex.prepareCall(InsertBitacora);
                            bitacora.executeQuery();  

                        }

                    }catch(Exception e){

                    }
                }
                try {
                        if(RegistroSeleccionados == 0){
                            JsfUtil.addSuccessMessage(Constantes.MSG_ERROR_SELECCIONVENTA);
                        }else{
                                if(this.getIdAsesorSelelected() <= 0){
                                    JsfUtil.addSuccessMessage(Constantes.MSG_ERROR_ASIGNAVENTA);
                                }
                                else{
                                    this.setJustificacion("");
                                    this.listaVentas = this.getVentas();            
                                }
                            }
                        conex.close();
                    } catch (SQLException ex) {
                        }
            }else{
                JsfUtil.addSuccessMessage(Constantes.MSG_ERROR_ASIGNA_TAMANIO);
            }            
        }else{
            JsfUtil.addSuccessMessage(Constantes.MSG_ERROR_ASIGNA);

        }      
    }
            
    
    public void btnClickDesasignarVentas(ActionEvent actionEvent) {
        Conexion conec = new Conexion();                
        Connection conex = conec.getConexion();
        for(int i=0; i< this.ventasSeleccionadas.size(); i++){
            DetalleVenta ventaSeleccioanda = this.ventasSeleccionadas.get(i);                     
            /////////////////           
            try{                
                Querys_C query = new Querys_C();
                query.generar_Consulta_CambioEstado(ventaSeleccioanda.getIdVenta());
                CallableStatement cstmt = conex.prepareCall(query.getModificar_Estado());
                cstmt.executeQuery();                                
                                              
            }catch(Exception e){
                
            }    
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        int idAsesorSeleccionado = this.getIdAsesorSelelected();
       // this.listaVentas = this.getVentasCoordinador(idAsesorSelelected);
    }
    
    
    public void btnClickRegresar(ActionEvent actionEvent){
        try{
          
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/ventasCoordinador.xhtml");           
        }catch(Exception e){
        
        }        
    }
     
    public List<Coordinador> obtenerListadeCoordinador(){
        List<Coordinador> listaCoordinador = new ArrayList<Coordinador>();
        Connection conex = null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            
            Querys_C query = new Querys_C();
            query.generar_Consulta_Coordinador_An();
                       
            rs1 = st1.executeQuery(query.getConsulta_CoordinadorAntigua());
            
            while (rs1.next())
            {
                Coordinador coor = new Coordinador();
                coor.setNombre((rs1.getString("NOMBRE")));
                coor.setIdCoordinador(Integer.parseInt(rs1.getString("ID")));
                coor.setUsuario_dominio(rs1.getString("USUARIO_DOMINIO"));
                listaCoordinador.add(coor);
            }            
        }catch(Exception e){ 
            
        }finally{
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        return listaCoordinador;
    }
    
    public List<Asesor> obtenerListadeAsesor(String nombreCoordinador){        
        List<Asesor> listaAsesor = new ArrayList<Asesor>();
        Connection conex = null;
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            Querys_C query = new Querys_C();  
            query.generar_Consulta_Vendedores(nombreCoordinador, this.getAtributoSession(Constantes.SS_ROL).equals("COORD"));
            rs1 = st1.executeQuery(query.getConsulta_Vendedores());
            
            while (rs1.next())
            {
                Asesor ases = new Asesor();
                ases.setNombre((rs1.getString("NOM_VENDEDOR")));
                ases.setIdAsesor(Integer.parseInt(rs1.getString("CODVEND")));
                listaAsesor.add(ases);
            }
            
        }catch(Exception e){        
        }finally{
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }    
        return listaAsesor;        
    }
     
    
    
    public List<DetalleVenta> getVentas(){
        List<DetalleVenta> listaVentas = new ArrayList<DetalleVenta>();
        Connection conex = null;
        try{                
            Conexion conec = new Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            Statement st1 = conex.createStatement();
            Statement st2 = conex.createStatement();
            
            String codFiltroCliente = this.getCodCliente();
            String nomFiltroCliente = this.getNomCliente();
            String noFiltroTelefono = this.getNoTelefono();
            String noFiltroNexo = this.getNoAnexo();
            String noFiltroInstalacion = this.getNoInstalacion();
            
            if(codFiltroCliente.equals("")){
                codFiltroCliente=null;
            }

            if(nomFiltroCliente.equals("")){
                nomFiltroCliente=null;
            }

            if(noFiltroTelefono.equals("")){
                noFiltroTelefono=null;
            }           

            if(noFiltroNexo.equals("")){
                noFiltroNexo=null;
            }
            
            if(noFiltroInstalacion.equals("")){
                noFiltroInstalacion=null;
            }
            
            Querys_C query = new Querys_C();
             
            query.generar_Consulta_VentasXAsignar(codFiltroCliente, nomFiltroCliente, noFiltroTelefono, noFiltroNexo , noFiltroInstalacion);
            query.generarConsultaVerifiqueSiExisteAsignacion(noFiltroTelefono, noFiltroNexo, noFiltroInstalacion);
            String consultaXAsignar = query.getConsulta_VentasXAsignar();
            String consultaSiEstaAsignada = query.getConsulta_VentasSiEstaAsignada();
            int contadorAsignar=0;
            int contadorEstaAsignada=0;                                                                        
            rs1 = st1.executeQuery(consultaXAsignar);
            
           
            while (rs1.next())
            {
                contadorAsignar++;
                DetalleVenta detalle = new DetalleVenta();
                detalle.setNombreCliente((rs1.getString("nom_cliente")));
                detalle.setFecha(this.retornarFecha(rs1.getString("FECHA")));                 
                String noInstalacion = rs1.getString(("NO_INSTALACION"));
                if(noInstalacion!=null){
                    detalle.setNoInstalacion((rs1.getString(("NO_INSTALACION"))));
                }else{
                    detalle.setNoInstalacion((""));
                }                
                detalle.setTelefono(rs1.getString("NUMTELEFONO"));
                detalle.setAnexoNum(rs1.getString(("ANEXO_NUM")));
                detalle.setTipoProd(rs1.getString("TIPO_PRODUCTO"));
                detalle.setTipoTransaccion(rs1.getString("TIPO_TRANSACCION"));
                detalle.setPlazo((rs1.getString(("PLAZO"))));
                detalle.setMoneda(rs1.getString(("MONEDA")));
                detalle.setCuotaBasica((rs1.getString("CUOTA_BASICA")));
                detalle.setValorEnlace((rs1.getString("VALOR_ENLACE")));
                detalle.setIdVenta((Integer.parseInt(rs1.getString("IDVENTA"))));
                listaVentas.add(detalle);
            }
            
            String coordinadorAsignado="";
            String veendedorAsignado="";
            if(contadorAsignar==0){
                if(!consultaSiEstaAsignada.equals("")){
                    rs2 = st2.executeQuery(consultaSiEstaAsignada);
                    while (rs2.next())
                    {
                        coordinadorAsignado=rs2.getString("COORDINADOR_TB_CONFIRMADO");
                        veendedorAsignado=rs2.getString("NOM_VENDEDOR_AS400");                        
                        contadorEstaAsignada++;
                        break;
                    }                  
                }   
            }else{
                JsfUtil.addSuccessMessage("Busqueda realizada con éxito.");
            }
            
            if(contadorEstaAsignada>0){
                String rolUsuarioLogueado = this.getAtributoSession(Constantes.SS_ROL);
                if(rolUsuarioLogueado.equals(Constantes.ROL_ADMIN)  || rolUsuarioLogueado.equals(Constantes.ROL_PLANIFICADOR)){
                     JsfUtil.addSuccessMessage("La venta se encuentra asignada al coordinador: "
                             + coordinadorAsignado+ " y el vendedor: "+veendedorAsignado
                     );
                }else{
                     JsfUtil.addSuccessMessage("La venta ya se encuentra asignada a un asesor.");
                }
               
            }
            
            if(contadorAsignar==0 && contadorEstaAsignada==0){
                JsfUtil.addSuccessMessage("La venta no existe, verifique su busqueda.");
            }
            
        }catch(Exception e){
       
        }finally{
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
                        
        return listaVentas;
    }
    
    public void onRowSelect(SelectEvent event) {
      
    }
 
    public void onRowUnselect(UnselectEvent event) {
       
    }
    
    
        public List<Coordinador> getListaCoordinador() {
        return listaCoordinador;
    }

    public void setListaCoordinador(List<Coordinador> listaCoordinador) {
        this.listaCoordinador = listaCoordinador;
    }

    public List<Asesor> getListaAsesor() {
        return listaAsesor;
    }

    public void setListaAsesor(List<Asesor> listaAsesor) {
        this.listaAsesor = listaAsesor;
    }
    
    public List<DetalleVenta> getVentasSeleccionadas() {
        return ventasSeleccionadas;
    }

    public void setVentasSeleccionadas(List<DetalleVenta> ventasSeleccionadas) {
        this.ventasSeleccionadas = ventasSeleccionadas;
    }
    
    
    public List<DetalleVenta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<DetalleVenta> listaVentas) {
        this.listaVentas = listaVentas;
    }
     
    
    
}
