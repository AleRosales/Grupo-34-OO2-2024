package com.unla.stock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.unla.stock.entities.Compra;
import com.unla.stock.helpers.ViewRouteHelper;
import com.unla.stock.services.implementation.CompraService;
import com.unla.stock.services.implementation.ProductoService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;
    
    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public ModelAndView comprasList() {
        ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.COMPRAS);
        modelAndView.addObject("compras", compraService.getAllCompras());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView comprasForm(@PathVariable("id") int id) throws Exception {
        ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.COMPRAS_FORM);
        Compra compra = compraService.getCompraById(id);
        modelAndView.addObject("productos", productoService.getAll());
        modelAndView.addObject("compra", compra);
        return modelAndView;
    }

    @GetMapping("/nuevo")
    public ModelAndView nuevaCompra(@ModelAttribute("compra") Compra compra) {
        ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.COMPRAS_FORM);
        modelAndView.addObject("productos", productoService.getAll());
        modelAndView.addObject("compras", compraService.getAllCompras());
        return modelAndView;
    }
    
    
    @PostMapping("/crearCompra")
    public ModelAndView crearCompra(@ModelAttribute("compra") Compra compra) {
        ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.COMPRAS);
        compraService.saveCompra(compra);
        modelAndView.addObject("productos", productoService.getAll());
        modelAndView.addObject("compras", compraService.getAllCompras());
        return modelAndView;
    }

    @PostMapping("/eliminar/{id}")
    public ModelAndView eliminarCompra(@PathVariable("id") int id) {
        compraService.deleteCompra(id);
        return comprasList();
    }
}
