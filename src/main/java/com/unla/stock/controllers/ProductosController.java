package com.unla.stock.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.stock.entities.Producto;
import com.unla.stock.helpers.ViewRouteHelper;
import com.unla.stock.services.implementation.ProductoService;


@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/productos")
public class ProductosController {

	private ModelMapper modelMapper = new ModelMapper();

	private ProductoService productoService;
	
	public ProductosController(ProductoService productoService) {
		this.productoService = productoService;
	} 
	
	
	@GetMapping("")
	public ModelAndView productosList() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PRODUCTOS);
		modelAndView.addObject("productos", productoService.getAll());
		return modelAndView;
	}
	@GetMapping("/{id}")
	public ModelAndView productosForm(@PathVariable("id") int id)  throws Exception{
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PRODUCTOS_FORM);
		Producto producto =  modelMapper.map(productoService.findById(id).get(), Producto.class);

		if(producto==null) return null;
		modelAndView.addObject("producto", producto);
		return modelAndView;
	}
	@GetMapping("/nuevo")
	public ModelAndView nuevo(@ModelAttribute("producto") Producto producto) {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PRODUCTOS_FORM);
		modelAndView.addObject("producto", new Producto());
		return modelAndView;		
	}
	@PostMapping("/crearProducto")
	public ModelAndView crearProducto(@ModelAttribute("producto") Producto producto) {
		try {
			productoService.insertOrUpdate(producto);
		}catch (Exception e) {
			ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PRODUCTOS_FORM);
			modelAndView.addObject("producto", producto);
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;		
		}
		return productosList();
	}
	@PostMapping("/eliminar/{id}")
	public ModelAndView eliminarProducto(@PathVariable("id") int id) {
		productoService.remove(id);
		return productosList();
	}
}
