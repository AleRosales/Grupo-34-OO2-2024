package com.unla.stock.services;

import java.util.List;
import java.util.Optional;

import com.unla.stock.entities.Almacen;
import com.unla.stock.entities.Producto;
import com.unla.stock.entities.ProductoAlmacen;

public interface IAlmacenService {
	
	public  List<Almacen> getAll();

	public  Almacen insertOrUpdate(Almacen almacen);

	public boolean remove(int id);
	
	public Optional<Almacen> findByIdAndFetchProductosAlmacen(int id) throws Exception;
	
	public  ProductoAlmacen entradaProductoAlmacen(Producto producto,int cantidad,Almacen almacen)  throws Exception;
	
	public  ProductoAlmacen salidaProductoAlmacen(Producto producto,int cantidad,Almacen almacen)  throws Exception;
	
	public  int cantidadProductoAlmacen(Producto producto,Almacen almacen);

	public  int cantidadProducto(Producto producto);


}
