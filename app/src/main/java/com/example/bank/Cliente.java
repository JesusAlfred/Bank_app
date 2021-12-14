package com.example.bank;

public class Cliente {
    private int id;
    private String user, password, name, lastname, cellphone;
    float balanceDeb1, balanceDeb2, balanceCre1 ;

    public Cliente(){

    }
    public Cliente(int id, String user, String password, String name, String lastname, String cellphone, float balanceDeb1, float balanceDeb2, float balanceCre1) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.cellphone = cellphone;
        this.balanceDeb1 = balanceDeb1;
        this.balanceDeb2 = balanceDeb2;
        this.balanceCre1 = balanceCre1;
    }
    public Cliente(String user, String password, String name, String lastname, String cellphone, float balanceDeb1, float balanceDeb2, float balanceCre1) {
        this.user = user;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.cellphone = cellphone;
        this.balanceDeb1 = balanceDeb1;
        this.balanceDeb2 = balanceDeb2;
        this.balanceCre1 = balanceCre1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public float getBalanceDeb1() {
        return balanceDeb1;
    }

    public void setBalanceDeb1(float balanceDeb1) {
        this.balanceDeb1 = balanceDeb1;
    }

    public float getBalanceDeb2() {
        return balanceDeb2;
    }

    public void setBalanceDeb2(float balanceDeb2) {
        this.balanceDeb2 = balanceDeb2;
    }

    public float getBalanceCre1() {
        return balanceCre1;
    }

    public void setBalanceCre1(float balanceCre1) {
        this.balanceCre1 = balanceCre1;
    }
}
