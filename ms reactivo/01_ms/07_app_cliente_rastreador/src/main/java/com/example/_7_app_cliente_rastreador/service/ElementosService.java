package com.example._7_app_cliente_rastreador.service;

import com.example._7_app_cliente_rastreador.model.Elemento;
import reactor.core.publisher.Flux;

public interface ElementosService {
    Flux<Elemento> elementosPorPrecio(double precioMax);
}
