package com.dipali.colorhouse.models;

public class NearByHelper {

    String title;
    String des;
    String phone;
    String email, useremail;

    public NearByHelper(String title, String des,String phone,String email,String useremail) {
        this.title = title;
        this.des = des;
        this.phone = phone;
        this.email = email;
        this.useremail = useremail;
    }

    public String getTitle() {
        return title;
    }

    public String getDes() {
        return des;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
}
