package com.tienda.ecomerce1.service;

import com.tienda.ecomerce1.model.Producto;
import com.tienda.ecomerce1.repository.ProductoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @NotNull
    @Override
    public Producto save(@NotNull Producto producto) {
        return productoRepository.save(producto);
    }

    @NotNull
    @Override
    public Optional<Producto> get(int id) {
        return productoRepository.findById(id);
    }

    @Override
    public void update(@NotNull Producto producto) {
       productoRepository.save(producto);
    }

    @Override
    public void delete(int id) {
      productoRepository.deleteById(id);
    }

    @NotNull
    @Override
    public List<Producto> listAll() {
        return productoRepository.findAll();
    }
}
