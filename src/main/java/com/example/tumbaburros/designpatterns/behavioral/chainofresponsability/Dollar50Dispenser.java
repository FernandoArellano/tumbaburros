package com.example.tumbaburros.designpatterns.behavioral.chainofresponsability;

public class Dollar50Dispenser implements DispenceChain{

    DispenceChain chain;

    @Override
    public void setChain(DispenceChain chain) {
        this.chain = chain;
    }

    @Override
    public void dispense(Currency currency) {
        if(currency != null && currency.getAmount()>=50){
            int toDispense = currency.getAmount()/50;
            int remaining = currency.getAmount()%50;
            System.out.println("Dispensing:" + toDispense + " bills of 50");
            if(remaining!=0 && chain != null) this.chain.dispense(new Currency(remaining));
        } else{
            if(chain!=null) chain.dispense(currency);
        }
    }
}
