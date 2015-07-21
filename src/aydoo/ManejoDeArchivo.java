package aydoo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

public class ManejoDeArchivo {
	private String path;
	private HashMap<Bicicleta, Integer> mapaBicicleta;
	private HashMap<Recorrido, Integer> mapaRecorrido;
	private TreeMap<String, Integer> mapaTiemposPromedio;
	private float tiempoTotalDeUso;
	private int contadorDeRegistrosEnArchivo;

	public ManejoDeArchivo(String pathArchivo,
			HashMap<Bicicleta, Integer> mapaBicicleta,
			HashMap<Recorrido, Integer> mapaRecorrido) {
		this.path = pathArchivo;
		this.mapaBicicleta = mapaBicicleta;
		this.mapaRecorrido = mapaRecorrido;

		this.tiempoTotalDeUso = 0;
		this.contadorDeRegistrosEnArchivo = 0;
		this.mapaTiemposPromedio = new TreeMap<String, Integer>();
	}

	public int getContadorDeRegistrosEnArchivo() {
		return contadorDeRegistrosEnArchivo;
	}

	public void setPathArchivo(String path) {
		this.path = path;
	}

	public void leerArchivoCsv() throws IOException {
		String registro = null;
		BufferedReader buffer = null;
		if (!(this.path == " ")) {
			try {
				buffer = new BufferedReader(new FileReader(this.path));
				boolean esPrimeraLinea = true;

				while ((registro = buffer.readLine()) != null) {
					if (esPrimeraLinea) {
						registro = buffer.readLine();
						esPrimeraLinea = false;
					}
					String[] registroSeparadaPorComas = registro.split(";");
					if (registroSeparadaPorComas.length == 9){
						this.llenarDatos(registroSeparadaPorComas);
					}


				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			buffer.close();
		}
	}

	private void llenarDatos(String[] registroSeparadaPorComas) {
		
		String idBicicleta=null;
		String nombreEstacionOrigen = null;
		String idEstacionDestino = null;
		String idEstacionOrigen = null;
		String recorrido = null;
		String fechaDestino = null;
		String fechaOrigen = null;
		int contadorDeColumnas = 0;
		this.contadorDeRegistrosEnArchivo++;

		this.llenarMapaBicicleta(registroSeparadaPorComas[1]);
		idBicicleta=registroSeparadaPorComas[1];
		fechaOrigen = registroSeparadaPorComas[2];
		idEstacionOrigen = registroSeparadaPorComas[3];
		recorrido = registroSeparadaPorComas[3];
		nombreEstacionOrigen = registroSeparadaPorComas[4];
		fechaDestino = registroSeparadaPorComas[5];
		recorrido = recorrido.concat(registroSeparadaPorComas[6]);
		idEstacionDestino = registroSeparadaPorComas[6];
		this.llenarMapaRecorrido(idEstacionOrigen, idEstacionDestino, fechaOrigen, fechaDestino, nombreEstacionOrigen, registroSeparadaPorComas[7]);

		if (!registroSeparadaPorComas[8].isEmpty()){
						
			this.llenarMapaTiemposPromedio(registroSeparadaPorComas[1], registroSeparadaPorComas[8]);
			tiempoTotalDeUso+= Float.parseFloat(registroSeparadaPorComas[8]);
			
		}
			

	}

	public HashMap<Recorrido, Integer> getMapaRecorrido() {
		return mapaRecorrido;
	}

	private void llenarMapaTiemposPromedio(String id, String tiempo){
		
		Integer temporal;
		
		if (mapaTiemposPromedio.containsKey(id)){
			
			temporal = mapaTiemposPromedio.get(id);
			temporal = temporal + Integer.parseInt(tiempo);
			mapaTiemposPromedio.put(id, temporal);
		}
		
		else {
			mapaTiemposPromedio.put(id, Integer.parseInt(tiempo));
		}
	}
	
	private void llenarMapaBicicleta(String idBicicleta) {

		Bicicleta bicicletaNueva = new Bicicleta(idBicicleta);

		if (mapaBicicleta.containsKey(bicicletaNueva)) {
			int contador = mapaBicicleta.get(bicicletaNueva);
			contador++;

			mapaBicicleta.put(bicicletaNueva, contador);
		} else

			mapaBicicleta.put(bicicletaNueva, 1);

	}

	private void llenarMapaRecorrido(String idEstacionOrigen,
			String idEstacionDestino, String fechaOrigen, String fechaDestino,String nombreOrigen,String nombreDestino) {
		Estacion estacionOrigen = new Estacion(nombreOrigen, idEstacionOrigen);

		Estacion estacionDestino = new Estacion(nombreDestino, idEstacionDestino);
		Recorrido recorrido = new Recorrido(estacionOrigen, estacionDestino);
		recorrido.setFechaOrigen(fechaOrigen);
		recorrido.setFechaDestino(fechaDestino);

		if (this.mapaRecorrido.containsKey(recorrido)) {
			int contador = this.mapaRecorrido.get(recorrido);
			contador++;

			this.mapaRecorrido.put(recorrido, contador);
		} else

			this.mapaRecorrido.put(recorrido, 1);

	}

	public HashMap<Bicicleta, Integer> getMapaBicicleta() {

		return mapaBicicleta;
	}

	public float getTiempoTotal() {

		return this.tiempoTotalDeUso;
	}

	public TreeMap<String, Integer> getMapaTiemposPromedio() {
		return mapaTiemposPromedio;
	}
}