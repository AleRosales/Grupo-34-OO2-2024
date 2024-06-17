package com.unla.stock.services.implementation;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.unla.stock.entities.Producto;
import com.unla.stock.repositories.IProductoRepository;
import com.unla.stock.services.IProductoService;

@Service("productoService")
public class ProductoService implements IProductoService{

	private IProductoRepository productoRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public ProductoService(IProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	@Override
	public List<Producto> getAll() {
		return productoRepository.findAll();
	}

	@Override
	public Producto insertOrUpdate(Producto productoModel) {
		Producto  producto = productoRepository.save(modelMapper.map(productoModel, Producto.class));
		return modelMapper.map(producto, Producto.class);
	}

	@Override
	public boolean remove(int id) {
		try {
			productoRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public Optional<Producto> findById(int id) throws Exception {
		return productoRepository.findById(id);
	}

}
