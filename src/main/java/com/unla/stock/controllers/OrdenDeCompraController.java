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

import com.unla.stock.entities.OrdenDeCompra;
import com.unla.stock.helpers.ViewRouteHelper;
import com.unla.stock.services.implementation.OrdenDeCompraService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/ordenDeCompra")
public class OrdenDeCompraController {

	private ModelMapper modelMapper = new ModelMapper();

	private OrdenDeCompraService ordenDeCompraService;

	public OrdenDeCompraController(OrdenDeCompraService ordenDeCompraService) {
		this.ordenDeCompraService = ordenDeCompraService;
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
		return modelAndView;
	}

	@GetMapping("/nuevo")
	public ModelAndView nuevo(@ModelAttribute("ordenDeCompra") OrdenDeCompra ordenDeCompra) {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.ORDEN_DE_COMPRA_FORM);
		modelAndView.addObject("ordenDeCompra", new OrdenDeCompra());
		return modelAndView;
	}

	@PostMapping("/guardar")
	public ModelAndView guardarInventario(@ModelAttribute("ordenDeCompra") OrdenDeCompra ordenDeCompra) {
		try {
			ordenDeCompraService.insertOrUpdate(ordenDeCompra);
		} catch (Exception e) {
			ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.ORDEN_DE_COMPRA_FORM);
			modelAndView.addObject("ordenDeCompra", ordenDeCompra);
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
		return list();
	}

	@PostMapping("/eliminar/{id}")
	public ModelAndView eliminarProducto(@PathVariable("id") int id) {
		ordenDeCompraService.remove(id);
		return list();
	}
}
