package com.example.android.sparklibrary.Responsovi;

/**
 * Created by adissertovic on 16/06/17.
 */

public class LoginResponse {

    private String username;
    private String password;

    public LoginResponse(String username, String password) {
        this.username = username;
        this.password = password;
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
