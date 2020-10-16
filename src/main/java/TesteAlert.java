import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {
	
private WebDriver driver;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200,765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After
	public void shutdown() {
		driver.quit();
	}
	
	@Test
	public void testeAlertSimples() {		
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		Assert.assertEquals("Alert Simples", text);
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(text);
	}
}
