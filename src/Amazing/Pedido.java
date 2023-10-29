package Amazing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pedido {
	private int numPedido;
	private Cliente datosCliente;
	private Map<Integer, PaqueteAEntregar> carrito;
	private int facturacion;
	private boolean cerrado;

	public Pedido(int numPedido, String nombre, String direccion, int dni) {
		this.numPedido = numPedido;
		this.datosCliente = new Cliente(dni, nombre, direccion);
		this.carrito = new HashMap<Integer, PaqueteAEntregar>();
		this.facturacion = 0;
		this.cerrado = false;
	}

	// Revisado
	public void agregarPaquete(int codigoPaquete, PaqueteAEntregar paquete) {
		if (cerrado)
			throw new RuntimeException("El pedido está finalizado.");

		carrito.put(codigoPaquete, paquete);
		facturacion += paquete.devolverCostoTotal();

	}

	// Revisado
	public boolean eliminarPaquete(int id) {
		if (!cerrado && carrito.containsKey(id)) {
			PaqueteAEntregar paquete = carrito.get(id);
			facturacion -= paquete.devolverCostoTotal();
			carrito.remove(id);
			return true;
		}
		return false;
	}

	public String cargarPaquetes(Transporte t) {
		if (!estaDisponible())
			return "";
		StringBuilder cargados = new StringBuilder();

		for (PaqueteAEntregar paq : carrito.values())
			if (paqueteCargado(t.cargarPaquete(paq)))

				cargados.append(obtenerDatos(paq));

		return cargados.toString();
	}

	private String obtenerDatos(PaqueteAEntregar paq) {
		return " + [ " + codUnico() + " - " + paq.devolverCodigoUnico() + " ] " + paq.devolverDirEntrega() + "\n";
	}

	private boolean paqueteCargado(boolean resp) {
		return resp;
	}

	public String duenio() {
		return obtenerNombreDelCliente();
	}

	private String obtenerNombreDelCliente() {
		return this.datosCliente.identificacion();
	}

//	public List<PaqueteOrdinario> obtenerPaquetesOrdinarios() {
//		if (!estaDisponible())
//			return null;
//		return paquetesOrdinarios();
//	}
//
//	public List<PaqueteEspecial> obtenerPaquetesEspeciales() {
//		if (!estaDisponible())
//			return null;
//		return paquetesEspeciales();
//	}

//	public List<PaqueteAEntregar> obtenerpaquetesMinVolumen(int volumen) {
//		if(!estaDisponible())
//			return null;
//		return paquetesPorVolumenMin(volumen);
//	}

	public PaqueteAEntregar entregarPaquete(int id) {
		if (cerrado && carrito.containsKey(id)) {
			PaqueteAEntregar paquete = carrito.get(id);
			carrito.remove(id);
			return paquete;
		}
		return null;
	}

//	public double devolverTotalAPagar() {
//		return facturacion;
//	}

//	public String consultarPaquetesNoEntregados() {
//		StringBuilder paquetesNoEntregados = new StringBuilder();
//		for (PaqueteAEntregar paquete : carrito.values()) {
//			paquetesNoEntregados.append(paquete.toString()).append("\n");
//		}
//		return paquetesNoEntregados.toString();

	public boolean faltanEntregar() {
		if (!estaDisponible() && !estaVacio())
			return true;
		return false;
	}

	public double cerrarPedido() {
		if (!estaDisponible())
			throw new RuntimeException("Pedido con código: " + this.numPedido + " no está disponible.");
		return finalizarPedido();
	}
	
	public String devolverCodigoUnico() {
		return "" + this.numPedido;
	}

	// --------------PRIVATE---------------
	private String codUnico() {
		return "" + this.numPedido;
	}

	private double finalizarPedido() {
		cerrado = true;
		return (double) this.facturacion;
	}

	private boolean estaDisponible() {
		return !cerrado;
	}

	private boolean estaVacio() {
		return this.carrito.size() < 1;
	}

	@Override
	public String toString() {
		return "Número de Pedido: " + numPedido + "\n" + "Cliente: " + datosCliente.toString() + "\n"
				+ "Facturación Total: " + facturacion + "\n" + "Estado: " + (cerrado ? "Cerrado" : "Abierto") + "\n";
	}
}