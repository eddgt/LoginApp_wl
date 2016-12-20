/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_buc;

import org.bdg.base.Constantes;
import org.bdg.utils.JsfUtil;

/** 
 * @author Daniel Mendez
 */
public class Querys_C {
    
    public String consulta_InformacionVentas = "";
    public String consulta_InformacionVentasOperacion = "";
    public String consulta_DetalleReporte = "";
    public String consulta_Vendedores ="";
    public String consulta_Coordinador="";
    public String consulta_CoordinadorAntigua ="";
    public String consulta_CoordinadorOperacion ="";
    public String consulta_CoordinadorOperacionVista ="";
    public String consulta_AsesorOperacionVista ="";
    public String consulta_ComboSistema ="";
    public String consulta_AsesorOperacion ="";
    public String  consulta_VendedorOperacion="";
    public String modificar_Estado="";
    public String Ejecutar_Proceso;
    public String borrar_AsesorVentaOperacion;
    public String asignar_AsesorVentaOperacion;
    public String consulta_InsertaBitacoraComentario;
    private String consulta_VentasXAsignar ="";
    public String asignarAsesor_Venta="";
    public String consulta_RevertirPlanningVerificadosCORDPLANNING;
    public String consulta_RevisarPlanningVerificadosCORDPLANNING;
    public String consulta_VentasSiEstaAsignada;
    public String consulta_ReAsignaVenta;
    public String consulta_ReAsignaRenovacion;    
    public String consulta_CargaAjusteVentas;
    public String consulta_CargaPreliminarVN;
    public String consulta_CargaDataPreliminar;
    public String consulta_InsertaBitacoraJustificacion;
    public String consulta_Agencias;
    public String consulta_InformacionVentasOperacionXAsesor;

    public static String getConsulta_Agencias() {
        return "SELECT * FROM TB_AGENCIA_DISTRIBUIDOR";        
    }
    
    public String getConsulta_InsertaBitacoraJustificacion() {
        return consulta_InsertaBitacoraJustificacion;
    }

    public void setConsulta_InsertaBitacoraJustificacion(String consulta_InsertaBitacoraJustificacion) {
        this.consulta_InsertaBitacoraJustificacion = consulta_InsertaBitacoraJustificacion;
    }

    public String getConsulta_CargaAjusteVentas() {
        return consulta_CargaAjusteVentas;
    }

    public void setConsulta_CargaAjusteVentas(String consulta_CargaAjusteVentas) {
        this.consulta_CargaAjusteVentas = consulta_CargaAjusteVentas;
    }

    public String getConsulta_CargaPreliminarVN() {
        return consulta_CargaPreliminarVN;
    }

    public void setConsulta_CargaPreliminarVN(String consulta_CargaPreliminarVN) {
        this.consulta_CargaPreliminarVN = consulta_CargaPreliminarVN;
    }

    public String getConsulta_CargaDataPreliminar() {
        return consulta_CargaDataPreliminar;
    }

    public void setConsulta_CargaDataPreliminar(String consulta_CargaDataPreliminar) {
        this.consulta_CargaDataPreliminar = consulta_CargaDataPreliminar;
    }

    public String getConsulta_ReAsignaVenta() {
        return consulta_ReAsignaVenta;
    }

    public void setConsulta_ReAsignaVenta(String consulta_ReAsignaVenta) {
        this.consulta_ReAsignaVenta = consulta_ReAsignaVenta;
    }

    public String getConsulta_ReAsignaRenovacion() {
        return consulta_ReAsignaRenovacion;
    }

    public void setConsulta_ReAsignaRenovacion(String consulta_ReAsignaRenovacion) {
        this.consulta_ReAsignaRenovacion = consulta_ReAsignaRenovacion;
    }
    
    public String getConsulta_VentasSiEstaAsignada() {
        return consulta_VentasSiEstaAsignada;
    }

    public void setConsulta_VentasSiEstaAsignada(String consulta_VentasSiEstaAsignada) {
        this.consulta_VentasSiEstaAsignada = consulta_VentasSiEstaAsignada;
    }
                
    public String getConsulta_RevisarPlanningVerificadosCORDPLANNING() {
        return consulta_RevisarPlanningVerificadosCORDPLANNING;
    }

    public void setConsulta_RevisarPlanningVerificadosCORDPLANNING(String consulta_RevisarPlanningVerificadosCORDPLANNING) {
        this.consulta_RevisarPlanningVerificadosCORDPLANNING = consulta_RevisarPlanningVerificadosCORDPLANNING;
    }

    public String getAsignarAsesor_Venta() {
        return asignarAsesor_Venta;
    }
    
    public String getConsulta_RevertirPlanningVerificadosCORDPLANNING() {
        return consulta_RevertirPlanningVerificadosCORDPLANNING;
    }

    public void setConsulta_RevertirPlanningVerificadosCORDPLANNING(String consulta_RevertirPlanningVerificadosCORDPLANNING) {
        this.consulta_RevertirPlanningVerificadosCORDPLANNING = consulta_RevertirPlanningVerificadosCORDPLANNING;
    }

    public String getConsulta_InsertaBitacoraComentario() {
        return consulta_InsertaBitacoraComentario;
    }

