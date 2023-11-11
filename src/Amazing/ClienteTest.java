package Amazing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ClienteTest {
	private int dni, dniNegativo, dniCero;
	private String nombre, direccion, valorVacio, valorNull;

	@Before
	public void startUp() throws Exception {
		dni = 22222;
		dniNegativo = -22;
		dniCero = 0;
		nombre = "Homero";
		direccion = "Av Siempreviva 742";
		valorVacio = "";
		valorNull = null;
	}

	@Test
	public void cliente_ok() {
		Cliente cliente = new Cliente(dni, nombre, direccion);
		assertEquals(nombre, cliente.identificacion());
		assertEquals(direccion, cliente.domicilio());
	}

	@Test(expected = RuntimeException.class)
	public void clienteDniCero() {
		new Cliente(dniCero, nombre, direccion);
	}

	@Test(expected = RuntimeException.class)
	public void clienteDniNegativo() {
		new Cliente(dniNegativo, nombre, direccion);
	}

	@Test(expected = RuntimeException.class)
	public void clienteSinNombre_vacio() {
		new Cliente(dni, valorVacio, direccion);
	}

	@Test(expected = RuntimeException.class)
	public void clienteSinNombre_null() {
		new Cliente(dni, valorNull, direccion);
	}

	@Test(expected = RuntimeException.class)
	public void clienteSinDireccion_vacio() {
		new Cliente(dni, nombre, valorVacio);
	}

	@Test(expected = RuntimeException.class)
	public void clienteSinDireccion_null() {
		new Cliente(dni, nombre, valorNull);
	}

}
