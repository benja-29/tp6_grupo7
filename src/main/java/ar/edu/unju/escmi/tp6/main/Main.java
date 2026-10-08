package ar.edu.unju.escmi.tp6.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.escmi.tp6.collections.CollectionCliente;
import ar.edu.unju.escmi.tp6.collections.CollectionCredito;
import ar.edu.unju.escmi.tp6.collections.CollectionFactura;
import ar.edu.unju.escmi.tp6.collections.CollectionProducto;
import ar.edu.unju.escmi.tp6.collections.CollectionStock;
import ar.edu.unju.escmi.tp6.collections.CollectionTarjetaCredito;

import ar.edu.unju.escmi.tp6.dominio.Cliente;
import ar.edu.unju.escmi.tp6.dominio.Credito;
import ar.edu.unju.escmi.tp6.dominio.Detalle;
import ar.edu.unju.escmi.tp6.dominio.Factura;
import ar.edu.unju.escmi.tp6.dominio.Producto;
import ar.edu.unju.escmi.tp6.dominio.Stock;
import ar.edu.unju.escmi.tp6.dominio.TarjetaCredito;

public class Main {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		try {

			CollectionCliente.precargarClientes();
			CollectionTarjetaCredito.precargarTarjetas();
			CollectionProducto.precargarProductos();
			CollectionStock.precargarStocks();

			int opcion;