    public void setConsulta_InsertaBitacoraComentario(String consulta_InsertaBitacoraComentario) {
        this.consulta_InsertaBitacoraComentario = consulta_InsertaBitacoraComentario;
    }
    
    
    
    public void generarConsultaRevisarPlanningVerificadosCORDPLANNIN(String coordinadorCORDPLANNIN){
        String queryCORDPLANNING = "SELECT distinct vd.Coordinador as coordinador,"
                + "vd.NOM_VENDEDOR, vd.COD_VENDEDOR_WEBSOX codvend\n" +
                "From IUSR_CMS_WEB.Vw_Venededor vd Where vd.periodo = to_number(to_char( add_months( Sysdate, cc), 'rrrrmm'))  and "
                + "vd.coordinador = "
                + " '"
                + coordinadorCORDPLANNIN
                + "'\n" +
                "And vd.estado_coordinador= 'FNC' AND vd.estado_planning = 'VRP'";
        this.setConsulta_RevisarPlanningVerificadosCORDPLANNING(queryCORDPLANNING);
    }
    
    public void generarConsultaVerifiqueSiExisteAsignacion(String telefono, String numAnexo, String numInstalacion){
        String consultaSiExiste = "Select x.nom_cliente ,  to_date( (((x.anio*100)+x.mes)*100)+x.dia, 'rrrrmmdd') "
                + "fecha, x.no_instalacion, x.numtelefono, x.anexo_num, x.producto_tb Tipo_Producto,"
                + " x.nom_tip_telefonia tipo_transaccion, x.meses_contrato plazo, x.moneda, "
                + "round(x.cuota_basica,2) cuota_basica, round(x.valor_enlace,2) valor_enlace, x.idventa, " +
                "x.coordinador_tb_confirmado, x.codigo_ejecutivo_websox, x.nom_vendedor_as400 " +
                " From IUSR_CMS_WEB.cms_venta_nueva x " +
                " Where x.periodo = to_number(to_char( add_months( Sysdate, c), 'rrrrmm')) " +                
                "  And (  ";
        
        
        if(numAnexo!=null){           
            consultaSiExiste +=  " x.anexo_num = " + numAnexo + " ";                       
        }
        
        if(telefono!=null){
            if(numAnexo!=null){           
                consultaSiExiste +=  " OR x.numtelefono = '" + telefono + "'" ;
            }else{
                consultaSiExiste +=  " x.numtelefono = '" + telefono + "'" ;
            }            
        }

        if(numInstalacion!=null){           
            if(telefono!=null){
                consultaSiExiste +=  " OR x.no_instalacion = '" + numInstalacion + "'" ;
            }else{
                consultaSiExiste +=  " x.no_instalacion = '" + numInstalacion + "'" ;
            }    
        }                
        
        if(numAnexo!=null || telefono!=null || numInstalacion!=null){
            consultaSiExiste += " )";
        }
        
        if(numAnexo == null && telefono==null && numInstalacion==null){
            consultaSiExiste ="";
        }
        
        this.setConsulta_VentasSiEstaAsignada(consultaSiExiste);                   
    }
    
    public void generarConsultaRevertirPlanningVerificadosCORDPLANNIN(String codigoVendedor, String codigoUsuarioCambio){
        String queryRevertirPlanning = ("{CALL comisiongt.tb_pkg_cms_web.Planning_devuelve_vta_new("+codigoVendedor+", '"
                + codigoUsuarioCambio
                + "' )}");
        this.setConsulta_RevertirPlanningVerificadosCORDPLANNING(queryRevertirPlanning);
    }

    public void setAsignarAsesor_Venta(String asignarAsesor_Venta) {
        this.asignarAsesor_Venta = asignarAsesor_Venta;
    }

    public String getConsulta_VentasXAsignar() {
        return consulta_VentasXAsignar;
    }
    
    public String getModificar_Estado() {
        return modificar_Estado;
    }

    public void setModificar_Estado(String modificar_Estado) {
        this.modificar_Estado = modificar_Estado;
    }

    public String getBorrar_AsesorVentaOperacion() {
        return borrar_AsesorVentaOperacion;
    }

    public void setBorrar_AsesorVentaOperacion(String borrar_AsesorVentaOperacion) {
        this.borrar_AsesorVentaOperacion = borrar_AsesorVentaOperacion;
    }

    public String getConsulta_InformacionVentasOperacionXAsesor() {
        return consulta_InformacionVentasOperacionXAsesor;
    }

    public void setConsulta_InformacionVentasOperacionXAsesor(String consulta_InformacionVentasOperacionXAsesor) {
        this.consulta_InformacionVentasOperacionXAsesor = consulta_InformacionVentasOperacionXAsesor;
    }

    public String getAsignar_AsesorVentaOperacion() {
        return asignar_AsesorVentaOperacion;
    }

    public void setAsignar_AsesorVentaOperacion(String asignar_AsesorVentaOperacion) {
        this.asignar_AsesorVentaOperacion = asignar_AsesorVentaOperacion;
    }
    
    
    
