/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.base;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.bdg.cms_dto.TipoPlan;

/**
 *
 * @author jarevalo
 */
public class Constantes {
    
    /**
     * Constantes para el inicio de sesión
     */
    public final static String LOGIN_FAIL = "NO-LOGON";
    public final static String TIGO_MAIL_DOMAIN = "tigo.com.gt";
    public final static String ROL_ADMIN = "ADMIN";
    public final static String ROL_VEND = "VENDE";  
  
    public final static String ROL_COORDINADOR = "COOR";
    public final static String ROL_PLANIFICADOR = "PLANI";
    public final static String ROL_NO_ROL = "NO-TIENE-PERFIL";
    
    /**
    * Constantes uso vendedor 
    */
    public final static String VEND_NAME = "VEND_NAME";    
    public final static String ID_VEND ="ID_VEND";
    public final static String VEND_GERENTE = "VEND_GERENTE";
    public final static String VEND_COORDINADOR = "VEND_COORDINADOR";
    public final static String VEND_USUARIO ="VEND_USUARIO";
    
    
    /**
     * Constantes para uso de base de datos
     */
    public final static String DB_SID = "COMISIONES";
    // Desarrollo
     public final static String DB_HOST = "172.30.13.65:1521";
     public final static String DB_PASS = "COMISIONGT1";//Revisar encripcion u ofuscación???
     public final static String DB_USER = "comisiongt";
    // Produccion
    //public final static String DB_USER = "comisiongt";
    //public final static String DB_PASS = "comisiongt$1";//Revisar encripcion u ofuscación???     
    //public final static String DB_HOST = "172.30.4.252:1521";
    
    public final static String DB_OJDBC_CLASSNAME = "oracle.jdbc.driver.OracleDriver";
    public final static String DB_SCHEME_NAME = "comisiongt";
    public final static String DB_ORACLE_PACKAGE = "tb_pkg_cms_web";
    public final static String DB_FUNCTION_LOGIN = "seg_fnc_verifica_user";
    public final static String DB_FUNCTION_ROL_USUARIO = "seg_verif_perfil";
    public final static String DB_FUNCTION_DATOS_USUARIO_COORD = "seg_cod_coordinador";
    
    /**
     * Constantes de valores en sesión
     */
    public final static String SS_USUARIO = "usuario";
    public final static String SS_ROL = "rol";
    public final static String SS_CoordinadorAsignar="coordinadorAsignar";
    public final static String SS_CoordinadorAsignarId="coordinadorAsignarId";
    public final static String SS_VendedorCongelar="vendedorCongelar";
    public final static String SS_VendedorCongelarId="vendedorCongelarId";
    public final static String SS_CoordinadorActual="coordinadorActual";
    public final static String SS_CoordinadorActualId="coordinadorActualId";
    public final static String SS_PaginaFinalizaVentaRenovacion="FinalizaVentaRenovacion";
    
     /**
     * Mensajes de usuario
     */
    public final static String MSG_ERROR_LOGIN_CREDENCIALES = "No se pudo iniciar sesión, credenciales incorrectas.";
    public final static String MSG_ERORR_LOGIN_NO_ROL = "Usuario ingresado no tiene autorización para ingresar al sistema.";
    public final static String MSG_ERROR_GENERICO = "Ocurrió un error al realizar la operación solicitada.";
    public final static String MSG_ERROR_SESION_CADUCADA = "Su sesion caducó, debe iniciar sesion nuevamente";

    
     /**
     * Mensajes Modulo Asignar Ventas
     */    
    public final static String MSG_SUCCESS_ASIGNA_VENTA = "Venta asignada exitosamente!";
    public final static String MSG_SUCCESS_DESASIGNA_VENTA = "Venta Des-asignada exitosamente!";
    public final static String MSG_SUCCESS_DESASIGNA_RENOVACION = "Renovacion Des-asignada exitosamente!";
    public final static String MSG_ERROR_BUSCAR_ASIGNAR_VENTA = "Debe Indicar al menos un dato a buscar";
    public final static String MSG_SUCCESS_ASIGNA_RENOVACION = "Renovacion asignada exitosamente!";;
    public final static String MSG_SUCCESS_CAMBIARMONTO= "El monto de la venta ha sido cambiado éxitosamente!";
    public final static String MSG_SUCCESS_CAMBIARMONTOR= "El monto de la renovacion ha sido cambiado éxitosamente!";
     
    /**
     * Mensajes Modulo Operaciones de Ventas
     */    
    public final static String MSG_SUCCESS_BORRA_VENTA_OPERACION = "Asesor borrado de la venta exitosamente!";
    public final static String MSG_SUCCESS_AGREGA_VENTA_OPERACION = "Asesor agregado a la venta exitosamente!";
    public final static String MSG_SUCCESS_MODIFICA_VENTA_OPERACION = "Asesor modificado en la venta exitosamente!";
    
