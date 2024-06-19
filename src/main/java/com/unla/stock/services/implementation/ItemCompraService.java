//package com.unla.stock.services.implementation;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.unla.stock.entities.ItemCompra;
//import com.unla.stock.repositories.IItemCompraRepository;
//import com.unla.stock.services.IItemCompraService;
//
//public class ItemCompraService implements IItemCompraService {
//	@Autowired
//    private IItemCompraRepository itemCompraRepository;
//
//    @Override
//    public List<ItemCompra> getAllItemsCompra() {
//        return itemCompraRepository.findAll();
//    }
//
//    @Override
//    public ItemCompra getItemCompraById(int id) {
//        return itemCompraRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public ItemCompra saveItemCompra(ItemCompra itemCompra) {
//        return itemCompraRepository.save(itemCompra);
//    }
//
//    @Override
//    public void deleteItemCompra(int id) {
//        itemCompraRepository.deleteById(id);
//    }
//
//}