package com.example._7_app_cliente_rastreador.controller;

import com.example._7_app_cliente_rastreador.service.ElementosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

@Controller
@RequiredArgsConstructor
public class ElementosController {

    private final ElementosService service;


    @GetMapping("/buscar")
    public String buscar(@RequestParam double precio, Model model){
        IReactiveDataDriverContextVariable reactive =
                new ReactiveDataDriverContextVariable(service.elementosPorPrecio(precio),1);
        model.addAttribute("resultado", reactive);
        return "listado";
    }

    @GetMapping("/")
    public String inicio(){
        return "inicio";
    }
}
