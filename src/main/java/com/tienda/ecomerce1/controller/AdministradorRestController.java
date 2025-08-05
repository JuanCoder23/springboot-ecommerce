package com.tienda.ecomerce1.controller;

import com.tienda.ecomerce1.model.Producto;
import com.tienda.ecomerce1.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/administrador")
@RequiredArgsConstructor
public class AdministradorRestController {

    private final ProductoService productoService;

    // Obtener todos los productos
    @GetMapping("/productos")
    public List<Producto> listarProductos() {
        return productoService.listAll();
    }

    // Obtener un producto específico por ID
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable("id") int id) {
        Optional<Producto> producto = productoService.get(id);

        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get()); // Si el producto existe, devolverlo con un 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Si no se encuentra, devolver 404 Not Found
        }
    }

    // Crear un nuevo producto
    @PostMapping("/productos")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto); // Retorna el nuevo producto con un 201 Created
    }

    // Actualizar un producto existente
    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable int id, @RequestBody Producto producto) {
        Optional<Producto> productoExistente = productoService.get(id);

        if (productoExistente.isPresent()) {
            producto.setId_producto(id); // Asegura que el ID del producto coincida
            productoService.update(producto);
            return ResponseEntity.ok(producto); // Si el producto existe, se actualiza y retorna con un 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Si el producto no existe, devuelve 404 Not Found
        }
    }

    // Eliminar un producto
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable int id) {
        Optional<Producto> productoExistente = productoService.get(id);

        if (productoExistente.isPresent()) {
            productoService.delete(id); // Eliminar el producto si existe
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Respuesta 204 No Content al eliminar
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Si no se encuentra el producto, devuelve 404 Not Found
        }
    }
}
