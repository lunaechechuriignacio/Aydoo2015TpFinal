package aydoo;
import java.io.File;
public class Procesador {

	public static void main(String[] args) {

		ProcesadorEstadistico procesador;
		String path = args[0];
		Boolean esDemonio = null;
		File directorio = new File("salida");
		if (!directorio.exists()) {
			directorio.mkdir();
		}

		if (args.length > 1)
			esDemonio = args[1].equals("demonio");
		else
			esDemonio=false;

		if (esDemonio) {

			System.out.println("Ejecutando Modo Daemon");

			procesador = new ProcesadorEstadisticoDaemon(path);
			procesador.ejecutar();

		} else {
			System.out.println("Ejecutando Modo On-demand.");

			procesador = new ProcesadorEstadisticoOnDemand(path);
			procesador.ejecutar();
		}
	}
}
