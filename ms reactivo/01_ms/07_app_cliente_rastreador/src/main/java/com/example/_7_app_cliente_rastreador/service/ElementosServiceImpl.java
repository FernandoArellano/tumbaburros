package com.example._7_app_cliente_rastreador.service;

import com.example._7_app_cliente_rastreador.model.Elemento;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ElementosServiceImpl implements ElementosService{

    String url="http://localhost:9000";
    private final WebClient webClient;

    @Override
    public Flux<Elemento> elementosPorPrecio(double precioMax) {
        //WebClient webClient= WebClient.create(url);
        return webClient

                .get()
                .uri(url+"/elementos/"+precioMax)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Elemento.class);

    }


}
