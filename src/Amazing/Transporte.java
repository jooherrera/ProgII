package Amazing;

import java.util.HashMap;
import java.util.Map;

public abstract class Transporte {
	private String patente;
	private int volMax;
	private int volActual;
	private int valorViaje;
	private Map<Integer, PaqueteAEntregar> cargamento;

	public Transporte(String patente, int volMax, int valorViaje) {
		this.patente = patente;
		this.volMax = volMax;
		this.volActual = volMax;
		this.valorViaje = valorViaje;
//		this.cantPaqActual = 0;
//		this.facturacion = 0;
		this.cargamento = new HashMap<>();
	}

//    public String cargarTransporte(String patente) {
//     
//    }

	public double consultarCostoEntrega() {
		if (estaVacio())
			throw new RuntimeException("Transporte vacío.");
		this.repartir();
		return (double) this.valorViaje;
	}
	
	private void repartir() {
		this.volActual = this.volMax;
		this.cargamento.clear();
	}


	public boolean cargarPaquete(PaqueteAEntregar paquete) {
		if (paquete.cabeEn(volActual)) {
			this.volActual -= paquete.cargarATransporte();
			this.cargamento.put(Integer.parseInt(paquete.devolverCodigoUnico()), paquete);
			return true;
		}
		return false;
	}
	
	public boolean cargarPaquete(PaqueteAEntregar paquete,int limite) {
		if(hayCapacidad(limite))
			return this.cargarPaquete(paquete);
		return false;
	}
	
	@Override
	public String toString() {
		return "Patente: " + patente + "\n" + "Volumen Máximo: " + volMax + "\n" + "Volumen Actual: " + volActual + "\n"
				+ "Valor del Viaje: " + valorViaje + "\n" + "Cantidad de Paquetes Actual: " + cantPaquetes() + "\n";
	}

	public int cantPaquetesCargados() {
		return this.cantPaquetes();
	}
	
	public abstract boolean esIgual(Transporte otro);


	private boolean estaVacio() {
		return cantPaquetes() < 1;
	}
	
	private int cantPaquetes() {
		return this.cargamento.size();
	}

    private boolean hayCapacidad(int limite) {
    	return  cantPaquetes() < limite;
    }

}