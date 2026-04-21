package com.example._3_ms;

import com.example._3_ms.model.Producto;
import com.example._3_ms.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
class ApplicationTests {

    @Autowired
    ProductoService service;

    @Test
    void testProductosCategoria() {
        StepVerifier.create(service.productorsCategoria("Alimentación"))
                .expectNextMatches(p->p.getNombre().equals("Azucar"))
                .expectNextMatches(p->p.getNombre().equals("Leche"))
                .expectNextMatches(p->p.getNombre().equals("Huevos"))
                .verifyComplete();
    }

    @Test
    void testEliminarProducto() {
        StepVerifier.create(service.eliminarProducto(103))
                .expectNextMatches(p->p.getNombre().equals("Mesa"))
                .verifyComplete();
    }
    @Test
    void testAltaProducto() {
        Producto p = new Producto(150,"ptest","cat1",10,2, true);
        StepVerifier.create(service.altaProducto(p))
                .expectComplete();
    }

    @Test
    void testCatalogo() {
        StepVerifier.create(service.catalogo())
                .expectNextCount(8)
                .verifyComplete();
    }

}
