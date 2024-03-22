package com.example.tumbaburros.designpatterns.structural.facade;

public class EthereumService extends CryptoService {
    @Override
    void buyCurrency(User user, double amount) {
        System.out.println("Buy currenty Ethereum: " +amount);
    }
}
