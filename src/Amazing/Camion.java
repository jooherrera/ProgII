package Amazing;

class Camion extends Transporte {
    private int adicXPaq;
	private final int VOLUMEN_MIN_POR_PAQUETE = 2000;


    
    public Camion(String patente, int volMax, int valorViaje, int adicXPaq) {
        super(patente, volMax, valorViaje);
        this.adicXPaq = adicXPaq;
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
	
	// ---------PRIVATE-----------
	private double calcularValorAdicional() {
		return super.cantPaquetesCargados() * this.adicXPaq;
	}
	
	private boolean paqueteAceptable(PaqueteAEntregar paq) {
		return (paq instanceof PaqueteEspecial) && paq.tamanioMayorQue(VOLUMEN_MIN_POR_PAQUETE);
	}
}