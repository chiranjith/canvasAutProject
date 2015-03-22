package mainDriver;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import utilities.CopyResultFile;
import configFiles.ConfigFile;
public class DriverFile {
	public static void main(String[] args) throws IOException {
		ConfigFile cF = new ConfigFile();
		String filePath = "C:\\Selenium\\canvasAutProject\\src\\configFiles";
		//Passing parameters to browserConfig() & catching the WebDriver dr handle.
		WebDriver dr = cF.browserConfig(filePath, "config.xlsx","config");
		//Calling copy file method to copy the result template to result folder
		CopyResultFile cRF = new CopyResultFile();
		String resultFilePath = cRF.copyFile(filePath, "config.xlsx","config");
		TestExecute.testFunctions(dr, resultFilePath);
		dr.close();
	}
}
