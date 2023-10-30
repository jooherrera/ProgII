package Amazing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Pedido {
	private int numPedido;
	private Cliente datosCliente;
	private Map<Integer, PaqueteAEntregar> carrito;
	private double facturacion;
	private boolean cerrado;

	public Pedido(int numPedido, String nombre, String direccion, int dni) {
		this.numPedido = numPedido;
		this.datosCliente = new Cliente(dni, nombre, direccion);
		this.carrito = new HashMap<Integer, PaqueteAEntregar>();
		this.facturacion = 0;
		this.cerrado = false;
	}

	// Revisado
	public void agregarPaquete(int codigoPaquete, int volumen, int precio, int costoEnvio) {
		if (cerrado)
			throw new RuntimeException("El pedido está finalizado.");

		String dirEntrega = obtenerDirCliente();
		PaqueteOrdinario paquete = new PaqueteOrdinario(codigoPaquete, volumen, precio, costoEnvio, dirEntrega);

		carrito.put(codigoPaquete, paquete);

		
		facturacion += paquete.devolverCostoTotal();
	}

	public void agregarPaquete(int codigoPaquete, int volumen, int precio, int porcentaje, int adicional) {
		if (estaCerrado())
			throw new RuntimeException("El pedido está finalizado.");

		String dirEntrega = obtenerDirCliente();
		PaqueteEspecial paquete = new PaqueteEspecial(codigoPaquete, volumen, precio, porcentaje, adicional,
				dirEntrega);

		carrito.put(codigoPaquete, paquete);

		facturacion += paquete.devolverCostoTotal();
	}

	// Revisado
	public boolean eliminarPaquete(int id) {
		if(!carrito.containsKey(id))
			throw new RuntimeException("Codigo de paquete no registrado.");
		
		if (!estaCerrado() ) {
			PaqueteAEntregar paquete = carrito.get(id);
			facturacion -= paquete.devolverCostoTotal();
			carrito.remove(id);
			return true;
		}
		return false;
	}

	public String cargarPaquetes(Transporte t) {
		if (!estaCerrado())
			return "";

		StringBuilder cargados = new StringBuilder();

		Iterator<Map.Entry<Integer, PaqueteAEntregar>> iterator = carrito.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<Integer, PaqueteAEntregar> entry = iterator.next();
			PaqueteAEntregar paq = entry.getValue();
			if (paqueteCargado(t.cargarPaquete(paq))) {
				cargados.append(obtenerDatos(paq));
				iterator.remove();
			}
		}
		
//		for (PaqueteAEntregar paq : carrito.values())
//			if (paqueteCargado(t.cargarPaquete(paq))) {
//				cargados.append(obtenerDatos(paq));
//				this.quitarPaquete(paq);
//			}

		return cargados.toString();
	}

	private void quitarPaquete(PaqueteAEntregar paquete) {
		int id = paquete.codigoUnicoNum();
		carrito.remove(id);
		System.out.println("Eliminar");
		System.out.println(id);
	}

	private String obtenerDatos(PaqueteAEntregar paq) {
		return " + [ " + codUnico() + " - " + paq.devolverCodigoUnico() + " ] " + paq.devolverDirEntrega() + "\n";
	}

	@Override
	public String toString() {
		return "Pedido [numPedido=" + numPedido + ", datosCliente=" + datosCliente + ", carrito=" + carrito
				+ ", facturacion=" + facturacion + ", cerrado=" + cerrado + "]";
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
		if (estaCerrado() && !estaVacio())
			return true;
		return false;
	}

	public double cerrarPedido() {
		if (estaCerrado())
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

	private boolean estaCerrado() {
		return cerrado;
	}

	private boolean estaVacio() {
		return this.carrito.size() < 1;
	}

	private String obtenerDirCliente() {
		return datosCliente.domicilio();
	}

}