package Amazing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PedidoTest {
	private Pedido pedido;
	@Before
	public void startUp() throws Exception {
		pedido = new Pedido(1, "Homero", "Av Siempreviva 742", 22222222);
	}

	@Test
	public void agregarPaqueteComun_ok() {
		pedido.agregarPaquete(1, 1000, 100, 10);
	}
	
	@Test
	public void agregarPaqueteEspecial_ok() {
		pedido.agregarPaquete(1, 1000, 100, 10,100);
	}
	
	@Test
	public void eliminarPaquete_ok() {
		pedido.agregarPaquete(1, 1000, 100, 10,100);
		assertTrue(pedido.eliminarPaquete(1));
	}
	
	@Test
	public void noEliminaPaqueteDePedidoCerrado() {
		pedido.agregarPaquete(1, 1000, 100, 10,100);
		pedido.cerrarPedido();
		assertFalse(pedido.eliminarPaquete(1));
	}
	
	@Test
	public void noEliminaPaqueteNoExisteID() {
		pedido.cerrarPedido();
		assertFalse(pedido.eliminarPaquete(2));
	}
	
	@Test
	public void faltaEntregar() {
		pedido.agregarPaquete(1, 1000, 100, 10,100);
		pedido.cerrarPedido();
		assertTrue(pedido.faltanEntregar());
	}
	
	@Test
	public void pedidoAbiertoNoEntregaPaquete() {
		pedido.agregarPaquete(1, 1000, 100, 10,100);
		assertFalse(pedido.faltanEntregar());
	}
	
	@Test
	public void cerrarPedido_devuelveFacturacion() {
		pedido.agregarPaquete(1, 1000, 100, 100);
		assertEquals(200,pedido.cerrarPedido(),0.1);
	}
	
	@Test(expected = RuntimeException.class)
	public void cerrarPedido_falla() {
		//Falla porque el pedido ya está cerrado
		pedido.agregarPaquete(1, 1000, 100, 100);
		pedido.cerrarPedido();
		assertEquals(200,pedido.cerrarPedido(),0.1);
	}
	
	@Test
	public void pedidoDevuelveCodigoUnico() {
		pedido.agregarPaquete(1, 1000, 100, 100);
		assertEquals("1",pedido.devolverCodigoUnico());
	}
	
}
