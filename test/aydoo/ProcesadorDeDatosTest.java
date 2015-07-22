package aydoo;

import java.io.*;

import org.junit.Assert;
import org.junit.Test;

public class ProcesadorDeDatosTest {
	
	@Test
	public void generarInformeConProcesadorDeDatosOnDemandTest(){
		
		String pathSalidaDeInforme="salida/";
		String pathEntradaDeInforme="entradas_tests/";
		this.borrarArchivos(pathSalidaDeInforme);
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
		
		String pathSalidaDeInforme="salida/";
		String pathEntradaDeInforme="entradas_tests/";
		this.borrarArchivos(pathSalidaDeInforme);
		boolean isDaemon=true;
		
		try {
			ProcesadorDeDatos procesador = new ProcesadorDeDatos();
			procesador.generarInforme(pathEntradaDeInforme, pathSalidaDeInforme, isDaemon);
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

	@Test
	public void pruebaDeIntegracionTiempoBiciMasUtilizada(){
		BufferedReader bufferedReader;
		String linea;
		String lineaAEvaluar="";
		Boolean encontro = false;
		String pathSalidaDeInforme="salida/";
		String pathEntradaDeInforme="entradas_tests/pruebaIntegracion/";
		this.borrarArchivos(pathSalidaDeInforme);
		boolean isDaemon=false;

		try {
			ProcesadorDeDatos procesador = new ProcesadorDeDatos();
			procesador.generarInforme(pathEntradaDeInforme, pathSalidaDeInforme, isDaemon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bufferedReader =  new BufferedReader(new FileReader("salida/Salida1.yml"));
			while ((linea = bufferedReader.readLine()) != null && encontro == false) {
				//linea = bufferedReader.readLine();
				if (linea.matches("(.*)Tiempo de la bicicleta mas utilizada(.*)")){
					encontro = true;
					lineaAEvaluar = linea;
				}
			}
			String[] lineaSeparada = lineaAEvaluar.split(":");
			Assert.assertEquals(" 630504600",lineaSeparada[1]);
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void pruebaDeIntegracionIdBiciMasUtilizada(){
		BufferedReader bufferedReader;
		String linea;
		String lineaAEvaluar="";
		Boolean encontro = false;
		String pathSalidaDeInforme="salida/";
		String pathEntradaDeInforme="entradas_tests/pruebaIntegracion/";
		this.borrarArchivos(pathSalidaDeInforme);
		boolean isDaemon=false;

		try {
			ProcesadorDeDatos procesador = new ProcesadorDeDatos();
			procesador.generarInforme(pathEntradaDeInforme, pathSalidaDeInforme, isDaemon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bufferedReader =  new BufferedReader(new FileReader("salida/Salida1.yml"));
			while ((linea = bufferedReader.readLine()) != null && encontro == false) {
				//linea = bufferedReader.readLine();
				if (linea.matches("(.*)Bicicleta utilizada mas tiempo(.*)")){
					encontro = true;
					lineaAEvaluar = bufferedReader.readLine();
				}
			}
			String[] lineaSeparada = lineaAEvaluar.split(":");
			Assert.assertEquals(" 1",lineaSeparada[1]);
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
