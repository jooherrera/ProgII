package Amazing;

public class PaqueteEspecial extends PaqueteAEntregar {
	private int porcentaje, adicional;
	private final int VOL_ADICIONAL_DUPLICADO = 5000;
	private final int VOL_ADICIONAL = 3000;

	public PaqueteEspecial(int id, int volumen, int precio, int porcentaje, int adicional, String dirEntrega) {
		super(id, volumen, precio, dirEntrega);
		this.porcentaje = porcentaje;
		this.adicional = adicional;
	}

	@Override
	public int devolverCostoTotal() {
		return calcularCostoTotal();
	}

	@Override
	public String toString() {
		return null;
	}

	// ---------------- PRIVATE-----------------------

	private int calcularCostoTotal() {
		if (super.tamanioMenorQue(VOL_ADICIONAL))
			return costoBase();
		if (super.tamanioMayorQue(VOL_ADICIONAL_DUPLICADO))
			return adicionalDuplicado();
		return adicionalSumado();
	}

	private int adicionalSumado() {
		return this.costoBase() + this.adicional();
	}

	private int adicionalDuplicado() {
		return this.costoBase() + (2 * this.adicional());
	}

	private int costoBase() {
		return super.devolverCostoTotal() * this.porcentaje();
	}

	private int porcentaje() {
		return this.porcentaje;
	}

	private int adicional() {
		return this.adicional;
	}

}
