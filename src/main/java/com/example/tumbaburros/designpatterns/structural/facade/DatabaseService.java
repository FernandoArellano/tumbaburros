package com.example.tumbaburros.designpatterns.structural.facade;

import java.util.HashMap;
import java.util.Map;

public class DatabaseService {

    private Map<String, User> users = new HashMap();

    public DatabaseService() {
        loadUsers();
    }

    public void loadUsers(){
        users.put("fer",new User("fer", 10000));
        users.put("fer2",new User("fer2", 100));
        users.put("fer3",new User("fer3", 1000));
    }

    public User getUser(String username){
        return users.get(username);
    }

}
