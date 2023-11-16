package Amazing;

public class Camion extends Transporte {
	private int adicXPaq;
	private final int VOLUMEN_MIN_POR_PAQUETE = 2000;

	public Camion(String patente, int volMax, int valorViaje, int adicXPaq) {
		super(patente, volMax, valorViaje);
		this.adicXPaq = this.validarAdicionalPorPaquete(adicXPaq);
	}

	@Override
	public boolean cargarPaquete(PaqueteAEntregar paquete) {
		boolean ret = false;
		if (paquete.tamanioMayorQue(VOLUMEN_MIN_POR_PAQUETE))
			ret |= super.cargarPaquete(paquete);
		return ret;
	}

	@Override
	public double consultarCostoEntrega() {
		return super.cantPaquetesCargados() * this.adicXPaq + super.consultarCostoEntrega();
	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("\n").append("\t tipo: Camion,\n").append("\t ").append(super.toString());
		return sBuilder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		return super.equals(obj);
	}
	// ---------PRIVATE-----------

	private int validarAdicionalPorPaquete(int adicXPaq) {
		if (adicXPaq < 0)
			throw new RuntimeException("El valor adicional por paquete no puede ser negativo.");
		return adicXPaq;
	}

}