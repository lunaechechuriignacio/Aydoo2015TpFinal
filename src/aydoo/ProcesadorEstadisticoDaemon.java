package aydoo;

import java.io.IOException;


public class ProcesadorEstadisticoDaemon implements ProcesadorEstadistico {
	
	private String pathEntrada;
	private String pathDeSalidaInforme="salida/";
	private ProcesadorDeDatos procesador;
	
	public ProcesadorEstadisticoDaemon(String pathEntrada) {
		
		this.pathEntrada = pathEntrada;
		this.procesador = new ProcesadorDeDatos();
		
	}
	

	@Override
	public void ejecutar() {

		SupervisadorDeDirectorio supervisador = new SupervisadorDeDirectorio(this.pathEntrada, this);
		int ciclo=0;
		do{
			ciclo++;
		supervisador.correrSupervisadorDeDirectorio(10000);
		if  (ciclo==9999)
			ciclo=0;
		}while(ciclo<10000);
	}

	public void generarInforme(String fullPath) {

		try {
			this.procesador.generarInforme(this.pathEntrada,
					this.pathDeSalidaInforme);
			procesador.ejecutar(fullPath,this.pathEntrada);
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
