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
Map<String, List<Usuario>>listausuarios = new HashMap<String, List<Usuario>>();
List<Factura> ListaFacturas = new ArrayList<>();
List<Pedido> ListaPedidos = new ArrayList<>();
//-----------------------------------------Metodos-------------------------------------------------------
	public String nuevoClient(String name, String surname, String dni, int telefono) {//Se registra nuevo cliente con String name, String surname, String dni, int telefono.
		if(!listausuarios.containsKey(dni)) {
			listausuarios.put(dni,new Usuario(name,surname,dni,telefono));
			return "Usuario A�adido";
		}
		return "Ya existe ese usuario";
	}
	public String registerProduct(int id,String name, float precio, int cant) {//Se registra nuevo producto con int id,String name, int precio, int cant
		if(!almacen.encontrarProduct(id)) {
			almacen.registerNewProduct(name, id, precio, cant);
			return "Producto A�adido";
		}
		return "Ya existe ese producto";
	}
	public String a�adirProduct(int id, int cant) {//Se a�aden producto con int id, int cant
		if(almacen.encontrarProduct(id)) {
			almacen.addcantProduct(id, cant);
			return "Cantidad de producto a�adida";
		}
		return "Producto no existe";
	}
	public String a�adirPedido(String dni,int idProducto ,int cantidad){//Se ve si existe usuario y si existe a�ade un pedido String dni,int idProducto ,int cantidad
		float precio = verPrecio(idProducto);
		int idLista = ListaPedidos.size();
		if((listausuarios.containsKey(dni))&&(almacen.hayCantidad(idProducto,cantidad))) {
				ListaPedidos.add( new Pedido(idLista,dni,idProducto,cantidad,precio,false));
				return "Pedido A�adido";	
		}
		return "No se ha podido a�adir";
	}
	public String pasarPedidoAFactura(String dni) {//Se sacan los pedidos por dni y se almacenan a ListaFactura
		for (Pedido Unpedido  : ListaPedidos) {//int idFactura, String dni, String estadoPago,float subtotal, float precio,float cantidad
		    if ((Unpedido.getDni().equals(dni))&&(!Unpedido.getTerminado())){
		    		ListaFacturas.add( new Factura(sacarfecha(), dni, facturaTerminado(Unpedido.getDni()), 0,Unpedido.getCantidad(), Unpedido.getPrecio()));
		    		Unpedido.setTerminado();
		    		return "Se encontro coincidencia";
		    }
		}
		return "No se encontro pedidos asociados al DNI";
	}
	public String mostrarFactura(int idFactura) {//Se una Factura por idFactura
		if(existFactura(idFactura)){
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
		return "Not found";
	}
	public String mostrarHistorial(String dni) {//Se sacan los id de Facturas que coincidan por dni.
		if(listausuarios.containsKey(dni)) {
			for (Factura UnaFactura : ListaFacturas) {
			    if (UnaFactura.getDni().equals(dni)) {
				return  "N�->"+UnaFactura.getIdFactura();
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
	public String listarPerdidos(String dni) {
		String cadena= "";
		for (Pedido UnPedido  : ListaPedidos) {
		    if (UnPedido.getDni()==dni) {
		    	cadena=cadena+ "| "+almacen.verNombre(UnPedido.getIdProducto())+"          "+UnPedido.getCantidad()+"          "+UnPedido.getPrecio()+"          "+"\n";
		    }
		}
		return cadena;
	}
	public String listarPedidos(int id) {
		String acac= "";
		for (Factura UnaFactura : ListaFacturas) {
		    if (UnaFactura.getIdFactura()==id) {
		    	System.out.println(UnaFactura.getDni());
		    	acac=acac+ listarPerdidos(UnaFactura.getDni())+"\n";
		    }
		}
		return acac;
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
		        return "|"+listausuarios.get(UnaFactura.getDni());
		    }
		}
		return null;
	}
	public String buscarEnFactura(int id) {
		String cadena = " ";
		for (Factura UnaFactura : ListaFacturas) {
		    if (UnaFactura.getIdFactura()==id) {
		        return  "| Subtotal                                                     "+UnaFactura.getSubtotal()+"      |\n"+
					    "| IVA 21%                                                      "+UnaFactura.getIva()+"%"+"       |\n"+
						"| Total                                                        "+UnaFactura.getSubtotal()*UnaFactura.getIva()+UnaFactura.getPrecio()+"  |";
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