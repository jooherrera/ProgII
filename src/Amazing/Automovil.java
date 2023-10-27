package Amazing;

class Automovil extends Transporte {
    private static final int MAX_PAQUETES = 5;

    public Automovil(String patente, int volMax, int valorViaje, int maxPaq) {
        super(patente, volMax, valorViaje);
    }

    @Override
    protected double consultarCostoEntrega() {
        return valorViaje;
    }

    @Override
    protected int getMaxPaquetes() {
        return MAX_PAQUETES;
    }
}