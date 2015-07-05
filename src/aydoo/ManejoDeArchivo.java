package aydoo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class ManejoDeArchivo {
	private String path;
	private HashMap<Bicicleta, Integer> mapaBicicleta;
	private HashMap<Recorrido, Integer> mapaRecorrido;
	private float tiempoTotalDeUso;
	private int contadorDeRegistrosEnArchivo;
	private float tiempoMaximoRecorrido;
	private List<String> listaIdBicicletaMaximoRecorrido;

	public ManejoDeArchivo(String pathArchivo,
			HashMap<Bicicleta, Integer> mapaBicicleta,
			HashMap<Recorrido, Integer> mapaRecorrido) {
		this.path = pathArchivo;
		this.mapaBicicleta = mapaBicicleta;
		this.mapaRecorrido = mapaRecorrido;

		this.tiempoTotalDeUso = 0;
		this.contadorDeRegistrosEnArchivo = 0;
		tiempoMaximoRecorrido=0;
		listaIdBicicletaMaximoRecorrido = new LinkedList<String>();
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
		String idBicicleta=null;
		String nombreEstacionOrigen = null;
		String idEstacionDestino = null;
		String idEstacionOrigen = null;
		String recorrido = null;
		String fechaDestino = null;
		String fechaOrigen = null;
		int contadorDeColumnas = 0;
		this.contadorDeRegistrosEnArchivo++;
		
		while (dividir.hasMoreTokens()) {
			String dato = dividir.nextToken();
			switch (contadorDeColumnas) {
			case 1:
				this.llenarMapaBicicleta(dato);
				idBicicleta=dato;
				break;

			case 2:
				fechaOrigen = dato;
				break;

			case 3: {
				idEstacionOrigen = dato;
				recorrido = dato;
				break;
			}
			case 4:
				nombreEstacionOrigen = dato;
				break;
			case 5:
				fechaDestino = dato;
				break;

			case 6: {
				recorrido = recorrido.concat(dato);
				idEstacionDestino = dato;

				break;
			}

			case 7: {
				this.llenarMapaRecorrido(idEstacionOrigen, idEstacionDestino,
						fechaOrigen, fechaDestino, nombreEstacionOrigen, dato);

				break;
			}
			case 8: {
				if (!dato.isEmpty()){
					this.tiempoTotalDeUso += (Float.parseFloat(dato));
					//Ademas lo agrego a la lista;
					if(Float.parseFloat(dato)>=tiempoMaximoRecorrido){
						
						if (Float.parseFloat(dato)==tiempoMaximoRecorrido){
							
							tiempoMaximoRecorrido = Float.parseFloat(dato);
							listaIdBicicletaMaximoRecorrido.add(idBicicleta);
						}
						
						else {
							
							tiempoMaximoRecorrido = Float.parseFloat(dato);
							listaIdBicicletaMaximoRecorrido.clear();
							listaIdBicicletaMaximoRecorrido.add(idBicicleta);
							
						}
						
						
					}
				}		

				
				break;
			}
			}
			contadorDeColumnas++;
		}

	}

	public float getTiempoMaximo() {
		return tiempoMaximoRecorrido;
	}

	public List<String> getListaIdBicicletaMaximoRecorrido() {
		return listaIdBicicletaMaximoRecorrido;
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
