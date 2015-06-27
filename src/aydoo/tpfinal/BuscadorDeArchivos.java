package aydoo.tpfinal;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BuscadorDeArchivos {

	private Path directorioSupervisado;
	private String tipoDeArchivoABuscar;

	public BuscadorDeArchivos(String directorio, String tipoDeArchivoABuscar) {

		this.directorioSupervisado = Paths.get(directorio);
		this.tipoDeArchivoABuscar = tipoDeArchivoABuscar;
	}

	public void setTipoDeArchivoABuscar(String tipoDeArchivoABuscar) {
		this.tipoDeArchivoABuscar = tipoDeArchivoABuscar;
	}

	public Path getDirectorioSupervisado() {
		return directorioSupervisado;
	}

	public void setDirectorioSupervisado(String path) {
		this.directorioSupervisado = Paths.get(path);
	}

	public List<String> getListaArchivosEnDirectorio() {

		List<String> listaArchivosEnDirectorio = new ArrayList<String>();

		File directorio = new File(this.directorioSupervisado.toString());
		String[] listaArchivos = directorio.list();
		

		if (listaArchivos != null) {
			for (int x = 0; x < listaArchivos.length; x++) {
				String extencionDeArchivo=listaArchivos[x].substring(listaArchivos[x].length() - 3,listaArchivos[x].length());
				if (extencionDeArchivo.equals(tipoDeArchivoABuscar))
					listaArchivosEnDirectorio.add(this.directorioSupervisado
							.toString().concat("\\").concat(listaArchivos[x]));
			}
		}

		return listaArchivosEnDirectorio;
	}

}
