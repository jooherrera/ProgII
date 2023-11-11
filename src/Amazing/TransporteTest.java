package Amazing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TransporteTest {
	private String patente1, patente2;
	private Automovil auto, auto2;
	private Utilitario utilitario;
	private Camion camion;

	@Before
	public void startUp() throws Exception {
		patente1 = "AAA000";
		patente2 = "BBB000";
		auto = new Automovil(patente1, 3000, 100, 3);
		auto2 = new Automovil(patente2, 3000, 100, 5);
		utilitario = new Utilitario(patente1, 3000, 100, 300);
		camion = new Camion(patente1, 50000, 20000, 500);

	}

	@Test
	public void autoCargaPaqueteOrdinario() {
		PaqueteOrdinario paqOrdinario = new PaqueteOrdinario(1, 100, 1000, 100, "Av Siempreviva 742");
		assertTrue(auto.cargarPaquete(paqOrdinario));
	}

	@Test
	public void autoNoCargaPaqueteEspecial() {
		PaqueteEspecial paqEspecial = new PaqueteEspecial(1, 100, 1000, 20, 10, "Av Siempreviva 742");
		assertFalse(auto.cargarPaquete(paqEspecial));
	}

	@Test
	public void autoNoCargaPaqOrdinarioGrande() {
		PaqueteOrdinario paqOrdinario = new PaqueteOrdinario(1, 3000, 100, 100, "Av Siempreviva 742");
		assertFalse(auto.cargarPaquete(paqOrdinario));
	}

	@Test
	public void autoNoCargaMasDelLimite() {
		PaqueteOrdinario paqOrdinario1 = new PaqueteOrdinario(1, 100, 100, 100, "Av Siempreviva 742");
		PaqueteOrdinario paqOrdinario2 = new PaqueteOrdinario(2, 100, 100, 100, "Av Siempreviva 742");
		PaqueteOrdinario paqOrdinario3 = new PaqueteOrdinario(3, 100, 100, 100, "Av Siempreviva 742");
		PaqueteOrdinario paqOrdinario4 = new PaqueteOrdinario(4, 100, 100, 100, "Av Siempreviva 742");

		auto.cargarPaquete(paqOrdinario1);
		auto.cargarPaquete(paqOrdinario2);
		auto.cargarPaquete(paqOrdinario3);
		auto.cargarPaquete(paqOrdinario4);

		assertEquals(3, auto.cantPaquetesCargados());
	}

	@Test
	public void autoNoCargaMasDelVolumenDisponible() {
		// Volumen del auto 3000
		PaqueteOrdinario paqOrdinario1 = new PaqueteOrdinario(1, 1000, 100, 100, "Av Siempreviva 742");
		PaqueteOrdinario paqOrdinario2 = new PaqueteOrdinario(2, 1000, 100, 100, "Av Siempreviva 742");
		PaqueteOrdinario paqOrdinario3 = new PaqueteOrdinario(3, 1001, 100, 100, "Av Siempreviva 742");

		auto.cargarPaquete(paqOrdinario1);
		auto.cargarPaquete(paqOrdinario2);
		auto.cargarPaquete(paqOrdinario3);

		assertEquals(2, auto.cantPaquetesCargados());
	}

	@Test
	public void autoCostoEntrega() {
		// Valor viaje $100
		PaqueteOrdinario paqOrdinario1 = new PaqueteOrdinario(1, 1000, 100, 100, "Av Siempreviva 742");
		auto.cargarPaquete(paqOrdinario1);

		assertEquals(100, auto.consultarCostoEntrega(), 0.1); //
	}

	// --------- UTILITARIO-----------

	@Test
	public void utilitarioCargaOrdinario() {
		// Volumen 3000
		PaqueteOrdinario paqOrdinario1 = new PaqueteOrdinario(1, 1000, 100, 100, "Av Siempreviva 742");
		utilitario.cargarPaquete(paqOrdinario1);
		assertEquals(1, utilitario.cantPaquetesCargados());
	}
	
	@Test
	public void utilitarioCargaEspecial() {
		// Volumen 3000
		PaqueteEspecial paq = new PaqueteEspecial(1, 1000, 100,20, 100, "Av Siempreviva 742");
		utilitario.cargarPaquete(paq);
		assertEquals(1, utilitario.cantPaquetesCargados());
	}

	@Test
	public void utilitarioCostoEntregaSinValorExtra() {
		// Valor viaje $100
		PaqueteOrdinario paqOrdinario1 = new PaqueteOrdinario(1, 1000, 100, 100, "Av Siempreviva 742");
		utilitario.cargarPaquete(paqOrdinario1);

		assertEquals(100, utilitario.consultarCostoEntrega(), 0.1); //
	}

	@Test
	public void utilitarioCostoEntregaConValorExtra() {
		// Valor viaje $100
		// Valor Extra $300
		PaqueteOrdinario paqOrdinario1 = new PaqueteOrdinario(1, 1000, 100, 100, "Av Siempreviva 742");
		PaqueteOrdinario paqOrdinario2 = new PaqueteOrdinario(2, 1000, 100, 100, "Av Siempreviva 742");
		PaqueteOrdinario paqOrdinario3 = new PaqueteOrdinario(3, 1000, 100, 100, "Av Siempreviva 742");
		PaqueteOrdinario paqOrdinario4 = new PaqueteOrdinario(4, 1000, 100, 100, "Av Siempreviva 742");
		Utilitario uti = new Utilitario("CCC000", 5000, 100, 300);

		uti.cargarPaquete(paqOrdinario1);
		uti.cargarPaquete(paqOrdinario2);
		uti.cargarPaquete(paqOrdinario3);
		uti.cargarPaquete(paqOrdinario4);

//		assertEquals(4, uti.cantPaquetesCargados());

		assertEquals(400, uti.consultarCostoEntrega(), 0.1); //
	}

	@Test
	public void camionNoCargaMenoresVol2000() {
		// Volumen 50000
		// Precio 20000
		// Adicional 500
		PaqueteOrdinario paqOrdinario = new PaqueteOrdinario(1, 1000, 100, 100, "Av Siempreviva 742");
		PaqueteEspecial paqEspecial = new PaqueteEspecial(2, 1000, 100,20, 100, "Av Siempreviva 742");
		camion.cargarPaquete(paqOrdinario);
		camion.cargarPaquete(paqEspecial);
		assertEquals(0, camion.cantPaquetesCargados());
	}
	
	@Test
	public void camionCargaPaquetes() {
		// Volumen 50000
		// Precio 20000
		// Adicional 500
		PaqueteOrdinario paqOrdinario = new PaqueteOrdinario(1, 2001, 100, 100, "Av Siempreviva 742");
		PaqueteEspecial paqEspecial = new PaqueteEspecial(2, 2001, 100,20, 100, "Av Siempreviva 742");
		camion.cargarPaquete(paqOrdinario);
		camion.cargarPaquete(paqEspecial);
		assertEquals(2, camion.cantPaquetesCargados());
	}
	
	@Test
	public void camionCostoEntrega() {
		// Valor viaje $20000
		// Valor por unidad $500
		PaqueteOrdinario paqOrdinario1 = new PaqueteOrdinario(1, 2001, 100, 100, "Av Siempreviva 742");
		PaqueteOrdinario paqOrdinario2 = new PaqueteOrdinario(2, 2001, 100, 100, "Av Siempreviva 742");

		camion.cargarPaquete(paqOrdinario1);
		camion.cargarPaquete(paqOrdinario2);

		assertEquals(20000 + 1000, camion.consultarCostoEntrega(), 0.1); //
	}
	
	@Test
	public void camionNoSuperaCapacidad() {
		//Volumen 50000

		PaqueteOrdinario paqOrdinario1 = new PaqueteOrdinario(1, 50000, 100, 100, "Av Siempreviva 742");
		PaqueteOrdinario paqOrdinario2 = new PaqueteOrdinario(2, 2001, 100, 100, "Av Siempreviva 742");

		camion.cargarPaquete(paqOrdinario1);
		camion.cargarPaquete(paqOrdinario2);

		assertEquals(1, camion.cantPaquetesCargados()); //
	}
	
	@Test
	public void autoMismaCarga() {
		PaqueteOrdinario paqOrdinario1 = new PaqueteOrdinario(1, 500, 100, 100, "Av Siempreviva 742");
		PaqueteOrdinario paqOrdinario2 = new PaqueteOrdinario(2, 500, 100, 100, "Otra calle");

		auto.cargarPaquete(paqOrdinario1);		
		auto2.cargarPaquete(paqOrdinario2);

		assertTrue(auto.equals(auto2)); //
	}
	
	@Test
	public void autoMismaCarga_False() {
		PaqueteOrdinario paqOrdinario1 = new PaqueteOrdinario(1, 500, 100, 100, "Av Siempreviva 742");
		PaqueteOrdinario paqOrdinario2 = new PaqueteOrdinario(2, 300, 100, 100, "Otra calle");

		auto.cargarPaquete(paqOrdinario1);		
		auto2.cargarPaquete(paqOrdinario2);

		assertFalse(auto.equals(auto2)); //
	}
	

}
