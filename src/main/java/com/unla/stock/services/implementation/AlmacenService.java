package com.unla.stock.services.implementation;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.unla.stock.entities.Almacen;
import com.unla.stock.entities.Producto;
import com.unla.stock.entities.ProductoAlmacen;
import com.unla.stock.repositories.IAlmacenRepository;
import com.unla.stock.repositories.IProductoAlmacenRepository;
import com.unla.stock.services.IAlmacenService;

@Service("almacenService")
public class AlmacenService implements IAlmacenService{

	private IAlmacenRepository almacenRepository;
	private IProductoAlmacenRepository productoAlmacenRepository;


	private ModelMapper modelMapper = new ModelMapper();

	public AlmacenService(IAlmacenRepository almacenRepository,IProductoAlmacenRepository productoAlmacenRepository) {
		this.almacenRepository = almacenRepository;
		this.productoAlmacenRepository=productoAlmacenRepository;
	}

	@Override
	public List<Almacen> getAll() {
		return almacenRepository.findAll();
	}

	@Override
	public Almacen insertOrUpdate(Almacen almacenModel) {
		Almacen  almacen = almacenRepository.save(modelMapper.map(almacenModel, Almacen.class));
		return modelMapper.map(almacen, Almacen.class);
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

	@Override
	public ProductoAlmacen entradaProductoAlmacen(Producto producto, int cantidad, Almacen almacen)  throws Exception{
		ProductoAlmacen prodAlm;
		try {
			prodAlm=this.productoAlmacenRepository.findByProductosAlmacen(producto.getId(),almacen.getId()).get();
			prodAlm.setCantidad(cantidad+prodAlm.getCantidad());

		}catch(NoSuchElementException e){
			 prodAlm=new ProductoAlmacen(producto, almacen, cantidad, LocalDateTime.now());

		}
		return 	productoAlmacenRepository.save(modelMapper.map(prodAlm, ProductoAlmacen.class));
		
	}

	@Override
	public ProductoAlmacen salidaProductoAlmacen(Producto producto, int cantidad, Almacen almacen) throws Exception {
		ProductoAlmacen prodAlm=this.productoAlmacenRepository.findByProductosAlmacen(producto.getId(),almacen.getId()).get();
		if(prodAlm!=null) {
			if(prodAlm.getCantidad()<=cantidad) {
				prodAlm.setCantidad(prodAlm.getCantidad()-cantidad);
			}else {
				throw new Exception("No hay suficiente stock en el almacen.");
			}
		}else {
			throw new Exception("No existen productos en el almacen.");
		}
		return 	productoAlmacenRepository.save(modelMapper.map(prodAlm, ProductoAlmacen.class));		
	}

	@Override
	public int cantidadProductoAlmacen(Producto producto, Almacen almacen) {
		Integer cantidad=this.productoAlmacenRepository.cantidadProductoAlmacen(producto.getId(),almacen.getId()).get();
		return cantidad;
	}

	@Override
	public int cantidadProducto(Producto producto) {
		Integer cantidad=this.productoAlmacenRepository.cantidadProductoAlmacen(producto.getId()).get();
		return cantidad;
	}

}
