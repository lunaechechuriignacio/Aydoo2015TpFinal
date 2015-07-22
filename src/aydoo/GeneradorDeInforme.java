package aydoo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GeneradorDeInforme {
	private HashMap<Bicicleta, Integer> mapaBicicleta;
	private HashMap<Recorrido, Integer> mapaRecorrido;
	private List<String> listaArchivos;
	private String pathSalida;
	private String nombreArchivoSalida = "Salida";
	private int valorMinimoBicicleta, valorMaximoBicicleta;
	private float tiempoPromedio;
	// private float tiempoTotalDeUso;
	// private int contadorDeViajes;

	private int contadorDeArchivos;

	public GeneradorDeInforme(List<String> listaArchivos, String pathSalida,
			int contadorDeArchivos) {
		this.mapaBicicleta = new HashMap<Bicicleta, Integer>();
		this.mapaRecorrido = new HashMap<Recorrido, Integer>();
		this.listaArchivos = listaArchivos;
		this.pathSalida = pathSalida;
		this.contadorDeArchivos = contadorDeArchivos;
		this.valorMaximoBicicleta = 0;
		this.valorMinimoBicicleta = 0;
		this.tiempoPromedio = 0;
	}

	public void generarInforme() throws IOException {
		int contadorDeRegistros = 0;
		float tiempoTotal = 0;
		Iterator<String> iteradorLista = this.listaArchivos.iterator();
		ManejoDeArchivo manejoDeArchivo = null;

		String pathArchivo = "";

		if (!listaArchivos.isEmpty()) {
			while (iteradorLista.hasNext()) {

				pathArchivo = iteradorLista.next();
				manejoDeArchivo = new ManejoDeArchivo(pathArchivo,
						this.mapaBicicleta, this.mapaRecorrido);
				manejoDeArchivo.leerArchivoCsv();
				this.mapaBicicleta = manejoDeArchivo.getMapaBicicleta();
				this.mapaRecorrido = manejoDeArchivo.getMapaRecorrido();
				contadorDeRegistros += this.getCantidadTotalDeViajes();
				tiempoTotal += this.getTiempoTotalDeUso();

			}
		}
		this.tiempoPromedio = tiempoTotal / contadorDeRegistros;
		this.guardarEnArchivo(this.contadorDeArchivos);
		this.resetearVariables();
	}

	private void resetearVariables() {
		this.contadorDeArchivos++;
		this.mapaBicicleta.clear();
		this.mapaRecorrido.clear();
	}

	private void guardarEnArchivo(int contadorDeArchivos) {
		ArchivoDeSalidaYML archivoYML;
		archivoYML = new ArchivoDeSalidaYML(nombreArchivoSalida
				+ contadorDeArchivos, this.pathSalida);

		archivoYML.escribirEnArchivo(this);
		archivoYML.cerrarArchivo();

	}

	public int getContadorDeArchivos() {
		return contadorDeArchivos;
	}

	public void setContadorDeArchivos(int contadorDeArchivos) {
		this.contadorDeArchivos = contadorDeArchivos;
	}

	private void calcularValorMaximoMininoBicicleta() {

		boolean estadoPrimeraVuelta = false;

		for (Map.Entry<Bicicleta, Integer> mapa : mapaBicicleta.entrySet()) {
			if (!estadoPrimeraVuelta) {
				this.valorMaximoBicicleta = this.valorMinimoBicicleta = mapa
						.getValue();
				estadoPrimeraVuelta = true;
			} else {
				guardarValorMaximoBicicleta(mapa.getValue());
				guardarValorMininoBicicleta(mapa.getValue());

			}

		}

	}

	private void guardarValorMaximoBicicleta(int valor) {
		if (this.valorMaximoBicicleta < valor)
			this.valorMaximoBicicleta = valor;
	}

	private void guardarValorMininoBicicleta(int valor) {
		if (this.valorMinimoBicicleta > valor)
			this.valorMinimoBicicleta = valor;
	}

	private int getValorMaximoRecorrido() {

		int valorMaximoRecorrido = 0;

		boolean estadoPrimeraVuelta = false;

		for (Map.Entry<Recorrido, Integer> mapa : mapaRecorrido.entrySet()) {
			if (!estadoPrimeraVuelta) {
				valorMaximoRecorrido = mapa.getValue();
				estadoPrimeraVuelta = true;
			} else if (valorMaximoRecorrido < mapa.getValue())

				valorMaximoRecorrido = mapa.getValue();
		}

		return valorMaximoRecorrido;
	}

	public List<Recorrido> getListaMaximosRecorridos() {

		List<Recorrido> recorridos = new ArrayList<Recorrido>();

		for (Map.Entry<Recorrido, Integer> mapa : this.mapaRecorrido.entrySet()) {
			if (getValorMaximoRecorrido() == mapa.getValue()) {
				recorridos.add(mapa.getKey());
			}
		}
		return recorridos;
	}

	public List<Bicicleta> getListaBicicletasMinimos() {

		this.calcularValorMaximoMininoBicicleta();

		List<Bicicleta> bicicletas = new ArrayList<Bicicleta>();

		for (Map.Entry<Bicicleta, Integer> mapa : this.mapaBicicleta.entrySet()) {
			if (this.getValorMinimoBicicleta() == mapa.getValue()) {
				bicicletas.add(mapa.getKey());
			}
		}
		return bicicletas;
	}

	public List<Bicicleta> getListaBicicletasMaximos() {

		this.calcularValorMaximoMininoBicicleta();

		List<Bicicleta> bicicletas = new ArrayList<Bicicleta>();

		for (Map.Entry<Bicicleta, Integer> mapa : this.mapaBicicleta.entrySet()) {
			if (this.getValorMaximoBicicleta() == mapa.getValue()) {
				bicicletas.add(mapa.getKey());
			}
		}
		return bicicletas;
	}

	public int getValorMinimoBicicleta() {
		return valorMinimoBicicleta;
	}

	public int getValorMaximoBicicleta() {
		return valorMaximoBicicleta;
	}

	public float getTiempoPromedio() {
		return tiempoPromedio;
	}

	public HashMap<Bicicleta, Integer> getMapaBicicleta() {
		return mapaBicicleta;
	}

	public void setMapaBicicleta(HashMap<Bicicleta, Integer> mapaBicicleta) {
		this.mapaBicicleta = mapaBicicleta;
	}

	public HashMap<Recorrido, Integer> getMapaRecorrido() {
		return mapaRecorrido;
	}

	public void setMapaRecorrido(HashMap<Recorrido, Integer> mapaRecorrido) {
		this.mapaRecorrido = mapaRecorrido;
	}

	public void setTiempoPromedio(float tiempoPromedio) {
		this.tiempoPromedio = tiempoPromedio;
	}

	private int getCantidadTotalDeViajes() {

		int totalViajes = 0;

		for (Map.Entry<Bicicleta, Integer> mapa : this.mapaBicicleta.entrySet()) {

			totalViajes += mapa.getValue();

		}

		return totalViajes;
	}

	private int getTiempoTotalDeUso() {

		int tiempoTotalUso = 0;

		for (Map.Entry<Recorrido, Integer> mapa : this.mapaRecorrido.entrySet()) {

			tiempoTotalUso += mapa.getKey().getTiempoDeUso();

		}

		return tiempoTotalUso;
	}

}
