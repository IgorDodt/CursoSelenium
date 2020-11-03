package br.ce.igordodt.test;
import static br.ce.igordodt.core.DriverFactory.getDriver;
import static br.ce.igordodt.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.igordodt.core.DSL;

public class TesteAjax {
	
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
	public void testAjax(){
		dsl.escrever("j_idt85:name", "Teste");
		dsl.clicarBotao("j_idt85:j_idt88");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
//		wait.until(ExpectedConditions.textToBe(By.id("j_idt85:display"), "Teste"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt98")));
		Assert.assertEquals("Teste", dsl.obterTexto("j_idt85:display"));
	}
}
