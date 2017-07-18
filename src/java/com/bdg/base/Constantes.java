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
    public final static String ROL_BASIC = "BASIC";
    public final static String ROL_NO_ROL = "SIN PERFIL";

    /**
     * Drivers
     */
    public final static String DB_OJDBC_CLASSNAME = "oracle.jdbc.driver.OracleDriver";
    public final static String DB_OJDBC_CLASSNAME_SQLSRVR = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        /**
     * Base de Datos Oracle Amsys
     */
    public final static String DB_SID_AMSYS_DEV = "amsys";
    public final static String DB_SID_AMSYS_PRO = "amsys";
    public final static String DB_USER_AMSYS_DEV = "amnetgt";
    public final static String DB_USER_AMSYS_PRO = "";
    public final static String DB_PASS_AMSYS_DEV = "";//Revisar encripcion u ofuscación???
    public final static String DB_PASS_AMSYS_PRO = "";
    public final static String DB_HOST_AMSYS_DEV = "172.30.14.18:1521";
    public final static String DB_HOST_AMSYS_PRO = "";
    public final static String DB_SCHEMA_NAME_AMSYS_DEV = "amnetgt";
    public final static String DB_SCHEMA_NAME_AMSYS_PRO = "amnetgt";
    public final static String DB_ORACLE_PACKAGE_AMSYS_DEV = "APP_PR_PKG";
    public final static String DB_ORACLE_PACKAGE_AMSYS_PRO = "APP_PR_PKG";
    public final static String DB_FUNCTION_LOGIN_AMSYS_DEV = "FNC_VERIFICA_USER";
    public final static String DB_FUNCTION_LOGIN_AMSYS_PRO = "FNC_VERIFICA_USER";
    public final static String DB_FUNCTION_ROL_USUARIO_AMSYS_DEV = "FNC_VERIFICA_PERFIL";
    public final static String DB_FUNCTION_ROL_USUARIO_AMSYS_PRO = "FNC_VERIFICA_PERFIL";
    public final static String DB_PROCESS_PWD_AMSYS_DEV = "pr_Change_PW";
    public final static String DB_PROCESS_PWD_AMSYS_PRO = "pr_Change_PW";
    public final static String DB_PROCESS_MAIL_AMSYS_DEV = "app_Send_Mail";
    public final static String DB_PROCESS_MAIL_AMSYS_PRO = "app_Send_Mail";
    public final static String QRY_USR_ACTIVO_AMSYS = "SELECT COUNT(*) FROM DBA_USERS WHERE ACCOUNT_STATUS='OPEN'  AND USERNAME = ";
    public final static String QRY_USR_INACTIVO_AMSYS = "SELECT COUNT(*) FROM DBA_USERS WHERE ACCOUNT_STATUS LIKE '%LOCKED%' AND USERNAME = ";

    /**
     * base de datos SQL Server
     */
    public final static String DB_HOST_SQLSRVR_LOCAL = "172.22.11.79";
    public final static String DB_HOST_SQLSRVR_DEV = "172.16.5.20";
    public final static String DB_HOST_SQLSRVR_PRO = "";
    public final static String DB_INSTANCE_NAME_SQLSRVR_LOCAL = "SQLSERVER";
    public final static String DB_INSTANCE_NAME_SQLSRVR_DEV = "NAVPLUSDB-DEV";
    public final static String DB_INSTANCE_NAME_SQLSRVR_PRO = "NAVPLUSDB-DEV";
    public final static String DB_NAME_SQLSRVR_LOCAL = "testdb";
    public final static String DB_NAME_SQLSRVR_DEV = "SSO_APPADMIN";
    public final static String DB_NAME_SQLSRVR_PRO = "SSO_APPADMIN";
    public final static String DB_USER_SQLSRVR_LOCAL = "sa";
    public final static String DB_USER_SQLSRVR_DEV = "navegaplus";
    public final static String DB_USER_SQLSRVR_PRO = "";
    public final static String DB_PASS_SQLSRVR_LOCAL = "Tigodb2016";//Revisar encripcion u ofuscación???
    public final static String DB_PASS_SQLSRVR_DEV = "";
    public final static String DB_PASS_SQLSRVR_PRO = "";
    public final static String DB_SCHEMA_SQLSRVR = "dbo";

      /**
     * base de datos Oracle Desarrollo Localhost
     */
    public final static String DB_HOST_TEST = "172.22.11.79:1521";
    public final static String DB_SID_TEST = "XE";
    public final static String DB_USER_TEST = "TEST";
    public final static String DB_PASS_TEST = "Tigodb2016#";//Revisar encripcion u ofuscación???
    public final static String DB_SCHEMA_NAME_TEST = "TEST";
    public final static String DB_ORACLE_PACKAGE_TEST = "TEST_PKG";
    public final static String DB_FUNCTION_ROL_USUARIO_TEST = "FNC_VERIFICA_PERFIL";
    public final static String DB_FUNCTION_LOGIN_TEST = "FNC_VERIFICA_USER";
    public final static String DB_PROCESS_PWD_TEST = "pr_Change_PW";
    public final static String DB_PROCESS_PWD_TEST_AMSYS = "pr_Change_PW";
    public final static String DB_PROCESS_MAIL_TEST = "app_Send_Mail";

         /**
     * base de datos Oracle Desarrollo NAF
     */
    public final static String DB_HOST_NAF_DEV = "172.22.116.124:1521";
    public final static String DB_SID_NAF_DEV = "CONTANAV";
    public final static String DB_HOST_NAF_PRO = "";
    public final static String DB_SID_NAF_PRO = "CONTANAV";
    public final static String DB_USER_NAF_DEV = "naf47";
    public final static String DB_USER_NAF_PRO = "";
    public final static String DB_PASS_NAF_DEV = "";
    public final static String DB_PASS_NAF_PRO = "";
    public final static String DB_SCHEMA_NAME_NAF_DEV = "naf47";
    public final static String DB_SCHEMA_NAME_NAF_PRO = "naf47";
    public final static String DB_ORACLE_PACKAGE_NAF_DEV = "APP_PR_PKG";
    public final static String DB_ORACLE_PACKAGE_NAF_PRO = "APP_PR_PKG";
    public final static String DB_PROCESS_PWD_NAF_DEV = "PR_CHANGE_NAF";
    public final static String DB_PROCESS_PWD_NAF_PRO = "PR_CHANGE_NAF";
    public final static String QRY_USR_ACTIVO_NAF = "SELECT COUNT(*) FROM TASGUSUARIO WHERE IND_ACTIVO='S' AND USUARIO = ";
    public final static String QRY_USR_INACTIVO_NAF = "SELECT COUNT(*) FROM TASGUSUARIO WHERE IND_ACTIVO='N' AND USUARIO = ";

    /**
     * Constantes para uso de WebService
     */
    public final static String WSERVICE_USR = "pruebas";
    public final static String WSERVICE_PWD = "pruebas";
    //public final static String WLAPP_LINK = "t3://172.24.240.20:8080/ResetService/";
    //public final static String WLAPP_LINK = "t3://172.30.100.36:7020/ResetService/";//nodo balanceador 1
    //public final static String WLAPP_LINK = "t3://172.30.100.37:7020/ResetService/";//nodo balanceador 2
    public final static String WLAPP_LINK = "t3://172.22.11.79:7001/ResetService/";//ip localhost pc
    public final static String WSERVICE_LINK = "https://172.16.5.19/wsSecurity/wsClaves.asmx?wsdl";//this only for reference not used in the functionality
    

    /**
     * Constantes de valores en sesión
     */

    public final static String SS_USUARIO = "usuario";
    public final static String SS_ROL = "rol";

    /**
     * Mensajes de usuario
     */
    public final static String MSG_ERROR_LOGIN_CREDENCIALES = "No se pudo iniciar sesión, credenciales incorrectas.";
    public final static String MSG_ERORR_LOGIN_NO_ROL = "Usuario no tiene autorización para ingresar al sistema.";
    public final static String MSG_ERROR_GENERICO = "Ocurrió un error al realizar la operación solicitada.";

}
