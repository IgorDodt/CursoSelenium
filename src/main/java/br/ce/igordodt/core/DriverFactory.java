package br.ce.igordodt.core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	private static WebDriver driver;
	private static String dirCasa = "C:\\Program Files (x86)\\ChromeDriver\\chromedriver.exe";
	private static String dirTrab = "C:\\Program Files (x86)\\Google\\chromedriver.exe";

	private DriverFactory() {
	}

	public static WebDriver getDriver() {
		if (driver == null) {

			switch (Properties.browser) {
			case FIREFOX: driver = new FirefoxDriver();
				break;
			case CHROME:
				System.setProperty("webdriver.chrome.driver", dirCasa);
				driver = new ChromeDriver();
				break;
			}

			
			driver.manage().window().setSize(new Dimension(1200, 765));
		}
		return driver;
	}

	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}