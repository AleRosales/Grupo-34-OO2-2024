package com.unla.stock.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.stock.entities.Compra;
import com.unla.stock.repositories.ICompraRepository;
import com.unla.stock.services.ICompraService;

@Service("compraService")
public class CompraService implements ICompraService {
	 	@Autowired
	    private ICompraRepository compraRepository;

	    @Override
	    public List<Compra> getAllCompras() {
	        return compraRepository.findAll();
	    }

	    @Override
	    public Compra getCompraById(int id) {
	        return compraRepository.findById(id).orElse(null);
	    }

	    @Override
	    public Compra saveCompra(Compra compra) {
	        return compraRepository.save(compra);
	    }

	    @Override
	    public void deleteCompra(int id) {
	        compraRepository.deleteById(id);
	    }
}
