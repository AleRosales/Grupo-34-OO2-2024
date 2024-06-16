package com.unla.stock.services;

import java.util.List;
import java.util.Optional;

import com.unla.stock.entities.Almacen;

public interface IAlmacenService {
	
	public  List<Almacen> getAll();

	public  Almacen insertOrUpdate(Almacen inventario);

	public boolean remove(int id);
	
	public Optional<Almacen> findByIdAndFetchProductosAlmacen(int id) throws Exception;

}
