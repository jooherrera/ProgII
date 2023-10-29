package Amazing;

class Utilitario extends Transporte {
	private final int MAX_PAQUETES = 3;
	private int valorExtra;

	public Utilitario(String patente, int volMax, int valorViaje, int valorExtra) {
		super(patente, volMax, valorViaje);
		this.valorExtra = valorExtra;
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
	
	

	// ---------PRIVATE-----------
	private double calcularValorExtra() {
		return super.cantPaquetesCargados() > MAX_PAQUETES ? valorExtra : 0;
	}
}
