package com.example.tumbaburros.refresher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BankReentrant {

    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    public BankReentrant(double balance){
        this.balance= balance;
    }

    public void deposit(double amount){
        lock.lock();

        try{
            System.out.println("depositando: " +amount + " from Thread:" + Thread.currentThread().getName());
            balance+=amount;
            balanceActual();
        } finally {
            System.out.println("Deposit unlock");
            lock.unlock();
        }
    }

    private void balanceActual(){
        System.out.println("Balance actual: " +this.balance);
    }

    public void withdraw(double amount){
        try{
            if(lock.tryLock(2, TimeUnit.SECONDS)){
                try{
                    System.out.println("Amount to withdraw:" + amount);
                    if(balance>= amount){
                        System.out.println("retirando: " + amount + " thread:" + Thread.currentThread().getName());
                        balance-=amount;
                        balanceActual();
                    } else {
                        System.out.println("Saldo insuficiente");
                        balanceActual();
                    }
                } finally {
                    System.out.println("Withdraw unlock");
                    lock.unlock();
                }

            } else {
                System.out.println("Fallo retiro:" + Thread.currentThread().getName());
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        BankReentrant b1 = new BankReentrant(1000.0);

        try {
        ExecutorService executor = Executors.newFixedThreadPool(3);



        executor.execute(()->{

            for(int i=0; i<5; i++){
                if(ThreadLocalRandom.current().nextBoolean()){
                    b1.deposit(ThreadLocalRandom.current().nextDouble(10,200));
                } else{
                    b1.withdraw(ThreadLocalRandom.current().nextDouble(100,300));
                }
            }
        });

        executor.shutdown();


            if(executor.awaitTermination(5, TimeUnit.SECONDS)){
                System.out.println("Simulation complete");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
