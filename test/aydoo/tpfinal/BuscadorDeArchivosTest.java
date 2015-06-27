package aydoo.tpfinal;

import java.util.Iterator;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class BuscadorDeArchivosTest {
	
	@Test
	public void testPorPantalla(){
		
		String rutaDeBusqueda="entradas_tests/";
		String extensionArchivoBuscado="zip";
		
		BuscadorDeArchivos bda = new BuscadorDeArchivos(rutaDeBusqueda,extensionArchivoBuscado);
		
		List<String> listaArchivos = bda.getListaArchivosEnDirectorio();
		
		Iterator<String> iteradorLista = listaArchivos.iterator();
		
		String archivo = "";
		
		if (listaArchivos.isEmpty()){
             System.out.println("No hay ficheros en el directorio especificado");
		} else {
             while (iteradorLista.hasNext()){
            	 
            	 archivo = iteradorLista.next();
            	 System.out.println(archivo); 
             }
		}
		
	}
	
	@Test
	public void testCarpetaConContenido(){
		
		String rutaDeBusqueda="entradas_tests/";
		String extensionArchivoBuscado="zip";
		
		BuscadorDeArchivos bda_conContenidos = new BuscadorDeArchivos(rutaDeBusqueda,extensionArchivoBuscado);
		
		List<String> listaArchivos = bda_conContenidos.getListaArchivosEnDirectorio();
		
		Assert.assertFalse(listaArchivos.isEmpty());
		Assert.assertTrue(listaArchivos.size()>1);

	}
	
	@Test
	public void testCarpetaSinContenido(){
		
		String rutaDeBusqueda="entradas_tests/pruebaCarpetaVacia/";
		String extensionArchivoBuscado="zip";
		
		BuscadorDeArchivos bda_sinContenidos = new BuscadorDeArchivos(rutaDeBusqueda,extensionArchivoBuscado);
		
		List<String> listaArchivos = bda_sinContenidos.getListaArchivosEnDirectorio();
		
		Assert.assertTrue(listaArchivos.isEmpty());
		Assert.assertEquals(0, listaArchivos.size());

	}
	

}
