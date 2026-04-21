package com.example;

import com.example.model.Producto;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductosRepository extends ReactiveMongoRepository<Producto, Integer> {

    Flux<Producto> findByCategoria(String categoria);

    Mono<Void> deleteByNombre(String nombre);

    @DeleteQuery("{'precioUnitario':{$lt: ?0} }")
    Mono<Void> deletePrecio(double precioMax);
}
