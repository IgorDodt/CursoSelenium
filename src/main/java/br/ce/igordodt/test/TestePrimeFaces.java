package br.ce.igordodt.test;
import static br.ce.igordodt.core.DriverFactory.getDriver;
import static br.ce.igordodt.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.ce.igordodt.core.DSL;

public class TestePrimeFaces {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}

	@After
	public void shutdown() {
		killDriver();
	}
	
	@Test
	public void deveIteragirRadio() {
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl.clicarRadio(By.xpath("//input[@id='j_idt728:console:0']/../..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt728:console:0"));
		
		dsl.clicarRadio(By.xpath("//label[.='PS4']/..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt728:console:1"));
	}
	
	@Test
	public void deveIteragirCombo() {
		driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		
		dsl.selecionarComboPrime("j_idt728:console", "Xbox One");		
		Assert.assertEquals("Xbox One", dsl.obterTexto("j_idt728:console_label"));
	}

}
