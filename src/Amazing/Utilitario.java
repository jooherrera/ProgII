package Amazing;

public class Utilitario extends Transporte {
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
		int extra = super.cantPaquetesCargados() > MAX_PAQUETES ? valorExtra : 0;
		return extra + super.consultarCostoEntrega();
	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("\n").append("\t tipo: Utilitario,\n").append("\t ").append(super.toString());
		return sBuilder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		return super.equals(obj);
	}
	// ---------PRIVATE-----------

	private int validarValorExtra(int valorExtra) {
		if (valorExtra < 0)
			throw new RuntimeException("El valor extra no puede ser negativo.");
		return valorExtra;
	}

}
