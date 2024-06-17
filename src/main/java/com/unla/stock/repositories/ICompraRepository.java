package com.unla.stock.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.stock.entities.Compra;

@Repository("compraRepository")
public interface ICompraRepository extends JpaRepository<Compra, Serializable> {

	public abstract Optional<Compra> findById(int id);

}
