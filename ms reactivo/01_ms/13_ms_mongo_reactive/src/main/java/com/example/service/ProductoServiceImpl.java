package com.example.service;

import com.example.ProductosRepository;
import com.example.model.Producto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService{

    private final ProductosRepository repository;

    @Override
    public Flux<Producto> catalogo() {
        return repository.findAll()
                .delayElements(Duration.ofSeconds(1));
    }

    @Override
    public Flux<Producto> productorsCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }


    public Mono<Producto> productoCodigo(int cod) {
        return repository.findById(cod);
    }

    @Override
    public Mono<Void> altaProducto(Producto producto) {
//        return productoCodigo(producto.getCodProducto())
//                .switchIfEmpty(Mono.just(producto).flatMap(repository::save))
//                        .then(); //Mono<Void>

        return productoCodigo(producto.getId())
                .flatMap(existing -> Mono.error(new RuntimeException("Producto ya existe")))
                .switchIfEmpty(repository.save(producto))
                .then();
    }

    @Override
    public Mono<Producto> eliminarProducto(int cod) {
        return productoCodigo(cod)
                .flatMap(p->repository.deleteById(cod) //Mono<Void>
                .then(Mono.just(p))); // Mono<Producto>

    }

    @Override
    public Mono<Producto> actualizarPrecio(int cod, double precio) {
        return productoCodigo(cod)
                .flatMap(p->{
                    p.setPrecioUnitario(precio);
                    return repository.save(p); //Mono<Product>
                });
    }
}
