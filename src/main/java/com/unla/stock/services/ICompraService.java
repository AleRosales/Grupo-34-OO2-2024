package com.unla.stock.services;

import java.util.List;

import com.unla.stock.entities.Compra;


public interface ICompraService {
	 public List<Compra> getAllCompras();
	 public Compra getCompraById(int id);
	 public Compra saveCompra(Compra compra);
	 public void deleteCompra(int id);
	
	
}