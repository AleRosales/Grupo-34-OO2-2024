package com.unla.stock.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.unla.stock.entities.Compra;
import com.unla.stock.entities.ItemCompra;
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
        Compra compra = compraService.getCompraById(id); // Aquí deberías manejar si el id no existe

        modelAndView.addObject("compra", compra);
        return modelAndView;
    }

    
//    @GetMapping("/nuevo")
//    public ModelAndView nueva(@ModelAttribute("compra") Compra compra) {
//        ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.COMPRAS_FORM);
//        modelAndView.addObject("compra", new Compra());
//        return modelAndView;
//    }
    
    public ModelAndView nuevaCompra(Model model) {
        ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.COMPRAS_FORM);
        Compra compra = new Compra();
        //compra.setItemsCompra(new ArrayList<>()); // Inicializar lista de ítems
        model.addAttribute("compra", compra);
        model.addAttribute("productos", productoService.getAll()); // Obtener todos los productos disponibles
        return modelAndView;
    }
    
    
    

//    @PostMapping("/crearCompra")
//    public ModelAndView crearCompra(@ModelAttribute("compra") Compra compra) {
//        try {
//            compraService.saveCompra(compra);
//        } catch (Exception e) {
//            ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.COMPRAS_FORM);
//            modelAndView.addObject("compra", compra);
//            modelAndView.addObject("error", e.getMessage());
//            return modelAndView;
//        }
//        return comprasList();
//    }
    
    @PostMapping("/crearCompra")
    public ModelAndView crearCompra(@ModelAttribute("compra") Compra compra) {
        ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.COMPRAS);

        // Lógica para calcular importes y total
        float total = 0;
        for (ItemCompra item : compra.getItemsCompra()) {
            float importe = item.getCantidad() * item.getProducto().getPrecio(); // Calcula el importe según la cantidad y el precio del producto
            item.setImporte(importe);
            total += importe;
        }
        compra.setImporte(total);

        // Aquí guardas la compra en la base de datos (usando el servicio correspondiente)
        compraService.saveCompra(compra);

        return modelAndView;
    }

    @PostMapping("/eliminar/{id}")
    public ModelAndView eliminarCompra(@PathVariable("id") int id) {
        compraService.deleteCompra(id);
        return comprasList();
    }
}
