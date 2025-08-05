package com.tienda.ecomerce1.controller;

import com.tienda.ecomerce1.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {


    private final ProductoService productoService;


    @GetMapping("")
    public String home(Model model) { //dto
        model.addAttribute("productos", productoService.listAll());
        return "administrador/usuario/home";
    }
}
