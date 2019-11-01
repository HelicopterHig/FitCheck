package com.fitcheck.model;

public class User_in {
   private String name;
   private String numbr;
    private String pass;
    private String email;
    private String newPass;
    private String token;

    public User_in(String email, String pass) {
        this.pass = pass;
        this.email = email;
    }

    public User_in(String name, String pass, String email, String numbr) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.numbr = numbr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumbr() {
        return numbr;
    }

    public void setNumbr(String numbr) {
        this.numbr = numbr;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
