public class Bicicleta {
	private String id;

	public Bicicleta(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public boolean estadoCompararBicicletas(Bicicleta bicicleta) {
		return this.id == bicicleta.getId();

	}
}
