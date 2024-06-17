package com.unla.stock.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Almacen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nombre", unique=true, nullable=false, length=255)
	private String nombre;
	
	@UpdateTimestamp
	private LocalDateTime fechaActualizacion;
	
	@CreationTimestamp
	private LocalDateTime fechaCreacion;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="almacen")
	private Set<ProductoAlmacen> productos = new HashSet<>();
	
}
