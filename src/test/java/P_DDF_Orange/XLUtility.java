package P_DDF_Orange;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility { 
	
	public FileInputStream file; 
	public XSSFWorkbook workbook; 
	public XSSFSheet sheet; 
	public XSSFRow rowcount; 
	public XSSFCell cellcount; 
	String path = null; 
	
	public XLUtility (String path) {
		
		this.path=path; 
	} 
	
	public int getRowCount(String sheetname) throws IOException { 
		
		file = new FileInputStream(path); 
		workbook = new XSSFWorkbook(sheetname); 
		sheet = workbook.getSheet(sheetname); 
		int rowcount = sheet.getLastRowNum(); 
		workbook.close(); 
		file.close(); 
		return rowcount; 
		
	} 
	
	public int getCellCount(String sheetname, int rowcount) throws IOException { 
		
		file = new FileInputStream(path); 
		workbook = new XSSFWorkbook(file); 
		sheet = workbook.getSheet(sheetname); 
		rowcount = sheet.getLastRowNum(); 
		int cellcount = sheet.getRow(rowcount).getLastCellNum(); 
		workbook.close(); 
		file.close(); 
		return cellcount;
		
	} 
	
	public String getCellValue(String sheetname, int rowcount, int cellcount) throws IOException {
		
		file = new FileInputStream(path); 
		workbook = new XSSFWorkbook(file); 
		sheet = workbook.getSheet(sheetname); 
		rowcount = sheet.getLastRowNum(); 
		sheet.getRow(rowcount).getLastCellNum(); 
		XSSFRow currentrow = sheet.getRow(rowcount);  
		currentrow.getCell(cellcount); 
		
		DataFormatter formatter = new DataFormatter(); 
		
		String data; 
		try 
		{
			data = formatter.formatCellValue((Cell) currentrow);
		} 
		
		catch(Exception e) 
		{
			data= "";		
			
		}
		
		workbook.close(); 
		file.close(); 
		return data;
	}

}
