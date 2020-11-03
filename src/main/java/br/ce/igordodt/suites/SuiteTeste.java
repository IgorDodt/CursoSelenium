package br.ce.igordodt.suites;
import static br.ce.igordodt.core.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.igordodt.test.TesteCadastro;
import br.ce.igordodt.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	TesteRegrasCadastro.class
})
public class SuiteTeste {
	
	@AfterClass
	public static void finalizaTudo() {
		killDriver();
	}

}
