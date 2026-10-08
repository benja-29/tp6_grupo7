package ar.edu.unju.escmi.tp6.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp6.dominio.Cliente;

public class CollectionCliente {

	public static List<Cliente> clientes =
			new ArrayList<Cliente>();

	public static void precargarClientes() {

		if (clientes.isEmpty()) {

			clientes.add(new Cliente(
					45111222,
					"Mario Barca",
					"Alvear 120",
					"65454686"));

			clientes.add(new Cliente(
					36888666,
					"Juan Perez",
					"Av. Belgrano 300",
					"35185695"));

			clientes.add(new Cliente(
					25777555,
					"Ana Juarez",
					"Islas Malvinas 731",
					"38845224"));
		}
	}

	public static void agregarCliente(Cliente cliente) {

		if (cliente != null) {
			clientes.add(cliente);
		}
	}

	public static Cliente buscarCliente(long dni) {

		for (Cliente cliente : clientes) {

			if (cliente.getDni() == dni) {
				return cliente;
			}
		}

		return null;
	}
}