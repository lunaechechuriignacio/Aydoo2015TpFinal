package aydoo;

import java.io.File;
import java.io.IOException;
import org.junit.Test;
import org.junit.Assert;

public class DescomprimidorArchivoZipTest {

	@Test
	public void testDescomprimirArchivos() {
		
		String archivoEntrada="entradas_tests/recorridos-2010.zip";
		String carpetaSalida="salida/";
		
		this.borrarArchivos(carpetaSalida);
		
		DescomprimidorArchivosZip daz = new DescomprimidorArchivosZip(archivoEntrada,carpetaSalida);
		
		try {
			
			daz.descomprimirArchivosZip();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		Assert.assertTrue(daz.getDirectorio().exists());
		Assert.assertEquals(4, daz.getDirectorio().listFiles().length);
		Assert.assertEquals("salida",daz.getDirectorio().getName());

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