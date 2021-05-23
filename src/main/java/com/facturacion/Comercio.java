package com.facturacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Comercio {
	Almacen almacen = new Almacen();
	Calendar fecha = new GregorianCalendar();

	public Comercio() {
	}

	// -----------------------------------------Listas--------------------------------------------------------
	Map<String, Usuario> listausuarios = new HashMap<>();
	List<Factura> listaFacturas = new ArrayList<>();
	List<Pedido> listaPedidos = new ArrayList<>();

	// -----------------------------------------Metodos-------------------------------------------------------
	public String nuevoClient(String name, String surname, String dni, int telefono) {// Se registra nuevo cliente con
		// String name, String surname,
		// String dni, int telefono.
		if (listausuarios.containsKey(dni)) {
			return "Ya existe ese usuario";
		}
		listausuarios.put(dni, new Usuario(name, surname, dni, telefono));
		return "Usuario Añadido";

	}

	public String registerProduct(int id, String name, float precio, int cant) {// Se registra nuevo producto con int
		// id,String name, int precio, int cant
		if (!almacen.encontrarProduct(id)) {
			almacen.registerNewProduct(name, id, precio, cant);
			return "Producto Añadido";
		}
		return "Ya existe ese producto";
	}

	// TODO: Javadoc
	public String añadirProduct(int id, int cant) {// Se añaden producto con int id, int cant
		if (almacen.encontrarProduct(id)) {
			almacen.addcantProduct(id, cant);
			return "Cantidad de producto añadida";
		}
		return "Producto no existe";
	}

	// TODO: Javadoc
	public String añadirPedido(String dni, int idProducto, int cantidad) {// Se ve si existe usuario y si existe añade
		// un
		// pedido String dni,int idProducto ,int
		// cantidad
		float precio = verPrecio(idProducto);
		int idLista = listaPedidos.size();
		if ((listausuarios.containsKey(dni)) && (almacen.hayCantidad(idProducto, cantidad))) {
			listaPedidos.add(new Pedido(idLista, dni, idProducto, cantidad, precio, false));
			return "Pedido Añadido";
		}
		return "No se ha podido añadir";
	}

	// TODO: Javadoc
	public String pasarPedidoAFactura(String dni) {// Se sacan los pedidos por dni y se almacenan a ListaFactura
		for (Pedido Unpedido : listaPedidos) {// int idFactura, String dni, String estadoPago,float subtotal, float
			// precio,float cantidad
			if ((Unpedido.getDni().equals(dni)) && (!Unpedido.getTerminado())) {
				listaFacturas.add(new Factura(sacarfecha(), dni, facturaTerminado(Unpedido.getDni()), 0, Unpedido.getCantidad(),
						Unpedido.getPrecio()));
				Unpedido.setTerminado();
				return "Se encontro coincidencia";
			}
		}
		return "No se encontro pedidos asociados al DNI";
	}

	public String mostrarFactura(int idFactura) {// Se una Factura por idFactura
		if (!existFactura(idFactura)) {
			// TODO: Tirar excepción y controlarla
			return "Not found";
		}
		return "+-----------------------------------------------------------------------------+\n"
				+ "|                                                                             |\n"
				+ mostrarDATOSFacturaUsuario(idFactura) + "\n" + "|Factura:" + mostrarFacturaUsuario(idFactura) + "\n"
				+ "|                                                                             |\n"
				+ "| Producto          Unidades       Precio        Descuento      Subtotal      |\n"
				+ "|-----------------------------------------------------------------------------|\n" + listarPedidos(idFactura)
				+ "\n" + "|                                                                             |\n"
				+ buscarEnFactura(idFactura) + "\n"
				+ "|                                                                             |\n"
				+ "+-----------------------------------------------------------------------------+\n";

	}

	public String mostrarHistorial(String dni) {// Se sacan los id de Facturas que coincidan por dni.
		if (listausuarios.containsKey(dni)) {
			for (Factura UnaFactura : listaFacturas) {
				if (UnaFactura.getDni().equals(dni)) {
					return "Nº->" + UnaFactura.getIdFactura();
				}
			}
		}
		return "No hay pedidos asociados a ese dni";
	}

	// ------------------------------------Existencia---------------------------------------------------
	public String facturaTerminado(String dni) { // Se comprueba que existe el pedido en la lista por coincidendia con
		// un
		// atributo.
		for (Pedido pedido : listaPedidos) {
			if (pedido.getDni().equals(dni) && !pedido.getTerminado()) {
				return "PENDIENTE DE PAGO";
			}
		}
		return "PAGADO";
	}

	public String facturaEstado(int id) { // Se comprueba que existe el pedido en la lista por coincidendia con un
		// atributo.
		for (Factura factura : listaFacturas) {
			if (factura.getIdFactura() == id) {
				if (factura.getEstadoPago().equals("PENDIENTE DE PAGO")) {
					return "PENDIENTE DE PAGO";
				} else {
					return "PAGADO";
				}
			}
		}
		return "null";
	}

	public boolean existFactura(int idFactura) { // Se comprueba que existe el pedido en la lista por coincidendia con
		// un
		// atributo.
		for (Factura factura : listaFacturas) {
			if (factura.getIdFactura() == idFactura) {
				return true;
			}
		}
		return false;
	}

	public boolean existPedido(String dni) { // Se comprueba que existe el pedido en la lista por coincidendia con un
		// atributo.
		for (Pedido UnPedido : listaPedidos) {
			if (UnPedido.getDni().equals(dni)) {
				return true;
			}
		}
		return false;
	}

	// --------------------------------Busqueda de
	// objetos----------------------------------------------------------
	public String verProductos() {
		return almacen.verProductos();
	}

	public float verPrecio(int idProducto) {
		return almacen.verPrecio(idProducto);
	}

	public String verNombre(int idProducto) {
		return almacen.verNombre(idProducto);
	}

	public String BuscarNombrePerdido(float cantidad, float descuento, String dni, int id) {
		String cadena = "";
		for (Pedido UnPedido : listaPedidos) {
			if (UnPedido.getDni().equals(dni)) {
				cadena = cadena + "| " + almacen.verNombre(UnPedido.getIdProducto()) + "               "
						+ UnPedido.getCantidad() + "            " + UnPedido.getPrecio() + "                 " + descuento
						+ "                    |\n";
			}
		}
		return cadena;
	}

	public String listarPedidos(int id) {
		for (Factura UnaFactura : listaFacturas) {
			if (UnaFactura.getIdFactura() == id) {

				return BuscarNombrePerdido(UnaFactura.getCantidad(), UnaFactura.getDescuento(), UnaFactura.getDni(), id);
			}
		}
		return null;
	}

	public String mostrarFacturaUsuario(int id) {
		for (Factura UnaFactura : listaFacturas) {
			if (UnaFactura.getIdFactura() == id) {
				return "|" + UnaFactura.getIdFactura() + "(" + UnaFactura.getEstadoPago() + ")";
			}
		}
		return null;
	}

	public String mostrarDATOSFacturaUsuario(int id) {
		for (Factura UnaFactura : listaFacturas) {
			if (UnaFactura.getIdFactura() == id) {
				return "|" + listausuarios.get(UnaFactura.getDni()).toString();
			}
		}
		return null;
	}

	public String buscarEnFactura(int id) {
		String cadena = " ";
		for (Factura UnaFactura : listaFacturas) {
			if (UnaFactura.getIdFactura() == id) {
				return "| Subtotal                                                        " + UnaFactura.getSubtotal()
						+ "      |\n" + "| IVA 21%                                                         " + UnaFactura.getIva()
						+ "%" + "       |\n" + "| Total                                                           "
						+ UnaFactura.getSubtotal() * UnaFactura.getIva() + "      |";
			}
		}
		return cadena;
	}

	// ------------------------------------Numero
	// Factura-------------------------------------------------------
	public int sacarfecha() {
		int mes = fecha.get(Calendar.MONTH);
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		int hora = fecha.get(Calendar.HOUR_OF_DAY);
		int minuto = fecha.get(Calendar.MINUTE);
		return dia + mes + hora + minuto;
	}
}
