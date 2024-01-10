package com.example.tumbaburros.designpatterns.behavioral.chainofresponsability;

public class Dollar20Dispenser implements DispenceChain{

    DispenceChain chain;

    @Override
    public void setChain(DispenceChain chain) {
        this.chain = chain;
    }

    @Override
    public void dispense(Currency currency) {
        if(currency != null && currency.getAmount()>=20){
            int toDispense= currency.getAmount()/20;
            int remaining = currency.getAmount()%20;
            System.out.println("Dispensing: " + toDispense + " bills of 20");
            if(remaining>0) chain.dispense(new Currency(remaining));
        } else {
            if(chain != null) chain.dispense(currency);
        }
    }
}
