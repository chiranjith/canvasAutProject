package utilities;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
public class CopyResultFile {
	public String copyFile(String filePath, String fileName, String sheetName) throws IOException{
		String destFilePath = null;
		Date dNow = new Date();
		SimpleDateFormat dF = new SimpleDateFormat("_d-MMM-y_hh-mm-ss'.xlsx'");
		String fileExt = dF.format(dNow).toString();
		ReadExcel rE = new ReadExcel();
		Sheet rS = rE.readSheet(filePath, fileName, sheetName);
		String tempFile = rS.getRow(18).getCell(2).getStringCellValue();
		String resFile = rS.getRow(19).getCell(2).getStringCellValue().concat(fileExt);
		File srcFile = new File(tempFile);
		System.out.println("Test Result Template File: "+tempFile);
		System.out.println("Result file location: "+resFile);
		File destFile =new File(resFile);
		FileUtils.copyFile(srcFile, destFile);
		destFilePath=destFile.getAbsolutePath();
		return destFilePath;
	}
}