    public String generar_Planning_finaliza_venta(int idPersona, String usuario){
        return "{CALL comisiongt.tb_pkg_cms_web.Planning_finaliza_vta_new("+idPersona+", '"+usuario+"')}";
    }
    public String generar_Consulta_RenovacionXAsignar(String codCliente, String nomCliente, String telefono, String numAnexo){        
 
                String consulta = new StringBuilder()
                        .append("Select x.fecposventa, ")
                        .append("x.tipo_factura_subsidio, ")
                        .append("x.tipo_subsidio, ")
                        .append("x.idtipo_factura_subsidio,")
                        .append("x.anexo, ")
                        .append("x.telefono_instalacion, ")
                        .append("x.agencia, ")
                        .append("x.nom_agencia, ")
                        .append("x.nit, ")
                        .append("x.no_cliente, ")
                        .append("x.nom_cliente,")
                        .append("x.promocion, ")
                        .append("x.meses_promocion, ")
                        .append("x.mbf_en_$usd, ")
                        .append("x.upgrades_$usd, ")
                        .append("x.mbf_en_$usd_ventas,")
                        .append("x.tiempo_pendiente, ")
                        .append("x.target, ")
                        .append("x.sistema, ")
                        .append("x.numdocumento, ")
                        .append("x.idtipasesor, ")
                        .append("x.nomtipasesor,")
                        .append("x.idtiptelefonia,")
                        .append("x.nomtiptelefonia, ")
                        .append("x.idposventa     ")
                 .append("From IUSR_CMS_WEB.CMS_Renovaciones x ")
                .append("Where x.periodo = to_number(to_char( add_months( Sysdate, -1), 'rrrrmm')) ")
                   .append("And x.idpersona = -1").toString();

                if(codCliente!=null){
                    consulta +=  " And x.NO_CLIENTE = " + codCliente ;
                }        
                if(nomCliente!=null){
                    consulta +=  " And UPPER(x.NOM_CLIENTE) LIKE UPPER('%" + nomCliente + "%')" ;
                }        
                if(telefono!=null){
                    consulta +=  " And x.telefono_instalacion = " + telefono ;
                }        
                if(numAnexo!=null){
                    consulta +=  " And x.anexo = " + numAnexo ;
                }            
        return consulta;  
        
   }
    
    public void generar_Consulta_VentasXAsignar(String codcliente, String nomcliente, String telefono, String numAnexo, String numInstalacion){
        if (codcliente==null && nomcliente==null && telefono==null && numAnexo==null && numInstalacion==null){
            //mostrar error si el usuario no indica al menos un parametro
            JsfUtil.addErrorMessage(Constantes.MSG_ERROR_BUSCAR_ASIGNAR_VENTA);
        }
        else{
            String consultaVentasxAsignar = "Select x.nom_cliente ,  to_date( (((x.anio*100)+x.mes)*100)+x.dia, 'rrrrmmdd') fecha, x.no_instalacion, x.numtelefono, x.anexo_num," +
                                " x.producto_tb Tipo_Producto, x.nom_tip_telefonia tipo_transaccion, x.meses_contrato plazo, x.moneda, round(x.cuota_basica,2) cuota_basica," +
                                " round(x.valor_enlace,2) valor_enlace, x.idventa" +
                                " From IUSR_CMS_WEB.cms_venta_nueva x" +
                                " Where x.periodo = to_number(to_char( add_months( Sysdate, -1), 'rrrrmm')) " +
                                " And x.codigo_ejecutivo_websox   = -1   " ;
            if(codcliente!=null){
                consultaVentasxAsignar +=  " And x.COD_CLIENTE_AS400_NAV = '" + codcliente + "'" ;
            }
            if(nomcliente!=null){
                consultaVentasxAsignar +=  " And UPPER(x.NOM_CLIENTE) LIKE UPPER('%" + nomcliente + "%')" ;
            }

            if(telefono!=null){
                consultaVentasxAsignar +=  " And x.numtelefono = '" + telefono + "'" ;
            }

            if(numAnexo!=null){
                consultaVentasxAsignar +=  " And x.anexo_num = '" + numAnexo + "'";
            }

            if(numInstalacion!=null){
                consultaVentasxAsignar +=   " And x.no_instalacion  = '" + numInstalacion + "'";
            }
            this.setConsulta_VentasXAsignar(consultaVentasxAsignar);
      }
    }
    
    public void setConsulta_VentasXAsignar(String consulta_VentasAsignar) {
        this.consulta_VentasXAsignar = consulta_VentasAsignar;
    }
    
    
    public String getConsulta_CoordinadorAntigua() {
        return consulta_CoordinadorAntigua;
    }

    public void setConsulta_CoordinadorAntigua(String consulta_CoordinadorAntigua) {
        this.consulta_CoordinadorAntigua = consulta_CoordinadorAntigua;
    }

    public String getConsulta_CoordinadorOperacion() {
        return consulta_CoordinadorOperacion;
    }

    public void setConsulta_CoordinadorOperacion(String consulta_CoordinadorOperacion) {
        this.consulta_CoordinadorOperacion = consulta_CoordinadorOperacion;
    }

    public String getConsulta_AsesorOperacion() {
        return consulta_AsesorOperacion;
    }

    public void setConsulta_AsesorOperacion(String consulta_AsesorOperacion) {
        this.consulta_AsesorOperacion = consulta_AsesorOperacion;
    }

