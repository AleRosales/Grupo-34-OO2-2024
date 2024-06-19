//package com.unla.stock.entities;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Table(name = "itemCompra")
//@Getter @Setter @NoArgsConstructor
//public class ItemCompra {
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private int id;
//	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="producto", nullable=true)
//	private Producto producto;
//	private int cantidad;
//	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="compra", nullable=true)
//	private Compra compra;
//	
//	private double importe;
//	
//	public ItemCompra(int id, Producto producto, int cantidad, Compra compra) {
//		this.id = id;
//		this.producto = producto;
//		this.cantidad = cantidad;
//		this.compra = compra;
//		
//	}
//
//	public float calcularImporteItem() {
//		return cantidad * producto.getPrecio();
//	}
//	
//}