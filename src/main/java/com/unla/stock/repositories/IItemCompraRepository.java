package com.unla.stock.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.stock.entities.ItemCompra;

@Repository("itemCompraRepository")
public interface IItemCompraRepository extends JpaRepository<ItemCompra, Serializable> {

	public abstract Optional<ItemCompra> findById(int id);


}
