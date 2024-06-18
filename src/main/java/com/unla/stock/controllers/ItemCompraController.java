package com.unla.stock.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.unla.stock.entities.ItemCompra;
import com.unla.stock.services.IItemCompraService;
import com.unla.stock.services.implementation.ItemCompraService;

@RestController
@RequestMapping("/itemsCompra")
public class ItemCompraController {

    //@Autowired
    private ItemCompraService itemCompraService;

    @GetMapping
    public List<ItemCompra> getAllItemsCompra() {
        return itemCompraService.getAllItemsCompra();
    }

    @GetMapping("/{id}")
    public ItemCompra getItemCompraById(@PathVariable int id) {
        return itemCompraService.getItemCompraById(id);
    }

    @PostMapping
    public ItemCompra createItemCompra(@RequestBody ItemCompra itemCompra) {
        return itemCompraService.saveItemCompra(itemCompra);
    }

    @PutMapping("/{id}")
    public ItemCompra updateItemCompra(@PathVariable int id, @RequestBody ItemCompra itemCompra) {
        ItemCompra existingItemCompra = itemCompraService.getItemCompraById(id);
        if (existingItemCompra != null) {
            existingItemCompra.setCantidad(itemCompra.getCantidad());
            existingItemCompra.setProducto(itemCompra.getProducto());
            existingItemCompra.setCompra(itemCompra.getCompra());
            return itemCompraService.saveItemCompra(existingItemCompra);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteItemCompra(@PathVariable int id) {
        itemCompraService.deleteItemCompra(id);
    }
}