package Amazing;

public class PaqueteOrdinario extends PaqueteAEntregar {
	private int costoEnvio;

	public PaqueteOrdinario(int id, int volumen, int precio, int costoEnvio, String dirEntrega) {
		super(id, volumen, precio, dirEntrega);
		this.costoEnvio = costoEnvio;
	}

	@Override
	public int devolverCostoTotal() {
		return 0;
	}

	@Override
	public String toString() {
		return null;
	}

}
