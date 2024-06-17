package com.unla.stock.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nombre", unique=true, nullable=false, length=255)
	private String nombre;
<<<<<<< HEAD
	
	private float precio;

	public void actualizarStockVenta(int cantidad) {
		// TODO Auto-generated method stub
		
	}
=======
>>>>>>> aea540782d019b82e409060c478e73edd680b438
}
