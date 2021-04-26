package com.dipali.colorhouse.models;

public class Stores {
    private int id;
    private String shop_name;
    private String email;
    private String phone;
    private String shop_no;
    private String street;
    private String landmark;
    private String state;
    private String city;
    private String area;
    private String timings;
    private String painters;
    private String password;

    public Stores() {
    }

    public Stores(String owner_name, String email, String phone) {
        this.shop_name = owner_name;
        this.email = email;
        this.phone = phone;
    }

    // Getter
    public int getId() {
        return id;
    }

    public String getOwner_name() {
        return shop_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getShop_no() {
        return shop_no;
    }

    public String getStreet() {
        return street;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getPassword() {
        return password;
    }

    public String getTimings() {
        return timings;
    }

    public String getPainters() {
        return painters;
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setOwner_name(String owner_name) {
        this.shop_name = owner_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setShop_no(String shop_no) {
        this.shop_no = shop_no;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public void setPainters(String painters) {
        this.painters = painters;
    }
}
