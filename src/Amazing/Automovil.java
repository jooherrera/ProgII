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

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("\n").append("\t tipo: Automovil,\n").append("\t ").append(super.toString());
		return sBuilder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		return super.equals(obj);
	}
	// --------PRIVATE-----

	private int validarCantidadMaxima(int cantMax) {
		if (cantMax < 1)
			throw new RuntimeException("Paramtro invalido.");
		return cantMax;
	}

}