package mainDriver;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import testFunctions.CanvasPageTest;
import testFunctions.CanvasTestIteration;
public class TestExecute {
	public static void testFunctions(WebDriver dr, String resultFilePath) throws IOException{
		System.out.println("Inside TestExecute");
		int itr = CanvasTestIteration.iterations(resultFilePath);
		System.out.println("# of times the test cases will be run: "+itr);
		CanvasPageTest.canvasTest(dr, resultFilePath, itr);
	}
}
