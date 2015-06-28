package aydoo;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class ManejoDeArchivosTest {

	@Test
	public void leerUnArchivoYLlenarLasEstructuras() {
		HashMap<Bicicleta, Integer> mapaBicicleta = new HashMap<Bicicleta, Integer>();
		HashMap<Recorrido, Integer> mapaRecorrido = new HashMap<Recorrido, Integer>();
		ManejoDeArchivo mda = new ManejoDeArchivo(
				"entradas_tests/recorridos-2010a.csv", mapaBicicleta,
				mapaRecorrido);

		try {

			mda.leerArchivoCsv();

		} catch (IOException e) {

			e.printStackTrace();
		}

		Assert.assertNotEquals(mda.getMapaRecorrido().size(), 0);
		Assert.assertNotEquals(mda.getMapaRecorrido().size(), 0);
		Assert.assertNotEquals(mda.getMapaBicicleta().size(), 0);
		Assert.assertNotEquals(mda.getContadorDeRegistrosEnArchivo(), 0);
		Assert.assertNotEquals(mda.getTiempoTotal(), 0);

	}

	@Test
	public void ejecutarConPathVacio() {
		HashMap<Bicicleta, Integer> mapaBicicleta = new HashMap<Bicicleta, Integer>();
		HashMap<Recorrido, Integer> mapaRecorrido = new HashMap<Recorrido, Integer>();
		ManejoDeArchivo mda = new ManejoDeArchivo(" ", mapaBicicleta,
				mapaRecorrido);

		try {

			mda.leerArchivoCsv();

		} catch (IOException e) {

			e.printStackTrace();
		}

		Assert.assertEquals(mda.getMapaRecorrido().size(), 0);
		Assert.assertEquals(mda.getMapaRecorrido().size(), 0);
		Assert.assertEquals(mda.getMapaBicicleta().size(), 0);
		Assert.assertEquals(mda.getContadorDeRegistrosEnArchivo(), 0);
		Assert.assertEquals(mda.getTiempoTotal(), 0, 0);

	}

}
