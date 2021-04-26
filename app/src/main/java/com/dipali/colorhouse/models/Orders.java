package com.dipali.colorhouse.models;

public class Orders {

    private int id;
    private String user;
    private String shop;

    public Orders() {
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getShop() {
        return shop;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }
}
