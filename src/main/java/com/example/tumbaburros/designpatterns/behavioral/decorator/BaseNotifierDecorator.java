package com.example.tumbaburros.designpatterns.behavioral.decorator;

public abstract class BaseNotifierDecorator implements INotifier {

    private final INotifier wrapped;
    protected final DatabaseService databaseService;

    BaseNotifierDecorator(INotifier wrapped) {
        this.wrapped = wrapped;
        this.databaseService = new DatabaseService();
    }

    public void send(String msg){
        wrapped.send(msg);
    }

    public String getUsername(){
        return wrapped.getUsername();
    }
}
