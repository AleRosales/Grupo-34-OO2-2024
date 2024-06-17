package com.unla.stock.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.stock.entities.Producto;


@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable> {

	public abstract Optional<Producto> findById(int id);

}
