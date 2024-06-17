package com.unla.stock.services.implementation;

import java.util.List;
import java.util.Optional;

import com.unla.stock.entities.ItemCompra;
import com.unla.stock.repositories.IItemCompraRepository;
import com.unla.stock.services.IItemCompraService;

public class ItemCompraService implements IItemCompraService {
	
	private IItemCompraRepository itemCompraRepository; 

	@Override
	public List<ItemCompra> getAll() {
		return itemCompraRepository.findAll();
	}

	@Override
	public Optional<ItemCompra> findById(int id) throws Exception {
		return itemCompraRepository.findById(id);
	}

	@Override
	public ItemCompra insertOrUpdate(ItemCompra itemCompra) {
		return itemCompraRepository.save(itemCompra);
	}

	@Override
	public boolean remove(int id) {
		// TODO Auto-generated method stub
		try {
			itemCompraRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
			
		}
	}

}