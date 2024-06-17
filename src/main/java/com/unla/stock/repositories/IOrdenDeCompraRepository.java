package com.unla.stock.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.stock.entities.OrdenDeCompra;

@Repository("ordenDeCompraRepository")
public interface IOrdenDeCompraRepository extends JpaRepository<OrdenDeCompra, Serializable> {

	@Query("FROM OrdenDeCompra u WHERE u.id = (:id)")
	public abstract Optional<OrdenDeCompra> findById(@Param("id") int id);

}
