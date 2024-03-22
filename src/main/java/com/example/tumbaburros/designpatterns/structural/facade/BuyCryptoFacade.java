package com.example.tumbaburros.designpatterns.structural.facade;



public class BuyCryptoFacade {

    public void buyCryptocurrency(double amount, String currency){
        DatabaseService dbService = new DatabaseService();
        User user = dbService.getUser("fer");
        if(user.balance()<amount){
            System.out.println("Insufficient Balance");
            return;
        }

        CryptoFactory.getCryptoService(currency).buyCurrency(user, amount);
        MailService mailService = new MailService();
        mailService.sendConfirmationMail(user);
    }
}
