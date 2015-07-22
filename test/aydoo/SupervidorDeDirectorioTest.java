package aydoo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Test;

public class SupervidorDeDirectorioTest {

	@Test
	public void agregarArchivoAlDirectorioSupervisado() {
		String directorio = "entradas_tests/testSupervidorDeDirectorio/";

		ProcesadorEstadisticoDaemon procesadorDaemon = new ProcesadorEstadisticoDaemon(
				directorio);

		this.copiarArchivoDePrueba();

		SupervisadorDeDirectorio supervisor = new SupervisadorDeDirectorio(
				directorio, procesadorDaemon);
		supervisor.correrSupervisadorDeDirectorio(1);

		Assert.assertNotNull(supervisor.getFullPath());
	}

	@Test
	public void noSeAgreganArchivosNuevosAlDirectorioSupervisadoTest() {
		String directorio = "entradas_tests/testSupervidorDeDirectorio/";

		ProcesadorEstadisticoDaemon procesadorDaemon = new ProcesadorEstadisticoDaemon(
				directorio);

		SupervisadorDeDirectorio supervisor = new SupervisadorDeDirectorio(
				directorio, procesadorDaemon);
		supervisor.correrSupervisadorDeDirectorio(1);

		Assert.assertNull(supervisor.getFullPath());
	}
	
	private void borrarArchivos() {
		File archivo = new File("entradas_tests/testSupervidorDeDirectorio/");
		File[] ficheros = archivo.listFiles();
		File f = null;
		if (archivo.exists()) {
			for (int x = 0; x < ficheros.length; x++) {
				f = new File(ficheros[x].toString());
				f.delete();
			}
		}

	}
	
	

	private void copiarArchivoDePrueba() {
		this.borrarArchivos();
		new Thread() {
			public void run() {

				try {

					String pathOrigen = "entradas_tests/recorridos-test.zip";
					String pathDestino = "entradas_tests/testSupervidorDeDirectorio/recorridos-test.zip";
					OutputStream out;
					InputStream in;
					Thread.sleep(3000);

					in = new FileInputStream(pathOrigen);

					out = new FileOutputStream(pathDestino);

					byte[] buf = new byte[1024];
					int len;

					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}

					in.close();
					out.close();
				} catch (IOException e) {

					e.printStackTrace();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}

			}
		}.start();
	}

}
