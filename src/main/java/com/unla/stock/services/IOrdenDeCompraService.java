package com.unla.stock.services;

import java.util.List;
import java.util.Optional;

import com.unla.stock.entities.OrdenDeCompra;

public interface IOrdenDeCompraService {
	
	public  List<OrdenDeCompra> getAll();

	public  OrdenDeCompra insertOrUpdate(OrdenDeCompra ordenDeCompra);

	public boolean remove(int id);
	
	public Optional<OrdenDeCompra> findById(int id) throws Exception;

}
