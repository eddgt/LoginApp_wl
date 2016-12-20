/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.bdg.base.Constantes;
import org.bdg.cms_buc.Querys_Vendedor;
import org.bdg.session.BaseSession;

/**
 *
 * @author Daniel Méndez
 */
public class ObtenerEstructura extends BaseSession {
    
    
    public boolean setAtributosVendedor(String usuario, String nombre){
        Connection conex=null;
        boolean resultado =false;
        
        try{            
            org.bdg.cms_conexion.Conexion conec = new org.bdg.cms_conexion.Conexion();                
            conex = conec.getConexion();
            /////////////////
            ResultSet rs1 = null;
            Statement st1 = conex.createStatement();
            String query = Querys_Vendedor.obtenerInformacionAsesor(usuario,nombre);            
            rs1 = st1.executeQuery(query);
            
            while (rs1.next())
            {
                this.setAbributoSession(Constantes.ID_VEND, rs1.getString("NUMDOCUMENTO"));
                this.setAbributoSession(Constantes.VEND_NAME, rs1.getString("NOMPERSONA"));
                this.setAbributoSession(Constantes.VEND_COORDINADOR, rs1.getString("NOM_COORDINADOR"));
                this.setAbributoSession(Constantes.VEND_GERENTE, rs1.getString("NOM_GERENTE"));
                this.setAbributoSession(Constantes.VEND_USUARIO, rs1.getString("USUARIO"));
                resultado=true;
            }
            
            
                                              
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                conex.close();
            } catch (SQLException ex) {
                
            }
        }     
        return resultado;
    }
}
