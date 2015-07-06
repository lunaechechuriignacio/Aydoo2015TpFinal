package aydoo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.List;

public class ProcesadorDeDatos {
	private final String pathTemporal = "/temporal/";
	private String pathSalidaDeInforme, path;
	private int contadorDeArchivos;

	public void generarInforme(String path, String pathSalidaInforme,
			boolean esDaemon) throws IOException {
		this.pathSalidaDeInforme = pathSalidaInforme;
		this.path = path;

		this.ejecutar(esDaemon);
	}

	private void ejecutar(Boolean esDaemon) throws IOException {
		
		System.out.println("Entre en metodo ejecutar");
		
		//INICIAL
		Date start = new Date();
		DescomprimidorArchivosZip descomprimidor = new DescomprimidorArchivosZip(
				path, path.concat(this.pathTemporal));
		//this.contadorDeArchivos = 1;
		this.contadorDeArchivos = this.buscarArchivos(this.pathSalidaDeInforme,"yml").size()+1;
		List<String> pathLista = this.buscarArchivos(this.path, "zip");
		this.borrarArchivos();
		
		Writer output = new BufferedWriter(new FileWriter(pathSalidaDeInforme+"Salida"+contadorDeArchivos+".yml", true));
		


		
		if (!pathLista.isEmpty()) {
			
			for (String archivosZip : pathLista) {

				descomprimidor.setPathZip(archivosZip);
				descomprimidor.descomprimirArchivosZip();
				if (esDaemon) {
					System.out.println("Entre en modo Daemon");
					this.aplicarGenerarInforme(esDaemon);
					//FINAL
					Date end = new Date();
					output.append("Tiempo de Procesamiento: " + (end.getTime()-start.getTime()));
					output.close();
					
				}
				System.out.println(pathSalidaDeInforme+"Salida"+contadorDeArchivos+".yml");
			}
			if (!esDaemon) {
				System.out.println("Entre en modo On-Demand");
				this.aplicarGenerarInforme(esDaemon);
				Date end = new Date();
				output.append("Tiempo de Procesamiento: " + (end.getTime()-start.getTime()));
				output.close();
				System.out.println(pathSalidaDeInforme+"Salida"+contadorDeArchivos+".yml");
			}
			

		}else
			throw new EmptyStackException();
	}

	private void aplicarGenerarInforme(boolean esDaemon) throws IOException {
		GeneradorDeInforme generador = new GeneradorDeInforme(
				this.buscarArchivos(this.path.concat(this.pathTemporal), "csv"),
				this.pathSalidaDeInforme, this.contadorDeArchivos);
		generador.generarInforme(esDaemon);
		this.contadorDeArchivos = generador.getContadorDeArchivos();
		
		this.borrarArchivos();

	}

	private List<String> buscarArchivos(String path, String tipoDeArchivoABuscar) {

		BuscadorDeArchivos buscador = new BuscadorDeArchivos(path,
				tipoDeArchivoABuscar);
		return buscador.getListaArchivosEnDirectorio();

	}

	private void borrarArchivos() {
		File archivo = new File(this.path.concat(this.pathTemporal));
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
