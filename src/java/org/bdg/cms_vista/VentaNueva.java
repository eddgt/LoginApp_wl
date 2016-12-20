/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_vista;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import org.bdg.cms_dto.Asesor;
import org.bdg.cms_dto.Coordinador;
import org.bdg.cms_conexion.Conexion;
import org.bdg.cms_buc.Querys_C;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.bdg.base.Constantes;
import org.bdg.session.BaseSession;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.context.ExternalContext;
import org.bdg.utils.JsfUtil;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.bdg.cms_buc.AgregarQuery_C;
import org.bdg.cms_dto.AgenciaDistribuidor;
import org.bdg.cms_dto.TipoVenta;
import java.sql.Types;
import java.text.SimpleDateFormat;
import org.bdg.cms_dto.TipoPlan;
/**
 *
 * @author Daniel Mendez
 */
@ManagedBean(name = "ventasNuevas")
@ViewScoped
public class VentaNueva extends BaseSession {
    
    private Date fechaSelect = new Date();
    private Boolean blnActivoOrdenTrabajo=true;
    private Boolean blnActivoAnexo=true;
    private Boolean blnActivoTelefono=true;
    private Boolean blnActivoNoInstalacion=true;
    private Boolean blnActivoCodCliente =true;
    private Boolean blnActivoCodAS = true;
    private Boolean blnActivoTipoPlan =true;
    //Cambio en base a elegir un tipo de venta
    private Boolean blnAjusteFijo = false;
    private Boolean blnAjusteVenta = false;
    
    private String textoNoInstalacion;
    private String textoAnexo;
    private String textoTelefono;
    private String textoOrdenTrabajo;
    private String codAS="";
    private String codNavega="";
    private List<TipoVenta> listaTipoVenta;
    private List<TipoPlan> listaTipoPlan;
    private List<AgenciaDistribuidor> listaAgencia;
    
    ///
    private List<Asesor> listaAsesor;
    private List<Coordinador> listaCoordinador;
    private String nombreCoordinadorSelected;
    private int idAsesorSelelected;
    
    ///
    private Map<Integer, String> listaAgenciasMap;    
    private int idTipoVenta;
    private int idAgencia;  
    private String lblAgencia;
    private String txtMeses;
    private String txtMonto;
    private String txtcodigoPlan;
    private String txtDescripcionPlan;
    private String txtNitCliente;
    private String txtNombreCliente;
    private String txtDireccionCliente;
    private String txtCodigoAsesor;
    private String txtNombreAsesor;
    private String txtCodigoAgencia;
    private String txtDescripcionAgencia;
    private String idMoneda;
    private String idTipoPlan;
    private Date fechaPInicial;
    private Date fechaPFinal;
    private boolean blnActivoOneSelected=false;

    public boolean isBlnActivoOneSelected() {
        return blnActivoOneSelected;
    }

    public void setBlnActivoOneSelected(boolean blnActivoOneSelected) {
        this.blnActivoOneSelected = blnActivoOneSelected;
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

    
    
    public List<Asesor> getListaAsesor() {
        return listaAsesor;
    }

    public void setListaAsesor(List<Asesor> listaAsesor) {
        this.listaAsesor = listaAsesor;
    }

    public List<Coordinador> getListaCoordinador() {
        return listaCoordinador;
    }

    public void setListaCoordinador(List<Coordinador> listaCoordinador) {
        this.listaCoordinador = listaCoordinador;
    }

    
    
    public Boolean getBlnActivoTipoPlan() {
        return blnActivoTipoPlan;
    }

    public void setBlnActivoTipoPlan(Boolean blnActivoTipoPlan) {
        this.blnActivoTipoPlan = blnActivoTipoPlan;
    }
  
    
    public void oneCoordinadorChange() {        
        String nombreCoordinadorFiltro = this.getNombreCoordinadorSelected();
        if(nombreCoordinadorFiltro!=null){
            this.listaAsesor = this.obtenerListadeAsesor(nombreCoordinadorFiltro);           
        }
        //listaMunicipio = abc.listMunicipiosByDepto(departamentoSelected);
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
            String error = e.toString();
        }finally{
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }
        return listaAsesor;        
    }
    
