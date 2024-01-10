package com.example.tumbaburros.designpatterns.behavioral.chainofresponsability;

public class AtmDispenseChain {

    private DispenceChain c1;

    public AtmDispenseChain() {
        c1 = new Dollar50Dispenser();
        DispenceChain c2 = new Dollar20Dispenser();
        DispenceChain c3 = new Dollar10Dispenser();

        c2.setChain(c3);
        c1.setChain(c2);

    }

    public DispenceChain getC1() {
        return c1;
    }
}
