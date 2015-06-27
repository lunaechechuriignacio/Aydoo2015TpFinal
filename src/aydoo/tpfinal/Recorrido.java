package aydoo.tpfinal;

public class Recorrido {
	private Estacion estacionOrigen = null;
	private Estacion estacionDestino = null;
	private String fechaOrigen, fechaDestino;

	public Recorrido(Estacion estacionOrigen, Estacion estacionDestino) {
		this.estacionOrigen = estacionOrigen;
		this.estacionDestino = estacionDestino;

	}

	public Estacion getEstacionOrigen() {
		return estacionOrigen;
	}

	public Estacion getEstacionDestino() {
		return estacionDestino;
	}

	@Override
	public boolean equals(Object obj) {
		Recorrido recorrido = (Recorrido) obj;
		boolean hola = (this.estacionDestino.getId()
				.equals(recorrido.estacionDestino.getId()))
				&& (this.estacionOrigen.getId().equals(recorrido.estacionOrigen
						.getId()));
		return hola;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 33 * this.estacionDestino.getId().hashCode()
				+ this.estacionOrigen.getId().hashCode();
		return result;
	}

	public String getFechaOrigen() {
		return fechaOrigen;
	}

	public void setFechaOrigen(String fechaOrigen) {
		this.fechaOrigen = fechaOrigen;
	}

	public String getFechaDestino() {
		return fechaDestino;
	}

	public void setFechaDestino(String fechaDestino) {
		this.fechaDestino = fechaDestino;
	}

}
