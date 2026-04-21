package com.example._6_rastreador.service;

import com.example._6_rastreador.model.Elemento;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Base64;

@Service
public class ElementosServiceImpl implements ElementosService{
    String url1="http://localhost:10000";
    String url2="http://localhost:9100";

    @Override
    public Flux<Elemento> elementosPrecioMax(double precioMax) {
        Flux<Elemento> flux1= catalogo(url1, "tienda 1");
        Flux<Elemento> flux2= catalogo(url2, "tienda 2");

        return Flux.merge(flux1,flux2)
                .filter(e->e.getPrecioUnitario()<=precioMax)
                .delayElements(Duration.ofSeconds(1));
    }

    private Flux<Elemento> catalogo(String url, String tienda){
        WebClient webClient= WebClient.create(url);

                return webClient
                        .get()
                        .uri("/productos")
                        .header("Authorization", "Basic "+getEncodedBase64Credentials("user1","user1"))
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToFlux(Elemento.class)
                        .map(e->{
                            e.setTienda(tienda);
                            return e;
                        });
    }

    private String getEncodedBase64Credentials(String user, String pwd){
        String credential = user+":"+pwd;
        return Base64.getEncoder().encodeToString(credential.getBytes());
    }
}
