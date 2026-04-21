package com.example._3_ms.service;

import com.example._3_ms.model.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{

    private static List<Producto> productos=new ArrayList<>(List.of(new Producto(100,"Azucar","Alimentación",1.10,20),
            new Producto(111,"Pan","Alimentación",1.30,15),
            new Producto(112,"Esponja","Limpieza",2,30),
            new Producto(113,"Jarron","Hogar",40,10),
            new Producto(114,"Sofa","Hogar",80,4),
            new Producto(115,"Harina","Alimentación",3,30),
            new Producto(116,"Fregona","Limpieza",3.40,6),
            new Producto(117,"Cubo","Limpieza",2.5,12)));

    @Override
    public Flux<Producto> catalogo() {
        return Flux.fromIterable(productos)
                .delayElements(Duration.ofSeconds(1));
    }

    @Override
    public Flux<Producto> productorsCategoria(String categoria) {
        return Flux.fromIterable(productos)
                .filter(p->p.getCategoria().equals(categoria));
    }


    public Mono<Producto> productoCodigo(int cod) {
        return catalogo().filter(p->p.getCodProducto()==cod).next();
    }

    @Override
    public Mono<Void> altaProducto(Producto producto) {
        return productoCodigo(producto.getCodProducto())
                .switchIfEmpty(Mono.fromRunnable(() -> productos.add(producto)))
                .then();
    }

    @Override
    public Mono<Producto> eliminarProducto(int cod) {
        return productoCodigo(cod)
                .map(p->{
                   productos.removeIf(pr->pr.getCodProducto()==cod);
                   return p;
                });

    }

    @Override
    public Mono<Producto> actualizarPrecio(int cod, double precio) {
        return productoCodigo(cod)
                .map(p->{
                    p.setPrecioUnitario(precio);
                    return p;
                });
    }
}
