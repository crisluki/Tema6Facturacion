package Tema6Facturacion;

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
//-----------------------------------------Listas--------------------------------------------------------
Map<String, Usuario>listausuarios = new HashMap<>();
List<Factura> ListaFacturas = new ArrayList<>();
List<Pedido> ListaPedidos = new ArrayList<>();
//-----------------------------------------Metodos-------------------------------------------------------
/**
 	* @param name   El nombre del usuario
* @param surname   El nombre del usuario
* @param dni    El dni del usuario
* @param telefono   El telefono del usuario
* @return Un string que si existe el usuario
*/
	public String nuevoClient(String name, String surname, String dni, int telefono) {
		if(listausuarios.containsKey(dni)) {
			return "Ya existe ese usuario";
		}
		listausuarios.put(dni,new Usuario(name,surname,dni,telefono));
		return "Usuario Añadido";
	}
	/**
	* @param id   El nombre del usuario
	* @param name   El nombre del usuario
	* @param precio    El dni del usuario
	* @param cant   El telefono del usuario
	* @return Un string que si existe el producto
	*/
	public String registerProduct(int id,String name, float precio, int cant) {
		if(!almacen.encontrarProduct(id)) {
			almacen.registerNewProduct(name, id, precio, cant);
			return "Producto Añadido";
		}
		return "Ya existe ese producto";
	}
	/**
	* @param id    El identificador del producto
	* @param cant   La cantidad introducida en producto
	* @return Un string que muestra si existe el producto
	*/
	public String añadirProduct(int id, int cant) {
		if(almacen.encontrarProduct(id)) {
			almacen.addcantProduct(id, cant);
			return "Cantidad de producto añadida";
		}
		return "Producto no existe";
	}
	/**
	* @param idProducto   El identificador de producto
	* @param dni    El dni del usuario
	* @param cantidad   La cantidad selecionada de producto
	* @return Un string que nos informa si se puede añadir
	*/
	public String añadirPedido(String dni,int idProducto ,int cantidad){
		float precio = verPrecio(idProducto);
		int idLista = ListaPedidos.size();
		if((listausuarios.containsKey(dni))&&(almacen.hayCantidad(idProducto,cantidad))) {
				ListaPedidos.add( new Pedido(idLista,dni,idProducto,cantidad,precio,false));
				return "Pedido Añadido";	
		}
		return "No se ha podido añadir";
	}
	/**
	* @param dni Buscamos el dni del usuario con pedidos
	* @return Un string que nos informa si hay coincidencias
	*/
	public String pasarPedidoAFactura(String dni) {
		for (Pedido Unpedido  : ListaPedidos) {
		    if ((Unpedido.getDni().equals(dni))&&(!Unpedido.getTerminado())){
		    		ListaFacturas.add( new Factura(sacarfecha(), dni, facturaTerminado(Unpedido.getDni()), 0,Unpedido.getCantidad(), Unpedido.getPrecio()));
		    		Unpedido.setTerminado();
		    		return "Se encontro coincidencia";
		    }
		}
		return "No se encontro pedidos asociados al DNI";
	}
	/**
	* @param idFactura   mostramos factura por id
	* @return Pasa String con la factura o un Not found
	*/
	public String mostrarFactura(int idFactura) {
		if(!existFactura(idFactura)){
			return "Not found";
		}
		return  "+-----------------------------------------------------------------------------+\n"+
		"|                                                                             |\n"+
		mostrarDATOSFacturaUsuario(idFactura)+"\n"+
		"|Factura:"+mostrarFacturaUsuario(idFactura)+"\n"+
		"|                                                                             |\n"+
		"| Producto          Unidades       Precio        Descuento      Subtotal      |\n"+
		"|-----------------------------------------------------------------------------|\n"+
		listarPedidos(idFactura)+"\n"+
		"|                                                                             |\n"+
		buscarEnFactura(idFactura)+"\n"+
		"|                                                                             |\n"+
		"+-----------------------------------------------------------------------------+\n";
	}
	/**
	* @param dni    El dni del usuario
	* @return Un string que muestra los Id de facturas que coincida con el dni
	*/
	public String mostrarHistorial(String dni) {
		if(listausuarios.containsKey(dni)) {
			for (Factura UnaFactura : ListaFacturas) {
			    if (UnaFactura.getDni().equals(dni)) {
				return  "Nº->"+UnaFactura.getIdFactura();
			    }
			}
		}
		return "No hay pedidos asociados a ese dni";
	}
	//------------------------------------Existencia---------------------------------------------------
	public String facturaTerminado(String dni) {  //Se comprueba que existe el pedido en la lista por coincidendia con un atributo.
		for (Pedido UnPedido  : ListaPedidos) {
		    if (UnPedido.getDni()==dni) {
		    	 if (!UnPedido.getTerminado()) {
		    	 return "PENDIENTE DE PAGO";
		    	 }
		    }
		}
		return "PAGADO";
	}
	
	public String facturaEstado(int id) {  //Se comprueba que existe el pedido en la lista por coincidendia con un atributo.
		for (Factura UnaFactura  : ListaFacturas) {
		    if (UnaFactura.getIdFactura()==id) {
		    	 if (UnaFactura.getEstadoPago().equals("PENDIENTE DE PAGO")) {
		    	 return "PENDIENTE DE PAGO";
		    	 }else {
		    		 return "PAGADO";
		    	 }
		    }
		}
		return "null";
	}
	
	public boolean existFactura(int idFactura) {  //Se comprueba que existe el pedido en la lista por coincidendia con un atributo.
		for (Factura UnaFactura  : ListaFacturas) {
		    if (UnaFactura.getIdFactura()==idFactura) {
		        return true;
		    }
		}
		return false;
	}
	
	public boolean existPedido(String dni) {  //Se comprueba que existe el pedido en la lista por coincidendia con un atributo.
		for (Pedido UnPedido  : ListaPedidos) {
		    if (UnPedido.getDni()==dni) {
		        return true;
		    }
		}
		return false;
	}
	
	//--------------------------------Busqueda de objetos----------------------------------------------------------
	
	public String verProductos() {
		return almacen.verProductos();
	}
	
	public float verPrecio(int idProducto) {
		return almacen.verPrecio(idProducto);
	}
	
	public String verNombre(int idProducto) {
		return almacen.verNombre(idProducto);
	}
	
	public String BuscarNombrePerdido(float cantidad,float descuento,String dni,int id ) {
		String cadena= "";
		for (Pedido UnPedido  : ListaPedidos) {
		    if (UnPedido.getDni().equals(dni)) {
		    	cadena=cadena+ "| "+almacen.verNombre(UnPedido.getIdProducto())+"               "+UnPedido.getCantidad()+"            "+UnPedido.getPrecio()+"                 "+descuento+"                    |\n";
		    }
		}
		return cadena;
	}
	
	public String listarPedidos(int id) {
		for (Factura UnaFactura : ListaFacturas) {
		    if (UnaFactura.getIdFactura()==id) {    	
		    	return BuscarNombrePerdido(UnaFactura.getCantidad(),UnaFactura.getDescuento(),UnaFactura.getDni(),id);
		    }
		}
		return null;
	}
	
	public String mostrarFacturaUsuario(int id) {
		for (Factura UnaFactura : ListaFacturas) {
		    if (UnaFactura.getIdFactura()==id) {
		        return "|"+UnaFactura.getIdFactura()+"("+UnaFactura.getEstadoPago()+")";
		    }
		}
		return null;
	}
	
	public String mostrarDATOSFacturaUsuario(int id) {
		for (Factura UnaFactura : ListaFacturas) {
		    if (UnaFactura.getIdFactura()==id) {
		        return "|"+listausuarios.get(UnaFactura.getDni()).toString();
		    }
		}
		return null;
	}
	public String buscarEnFactura(int id) {
		String cadena = " ";
		for (Factura UnaFactura : ListaFacturas) {
		    if (UnaFactura.getIdFactura()==id) {
		        return  "| Subtotal                                                        "+UnaFactura.getSubtotal()+"      |\n"+
					    "| IVA 21%                                                         "+UnaFactura.getIva()+"%"+"       |\n"+
						"| Total                                                           "+UnaFactura.getSubtotal()*UnaFactura.getIva()+"      |";
		    }
		}
		return cadena;
	}
	
//------------------------------------Numero Factura-------------------------------------------------------
	public int sacarfecha() {
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
		return dia+mes+hora+minuto;
	}
}