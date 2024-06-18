package com.unla.stock.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "compra")
@Getter @Setter @NoArgsConstructor
public class Compra {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@CreationTimestamp
	private LocalDate fecha;
	
	private String cliente;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="compra")
	private Set<ItemCompra> itemsCompra;
	
	private float importe;
	
	public Compra(int id, LocalDate fecha, String cliente, Set<ItemCompra> itemsCompra, float importe) {
		this.id = id;
		this.fecha = fecha;
		this.cliente = cliente;
		this.itemsCompra = itemsCompra;
		this.importe = importe;
		itemsCompra = new HashSet<>();
	}
	
	public float calcularImporte() {
		float importe = 0;
		for(ItemCompra itemCompra: itemsCompra) {
			importe = importe + itemCompra.calcularImporteItem();
		}
		return importe;
	}
	
	public void comprarProductos() {
		importe = calcularImporte();
		for(ItemCompra itemCompra : itemsCompra) {
			itemCompra.getProducto().actualizarStockVenta(itemCompra.getCantidad());
		}
	}

//	public void setItemsCompra(Object itemsCompra2) {
//		this.itemsCompra = (Set<ItemCompra>) itemsCompra2;		
//	}


}
