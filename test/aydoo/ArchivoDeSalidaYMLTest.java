package aydoo;

import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class ArchivoDeSalidaYMLTest {

	@Test
	public void generoUnArchivsdfsdfsdfsdo() {
	
		String  path="salidas/pruebas/YML/ArchivoSalidaYML/";
		ArchivoDeSalidaYML archivoYML = new ArchivoDeSalidaYML("prueba",
				path);
		BuscadorDeArchivos ba = new BuscadorDeArchivos(path, "yml");

		HashMap<Bicicleta, Integer> mapaBicicleta = new HashMap<Bicicleta, Integer>();
		HashMap<Recorrido, Integer> mapaRecorrido = new HashMap<Recorrido, Integer>();
		Estacion retiro = new Estacion("retiro", "100");
		Estacion callao = new Estacion("callao", "101");
		Estacion beiro = new Estacion("beiro", "102");

		mapaBicicleta.put(new Bicicleta("10"), 1);
		mapaBicicleta.put(new Bicicleta("11"), 2);
		mapaBicicleta.put(new Bicicleta("12"), 4);

		mapaRecorrido.put(new Recorrido(retiro, callao), 1);
		mapaRecorrido.put(new Recorrido(retiro, beiro), 4);
		mapaRecorrido.put(new Recorrido(beiro, callao), 2);
		archivoYML.escribirEnArchivo(mapaBicicleta, mapaRecorrido, 20);
		archivoYML.cerrarArchivo();
		
		ba.setDirectorioSupervisado(path);
		ba.setTipoDeArchivoABuscar("yml");
		List <String> listadodeArchivos=ba.getListaArchivosEnDirectorio();
		String pathDeArchivoYmlEncontado = null;
		
		for(String pathYml: listadodeArchivos)
			pathDeArchivoYmlEncontado=pathYml;
			Assert.assertEquals(listadodeArchivos.size(),1,0);

			Assert.assertEquals(pathDeArchivoYmlEncontado.substring(pathDeArchivoYmlEncontado.length()-10),"prueba.yml");
	}

	
}
