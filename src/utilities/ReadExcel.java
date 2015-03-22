package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ReadExcel {
	public Sheet dataSheet;
	@SuppressWarnings("resource")
	public Sheet readSheet(String filePath, String fileName, String sheetName) throws IOException{
			File file = new File(filePath+"\\"+fileName);
			FileInputStream inStream = new FileInputStream(file);
			Workbook wB = null;
			String fileExt = fileName.substring(fileName.indexOf("."));
			if(fileExt.equals(".xlsx")){
				wB = new XSSFWorkbook(inStream);		}
			else if(fileExt.equals(".xls")){
				wB = new HSSFWorkbook(inStream);		}
			Sheet dataSheet = wB.getSheet(sheetName);
		return dataSheet;
	}
}
