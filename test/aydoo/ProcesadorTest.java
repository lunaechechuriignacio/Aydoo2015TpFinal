package aydoo;

import org.junit.Assert;

import org.junit.Test;
public class ProcesadorTest {

	@Test
	public void pruebaMain(){

		Procesador procesadorTestear = new Procesador();
		String[] cadena={"",""};
		
		cadena[0]="entradas_tests/";
		
		Procesador.main(cadena);
		
		Assert.assertNotNull(procesadorTestear);
		
	}
}
