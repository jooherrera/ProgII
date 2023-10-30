package Amazing;

public class PaqueteEspecial extends PaqueteAEntregar {
	private int adicional;
	private double porcentaje;
	private final int VOL_ADICIONAL_DUPLICADO = 5000;
	private final int VOL_ADICIONAL = 3000;

	public PaqueteEspecial(int id, int volumen, int precio, int porcentaje, int adicional, String dirEntrega) {
		super(id, volumen, precio, dirEntrega);
		this.porcentaje = porcentaje;
		this.adicional = adicional;
	}

	@Override
	public double devolverCostoTotal() {
		return calcularCostoTotal();
	}

	@Override
	public String toString() {
		return "PaqueteEspecial [porcentaje=" + porcentaje + ", adicional=" + adicional + ", VOL_ADICIONAL_DUPLICADO="
				+ VOL_ADICIONAL_DUPLICADO + ", VOL_ADICIONAL=" + VOL_ADICIONAL + "," + super.toString()
				+ "]";
	}

	// ---------------- PRIVATE-----------------------

	private double calcularCostoTotal() {
		if (super.tamanioMenorQue(VOL_ADICIONAL))
			return costoBase();
		if (super.tamanioMayorQue(VOL_ADICIONAL_DUPLICADO))
			return adicionalDuplicado();
		return adicionalSumado();
	}

	private double adicionalSumado() {
		return this.costoBase() + this.adicional();
	}

	private double adicionalDuplicado() {
		return this.costoBase() + (2 * this.adicional());
	}

	private double costoBase() {
		return super.devolverCostoTotal() * this.porcentaje();
	}

	private double porcentaje() {
		return (this.porcentaje /100) + 1 ;
	}

	private int adicional() {
		return this.adicional;
	}

}
