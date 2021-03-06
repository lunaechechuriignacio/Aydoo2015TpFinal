package aydoo;

import java.io.File;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.List;

public class ProcesadorDeDatos {
	private String pathTemporal = "/temporal/";
	private String pathSalidaDeInforme, path;
	private int contadorDeArchivos;

	public void generarInforme(String path, String pathSalidaInforme)
			throws IOException {
		this.pathSalidaDeInforme = pathSalidaInforme;
		this.path = path;

	}

	public void ejecutar() throws IOException {

		DescomprimidorArchivosZip descomprimidor = new DescomprimidorArchivosZip(
				path, path.concat(this.pathTemporal));

		this.contadorDeArchivos = this.buscarArchivos(this.pathSalidaDeInforme,
				"yml").size() + 1;
		List<String> pathLista = this.buscarArchivos(this.path, "zip");
		this.borrarArchivos();
		if (!pathLista.isEmpty()) {
			for (String archivosZip : pathLista) {

				descomprimidor.setPathZip(archivosZip);
				descomprimidor.descomprimirArchivosZip();

			}

			this.aplicarGenerarInforme();
		} else
			throw new EmptyStackException();
	}

	public void ejecutar(String fullPath, String path) {

		DescomprimidorArchivosZip descomprimidor = new DescomprimidorArchivosZip(
				path, path.concat(this.pathTemporal));

		try {
			descomprimidor.setPathZip(fullPath);
			descomprimidor.descomprimirArchivosZip();
			this.aplicarGenerarInforme();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void aplicarGenerarInforme() throws IOException {
		GeneradorDeInforme generador = new GeneradorDeInforme(
				this.buscarArchivos(this.path.concat(this.pathTemporal), "csv"),
				this.pathSalidaDeInforme, this.contadorDeArchivos);
		generador.generarInforme();
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
