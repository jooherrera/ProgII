package Amazing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PaqueteTest {
	private int id, volumen, precio, costoEnvio, porcentaje, adicional;
	private String direccionEntrega;

	private PaqueteOrdinario paqOrdinario, paqOrdinarioIgual, paqOrdinarioNoIgual;
	private PaqueteEspecial paqEspecialBase; // Vol menor a 3000
	private PaqueteEspecial paqEspecial1; // Vol >= 3000 && < 5000
	private PaqueteEspecial paqEspecial2; // Vol >= 5000

	@Before
	public void startUp() throws Exception {
		id = 1;
		volumen = 1000;
		precio = 100;
		costoEnvio = 100;
		porcentaje = 20;
		adicional = 60;
		direccionEntrega = "Av Siempreviva 742";

		paqOrdinario = new PaqueteOrdinario(id, volumen, precio, costoEnvio, direccionEntrega);
		paqOrdinarioIgual = new PaqueteOrdinario(id + 1, volumen, precio, costoEnvio, direccionEntrega);
		paqOrdinarioNoIgual = new PaqueteOrdinario(id + 2, volumen + 1, precio, costoEnvio, direccionEntrega);
		paqEspecialBase = new PaqueteEspecial(id, 1000, precio, porcentaje, adicional, direccionEntrega);
		paqEspecial1 = new PaqueteEspecial(id, 3500, precio, porcentaje, adicional, direccionEntrega);
		paqEspecial2 = new PaqueteEspecial(id, 6000, precio, porcentaje, adicional, direccionEntrega);

	}

	@Test
	public void paqueteOrdinario_costo_ok() {
		int total = precio + costoEnvio;
		assertEquals(total, paqOrdinario.devolverCostoTotal(), 0.1);
	}

	@Test
	public void paqueteEspecial_costoBase() {
		double total = precio * ((double) porcentaje / 100 + 1);
		assertEquals(total, paqEspecialBase.devolverCostoTotal(), 0.1);
	}

	@Test
	public void paqueteEspecial_adicionalSuma() {
		double total = precio * ((double) porcentaje / 100 + 1) + adicional;
		assertEquals(total, paqEspecial1.devolverCostoTotal(), 0.1);
	}

	@Test
	public void paqueteEspecial_adicionalDuplica() {
		double total = precio * ((double) porcentaje / 100 + 1) + 2 * adicional;
		assertEquals(total, paqEspecial2.devolverCostoTotal(), 0.1);
	}

	@Test
	public void paqueteOrdinario_equals() {
		assertTrue(paqOrdinario.equals(paqOrdinarioIgual));
	}

	@Test
	public void paqueteOrdinario_no_equals() {
		assertFalse(paqOrdinario.equals(paqOrdinarioNoIgual));
	}

	@Test
	public void paquetesDiferentesClases() {
		assertFalse(paqOrdinario.equals(paqEspecialBase));
	}

	@Test
	public void paquetesIgualClaseDiferentesAtt() {
		assertFalse(paqEspecialBase.equals(paqEspecial1));
	}

	/**
	 * Si lo carga tiene que retornar el volumen.
	 */
	@Test
	public void paqueteCargarATransporte() {
		assertEquals(volumen, paqOrdinario.cargarATransporte());
	}

	@Test(expected = RuntimeException.class)
	public void paqueteCargarATransporte_nok() {
		// Primera vez carga
		paqOrdinario.cargarATransporte();
		// Segunda vez lanza error
		paqOrdinario.cargarATransporte();
	}


}
