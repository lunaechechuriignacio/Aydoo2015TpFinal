package aydoo;

import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class ProcesadorDeDatosTest {
	
	@Test
	public void generarInformeConProcesadorDeDatosOnDemandTest(){
		
		String pathSalidaDeInforme="salida/";
		String pathEntradaDeInforme="entradas_tests/";
		this.borrarArchivos(pathSalidaDeInforme);
			
		try {
			ProcesadorDeDatos procesador = new ProcesadorDeDatos();
			procesador.generarInforme(pathEntradaDeInforme, pathSalidaDeInforme);
			procesador.ejecutar();
			Assert.assertNotNull(procesador);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void generarInformeConProcesadorDeDatosDaemonTest(){
		
		String pathSalidaDeInforme="salida/";
		String pathEntradaDeInforme="entradas_tests/";
		String fullPath="entradas_tests/recorridos-2010.zip";
		
		this.borrarArchivos(pathSalidaDeInforme);	
		
		try {
			ProcesadorDeDatos procesador = new ProcesadorDeDatos();
			procesador.generarInforme(pathEntradaDeInforme, pathSalidaDeInforme);
			procesador.ejecutar(fullPath, pathEntradaDeInforme);
			Assert.assertNotNull(procesador);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void borrarArchivos(String ruta) {
		File archivo = new File(ruta);
		File[] ficheros = archivo.listFiles();
		File f = null;
		if (archivo.exists()) {
			for (int x = 0; x < ficheros.length; x++) {
				f = new File(ficheros[x].toString());
				f.delete();
			}
		}

	}
	
}
