package Tema6Facturacion;

public class Factura {
	private int idFactura;
	private String dni;
	private String estadoPago;
	private float Descuento = (float) 0.10;
	private float Subtotal;
	private float precio;
	private float cantidad;
	private float iva=(float) 1.21;
	
	public Factura(int idFactura, String dni, String estadoPago,float subtotal, float precio,
			float cantidad) {
		super();
		this.idFactura = idFactura;
		this.dni = dni;
		this.estadoPago = estadoPago;
		Subtotal = ((precio*cantidad)*Descuento-(precio*cantidad))*iva;
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
		return Descuento;
	}

	public float getSubtotal() {
		return Subtotal;
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

	public void setDescuento(float descuento) {
		Descuento = descuento;
	}

	public void setSubtotal(float subtotal) {
		Subtotal = subtotal;
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
	
}