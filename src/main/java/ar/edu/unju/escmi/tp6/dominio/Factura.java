package ar.edu.unju.escmi.tp6.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura {

	private LocalDate fecha;
	private long nroFactura;
	private Cliente cliente;
	private List<Detalle> detalles = new ArrayList<Detalle>();

	public Factura() {
	}

	public Factura(LocalDate fecha, long nroFactura,
			Cliente cliente, List<Detalle> detalles) {

		this.fecha = fecha;
		this.nroFactura = nroFactura;
		this.cliente = cliente;

		if (detalles != null) {
			this.detalles = detalles;
		}
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public long getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(long nroFactura) {
		this.nroFactura = nroFactura;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> detalles) {

		if (detalles != null) {
			this.detalles = detalles;
		} else {
			this.detalles = new ArrayList<Detalle>();
		}
	}

	public double calcularTotal() {

		double total = 0;

		for (Detalle detalle : detalles) {
			total += detalle.getImporte();
		}

		return total;
	}

	@Override
	public String toString() {

		return "\n\n******************** Factura ********************"
				+ "\nFecha: " + fecha
				+ " N° de Factura: " + nroFactura
				+ "\nCliente: "
				+ (cliente != null ? cliente.getNombre() : "Sin cliente")
				+ "\n************ Detalles de la Factura *************"
				+ "\n"
				+ detalles.toString()
						.replaceAll("\\[|\\]", "")
						.replaceAll(", ", "")
				+ "\nTotal: $" + calcularTotal()
				+ "\n";
	}
}