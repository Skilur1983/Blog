package com.mycompany.blog.pojos;

public class UserRegistrtion {

    private String email;
    private String name;
    private String password;
    private String passwordConfirmation;

    public UserRegistrtion() {
    }

    public UserRegistrtion(String email, String name, String password, String passwordConfirmation) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
