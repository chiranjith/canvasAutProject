package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class WriteExcel {
	@SuppressWarnings("resource")
	public Row writeExcel(String resultFilePath, String sheetName, Object[] valueToWrite)throws IOException{
		File file = new File(resultFilePath);
		FileInputStream inStream = new FileInputStream(file);
		Workbook workBook = null;
		workBook = new XSSFWorkbook(inStream);
		Sheet sheet = workBook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		Row newRow = sheet.createRow(rowCount+1);
		//Fill data in row
		for(int i=0; i<valueToWrite.length; i++){
			Cell cell = newRow.createCell(i);
			cell.setCellValue(valueToWrite[i].toString());
		}
		inStream.close();
		FileOutputStream outStream = new FileOutputStream(resultFilePath);
		workBook.write(outStream);
		outStream.close();
		return newRow;
	}
}
