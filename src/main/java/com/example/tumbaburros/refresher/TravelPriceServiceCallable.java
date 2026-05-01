package com.example.tumbaburros.refresher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TravelPriceServiceCallable {

    public static void main(String[] args) {


        List<Callable<Double>> tasks = new ArrayList<>();
        for(int i=0; i<10;i++){
            Callable<Double> flightTask = ()->{
                Thread.sleep(300);
                if(Math.random()>0.8) throw new RuntimeException("Api de vuelos caida");
                return 450.0;
            };
            tasks.add(flightTask);
        }


        ExecutorService executorService = Executors.newFixedThreadPool(4);

        try{
            for(int i=0; i<tasks.size(); i++){
                Future<Double> future = executorService.submit(tasks.get(i));
                Double price = future.get(2, TimeUnit.SECONDS);
                System.out.println("precio obtenido:" + price);
            }

        }catch (ExecutionException e) {
            System.out.println("error en la tarea: " + e.getCause().getMessage());
        } catch (TimeoutException e) {
            System.out.println("La consulta tardo demasiado");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {

            executorService.shutdown();
        }
    }
}
