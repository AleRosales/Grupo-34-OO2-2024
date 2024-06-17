package com.unla.stock.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.stock.entities.Almacen;

@Repository("almacenRepository")
public interface IAlmacenRepository extends JpaRepository<Almacen, Serializable> {

	@Query("FROM Almacen u LEFT JOIN FETCH u.productos p WHERE u.id = (:id)")
	public abstract Optional<Almacen> findByIdAndFetchProductosAlmacen(@Param("id") int id);

}
