package com.example.tumbaburros.designpatterns.behavioral.decorator;

public class UserInfo {
    private String email;
    private String username;
    private String phone;
    private String facebook;

    public UserInfo(String username, String email, String phone, String facebook) {
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.facebook = facebook;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getFacebook() {
        return facebook;
    }
}
