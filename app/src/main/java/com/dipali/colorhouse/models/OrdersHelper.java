package com.dipali.colorhouse.models;

public class OrdersHelper {

    String title;
    String phone;
    String email;

    public OrdersHelper(String title, String email, String phone) {
        this.title = title;
        this.phone = phone;
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
