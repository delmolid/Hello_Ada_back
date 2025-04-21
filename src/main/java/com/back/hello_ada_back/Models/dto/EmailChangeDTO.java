package com.back.hello_ada_back.Models.dto;

public class EmailChangeDTO {

    private String password;
    private String newEmail;

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewEmail() {
        return this.newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

}
