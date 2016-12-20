/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.exporters;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.bdg.base.Constantes;
import org.bdg.database.ObtenerEstructura;
import org.bdg.session.BaseSession;

/**
 *
 * @author Admin_bdgsa
 */
public class ExcelConfigRepOperacion extends BaseSession {
    
    public void configurarExcelOperacion(Object document, boolean asesorSeleccionado, String nombreAsesor){
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);               
        HSSFCellStyle style = wb.createCellStyle();
        HSSFCellStyle styleEntero = wb.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        styleEntero.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
        
        
        List<String> nombreColumnas = new ArrayList<String>();
        /*
        nombreColumnas.add("GERENTE");
        nombreColumnas.add("COORDINADOR");
        nombreColumnas.add("EJECUTIVO");
        */
        
                                     
        sheet.shiftRows(0, sheet.getPhysicalNumberOfRows(), 2);
        
        //Crear primeras filas
        Row row0 = sheet.createRow((short)0);
        Row row1 = sheet.createRow((short)1);
      
        /*Row row2 = sheet.createRow((short)2);*/
        
        ObtenerEstructura estructuraVend = new ObtenerEstructura();                                
        String nombreVendedor="";
        String codVendedor="";
        String nombreGerente="";
        String nombreCoordinador="";
        String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);        
        boolean condicion=false;
    
        if(asesorSeleccionado){
            if(estructuraVend.setAtributosVendedor(null,nombreAsesor)){                
                nombreVendedor = this.getAtributoSession(Constantes.VEND_NAME);        
                codVendedor = this.getAtributoSession(Constantes.ID_VEND);
                nombreGerente = this.getAtributoSession(Constantes.VEND_GERENTE);
                nombreCoordinador = this.getAtributoSession(Constantes.VEND_COORDINADOR);
                condicion=true;
            }                       
        }
                
        if(!condicion){
            nombreVendedor = "--";        
            codVendedor = "--";
            nombreGerente = "--";
            nombreCoordinador = "--";
        }
        
        row0.createCell(0).setCellValue("Codigo: " + codVendedor);
        row1.createCell(0).setCellValue("Usuario: " + usuarioLogueado);   
       
     
        HSSFRow header = sheet.getRow(2);        
        
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = header.getCell(i);
            nombreColumnas.add(cell.getStringCellValue());
        }
        
        Row row2 = sheet.createRow((short)2);
        for(int i=0; i < nombreColumnas.size();i++) {
           row2.createCell(i).setCellValue(nombreColumnas.get(i));
        }

        int numeroInicial= 3;
        int numberOfRows= sheet.getPhysicalNumberOfRows();
        
        
        /*Insercion de columnas al principio del excel*/
        /*
        ExcelHelper.InsertarNuevaColumna(sheet, numberOfRows, nombreColumnas.size()-1, numeroInicial,0);
        ExcelHelper.InsertarNuevaColumna(sheet, numberOfRows, nombreColumnas.size()-1, numeroInicial,1);
        ExcelHelper.InsertarNuevaColumna(sheet, numberOfRows, nombreColumnas.size()-1, numeroInicial,2);
        
        
        ExcelHelper.RepetirValorEnColumna(sheet, nombreGerente, 0,numberOfRows, numeroInicial);
        ExcelHelper.RepetirValorEnColumna(sheet, nombreCoordinador, 1,numberOfRows, numeroInicial);
        ExcelHelper.RepetirValorEnColumna(sheet, nombreVendedor, 2,numberOfRows, numeroInicial);
        */
        
        //int numeroCell = header.getPhysicalNumberOfCells();        
        for(int noC=0; noC <nombreColumnas.size()-1 ;noC++) {
            sheet.autoSizeColumn(noC);
        }
        
        
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            String nombreColumna = nombreColumnas.get(i);
           
            if(nombreColumna.equals("ID")){
                ExcelHelper.ArreglarColumnasNumber(style, nombreColumna, numeroInicial,numberOfRows, sheet,i);
            }
            
            if(nombreColumna.equals("TELEFONO")){
                ExcelHelper.ArreglarColumnasNumber(style, nombreColumna, numeroInicial,numberOfRows, sheet,i);
            }

             if(nombreColumna.equals("ANEXO")){
                ExcelHelper.ArreglarColumnasNumber(styleEntero, nombreColumna, numeroInicial,numberOfRows, sheet,i);
            }  
            
            if(nombreColumna.equals("TIPO CAMBIO")){
               ExcelHelper.ArreglarColumnasNumber(styleEntero, nombreColumna, numeroInicial,numberOfRows, sheet,i);
            }  
            /*
            if(nombreColumna.equals("Teléfono")){
               ExcelHelper.ArreglarColumnasNumber(styleEntero, nombreColumna, numeroInicial,numberOfRows, sheet,i);
            }    */
            
        }
    }

    public void configurarExcelVentas(Object document, boolean asesorSeleccionado, String nombreAsesor){
         
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
       
        
        HSSFCellStyle style = wb.createCellStyle();
        HSSFCellStyle styleEntero = wb.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        styleEntero.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
        
        List<String> nombreColumnas = new ArrayList<String>();
        nombreColumnas.add("Gerente");
        nombreColumnas.add("Coordinador");
        nombreColumnas.add("Asesor");
                                     
        sheet.shiftRows(0, sheet.getPhysicalNumberOfRows(), 2);
        
        //Crear primeras filas
        Row row0 = sheet.createRow((short)0);
        Row row1 = sheet.createRow((short)1);
      
        /*Row row2 = sheet.createRow((short)2);*/
        
        ObtenerEstructura estructuraVend = new ObtenerEstructura();                                
        String nombreVendedor="";
        String codVendedor="";
        String nombreGerente="";
        String nombreCoordinador="";
        String usuarioLogueado = this.getAtributoSession(Constantes.SS_USUARIO);        
        boolean condicion=false;
        
        if(asesorSeleccionado){
             if(estructuraVend.setAtributosVendedor(null,nombreAsesor)){                
                 nombreVendedor = this.getAtributoSession(Constantes.VEND_NAME);        
                 codVendedor = this.getAtributoSession(Constantes.ID_VEND);
                 nombreGerente = this.getAtributoSession(Constantes.VEND_GERENTE);
                 nombreCoordinador = this.getAtributoSession(Constantes.VEND_COORDINADOR);
                 condicion=true;
             }                       
         }

         if(!condicion){
             nombreVendedor = "--";        
             codVendedor = "--";
             nombreGerente = "--";
             nombreCoordinador = "--";
         }
        
        
        
//        if(estructuraVend.setAtributosVendedor(null,this.listaAsesor.get(this.idAsesorSelelected))){
//            
//        }else{
//            nombreVendedor = "--";        
//            codVendedor = "--";
//            nombreGerente = "--";
//            nombreCoordinador = "--";
//        }            
              
        row0.createCell(0).setCellValue("Codigo: " + codVendedor);
        row1.createCell(0).setCellValue("Usuario: " + usuarioLogueado);   
       
     
        HSSFRow header = sheet.getRow(2);        
        
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = header.getCell(i);
            nombreColumnas.add(cell.getStringCellValue());
        }
        
        Row row2 = sheet.createRow((short)2);
        for(int i=0; i < nombreColumnas.size();i++) {
           row2.createCell(i).setCellValue(nombreColumnas.get(i));
        }
          
                          
        int numeroInicial= 3;
        int numberOfRows= sheet.getPhysicalNumberOfRows();
        
        ExcelHelper.InsertarNuevaColumna(sheet, numberOfRows, nombreColumnas.size()-1, numeroInicial,0);
        ExcelHelper.InsertarNuevaColumna(sheet, numberOfRows, nombreColumnas.size()-1, numeroInicial,1);
        ExcelHelper.InsertarNuevaColumna(sheet, numberOfRows, nombreColumnas.size()-1, numeroInicial,2);
        
        ExcelHelper.RepetirValorEnColumna(sheet, nombreGerente, 0,numberOfRows, numeroInicial);
        ExcelHelper.RepetirValorEnColumna(sheet, nombreCoordinador, 1,numberOfRows, numeroInicial);
        ExcelHelper.RepetirValorEnColumna(sheet, nombreVendedor, 2,numberOfRows, numeroInicial);
        
        //int numeroCell = header.getPhysicalNumberOfCells();        
        for(int noC=0; noC <nombreColumnas.size()-1 ;noC++) {
            sheet.autoSizeColumn(noC);
        }
        
        
        for(int i=0; i < nombreColumnas.size()-1;i++) {
            //HSSFCell cell = header.getCell(i);
            String nombreColumna = nombreColumnas.get(i);
                                    
            if(nombreColumna.equals("Cuota Básica")){
               ExcelHelper.ArreglarColumnasNumber(style, nombreColumna, numeroInicial,numberOfRows, sheet,i);
            }
            
            if(nombreColumna.equals("Valor del Enlace")){
               ExcelHelper.ArreglarColumnasNumber(style, nombreColumna, numeroInicial,numberOfRows, sheet,i);                
            }     
            
             
            if(nombreColumna.equals("CodCliente")){
               ExcelHelper.ArreglarColumnasNumber(styleEntero, nombreColumna, numeroInicial,numberOfRows, sheet,i);   
            }  
            
             if(nombreColumna.equals("No. Anexo")){
                ExcelHelper.ArreglarColumnasNumber(styleEntero, nombreColumna, numeroInicial,numberOfRows, sheet,i);   
            }  
            
            if(nombreColumna.equals("No. Instalación")){
                ExcelHelper.ArreglarColumnasNumber(styleEntero, nombreColumna, numeroInicial,numberOfRows, sheet,i);   
            }  
            
            if(nombreColumna.equals("Teléfono")){
               ExcelHelper.ArreglarColumnasNumber(styleEntero, nombreColumna, numeroInicial,numberOfRows, sheet,i);   
            }    
             
        }
    
    }

}
