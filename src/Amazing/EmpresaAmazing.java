package Amazing;

import java.util.HashMap;
import java.util.Map;

public class EmpresaAmazing implements IEmpresa {
	private String cuit;
	private HashMap<Integer, Pedido> pedidos;
	private Map<String, Transporte> transportes;
	private int sigCodPaquete;
	private double totalFacturado;

	public EmpresaAmazing(String cuit) {
		this.cuit = validarCuit(cuit);
		this.pedidos = new HashMap<Integer, Pedido>();
		this.transportes = new HashMap<String, Transporte>();
		this.sigCodPaquete = 1;
		this.totalFacturado = 0;
	}

	@Override
	public void registrarAutomovil(String patente, int volMax, int valorViaje, int maxPaq) {
		this.agregarTransporte(patente, new Automovil(patente, volMax, valorViaje, maxPaq));
	}

	@Override
	public void registrarUtilitario(String patente, int volMax, int valorViaje, int valorExtra) {
		this.agregarTransporte(patente, new Utilitario(patente, volMax, valorViaje, valorExtra));
	}

	@Override
	public void registrarCamion(String patente, int volMax, int valorViaje, int adicXPaq) {
		this.agregarTransporte(patente, new Camion(patente, volMax, valorViaje, adicXPaq));
	}

	@Override
	public int registrarPedido(String cliente, String direccion, int dni) {
		int id = this.siguienteIDPedido();
		this.pedidos.put(id, new Pedido(id, cliente, direccion, dni));
		return id;
	}

	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int costoEnvio) {
		int codigoUnico = sigCodPaquete;
		this.obtenerPedido(codPedido).agregarPaquete(codigoUnico, volumen, precio, costoEnvio);
		this.aumentarCodPaquete();
		return codigoUnico;
	}

	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int porcentaje, int adicional) {
		int codigoUnico = sigCodPaquete;
		this.obtenerPedido(codPedido).agregarPaquete(codigoUnico, volumen, precio, porcentaje, adicional);
		this.aumentarCodPaquete();

		return codigoUnico;
	}

	@Override
	public boolean quitarPaquete(int codPaquete) {
		if (!this.codigoPaqExiste(codPaquete))
			throw new RuntimeException("Cod paquete: " + codPaquete + " no registrado.");
		boolean eliminado = false;
		for (Pedido pedido : pedidos.values())
			eliminado |= pedido.eliminarPaquete(codPaquete);
		return eliminado;
	}

	@Override
	public double cerrarPedido(int codPedido) {
		double valor = this.obtenerPedido(codPedido).cerrarPedido();
		this.totalFacturado += valor;
		return valor;
	}

	@Override
	public String cargarTransporte(String patente) {

		Transporte transporte = this.obtenerTransporte(patente);

		StringBuilder cargamento = new StringBuilder();

		for (Pedido pedido : pedidos.values()) {
			cargamento.append(pedido.cargarEspecial(transporte));
		}

		for (Pedido pedido : pedidos.values()) {
			cargamento.append(pedido.cargarPaquetes(transporte));
		}

		return cargamento.toString();
	}

	@Override
	public double costoEntrega(String patente) {
		Transporte transporte = this.obtenerTransporte(patente);
		return transporte.consultarCostoEntrega();
	}

	@Override
	public Map<Integer, String> pedidosNoEntregados() {

		Map<Integer, String> listaNoEntregados = new HashMap<Integer, String>();
		for (Map.Entry<Integer, Pedido> entrada : pedidos.entrySet()) {

			Integer key = entrada.getKey();
			Pedido pedido = entrada.getValue();

			if (pedido.faltanEntregar())
				listaNoEntregados.put(key, pedido.duenio());
		}
		return listaNoEntregados;
	}

	@Override
	public double facturacionTotalPedidosCerrados() {
		return this.totalFacturado;
	}

	@Override
	public boolean hayTransportesIdenticos() {
		boolean ret = false;

		for (Transporte t1 : transportes.values())
			ret |= algunParecidoA(t1);

		return ret;

	}

	private boolean algunParecidoA(Transporte t) {
		boolean ret = false;
		for (Transporte elem : this.transportes.values())
			ret |= elem.equals(t);

		return ret;
	}

	@Override
	public String toString() {
		return "EmpresaAmazing { cuit=" + cuit + ", pedidos=" + pedidos + ", transportes=" + transportes
				+ ", sigCodPaquete=" + sigCodPaquete + ", totalFacturado=" + totalFacturado + " }";
	}

	// --------------- PRIVATE
	private boolean existeTransporte(String patente) {
		return this.transportes.containsKey(patente);
	}

	private boolean existePedido(int codPedido) {
		return this.pedidos.containsKey(codPedido);
	}

	private Transporte obtenerTransporte(String patente) {
		if (!this.existeTransporte(patente))
			throw new RuntimeException("El transporte con patente: " + patente + " no existe.");
		return transportes.get(patente);
	}

	private Pedido obtenerPedido(int codPedido) {
		if (!this.existePedido(codPedido))
			throw new RuntimeException("El pedido con código: " + codPedido + " no existe.");
		return pedidos.get(codPedido);
	}

	private void agregarTransporte(String patente, Transporte transporte) {
		if (this.existeTransporte(patente))
			throw new RuntimeException("El transporte con patente: '" + patente + "' ya existe");
		this.transportes.put(patente, transporte);
	}

	private void aumentarCodPaquete() {
		this.sigCodPaquete++;
	}

	private int siguienteIDPedido() {
		return this.pedidos.size() + 1;
	}

	// Método para validar el CUIT
	private String validarCuit(String cuit) {
		if (cuit == null || cuit.isEmpty())
			throw new RuntimeException("El CUIT de la empresa no puede ser nulo ni estar vacío.");
		return cuit;
	}

	private boolean codigoPaqExiste(int codPaquete) {
		return codPaquete >= 1 && codPaquete < this.sigCodPaquete;
	}

}
