import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteRegrasCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	private String dirCasa = "C:\\Program Files (x86)\\ChromeDriver\\chromedriver.exe";
	private String dirTrab = "C:\\Program Files (x86)\\Google\\chromedriver.exe";
	
	@Parameter
	private String nome;
	@Parameter(value=1)
	private String sobrenome;
	@Parameter(value=2)
	private String sexo;
	@Parameter(value=3)
	private List<String> comidas;
	@Parameter(value=4)
	private String[] esportes;
	@Parameter(value=5)
	private String msg;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver", dirTrab);
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200,765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void shutdown() {
		driver.quit();
	}
	
	@Parameters
	public static Collection<Object[]> getCollections(){
		return Arrays.asList(new Object[][]{
			
		});
	}
	
	@Test
	public void deveValidarRegras(){
		page.setNome(nome);
		page.setSobreNome(sobrenome);
		
		if(sexo.equals("Masculino")) {
			page.setSexoMasculino();
		}else {
			page.setSexoFeminino(); 
		}
		
		if(comidas.contains("Carne")) page.setComidaCarne();
		if(comidas.contains("Pizza")) page.setComidaPizza();
		if(comidas.contains("Vegetariano")) page.setComidaVegetariano();
		
		page.setEsporte(esportes);
		page.cadastrar();
		Assert.assertEquals(msg, dsl.alertaObterTextoEAceita());
	}
}
