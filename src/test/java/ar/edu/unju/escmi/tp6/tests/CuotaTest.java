package ar.edu.unju.escmi.tp6.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.tp6.dominio.Credito;
import ar.edu.unju.escmi.tp6.dominio.Cuota;
import ar.edu.unju.escmi.tp6.dominio.Detalle;
import ar.edu.unju.escmi.tp6.dominio.Factura;
import ar.edu.unju.escmi.tp6.dominio.Producto;

class CuotaTest {

	private Credito crearCredito() {

		Producto producto = new Producto(
				1111,
				"Televisor",
				200000,
				"Argentina");

		Detalle detalle = new Detalle(
				2,
				0,
				producto);

		List<Detalle> detalles =
				new ArrayList<Detalle>();

		detalles.add(detalle);

		Factura factura = new Factura(
				LocalDate.now(),
				1,
				null,
				detalles);

		return new Credito(
				null,
				factura,
				new ArrayList<Cuota>());
	}

	@Test
	void testListaCuotasNoEsNull() {

		Credito credito = crearCredito();

		assertNotNull(
				credito.getCuotas(),
				"La lista de cuotas no debe ser null");
	}

	@Test
	void testCreditoTiene20Cuotas() {

		Credito credito = crearCredito();

		assertEquals(
				20,
				credito.getCuotas().size(),
				"El credito debe generar exactamente 20 cuotas");
	}

	@Test
	void testCantidadCuotasNoSuperaLimite() {

		Credito credito = crearCredito();

		assertTrue(
				credito.getCuotas().size() <= 20,
				"La cantidad de cuotas no debe superar las 20");
	}
}