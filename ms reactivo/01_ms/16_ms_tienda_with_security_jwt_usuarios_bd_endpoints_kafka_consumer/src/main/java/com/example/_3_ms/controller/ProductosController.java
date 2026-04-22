package com.example._3_ms.controller;

import com.example._3_ms.model.Producto;
import com.example._3_ms.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@Configuration
public class ProductosController {

    private final ProductoService service;

//    @GetMapping("/productos")
//    public ResponseEntity<Flux<Producto>> productos(){
//        return ResponseEntity.ok(service.catalogo());
//    }
//
//    @GetMapping("/productos/por-categoria")
//    public ResponseEntity<Flux<Producto>> productosCategoria(@RequestParam String categoria){
//        return ResponseEntity.ok(service.productorsCategoria(categoria));
//    }
//
//    @GetMapping("/producto/{cod}")
//    public ResponseEntity<Mono<Producto>> productoCodigo(@PathVariable int cod){
//        return ResponseEntity.ok(service.productoCodigo(cod));
//    }
//
//    @PostMapping("/productos")
//    public Mono<ResponseEntity<Void>> altaProducto(@RequestBody Producto producto){
//        return service.altaProducto(producto)
//                .thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
//    }
//
//    @DeleteMapping("/productos/{cod}")
//    public Mono<ResponseEntity<Producto>> eliminarProducto(@PathVariable int cod){
//        return service.eliminarProducto(cod)
//                .map(pr->new ResponseEntity<>(pr,HttpStatus.OK))
//                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
//    }
//
//    @PutMapping("/productos/{cod}")
//    public Mono<ResponseEntity<Producto>> actualizarPrecio(@PathVariable int cod, @RequestParam double precio){
//        return service.actualizarPrecio(cod, precio)
//                .map(pr->new ResponseEntity<>(pr,HttpStatus.OK))
//                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
//    }


    @Bean
    public RouterFunction<ServerResponse> respuestas(){
        return RouterFunctions.route(RequestPredicates.GET("/productos"),
                req->ServerResponse.ok()
                        .body(service.catalogo(),Producto.class))
                .andRoute(RequestPredicates.GET("productos/por-categoria"),
                req->ServerResponse.ok()
                        .body(service.productorsCategoria(
                                req.queryParam("categoria").get()),Producto.class))
                .andRoute(RequestPredicates.GET("producto/{cod}"),
                        req->ServerResponse.ok()
                                .body(service.productoCodigo(
                                        Integer.parseInt(req.pathVariable("cod"))
                                                ), Producto.class))
                .andRoute(RequestPredicates.POST("productos"),
                        req->req.bodyToMono(Producto.class)
                                .flatMap(p->{
                                    return service.altaProducto(p);
                                })
                                .flatMap(v->ServerResponse.ok()
                                        .build())
                )
                .andRoute(RequestPredicates.DELETE("productos/{cod}"),
                        req->
                        service.eliminarProducto(
                                Integer.parseInt(req.pathVariable("cod"))
                                )
                                .flatMap(p->ServerResponse.ok()
                                        .bodyValue(p))
                                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)
                                        .build())
                )
                .andRoute(RequestPredicates.PUT("productos/{cod}"),
                        req-> service.actualizarPrecio(
                                Integer.parseInt(req.pathVariable("cod"))
                                , req.queryParam("precio")
                                                .map(s->Integer.parseInt(s)).get())
                                .flatMap(p->ServerResponse.ok()
                                        .bodyValue(p))
                                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)
                                        .build())
                );

    }
}
















