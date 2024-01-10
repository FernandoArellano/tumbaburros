package com.example.tumbaburros.designpatterns.behavioral.chainofresponsability;

public class Dollar10Dispenser implements DispenceChain{

    DispenceChain chain;

    @Override
    public void setChain(DispenceChain chain) {
        this.chain = chain;
    }

    @Override
    public void dispense(Currency currency) {
        if(currency!=null && currency.getAmount()>=10){
            int toDispense = currency.getAmount()/10;
            int remaining = currency.getAmount()%10;
            System.out.println("Dispensing: " + toDispense + " bills of 10");
            if(remaining>0 && chain != null) chain.dispense(new Currency(remaining));
        } else{
            if(chain != null) chain.dispense(currency);
        }
    }
}
