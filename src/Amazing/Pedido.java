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
		this.numPedido = validarNumPedido(numPedido);
		this.datosCliente = new Cliente(dni, nombre, direccion);
		this.carrito = new HashMap<Integer, PaqueteAEntregar>();
		this.facturacion = 0;
		this.cerrado = false;
	}

	public void agregarPaquete(int codigoPaquete, int volumen, int precio, int costoEnvio) {
		if (estaCerrado())
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

	public boolean eliminarPaquete(int id) {
		if (!existePaquete(id) || estaCerrado())
			return false;

		PaqueteAEntregar paquete = carrito.get(id);
		facturacion -= paquete.devolverCostoTotal();
		carrito.remove(id);
		return true;

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

		return cargados.toString();
	}

	public String duenio() {
		return obtenerNombreDelCliente();
	}

	public PaqueteAEntregar entregarPaquete(int id) {
		if (cerrado && carrito.containsKey(id)) {
			PaqueteAEntregar paquete = carrito.get(id);
			carrito.remove(id);
			return paquete;
		}
		return null;
	}

	public boolean faltanEntregar() {
		if (estaCerrado() && !estaVacio())
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "Pedido { numPedido=" + numPedido + ", datosCliente=" + datosCliente + ", carrito=" + carrito
				+ ", facturacion=" + facturacion + ", cerrado=" + cerrado + "}";
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

	private boolean paqueteCargado(boolean resp) {
		return resp;
	}

	private String obtenerNombreDelCliente() {
		return this.datosCliente.identificacion();
	}

	private double finalizarPedido() {
		cerrado = true;
		return (double) this.facturacion;
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

	private boolean existePaquete(int id) {
		return carrito.containsKey(id);
	}

	private String obtenerDatos(PaqueteAEntregar paq) {
		return " + [ " + codUnico() + " - " + paq.devolverCodigoUnico() + " ] " + paq.devolverDirEntrega() + "\n";
	}

	// Método para validar el número de pedido
	private int validarNumPedido(int numPedido) {
		if (numPedido <= 0)
			throw new RuntimeException("El número de pedido debe ser mayor que cero.");
		return numPedido;
	}

}