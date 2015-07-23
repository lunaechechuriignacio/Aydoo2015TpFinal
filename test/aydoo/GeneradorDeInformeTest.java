package aydoo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class GeneradorDeInformeTest {

	@Test
	public void comprobarValorContadorDeArhivosEjecutandoGeneradorYSeteandoloManualmente() {
		
		String path="entradas_tests/temporal/recorrido-2012a.csv";
		String pathDeSalida="salida/";
		this.borrarArchivos(pathDeSalida);
		BuscadorDeArchivos ba=new BuscadorDeArchivos(path,"csv");
		int contadorDeArchivos=0;
	GeneradorDeInforme gdi=new GeneradorDeInforme(ba.getListaArchivosEnDirectorio(),pathDeSalida,contadorDeArchivos);
	try {
		gdi.generarInforme();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
		Assert.assertNotEquals(gdi.getContadorDeArchivos(),0);
		
		gdi.setContadorDeArchivos(10);
		Assert.assertEquals(gdi.getContadorDeArchivos(),10,0);
	}

	@Test
	public void comprobarQueFueGeneradoUnArchivoDeSalida() {
		
		String path="entradas_tests/temporal/recorrido-2012a.csv";

		String pathDeSalida="salida/";
			
		this.borrarArchivos(pathDeSalida);

		BuscadorDeArchivos ba=new BuscadorDeArchivos(path,"csv");
		int contadorDeArchivos=0;
		GeneradorDeInforme gdi=new GeneradorDeInforme(ba.getListaArchivosEnDirectorio(),pathDeSalida,contadorDeArchivos);
		try {
			gdi.generarInforme();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
			
		ba.setDirectorioSupervisado(pathDeSalida);
		ba.setTipoDeArchivoABuscar("yml");
		List <String> listadodeArchivos=ba.getListaArchivosEnDirectorio();
		String pathDeArchivoYmlEncontado = null;
		
		for(String pathYml: listadodeArchivos)
			pathDeArchivoYmlEncontado=pathYml;
			Assert.assertEquals(listadodeArchivos.size(),1,0);
            Assert.assertEquals(pathDeArchivoYmlEncontado.substring(pathDeArchivoYmlEncontado.length()-11),"Salida0.yml");

	

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
