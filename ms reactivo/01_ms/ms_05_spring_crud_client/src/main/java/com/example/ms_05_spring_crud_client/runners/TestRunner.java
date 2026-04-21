package com.example.ms_05_spring_crud_client.runners;

import com.example.ms_05_spring_crud_client.model.Producto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TestRunner implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        WebClient client = WebClient.create("http://localhost:8080");
//        Flux<Producto> flux = client.get()
//                .uri("/productos")
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToFlux(Producto.class);
//        flux.subscribe(System.out::println);

//        client.post()
//                .uri("/productos")
//                .body(Mono.just(new Producto(200,"prueba","categoria test",5,20)),Producto.class)
//                .retrieve()
//                .bodyToMono(Void.class)
//                .doOnTerminate(()-> System.out.println("Alta de producto"))
//                .block();

//        Mono<Producto> monoFind = client
//                .get()
//                .uri("producto/161")
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(Producto.class);
//
//        monoFind.subscribe(p-> System.out.println(p));
//        monoFind.switchIfEmpty(Mono.just(new Producto()).map(p->{
//            System.out.println("no se ha encontrado");
//            return p;
//        })).block();

        client
                .delete()
                .uri("productos/107")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(h->h.is4xxClientError(), t->{
                    System.out.println("no se encontro el producto");
                    return Mono.empty();
                })
                .bodyToMono(Producto.class)
                .subscribe(System.out::println);

    }
}
