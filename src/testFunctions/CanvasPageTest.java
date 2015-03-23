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
			}else if(row.getCell(2).toString().equals("courseCount")){
				try{
					String tcNum = row.getCell(0).toString();
					int exp = (int) row.getCell(3).getNumericCellValue()+1;
					try{
						dr.findElement(By.xpath("html/body/div[1]/div[2]/table/tbody/tr["+exp+"]/td[2]")).getText();
						Object[] valueToWrite = new Object[]{tcNum,"courseCount","Fail","<=2",">2"};
						wE.writeExcel(resultFilePath, "result", valueToWrite);
					}catch (Exception e){
						Object[] valueToWrite = new Object[]{tcNum,"courseCount","Pass","<=2","<=2"};
						wE.writeExcel(resultFilePath, "result", valueToWrite);	}
				}catch (Exception e) {e.printStackTrace();}
			}else if(row.getCell(2).toString().equals("checkCourseData")){
				String tcNum = row.getCell(0).toString();
				Sheet courseList = rE.readSheet(filePath, "FunctionalTestCaseMatrix.xlsx", "courseTableTestData");
				int testCount = courseList.getLastRowNum();
				int rowCounter=0;
				for(int a=1; a<=itr; a++){
					for(int k=1; k<=2;k++){	
						try{
							rowCounter=rowCounter+1;
							if(rowCounter<=testCount){
								Row x = courseList.getRow(rowCounter);
								String expID = x.getCell(1).toString();
								String expName = x.getCell(2).toString();
								String expDesc = x.getCell(3).toString();
								String actID = dr.findElement(By.xpath("html/body/div[1]/div[2]/table/tbody/tr["+k+"]/td[2]")).getText();
								String actName = dr.findElement(By.xpath("html/body/div[1]/div[2]/table/tbody/tr["+k+"]/td[3]")).getText();
								String actDesc = dr.findElement(By.xpath("html/body/div[1]/div[2]/table/tbody/tr["+k+"]/td[4]")).getText();
								if(!actID.equals(expID)){
									Object[] valueToWrite = new Object[]{tcNum,"checkCourseData","Fail",expID,actID,"Course result page: "+a+", Course #: "+k};
									wE.writeExcel(resultFilePath, "result", valueToWrite);
								}if(!actName.equals(expName)){
									Object[] valueToWrite = new Object[]{tcNum,"checkCourseData","Fail",expName,actName,"Course result page: "+a+", Course #: "+k};
									wE.writeExcel(resultFilePath, "result", valueToWrite);
								}if(!actDesc.equals(expDesc)){
									Object[] valueToWrite = new Object[]{tcNum,"checkCourseData","Fail",expDesc,actDesc,"Course result page: "+a+", Course #: "+k};
									wE.writeExcel(resultFilePath, "result", valueToWrite);	
								}else{
									Object[] valueToWrite = new Object[]{tcNum,"checkCourseData","Pass","","","Course result page: "+a+", Course #: "+k};
									wE.writeExcel(resultFilePath, "result", valueToWrite);
								}
							}
						}catch (Exception e) {e.printStackTrace();}
					}
					//Footer Validation
					if(a==1){
						boolean prev = dr.findElement(By.xpath("html/body/div[1]/div[3]/div/button[2]")).isEnabled();
						if(prev=false){
							Object[] valueToWrite = new Object[]{"TC005","checkPrevButton","Fail","Prev button Disabled","Enabled","Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}else{
							Object[] valueToWrite = new Object[]{"TC005","checkPrevButton","Pass","Prev button Disabled","Disabled","Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}
						boolean next = dr.findElement(By.xpath("html/body/div[1]/div[3]/div/button[3]")).isEnabled();
						if(next=false){
							Object[] valueToWrite = new Object[]{"TC006","checkNextButton","Fail","Next button Enabled","Disabled","Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}else{
							Object[] valueToWrite = new Object[]{"TC006","checkNextButton","Pass","Next button Enabled","Enabled","Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}
						String curPage = "Current Page - "+a;
						String actPage = dr.findElement(By.xpath("html/body/div[1]/div[3]/div/span")).getText();
						if(!curPage.equals(actPage)){
							Object[] valueToWrite = new Object[]{"TC007","checkPageNumber","Fail",curPage,actPage,"Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}else{
							Object[] valueToWrite = new Object[]{"TC007","checkPageNumber","Pass",curPage,actPage,"Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}
					}
					//Test Prev & Next buttons in the middle pages (Not 1st & Last page)
					if(a>1&&a<itr){
						boolean prev = dr.findElement(By.xpath("html/body/div[1]/div[3]/div/button[2]")).isEnabled();
						if(prev=false){
							Object[] valueToWrite = new Object[]{"TC005","checkPrevButton","Fail","Prev button Enabled","Disabled","Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}else{
							Object[] valueToWrite = new Object[]{"TC005","checkPrevButton","Pass","Prev button Enabled","Enabled","Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}
						boolean next = dr.findElement(By.xpath("html/body/div[1]/div[3]/div/button[3]")).isEnabled();
						if(next=false){
							Object[] valueToWrite = new Object[]{"TC006","checkNextButton","Fail","Next button Enabled","Disabled","Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}else{
							Object[] valueToWrite = new Object[]{"TC006","checkNextButton","Pass","Next button Enabled","Enabled","Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}
						String curPage = "Current Page - "+a;
						String actPage = dr.findElement(By.xpath("html/body/div[1]/div[3]/div/span")).getText();
						if(!curPage.equals(actPage)){
							Object[] valueToWrite = new Object[]{"TC007","checkPageNumber","Fail",curPage,actPage,"Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}else{
							Object[] valueToWrite = new Object[]{"TC007","checkPageNumber","Pass",curPage,actPage,"Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}
					}
					if(a==itr){		//Test the Next & Prev buttons on last page
						boolean prev = dr.findElement(By.xpath("html/body/div[1]/div[3]/div/button[2]")).isEnabled();
						if(prev=true){
							Object[] valueToWrite = new Object[]{"TC005","checkPrevButton","Pass","Prev button Enabled","Enabled","Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}else{
							Object[] valueToWrite = new Object[]{"TC005","checkPrevButton","Fail","Prev button Enabled","Disabled","Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}
						boolean next = dr.findElement(By.xpath("html/body/div[1]/div[3]/div/button[3]")).isEnabled();
						if(next=true){
							Object[] valueToWrite = new Object[]{"TC006","checkNextButton","Pass","Next button Disabled","Disabled","Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}else{
							Object[] valueToWrite = new Object[]{"TC006","checkNextButton","Fail","Next button Disabled","Enabled","Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}
						String curPage = "Current Page - "+a;
						String actPage = dr.findElement(By.xpath("html/body/div[1]/div[3]/div/span")).getText();
						if(!curPage.equals(actPage)){
							Object[] valueToWrite = new Object[]{"TC007","checkPageNumber","Fail",curPage,actPage,"Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}else{
							Object[] valueToWrite = new Object[]{"TC007","checkPageNumber","Pass",curPage,actPage,"Course result page: "+a};
							wE.writeExcel(resultFilePath, "result", valueToWrite);
						}
					}
					//Navigate to next page
					dr.findElement(By.xpath("html/body/div[1]/div[3]/div/button[3]")).click();
					try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		}
	}
}
