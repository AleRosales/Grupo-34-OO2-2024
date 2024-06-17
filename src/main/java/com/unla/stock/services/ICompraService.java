package com.unla.stock.services;

import java.util.List;
import java.util.Optional;

import com.unla.stock.entities.Compra;


public interface ICompraService {

	public List<Compra> getAll();

	public Compra insertOrUpdate(Compra compra);

	public boolean remove(int id);
	
	public Optional<Compra> findById(int id) throws Exception;
	
	
}