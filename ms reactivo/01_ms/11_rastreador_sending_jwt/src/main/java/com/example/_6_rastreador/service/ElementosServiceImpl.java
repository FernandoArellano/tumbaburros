package com.example._6_rastreador.service;

import com.example._3_ms.model.Credentials;
import com.example._6_rastreador.model.Elemento;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Base64;

@Service
public class ElementosServiceImpl implements ElementosService{
    String url1="http://localhost:10000";
    String url2="http://localhost:10000";

    @Value("${user}")
    private String user;

    @Value("${pwd}")
    private String pwd;

    private String token1;
    private String token2;

    @PostConstruct
    public void init(){
        System.out.println("user: " +user);
        System.out.println("pwd: " +pwd);
        Credentials credentials1 = new Credentials(user, pwd);
        Credentials credentials2 = new Credentials(user, pwd);

        loadToken(url1, credentials1)
                .subscribe(s->token1=s);
        loadToken(url2, credentials2)
                .subscribe(s->token2=s);
    }

    private Mono<String> loadToken(String url, Credentials credentials){
        return WebClient.create(url)
                .post()
                .uri("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN)
                .bodyValue(credentials)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public Flux<Elemento> elementosPrecioMax(double precioMax) {
        Flux<Elemento> flux1= catalogo(url1, "tienda 1", token1);
        Flux<Elemento> flux2= catalogo(url2, "tienda 2", token2);

        return Flux.merge(flux1,flux2)
                .filter(e->e.getPrecioUnitario()<=precioMax)
                .delayElements(Duration.ofSeconds(1));
    }

    private Flux<Elemento> catalogo(String url, String tienda, String token){
        WebClient webClient= WebClient.create(url);

                return webClient
                        .get()
                        .uri("/productos")
                        .header("Authorization", "Bearer "+ token)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToFlux(Elemento.class)
                        .map(e->{
                            e.setTienda(tienda);
                            return e;
                        });
    }

}
