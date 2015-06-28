package aydoo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ArchivoDeSalidaYML {
	private String nombreArchivo;
	private FileWriter archivoYML = null;
	private BufferedWriter buffer = null;
	private PrintWriter escritura = null;
	private String extension = ".yml";
	private String path;
	private int valorMinimoBicicleta, valorMaximoBicicleta;

	public ArchivoDeSalidaYML(String nombreArchivo, String path) {
		this.nombreArchivo = nombreArchivo;
		this.path = path;
	}

	private void crearArchivo() {
		try {
			archivoYML = new FileWriter(this.path.concat(this.nombreArchivo
					.concat(extension)));
			this.buffer = new BufferedWriter(archivoYML);
		} catch (IOException ex) {
		}
	}

	public void escribirEnArchivo(HashMap<Bicicleta, Integer> mapaBicicleta,
			HashMap<Recorrido, Integer> mapaRecorrido, float tiempoPromedio) {
		this.crearArchivo();
		escritura = new PrintWriter(buffer);
		this.valorMAximoMininoBicicleta(mapaBicicleta);
		
		escritura.println("Bicicletas mas usadas:");
		this.escribirMaximoValorDeBicicleta(mapaBicicleta, escritura);
		
		escritura.println("Bicicletas menos usadas:");
		this.escribirMinimoValorDeBicicleta(mapaBicicleta, escritura);
		
		escritura.println("Recorrido mas realizado:");
		this.escribirRecorridoMasUtilizado(mapaRecorrido, escritura);

		escritura.println("Tiempo promedio de uso: "
				+ String.valueOf(tiempoPromedio));

		escritura.close();
	}

	private void escribirRecorridoMasUtilizado(
			HashMap<Recorrido, Integer> mapaRecorrido, PrintWriter escritura) {

		Recorrido recorrido = null;
		int valorMaximoRecorrido = this.valorMAximoRecorrido(mapaRecorrido);
		
		for (Map.Entry<Recorrido, Integer> mapa : mapaRecorrido.entrySet())
			if (valorMaximoRecorrido == mapa.getValue()) {
				recorrido = mapa.getKey();
				
				escritura.println("id origen: "
						+ recorrido.getEstacionOrigen().getId());
				escritura.println("id destino: "
						+ recorrido.getEstacionDestino().getId());
			}

	}

	private void escribirMinimoValorDeBicicleta(
			HashMap<Bicicleta, Integer> mapaBicicleta, PrintWriter escritura) {

		Bicicleta bicicleta = null;
		
		for (Map.Entry<Bicicleta, Integer> mapa : mapaBicicleta.entrySet()) {
			if (valorMinimoBicicleta == mapa.getValue()) {
				bicicleta = mapa.getKey();
				
				escritura.println("id: " + bicicleta.getId());

			}
		}

	}

	private void escribirMaximoValorDeBicicleta(
			HashMap<Bicicleta, Integer> mapaBicicleta, PrintWriter escritura2) {

		Bicicleta bicicleta = null;
		
		for (Map.Entry<Bicicleta, Integer> mapa : mapaBicicleta.entrySet()) {
			if (valorMaximoBicicleta == mapa.getValue()) {
				bicicleta = mapa.getKey();
				
				escritura.println("id: " + bicicleta.getId());

			}
		}

	}

	private void valorMAximoMininoBicicleta(
			HashMap<Bicicleta, Integer> mapaBicicleta) {

		boolean estadoPrimeraVuelta = false;

		for (Map.Entry<Bicicleta, Integer> mapa : mapaBicicleta.entrySet()) {
			if (!estadoPrimeraVuelta) {
				this.valorMaximoBicicleta = valorMinimoBicicleta = mapa
						.getValue();

				estadoPrimeraVuelta = true;
			} else {
				if (this.valorMaximoBicicleta < mapa.getValue())
					this.valorMaximoBicicleta = mapa.getValue();

				if (valorMinimoBicicleta > mapa.getValue())
					valorMinimoBicicleta = mapa.getValue();

			}

		}

	}

	private int valorMAximoRecorrido(HashMap<Recorrido, Integer> mapaRecorrido) {

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

	public void cerrarArchivo() {
		try {
			this.buffer.close();
			this.archivoYML.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
