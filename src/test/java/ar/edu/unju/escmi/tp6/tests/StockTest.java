package ar.edu.unju.escmi.tp6.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.tp6.collections.CollectionStock;
import ar.edu.unju.escmi.tp6.dominio.Producto;
import ar.edu.unju.escmi.tp6.dominio.Stock;

class StockTest {

	@Test
	void testReducirStock() {

		Producto producto = new Producto(
				1111,
				"Televisor",
				200000,
				"Argentina");

		Stock stock = new Stock(
				10,
				producto);

		CollectionStock.reducirStock(
				stock,
				3);

		assertEquals(
				7,
				stock.getCantidad(),
				"El stock debe reducirse exactamente en la cantidad indicada");
	}
}