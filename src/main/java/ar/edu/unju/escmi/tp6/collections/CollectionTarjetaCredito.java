package ar.edu.unju.escmi.tp6.collections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp6.dominio.TarjetaCredito;

public class CollectionTarjetaCredito {

	public static List<TarjetaCredito> tarjetas =
			new ArrayList<TarjetaCredito>();

	public static void precargarTarjetas() {

		if (tarjetas.isEmpty()) {

			tarjetas.add(new TarjetaCredito(
					232323,
					LocalDate.of(2026, 10, 10),
					CollectionCliente.buscarCliente(45111222),
					800000));

			tarjetas.add(new TarjetaCredito(
					4458444,
					LocalDate.of(2030, 3, 15),
					CollectionCliente.buscarCliente(36888666),
					900000));

			tarjetas.add(new TarjetaCredito(
					8754566,
					LocalDate.of(2030, 4, 21),
					CollectionCliente.buscarCliente(25777555),
					1000000));
		}
	}

	public static void agregarTarjetaCredito(
			TarjetaCredito tarjeta) {

		if (tarjeta == null) {
			return;
		}

		if (buscarTarjetaCredito(tarjeta.getNumero()) != null) {

			System.out.println(
					"\nYa existe una tarjeta con ese numero.");

			return;
		}

		tarjetas.add(tarjeta);
	}

	public static TarjetaCredito buscarTarjetaCredito(
			long numero) {

		for (TarjetaCredito tarjeta : tarjetas) {

			if (tarjeta.getNumero() == numero) {
				return tarjeta;
			}
		}

		return null;
	}

	public static TarjetaCredito buscarTarjetaPorDni(long dni) {

		for (TarjetaCredito tarjeta : tarjetas) {

			if (tarjeta.getCliente() != null
					&& tarjeta.getCliente().getDni() == dni) {

				return tarjeta;
			}
		}

		return null;
	}
}