    public String getConsulta_CoordinadorOperacionVista() {
        return consulta_CoordinadorOperacionVista;
    }

    public void setConsulta_CoordinadorOperacionVista(String consulta_CoordinadorOperacionVista) {
        this.consulta_CoordinadorOperacionVista = consulta_CoordinadorOperacionVista;
    }

    public String getConsulta_AsesorOperacionVista() {
        return consulta_AsesorOperacionVista;
    }

    public void setConsulta_AsesorOperacionVista(String consulta_AsesorOperacionVista) {
        this.consulta_AsesorOperacionVista = consulta_AsesorOperacionVista;
    }

    public String getConsulta_ComboSistema() {
        return consulta_ComboSistema;
    }

    public void setConsulta_ComboSistema(String consulta_ComboSistema) {
        this.consulta_ComboSistema = consulta_ComboSistema;
    }

    public String getConsulta_VendedorOperacion() {
        return consulta_VendedorOperacion;
    }

    public void setConsulta_VendedorOperacion(String consulta_VendedorOperacion) {
        this.consulta_VendedorOperacion = consulta_VendedorOperacion;
    }

    public String getEjecutar_Proceso() {
        return Ejecutar_Proceso;
    }

    public void setEjecutar_Proceso(String Ejecutar_Proceso) {
        this.Ejecutar_Proceso = Ejecutar_Proceso;
    }

    public String getConsulta_DetalleReporte() {
        return consulta_DetalleReporte;
    }

    public void setConsulta_DetalleReporte(String consulta_DetalleReporte) {
        this.consulta_DetalleReporte = consulta_DetalleReporte;
    }
    
    
    
    
    /*VENTAS NUEVAS*/    
    public String generar_Consulta_PlanningFinalizarVenta(int codigoVendedor, String usuario){
        String queryAsignarAsesorVenta = ("{CALL comisiongt.tb_pkg_cms_web.Planning_finaliza_vta_new("+codigoVendedor+", '"+usuario+"')}");
        return queryAsignarAsesorVenta;
    }
    /*VENTAS NUEVAS*/
    public String generar_Consulta_CoordinadorFinalizarVenta(int codigoVendedor, String usuario){
        String queryAsignarAsesorVenta = ("{CALL comisiongt.tb_pkg_cms_web.Coordina_Acepta_vta_new("+codigoVendedor+", '"+usuario+"')}");
        return queryAsignarAsesorVenta;
    }

    /*RENOVACIONES*/    
    public String generar_Consulta_PlanningFinalizarRenovacion(int codigoVendedor, String usuario){
        String queryAsignarAsesorVenta = ("{CALL comisiongt.tb_pkg_cms_web.Planning_finaliza_Renov("+codigoVendedor+", '"+usuario+"')}");
        return queryAsignarAsesorVenta;
    }    
    /*RENOVACIONES*/
    public String generar_Consulta_CoordinadorFinalizarRenovacion(int codigoVendedor, String usuario){
        String queryAsignarAsesorVenta = ("{CALL comisiongt.tb_pkg_cms_web.Coordina_Acepta_Renov("+codigoVendedor+", '"+usuario+"')}");
        return queryAsignarAsesorVenta;
    }
    
    public void generar_Consulta_AsignarAsesorVenta(int idVenta, int codigoVendedor, String usuario){
        String queryAsignarAsesorVenta = ("CALL comisiongt.tb_pkg_cms_web.asigna_venta("
        + Integer.toString(idVenta)+ ", " + Integer.toString(codigoVendedor)+ ", "+ "'"+ usuario+ "' )");
        this.setAsignarAsesor_Venta(queryAsignarAsesorVenta);
    }
     
    public void generar_Consulta_CambioEstado(int codigoVenta){
        String queryCambioEstado = ("{CALL ComisionGt.tb_asigna_venta( "+codigoVenta+", '-1' )}");
        this.setModificar_Estado(queryCambioEstado);
    }
    
    public void generar_Consulta_EjecutaProceso(String periodo){
        String query = ("{CALL ComisionGt.PKG_TBBO_EJECUCION.EJECUTARPROCESOS( '"+periodo+"')}");
        this.setEjecutar_Proceso(query);
    }
    
    /*Metodo que borrar Asesor de una o varias Operacion de Ventas*/
     public void generar_Consulta_BorrarAsesorVentaOperacion(int id){
        String queryBorrarAsesorVentaOperacion = ("UPDATE TBBO_OPERACIONES SET EJECUTIVO_VENTA=NULL, COORDINADOR=NULL, GERENTE=NULL WHERE id = "+id);
        this.setBorrar_AsesorVentaOperacion(queryBorrarAsesorVentaOperacion);
    }
    
     /*Metodo que Asignar Asesor a Operacion de Ventas*/
     public void generar_Consulta_AsignarAsesorVentaOperacion(int id, String asesor, String coordinador){
        String queryAsignarAsesorVentaOperacion = ("UPDATE TBBO_OPERACIONES SET EJECUTIVO_VENTA=UPPER('"+asesor+"'), COORDINADOR=UPPER('"+coordinador+"') WHERE id = "+id);
        this.setAsignar_AsesorVentaOperacion(queryAsignarAsesorVentaOperacion);
    }
    
