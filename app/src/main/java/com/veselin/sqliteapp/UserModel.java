package com.veselin.sqliteapp;

public class UserModel {
    private String username;
    private String email;
    private String pass;
    private String gender;
    private int id;
    public UserModel(String username, String email, String pass, String gender, int id) {
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.gender = gender;
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
