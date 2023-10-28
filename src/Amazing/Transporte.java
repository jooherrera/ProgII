package Amazing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Transporte {
    protected String patente;
    protected int volMax;
    protected int volActual;
    protected int valorViaje;
    protected int cantPaqActual;
    protected Map<Integer, PaqueteAEntregar> cargamento;

    public Transporte(String patente, int volMax, int valorViaje) {
        this.patente = patente;
        this.volMax = volMax;
        this.volActual = 0;
        this.valorViaje = valorViaje;
        this.cantPaqActual = 0;
        this.cargamento = new HashMap<>();
    }


//    public String cargarTransporte(String patente) {
//     
//    }

    public double consultarCostoEntrega() {
    	return (double) this.valorViaje;
    }
    

    @Override
    public String toString() {
        return "Patente: " + patente + "\n" +
                "Volumen Máximo: " + volMax + "\n" +
                "Volumen Actual: " + volActual + "\n" +
                "Valor del Viaje: " + valorViaje + "\n" +
                "Cantidad de Paquetes Actual: " + cantPaqActual + "\n";
    }

    protected abstract boolean esIgual(Transporte otro);

    public abstract String cargarTransporte(List<Pedido> pedidos);
    
    
//        int paqueteVolumen = paquete.getVolumen();
//       
//        
//        if ( paquete.cabeEn(volActual) && tieneCapacidad()) {
//            cargamento.put(paquete.getId(), paquete);
//            volActual += paqueteVolumen;
//            cantPaqActual++;
//            paquete.cargarTransporte();
//            return true;
//        }
//        return false;
    

    public boolean descargarPaquete(int idPaquete) {
        if (cargamento.containsKey(idPaquete)) {
            PaqueteAEntregar paquete = cargamento.get(idPaquete);
            volActual -= paquete.getVolumen();
            cantPaqActual--;
            cargamento.remove(idPaquete);
            return true;
        }
        return false;
    }



    private int obtenerCantPaqActual() {
    	return this.cantPaqActual;
    }
    
//    private boolean tieneCapacidad() {
//    	return  cantPaqActual < g
//    }
    
    
}