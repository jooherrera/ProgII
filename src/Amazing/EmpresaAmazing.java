package Amazing;

import java.util.HashMap;
import java.util.Map;

public class EmpresaAmazing implements IEmpresa {
	private String cuit;
	private HashMap<Integer, Pedido> pedidos;
	private Map<String, Transporte> transportes;
	private int sigCodPaquete;
	private double totalFacturado;

	EmpresaAmazing(String cuit) {
		this.cuit = cuit;
		this.pedidos = new HashMap<Integer, Pedido>();
		this.transportes = new HashMap<String, Transporte>();
		this.sigCodPaquete = 1;
		this.totalFacturado = 0;
	}

	@Override
	public void registrarAutomovil(String patente, int volMax, int valorViaje, int maxPaq) {
		agregarTransporte(patente, new Automovil(patente, volMax, valorViaje, maxPaq));
	}

	@Override
	public void registrarUtilitario(String patente, int volMax, int valorViaje, int valorExtra) {
		agregarTransporte(patente, new Utilitario(patente, volMax, valorViaje, valorExtra));
	}

	@Override
	public void registrarCamion(String patente, int volMax, int valorViaje, int adicXPaq) {
		agregarTransporte(patente, new Camion(patente, volMax, valorViaje, adicXPaq));
	}

	@Override
	public int registrarPedido(String cliente, String direccion, int dni) {
		int id = siguienteIDPedido();
		this.pedidos.put(id, new Pedido(id, cliente, direccion, dni));
		return id;
	}

	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int costoEnvio) {
		int codigoUnico = sigCodPaquete;
		agregarPaqueteAPedido(codPedido, codigoUnico,new PaqueteOrdinario(codigoUnico, volumen, precio, costoEnvio, cuit));
		return codigoUnico;
	}

	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int porcentaje, int adicional) {		
		int codigoUnico = sigCodPaquete;
		agregarPaqueteAPedido(codPedido, codigoUnico,new PaqueteEspecial(codPedido, volumen, precio, porcentaje, adicional, cuit));
		return codigoUnico;
	}

	@Override
	public boolean quitarPaquete(int codPaquete) {
		boolean eliminado = false;
		for(Pedido pedido : pedidos.values())
				eliminado |= pedido.eliminarPaquete(codPaquete);
		return eliminado;	
	}

	@Override
	public double cerrarPedido(int codPedido) {
		
		return obtenerPedido(codPedido).cerrarPedido();
	}

	@Override
	public String cargarTransporte(String patente) {

		Transporte transporte = obtenerTransporte(patente);

		StringBuilder cargamento = new StringBuilder();

		for (Pedido pedido : pedidos.values() ) 
//			cargamento.append(transporte.cargarTransporte(pedido));
			cargamento.append(pedido.cargarPaquetes(transporte));


		return cargamento.toString();
	}

	
	@Override
	public double costoEntrega(String patente) {
		if (!existeTransporte(patente)) 
			throw new RuntimeException("Patente no encontrada.");
		
		Transporte transporte = transportes.get(patente);
		
		return transporte.consultarCostoEntrega();	
	}

	@Override
	public Map<Integer, String> pedidosNoEntregados() {
		
		Map<Integer,String> listaNoEntregados = new HashMap<Integer, String>();
		for (Map.Entry<Integer, Pedido> entrada : pedidos.entrySet()) {
			
			Integer key = entrada.getKey();
			Pedido pedido = entrada.getValue();
			
			if(pedido.faltanEntregar())
				listaNoEntregados.put(key,  pedido.duenio());
				
		}
		
		return listaNoEntregados;
	}

	@Override
	public double facturacionTotalPedidosCerrados() {
		return this.totalFacturado;
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
		return transportes.containsKey(patente);
	}

	private boolean existePedido(int codPedido) {
		return this.pedidos.containsKey(codPedido);
	}
	
	private Transporte obtenerTransporte(String patente) {
		if(!existeTransporte(patente))
			throw new RuntimeException("El transporte con patente: " + patente + " no existe.");
		return transportes.get(patente);
	}
	
	private Pedido obtenerPedido(int codPedido) {
		if(!existePedido(codPedido))
			throw new RuntimeException("El pedido con código: " + codPedido + " no existe.");
		return pedidos.get(codPedido);
	}

	private void agregarTransporte(String patente, Transporte transporte) {
		if (existeTransporte(patente))
			throw new RuntimeException("El transporte con patente: '" + patente + "' ya existe");
		this.transportes.put(patente, transporte);
	}
	
	

	
	private void agregarPaqueteAPedido(int codPedido, int codUnico, PaqueteAEntregar paquete) {
		obtenerPedido(codPedido).agregarPaquete(codUnico, paquete);
		this.aumentarCodPaquete();
	}
	
	
	private void aumentarCodPaquete() {
		this.sigCodPaquete++;
	}

	private int siguienteIDPedido() {
		return this.pedidos.size() + 1;
	}

}
