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
		if (super.tamanioMenorIgualQue(VOL_ADICIONAL))
			return costoBase();
		if (super.tamanioMayorQue(VOL_ADICIONAL_DUPLICADO))
			return adicionalDuplicado();
		return adicionalSumado();

	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("{ adicional:").append(adicional).append(", porcentaje:").append(porcentaje).append(", ")
				.append(super.toString()).append("}");
		return sBuilder.toString();
	}

	// ---------------- PRIVATE-----------------------

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
		if (obj == null || getClass() != obj.getClass())
			return false;

		PaqueteEspecial paq = (PaqueteEspecial) obj;

		return paq.esMismoAdicional(this.adicional) && paq.esMismoPorcentaje(this.porcentaje) && super.equals(paq);
	}

	private boolean esMismoAdicional(int valor) {
		return this.adicional == valor;
	}

	private boolean esMismoPorcentaje(double valor) {
		return this.porcentaje == valor;
	}

}
