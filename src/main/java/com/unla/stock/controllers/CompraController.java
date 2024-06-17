package com.unla.stock.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.stock.entities.Compra;
import com.unla.stock.helpers.ViewRouteHelper;
import com.unla.stock.services.implementation.CompraService;

@Controller
//@PreAuthorize("hasRole('ROLE_CLIENTE')")
@RequestMapping("/compras")
public class CompraController {

	private ModelMapper modelMapper = new ModelMapper();

	private CompraService compraService;
	
	public CompraController(CompraService compraService) {
		this.compraService = compraService;
	} 
	
	
	@GetMapping("")
	public ModelAndView comprasList() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.COMPRAS);
		modelAndView.addObject("compras", compraService.getAll());
		return modelAndView;
	}
	@GetMapping("/{id}")
	public ModelAndView comprasForm(@PathVariable("id") int id)  throws Exception{
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.COMPRAS_FORM);
		Compra compra =  modelMapper.map(compraService.findById(id).get(), Compra.class);

		if(compra==null) return null;
		modelAndView.addObject("compra", compra);
		return modelAndView;
	}
	
	@GetMapping("/nuevo")
	public ModelAndView nueva(@ModelAttribute("compra") Compra compra) {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.COMPRAS_FORM);
		modelAndView.addObject("compra", new Compra());
		return modelAndView;		
	}
	@PostMapping("/crearCompra")
	public ModelAndView crearCompra(@ModelAttribute("compra") Compra compra) {
		try {
			compraService.insertOrUpdate(compra);
		}catch (Exception e) {
			ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.COMPRAS_FORM);
			modelAndView.addObject("compra", compra);
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;		
		}
		return comprasList();
	}
	@PostMapping("/eliminar/{id}")
	public ModelAndView eliminarProducto(@PathVariable("id") int id) {
		compraService.remove(id);
		return comprasList();
	}
}
