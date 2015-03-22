package testFunctions;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Sheet;
import utilities.ReadExcel;
public class CanvasTestIteration {
	public static int iterations(String resultFilePath) throws IOException{
		String filePath = "C:\\Selenium\\Instructure\\src\\testCase\\";
		ReadExcel rE = new ReadExcel();
		Sheet testData = rE.readSheet(filePath, "FunctionalTestCaseMatrix.xlsx","courseTableTestData");
		int iteration = testData.getLastRowNum()/2+1;
		return iteration;
	}
}
