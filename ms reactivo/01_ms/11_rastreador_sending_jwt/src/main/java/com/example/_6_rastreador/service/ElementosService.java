package com.example._6_rastreador.service;

import com.example._6_rastreador.model.Elemento;
import reactor.core.publisher.Flux;

public interface ElementosService {
    Flux<Elemento> elementosPrecioMax(double precioMax);
}
