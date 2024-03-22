package com.example.tumbaburros.designpatterns.structural.facade;

public class MailService {
    public void sendConfirmationMail(User user) {
        System.out.println("Send Confirmation to " + user.username());
    }
}
