package ar.edu.unju.escmi.tp6.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp6.dominio.Producto;
import ar.edu.unju.escmi.tp6.dominio.Stock;

public class CollectionStock {

	public static List<Stock> stocks = new ArrayList<Stock>();

	public static void precargarStocks() {

		if (stocks.isEmpty()) {

			stocks.add(new Stock(12, CollectionProducto.productos.get(0)));
			stocks.add(new Stock(22, CollectionProducto.productos.get(1)));
			stocks.add(new Stock(13, CollectionProducto.productos.get(2)));
			stocks.add(new Stock(101, CollectionProducto.productos.get(3)));
			stocks.add(new Stock(87, CollectionProducto.productos.get(4)));
			stocks.add(new Stock(45, CollectionProducto.productos.get(5)));
			stocks.add(new Stock(16, CollectionProducto.productos.get(6)));
			stocks.add(new Stock(8, CollectionProducto.productos.get(7)));
			stocks.add(new Stock(5, CollectionProducto.productos.get(8)));
			stocks.add(new Stock(21, CollectionProducto.productos.get(9)));
			stocks.add(new Stock(17, CollectionProducto.productos.get(10)));
			stocks.add(new Stock(11, CollectionProducto.productos.get(11)));
			stocks.add(new Stock(8, CollectionProducto.productos.get(12)));
			stocks.add(new Stock(14, CollectionProducto.productos.get(13)));
			stocks.add(new Stock(4, CollectionProducto.productos.get(14)));
			stocks.add(new Stock(15, CollectionProducto.productos.get(15)));
			stocks.add(new Stock(28, CollectionProducto.productos.get(16)));
			stocks.add(new Stock(47, CollectionProducto.productos.get(17)));
			stocks.add(new Stock(33, CollectionProducto.productos.get(18)));
			stocks.add(new Stock(13, CollectionProducto.productos.get(19)));
		}
	}

	public static void agregarStock(Stock stock) {

		try {

			if (stock == null || stock.getProducto() == null) {
				return;
			}

			Stock stockExistente = buscarStock(stock.getProducto());

			if (stockExistente != null) {
				stockExistente.setCantidad(stock.getCantidad());
			} else {
				stocks.add(stock);
			}

		} catch (Exception e) {

			System.out.println(
					"\nNO SE PUEDE GUARDAR EL STOCK");
		}
	}

	public static void reducirStock(Stock stock, int cantidad) {

		if (stock == null) {
			System.out.println(
					"\nStock no encontrado.");
			return;
		}

		if (cantidad <= 0) {
			System.out.println(
					"\nLa cantidad debe ser mayor a cero.");
			return;
		}

		if (stock.getCantidad() < cantidad) {
			System.out.println(
					"\nStock insuficiente.");
			return;
		}

		stock.setCantidad(
				stock.getCantidad() - cantidad);
	}

	public static Stock buscarStock(Producto producto) {

		if (producto == null) {
			return null;
		}

		try {

			for (Stock stock : stocks) {

				if (stock.getProducto() != null
						&& stock.getProducto().getCodigo()
						== producto.getCodigo()) {

					return stock;
				}
			}

		} catch (Exception e) {

			return null;
		}

		return null;
	}
}