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
//		if (paquete.cabeEn(volActual)) {
//			this.volActual -= paquete.cargarATransporte();
//			this.cargamento.put(Integer.parseInt(paquete.devolverCodigoUnico()), paquete);
//			return true;
//		}
//		return false;
		return cargar(paquete);
	}
	
	public boolean cargarPaquete(PaqueteAEntregar paquete,int limite) {
		if(hayCapacidad(limite))
			return cargar(paquete);
		return false;
	}
	
	private boolean cargar(PaqueteAEntregar paquete) {
		if (paquete.cabeEn(volActual)) {
			this.volActual -= paquete.cargarATransporte();
			this.cargamento.put(Integer.parseInt(paquete.devolverCodigoUnico()), paquete);
			return true;
		}
		return false;
	}
	


	@Override
	public String toString() {
		return "Transporte [patente=" + patente + ", volMax=" + volMax + ", volActual=" + volActual + ", valorViaje="
				+ valorViaje + ", cargamento=" + cargamento + "]";
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
	
	public void imprimirCargamento() {
		System.out.println(cargamento);
	}

    private boolean hayCapacidad(int limite) {
    	return  cantPaquetes() < limite;
    }

}