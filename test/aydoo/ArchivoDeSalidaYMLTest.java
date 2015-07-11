package aydoo;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ArchivoDeSalidaYMLTest {

	@Test
	public void generoUnArchivoDePrueba() {
	
		String  path="salida/";
		this.borrarArchivos(path);
		ArchivoDeSalidaYML archivoYML = new ArchivoDeSalidaYML("prueba",path);
		BuscadorDeArchivos ba = new BuscadorDeArchivos(path, "yml");

		HashMap<Bicicleta, Integer> mapaBicicleta = new HashMap<Bicicleta, Integer>();
		HashMap<Recorrido, Integer> mapaRecorrido = new HashMap<Recorrido, Integer>();
		Estacion retiro = new Estacion("retiro", "100");
		Estacion callao = new Estacion("callao", "101");
		Estacion beiro = new Estacion("beiro", "102");

		mapaBicicleta.put(new Bicicleta("10"), 1);
		mapaBicicleta.put(new Bicicleta("11"), 2);
		mapaBicicleta.put(new Bicicleta("12"), 4);
		
		List<String> lista = new LinkedList<String>();
		lista.add("1234");

		mapaRecorrido.put(new Recorrido(retiro, callao), 1);
		mapaRecorrido.put(new Recorrido(retiro, beiro), 4);
		mapaRecorrido.put(new Recorrido(beiro, callao), 2);
		archivoYML.escribirEnArchivo(mapaBicicleta, mapaRecorrido, 20, lista, 100);
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
	
	private void borrarArchivos(String ruta) {
		File archivo = new File(ruta);
		File[] ficheros = archivo.listFiles();
		File f = null;
		if (archivo.exists()) {
			for (int x = 0; x < ficheros.length; x++) {
				f = new File(ficheros[x].toString());
				f.delete();
			}
		}

	}

	
}
