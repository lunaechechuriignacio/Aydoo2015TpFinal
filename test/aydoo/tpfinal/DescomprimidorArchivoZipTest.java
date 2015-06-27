package aydoo.tpfinal;
import java.io.IOException;
import org.junit.Test;
import org.junit.Assert;

public class DescomprimidorArchivoZipTest {

	@Test
	public void testDescomprimirArchivos() {
		
		String archivoEntrada="entradas_tests/recorridos-2010.zip";
		String carpetaSalida="salidas/pruebas/YML/";
		
		DescomprimidorArchivosZip daz = new DescomprimidorArchivosZip(archivoEntrada,carpetaSalida);
		
		try {
			
			daz.descomprimirArchivosZip();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		Assert.assertTrue(daz.getDirectorio().exists());
		Assert.assertEquals(4, daz.getDirectorio().listFiles().length);
		Assert.assertEquals("YML",daz.getDirectorio().getName());

	}

}