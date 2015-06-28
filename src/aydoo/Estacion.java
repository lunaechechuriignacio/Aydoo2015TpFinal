package aydoo;

public class Estacion {
	private String id;
	private String nombre;

	public Estacion(String nombre, String id) {

		this.nombre = nombre;
		this.id = id;

	}

	public String getId() {
		return id;
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre=nombre;
	}

	public boolean estadoCompararEstacion(Estacion estacion) {
		return this.id == estacion.getId();
	}

	public void setId(String id) {
		this.id=id;
	}
}
