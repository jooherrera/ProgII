package Amazing;

class Camion extends Transporte {
	private int adicXPaq;
	private final int VOLUMEN_MIN_POR_PAQUETE = 2000;

	public Camion(String patente, int volMax, int valorViaje, int adicXPaq) {
		super(patente, volMax, valorViaje);
		this.adicXPaq = this.validarAdicionalPorPaquete(adicXPaq);
	}

	@Override
	public boolean cargarPaquete(PaqueteAEntregar paquete) {
		boolean ret = false;
		if (paqueteAceptable(paquete))
			ret |= super.cargarPaquete(paquete);
		return ret;
	}

	@Override
	public double consultarCostoEntrega() {
		return this.calcularValorAdicional() + super.consultarCostoEntrega();
	}

	@Override
	public boolean esIgual(Transporte otro) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "Camion { adicXPaq=" + adicXPaq + ", VOLUMEN_MIN_POR_PAQUETE=" + VOLUMEN_MIN_POR_PAQUETE + ", "
				+ super.toString();
	}

	// ---------PRIVATE-----------
	private double calcularValorAdicional() {
		return super.cantPaquetesCargados() * this.adicXPaq;
	}

	private boolean paqueteAceptable(PaqueteAEntregar paq) {
		return (paq instanceof PaqueteEspecial) && paq.tamanioMayorQue(VOLUMEN_MIN_POR_PAQUETE);
	}

	// Método para validar el valor adicional por paquete
	private int validarAdicionalPorPaquete(int adicXPaq) {
		if (adicXPaq < 0)
			throw new RuntimeException("El valor adicional por paquete no puede ser negativo.");
		return adicXPaq;
	}
}