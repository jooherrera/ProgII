package Amazing;

public class PaqueteEspecial extends PaqueteAEntregar {
	private int adicional;
	private double porcentaje;
	private final int VOL_ADICIONAL_DUPLICADO = 5000;
	private final int VOL_ADICIONAL = 3000;

	public PaqueteEspecial(int id, int volumen, int precio, int porcentaje, int adicional, String dirEntrega) {
		super(id, volumen, precio, dirEntrega);
		this.porcentaje = validarPorcentaje(porcentaje);
		this.adicional = validarAdicional(adicional);
	}

	@Override
	public double devolverCostoTotal() {
		return calcularCostoTotal();
	}

	@Override
	public String toString() {
		return "PaqueteEspecial { adicional=" + adicional + ", porcentaje=" + porcentaje + ", VOL_ADICIONAL_DUPLICADO="
				+ VOL_ADICIONAL_DUPLICADO + ", VOL_ADICIONAL=" + VOL_ADICIONAL + ", " + super.toString();
	}

	// ---------------- PRIVATE-----------------------

	private double calcularCostoTotal() {
		if (super.tamanioMenorIgualQue(VOL_ADICIONAL))
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
		return (this.porcentaje / 100) + 1;
	}

	private int adicional() {
		return this.adicional;
	}

	// Método para validar el porcentaje
	private double validarPorcentaje(double porcentaje) {
		if (porcentaje < 0)
			throw new RuntimeException("El porcentaje no puede ser negativo.");
		return porcentaje;

	}

	// Método para validar el valor adicional
	private int validarAdicional(int adicional) {
		if (adicional < 0)
			throw new RuntimeException("El valor adicional no puede ser negativo.");
		return adicional;

	}

	// _______________NUEVOOOOOOOOOOOOOOOOOO

	@Override
	public boolean equals(Object obj) {
		return this.esMismoTipo(obj) && this.esIgual((PaqueteEspecial) obj);
	}

	private boolean esMismoTipo(Object obj) {
		return obj != null && getClass() == obj.getClass();
	}

	private boolean esIgual(PaqueteEspecial paq) {
		return paq.esMismoAdicional(this.adicional) && paq.esMismoPorcentaje(this.porcentaje) && super.equals(paq);
	}

	private boolean esMismoAdicional(int valor) {
		return this.adicional == valor;
	}

	private boolean esMismoPorcentaje(double valor) {
		return this.porcentaje == valor;
	}

}
