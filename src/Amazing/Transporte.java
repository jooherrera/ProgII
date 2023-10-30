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
		this.patente = validarPatente(patente);
		this.volMax = validarVolMax(volMax);
		this.volActual = volMax;
		this.valorViaje = validarValorViaje(valorViaje);
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

	// Validación de la patente
	private String validarPatente(String patente) {
		if (patente == null || patente.isEmpty()) {
			throw new Error("La patente no puede ser nula ni estar vacía.");
		}
		return patente;
	}

	// Validación del volumen máximo
	private int validarVolMax(int volMax) {
		if (volMax <= 0) {
			throw new Error("El volumen máximo debe ser mayor que cero.");
		}
		return volMax;
	}

	// Validación del valor del viaje
	private int validarValorViaje(int valorViaje) {
		if (valorViaje <= 0) {
			throw new Error("El valor del viaje debe ser mayor que cero.");
		}
		return valorViaje;
	}

}