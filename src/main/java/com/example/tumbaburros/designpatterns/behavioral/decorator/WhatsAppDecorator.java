package com.example.tumbaburros.designpatterns.behavioral.decorator;

public class WhatsAppDecorator extends BaseNotifierDecorator{

    public WhatsAppDecorator(INotifier wrapped) {
        super(wrapped);
    }

    public void send(String msg){
        super.send(msg);
        String phoneNbr = databaseService.getPhoneNbrFromUsername(getUsername());
        System.out.println("Sending whatsapp to number " + phoneNbr);
    }
}
