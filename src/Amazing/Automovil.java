package Amazing;

public class Automovil extends Transporte {
	private int maxPaq;
	private final int VOLUMEN_MAX_POR_PAQUETE = 1999;

	public Automovil(String patente, int volMax, int valorViaje, int maxPaq) {
		super(patente, volMax, valorViaje);
		this.maxPaq = this.validarCantidadMaxima(maxPaq);
	}

	@Override
	public boolean cargarPaquete(PaqueteAEntregar paquete) {
		boolean ret = false;
		if ((paquete instanceof PaqueteOrdinario) && paquete.cabeEn(VOLUMEN_MAX_POR_PAQUETE))
			ret |= super.cargarPaquete(paquete, maxPaq);
		return ret;
	}
	
//	@Override
//	public double consultarCostoEntrega() {
//		return super.consultarCostoEntrega();
//	}

	@Override
	public String toString() {
		return "\n"
				+ "\t tipo: Automovil,\n"
				+ "\t " + super.toString();
	}

	// --------PRIVATE-----

	private int validarCantidadMaxima(int cantMax) {
		if (cantMax < 1)
			throw new RuntimeException("Paramtro invalido.");
		return cantMax;
	}

//	private boolean paqueteAceptable(PaqueteAEntregar paq) {
//		return (paq instanceof PaqueteOrdinario) && paq.cabeEn(VOLUMEN_MAX_POR_PAQUETE);
//	}

	@Override
	public boolean equals(Object obj) {
		return esMismoTipo(obj) && super.equals(obj);
	}

	private boolean esMismoTipo(Object obj) {
		return obj != null && getClass() == obj.getClass();
	}

}