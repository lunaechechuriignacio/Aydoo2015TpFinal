package aydoo;

import org.junit.Assert;

import org.junit.Test;
public class ProcesadorTest {

	@Test
	public void pruebaMain(){

		Procesador procesadorTestear = new Procesador();
		
		Assert.assertNotNull(procesadorTestear);
		
	}
}