     public void oneAsesorChange(){
        int idAsesorSeleccionado = this.getIdAsesorSelelected();
        
    }
     
     
    public List<Coordinador> obtenerListadeCoordinador(){
        List<Coordinador> listaCoordinador = new ArrayList<Coordinador>();
        Connection conex=null;
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
            
        }
        try {
            conex.close();
        } catch (SQLException ex) {
        }
        return listaCoordinador;
    }
    
    public void oneTipoChange() {    
        boolean blnMovil;
        this.listaTipoPlan.clear();
        
        if(this.idTipoVenta==26){
            this.blnActivoTipoPlan=false;            
            this.listaTipoPlan = Constantes.listaPlanMovil();
            //Movil           
            this.blnActivoAnexo=false;
            this.blnActivoTelefono=false;
            this.blnActivoCodAS=false;
            
            this.textoNoInstalacion="";
            this.textoOrdenTrabajo="";
            this.codNavega="";
            
            
            this.blnActivoNoInstalacion=true;           
            this.blnActivoOrdenTrabajo=true;
            this.blnActivoCodCliente=true;
        }
        
        if(this.idTipoVenta==27){
            // Fijo
            this.listaTipoPlan = Constantes.listaPlanFijo();
            this.blnActivoTipoPlan=false;
            this.blnActivoAnexo=true;
            this.blnActivoTelefono=true;
            this.blnActivoCodAS=true;
            
            this.textoAnexo="";
            this.textoTelefono="";
            this.codAS="";
            
            this.blnActivoNoInstalacion=false;           
            this.blnActivoOrdenTrabajo=false;
            this.blnActivoCodCliente=false;
            
            
        }
        //listaMunicipio = abc.listMunicipiosByDepto(departamentoSelected);
    }

    public List<TipoPlan> getListaTipoPlan() {
        return listaTipoPlan;
    }

    public void setListaTipoPlan(List<TipoPlan> listaTipoPlan) {
        this.listaTipoPlan = listaTipoPlan;
    }
    
    
    public Boolean getBlnAjusteFijo() {
        return blnAjusteFijo;
    }

    public void setBlnAjusteFijo(Boolean blnAjusteFijo) {
        this.blnAjusteFijo = blnAjusteFijo;
    }

    public Boolean getBlnAjusteVenta() {
        return blnAjusteVenta;
    }

    public void setBlnAjusteVenta(Boolean blnAjusteVenta) {
        this.blnAjusteVenta = blnAjusteVenta;
    }
                
    public Date getFechaPInicial() {
        return fechaPInicial;
    }

    public void setFechaPInicial(Date fechaPInicial) {
        this.fechaPInicial = fechaPInicial;
    }

    public Date getFechaPFinal() {
        return fechaPFinal;
    }

    public void setFechaPFinal(Date fechaPFinal) {
        this.fechaPFinal = fechaPFinal;
    }
    
    
    

    public Map<Integer, String> getListaAgenciasMap() {
        return listaAgenciasMap;
    }

    public void setListaAgenciasMap(Map<Integer, String> listaAgenciasMap) {
        this.listaAgenciasMap = listaAgenciasMap;
    }

    
    public String getLblAgencia() {
        return lblAgencia;
    }

    public void setLblAgencia(String lblAgencia) {
        this.lblAgencia = lblAgencia;
    }
        
    
    
    public int getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(int idAgencia) {
        this.idAgencia = idAgencia;
    }

    
    public List<AgenciaDistribuidor> getListaAgencia() {
        return listaAgencia;
    }

    public void setListaAgencia(List<AgenciaDistribuidor> listaAgencia) {
        this.listaAgencia = listaAgencia;
    }
   

    public Boolean getBlnActivoTelefono() {
        return blnActivoTelefono;
    }

    public void setBlnActivoTelefono(Boolean blnActivoTelefono) {
        this.blnActivoTelefono = blnActivoTelefono;
    }

    public Boolean getBlnActivoNoInstalacion() {
        return blnActivoNoInstalacion;
    }

    public void setBlnActivoNoInstalacion(Boolean blnActivoNoInstalacion) {
        this.blnActivoNoInstalacion = blnActivoNoInstalacion;
    }

    
    
    public String getIdTipoPlan() {
        return idTipoPlan;
    }

    public void setIdTipoPlan(String idTipoPlan) {
        this.idTipoPlan = idTipoPlan;
    }
    
    
    
    public String getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }
    
    
    public String getTxtDescripcionPlan() {
        return txtDescripcionPlan;
    }

    public void setTxtDescripcionPlan(String txtDescripcionPlan) {
        this.txtDescripcionPlan = txtDescripcionPlan;
    }

    public String getTxtNitCliente() {
        return txtNitCliente;
    }

    public void setTxtNitCliente(String txtNitCliente) {
        this.txtNitCliente = txtNitCliente;
    }

    public String getTxtNombreCliente() {
        return txtNombreCliente;
    }

    public void setTxtNombreCliente(String txtNombreCliente) {
        this.txtNombreCliente = txtNombreCliente;
    }

    public String getTxtDireccionCliente() {
        return txtDireccionCliente;
    }

    public void setTxtDireccionCliente(String txtDireccionCliente) {
        this.txtDireccionCliente = txtDireccionCliente;
    }

    public String getTxtCodigoAsesor() {
        return txtCodigoAsesor;
    }

    public void setTxtCodigoAsesor(String txtCodigoAsesor) {
        this.txtCodigoAsesor = txtCodigoAsesor;
    }

    public String getTxtNombreAsesor() {
        return txtNombreAsesor;
    }

    public void setTxtNombreAsesor(String txtNombreAsesor) {
        this.txtNombreAsesor = txtNombreAsesor;
    }

    public String getTxtCodigoAgencia() {
        return txtCodigoAgencia;
    }

    public void setTxtCodigoAgencia(String txtCodigoAgencia) {
        this.txtCodigoAgencia = txtCodigoAgencia;
    }

    public String getTxtDescripcionAgencia() {
        return txtDescripcionAgencia;
    }

    public void setTxtDescripcionAgencia(String txtDescripcionAgencia) {
        this.txtDescripcionAgencia = txtDescripcionAgencia;
    }
    
    
    
    
    
    public String getTxtcodigoPlan() {
        return txtcodigoPlan;
    }

    public void setTxtcodigoPlan(String txtcodigoPlan) {
        this.txtcodigoPlan = txtcodigoPlan;
    }

    
    
    
    public String getTxtMeses() {
        return txtMeses;
    }

    public void setTxtMeses(String txtMeses) {
        this.txtMeses = txtMeses;
    }

    public String getTxtMonto() {
        return txtMonto;
    }

    public void setTxtMonto(String txtMonto) {
        this.txtMonto = txtMonto;
    }
    
    

    public String getCodAS() {
        return codAS;
    }

    public void setCodAS(String codAS) {
        this.codAS = codAS;
    }
    
   
 
    public String getCodNavega() {
        return codNavega;
    }

    public void setCodNavega(String codNavega) {
        this.codNavega = codNavega;
    }
    

    public Boolean getBlnActivoCodCliente() {
        return blnActivoCodCliente;
    }

    public void setBlnActivoCodCliente(Boolean blnActivoCodCliente) {
        this.blnActivoCodCliente = blnActivoCodCliente;
    }

    public Boolean getBlnActivoCodAS() {
        return blnActivoCodAS;
    }

    public void setBlnActivoCodAS(Boolean blnActivoCodAS400) {
        this.blnActivoCodAS = blnActivoCodAS400;
    }

    
    public List<TipoVenta> getListaTipoVenta() {
        return listaTipoVenta;
    }

    public void setListaTipoVenta(List<TipoVenta> listaTipoVenta) {
        this.listaTipoVenta = listaTipoVenta;
    }

    public int getIdTipoVenta() {
        return idTipoVenta;
    }

    public void setIdTipoVenta(int idTipoVenta) {
        this.idTipoVenta = idTipoVenta;
    }
    
    public String getTextoNoInstalacion() {
        return textoNoInstalacion;
    }

    public void setTextoNoInstalacion(String textoNoInstalacion) {
        this.textoNoInstalacion = textoNoInstalacion;
    }

    public String getTextoAnexo() {
        return textoAnexo;
    }

    public void setTextoAnexo(String textoAnexo) {
        this.textoAnexo = textoAnexo;
    }

    public String getTextoTelefono() {
        return textoTelefono;
    }

    public void setTextoTelefono(String textoTelefono) {
        this.textoTelefono = textoTelefono;
    }

    public String getTextoOrdenTrabajo() {
        return textoOrdenTrabajo;
    }

    public void setTextoOrdenTrabajo(String textoOrdenTrabajo) {
        this.textoOrdenTrabajo = textoOrdenTrabajo;
    }
    
    
    public Boolean getBlnActivoOrdenTrabajo() {
        return blnActivoOrdenTrabajo;
    }

    public void setBlnActivoOrdenTrabajo(Boolean blnActivoOrdenTrabajo) {
        this.blnActivoOrdenTrabajo = blnActivoOrdenTrabajo;
    }

    public Boolean getBlnActivoAnexo() {
        return blnActivoAnexo;
    }

    public void setBlnActivoAnexo(Boolean blnActivoAnexo) {
        this.blnActivoAnexo = blnActivoAnexo;
    }
    
    
    
    public Date getFechaSelect() {
        return fechaSelect;
    }

    public void setFechaSelect(Date fechaSelect) {
        this.fechaSelect = fechaSelect;
    }
    
    
   
    
    
    @PostConstruct
    public void init() {           
            
        ////////////////////////// 
        String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);
        String rolUsuarioLogueado = this.getAtributoSession(Constantes.SS_ROL);
        this.textoAnexo="";
        this.textoNoInstalacion="";
        this.textoOrdenTrabajo="";
        this.textoTelefono="";
        this.fechaPInicial = Constantes.obtenerFechaAnterior().getTime();
        this.fechaPFinal = Constantes.obtenerFechaFinal().getTime();
        this.fechaSelect= this.fechaPInicial;
        this.listaTipoPlan = new ArrayList<TipoPlan>();
        listaCoordinador = this.obtenerListadeCoordinador();        
         
        if(usuarioLogueado!=null && rolUsuarioLogueado!=null){
            this.listaTipoVenta = this.obtenerListaTipoVenta();
            this.listaAgencia = this.obtenerListaAgencia();
            if(rolUsuarioLogueado.equals("COORD")){
                for(int i =0; i< listaCoordinador.size(); i++){
                    String usuarioDom = listaCoordinador.get(i).getUsuario_dominio();
                    if(usuarioDom.equals(usuarioLogueado.toUpperCase())){
                        this.setBlnActivoOneSelected(true);                        
                        this.setNombreCoordinadorSelected(listaCoordinador.get(i).getNombre());
                        this.listaAsesor = this.obtenerListadeAsesor(listaCoordinador.get(i).getNombre());
                        break;
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
    }
    
    
     public List<AgenciaDistribuidor> obtenerListaAgencia(){
        listaAgenciasMap = new LinkedHashMap<Integer,String>();
       
        Connection conex = null;
        ResultSet rs1 = null;
        String error="";
        
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();                        
            Statement st1 = conex.createStatement();            
            String qury = Querys_C.getConsulta_Agencias();
            rs1 = st1.executeQuery(qury);
            
            while (rs1.next())
            {
                AgenciaDistribuidor agencia = new AgenciaDistribuidor();
                listaAgenciasMap.put(Integer.parseInt(rs1.getString("ID_DISTRIBUIDOR")),(rs1.getString("NOM_DISTRIBUIDOR")));                             
            }
            
        }catch(Exception e){
            error = e.toString();
        }finally{
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }    
        return listaAgencia;        
    }
     
     
    public List<TipoVenta> obtenerListaTipoVenta(){
        List<TipoVenta> listaTipoVenta = new ArrayList<TipoVenta>();
        Connection conex = null;
        ResultSet rs1 = null;
        String error="";
        
        try{
            Conexion conec = new Conexion();                
            conex = conec.getConexion();                        
            Statement st1 = conex.createStatement();
            AgregarQuery_C query = new AgregarQuery_C();
            String qury = query.generarQueryTipoVenta();
            rs1 = st1.executeQuery(qury);
            
            while (rs1.next())
            {
                TipoVenta tVenta = new TipoVenta();
                tVenta.setIdTipoTelefonia(Integer.parseInt(rs1.getString("Cod_TELEFONIA")));
                tVenta.setNombreTelenia((rs1.getString("NOMTELEFONIA")));
                listaTipoVenta.add(tVenta);
            }
            
        }catch(Exception e){
            error = e.toString();
        }finally{
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }    
        return listaTipoVenta;        
    }
    
  
     public void btnClickRegresarEvento(){
        try{          
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/renovacion.xhtml");           
        }catch(Exception e){
        
        }        
    }
    
    public void btnClickGuardar(){
        try{
           boolean bandera=true; 
           if(this.codAS.equals("") && this.codNavega.equals("")){
                showMessage("Ingrese un codigo de cliente de AS400 o Navega Plus","");
                bandera=false;
           }
           
           if(this.blnActivoAnexo==false && this.blnActivoTelefono==false){
               if(this.textoAnexo.equals("")){
                    showMessage("Ingrese un anexo","");
                    bandera=false;
               }

                if(this.textoTelefono.equals("")){
                    showMessage("Ingrese un telefono","");
                    bandera=false;
                }               
           }
           
           if(this.blnActivoOrdenTrabajo==false && this.blnActivoNoInstalacion==false){
               if(this.textoOrdenTrabajo.equals("")){
                    showMessage("Ingrese una orden de trabajo","");
                    bandera=false;
               }    
                if(this.textoNoInstalacion.equals("")){
                    showMessage("Ingrese un numero de instalación","");
                    bandera=false;
                }   
           }
           
           
            
           if(this.blnActivoTelefono==false && this.blnActivoNoInstalacion==false){               
                showMessage("Ingrese un numero de instalacion o un telefono","");
                bandera=false;                          
           }
           
           if(bandera){
               
                Conexion conec = new Conexion();                
                Connection conex = conec.getConexion();
                String queryAsignar = "{CALL comisiongt.tb_cargaajusteventas_portalweb(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                CallableStatement cstmt = conex.prepareCall(queryAsignar);
                
                if(!this.blnActivoAnexo && !this.blnActivoTelefono){
                    cstmt.setFloat("ad_anexo", Float.parseFloat(this.textoAnexo));
                    cstmt.setString("ad_telefono", this.textoTelefono); 
                    cstmt.setNull("ad_instalacion", Types.FLOAT);
                    cstmt.setNull("ad_ordentrabajo", Types.FLOAT);                    
                }
                
                if(!this.blnActivoNoInstalacion && !this.blnActivoOrdenTrabajo){
                    cstmt.setNull("ad_anexo", Types.FLOAT);
                    cstmt.setNull("ad_telefono", Types.VARCHAR);  
                    cstmt.setFloat("ad_instalacion", Float.parseFloat(this.textoNoInstalacion));
                    cstmt.setFloat("ad_ordentrabajo", Float.parseFloat(this.textoOrdenTrabajo));                       
                }
                
                cstmt.setFloat("ad_idtipotelefonia", (this.idTipoVenta)); // tipo de telefonia               
                SimpleDateFormat fechaGeneralFormato = new SimpleDateFormat("dd/MM/yyyy");
                String fechaFinal = (fechaGeneralFormato.format(this.fechaSelect));                                
                cstmt.setString("ad_fecVenta", fechaFinal);
                cstmt.setFloat("ad_valventa", Float.parseFloat(this.txtMonto));
                cstmt.setFloat("ad_mesescontrato", Float.parseFloat(this.txtMeses));
                cstmt.setString("ad_tipoplan", this.idTipoPlan);
                cstmt.setFloat("ad_idplan", Float.parseFloat(this.txtcodigoPlan));
                cstmt.setString("ad_descplan", this.txtDescripcionPlan);
                cstmt.setString("ad_moneda", this.idMoneda);
                
                if(!this.blnActivoCodAS){
                    cstmt.setFloat("ad_idcliente", Float.parseFloat(this.codAS));
                }
                
                if(!this.blnActivoCodCliente){
                    cstmt.setFloat("ad_idcliente", Float.parseFloat(this.codNavega) );
                }
               
                cstmt.setString("ad_nit", this.txtNitCliente);
                cstmt.setString("ad_nomcliente", this.txtNombreCliente);
                cstmt.setString("ad_direccion", this.txtDireccionCliente);
                
                cstmt.setFloat("ad_idvendedor", (this.idAsesorSelelected));
                cstmt.setString("ad_nomvendedor", "");
                
                cstmt.setString("ad_idagencia", Integer.toString(this.idAgencia));
                cstmt.setString("ad_descAgencia", this.txtDescripcionAgencia.trim());
                
                int idProceso=0;
                if(this.idTipoVenta==26){
                    idProceso=20010;  // Movil                  
                }
                if(this.idTipoVenta==27){
                    idProceso=20011;  // Fijo                 
                }
                cstmt.setFloat("ad_proceso", idProceso); // tipo de telefonia
                cstmt.registerOutParameter("ad_error",  java.sql.Types.FLOAT);
                
                String query = cstmt.toString();                
                cstmt.executeQuery();  
                float valorRetorno = cstmt.getFloat("ad_error");
                cstmt.close(); 
                
                if(valorRetorno==0){
                     JsfUtil.addSuccessMessage(Constantes.SS_AGREGARVENTA);
                     vaciarCampos();
                }else{
                     JsfUtil.addSuccessMessage(Constantes.SS_AGREGARVENTAFALLO);
                }                            
                                                                      
               String callProcedure ="";
           }
        }catch(Exception e){
            String error = e.toString();
            error = error+ "";
        }
        
    }
    
    public void vaciarCampos(){
        this.textoAnexo="";
        this.textoTelefono="";
        this.textoNoInstalacion="";
        this.textoOrdenTrabajo="";       
        this.fechaSelect= Constantes.obtenerFechaAnterior().getTime();
        this.idTipoVenta=0;
        this.txtMonto="";
        this.txtMeses="";
        this.idTipoPlan="";
        this.txtcodigoPlan="";
        this.txtDescripcionPlan="";
        this.idMoneda="";
        this.codAS="";
        this.codNavega="";
        this.txtNitCliente="";
        this.txtNombreCliente="";
        this.txtDireccionCliente="";
        this.txtCodigoAsesor="";
        this.txtNombreAsesor="";
        this.idAgencia=0;
        this.txtDescripcionAgencia="";   
        this.idAsesorSelelected=0;
        this.nombreCoordinadorSelected="";
        this.listaAsesor.clear();
    }
    
    
    
    public void key_upTextNoInstalacion() {
        boolean bandera=false;        
        
        if(this.textoAnexo.equals("")){
            this.blnActivoAnexo=true;
            this.blnActivoTelefono=true;
            if(this.blnActivoAnexo){
                this.blnActivoOrdenTrabajo=false;
                this.blnActivoTelefono=true;
            }else{
                bandera=true;
            }
        }else{
            bandera=true;
        }
        
        if(this.textoNoInstalacion.equals("")){
            this.blnActivoTelefono=false;
            this.blnActivoOrdenTrabajo=true;
        }
        
        if(bandera){
            showMessage("Ah ingresado un teléfono","" );
        }
    }
     
    
     public void key_upTextTelefono() {
        boolean bandera=false;        
        if(this.textoOrdenTrabajo.equals("")){
            this.blnActivoOrdenTrabajo=true;
            this.blnActivoNoInstalacion=true;
            if(this.blnActivoOrdenTrabajo){
                this.blnActivoAnexo=false;             
                 this.blnActivoNoInstalacion=true;
            }else{
               bandera=true; 
            }
        }else{
            bandera=true;
        }
        
        if(this.textoTelefono.equals("")){
           this.blnActivoNoInstalacion=false;
           this.blnActivoAnexo=true;    
        }
         
        if(bandera){
            showMessage("Ah ingresado un número de instalacion","");
        }                
    }
                
    public void key_upTextCodigoAS() {               
        if(this.blnActivoCodCliente==false && this.codNavega.equals("")){
            this.blnActivoCodCliente=true;                       
        }
        
        if(!this.codAS.equals("")){
            this.blnActivoCodCliente=true;   
        }else{
            this.blnActivoCodCliente=false; 
        }
    }
     
    public void key_upTextCodigoNavega() {
        if(this.blnActivoCodAS==false && this.codAS.equals("")){            
            this.blnActivoCodAS=true;   
        }                 
        if(!this.codNavega.equals("")){
            this.blnActivoCodAS=true;  
        }else{
            this.blnActivoCodAS=false; 
        }
         
    }
    
   
    public void oneAgenciaChange(){        
        int id= this.idAgencia;
        this.txtDescripcionAgencia = this.listaAgenciasMap.get(id);               
    }
     
     
    public void showMessage(String titulo, String descripcion){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, descripcion));
    }
    
   
   
}
