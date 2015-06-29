package aydoo;

import org.junit.Assert;
//import java.util.concurrent.TimeUnit;
import org.junit.Test;


public class SupervisadorDeDirectorioTest {
	
	@Test
	public void pruebaDeCreacion(){
		
		String directorio="entradas_tests/";
			
		ProcesadorEstadisticoDaemon procesadorDaemon=new ProcesadorEstadisticoDaemon(directorio);

		SupervisadorDeDirectorio supervisor = new SupervisadorDeDirectorio(directorio,procesadorDaemon);
		
		Assert.assertNotNull(supervisor);
		
	}
	
	@Test
	public void testDeEjecucion() {
		
		// ESTE TEST NO LO CORREMOS PORQUE GENERA UN LOOP INFINITO PARA EL SERVICIO QUE MONITOREA EL PATH DE ENTRADA
		// SEGUN LO ACORDADO, SE DEJA COMENTADO ESTE CODIGO, AUNQUE ESTE TEST ESTA CORRIENDO SIN PROBLEMAS.
		/*String pathDeEntrada="entradas_tests/";
		
		ProcesadorEstadisticoDaemon procesadorDaemon= new ProcesadorEstadisticoDaemon(pathDeEntrada);
		SupervisadorDeDirectorio sdd=new SupervisadorDeDirectorio(pathDeEntrada,procesadorDaemon);
		sdd.correrSupervisadorDeDirectorio();
		Assert.assertNotNull(sdd);
		try {
			sdd.getServicio().poll(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		
	}
	
}
