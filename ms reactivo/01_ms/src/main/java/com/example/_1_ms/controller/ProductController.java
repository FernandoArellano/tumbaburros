package com.example._1_ms.controller;

import com.example._1_ms.model.Producto;
import com.example._1_ms.service.ProductosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductosService service;

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> getProducts(){
        return ResponseEntity.ok(service.catalogo());
    }

    @GetMapping("/productos/{categoria}")
    public ResponseEntity<List<Producto>> productosCategoria(@PathVariable String categoria){
        return ResponseEntity.ok(service.productorsCategoria(categoria));
    }

    @GetMapping("/producto")
    public ResponseEntity<Producto> productoCodigo(@RequestParam int cod){
        return ResponseEntity.ok(service.productoCodigo(cod));
    }

    @PostMapping("/alta")
    public ResponseEntity<Void> altaProducto(@RequestBody Producto producto){
        service.altaProducto(producto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Producto> eliminarProducto(@RequestParam int cod){
        Producto p = service.eliminarProducto(cod);
        return ResponseEntity.ok(p);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Producto> actualizarPrecio(@RequestParam int cod, @RequestParam double precio){
        return ResponseEntity.ok(service.actualizarPrecio(cod, precio));
    }
}
