package com.example.home_ygad;

public class Item {

    int Place_id;
    String P_name;
    String P_address;
    String P_image;

    public Item() {
    }

    public Item(int Place_id, String P_name, String P_address, String P_image) {
        this.Place_id = Place_id;
        this.P_name = P_name;
        this.P_address = P_address;
        this.P_image = P_image;
    }

    public int getPlace_id() {
        return Place_id;
    }
//
//    public void setPlace_id(int Place_id) {
//        this.Place_id = Place_id;
//    }

    public String getP_name() {
        return P_name;
    }

    public void setP_name(String P_name) {
        this.P_name = P_name;
    }

    public String getP_address() {
        return P_address;
    }

    public void setP_address(String P_address) {
        this.P_address = P_address;
    }

    public String getP_image() {
        return P_image;
    }

    public void setP_image(String P_image) {
        this.P_image = P_image;
    }

}
