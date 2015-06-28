package aydoo;

import java.io.IOException;

public class ProcesadorEstadisticoOnDemand implements ProcesadorEstadistico {

	private String pathEntrada;
	private String pathDeSalidaInforme="salidas/onDemand/YML/";
	private ProcesadorDeDatos procesador;
	
	public ProcesadorEstadisticoOnDemand(String pathEntrada) {
		
		this.pathEntrada = pathEntrada;
		this.procesador = new ProcesadorDeDatos();
		
	}
	
	@Override
	public void ejecutar(){
				
		try {
			this.procesador.generarInforme(this.pathEntrada,this.pathDeSalidaInforme, false);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
