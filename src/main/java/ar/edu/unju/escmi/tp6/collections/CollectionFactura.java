package ar.edu.unju.escmi.tp6.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp6.dominio.Factura;

public class CollectionFactura {

	public static List<Factura> facturas =
			new ArrayList<Factura>();

	public static void agregarFactura(Factura factura) {

		if (factura != null) {
			facturas.add(factura);
		}
	}

	public static Factura buscarFactura(long nroFactura) {

		for (Factura factura : facturas) {

			if (factura.getNroFactura() == nroFactura) {
				return factura;
			}
		}

		return null;
	}
}