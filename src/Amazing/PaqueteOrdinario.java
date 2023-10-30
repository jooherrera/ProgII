package Amazing;

public class PaqueteOrdinario extends PaqueteAEntregar {
	private int costoEnvio;

	public PaqueteOrdinario(int id, int volumen, int precio, int costoEnvio, String dirEntrega) {
		super(id, volumen, precio, dirEntrega);
		this.costoEnvio = validarCostoEnvio(costoEnvio);
	}

	@Override
	public double devolverCostoTotal() {
		return this.calcularCostoTotal();
	}


	
	//---------------- PRIVATE-----------------------

	@Override
	public String toString() {
		return "PaqueteOrdinario [costoEnvio=" + costoEnvio + " , " + super.toString();
	}

	private double calcularCostoTotal() {
		return this.costoEnvio + super.devolverCostoTotal();
	}

	// Método para validar el costo de envío
	private int validarCostoEnvio(int costoEnvio) {
		if (costoEnvio < 0) {
			throw new Error("El costo de envío no puede ser negativo.");
		}
		return costoEnvio;
	}
}
