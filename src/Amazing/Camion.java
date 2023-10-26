class Camion extends Transporte {
    private static final int MAX_PAQUETES = Integer.MAX_VALUE;
    private int adicXPaq;

    public Camion(String patente, int volMax, int valorViaje, int adicXPaq) {
        super(patente, volMax, valorViaje);
        this.adicXPaq = adicXPaq;
    }

    @Override
    protected double consultarCostoEntrega() {
        return valorViaje + canPaqActual * adicXPaq;
    }

    @Override
    protected int getMaxPaquetes() {
        return MAX_PAQUETES;
    }
}