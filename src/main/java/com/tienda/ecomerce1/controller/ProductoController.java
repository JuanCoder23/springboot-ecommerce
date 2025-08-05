package com.tienda.ecomerce1.controller;

import com.tienda.ecomerce1.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.tienda.ecomerce1.model.Producto;
import com.tienda.ecomerce1.model.Usuario;
import com.tienda.ecomerce1.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@RestController

@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    private final ProductoService productoService;


    private final UploadFileService upload;




    @GetMapping("")
    public String showProductos(Model model) {
        model.addAttribute("productos", productoService.listAll());
        return "administrador/productos/show";
    }


    @GetMapping("/create")
    public String createProductos() {
        return "administrador/productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {

        LOGGER.info("Este es el objeto de la vista producto {}", producto);

        Usuario u = new Usuario();
        u.setId_usuario(1);
        u.setNombre("");
        u.setNombre_usuario("");
        u.setDireccion("");
        u.setTelefono(33222);
        u.setCorreo("");
        u.setTipo("");
        u.setContrasena("");
        producto.setUsuario(u);

        // Guardar la imagen si existe un archivo cargado
        if (!file.isEmpty()) {
            String nombre_imagen = upload.saveImage(file);
            producto.setImagen(nombre_imagen);
        } else {
            producto.setImagen("default.png"); // Imagen por defecto si no se carga ninguna
        }

        productoService.save(producto); // Guardar producto en la base de datos

        return "redirect:/productos";
    }


    @GetMapping("/edit/{id}")
    public String editar(@PathVariable int id, Model model) {
        Optional<Producto> productoOptional = productoService.get(id);

        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            LOGGER.info("Producto buscado: {}", producto);
            model.addAttribute("producto", producto);
        } else {
            LOGGER.error("Producto con id {} no encontrado.", id);
            return "redirect:/productos";
        }

        return "administrador/productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        Producto p = new Producto();
        p = productoService.get(producto.getId_producto()).get();


        if(file.isEmpty()){// cuando editamos el producto pero no cambia la imagen

            producto.setImagen(p.getImagen());
        }else {



            if(p.getImagen().equals("default.png")){
                upload.deleteImage(p.getImagen());
            }
            String nombre_imagen = upload.saveImage(file);
            producto.setImagen(nombre_imagen);
        }
        producto.setUsuario(p.getUsuario());
        productoService.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        Producto p = new Producto();
        p = productoService.get(id).get();


        if(p.getImagen().equals("default.png")){
            upload.deleteImage(p.getImagen());
        }

        productoService.delete(id);
        return "redirect:/productos";
    }

}

