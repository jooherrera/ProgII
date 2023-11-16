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
		this.volActual = this.volMax;
		this.cargamento.clear();
		return (double) this.valorViaje;
	}

	public boolean cargarPaquete(PaqueteAEntregar paquete) {
		return this.cargar(paquete);
	}

	public boolean cargarPaquete(PaqueteAEntregar paquete, int limite) {
		if (cantPaquetes() < limite)
			return this.cargar(paquete);
		return false;
	}

	public int cantPaquetesCargados() {
		return this.cantPaquetes();
	}

	public String obtenerPatente() {
		return this.patente;
	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("capacidad: ").append(volMax).append(",\n").append("\t capacidad disponible: ")
				.append(volActual).append(",\n\t").append(" cantidad de paquetes cargados: ").append(cargamento.size())
				.append("\n\t");
		return sBuilder.toString();
	}
	// ---------------PRIVATE----------------

	private boolean cargar(PaqueteAEntregar paquete) {
		if (paquete.estaCargado())
			return false;
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

	private int cantPaquetes() {
		return this.cargamento.size();
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
		if (obj == null || getClass() != obj.getClass())
			return false;

		Transporte transporte = (Transporte) obj;

		return transporte.esMismoCargamento(this.cargamento);
	}

	private boolean esMismoCargamento(Map<Integer, PaqueteAEntregar> carga) {

		if(!esMismaCantidadCargamento(carga) || carga.size() == 0 || this.cargamento.size() == 0)
			return false;
		Map<String, Integer> frecuenciaConjunto1 = contarFrecuencia(this.cargamento);
		Map<String, Integer> frecuenciaConjunto2 = contarFrecuencia(carga);

		return frecuenciaConjunto1.equals(frecuenciaConjunto2);

	}

	private static Map<String, Integer> contarFrecuencia(Map<Integer, PaqueteAEntregar> cargamento) {
		Map<String, Integer> frecuencia = new HashMap<>();
		for (PaqueteAEntregar paquete : cargamento.values()) 
			frecuencia.put(paquete.toString(), frecuencia.getOrDefault(paquete, 0) + 1);
		return frecuencia;
	}

	private boolean esMismaCantidadCargamento(Map<Integer, PaqueteAEntregar> carga) {
		return this.cantPaquetes() == carga.size();
	}

}