			do {

				System.out.println("\n====== MENU PRINCIPAL =====");
				System.out.println("1- Realizar una venta con Ahora 20");
				System.out.println("2- Revisar compras realizadas por un cliente");
				System.out.println("3- Mostrar electrodomesticos disponibles en Ahora 20");
				System.out.println("4- Consultar stock");
				System.out.println("5- Revisar creditos de un cliente");
				System.out.println("6- Salir");

				opcion = leerEntero("Ingrese una opcion: ");

				switch (opcion) {

				case 1:
					realizarVenta();
					break;

				case 2:
					revisarComprasCliente();
					break;

				case 3:
					mostrarProductosAhora20();
					break;

				case 4:
					consultarStock();
					break;

				case 5:
					revisarCreditosCliente();
					break;

				case 6:
					System.out.println("\nPrograma finalizado.");
					break;

				default:
					System.out.println("\nOpcion incorrecta.");
				}

			} while (opcion != 6);

		} catch (Exception e) {

			System.out.println(
					"\nSe produjo un error: "
					+ e.getMessage());

		} finally {

			scanner.close();
			System.out.println(
					"\nScanner cerrado correctamente.");
		}
	}


	public static void realizarVenta() {

		System.out.println(
				"\n===== VENTA PROGRAMA AHORA 20 =====");

		long dni = leerLong(
				"Ingrese DNI del cliente: ");

		Cliente cliente =
				CollectionCliente.buscarCliente(dni);

		if (cliente == null) {

			System.out.println(
					"\nCliente no encontrado.");

			return;
		}

		TarjetaCredito tarjeta =
				CollectionTarjetaCredito
						.buscarTarjetaPorDni(dni);

		if (tarjeta == null) {

			System.out.println(
					"\nEl cliente no posee tarjeta.");

			return;
		}

		if (tarjeta.getFechaCaducacion()
				.isBefore(LocalDate.now())) {

			System.out.println(
					"\nLa tarjeta esta vencida.");

			return;
		}

		mostrarProductosAhora20();

		long codigo = leerLong(
				"\nIngrese codigo del producto: ");

		Producto producto =
				CollectionProducto
						.buscarProducto(codigo);

		if (producto == null) {

			System.out.println(
					"\nProducto no encontrado.");

			return;
		}

		if (!producto.getOrigenFabricacion()
				.equalsIgnoreCase("Argentina")) {

			System.out.println(
					"\nEl producto no esta incluido "
					+ "en el programa Ahora 20.");

			return;
		}

		Stock stock =
				CollectionStock.buscarStock(producto);

		if (stock == null) {

			System.out.println(
					"\nNo existe stock del producto.");

			return;
		}

		int cantidad = leerEntero(
				"Ingrese cantidad: ");

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

		Detalle detalle =
				new Detalle(
						cantidad,
						0,
						producto);

		double totalCompra =
				detalle.getImporte();

		boolean esCelular =
				producto.getDescripcion()
						.toLowerCase()
						.contains("celular");

		if (esCelular
				&& totalCompra > 1000000) {

			System.out.println(
					"\nLa compra de celulares "
					+ "no puede superar $1.000.000.");

			return;
		}

		if (totalCompra > 2500000) {

			System.out.println(
					"\nLa compra no puede superar "
					+ "$2.500.000.");

			return;
		}

		if (totalCompra
				> tarjeta.getLimiteCompra()) {

			System.out.println(
					"\nLimite de tarjeta insuficiente.");

			System.out.println(
					"Limite disponible: $"
					+ tarjeta.getLimiteCompra());

			return;
		}

		List<Detalle> detalles =
				new ArrayList<Detalle>();

		detalles.add(detalle);

		long numeroFactura =
				CollectionFactura.facturas.size() + 1;

		Factura factura =
				new Factura(
						LocalDate.now(),
						numeroFactura,
						cliente,
						detalles);

		Credito credito =
				new Credito(
						tarjeta,
						factura,
						new ArrayList<>());

		CollectionFactura.agregarFactura(
				factura);

		CollectionCredito.agregarCredito(
				credito);

		CollectionStock.reducirStock(
				stock,
				cantidad);

		tarjeta.setLimiteCompra(
				tarjeta.getLimiteCompra()
				- totalCompra);

		System.out.println(
				"\nVENTA REALIZADA CORRECTAMENTE");

		System.out.println(factura);

		System.out.println(
				"Total compra: $"
				+ factura.calcularTotal());

		System.out.println(
				"Cantidad de cuotas: "
				+ credito.getCuotas().size());

		System.out.println(
				"Monto de cada cuota: $"
				+ credito.getCuotas()
						.get(0)
						.getMonto());

		System.out.println(
				"Nuevo limite disponible: $"
				+ tarjeta.getLimiteCompra());
	}


	public static void revisarComprasCliente() {

		long dni = leerLong(
				"\nIngrese DNI del cliente: ");

		Cliente cliente =
				CollectionCliente.buscarCliente(dni);

		if (cliente == null) {

			System.out.println(
					"\nCliente no encontrado.");

			return;
		}

		List<Factura> compras =
				cliente.consultarCompras();

		if (compras.isEmpty()) {

			System.out.println(
					"\nEl cliente no tiene compras.");

			return;
		}

		System.out.println(
				"\n===== COMPRAS DEL CLIENTE =====");

		for (Factura factura : compras) {

			System.out.println(factura);
		}
	}


	public static void mostrarProductosAhora20() {

		System.out.println(
				"\n===== PRODUCTOS AHORA 20 =====");

		List<Producto> productos =
				CollectionProducto
						.obtenerProductosAhora20();

		for (Producto producto : productos) {

			System.out.println(producto);
		}
	}


	public static void consultarStock() {

		long codigo = leerLong(
				"\nIngrese codigo del producto: ");

		Producto producto =
				CollectionProducto
						.buscarProducto(codigo);

		if (producto == null) {

			System.out.println(
					"\nProducto no encontrado.");

			return;
		}

		Stock stock =
				CollectionStock.buscarStock(producto);

		if (stock == null) {

			System.out.println(
					"\nNo existe stock para ese producto.");

			return;
		}

		System.out.println(
				"\nProducto: "
				+ producto.getDescripcion());

		System.out.println(
				"Stock disponible: "
				+ stock.getCantidad());
	}


	public static void revisarCreditosCliente() {

		long dni = leerLong(
				"\nIngrese DNI del cliente: ");

		Cliente cliente =
				CollectionCliente.buscarCliente(dni);

		if (cliente == null) {

			System.out.println(
					"\nCliente no encontrado.");

			return;
		}

		List<Credito> creditos =
				CollectionCredito
						.buscarCreditosPorDni(dni);

		if (creditos.isEmpty()) {

			System.out.println(
					"\nEl cliente no tiene creditos.");

			return;
		}

		System.out.println(
				"\n===== CREDITOS DEL CLIENTE =====");

		for (Credito credito : creditos) {

			credito.mostarCredito();

			System.out.println(
					"--------------------------------");
		}
	}


	public static int leerEntero(String mensaje) {

		while (true) {

			try {

				System.out.print(mensaje);

				return Integer.parseInt(
						scanner.nextLine());

			} catch (NumberFormatException e) {

				System.out.println(
						"Debe ingresar un numero entero.");
			}
		}
	}

	public static long leerLong(String mensaje) {

		while (true) {

			try {

				System.out.print(mensaje);

				return Long.parseLong(
						scanner.nextLine());

			} catch (NumberFormatException e) {

				System.out.println(
						"Debe ingresar un numero valido.");
			}
		}
	}
}