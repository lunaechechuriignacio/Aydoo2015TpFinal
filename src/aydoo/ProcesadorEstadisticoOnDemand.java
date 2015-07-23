package aydoo;

public class ProcesadorEstadisticoOnDemand implements ProcesadorEstadistico {

	private String pathEntrada;
	private String pathDeSalidaInforme="salida/";
	private ProcesadorDeDatos procesador;
	
	public ProcesadorEstadisticoOnDemand(String pathEntrada) {
		
		this.pathEntrada = pathEntrada;
		this.procesador = new ProcesadorDeDatos();
		
	}
	
	@Override
	public void ejecutar(){
				
		try {
			this.procesador.generarInforme(this.pathEntrada,this.pathDeSalidaInforme);
		    this.procesador.ejecutar();
		} catch (Exception e) {

			System.out.println( "El directorio se encuentra vacio" );
		}

	}

}
