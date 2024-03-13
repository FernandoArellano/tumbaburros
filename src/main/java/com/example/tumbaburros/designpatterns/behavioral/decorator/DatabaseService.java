package com.example.tumbaburros.designpatterns.behavioral.decorator;

import java.util.HashMap;
import java.util.Map;

public class DatabaseService {

    private Map<String, UserInfo> users = new HashMap();

    public DatabaseService() {
        loadUsers();
    }

    public void loadUsers(){
        users.put("fer",new UserInfo("fer","ferhotmail","3333333333", "ferface"));
        users.put("fer2", new UserInfo("fer2","ferhotmail2","222222222", "ferface2"));
        users.put("fer3", new UserInfo("fer3","ferhotmail3","444444444", "ferface3"));
    }

    public String getMailFromUsername(String username) {
        UserInfo userInfo = users.getOrDefault(username,null);
        return userInfo != null ? userInfo.getEmail():"unknown";
    }

    public String getFBNameFromUsername(String username) {
        UserInfo userInfo = users.getOrDefault(username,null);
        return userInfo != null ? userInfo.getFacebook():"unknown";
    }

    public String getPhoneNbrFromUsername(String username) {
        UserInfo userInfo = users.getOrDefault(username,null);
        return userInfo != null ? userInfo.getPhone():"unknown";
    }
}
