package aydoo.tpfinal;

import java.util.Scanner;

public class MainPedidoDeParametros {

	private static void ejecutarProcesadores(String pathOndemand, String pathDaemon) {
		ProcesadorEstadistico daemon = new ProcesadorEstadisticoDaemon(	pathDaemon);
		ProcesadorEstadistico ondemand = new ProcesadorEstadisticoOnDemand(
			pathOndemand);
		daemon.ejecutar();
		ondemand.ejecutar();
	}

	public static void main(String[] args) {
		String entradaOndemand = "";
		String entradaDaemon = "";
		Scanner entradaEscaner;
		do {
			System.out
					.println("Por favor introduzca el path del procesador Ondemand:");

			entradaEscaner = new Scanner(System.in);
			entradaOndemand = entradaEscaner.nextLine();
		} while (entradaOndemand == " ");

		do {
			System.out
					.println("Por favor introduzca el path del procesador Daemon:");

			entradaEscaner = new Scanner(System.in);
			entradaDaemon = entradaEscaner.nextLine();
		} while (entradaDaemon == " ");
		
		entradaEscaner.close();
		ejecutarProcesadores( entradaOndemand, entradaDaemon);

	}

}
