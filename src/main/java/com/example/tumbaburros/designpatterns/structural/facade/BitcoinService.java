package com.example.tumbaburros.designpatterns.structural.facade;

public class BitcoinService extends CryptoService {

    @Override
    void buyCurrency(User user, double amount) {
        System.out.println("Buy currency Bitcoint: " + amount);
    }
}
