package com.example.tumbaburros.cracking.callcenter;

public abstract class Employee {
    private Call currentCall = null;
    protected Rank rank;
    private CallHandler callHandler;

    public Employee(CallHandler callHandler){
        this.callHandler = callHandler;
    }

    public boolean isFree() {
        return currentCall == null;
    }

    public void assignNewCall(Call call) {
        currentCall = call;
        System.out.println("Thanks for calling");
    }

    public void callCompleted(){
        currentCall = null;
        System.out.println("Have a Nice Day");
    }


    public void escalateAndReassign() throws InterruptedException {
        currentCall.incrementRank();
        Call temp = currentCall;
        currentCall = null;
        System.out.println("Will be redirected to my supervisor");
        callHandler.dispatchCall(temp);

    }


}
