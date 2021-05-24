package Tema6Facturacion;
public class Pedido {
	private String nombre;
	private float precio;
	private int cantidad;
	private String dni;
	private int idLista;
	private int idProducto;
	private boolean terminado=false;
	
	public Pedido(int idLista, String dni, int idProducto, int cantidad,float precio,boolean terminado) {//idLista,dni,idProducto,cantidad,precio
		super();
		this.terminado = terminado;
		this.idLista = idLista;
		this.dni = dni;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public float getPrecio() {
		return precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public String getDni() {
		return dni;
	}
	public int getIdLista() {
		return idLista;
	}
	public int getIdProducto() {
		return idProducto;
	}
	
	public boolean getTerminado() {
		return terminado;
	}
	public void setTerminado() {
		this.terminado = true;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	
	@Override
	public String toString() {
		return "Pedido [nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad + ", dni=" + dni
				+ ", idLista=" + idLista + ", idProducto=" + idProducto + ", terminado=" + terminado + "]";
	}
	}