    /**
     * Mensajes Modulo ReAsignar Ventas/Renovaciones
     */    
    public final static String SS_VentasReAsignar="ReAsignar";
    public final static String SS_RenovacionesReAsignar="ReAsignar";
    public final static String MSG_SUCCESS_REASIGNA_VENTA = "Venta ReAsignada exitosamente!";    
    public final static String MSG_SUCCESS_REASIGNA_RENOVACION = "Renovación ReAsignada exitosamente!";    
    public final static String MSG_ERROR_REASIGNACION = "Se debe de seleccionar alguna venta para ser reasignada a otro vendedor.";
    public final static String MSG_ERROR_REASIGNACION_RENOVA = "Se debe de seleccionar alguna renovación para ser reasignada a otro vendedor.";
    public final static String MSG_ERROR_ASIGNAVENTA = "Se debe de seleccionar al asesor al cual se asignara la venta.";
    public final static String MSG_ERROR_ASIGNARENOVACION= "Se debe de seleccionar al asesor al cual se asignara la renovación.";
    public final static String MSG_ERROR_SELECCIONVENTA = "Se debe de seleccionar por lo menos alguna venta.";
    public final static String MSG_ERROR_SELECCIONRENOVA = "Se debe de seleccionar por lo menos alguna renovación.";
    public final static String MSG_ERROR_ASIGNA= "SE DEBE DE ESCRIBIR LA JUSTIFICACIÓN PARA PODER REALIZAR EL PROCESO.";
    public final static String MSG_ERROR_ASIGNA_TAMANIO= "LA JUSTIFICACIÓN NO DEBE DE SER MAYOR A 150 LETRAS.";

     /**
     * Mensajes Modulo ReAsignar Ventas/Renovaciones
     */    
    public final static String SS_MSG_SUCCESS_RREVERSION_FINALIZACION="Se realizó CORRECTAMENTE la reversión del cierre o finalización del ejecutivo.";

     /**
     * Mensajes Modulo Procesos
     */    
    public final static String SS_MSG_SUCCESS_AJUSTEVENTAS="Se realizó CORRECTAMENTE el Re-Proceso de Ajuste de Ventas.";
    public final static String SS_MSG_ERROR_AJUSTEVENTAS="NO SE REALIZÓ CORRECTAMENTE EL RE-PROCESO DE AJUSTE DE VENTAS.";
    public final static String SS_MSG_SUCCESS_DATAPRELIMINAR="Se realizó CORRECTAMENTE el Re-Proceso de carga de datos Preliminares.";
    public final static String SS_MSG_ERROR_DATAPRELIMINAR="NO SE REALIZÓ CORRECTAMENTE EL RE-PROCESO DE CARGA DE DATOS PRELIMINARES.";
    
    /**/
    public final static String SS_AGREGARVENTA="La incersión fue realizada correctamente";
    public final static String SS_AGREGARVENTAFALLO="Ha ocurrido una incidencia al insertar la venta";
    public final static String SS_AGREGARRENOVACION="La incersión fue realizada correctamente";
    public final static String SS_AGREGARRENOVACIONFALLO="Ha ocurrido una incidencia al insertar la renovación";
    
    /** Mensajes para Modulo de Persona*/
    public final static String SS_AGREGAROK="Registro creado correctamente";
     public final static String SS_AGREGARERROR="Se ha producido un error al tratar de crear el registro.";
     public final static String SS_ACTUALIZAROK="Datos actualizados correctamente";
     public final static String SS_ACTUALIZARERROR="Se ha producido un error al tratar de actualizar.";
     public final static String SS_ELIMINAROK="Datos eliminados correctamente";
     public final static String SS_ELIMINARERROR="Se ha producido un error al tratar de eliminar.";
    
    
    public static Calendar obtenerFechaAnterior(){
       Calendar date = Calendar.getInstance();
       date.setTime(Calendar.getInstance().getTime());
       int anyo = date.get(Calendar.YEAR);
       int mes = date.get(Calendar.MONTH);
       mes --;       
       int day = 1;
       date.set(anyo, mes, 1);       
       return date;
    }
    
     public static Calendar obtenerFechaFinal(){
       Calendar date = Calendar.getInstance();
       date.setTime(Calendar.getInstance().getTime());
       int anyo = date.get(Calendar.YEAR);
       int mes = date.get(Calendar.MONTH);
       mes --;
       date.set(anyo, mes , 1);
       int day = date.getActualMaximum((Calendar.DAY_OF_MONTH));
       date.set(anyo, mes, day);       
       return date;      
    }
     
    public static List<TipoPlan> listaPlanFijo(){
        List<TipoPlan> lista = new ArrayList<TipoPlan>();
        
        TipoPlan local = new TipoPlan();
        local.setNombre("DATA LOCAL");
        
        TipoPlan regional = new TipoPlan();
        regional.setNombre("DATA REGIONAL");
        
        TipoPlan internet = new TipoPlan();
        internet.setNombre("INTERNET");
        
        lista.add(local);
        lista.add(regional);
        lista.add(internet);
        
        return lista;
    }
    
    public static List<TipoPlan> listaPlanMovil(){
        List<TipoPlan> lista = new ArrayList<TipoPlan>();
        
        TipoPlan voz = new TipoPlan();
        voz.setNombre("VOZ");
        
        TipoPlan sms = new TipoPlan();
        sms.setNombre("SMS");
        
        TipoPlan gprs = new TipoPlan();
        gprs.setNombre("GPRS");
        
        lista.add(voz);
        lista.add(sms);
        lista.add(gprs);
        
        return lista;        
    }
    
    
}
