package com.bdg.base;

/**
 *
 * @author oulloa
 */

public class Constantes {
    
    /**
     * Constantes para el inicio de sesión
     */
    public final static String LOGIN_FAIL = "NO-LOGON";
    public final static String TIGO_MAIL_DOMAIN = "tigo.com.gt";
    public final static String ROL_ADMIN = "ADMIN";
    public final static String ROL_TIER2 = "TIER2";
    public final static String ROL_PLANIFICADOR = "BASIC";
    public final static String ROL_GERENTE = "ADMIN";
    public final static String ROL_NO_ROL = "NO-TIENE-PERFIL";
    
    /**
     * Drivers
     */
    public final static String DB_OJDBC_CLASSNAME = "oracle.jdbc.driver.OracleDriver";
    public final static String DB_OJDBC_CLASSNAME_SQLSRVR = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
        /**
     * Base de Datos Oracle Amsys 
     */
    public final static String DB_SID_AMSYS = "amsys";
    public final static String DB_USER_AMSYS = "amnetgt";
    public final static String DB_PASS_AMSYS = "amnetgt";//Revisar encripcion u ofuscación???    
    public final static String DB_HOST_AMSYS = "172.30.14.18:1521";
    public final static String DB_SCHEMA_NAME_AMSYS = "amnetgt";    
    public final static String DB_ORACLE_PACKAGE_AMSYS = "APP_PR_PKG";
    public final static String DB_FUNCTION_LOGIN_AMSYS = "FNC_VERIFY_USER";
    public final static String DB_FUNCTION_ROL_USUARIO_AMSYS = "FNC_VERIFY_PROFILE";
    public final static String DB_PROCESS_PWD_AMSYS = "pr_Change_PW";
    public final static String QRY_BUSCAR_USUARIO_AMSYS = "SELECT COUNT(*) FROM DBA_USERS WHERE USERNAME = ";
    
    
    /**
     * base de datos Oracle Desarrollo Test
     */
    public final static String DB_SID = "COMISIONES";    
    public final static String DB_USER = "COMISIONGT";    
    public final static String DB_PASS = "COMISIONGT1";//Revisar encripcion u ofuscación???    
    public final static String DB_HOST = "172.30.13.65:1521";        
    public final static String DB_SCHEMA_NAME = "comisiongt";    
    public final static String DB_ORACLE_PACKAGE = "tb_pkg_cms_web";    
    public final static String DB_FUNCTION_LOGIN = "FNC_VERIFICA_USER";
    public final static String DB_FUNCTION_ROL_USUARIO = "FNC_VERIFICA_PERFIL";
    public final static String DB_FUNCTION_DATOS_USUARIO_COORD = "seg_cod_coordinador";
    public final static String DB_PROCESS_PWD = "pr_Update_Pwd";
        
         /**
     * base de datos SQL Server 
     */    
    public final static String DB_HOST_SQLSRVR = "172.22.11.79";
    public final static String DB_INSTANCE_NAME_SQLSRVR = "SQLSERVER";
    public final static String DB_NAME_SQLSRVR = "testdb";
    public final static String DB_USER_SQLSRVR = "sa";
    public final static String DB_PASS_SQLSRVR = "Tigodb2016";//Revisar encripcion u ofuscación???
    public final static String DB_SCHEMA_SQLSRVR = "dbo";    
         
      /**
     * base de datos Oracle Desarrollo Localhost
     */
    public final static String DB_HOST_TEST = "172.22.11.79:1521";
    public final static String DB_SID_TEST = "XE";
    public final static String DB_USER_TEST = "TEST";
    public final static String DB_PASS_TEST = "Tigodb2016";//Revisar encripcion u ofuscación???
    public final static String DB_SCHEMA_NAME_TEST = "TEST";
    public final static String DB_ORACLE_PACKAGE_TEST = "TEST_PKG";
    public final static String DB_FUNCTION_ROL_USUARIO_TEST = "FNC_VERIFICA_PERFIL";
    public final static String DB_FUNCTION_LOGIN_TEST = "FNC_VERIFICA_USER";
    public final static String DB_PROCESS_PWD_TEST = "pr_Change_PW";
    public final static String DB_PROCESS_PWD_TEST_AMSYS = "pr_Change_PW";
    
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
    
    /**
     * Mensajes de usuario
     */
    public final static String MSG_ERROR_LOGIN_CREDENCIALES = "No se pudo iniciar sesión, credenciales incorrectas.";
    public final static String MSG_ERORR_LOGIN_NO_ROL = "Usuario ingresado no tiene autorización para ingresar al sistema.";
    public final static String MSG_ERROR_GENERICO = "Ocurrió un error al realizar la operación solicitada.";
     
    
     /**
     * Mensajes Modulo Asignar Ventas
     */    
    public final static String MSG_SUCCESS_ASIGNA_VENTA = "Venta asignada exitosamente!";
    public final static String MSG_SUCCESS_DESASIGNA_VENTA = "Venta Des-asignada exitosamente!";
    public final static String MSG_SUCCESS_DESASIGNA_RENOVACION = "Renovacion Des-asignada exitosamente!";
    public final static String MSG_ERROR_BUSCAR_ASIGNAR_VENTA = "Debe Indicar al menos un dato a buscar";
    public final static String MSG_SUCCESS_ASIGNA_RENOVACION = "Renovacion asignada exitosamente!";;
    
    /**
     * Mensajes Modulo ReAsignar Ventas/Renovaciones
     */    
    public final static String SS_VentasReAsignar="ReAsignar";
    public final static String SS_RenovacionesReAsignar="ReAsignar";
    public final static String MSG_SUCCESS_REASIGNA_VENTA = "Venta ReAsignada exitosamente!";    
    public final static String MSG_SUCCESS_REASIGNA_RENOVACION = "Renovación ReAsignada exitosamente!";    
    public final static String MSG_ERROR_REASIGNACION = "Se debe de seleccionar alguna venta para ser reasignada a otro vendedor.";
    public final static String MSG_ERROR_REASIGNACION_RENOVA = "Se debe de seleccionar alguna renovación para ser reasignada a otro vendedor.";
 
    /**
     * Mensajes Modulo GTM- Excepciones por Vendedor
     */    
    public final static String MSG_ERROR_BUSCAR_PERFILGR  = "Debe seleccionar un Gerente";    
    public final static String MSG_ERROR_BUSCAR_PERFILSP  = "Debe seleccionar un Supervisor";    
    public final static String MSG_ERROR_BUSCAR_PERFILTL  = "Debe seleccionar un Team Leader";    
}
