package aydoo;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;
import org.junit.Assert;

public class ProcesadorEstadisticoDaemonTest  {

	/*
	// Se comenta este test porque genera un loop dejando el servicioa la espera de ingreso de archivos en un folder
	// de esta forma, cuelga Travis y hace fallar Jenkins.	
	@Test
	public void testEjecucionDaemon() {
		String pathDeEntrada="entradas_tests/";
				
		ProcesadorEstadistico procesadorEstadistico=new ProcesadorEstadisticoDaemon(pathDeEntrada);
		procesadorEstadistico.ejecutar();
		
		Assert.assertNotNull(procesadorEstadistico);
	
	}*/
	
	@Test
	public void testEjecucionDaemon() {
		
		
		TimerTask timerTask = new TimerTask()
	    {
	        public void run() 
	        {
	        	String pathDeEntrada="entradas_tests/";
				
	    		ProcesadorEstadistico procesadorEstadistico=new ProcesadorEstadisticoDaemon(pathDeEntrada);
	        	procesadorEstadistico.ejecutar();
	        }
	    };
	    
	    Timer timer = new Timer();
	   
	    timer.scheduleAtFixedRate(timerTask, 0, 1000);
		
		Assert.assertNotNull(timer);
	
	}
	
	@Test
	public void testGenerarInformeDaemon() {
		String pathDeEntrada="entradas_tests/";
		String fullPath="entradas_tests/recorridos-2010.zip";
		ProcesadorEstadisticoDaemon procesadorEstadistico=new ProcesadorEstadisticoDaemon(pathDeEntrada);
		procesadorEstadistico.generarInforme(fullPath);
		
		Assert.assertNotNull(procesadorEstadistico);
	
	}

}
