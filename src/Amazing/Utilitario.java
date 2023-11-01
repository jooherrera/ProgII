package Amazing;

class Utilitario extends Transporte {
	private final int MAX_PAQUETES = 3;
	private int valorExtra;

	public Utilitario(String patente, int volMax, int valorViaje, int valorExtra) {
		super(patente, volMax, valorViaje);
		this.valorExtra = validarValorExtra(valorExtra);
	}

	@Override
	public boolean cargarPaquete(PaqueteAEntregar paquete) {
		return super.cargarPaquete(paquete);
	}

	@Override
	public double consultarCostoEntrega() {
		return super.consultarCostoEntrega() + this.calcularValorExtra();
	}

	@Override
	public boolean esIgual(Transporte otro) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "Utilitario { MAX_PAQUETES=" + MAX_PAQUETES + ", valorExtra=" + valorExtra + ", " + super.toString();
	}

	// ---------PRIVATE-----------

	private double calcularValorExtra() {
		return super.cantPaquetesCargados() > MAX_PAQUETES ? valorExtra : 0;
	}

	// Método para validar el valor extra
	private int validarValorExtra(int valorExtra) {
		if (valorExtra < 0)
			throw new RuntimeException("El valor extra no puede ser negativo.");
		return valorExtra;
	}

}
