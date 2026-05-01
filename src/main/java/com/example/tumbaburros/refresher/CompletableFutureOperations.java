package com.example.tumbaburros.refresher;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompletableFutureOperations {

    public static void main(String[] args) {

        List<CompletableFuture<Void>> pruebas =
                IntStream.rangeClosed(1,3)
                        .mapToObj(i->ejecutarPrueba(i))
                        .collect(Collectors.toList());

        CompletableFuture.allOf(pruebas.toArray(new CompletableFuture[0])).join();
        System.out.println("Pruebas finalizadas");
    }

    private static CompletableFuture<Void> ejecutarPrueba(int id){
        return CompletableFuture.supplyAsync(() -> "Pedido-" +id)
                .thenApplyAsync(order -> {
                    System.out.println("[Iteracion " + id + "] Validando...");
                    return order + " [OK]";
                })
                .thenComposeAsync(validated -> CompletableFuture.supplyAsync(()->{
                    if(Math.random()>0.8) throw new RuntimeException("Falla de red");
                    return 100.0;
                }))
                .handleAsync((monto, ex) ->{
                    if(ex!=null) return "Error en prueba " + id + ": " + ex.getMessage();
                    return "Prueba " + id + " completada con exito ($" + monto + ")";
                })
                .thenAcceptAsync(resultado -> System.out.println("LOG: "+ resultado));
        }
}