    public void generar_Consulta_Coordinador_An(){
         String query =("SELECT * FROM comisiongt.tb_coordinadores order by nombre ");
         this.setConsulta_CoordinadorAntigua(query);
    }
    
    public void generar_Consulta_Coordinador_Oper(){
         String query =("SELECT DISTINCT(COORDINADOR) NOMBRE, COORDINADOR_USER FROM  VIEW_TBBO_ESTRUCTURA_COMERCIAL WHERE COORDINADOR_USER IS NOT NULL ORDER BY COORDINADOR ASC");
         this.setConsulta_CoordinadorOperacion(query);
    }
    
    public void generar_Consulta_Coordinador_Vista(String asesor){
         String query =("SELECT COORDINADOR NOMBRE, GERENTE FROM VIEW_TBBO_ESTRUCTURA_COMERCIAL WHERE EJECUTIVO='"+asesor+"' GROUP BY COORDINADOR, GERENTE ORDER BY NOMBRE ASC");
         this.setConsulta_CoordinadorOperacionVista(query);
    }
    
    public void generar_Consulta_Asesor_Vista(String user, String rol){
        if(rol.equals("ADMIN")){
            String query =("SELECT DISTINCT(ejecutivo)NOMBRE FROM VIEW_TBBO_ESTRUCTURA_COMERCIAL ORDER BY NOMBRE ASC");
            this.setConsulta_AsesorOperacionVista(query);
        }else{
            String query =("SELECT DISTINCT(ejecutivo)NOMBRE FROM VIEW_TBBO_ESTRUCTURA_COMERCIAL WHERE COORDINADOR_USER ='"+user+"' ORDER BY NOMBRE ASC");
            this.setConsulta_AsesorOperacionVista(query);
        }         
    }
    
    public void generar_Consulta_Asesor_Oper(String coordinador){
         String query =("SELECT DISTINCT(EJECUTIVO)NOMBRE FROM  VIEW_TBBO_ESTRUCTURA_COMERCIAL WHERE COORDINADOR ='"+coordinador+"' ORDER BY EJECUTIVO ASC");
         this.setConsulta_AsesorOperacion(query);
    }
    
    public void generar_Consulta_Vendedor_Oper(String user){
         String query =("SELECT DISTINCT(EJECUTIVO)NOMBRE, USUARIO_USER FROM  VIEW_TBBO_ESTRUCTURA_COMERCIAL WHERE USUARIO_USER ='"+user+"' ORDER BY EJECUTIVO ASC");
         this.setConsulta_VendedorOperacion(query);
    }
    
     public void generar_Consulta_Combo_Sistema(){
         String query =("SELECT DISTINCT(SISTEMA) SISTEMA FROM TBBO_OPERACIONES WHERE SISTEMA IS NOT NULL");
         this.setConsulta_ComboSistema(query);
    }
    
    public void generar_ConsultaCoordRenovacion(boolean isCoordinador){
        String consultaCoordinador = "";                    
            consultaCoordinador =
                    " SELECT distinct Ren.coordinador_tb_confirmado as NOMBRE , PER.NUMDOCUMENTO as ID, TMP.USUARIO_DOMINIO as USUARIO_DOMINIO "+
                    " FROM IUSR_CMS_WEB.CMS_Renovaciones Ren  "
                    + " INNER JOIN comisionreg.TABCOPERSONA per "
                    + " ON (UPPER(PER.NOMPERSONA) = UPPER(coordinador_tb_confirmado)) "
                    + "  INNER JOIN comisiongt.tb_coordinadores tmp ON (UPPER(PER.NOMPERSONA) = UPPER(TMP.NOMBRE)) "
                    + " where Ren.coordinador_tb_confirmado != '-'"
                    + " and Ren.numdocumento not in (-1,-2) "
//                    + " and Ren.estado_coordinador = 'RVC'"
                    + " and Ren.periodo = to_number(to_char( add_months( Sysdate, -1), 'rrrrmm'))"
                    + " order by Ren.coordinador_tb_confirmado  ";

            this.setConsulta_CoordinadorAntigua(consultaCoordinador);
    }
    
       
    
    public String generar_Consulta_Desasignar_Renovacion(int codigoRenovacion, String username){
        String queryCambioEstado = ("{CALL ComisionGt.tb_pkg_cms_web.asigna_renovacion( "+codigoRenovacion+", -1,'" + username + "')}");
        System.out.println(queryCambioEstado);
        return queryCambioEstado;
    }
    
    public String generar_Consulta_Asignar_Renovacion(int codigoRenovacion, int codigoVendedor, String username){
        String queryCambioEstado = ("{CALL ComisionGt.tb_pkg_cms_web.asigna_renovacion( "+codigoRenovacion+", "+ codigoVendedor+",'" + username + "')}");
        System.out.println(queryCambioEstado);
        JsfUtil.addSuccessMessage(Constantes.MSG_SUCCESS_ASIGNA_RENOVACION);//mensaje success
        return queryCambioEstado;
    }
    
