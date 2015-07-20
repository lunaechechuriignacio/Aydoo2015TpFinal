package aydoo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ListIterator;

public class ArchivoDeSalidaYML {
	private String nombreArchivo;
	private FileWriter archivoYML = null;
	private BufferedWriter buffer = null;
	private PrintWriter escritura = null;
	private String extension = ".yml";
	private String path;

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
			ex.printStackTrace();
		}
	}

	public void escribirEnArchivo(GeneradorDeInforme generadorInforme) {
		this.crearArchivo();
		escritura = new PrintWriter(buffer);
				
		escritura.println("Bicicletas mas usadas:");
		this.escribirMaximoValorDeBicicleta(generadorInforme, escritura);
		
		escritura.println("Bicicletas menos usadas:");
		this.escribirMinimoValorDeBicicleta(generadorInforme, escritura);
		
		escritura.println("Recorrido mas realizado:");
		this.escribirRecorridoMasUtilizado(generadorInforme, escritura);

		escritura.println("Tiempo promedio de uso: "+ String.valueOf(generadorInforme.getTiempoPromedio()));

		escritura.close();
	}

	private void escribirRecorridoMasUtilizado(GeneradorDeInforme generadorInforme, PrintWriter escritura) {

		Recorrido recorrido = null;
		List<Recorrido> recorridos = generadorInforme.getListaMaximosRecorridos();
		ListIterator<Recorrido> iterador = recorridos.listIterator();
		
		while (iterador.hasNext()){
		
				recorrido = iterador.next();
				
				escritura.println("id origen: "
						+ recorrido.getEstacionOrigen().getId());
				escritura.println("id destino: "
						+ recorrido.getEstacionDestino().getId());
		}

	}

	private void escribirMinimoValorDeBicicleta(GeneradorDeInforme generadorInforme, PrintWriter escritura) {

		Bicicleta bicicleta = null;
		List<Bicicleta> bicicletas = generadorInforme.getListaBicicletasMinimos();
		ListIterator<Bicicleta> iterador = bicicletas.listIterator();
		
		while (iterador.hasNext()){
		
			bicicleta = iterador.next();
				
			escritura.println("id: " + bicicleta.getId());
		}
	}

	private void escribirMaximoValorDeBicicleta(GeneradorDeInforme generadorInforme,PrintWriter escritura2) {

		Bicicleta bicicleta = null;
		List<Bicicleta> bicicletas = generadorInforme.getListaBicicletasMaximos();
		ListIterator<Bicicleta> iterador = bicicletas.listIterator();
		
		while (iterador.hasNext()){
		
			bicicleta = iterador.next();
				
			escritura.println("id: " + bicicleta.getId());
		}

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
