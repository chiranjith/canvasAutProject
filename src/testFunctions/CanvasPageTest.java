package testFunctions;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.ReadExcel;
import utilities.WriteExcel;

public class CanvasPageTest {
	public static void canvasTest(WebDriver dr, String resultFilePath, int itr) throws IOException{
		String filePath = "C:\\Selenium\\Instructure\\src\\testCase\\";
		ReadExcel rE = new ReadExcel();
		Sheet tcSheet = rE.readSheet(filePath, "FunctionalTestCaseMatrix.xlsx", "TC");
		WriteExcel wE = new WriteExcel();
		int rowCount = tcSheet.getLastRowNum();
		for(int i=2; i<rowCount;i++){
			Row row = tcSheet.getRow(i);
			if(row.getCell(2).toString().equals("pageHeader")){
				try{
					String tcNum = row.getCell(0).toString();
					String expValue = row.getCell(3).toString();
					String actValue = dr.findElement(By.xpath("html/body/div[1]/div[1]/h1")).getText();
					if(actValue.equals(expValue)){
						System.out.println(dr.findElement(By.xpath("html/body/div[1]/div[1]/h1")).getText());
						Object[] valueToWrite = new Object[]{tcNum,"pageHeader","Pass",expValue,actValue};
						wE.writeExcel(resultFilePath, "result", valueToWrite);
					}else{
						Object[] valueToWrite = new Object[]{tcNum,"pageHeader","Fail",expValue,actValue};
						wE.writeExcel(resultFilePath, "result", valueToWrite);	}
				}catch (Exception e) {e.printStackTrace();}
			}else if(row.getCell(2).toString().equals("tableHeader")){
				try{
					String tcNum = row.getCell(0).toString();
					Sheet tcSheetHeader = rE.readSheet(filePath, "FunctionalTestCaseMatrix.xlsx", "tableHeaderData");
					int headerCount = tcSheetHeader.getLastRowNum();
					for(int j=1; j<=headerCount;j++){
					Row headerRow = tcSheetHeader.getRow(j);
						String exp = headerRow.getCell(0).toString();
						String act = dr.findElement(By.xpath("html/body/div[1]/div[2]/table/thead/tr/th["+j+"]")).getText();
						if(!exp.equals(act)){
							Object[] valueToWrite = new Object[]{tcNum,"tableHeader","Fail",exp,act};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}else{
							Object[] valueToWrite = new Object[]{tcNum,"tableHeader","Pass",exp,act};
							wE.writeExcel(resultFilePath, "result", valueToWrite);	}
					}
				}catch (Exception e) {e.printStackTrace();}
			}
		}
		
	}
}
