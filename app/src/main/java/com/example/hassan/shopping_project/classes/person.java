package com.example.hassan.shopping_project.classes;

public class person {

    private String Username , Pass , Email ;
    private int id , Age ;

    public person() {

    }

    public person(String username , String pass , String email , int age)
    {
        this.Username = username;
        this.Pass = pass;
        this.Email = email;
        this.Age = age;
    }

    public person(String username , String pass , String email , int age , int id)
    {
        this.Username = username;
        this.Pass = pass;
        this.Email = email;
        this.Age = age;
        this.id = id;
    }

    public void setAge(int age) {
        Age = age;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUsername()
    {
        return Username;
    }
    public String getPass()
    {
        return Pass;
    }
    public String getEmail()
    {
        return Email;
    }

    public int getId() {
        return id;
    }

    public int getAge()
    {
        return Age;
    }
}


