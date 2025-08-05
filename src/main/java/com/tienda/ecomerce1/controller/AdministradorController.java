package com.tienda.ecomerce1.controller;


import com.tienda.ecomerce1.model.Producto;
import com.tienda.ecomerce1.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.processing.Generated;
import java.util.List;

@Controller
@RequestMapping("/administrador")
@RequiredArgsConstructor
public class AdministradorController {


    private final ProductoService productoService;

    @GetMapping("")
    public String home(Model model) {

        List<Producto> productos = productoService.listAll();
        model.addAttribute("productos", productos);
        return "administrador/home";
    }

}
