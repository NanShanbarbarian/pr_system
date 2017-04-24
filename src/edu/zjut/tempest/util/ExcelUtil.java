package edu.zjut.tempest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelUtil {

	private static InputStream read = null;
	private static OutputStream write = null;
	
	/**
	 * getWorkbook   判断Excel版本，获取Workbook
	 * @param file
	 * @return
	 */
	public static Workbook getWorkbook(File file, String fileName) throws Exception{
		Workbook wb = null;
		
		if(file == null || file.equals("")) {
			System.out.println("文件不能为空");
			throw new Exception();
		}
		
		try {
			read = new FileInputStream(file);
		} catch(FileNotFoundException e) {
			System.out.println("文件路径不正确！");
			e.printStackTrace();
		}
		try {
			if(fileName.endsWith(".xls")) {   //Excel 2003
				wb = new HSSFWorkbook(read);
			} else if(fileName.endsWith(".xlsx")) {   //Excel 2007/2010
				wb = new XSSFWorkbook(read);
			} else {
				System.out.println("文件格式不正确！");
				throw new Exception();
			}
		} catch(IOException e) {
			System.out.println("文件已损坏或未知原因！");
			e.printStackTrace();
		}
		
		return wb;
	}
	
	public static List<Map<String, Object>> readExcel(Workbook wb) {
		List<Map<String, Object>> list = null;
		if(wb instanceof HSSFWorkbook) {
			list = readExcel2003((HSSFWorkbook)wb);
		} else if(wb instanceof XSSFWorkbook) {
			list = readExcel2007((XSSFWorkbook)wb);
		}
		return list;
	}
	
	/**
	 * readExcel2003   读取2003版本的Excel
	 * @return
	 */
	public static List<Map<String, Object>> readExcel2003(HSSFWorkbook wb) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		//获得表里的第一个工作薄
		HSSFSheet sheet = wb.getSheetAt(0);
		int rowNum = getRealRowNum2003(sheet);
		for(int i=1; i<=rowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			
			for(int j=0; j<sheet.getRow(0).getPhysicalNumberOfCells(); j++) {
				map.put(ExcelUtil.getCellValue2003(sheet.getRow(0), j), ExcelUtil.getCellValue2003(row, j));
			
			list.add(map);
			}
		}
		return list;
	}
	
	/**
	 * readExcel2007   读取2007版本以上的Excel
	 * @return
	 */
	public static List<Map<String, Object>> readExcel2007(XSSFWorkbook wb) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		//获得表里的第一个工作薄
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowNum = getRealRowNum2007(sheet); 
		
		System.out.println(rowNum);
		for(int i=1; i<=rowNum ; i++) {
			XSSFRow row = sheet.getRow(i);
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			
			for(int j=0; j<sheet.getRow(0).getPhysicalNumberOfCells(); j++) {
				map.put(ExcelUtil.getCellValue2007(sheet.getRow(0), j), ExcelUtil.getCellValue2007(row, j));
			}
			list.add(map);
		}
		return list;
	}
	
	/**
	 * getCellVaule2003   获得2003版本Excel的单元格
	 * @param row
	 * @param index
	 * @return
	 */
	public static String getCellValue2003(HSSFRow row, int index) {
		String value = "";  
        try {  
            HSSFCell cell = row.getCell((short)index);  
            value = (String) getCellValue(cell);  
        } catch (RuntimeException e) {  
        	e.printStackTrace();
        }  
        
        return value;  
	}
	
	/**
	 * getCellValue2007 获得2007以上版本Excel的单元格
	 * @param row
	 * @param index
	 * @return
	 */
	public static String getCellValue2007(XSSFRow row, int index) {
		String value = "";
		try {
			XSSFCell cell = row.getCell((short)index);
			value = (String) getCellValue(cell);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
	private static String getCellValue(Cell cell) { 
		String value = null;  
        //简单的查检列类型  
		if(cell != null) {
			switch(cell.getCellType())  {  
			
			case Cell.CELL_TYPE_STRING://字符串  
			System.out.println("Cell.CELL_TYPE_STRING:" + cell.getRichStringCellValue().toString());  
			value = cell.getRichStringCellValue().toString();  
			break;  
			case Cell.CELL_TYPE_NUMERIC://数字  
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					
					value =  sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
				} else {
					long dd = (long)cell.getNumericCellValue();  
					value = dd+"";  
				}
				System.out.println("Cell.CELL_TYPE_NUMERIC:"+cell.getNumericCellValue());  
				break;  
			case Cell.CELL_TYPE_BLANK:  
				System.out.println("Cell.CELL_TYPE_BLANK:");  
				value = "";  
				break;     
			case Cell.CELL_TYPE_FORMULA:  
				System.out.println("Cell.CELL_TYPE_FORMULA:"+String.valueOf(cell.getCellFormula()));  
				value = String.valueOf(cell.getCellFormula());  
				break;  
			case Cell.CELL_TYPE_BOOLEAN://boolean型值  
				System.out.println("Cell.CELL_TYPE_BOOLEAN:"+String.valueOf(cell.getBooleanCellValue()));  
				value = String.valueOf(cell.getBooleanCellValue());  
				break;  
			case Cell.CELL_TYPE_ERROR:  
				System.out.println("Cell.CELL_TYPE_ERROR:"+Cell.CELL_TYPE_ERROR);  
				value = String.valueOf(cell.getErrorCellValue());  
				break;  
			default:  
				System.out.println("default");  
				break;  
			}  
		}
        return value;  
    }
	
	/**
	 * getRealRowNum2003   获取真实的行数
	 * @param sheet
	 * @return
	 */
	private static int getRealRowNum2003(HSSFSheet sheet) {
	    CellReference cellReference = new CellReference("A4");  
        boolean flag = false;  
        for (int i = cellReference.getRow(); i <= sheet.getLastRowNum();) {  
            Row r = sheet.getRow(i);  
            if(r == null){  
                // 如果是空行（即没有任何数据、格式），直接把它以下的数据往上移动  
                sheet.shiftRows(i+1, sheet.getLastRowNum(),-1);  
                continue;  
            }  
            flag = false;  
            for(Cell c:r){  
                if(c.getCellType() != Cell.CELL_TYPE_BLANK){  
            		flag = true;  
            		break;  
                } 
            }  
            if(flag){  
                i++;  
                continue;  
            } else{//如果是空白行（即可能没有数据，但是有一定格式）  
                if(i == sheet.getLastRowNum()) { //如果到了最后一行，直接将那一行remove掉  
                	sheet.removeRow(r);  
                } else { //如果还没到最后一行，则数据往上移一行  
                	
                	sheet.shiftRows(i+1, sheet.getLastRowNum(),-1);  
                }
            }  
        }  
        return sheet.getLastRowNum();
	}
	
	/**
	 * getRealRowNum2007   获取真实的行数
	 * @param sheet
	 * @return
	 */
	private static int getRealRowNum2007(XSSFSheet sheet) {
	    CellReference cellReference = new CellReference("A4");  
        boolean flag = false;  
        for (int i = cellReference.getRow(); i <= sheet.getLastRowNum();) {  
            Row r = sheet.getRow(i);  
            if(r == null){  
                // 如果是空行（即没有任何数据、格式），直接把它以下的数据往上移动  
                sheet.shiftRows(i+1, sheet.getLastRowNum(),-1);  
                continue;  
            }  
            flag = false;  
            for(Cell c:r){  
                if(c.getCellType() != Cell.CELL_TYPE_BLANK){  
                    flag = true;  
                    break;  
                }  
            }  
            if(flag){  
                i++;  
                continue;  
            }  
            else{//如果是空白行（即可能没有数据，但是有一定格式）  
            	if(i == sheet.getLastRowNum()) { //如果到了最后一行，直接将那一行remove掉  
                	sheet.removeRow(r);  
                } else { //如果还没到最后一行，则数据往上移一行  
                	
                	sheet.shiftRows(i+1, sheet.getLastRowNum(),-1);  
                }
            }  
        }  
        return sheet.getLastRowNum();
	}
	/**
	 * freeIO   释放IO流
	 */
	public static void freeIO() {
		try {
			if(read != null) {
				read.close();
			}
			if(write != null) {
				write.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
