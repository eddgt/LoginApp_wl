package org.bdg.cms_vista;

/**
 *
 * @author oulloa
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.text.*;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.bdg.cms_conexion.Conexion;
import org.bdg.session.BaseSession;

@ManagedBean(name = "excelReport")
@ViewScoped
public class ExcelWriter extends BaseSession {
  
 private static HashMap stylesMap = new HashMap(); 
  
 static {
  stylesMap = new HashMap();
   
 }
  
 //public static void main(String [] args){
public void executeReport(){
   
  try {
   //Class.forName("oracle.jdbc.driver.OracleDriver");
   //Connection con = null;
   //con = DriverManager.getConnection("jdbc:oracle:thin:@Database name:port number:environment code",
          //"username", "password");
   
   Connection conex = null;
   Conexion conec = new Conexion();                
   conex = conec.getConexion2();
   
   PreparedStatement pstmt = conex.prepareStatement("SELECT cod_cliente columna1, sistema columna2, cliente_mic columna3, fecha columna4 FROM tbbo_operaciones WHERE rownum<11");
   ResultSet rs = pstmt.executeQuery();
   ArrayList list = new ArrayList();
    
   while(rs.next()){
    idBean idB = new idBean();
    idB.setColumn1(rs.getInt("columna1")) ;
    idB.setColumn2(rs.getInt("columna2"));
    idB.setColumn3(rs.getString("columna3"));
    idB.setColumn4(rs.getDate("columna4"));
    list.add(idB);    
   }
   generateExcel(list);
    
  } catch (SQLException e) {
  }
   
 }
 public static void generateExcel(ArrayList results) {
  HSSFWorkbook l_workBook_out = new HSSFWorkbook();
  HSSFSheet sheet = l_workBook_out.createSheet("Records");
   
  Iterator Itr = results.iterator();
  HSSFRow row;
  int rowCounter = 0;
  sheet.setDefaultRowHeightInPoints(5000);
   
  sheet.setColumnWidth((short)1, (short)3000); //S no
  sheet.setColumnWidth((short)1, (short)3000);//Date (If you want current date to be displayed)
  sheet.setColumnWidth((short)2, (short)4000); //column1
  sheet.setColumnWidth((short)3, (short)4000); //column2
  sheet.setColumnWidth((short)4, (short)4000); //column3
  sheet.setColumnWidth((short)5, (short)4000); //column4
     
  HSSFCellStyle cellStyle = l_workBook_out.createCellStyle();
  cellStyle.setWrapText(true);
     
  //Create Font
  HSSFFont l_font = l_workBook_out.createFont();
  l_font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
   
  while(Itr.hasNext()) {
    
   idBean idB = (idBean)Itr.next();
    
   if(results == null) {
    System.out.println("No results for the given query");
    continue;
   }
   row = sheet.createRow(rowCounter);
   row.setHeightInPoints((float)38.25);
   int cellCounter = 0;
   HSSFCell cell;
    
   //S no   
   cell = row.createCell( cellCounter);
   cell.setCellValue(String.valueOf(rowCounter+1));
    
   //Date (optional)
   cellCounter++;
   Date d = new Date();
   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
   String date = sdf.format(d);
   cell = row.createCell(cellCounter);
   cell.setCellValue(date);
    
   //column1
   cellCounter++;
   cell = row.createCell( cellCounter);
   cell.setCellValue(String.valueOf(checkForNull(Integer.valueOf(idB.getColumn1()))));   
     
   //column2
   cellCounter++;
   cell = row.createCell( cellCounter);   
   cell.setCellValue(String.valueOf(checkForNull(Integer.valueOf(idB.getColumn2()))));
    
   //column3
   cellCounter++;
   cell = row.createCell( cellCounter);
   cell.setCellValue(String.valueOf(checkForNull(idB.getColumn3())));
    
   //column4
   cellCounter++;
   cell = row.createCell( cellCounter);
   cell.setCellValue(String.valueOf(checkForNull(idB.getColumn4())));
       
   rowCounter++;
  }
  writeExcel(l_workBook_out); 
 } 
  
 private static void writeExcel(HSSFWorkbook l_workBook_out) {
  String l_str_file_out = "C:\\Users\\oulloa\\Documents\\results.xls"; //Give the location suitable to your requirement
  FileOutputStream fileOut;
  try {
   fileOut = new FileOutputStream(l_str_file_out);
   l_workBook_out.write(fileOut);
   fileOut.close();
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
  
 private static Object checkForNull(Object obj) {
  if(obj == null) {
   return "";
  }
  else {
   return obj;
  }
 }  
  }
 
class idBean{
  
 private int column1;
 private int column2;
 private String column3;
 private Date column4;
  
 public int getColumn1(){
  return column1;
 }
  
 public void setColumn1(int i){
  this.column1 = column1;
 }
  
 public int getColumn2(){
  return column2;
 }
  
 public void setColumn2(int alrt_id){
  this.column2 = column2;
 }
  
 public String getColumn3(){
  return column3;
 }
  
 public void setColumn3(String vnder){
  this.column3 = column3;
 }
  
 public Date getColumn4(){
  return column4;
 }
  
 public void setColumn4(Date cst_alrt_dt){
  this.column4 = column4;
 } 
}