class Utilitario extends Transporte {
    private static final int MAX_PAQUETES = 3;
    private int valorExtra;

    public Utilitario(String patente, int volMax, int valorViaje, int valorExtra) {
        super(patente, volMax, valorViaje);
        this.valorExtra = valorExtra;
    }

    @Override
    public double consultarCostoEntrega() {
        return valorViaje + calcularValorExtra();
    }

    private double calcularValorExtra() {
    	return cantPaqActual > MAX_PAQUETES ? valorExtra : 0;
    }
    
    @Override
	public boolean cargarPaquete(PaqueteAEntregar paquete) {
//    	Los utilitarios tienen un valor extra que cobran a la empresa por cada viaje si la entrega
//    	supera los 3 paquetes. Lleva paquetes ordinarios y especiales.
		return false;
	}
}