    /*Re-Asigna Venta*/
    public void generar_Consulta_ReAsignaVenta(int idVenta, String old_vendedor, String new_vendedor, String user_modif){
        String queryReAsignaVenta = ("{CALL ComisionGt.tb_pkg_cms_web.reasigna_venta( "+idVenta+", '"+old_vendedor+"', '"+new_vendedor+"', '"+user_modif+"' )}");
        this.setConsulta_ReAsignaVenta(queryReAsignaVenta);
    }
    
    /*Re-Asigna Renovación*/
    public void generar_Consulta_ReAsignaRenovacion(int idPosVenta, String old_vendedor, String new_vendedor, String user_modif){
        String queryReAsignaRenovacion = ("{CALL ComisionGt.tb_pkg_cms_web.ReAsigna_Renovacion( "+idPosVenta+", '"+old_vendedor+"', '"+new_vendedor+"', '"+user_modif+"' )}");
        this.setConsulta_ReAsignaRenovacion(queryReAsignaRenovacion);
    }

    public String generar_Consulta_Renovacion(int codVendedor){
         String consulta = new StringBuilder()
                .append("Select x.fecposventa, ")
                .append("x.tipo_factura_subsidio, ")
                .append("x.tipo_subsidio, ")
                .append("x.idtipo_factura_subsidio,")
                .append("x.anexo, ")
                .append("x.telefono_instalacion, ")
                .append("x.agencia, ")
                .append("x.nom_agencia, ")
                .append("x.nit, ")
                .append("x.no_cliente, ")
                .append("x.nom_cliente,")
                .append("x.promocion, ")
                .append("x.meses_promocion, ")
                .append("x.mbf_en_$usd, ")
                .append("x.upgrades_$usd, ")
                .append("x.mbf_en_$usd_ventas,")
                .append("x.tiempo_pendiente, ")
                .append("x.target, ")
                .append("x.sistema, ")
                .append("x.numdocumento, ")
                .append("x.idtipasesor, ")
                .append("x.nomtipasesor,")
                .append("x.idtiptelefonia,")
                .append("x.nomtiptelefonia, ")
                .append("x.idposventa     ")
        .append(" From IUSR_CMS_WEB.CMS_Renovaciones x ")
        .append(" Where x.periodo = to_number(to_char( add_months( Sysdate, -1), 'rrrrmm')) ")
        .append(" And x.numdocumento = " + Integer.toString(codVendedor)).toString();

        return consulta;
    }

    public String generar_Consulta_ReAsignaRenovacion(String IdsVentas){
        String consulta = new StringBuilder()
                .append("Select x.fecposventa, ")
                .append("x.tipo_factura_subsidio, ")
                .append("x.tipo_subsidio, ")
                .append("x.idtipo_factura_subsidio,")
                .append("x.anexo, ")
                .append("x.telefono_instalacion, ")
                .append("x.agencia, ")
                .append("x.nom_agencia, ")
                .append("x.nit, ")
                .append("x.no_cliente, ")
                .append("x.nom_cliente,")
                .append("x.promocion, ")
                .append("x.meses_promocion, ")
                .append("x.mbf_en_$usd, ")
                .append("x.upgrades_$usd, ")
                .append("x.mbf_en_$usd_ventas,")
                .append("x.tiempo_pendiente, ")
                .append("x.target, ")
                .append("x.sistema, ")
                .append("x.numdocumento, ")
                .append("x.idtipasesor, ")
                .append("x.nomtipasesor,")
                .append("x.idtiptelefonia,")
                .append("x.nomtiptelefonia, ")
                .append("x.idposventa     ")
        .append(" From IUSR_CMS_WEB.CMS_Renovaciones x ")
        .append(" Where x.periodo = to_number(to_char( add_months( Sysdate, -1), 'rrrrmm')) ")
//           .append("And x.idpersona = (Select p.idpersona From comisionreg.tabcopersona p Where p.numdocumento = " + Integer.toString(codVendedor) + ")").toString();
        .append(" And x.IdPosVenta IN (" + IdsVentas + ")").toString();
        return consulta;
    }
    
    public void generar_Consulta_Parametros(int codVendedor){
         String consulta ="Select x.COD_CLIENTE_AS400_NAV CodCliente, x.NIT_CLIENTE NIT, x.nom_cliente, "
        +"to_date( (((x.anio*100)+x.mes)*100)+x.dia, 'rrrrmmdd') fecha, x.no_instalacion, x.numtelefono, "
        +"x.anexo_num, x.producto_tb Tipo_Producto, x.nom_tip_telefonia tipo_transaccion, x.meses_contrato plazo, "
        +"x.moneda, round(x.cuota_basica,2) cuota_basica, round(x.valor_enlace,2) valor_enlace, x.idventa, "
        +"x.Sistema Origen, x.nombre_plan_voz Producto  "
        +" From IUSR_CMS_WEB.cms_venta_nueva x"
        +" Where x.periodo = to_number(to_char( add_months( Sysdate, -1), 'rrrrmm')) "
        +" And x.codigo_ejecutivo_websox   <> -1 "  
        +" And x.codigo_ejecutivo_websox  = '" + Integer.toString(codVendedor)+"'";
       this.setConsulta_InformacionVentas(consulta);
    }   

    
     public String getConsulta_InformacionVentasOperacion() {
        return consulta_InformacionVentasOperacion;
    }

