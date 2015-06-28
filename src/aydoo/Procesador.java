package aydoo;

public class Procesador {

	public static void main(String[] args) {

		ProcesadorEstadistico procesador;
		String path = args[0];
		Boolean esDemonio = null;

		if (args.length > 1)
			esDemonio = args[1].equals("demonio");

		if (esDemonio) {

			System.out.println("Ejecutando Modo Daemon");

			procesador = new ProcesadorEstadisticoDaemon(path);

		} else {
			System.out.println("Ejecutando Modo On-demand.");

			procesador = new ProcesadorEstadisticoOnDemand(path);

		}
	}
}
