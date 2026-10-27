package ar.edu.unju.escmi.tp6.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.tp6.dominio.Cliente;
import ar.edu.unju.escmi.tp6.dominio.Credito;
import ar.edu.unju.escmi.tp6.dominio.Detalle;
import ar.edu.unju.escmi.tp6.dominio.Factura;
import ar.edu.unju.escmi.tp6.dominio.Producto;
import ar.edu.unju.escmi.tp6.dominio.TarjetaCredito;

class CreditoTest {

	@Test
	void testMontoCreditoNoSupera2500000() {

		Producto producto = new Producto(
				1111,
				"Televisor",
				600000,
				"Argentina");

		Detalle detalle = new Detalle(
				4,
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

		Credito credito = new Credito(
				null,
				factura,
				new ArrayList<>());

		assertTrue(
				credito.getFactura().calcularTotal() <= 2500000,
				"El monto total del credito no debe superar $2.500.000");
	}

	@Test
	void testSumaDetallesIgualTotalFactura() {

		Producto producto1 = new Producto(
				1111,
				"Televisor",
				300000,
				"Argentina");

		Producto producto2 = new Producto(
				3111,
				"Heladera",
				200000,
				"Argentina");

		Detalle detalle1 = new Detalle(
				2,
				0,
				producto1);

		Detalle detalle2 = new Detalle(
				1,
				0,
				producto2);

		List<Detalle> detalles =
				new ArrayList<Detalle>();

		detalles.add(detalle1);
		detalles.add(detalle2);

		Factura factura = new Factura(
				LocalDate.now(),
				2,
				null,
				detalles);

		double sumaDetalles =
				detalle1.getImporte()
				+ detalle2.getImporte();

		assertEquals(
				sumaDetalles,
				factura.calcularTotal(),
				0.01,
				"La suma de los detalles debe coincidir con el total de la factura");
	}

	@Test
	void testCompraNoSuperaLimiteGeneralNiTarjeta() {

		Cliente cliente = new Cliente(
				36888666,
				"Juan Perez",
				"Av. Belgrano 300",
				"35185695");

		TarjetaCredito tarjeta =
				new TarjetaCredito(
						4458444,
						LocalDate.of(2030, 3, 15),
						cliente,
						2500000);

		Producto producto = new Producto(
				5113,
				"Televisor",
				500000,
				"Argentina");

		Detalle detalle = new Detalle(
				4,
				0,
				producto);

		List<Detalle> detalles =
				new ArrayList<Detalle>();

		detalles.add(detalle);

		Factura factura = new Factura(
				LocalDate.now(),
				3,
				cliente,
				detalles);

		double totalCompra =
				factura.calcularTotal();

		assertTrue(
				totalCompra <= 2500000
				&& totalCompra <= tarjeta.getLimiteCompra(),
				"La compra no debe superar ni $2.500.000 ni el limite disponible de la tarjeta");
	}
}