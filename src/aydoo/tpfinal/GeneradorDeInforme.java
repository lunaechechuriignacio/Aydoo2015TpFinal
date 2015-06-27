package aydoo.tpfinal;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GeneradorDeInforme {
	private HashMap<Bicicleta, Integer> mapaBicicleta;
	private HashMap<Recorrido, Integer> mapaRecorrido;
	private List<String> listaArchivos;
	private String pathSalida;
	private String nombreArchivoSalida = "Salida";

	private int contadorDeArchivos;

	public GeneradorDeInforme(List<String> listaArchivos, String pathSalida,
			int contadorDeArchivos) {
		this.mapaBicicleta = new HashMap<Bicicleta, Integer>();
		this.mapaRecorrido = new HashMap<Recorrido, Integer>();
		this.listaArchivos = listaArchivos;
		this.pathSalida = pathSalida;
		this.contadorDeArchivos = contadorDeArchivos;
	}

	public void generarInforme(Boolean esDaemon) throws IOException {
		int contadorDeRegistros = 0;
		float tiempoTotal=0;
		Iterator<String> iteradorLista = this.listaArchivos.iterator();
		ManejoDeArchivo manejoDeArchivo = null;

		String pathArchivo = "";

		if (!listaArchivos.isEmpty())
			while (iteradorLista.hasNext()) {

				pathArchivo = iteradorLista.next();
				manejoDeArchivo = new ManejoDeArchivo(pathArchivo,
						this.mapaBicicleta, this.mapaRecorrido);
				manejoDeArchivo.leerArchivoCsv();
				this.mapaBicicleta = manejoDeArchivo.getMapaBicicleta();
				this.mapaRecorrido = manejoDeArchivo.getMapaRecorrido();
				contadorDeRegistros+=manejoDeArchivo.getContadorDeRegistrosEnArchivo();
				tiempoTotal+=manejoDeArchivo.getTiempoTotal();
						  
				 
				 }

		
		float tiempoPromedio=tiempoTotal/contadorDeRegistros;
		
		this.guardarEnArchivo(this.contadorDeArchivos,tiempoPromedio);
		this.resetearVariables();
	}

	private void resetearVariables() {
		this.contadorDeArchivos++;
		this.mapaBicicleta.clear();
		this.mapaRecorrido.clear();
	
	
}
	private void guardarEnArchivo(int contadorDeArchivos,float tiempoPromedio) {
		ArchivoDeSalidaYML archivoYML;
		archivoYML = new ArchivoDeSalidaYML(nombreArchivoSalida
				+ contadorDeArchivos, this.pathSalida);

		archivoYML.escribirEnArchivo(this.mapaBicicleta,this.mapaRecorrido,tiempoPromedio);
		archivoYML.cerrarArchivo();

	}

	

	public int getContadorDeArchivos() {
		return contadorDeArchivos;
	}

	public void setContadorDeArchivos(int contadorDeArchivos) {
		this.contadorDeArchivos = contadorDeArchivos;
	}

}
