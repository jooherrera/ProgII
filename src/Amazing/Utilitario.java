class Utilitario extends Transporte {
    private static final int MAX_PAQUETES = 10;
    private int valorExtra;

    public Utilitario(String patente, int volMax, int valorViaje, int valorExtra) {
        super(patente, volMax, valorViaje);
        this.valorExtra = valorExtra;
    }

    @Override
    protected double consultarCostoEntrega() {
        return valorViaje + (canPaqActual > MAX_PAQUETES ? valorExtra : 0);
    }

    @Override
    protected int getMaxPaquetes() {
        return MAX_PAQUETES;
    }
}
