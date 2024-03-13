package com.example.tumbaburros.designpatterns.behavioral.decorator;

public class Notifier implements INotifier {
    private final String username;
    private final DatabaseService databaseService;

    public Notifier(String username){
        this.username = username;
        databaseService = new DatabaseService();
    }

    public void send(String msg){
        String mail = databaseService.getMailFromUsername(username);
        System.out.println("Sending " + msg + " by Mail to " + mail);
    }

    public String getUsername(){
        return username;
    }
}
