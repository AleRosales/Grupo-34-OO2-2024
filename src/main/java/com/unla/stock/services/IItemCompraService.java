package com.unla.stock.services;

import java.util.List;
import java.util.Optional;

import com.unla.stock.entities.ItemCompra;


public interface IItemCompraService {

	public List<ItemCompra> getAll();

	public Optional<ItemCompra> findById(int id) throws Exception;

	public ItemCompra insertOrUpdate(ItemCompra itemCompra);

	public boolean remove(int id);
	
	
}