    public void setConsulta_InformacionVentasOperacion(String consulta_InformacionVentasOperacion) {
        this.consulta_InformacionVentasOperacion = consulta_InformacionVentasOperacion;
    }
    
      public void generar_Consulta_Parametros_Operacion(){
          //Set my query
         String consulta ="SELECT a.ID,a.ANE_INS, a.CATEGORIA, a.CLIENTE, a.AB_VENTA, a.ANEXO_PADRE, a.BUSINESS_CD, a.BUSINESS_NAME, "
                 + "a.BUSINESS_TYPE , a.CLASI_MIC, a.CLIENTE_MIC, a.CLIENTE_WHOLESALE, a.NIT, a.CNT_RGU_VENTA, a.COD_CLIENTE, "
                 + "a.COD_DISTRIBUIDOR, a.COD_MODELO, a.COD_PROMOCION, a.COD_VENDEDOR, a.COD_VENDEDOR_NP, a.COD_VENTA, a.MONTO_VENTA, "
                 + "a.COORDINADOR, a.DISTRIBUIDOR_AS400, a.EJECUTIVO_VENTA, a.ESTADO_ANULACION, a.FECHA, a.FECHA_ANULACION, "
                 + "a.FUENTE, a.GERENTE, a.PERIODO, a.PRODUCTO_GLOBAL "
                 + "FROM TBBO_OPERACIONES a WHERE  a.EJECUTIVO_VENTA is null ORDER BY  a.ID ASC";
         //returno my query
       this.setConsulta_InformacionVentasOperacion(consulta);
    }   

    public void generar_Consulta_Parametros_Operacion_XAsesor(String Asesor, String Periodo){
          //Set my query
         String consulta ="SELECT a.ID,a.ANE_INS, a.SISTEMA,a.FUENTE, a.CATEGORIA, a.CLIENTE, a.AB_VENTA, a.ANEXO_PADRE, a.BUSINESS_CD, a.BUSINESS_NAME, "
                 + "a.BUSINESS_TYPE , a.CLASI_MIC, a.CLIENTE_MIC, a.CLIENTE_WHOLESALE, a.NIT, a.CNT_RGU_VENTA, a.COD_CLIENTE, "
                 + "a.COD_DISTRIBUIDOR, a.COD_MODELO,a.MODELO, a.COD_PROMOCION, a.COD_VENDEDOR, a.COD_VENDEDOR_NP, a.COD_VENTA, a.MONTO_VENTA,"
                 + "a.COORDINADOR, a.DISTRIBUIDOR_AS400,a.MESES_CONTRATO,  a.TIPO_CAMBIO, a.EJECUTIVO_VENTA, a.ESTADO_ANULACION, a.FECHA, a.FECHA_ANULACION, "
                 + "a.FUENTE, a.GERENTE, a.PERIODO, a.PRODUCTO_GLOBAL "
                 + "FROM TBBO_OPERACIONES a WHERE a.EJECUTIVO_VENTA = UPPER('"+Asesor+"') AND a.PERIODO ='"+Periodo+"'";
         //return my query
       this.setConsulta_InformacionVentasOperacionXAsesor(consulta);
    }   
    

    public void generar_Consulta_Reporte(String per){
       //String consulta ="SELECT SISTEMA, FECHA FROM VIEW_TBBO_OPERACIONES";
       String consulta ="SELECT * FROM VIEW_TBBO_OPERACIONES WHERE PERIODO='"+per+"'";
       this.setConsulta_DetalleReporte(consulta);
    }
    
    public void generar_Consulta_Vendedores(String nombreCordinador, boolean isCoordinador){
        String consultaVendedor = "";
        if (isCoordinador){
            consultaVendedor = "SELECT distinct vd.Coordinador as coordinador,vd.NOM_VENDEDOR, vd.COD_VENDEDOR_WEBSOX codvend "+
            "From IUSR_CMS_WEB.Vw_Venededor vd Where vd.periodo = to_number(to_char( add_months( Sysdate, -1), 'rrrrmm'))  and vd.coordinador =  '"+ nombreCordinador +"' order by  NOM_VENDEDOR ";            
            //"And vd.estado_coordinador = 'RVC'";
        }else{
            consultaVendedor = "SELECT distinct vd.Coordinador as coordinador,vd.NOM_VENDEDOR, vd.COD_VENDEDOR_WEBSOX codvend "+
            "From IUSR_CMS_WEB.Vw_Venededor vd Where vd.periodo = to_number(to_char( add_months( Sysdate, -1), 'rrrrmm')) and vd.coordinador =  '"+ nombreCordinador +"' order by  NOM_VENDEDOR ";
            //"And vd.estado_planning = 'VRP'";
        }
      
        this.setConsulta_Vendedores(consultaVendedor);
    }

