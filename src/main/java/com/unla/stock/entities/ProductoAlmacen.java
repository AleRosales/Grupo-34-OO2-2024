package com.unla.stock.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class ProductoAlmacen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="producto_id", nullable=false)
	private Producto producto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="almacen_id", nullable=false)
	private Almacen almacen;
	
	private int cantidad;
	
	@CreationTimestamp
	private LocalDateTime fechActualizacion;

	public ProductoAlmacen(Producto producto, Almacen almacen, int cantidad, LocalDateTime fechActualizacion) {
		super();
		this.producto = producto;
		this.almacen = almacen;
		this.cantidad = cantidad;
		this.fechActualizacion = fechActualizacion;
	}
	
	
}
