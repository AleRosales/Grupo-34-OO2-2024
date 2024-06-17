package com.unla.stock.services.implementation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.unla.stock.entities.Compra;
import com.unla.stock.repositories.ICompraRepository;
import com.unla.stock.services.ICompraService;

@Service("compraService")
public class CompraService implements ICompraService {
	private ICompraRepository compraRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public CompraService(ICompraRepository compraRepository) {
		this.compraRepository = compraRepository;
	}

	@Override
	public List<Compra> getAll() {
		return compraRepository.findAll();
	}

	@Override
	public Optional<Compra> findById(int id) throws Exception {
		return compraRepository.findById(id);
	}

	@Override
	public Compra insertOrUpdate(Compra compraModel) {
		Compra  compra = compraRepository.save(modelMapper.map(compraModel, Compra.class));
		return modelMapper.map(compra, Compra.class);	}

	@Override
	public boolean remove(int id) {
		try {
			compraRepository.deleteById(id);
			return true;
			
		} catch(Exception e) {
			return false;	
		}
		 
	}
}
