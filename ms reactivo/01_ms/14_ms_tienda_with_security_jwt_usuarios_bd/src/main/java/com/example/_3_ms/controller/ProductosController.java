package com.example._3_ms.controller;

import com.example._3_ms.model.Producto;
import com.example._3_ms.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductosController {

    private final ProductoService service;

    @GetMapping("/productos")
    public ResponseEntity<Flux<Producto>> productos(){
        return ResponseEntity.ok(service.catalogo());
    }

    @GetMapping("/productos/por-categoria")
    public ResponseEntity<Flux<Producto>> productosCategoria(@RequestParam String categoria){
        return ResponseEntity.ok(service.productorsCategoria(categoria));
    }

    @GetMapping("/producto/{cod}")
    public ResponseEntity<Mono<Producto>> productoCodigo(@PathVariable int cod){
        return ResponseEntity.ok(service.productoCodigo(cod));
    }

    @PostMapping("/productos")
    public Mono<ResponseEntity<Void>> altaProducto(@RequestBody Producto producto){
        return service.altaProducto(producto)
                .thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
    }

    @DeleteMapping("/productos/{cod}")
    public Mono<ResponseEntity<Producto>> eliminarProducto(@PathVariable int cod){
        return service.eliminarProducto(cod)
                .map(pr->new ResponseEntity<>(pr,HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @PutMapping("/productos/{cod}")
    public Mono<ResponseEntity<Producto>> actualizarPrecio(@PathVariable int cod, @RequestParam double precio){
        return service.actualizarPrecio(cod, precio)
                .map(pr->new ResponseEntity<>(pr,HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }
}
