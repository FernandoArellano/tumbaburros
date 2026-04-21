package com.example._6_rastreador.controller;

import com.example._6_rastreador.model.Elemento;
import com.example._6_rastreador.service.ElementosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class ElementosController {


    private final ElementosService service;

    @GetMapping("/elementos/{precioMax}")
    public ResponseEntity<Flux<Elemento>> elementosPrecio(@PathVariable double precioMax){
        return new ResponseEntity<>(service.elementosPrecioMax(precioMax), HttpStatus.OK);
    }
}
