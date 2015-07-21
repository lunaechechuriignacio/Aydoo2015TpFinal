package aydoo;

import java.io.IOException;
import java.util.*;

public class GeneradorDeInforme {
	private HashMap<Bicicleta, Integer> mapaBicicleta;
	private HashMap<Recorrido, Integer> mapaRecorrido;
	private List<String> listaArchivos;
	private String pathSalida;
	private String nombreArchivoSalida = "Salida";
	private float tiempoMaximoRecorrido;
	private List<String> listaIdBicicletaMaximoRecorrido;
	private TreeMap<String, Integer> mapaTiemposPromedio;
	private EstadisticaBiciUsadaMasTiempo estadisticaBiciUsadaMasTiempo;
	
	private int contadorDeArchivos;

	public GeneradorDeInforme(List<String> listaArchivos, String pathSalida,
			int contadorDeArchivos) {
		this.mapaBicicleta = new HashMap<Bicicleta, Integer>();
		this.mapaRecorrido = new HashMap<Recorrido, Integer>();
		this.listaArchivos = listaArchivos;
		this.pathSalida = pathSalida;
		this.contadorDeArchivos = contadorDeArchivos;
		this.listaIdBicicletaMaximoRecorrido = new LinkedList<String>();
		this.mapaTiemposPromedio = new TreeMap<String, Integer>();
		this.estadisticaBiciUsadaMasTiempo = new EstadisticaBiciUsadaMasTiempo();
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
				manejoDeArchivo = new ManejoDeArchivo(pathArchivo,this.mapaBicicleta, this.mapaRecorrido);
				manejoDeArchivo.leerArchivoCsv();
				this.mapaBicicleta = manejoDeArchivo.getMapaBicicleta();
				this.mapaRecorrido = manejoDeArchivo.getMapaRecorrido();
				aparearMapaTiemposPromedio(manejoDeArchivo.getMapaTiemposPromedio());
				contadorDeRegistros+=manejoDeArchivo.getContadorDeRegistrosEnArchivo();
				tiempoTotal+=manejoDeArchivo.getTiempoTotal();
				
			}
		this.estadisticaBiciUsadaMasTiempo.calcularTiempoBicicletaMasUsadayTiempoMaximoRecorrido(this.mapaTiemposPromedio);
		this.tiempoMaximoRecorrido = estadisticaBiciUsadaMasTiempo.getTiempoMaximoRecorrido();
		this.listaIdBicicletaMaximoRecorrido = estadisticaBiciUsadaMasTiempo.getListaIdBicicletaMaximoRecorrido();
		float tiempoPromedio=tiempoTotal/contadorDeRegistros;		
		this.guardarEnArchivo(this.contadorDeArchivos,tiempoPromedio);
		this.resetearVariables();
	
	}

	private void aparearMapaTiemposPromedio(TreeMap<String, Integer> mapaTiemposPromedioParcial){
		Integer temporal;
		for (String id: mapaTiemposPromedioParcial.keySet()){
			if(this.mapaTiemposPromedio.containsKey(id)){
				temporal = mapaTiemposPromedioParcial.get(id) + this.mapaTiemposPromedio.get(id);
				mapaTiemposPromedio.put(id, temporal);
			}
			else{
				this.mapaTiemposPromedio.put(id,mapaTiemposPromedioParcial.get(id));
			}

		}
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

		archivoYML.escribirEnArchivo(this.mapaBicicleta,this.mapaRecorrido,tiempoPromedio, this.listaIdBicicletaMaximoRecorrido, this.tiempoMaximoRecorrido);
		archivoYML.cerrarArchivo();

	}

	public int getContadorDeArchivos() {
		return contadorDeArchivos;
	}

	public void setContadorDeArchivos(int contadorDeArchivos) {
		this.contadorDeArchivos = contadorDeArchivos;
	}

}
