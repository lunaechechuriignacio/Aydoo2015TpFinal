package aydoo.tpfinal;

import org.junit.Assert;
import org.junit.Test;

public class ProcesadorEstadisticoOnDemandTest  {

	@Test
	public void testEjecucionOnDemand() {
		String pathDeEntrada="entradas_tests/";
		
		ProcesadorEstadistico procesadorEstadistico=new ProcesadorEstadisticoOnDemand(pathDeEntrada);
		procesadorEstadistico.ejecutar();
		Assert.assertNotNull(procesadorEstadistico);
	}

}
