package com.example.tumbaburros.designpatterns.structural.facade;

public class Main {
    public static void main(String[] args) {
        BuyCryptoFacade buyCrypto = new BuyCryptoFacade();
        buyCrypto.buyCryptocurrency(1000, "BTC");
    }
}
