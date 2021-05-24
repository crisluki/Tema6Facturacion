package Tema6Facturacion;

public class Usuario{
private String nombre;
private String apellidos;
private String dni;
private int telefono;
public Usuario(String nombre, String apellidos, String dni, int telefono) {
	super();
	this.nombre = nombre;
	this.apellidos = apellidos;
	this.dni = dni;
	this.telefono = telefono;
}
public String getNombre() {
	return nombre;
}
public String getApellidos() {
	return apellidos;
}
public String getDni() {
	return dni;
}

public int getTelefono() {
	return telefono;
}
public void setTelefono(int telefono) {
	this.telefono = telefono;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}
public void setDni(String dni) {
	this.dni = dni;
}
@Override
public String toString() {
	return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", telefono=" + telefono + "]";
}

}
