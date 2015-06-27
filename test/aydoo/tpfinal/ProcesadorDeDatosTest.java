package aydoo.tpfinal;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class ProcesadorDeDatosTest {
	
	@Test
	public void generarInformeConProcesadorDeDatosOnDemandTest(){
		
		String pathSalidaDeInforme="salidas/pruebas/YML/";
		String pathEntradaDeInforme="entradas_tests/";
		boolean isDaemon=false;
		
		try {
			ProcesadorDeDatos procesador = new ProcesadorDeDatos();
			procesador.generarInforme(pathEntradaDeInforme, pathSalidaDeInforme, isDaemon);
			Assert.assertNotNull(procesador);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void generarInformeConProcesadorDeDatosDaemonTest(){
		
		String pathSalidaDeInforme="salidas/pruebas/YML/";
		String pathEntradaDeInforme="entradas_tests/";
		boolean isDaemon=true;
		
		try {
			ProcesadorDeDatos procesador = new ProcesadorDeDatos();
			procesador.generarInforme(pathEntradaDeInforme, pathSalidaDeInforme, isDaemon);
			Assert.assertNotNull(procesador);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
