package com.unla.stock.services.implementation;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.unla.stock.entities.Almacen;
import com.unla.stock.repositories.IAlmacenRepository;
import com.unla.stock.services.IAlmacenService;

@Service("almacenService")
public class AlmacenService implements IAlmacenService{

	private IAlmacenRepository almacenRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public AlmacenService(IAlmacenRepository almacenRepository) {
		this.almacenRepository = almacenRepository;
	}

	@Override
	public List<Almacen> getAll() {
		return almacenRepository.findAll();
	}

	@Override
	public Almacen insertOrUpdate(Almacen inventarioModel) {
		Almacen  inventario = almacenRepository.save(modelMapper.map(inventarioModel, Almacen.class));
		return modelMapper.map(inventario, Almacen.class);
	}

	@Override
	public boolean remove(int id) {
		try {
			almacenRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public Optional<Almacen> findByIdAndFetchProductosAlmacen(int id) throws Exception {
		return almacenRepository.findByIdAndFetchProductosAlmacen(id);
	}

}
