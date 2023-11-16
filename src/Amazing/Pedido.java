package Amazing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;

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

	public void agregarPaquete(PaqueteAEntregar paquete) {
		if (estaCerrado())
			throw new RuntimeException("El pedido está finalizado.");

		carrito.put(paquete.codigoUnicoNum(), paquete);

		facturacion += paquete.devolverCostoTotal();
	}

//	public void agregarPaquete(int codigoPaquete, int volumen, int precio, int costoEnvio) {
//		if (estaCerrado())
//			throw new RuntimeException("El pedido está finalizado.");
//
//		String dirEntrega = obtenerDirCliente();
//		PaqueteOrdinario paquete = new PaqueteOrdinario(codigoPaquete, volumen, precio, costoEnvio, dirEntrega);
//
//		carrito.put(codigoPaquete, paquete);
//
//		facturacion += paquete.devolverCostoTotal();
//	}
//
//	public void agregarPaquete(int codigoPaquete, int volumen, int precio, int porcentaje, int adicional) {
//		if (estaCerrado())
//			throw new RuntimeException("El pedido está finalizado.");
//
//		String dirEntrega = obtenerDirCliente();
//		PaqueteEspecial paquete = new PaqueteEspecial(codigoPaquete, volumen, precio, porcentaje, adicional,
//				dirEntrega);
//
//		carrito.put(codigoPaquete, paquete);
//
//		facturacion += paquete.devolverCostoTotal();
//	}

	public boolean eliminarPaquete(int id) {
		if (estaCerrado() || !carrito.containsKey(id))
			return false;

		facturacion -= carrito.get(id).devolverCostoTotal();
		carrito.remove(id);
		return true;

	}

//	public Map<Integer, PaqueteAEntregar> obtenerCarrito() {
//		return this.carrito;
//	}

//	public String cargarPaquetes(Transporte t) {
//		if (!estaCerrado())
//			return "";
//		StringBuilder cargados = new StringBuilder();
//
//		Iterator<Map.Entry<Integer, PaqueteAEntregar>> iterator = carrito.entrySet().iterator();
//
//		while (iterator.hasNext()) {
//			Map.Entry<Integer, PaqueteAEntregar> entry = iterator.next();
//			PaqueteAEntregar paq = entry.getValue();
//
//			if (paqueteCargado(t.cargarPaquete(paq))) {
//				cargados.append(obtenerDatos(paq));
//				iterator.remove();
//			}
//
//		}
//
//		return cargados.toString();
//	}
//	

	public void cargarPaquete(Transporte t, StringBuilder sBuilder, Predicate<PaqueteAEntregar> filtro) {
		if (!estaCerrado())
			return;
		for (PaqueteAEntregar paquete : carrito.values())
			if (filtro.test(paquete) && t.cargarPaquete(paquete) )
				obtenerDatos(paquete, sBuilder);
	}

//	public void cargarComunes(Transporte t, StringBuilder sBuilder) {
//		if (!estaCerrado())
//			return;
//		for (PaqueteAEntregar paquete : carrito.values())
//			t.cargarPaquete(devolverCodigoUnico(), paquete, sBuilder);
//
//	}
//
//	///////
//	public void cargarPrioritario(Transporte t, StringBuilder sBuilder) {
//		if (!estaCerrado())
//			return;
//		for (PaqueteAEntregar paquete : carrito.values())
//			if (paquete instanceof PaqueteEspecial)
//				t.cargarPaquete(devolverCodigoUnico(), paquete, sBuilder);
//
//	}

	public String duenio() {
		return this.datosCliente.identificacion();
	}

	public boolean faltanEntregar() {
		if (estaCerrado() && !estaVacio())
			return true;
		return false;
	}
	
	public double cerrarPedido() {
		if (estaCerrado())
			throw new RuntimeException("Pedido con código: " + this.numPedido + " no está disponible.");
		cerrado = true;
		return (double) this.facturacion;
	}

	public String devolverCodigoUnico() {
		return "" + this.numPedido;
	}


	@Override
	public String toString() {
	    StringBuilder sBuilder = new StringBuilder();
	    sBuilder.append(datosCliente).append(",\n")
	            .append("\t cantidad de paquetes:").append(carrito.size()).append(",\n")
	            .append("\t facturacion total: $").append(facturacion).append(",\n")
	            .append("\t cerrado:").append(cerrado).append("\n\t");
	    return sBuilder.toString();
	}


	// --------------PRIVATE---------------

	public boolean estaCerrado() {
		return cerrado;
	}

	public boolean estaVacio() {
		return this.carrito.size() < 1;
	}

	public String destino() {
		return datosCliente.domicilio();
	}

	private void obtenerDatos(PaqueteAEntregar paq, StringBuilder sBuilder) {
		sBuilder.append(" + [ ").append(this.numPedido)
        .append(" - ").append(paq.devolverCodigoUnico())
        .append(" ] ").append(paq.devolverDirEntrega())
        .append("\n");
	}

	// Método para validar el número de pedido
	private int validarNumPedido(int numPedido) {
		if (numPedido <= 0)
			throw new RuntimeException("El número de pedido debe ser mayor que cero.");
		return numPedido;
	}

}