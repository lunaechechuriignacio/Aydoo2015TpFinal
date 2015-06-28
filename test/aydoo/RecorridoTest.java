package aydoo;

import org.junit.Assert;
import org.junit.Test;

public class RecorridoTest {

	@Test
	public void compararDosDistintos() {
		Estacion retiro = new Estacion("retiro", "100");
		Estacion callao = new Estacion("callao", "101");
		Estacion beiro = new Estacion("Beiro", "101");
		Recorrido recorridoA = new Recorrido(retiro, callao);
		Recorrido recorridoB = new Recorrido(beiro, callao);
		Assert.assertFalse(recorridoA.equals(recorridoB));
	}

	@Test
	public void compararDosRecorridosIguales() {
		Estacion retiro = new Estacion("retiro", "100");
		Estacion callao = new Estacion("callao", "101");
 
		Recorrido recorridoA = new Recorrido(retiro, callao);
		Recorrido recorridoB = new Recorrido(retiro, callao);
		 
		Assert.assertTrue(recorridoA.equals(recorridoB));
	}

	@Test
	public void devolverNombreYIdDeEstaciones() {
		Estacion retiro = new Estacion("retiro", "100");
		Estacion callao = new Estacion("callao", "101");
 
		Recorrido recorrido = new Recorrido(retiro, callao);

		Assert.assertEquals(recorrido.getEstacionOrigen().getNombre(), "retiro");
		Assert.assertEquals(recorrido.getEstacionDestino().getNombre(), "callao");
		Assert.assertEquals(recorrido.getEstacionOrigen().getId(), "100");
		Assert.assertEquals(recorrido.getEstacionDestino().getId(), "101");
	}

	@Test
	public void seteandoFechasParaCompararlas() {
		Estacion retiro = new Estacion("retiro", "100");
		Estacion callao = new Estacion("callao", "101");
 
		Recorrido recorrido = new Recorrido(retiro, callao);
		
		recorrido.setFechaDestino("29/09/2012  02:19:00 p.m.");
		recorrido.setFechaOrigen("29/10/2015  02:19:00 p.m.");
		 
		Assert.assertEquals(recorrido.getFechaDestino(),"29/09/2012  02:19:00 p.m.");
		Assert.assertEquals(recorrido.getFechaOrigen(),"29/10/2015  02:19:00 p.m.");

	}
}
