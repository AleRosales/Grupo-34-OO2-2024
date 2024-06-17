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

import com.unla.stock.entities.Almacen;
import com.unla.stock.helpers.ViewRouteHelper;
import com.unla.stock.services.implementation.AlmacenService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/almacen")
public class AlmacenController {

	private ModelMapper modelMapper = new ModelMapper();

	private AlmacenService almacenService;

	public AlmacenController(AlmacenService inventarioService) {
		this.almacenService = inventarioService;
	}

	@GetMapping("")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.ALMACEN);
		modelAndView.addObject("almacenes", almacenService.getAll());
		return modelAndView;
	}

	@GetMapping("/{id}")
	public ModelAndView inventarioForm(@PathVariable("id") int id) throws Exception {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.ALMACEN_FORM);
		Almacen almacen = modelMapper.map(almacenService.findByIdAndFetchProductosAlmacen(id).get(), Almacen.class);
		if (almacen == null)
			return null;
		modelAndView.addObject("almacen", almacen);
		return modelAndView;
	}

	@GetMapping("/nuevo")
	public ModelAndView nuevo(@ModelAttribute("almacen") Almacen almacen) {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.ALMACEN_FORM);
		modelAndView.addObject("almacen", new Almacen());
		return modelAndView;
	}

	@PostMapping("/guardar")
	public ModelAndView guardarInventario(@ModelAttribute("almacen") Almacen almacen) {
		System.out.println("almacen -----"+almacen);
		try {
			almacenService.insertOrUpdate(almacen);
		} catch (Exception e) {
			ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.ALMACEN_FORM);
			modelAndView.addObject("almacen", almacen);
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
		return list();
	}

	@PostMapping("/eliminar/{id}")
	public ModelAndView eliminarProducto(@PathVariable("id") int id) {
		almacenService.remove(id);
		return list();
	}
}
