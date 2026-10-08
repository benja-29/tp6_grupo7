package ar.edu.unju.escmi.tp6.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp6.dominio.Credito;

public class CollectionCredito {

	public static List<Credito> creditos =
			new ArrayList<Credito>();

	public static void agregarCredito(Credito credito) {

		if (credito != null) {
			creditos.add(credito);
		}
	}

	public static List<Credito> buscarCreditosPorDni(long dni) {

		List<Credito> creditosEncontrados =
				new ArrayList<Credito>();

		for (Credito credito : creditos) {

			if (credito.getFactura() != null
					&& credito.getFactura().getCliente() != null
					&& credito.getFactura()
							.getCliente()
							.getDni() == dni) {

				creditosEncontrados.add(credito);
			}
		}

		return creditosEncontrados;
	}
}