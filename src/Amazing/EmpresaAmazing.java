package Amazing;

import java.util.HashMap;
import java.util.Map;

public class EmpresaAmazing implements IEmpresa {
	private String cuit;
	private Map<Integer, Pedido> pedidos;
	private Map<String, Transporte> transportes;
	private int sigCodPaquete;
	
	EmpresaAmazing(String cuit){
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double costoEntrega(String patente) {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return false;
	}
	
	//--------------- PRIVATE
	private boolean existeTransporte(String patente) {
		if(transportes.containsKey(patente))
			throw new Error("Transporte con patente: " + patente + " ya está registrado.");
		return true;
	}
	
	private void agregarAutomovil(String patente, int volMax, int valorViaje, int maxPaq) {
		if(existeTransporte(patente))
			this.transportes.put(patente, new Auto)
	}
}
