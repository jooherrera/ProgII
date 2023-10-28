package Amazing;

import java.util.HashMap;
import java.util.Map;

public class EmpresaAmazing implements IEmpresa {
	private String cuit;
	private Map<Integer, Pedido> pedidos;
	private Map<String, Transporte> transportes;
	private int sigCodPaquete;

	EmpresaAmazing(String cuit) {
		this.cuit = cuit;
		this.pedidos = new HashMap<Integer, Pedido>();
		this.transportes = new HashMap<String, Transporte>();
		this.sigCodPaquete = 1;
	}

	@Override
	public void registrarAutomovil(String patente, int volMax, int valorViaje, int maxPaq) {
		registrar();
		return;

	}

	@Override
	public void registrarUtilitario(String patente, int volMax, int valorViaje, int valorExtra) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registrarCamion(String patente, int volMax, int valorViaje, int adicXPaq) {
		// TODO Auto-generated method stub

	}

	@Override
	public int registrarPedido(String cliente, String direccion, int dni) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int costoEnvio) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int porcentaje, int adicional) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean quitarPaquete(int codPaquete) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double cerrarPedido(int codPedido) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String cargarTransporte(String patente) {
		if (!transportes.containsKey(patente)) {
			throw new IllegalArgumentException("Patente no encontrada.");
		}
		Transporte transporte = transportes.get(patente);

		StringBuilder cargaTransporte = new StringBuilder();

		for (Map.Entry<Integer, PaqueteAEntregar> entry : transporte.cargamento.entrySet()) {
			int numPedido = entry.getKey();
			int codPaquete = entry.getValue().getId();
			String direccion = entry.getValue().getDireccionEntrega();

			cargaTransporte.append(" + [").append(numPedido).append(" - ").append(codPaquete).append("] ")
					.append(direccion).append("\n");
		}

		return cargaTransporte.toString();
	}

	@Override
	public double costoEntrega(String patente) {
		if (!transportes.containsKey(patente)) {
			throw new IllegalArgumentException("Patente no encontrada.");
		}
		Transporte transporte = transportes.get(patente);
		if (transporte.cargamento.isEmpty()) {
			throw new IllegalArgumentException("El transporte no está cargado.");
		}
		return transporte.consultarCostoEntrega();
	}

	@Override
	public Map<Integer, String> pedidosNoEntregados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double facturacionTotalPedidosCerrados() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hayTransportesIdenticos() {
		for (Transporte transporte1 : transportes.values()) {
			for (Transporte transporte2 : transportes.values()) {
				if (transporte1 != transporte2 && transporte1.esIgual(transporte2)) {
					return true;
				}
			}
		}
		return false;
	}

	// --------------- PRIVATE
	private boolean existeTransporte(String patente) {
		if (transportes.containsKey(patente))
			throw new Error("Transporte con patente: " + patente + " ya está registrado.");
		return true;
	}

	private void agregarAutomovil(String patente, int volMax, int valorViaje, int maxPaq) {
		if(existeTransporte(patente))
			this.transportes.put(patente, new Auto)
	}
}
