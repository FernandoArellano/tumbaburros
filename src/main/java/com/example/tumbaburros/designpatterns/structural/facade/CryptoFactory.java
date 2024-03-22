package com.example.tumbaburros.designpatterns.structural.facade;

public class CryptoFactory {
    public static CryptoService getCryptoService(String currency) {
        if(currency.equals("BTC")){
            return new BitcoinService();
        } else if(currency.equals("ETH")){
            return new EthereumService();
        }
        return null;
    }
}
