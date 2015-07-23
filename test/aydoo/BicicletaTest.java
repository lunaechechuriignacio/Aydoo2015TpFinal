package aydoo;

import org.junit.Assert;
import org.junit.Test;


public class BicicletaTest {

	@Test
	public void comparoDosBicicletasConIdDistintos() {
		Bicicleta bicicletaUno=new Bicicleta("100");
		Bicicleta bicicletaDos=new Bicicleta("101");
		Assert.assertFalse(bicicletaUno.equals(bicicletaDos));

	}
	@Test
	public void comparoDosBicicletasConIdIguales() {
		Bicicleta bicicletaUno=new Bicicleta("100");
		Bicicleta bicicletaDos=new Bicicleta("100");
		Assert.assertTrue(bicicletaUno.equals(bicicletaDos));

	}
}