      public void generar_Consulta_VendedoresRenovacion(String nombreCordinador, boolean isCoordinador){
        String consultaVendedor = "";
        if (isCoordinador){
            consultaVendedor = 
                    
            /*"SELECT  vd.Coordinador as coordinador,vd.NOM_VENDEDOR, vd.COD_VENDEDOR_WEBSOX codvend "+
            "From IUSR_CMS_WEB.Vw_Venededor vd Where vd.periodo = to_number(to_char( add_months( Sysdate, -1), 'rrrrmm'))  and vd.coordinador =  '"+ nombreCordinador +"' "+
            "And vd.estado_coordinador = 'RVC'";*/
             " SELECT distinct coordinador_tb_confirmado as coordinador, nompersona as NOM_VENDEDOR, NUMDOCUMENTO as codvend  FROM IUSR_CMS_WEB.CMS_Renovaciones where coordinador_tb_confirmado != '-' "+
            " and numdocumento not in (-1,-2) "
//            + " and estado_coordinador = 'RVC'"
            + " and periodo = to_number(to_char( add_months( Sysdate, -1), 'rrrrmm')) "
            + " and UPPER(coordinador_tb_confirmado) = UPPER('" + nombreCordinador+ "')"
            + " order by coordinador_tb_confirmado, nompersona";          
                    
        }else{
            consultaVendedor = 
                    
            //"SELECT vd.Coordinador as coordinador,vd.NOM_VENDEDOR, vd.COD_VENDEDOR_WEBSOX codvend "+
            //"From IUSR_CMS_WEB.Vw_Venededor vd Where vd.periodo = to_number(to_char( add_months( Sysdate, -1), 'rrrrmm')) and vd.coordinador =  '"+ nombreCordinador +"' "+
            //"And vd.estado_planning = 'VRP'";
            
            //"SELECT distinct coordinador_tb_confirmado as coordinador, nompersona as NOM_VENDEDOR, NUMDOCUMENTO as codvend 
            
            
            " SELECT distinct coordinador_tb_confirmado as coordinador, nompersona as NOM_VENDEDOR, NUMDOCUMENTO as codvend  FROM IUSR_CMS_WEB.CMS_Renovaciones where coordinador_tb_confirmado != '-' "+
            " and numdocumento not in (-1,-2) "
//            + " and estado_coordinador = 'VRP'"
            + " and periodo = to_number(to_char( add_months( Sysdate, -1), 'rrrrmm')) "
            + " and UPPER(coordinador_tb_confirmado) = UPPER('" + nombreCordinador+ "')"
            + " order by coordinador_tb_confirmado, nompersona";                                                                                                  
        }
      
        this.setConsulta_Vendedores(consultaVendedor);
    }
     
    public void generar_Consulta_AjusteVentas_TigoSpace(){
        String consultaAjusteVentas_TigoSpace =("{COMISIONGT.TB_CargaAjusteVentas_TigoSpace(20039)}");
        this.setConsulta_CargaAjusteVentas(consultaAjusteVentas_TigoSpace);        
    }
    
    public void generar_Consulta_CargaPreliminarVN(){
        String consultaCargaPreliminarVN =("{COMISIONGT.TB_REP_PRELIM_VTAS_NVAS ( TO_NUMBER(TO_CHAR(add_months(sysdate, -1),'rrrrmm')) )}");
        this.setConsulta_CargaPreliminarVN(consultaCargaPreliminarVN);        
    }

    public void generar_Consulta_CargaDataPreliminar(){
        String consultaCargaDataPreliminar =("{COMISIONGT.PKG_CMS_WEB.PRC_CARGA_DATA ( TO_NUMBER(TO_CHAR(add_months(sysdate, -1),'rrrrmm')) )}");
        this.setConsulta_CargaDataPreliminar(consultaCargaDataPreliminar);        
    }

    public String getConsulta_Coordinador() {
        return consulta_Coordinador;
    }

    public void setConsulta_Coordinador(String consulta_Coordinador) {
        this.consulta_Coordinador = consulta_Coordinador;
    }
    
    
    
    public String getConsulta_Vendedores() {
        return consulta_Vendedores;
    }

    public void setConsulta_Vendedores(String consulta_Vendedores) {
        this.consulta_Vendedores = consulta_Vendedores;
    }
    
    
    public String getConsulta_InformacionVentas() {
        return consulta_InformacionVentas;
    }

    public void setConsulta_InformacionVentas(String consulta_InformacionVentas) {
        this.consulta_InformacionVentas = consulta_InformacionVentas;
    }
    
    public void generar_InsertaBitacoraJustificacion(String IdVenta_PosVenta, String Justificacion, String Usuariomodifico, String tipoVenta){
        String InsertaBitacoraJustificacion =("{CALL COMISIONGT.SP_TB_BITACORA_ASIGNACIONES( " + IdVenta_PosVenta + ", '" + Justificacion + "', '" + Usuariomodifico + "', '" + tipoVenta + "' )}");
        this.setConsulta_InsertaBitacoraJustificacion(InsertaBitacoraJustificacion);        
    }
    
    
    public void generar_InsertaBitacoraComentarioOperacion(int IdVenta, String Comentario, String UsuarioModifico){
        String InsertaBitacoraComentario =("{CALL COMISIONGT.PKG_TBBO_OPERACIONES.OPERACIONES_REG_OBSERVACIONES( " + IdVenta + ", '" + Comentario + "', '" + UsuarioModifico + "')}");
        this.setConsulta_InsertaBitacoraComentario(InsertaBitacoraComentario);        
    }
            
 }
