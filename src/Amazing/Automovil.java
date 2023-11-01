package Amazing;

class Automovil extends Transporte {
	private int maxPaq;
	private final int VOLUMEN_MAX_POR_PAQUETE = 2000;

	public Automovil(String patente, int volMax, int valorViaje, int maxPaq) {
		super(patente, volMax, valorViaje);
		this.maxPaq = this.validarCantidadMaxima(maxPaq);
	}

	@Override
	public boolean cargarPaquete(PaqueteAEntregar paquete) {
		boolean ret = false;
		if (this.paqueteAceptable(paquete))
			ret |= super.cargarPaquete(paquete, maxPaq);
		return ret;
	}
	
	@Override
	public boolean esIgual(Transporte otro) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "Automovil { maxPaq=" + maxPaq + ", VOLUMEN_MAX_POR_PAQUETE=" + VOLUMEN_MAX_POR_PAQUETE + ", "
				+ super.toString();
	}

	// --------PRIVATE-----

	private int validarCantidadMaxima(int cantMax) {
		if (cantMax < 1)
			throw new RuntimeException("Paramtro invalido.");
		return cantMax;
	}

	private boolean paqueteAceptable(PaqueteAEntregar paq) {
		return (paq instanceof PaqueteOrdinario) && paq.cabeEn(VOLUMEN_MAX_POR_PAQUETE);
	}

}