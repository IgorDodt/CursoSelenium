package br.ce.igordodt.test;
import static br.ce.igordodt.core.DriverFactory.getDriver;
import static br.ce.igordodt.core.DriverFactory.killDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.igordodt.core.DSL;

public class TesteSincronismo {
	
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
	public void devetilizarEsperaFixa() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000);
		dsl.escrever("novoCampo", "Tititi");
	}
	
	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException {
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		dsl.clicarBotao("buttonDelay");
		dsl.escrever("novoCampo", "Tititi");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	@Test
	public void deveUtilizarEsperaExplicita() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(),30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escrever("novoCampo", "Tititi");
	}
}