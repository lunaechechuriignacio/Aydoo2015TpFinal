import org.junit.Assert;
import org.junit.Test;

public class EstacionTest {

	@Test
	public void compararDosEstacionesConIdDistintos() {
		Estacion retiro = new Estacion("retiro", "100");
		Estacion callao = new Estacion("callao", "101");

		Assert.assertFalse(retiro.estadoCompararEstacion(callao));
	}

	@Test
	public void compararDosEstacionesConIdIguales() {
		Estacion retiro = new Estacion("retiro", "100");

		Assert.assertTrue(retiro.estadoCompararEstacion(retiro));
	}

	@Test
	public void devolverNombreDeLaEstacion() {
		Estacion retiro = new Estacion("retiro", "100");

		Assert.assertEquals(retiro.getNombre(), "retiro");
	}

	@Test
	public void devolverIdDeLaEstacion() {
		Estacion retiro = new Estacion("retiro", "100");

		Assert.assertEquals(retiro.getId(), "100");
	}
}
