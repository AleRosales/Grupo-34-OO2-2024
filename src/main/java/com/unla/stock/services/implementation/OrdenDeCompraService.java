package com.unla.stock.services.implementation;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.unla.stock.entities.OrdenDeCompra;
import com.unla.stock.repositories.IOrdenDeCompraRepository;
import com.unla.stock.services.IOrdenDeCompraService;

@Service("ordenDeCompraService")
public class OrdenDeCompraService implements IOrdenDeCompraService{

	private IOrdenDeCompraRepository ordenDeCompraRepository;


	private ModelMapper modelMapper = new ModelMapper();

	public OrdenDeCompraService(IOrdenDeCompraRepository ordenDeCompraRepository) {
		this.ordenDeCompraRepository = ordenDeCompraRepository;
	}

	@Override
	public List<OrdenDeCompra> getAll() {
		return ordenDeCompraRepository.findAll();
	}

	@Override
	public OrdenDeCompra insertOrUpdate(OrdenDeCompra ordenDeCompraModel) {
		OrdenDeCompra  ordenDeCompra = ordenDeCompraRepository.save(modelMapper.map(ordenDeCompraModel, OrdenDeCompra.class));
		return modelMapper.map(ordenDeCompra, OrdenDeCompra.class);
	}

	@Override
	public boolean remove(int id) {
		try {
			ordenDeCompraRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public Optional<OrdenDeCompra> findById(int id) throws Exception {
		return ordenDeCompraRepository.findById(id);
	}



}
