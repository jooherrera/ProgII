package Amazing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PedidoTest {
	private Pedido pedido;
	PaqueteOrdinario paqueteOrd;
	PaqueteEspecial paqueteEsp;

	@Before
	public void startUp() throws Exception {
		pedido = new Pedido(1, "Homero", "Av Siempreviva 742", 22222222);
		paqueteOrd = new PaqueteOrdinario(1, 1000, 100, 100, "Av Siempreviva 742");
		paqueteEsp = new PaqueteEspecial(1, 1000, 100, 10, 100, "Av Siempreviva 742");
	}

	@Test
	public void agregarPaqueteComun_ok() {
		pedido.agregarPaquete(paqueteOrd);
	}

	@Test
	public void agregarPaqueteEspecial_ok() {
		pedido.agregarPaquete(paqueteEsp);
	}

	@Test
	public void eliminarPaquete_ok() {
		pedido.agregarPaquete(paqueteOrd);
		assertTrue(pedido.eliminarPaquete(1));
	}

	@Test
	public void noEliminaPaqueteDePedidoCerrado() {
		pedido.agregarPaquete(paqueteOrd);
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
		pedido.agregarPaquete(paqueteEsp);
		pedido.cerrarPedido();
		assertTrue(pedido.faltanEntregar());
	}

	@Test
	public void pedidoAbiertoNoEntregaPaquete() {
		pedido.agregarPaquete(paqueteEsp);
		assertFalse(pedido.faltanEntregar());
	}

	@Test
	public void cerrarPedido_devuelveFacturacion() {
		pedido.agregarPaquete(paqueteOrd);
		assertEquals(200, pedido.cerrarPedido(), 0.1);
	}

	@Test(expected = RuntimeException.class)
	public void cerrarPedido_falla() {
		// Falla porque el pedido ya está cerrado
		pedido.agregarPaquete(paqueteOrd);
		pedido.cerrarPedido();
		assertEquals(200, pedido.cerrarPedido(), 0.1);
	}

	@Test
	public void pedidoDevuelveCodigoUnico() {
		pedido.agregarPaquete(paqueteOrd);
		assertEquals("1", pedido.devolverCodigoUnico());
	}

}
