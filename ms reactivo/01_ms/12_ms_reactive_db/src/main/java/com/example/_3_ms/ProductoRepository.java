package com.example._3_ms;

import com.example._3_ms.model.Producto;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductoRepository extends ReactiveCrudRepository<Producto, Integer> {
    Flux<Producto> findByCategoria(String categoria);

    @Modifying
    @Transactional
    Mono<Void> deleteByNombre(String nombre);

    @Query("delete from productos where precioUnitario > ?")
    @Modifying
    @Transactional
    Mono<Void> deletePrecio(double precioMax);
}
