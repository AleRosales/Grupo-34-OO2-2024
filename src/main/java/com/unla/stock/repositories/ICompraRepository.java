package com.unla.stock.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.stock.entities.Compra;

@Repository("compraRepository")
public interface ICompraRepository extends JpaRepository<Compra, Serializable> {


}
