package com.example.tumbaburros.refresher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;



public class PuntoStampedLock {
    private double x,y;
    private final StampedLock stamped = new StampedLock();

    public void move(double deltaX, double deltaY){

        long stamp = stamped.writeLock();
        System.out.println("move stamp value:" + stamp);

        try{
            if(stamp!=0L){
                this.x+=deltaX;
                this.y+=deltaY;
            }
        } finally {
            System.out.println("move release write lock");
            stamped.unlockWrite(stamp);
        }

    }

    public double pointFromOrigin(){
        long stamp = stamped.tryOptimisticRead();

        double currentX=x;
        double currentY=y;

        if(!stamped.validate(stamp)){
            System.out.println("Had to use readLock");
            stamp = stamped.readLock();
            try{
                currentX =x;
                currentY = y;
            }finally {
                stamped.unlockRead(stamp);
            }
        }

        return Math.sqrt(currentX *currentX + currentY * currentY);
    }

    public static void main(String[] args) throws InterruptedException{
        PuntoStampedLock punto = new PuntoStampedLock();
        ExecutorService executorService = Executors.newFixedThreadPool(5);


        Runnable writer = ()-> {
            for(int i=0; i<4; i++){
                double deltaX = ThreadLocalRandom.current().nextDouble(1, 10);
                double deltaY = ThreadLocalRandom.current().nextDouble(1, 10);
                punto.move(deltaX, deltaY);
                System.out.println("-> [Escritor] Punto movido a: (" + deltaX + ", " + deltaY + ")");
                sleep(100); // Pequeña pausa
            }
        };

        Runnable reader = ()->{
          for(int i=0; i<10;i++){
              double dist = punto.pointFromOrigin();
              System.out.println("<- [Lector " + Thread.currentThread().getId() + "] Distancia calculada: " + dist);
              sleep(50);
          }
        };


        executorService.execute(writer);
        for(int i=0; i<4; i++){
            executorService.execute(reader);
        }

        executorService.shutdown();

        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Prueba completada");
    }




    private static void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
