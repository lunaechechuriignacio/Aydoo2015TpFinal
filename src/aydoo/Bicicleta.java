package aydoo;

public class Bicicleta {
	private String id;

	public Bicicleta(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	
	@Override
	public boolean equals(Object obj) {
		Bicicleta bicicleta= (Bicicleta) obj;
		
		return this.id.equals(bicicleta.getId());
	}
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	
}
