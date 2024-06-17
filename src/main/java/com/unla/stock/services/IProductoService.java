package com.unla.stock.services;

import java.util.List;
import java.util.Optional;

import com.unla.stock.entities.Producto;

public interface IProductoService {
	
	public  List<Producto> getAll();

	public  Producto insertOrUpdate(Producto producto);

	public boolean remove(int id);
	
	public Optional<Producto> findById(int id) throws Exception;

}
