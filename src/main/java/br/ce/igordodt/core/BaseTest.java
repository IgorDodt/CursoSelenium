package br.ce.igordodt.core;

import static br.ce.igordodt.core.DriverFactory.getDriver;
import static br.ce.igordodt.core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {
	
	@Rule
	public TestName testname = new TestName();
	
	@After
	public void shutdown() throws IOException {
		TakesScreenshot ss = (TakesScreenshot) getDriver();
		File arquivo = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo,new File("target" +File.separator + "screenshot" + File.separator + testname.getMethodName() + ".jpg"));
		
		
		if(Properties.FECHAR_BROWSER) {
		killDriver();
		}
	}
}
