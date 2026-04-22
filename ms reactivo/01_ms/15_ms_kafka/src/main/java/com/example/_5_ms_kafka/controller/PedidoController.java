package com.example._5_ms_kafka.controller;

import com.example._5_ms_kafka.model.Pedido;
import com.example._5_ms_kafka.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping("alta")
    public ResponseEntity<Void> nuevoPedido(@RequestBody Pedido pedido){
        try{
            pedidoService.registrarPedido(pedido);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
