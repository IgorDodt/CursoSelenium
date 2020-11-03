package br.ce.igordodt.test;
import static br.ce.igordodt.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.igordodt.core.BaseTest;
import br.ce.igordodt.core.DSL;
import br.ce.igordodt.page.CampoTreinamentoPage;

public class TesteCadastro extends BaseTest {
	
	private CampoTreinamentoPage page;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();
		dsl = new DSL();
	}	

	@Test
	public void deveRealizarCadastroComSucesso(){
		page.setNome("Igor");
		page.setSobreNome("Dodt");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Mestrado");
		page.setEsporte("Natacao");
		page.cadastrar();
		
		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals("Igor", page.obterNomeCadastro());
		Assert.assertEquals("Dodt", page.obterSobrenomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Pizza", page.obterComidaCadastro());
		Assert.assertEquals("mestrado", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Natacao", page.obterEsporteCadastro());
	}
	
	@Test
	public void deveValidarNomeObrigatorio(){
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio(){
		page.setNome("Igor");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarSexoObrigatorio(){
		page.setNome("Igor");
		page.setSobreNome("Dodt");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarComidaVegetariana(){
		page.setNome("Igor");
		page.setSobreNome("Dodt");
		page.setSexoFeminino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarEsportistaIndeciso(){
		page.setNome("Igor");
		page.setSobreNome("Dodt");
		page.setSexoFeminino();
		page.setComidaCarne();
		page.setEsporte("Karate", "O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
	}
}
