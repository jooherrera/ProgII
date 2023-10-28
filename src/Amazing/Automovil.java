package Amazing;

import java.util.List;

class Automovil extends Transporte {
	
    private int maxPaq;
    private static final int VOLUMEN_MAX_POR_PAQUETE = 2000;

    public Automovil(String patente, int volMax, int valorViaje, int maxPaq) {
        super(patente, volMax, valorViaje);
        this.maxPaq = maxPaq;
    }
    
    @Override
    public boolean esIgual(Transporte otro) {
        return this.getClass() == otro.getClass() &&
                this.volMax == otro.volMax &&
                this.valorViaje == otro.valorViaje &&
                this.cantPaqActual == otro.cantPaqActual &&
                this.cargamento.equals(otro.cargamento);
    }


	
    @Override
	public String cargarTransporte(List<Pedido> pedidos) {
//    	 Los comunes tienen un límite máximo de paquetes. Solo lleva paquetes ordinarios
//    	 menores a 2000 de volumen cada uno
		return "";
	}

    @Override
    public String toString(){
    	return "";
    }

}