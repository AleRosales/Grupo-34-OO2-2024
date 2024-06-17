package com.unla.stock.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.stock.entities.ProductoAlmacen;

@Repository("productoAlmacenRepository")
public interface IProductoAlmacenRepository extends JpaRepository<ProductoAlmacen, Serializable> {

	@Query("FROM ProductoAlmacen u LEFT JOIN FETCH u.producto p LEFT JOIN FETCH u.almacen a WHERE p.id = (:pId) and a.id = (:aId)")
	public abstract Optional<ProductoAlmacen> findByProductosAlmacen(@Param("pId") int pId,@Param("aId") int aId);

	@Query("SELECT SUM(u.cantidad) FROM ProductoAlmacen u LEFT JOIN u.producto p LEFT JOIN u.almacen a WHERE p.id = (:pId) and a.id = (:aId)")
	public abstract Optional<Integer> cantidadProductoAlmacen(@Param("pId") int pId,@Param("aId") int aId);
	
	@Query("SELECT SUM(u.cantidad) FROM ProductoAlmacen u LEFT JOIN u.producto p WHERE p.id = (:pId)")
	public abstract Optional<Integer> cantidadProductoAlmacen(@Param("pId") int pId);
}
