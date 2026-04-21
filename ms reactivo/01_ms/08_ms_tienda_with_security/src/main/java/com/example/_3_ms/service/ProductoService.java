package com.example._3_ms.service;

import com.example._3_ms.model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductoService {
    Flux<Producto> catalogo();
    Flux<Producto> productorsCategoria(String categoria);
    Mono<Producto> productoCodigo(int cod);
    Mono<Void> altaProducto(Producto producto);
    Mono<Producto> eliminarProducto(int cod);
    Mono<Producto> actualizarPrecio(int cod, double precio);
}
