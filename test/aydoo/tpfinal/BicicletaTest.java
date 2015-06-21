

import org.junit.Assert;
import org.junit.Test;


public class BicicletaTest {

	@Test
	public void compararDosBicicletasConIdDistintos() {
		Bicicleta bicicletaUno=new Bicicleta("100");
		Bicicleta bicicletaDos=new Bicicleta("101");
		Assert.assertFalse(bicicletaUno.estadoCompararBicicletas(bicicletaDos));

	}
	@Test
	public void compararDosBicicletasConIdIguales() {
		Bicicleta bicicletaUno=new Bicicleta("100");
		Bicicleta bicicletaDos=new Bicicleta("100");
		Assert.assertTrue(bicicletaUno.estadoCompararBicicletas(bicicletaDos));

	}
}
