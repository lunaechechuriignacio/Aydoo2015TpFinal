package aydoo.tpfinal;

import org.junit.Test;
import org.junit.Assert;

public class ProcesadorEstadisticoDaemonTest  {

	@Test
	public void testEjecucionDaemon() {
		String pathDeEntrada="entradas_tests/";
				
		ProcesadorEstadistico procesadorEstadistico=new ProcesadorEstadisticoDaemon(pathDeEntrada);
		procesadorEstadistico.ejecutar();
		
		Assert.assertNotNull(procesadorEstadistico);
	
	}
	
	@Test
	public void testGenerarInformeDaemon() {
		String pathDeEntrada="entradas_tests/";
				
		ProcesadorEstadisticoDaemon procesadorEstadistico=new ProcesadorEstadisticoDaemon(pathDeEntrada);
		procesadorEstadistico.generarInforme();
		
		Assert.assertNotNull(procesadorEstadistico);
	
	}

}
