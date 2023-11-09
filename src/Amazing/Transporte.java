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
		this.cargamento = new HashMap<>();
	}

	public double consultarCostoEntrega() {
		if (this.estaVacio())
			throw new RuntimeException("Transporte vacío.");
		this.repartir();
		return (double) this.valorViaje;
	}

	public boolean cargarPaquete(PaqueteAEntregar paquete) {
		return this.cargar(paquete);
	}

	public boolean cargarPaquete(PaqueteAEntregar paquete, int limite) {
		if (this.hayCapacidad(limite))
			return this.cargar(paquete);
		return false;
	}

	public int cantPaquetesCargados() {
		return this.cantPaquetes();
	}

	@Override
	public String toString() {
		return "patente=" + patente + ", volMax=" + volMax + ", volActual=" + volActual + ", valorViaje=" + valorViaje
				+ ", cargamento=" + cargamento + " }";
	}

	// ---------------PRIVATE----------------

	private boolean cargar(PaqueteAEntregar paquete) {
		if (paquete.cabeEn(volActual)) {
			this.volActual -= paquete.cargarATransporte();
			this.cargamento.put(Integer.parseInt(paquete.devolverCodigoUnico()), paquete);
			return true;
		}
		return false;
	}

	private boolean estaVacio() {
		return this.cantPaquetes() < 1;
	}

	private void repartir() {
		this.volActual = this.volMax;
		this.cargamento.clear();
	}

	private int cantPaquetes() {
		return this.cargamento.size();
	}

	private boolean hayCapacidad(int limite) {
		return this.cantPaquetes() < limite;
	}

	// Validación de la patente
	private String validarPatente(String patente) {
		if (patente == null || patente.isEmpty())
			throw new RuntimeException("La patente no puede ser nula ni estar vacía.");
		return patente;
	}

	// Validación del volumen máximo
	private int validarVolMax(int volMax) {
		if (volMax <= 0)
			throw new RuntimeException("El volumen máximo debe ser mayor que cero.");
		return volMax;
	}

	// Validación del valor del viaje
	private int validarValorViaje(int valorViaje) {
		if (valorViaje <= 0)
			throw new RuntimeException("El valor del viaje debe ser mayor que cero.");
		return valorViaje;
	}

	// NUEVOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
	@Override
	public boolean equals(Object obj) {
		return esMismoTipo(obj) && this.esIgual((Transporte) obj);
	}

	private boolean esIgual(Transporte transporte) {
		return !transporte.esMismaPatente(this.patente) && transporte.esMismoCargamento(this.cargamento);
	}

	private boolean esMismoTipo(Object obj) {
		return obj != null && getClass() == obj.getClass();
	}

	private boolean esMismaPatente(String pat) {
		return this.patente.equals(pat);
	}

	private boolean esMismoCargamento(Map<Integer, PaqueteAEntregar> carga) {
		boolean ret = false;
		if (esMismaCantidadCargamento(carga))
			for (PaqueteAEntregar paq : carga.values()) {
				ret |= algunParecidoA(paq);
			}
		return ret;
	}

	private boolean algunParecidoA(PaqueteAEntregar paq) {
		boolean ret = false;
		for (PaqueteAEntregar elem : this.cargamento.values()) {
			ret |= elem.equals(paq);
		}
		return ret;
	}

	private boolean esMismaCantidadCargamento(Map<Integer, PaqueteAEntregar> carga) {
		return this.cantPaquetes() == carga.size();
	}

}