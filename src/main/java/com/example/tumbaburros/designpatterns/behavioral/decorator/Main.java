package com.example.tumbaburros.designpatterns.behavioral.decorator;

public class Main {
    public static void main(String[] args) {
        INotifier notifier = new FacebookDecorator(new WhatsAppDecorator(new Notifier("fer3")));

        notifier.send("Like and sub");
    }
}
