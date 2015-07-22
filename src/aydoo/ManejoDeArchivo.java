package aydoo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ManejoDeArchivo {
	private String path;
	private HashMap<Bicicleta, Integer> mapaBicicleta;
	private HashMap<Recorrido, Integer> mapaRecorrido;
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

					StringTokenizer dividir = new StringTokenizer(registro, ";");
					this.llenarDatos(dividir);

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			buffer.close();
		}
	}

	private void llenarDatos(StringTokenizer dividir) {
		this.contadorDeRegistrosEnArchivo++;
		
		String[] datos = new String[dividir.countTokens() ];
	 	
		int indiceArray=0;
	 	
		while (dividir.hasMoreTokens()) {
		 	String dato = dividir.nextToken();
		 	datos[indiceArray]=dato;
		 	indiceArray++;
		}
		 
		this.llenarMapaBicicleta(datos[1]);
		 	 
		this.llenarMapaRecorrido(datos[3], datos[6],datos[2], datos[5],datos[4], datos[7]); 
		
		if (datos.length==9){
			this.tiempoTotalDeUso += (Float.parseFloat(datos[8]));
		}
		
	}

	public HashMap<Recorrido, Integer> getMapaRecorrido() {
		return mapaRecorrido;
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
}
