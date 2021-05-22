package Tema6Facturacion;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Usuario implements List<Usuario> {
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
public boolean add(Usuario arg0) {
	return false;
}
@Override
public void add(int arg0, Usuario arg1) {
}
@Override
public boolean addAll(Collection<? extends Usuario> arg0) {
	return false;
}
@Override
public boolean addAll(int arg0, Collection<? extends Usuario> arg1) {
	return false;
}
@Override
public void clear() {
	
}
@Override
public boolean contains(Object arg0) {
	return false;
}
@Override
public boolean containsAll(Collection<?> arg0) {
	return false;
}
@Override
public Usuario get(int arg0) {
	return null;
}
@Override
public int indexOf(Object arg0) {
	return 0;
}
@Override
public boolean isEmpty() {
	return false;
}
@Override
public Iterator<Usuario> iterator() {
	return null;
}
@Override
public int lastIndexOf(Object arg0) {
	return 0;
}
@Override
public ListIterator<Usuario> listIterator() {
	return null;
}
@Override
public ListIterator<Usuario> listIterator(int arg0) {
	return null;
}
@Override
public boolean remove(Object arg0) {
	return false;
}
@Override
public Usuario remove(int arg0) {
	return null;
}
@Override
public boolean removeAll(Collection<?> arg0) {
	return false;
}
@Override
public boolean retainAll(Collection<?> arg0) {
	return false;
}
@Override
public Usuario set(int arg0, Usuario arg1) {
	return null;
}
@Override
public int size() {
	return 0;
}
@Override
public List<Usuario> subList(int arg0, int arg1) {
	return null;
}
@Override
public Object[] toArray() {
	return null;
}
@Override
public <T> T[] toArray(T[] arg0) {
	return null;
}
}
