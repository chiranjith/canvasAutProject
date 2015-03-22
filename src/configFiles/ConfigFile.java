package configFiles;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.ReadExcel;
public class ConfigFile {
	public WebDriver dr;
	public WebDriver browserConfig(String filePath, String fileName, String sheetName)throws IOException{
		ReadExcel rE = new ReadExcel();
		Sheet dataSheet = rE.readSheet(filePath, fileName, sheetName);
		Row configRow = dataSheet.getRow(3);
		System.out.println("Browser selected is: "+configRow.getCell(2).getStringCellValue());
		if (configRow.getCell(2).getStringCellValue().equals("Firefox")){
			dr = new FirefoxDriver();
		}else if(configRow.getCell(2).getStringCellValue().equals("IE")){
			System.out.println("Not coded for IE browser");
			dr = new FirefoxDriver();
		}
		//Get environment details
		Row envRow = dataSheet.getRow(8);
		dr.get(envRow.getCell(2).getStringCellValue());
		System.out.println("Env selected is: "+envRow.getCell(2).getStringCellValue());
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return dr;
	}
}
