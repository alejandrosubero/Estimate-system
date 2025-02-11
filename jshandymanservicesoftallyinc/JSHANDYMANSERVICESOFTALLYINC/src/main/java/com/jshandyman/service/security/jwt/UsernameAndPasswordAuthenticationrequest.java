package com.jshandyman.service.security.jwt;

public class UsernameAndPasswordAuthenticationrequest {

    private  String username;
    private  String password;

    public UsernameAndPasswordAuthenticationrequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
