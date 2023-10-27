package Amazing;

public class PaqueteEspecial extends PaqueteAEntregar {
	private int porcentaje, adicional;

	public PaqueteEspecial(int id, int volumen, int precio, int porcentaje, int adicional, String dirEntrega) {
		super(id, volumen, precio, dirEntrega);
		this.porcentaje = porcentaje;
		this.adicional = adicional;
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
