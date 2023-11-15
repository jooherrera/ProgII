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
		return  "\n" + 
				"\t tipo: Utilitario,\n"
			  + "\t " + super.toString();
	}

	// ---------PRIVATE-----------

//	private int calcularValorExtra() {
//		return super.cantPaquetesCargados() > MAX_PAQUETES ? valorExtra : 0;
//	}

	// Método para validar el valor extra
	private int validarValorExtra(int valorExtra) {
		if (valorExtra < 0)
			throw new RuntimeException("El valor extra no puede ser negativo.");
		return valorExtra;
	}

	@Override
	public boolean equals(Object obj) {
		return esMismoTipo(obj) && super.equals(obj);
	}

	private boolean esMismoTipo(Object obj) {
		return obj != null && getClass() == obj.getClass();
	}

}
