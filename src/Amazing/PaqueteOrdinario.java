package Amazing;

public class PaqueteOrdinario extends PaqueteAEntregar {
	private int costoEnvio;

	public PaqueteOrdinario(int id, int volumen, int precio, int costoEnvio, String dirEntrega) {
		super(id, volumen, precio, dirEntrega);
		this.costoEnvio = validarCostoEnvio(costoEnvio);
	}

	@Override
	public double devolverCostoTotal() {
		return this.costoEnvio + super.devolverCostoTotal();
	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("costoEnvio:").append(costoEnvio).append(", ").append(super.toString()).append("}");
		return sBuilder.toString();
	}

	// ---------------- PRIVATE-----------------------

//	private double calcularCostoTotal() {
//		return this.costoEnvio + super.devolverCostoTotal();
//	}

	// Método para validar el costo de envío
	private int validarCostoEnvio(int costoEnvio) {
		if (costoEnvio < 1)
			throw new RuntimeException("El costo de envío no puede ser negativo.");
		return costoEnvio;
	}

	// _______________NUEVOOOOOOOOOOOOOOOOOO

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;

		PaqueteOrdinario paq = (PaqueteOrdinario) obj;

		return paq.esMismoCostoEnvio(this.costoEnvio) && super.equals(paq);
	}

	private boolean esMismoCostoEnvio(int valor) {
		return this.costoEnvio == valor;
	}

}
