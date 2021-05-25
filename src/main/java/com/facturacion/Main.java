package com.facturacion;

import java.util.Scanner;

public class Main {
	static Scanner entradanumeros = new Scanner(System.in);
	static Scanner entradatexto = new Scanner(System.in);

	public static void main(String[] args) {
		Comercio tienda = new Comercio();
		int opcion;
		do {
			System.out.println("+-----------------------------------------------+");
			System.out.println("|*1*Registrar un cliente                        |");
			System.out.println("|*2*Incorporación de nuevos productos al almacén|");
			System.out.println("|*3*Reposición de existencia de productos       |");
			System.out.println("|*4*Generación de pedidos                       |");
			System.out.println("|*5*Generación de factura                       |");
			System.out.println("|*6*Visualizar por pantalla una factura         |");
			System.out.println("|*7*Visualizar las facturas de un cliente       |");
			System.out.println("|*8*Guardar una factura en un archivo           |");
			System.out.println("+-----------------------------------------------+");
			opcion = entradanumeros.nextInt();
			switch (opcion) {
				case 1:
					System.out.println("|*1*Registrar un cliente                        |");
					System.out.println("Introduce nombre");
					String nombre = entradatexto.nextLine();
					System.out.println("Introduce apellidos");
					String apellidos = entradatexto.nextLine();
					System.out.println("Introduce DNI");
					String DNI = entradatexto.nextLine();
					System.out.println("Introduce telefono");
					int telefono = entradanumeros.nextInt();
					System.out.println(tienda.nuevoClient(nombre, apellidos, DNI, telefono));
					break;
				case 2:
					System.out.println("|*2*Incorporación de nuevos productos al almacén|");
					System.out.println("Introduce el identificador del producto");
					int idProducto = entradanumeros.nextInt();
					System.out.println("Introduce el nombre");
					String nombreProducto = entradatexto.nextLine();
					System.out.println("Introduce la cantidad");
					int cantidadProducto = entradanumeros.nextInt();
					System.out.println("Introduce el precio");
					int precio = entradanumeros.nextInt();
					System.out.println(tienda.registerProduct(idProducto, nombreProducto, precio, cantidadProducto));
					break;
				case 3:
					System.out.println("|*3*Reposición de existencia de productos       |");
					System.out.println("Introduce el identificador");
					int idProduct = entradanumeros.nextInt();
					System.out.println("Introduce cantidad de producto");
					int cantidadad = entradanumeros.nextInt();
					System.out.println(tienda.añadirProduct(idProduct, cantidadad));
					break;
				case 4:
					System.out.println("|*4*Generación de pedidos                       |");
					System.out.println("Introduce el dni");
					String dniCliente = entradatexto.nextLine();
					System.out.println(tienda.verProductos());
					System.out.println("Introduce el id del producto que comprar");
					int idProductSelecionado = entradanumeros.nextInt();
					System.out.println("Introduce la cantidad del producto que comprar");
					int cantidadProductSelecionado = entradanumeros.nextInt();
					System.out.println(tienda.añadirPedido(dniCliente, idProductSelecionado, cantidadProductSelecionado));// String
					break;
				case 5:
					System.out.println("|*5*Generación de factura                       |");
					System.out.println("introduce dni");
					String dniClienteC = entradatexto.nextLine();
					System.out.println(tienda.pasarPedidoAFactura(dniClienteC));
					break;
				case 6:
					System.out.println("|*6*Visualizar por pantalla una factura         |");
					System.out.println("introduce id de Factura");
					int idFactura = entradatexto.nextInt();
					System.out.println(tienda.mostrarFactura(idFactura));
					break;
				case 7:
					System.out.println("|*7*Visualizar las facturas de un cliente       |");
					System.out.println("introduce dni");
					String dniClient = entradatexto.nextLine();
					System.out.println(tienda.mostrarHistorial(dniClient));
					break;
				case 8:
					System.out.println("|*8*Guardar una factura en un archivo           |");
					System.out.println("introduce identificador");
					String dniDeClient = entradatexto.nextLine();
					System.out.println(tienda.pasarPedidoAFactura(dniDeClient));
					break;
				default:
					System.out.println("*********OPCION NO VALIDA**********");
			}
		} while (opcion != 10);
	}
}
