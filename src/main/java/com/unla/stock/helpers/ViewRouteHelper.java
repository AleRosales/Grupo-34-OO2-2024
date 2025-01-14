package com.unla.stock.helpers;

public class ViewRouteHelper {
	/**** Views ****/
	//HOME
	public final static String INDEX = "home/index";
	
	//USER
	public final static String USER_LOGIN = "user/login";
	public final static String USER_LOGOUT = "user/logout";
	
	//productos
	public final static String PRODUCTOS = "productos/productosList";
	public final static String PRODUCTOS_FORM = "productos/productosForm";
	
	//inventario
	public final static String ALMACEN = "almacen/almacenList";
	public final static String ALMACEN_FORM = "almacen/almacenForm";

	/**** Redirects ****/
	public final static String ROUTE = "/index";


	//compra
	public static final String COMPRAS = "compras/index";
	public static final String COMPRAS_FORM = "compras/form";	

	//ordenDeCompra
	public final static String ORDEN_DE_COMPRA = "ordenDeCompra/ordenDeCompraList";
	public final static String ORDEN_DE_COMPRA_FORM = "ordenDeCompra/ordenDeCompraForm";
	
}
