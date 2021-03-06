package com.facturacion;

import java.util.ArrayList;
import java.util.List;

public class Almacen {
	List<Producto> listaProductos = new ArrayList<>();

	public Almacen() {
		listaProductos.add(new Producto(1, "cocos", 1, 10));
		listaProductos.add(new Producto(2, "cocacola", 2, 10));
		listaProductos.add(new Producto(3, "pepsi", 2, 10));
		listaProductos.add(new Producto(4, "monster", 1, 10));
		listaProductos.add(new Producto(5, "patatas", 3, 10));
	}

	public void registerNewProduct(String name, int id, float precio, int cant) {
		listaProductos.add(new Producto(id, name, precio, cant));
	}

	public void addcantProduct(int id, int cant) {
		for (Producto Unproducto : listaProductos) {
			if (Unproducto.getId() == id) {
				Unproducto.setCantidad(cant);
			}
		}
	}

	public boolean encontrarProduct(int id) {
		for (Producto Unproducto : listaProductos) {
			if (Unproducto.getId() == id) {
				return true;
			}
		}
		return false;
	}

	public boolean hayCantidad(int idProducto, int cantidad) {
		for (Producto Unproducto : listaProductos) {
			if ((Unproducto.getId() == idProducto) && (Unproducto.getCantidad() > cantidad)) {
				return true;
			}
		}
		return false;
	}

	public String verProductos() {
		String cadena = "";
		for (Producto Unproducto : listaProductos) {
			cadena = cadena + Unproducto.toString() + "\n";
		}
		return cadena;
	}

	public float verPrecio(int idProducto) {
		for (Producto Unproducto : listaProductos) {
			if (Unproducto.getId() == idProducto) {
				return Unproducto.getPrecio();
			}
		}
		return 0;
	}

	public String verNombre(int idProducto) {
		for (Producto Unproducto : listaProductos) {
			if (Unproducto.getId() == idProducto) {
				return Unproducto.getNombre();
			}
		}
		return null;
	}
}
