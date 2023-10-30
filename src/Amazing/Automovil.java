package Amazing;

class Automovil extends Transporte {

	@Override
	public String toString() {
		return "Automovil [maxPaq=" + maxPaq + ", VOLUMEN_MAX_POR_PAQUETE=" + VOLUMEN_MAX_POR_PAQUETE + ", "
				+ super.toString();
	}

	private int maxPaq;
	private final int VOLUMEN_MAX_POR_PAQUETE = 2000;

	public Automovil(String patente, int volMax, int valorViaje, int maxPaq) {
		super(patente, volMax, valorViaje);
		this.maxPaq = maxPaq;
	}

	@Override
	public boolean cargarPaquete(PaqueteAEntregar paquete) {
		boolean ret = false;
		if (paqueteAceptable(paquete))
			ret |= cargarPaquete(paquete, maxPaq);

		return ret;
	}

	@Override
	public double consultarCostoEntrega() {
		return super.consultarCostoEntrega();
	}

	@Override
	public boolean esIgual(Transporte otro) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//--------PRIVATE-----

	private boolean paqueteAceptable(PaqueteAEntregar paq) {
		return (paq instanceof PaqueteOrdinario) && paq.cabeEn(VOLUMEN_MAX_POR_PAQUETE);
	}

}