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

import com.unla.stock.entities.EstadoOrdenCompra;
import com.unla.stock.entities.OrdenDeCompra;
import com.unla.stock.helpers.ViewRouteHelper;
import com.unla.stock.services.implementation.AlmacenService;
import com.unla.stock.services.implementation.OrdenDeCompraService;
import com.unla.stock.services.implementation.ProductoService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/ordenDeCompra")
public class OrdenDeCompraController {

	private ModelMapper modelMapper = new ModelMapper();

	private OrdenDeCompraService ordenDeCompraService;
    private ProductoService productoService;
	private AlmacenService almacenService;


	public OrdenDeCompraController(OrdenDeCompraService ordenDeCompraService,ProductoService productoService,AlmacenService inventarioService) {
		this.ordenDeCompraService = ordenDeCompraService;
		this.productoService=productoService;
		this.almacenService = inventarioService;

	}

	@GetMapping("")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.ORDEN_DE_COMPRA);
		modelAndView.addObject("ordenesDeCompra", ordenDeCompraService.getAll());
		return modelAndView;
	}

	@GetMapping("/{id}")
	public ModelAndView form(@PathVariable("id") int id) throws Exception {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.ORDEN_DE_COMPRA_FORM);
		OrdenDeCompra ordenDeCompra = modelMapper.map(ordenDeCompraService.findById(id).get(), OrdenDeCompra.class);
		if (ordenDeCompra == null)
			return null;
		modelAndView.addObject("ordenDeCompra", ordenDeCompra);
		modelAndView.addObject("productos",productoService.getAll());
		modelAndView.addObject("almacenes",almacenService.getAll());
		modelAndView.addObject("estados",EstadoOrdenCompra.values());

		return modelAndView;
	}

	@GetMapping("/nuevo")
	public ModelAndView nuevo(@ModelAttribute("ordenDeCompra") OrdenDeCompra ordenDeCompra) {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.ORDEN_DE_COMPRA_FORM);
		modelAndView.addObject("ordenDeCompra", new OrdenDeCompra());
		modelAndView.addObject("productos",productoService.getAll());
		modelAndView.addObject("almacenes",almacenService.getAll());
		modelAndView.addObject("estados",EstadoOrdenCompra.values());

		return modelAndView;
	}

	@PostMapping("/guardar")
	public ModelAndView guardar(@ModelAttribute("ordenDeCompra") OrdenDeCompra ordenDeCompra) throws Exception {
		//try {
			ordenDeCompra=ordenDeCompraService.insertOrUpdate(ordenDeCompra);
			if(ordenDeCompra.getEstado()==EstadoOrdenCompra.ENTREGADO) {
				//inserta stock en almacen
				almacenService.entradaProductoAlmacen(ordenDeCompra.getProducto(), ordenDeCompra.getCantidad(), ordenDeCompra.getAlmacen());
			}
		/*} catch (Exception e) {
			ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.ORDEN_DE_COMPRA_FORM);
			modelAndView.addObject("ordenDeCompra", new OrdenDeCompra());
			modelAndView.addObject("productos",productoService.getAll());
			modelAndView.addObject("almacenes",almacenService.getAll());
			modelAndView.addObject("estados",EstadoOrdenCompra.values());
			modelAndView.addObject("error", e.getMessage());
			System.out.println(e);
			return modelAndView;
		}*/
		return list();
	}

	@PostMapping("/eliminar/{id}")
	public ModelAndView eliminarProducto(@PathVariable("id") int id) {
		ordenDeCompraService.remove(id);
		return list();
	}
}
