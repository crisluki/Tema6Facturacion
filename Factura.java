package Tema6Facturacion;

public class Factura {
	private int idFactura;
	private String dni;
	private String estadoPago;
	private float descuento = (float) 0.10;
	private float subtotal;
	private float precio;
	private float cantidad;
	private float iva=(float) 1.21;
	
	public Factura(int idFactura, String dni, String estadoPago,float subtotal, float precio,
			float cantidad) {
		super();
		this.idFactura = idFactura;
		this.dni = dni;
		this.estadoPago = estadoPago;
		subtotal = (precio*cantidad)+((precio*cantidad)*descuento);
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public String getDni() {
		return dni;
	}

	public String getEstadoPago() {
		return estadoPago;
	}

	public float getDescuento() {
		return descuento;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public float getPrecio() {
		return precio;
	}

	public float getCantidad() {
		return cantidad;
	}

	public float getIva() {
		return iva;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}

	public void setDescuento(float des) {
		descuento = des;
	}

	public void setSubtotal(float subtot) {
		subtotal = subtot;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", dni=" + dni + ", estadoPago=" + estadoPago + ", Descuento="
				+ descuento + ", Subtotal=" + subtotal + ", precio=" + precio + ", cantidad=" + cantidad + ", iva="
				+ iva + "]";
	}
}