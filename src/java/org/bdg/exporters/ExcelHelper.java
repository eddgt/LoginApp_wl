/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.exporters;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Admin_bdgsa
 */
public class ExcelHelper {
    
    public static void ArreglarColumnasNumber(HSSFCellStyle style, String nombreColumna,
            int lineaInicio, int numeroFilas, HSSFSheet sheet, int iteracion){
       
        
        for(int fil=lineaInicio; fil<numeroFilas ; fil++){
            HSSFCell celdaCuota = sheet.getRow(fil).getCell(iteracion);
            if(celdaCuota!=null){
                if(celdaCuota.getStringCellValue()!=null){
                     String cuo= celdaCuota.getStringCellValue();
                    if(!cuo.equals("")){
                        celdaCuota.setCellStyle(style);
                        celdaCuota.setCellValue(Float.parseFloat(cuo));
                    }
                }               
            }           
        }                
    }
    
    
    public static void RepetirValorEnColumna(HSSFSheet sheet,String valor, int numeroColumna, int numeroFila, int numeroInicial){
        for (int row = numeroInicial; row < numeroFila-1; row++) {
            Row fila = sheet.getRow(row);
            Cell newCell = fila.createCell(numeroColumna, Cell.CELL_TYPE_STRING);
            newCell.setCellValue(valor);         
        }
    }
    
    public static void InsertarNuevaColumna(HSSFSheet sheet, int numeroFilas, int numeroColumnas, int numeroInicial, int noColumnaACrear){
        int filaError=0;
        int colError=0;
        
        try{
            for (int row = numeroInicial; row < numeroFilas; row++) {
            Row fila = sheet.getRow(row);
            filaError = row;
                        
            // shift to right
            for (int col = numeroColumnas; col > noColumnaACrear; col--) {
                    //Celda original
                    colError = col;
                    Cell celdaOriginal = fila.getCell(col);
                    Cell celdaIzquierda = fila.getCell(col - 1);
                    
                    if (celdaOriginal != null) {
                        fila.removeCell(celdaOriginal); //Remueve la celda original la deja vacia
                    }
                    //Celda con valor izquierdo
                    

                    if (celdaIzquierda != null) {
                       
                        Cell newCell = fila.createCell(col, celdaIzquierda.getCellType());
                        cloneCell(newCell, celdaIzquierda);  
                        fila.removeCell(celdaIzquierda);
                    }
                    
                    //if(celdaOriginal != null){
                        /*Cell newCellO = fila.createCell((col-1), celdaOriginal.getCellType());
                        fila.removeCell(celdaOriginal);*/
                        //cloneCell(newCellO, celdaIzquierda);   
                    //}
                }            
            }
        }catch(Exception e){
            int i = filaError;
            int c = colError;
            String error ="Error en la fila y columna" ;
        }        
    }
    
    private static void cloneCell(Cell cNew, Cell cOld) {
        cNew.setCellComment(cOld.getCellComment());
        cNew.setCellStyle(cOld.getCellStyle());

        switch (cOld.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN: {
                cNew.setCellValue(cOld.getBooleanCellValue());
                break;
            }
            case Cell.CELL_TYPE_NUMERIC: {
                cNew.setCellValue(cOld.getNumericCellValue());
                break;
            }
            case Cell.CELL_TYPE_STRING: {
                cNew.setCellValue(cOld.getStringCellValue());
                break;
            }
            case Cell.CELL_TYPE_ERROR: {
                cNew.setCellValue(cOld.getErrorCellValue());
                break;
            }
            case Cell.CELL_TYPE_FORMULA: {
                cNew.setCellFormula(cOld.getCellFormula());
                break;
            }
        }
}
    
    
}
