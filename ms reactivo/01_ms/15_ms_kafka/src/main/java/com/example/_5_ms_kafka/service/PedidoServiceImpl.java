package com.example._5_ms_kafka.service;

import com.example._5_ms_kafka.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    @Value("${topic}")
    String topic;

    private final KafkaTemplate<String, Pedido> kafkaTemplate;


    @Override
    public void registrarPedido(Pedido pedido) {
        CompletableFuture<SendResult<String, Pedido>> future = kafkaTemplate.send(topic, pedido);
        future.whenCompleteAsync((r,t) ->{
            if(t!=null){
                throw new RuntimeException("");
            }
            System.out.println("Se ha registrado el pedido"+r.getProducerRecord().value()
            +" en el topico "+ r.getRecordMetadata().topic());
        });

        future.join();
    }
}
