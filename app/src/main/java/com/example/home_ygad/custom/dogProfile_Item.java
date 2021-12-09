package com.example.home_ygad.custom;

public class dogProfile_Item {



    String D_name,ID,D_sav,D_sex,D_kg,Dog_id;



    //강아지 무게, 이름, 유저아이디, 맹견여부, 성별

    int Place_id;


    public dogProfile_Item() {
    }

    public dogProfile_Item(String Dog_id,String D_name,  String D_sex,String D_sav,String D_kg) {
        this.D_name = D_name;
        this.D_sex = D_sex;
        this.D_sav = D_sav;
        this.D_kg = D_kg;
        this.Dog_id = Dog_id;

    }


    public String getDog_id() {
        return Dog_id;
    }

    public void setDog_id(String D_name) { this.Dog_id = Dog_id; }


    public String getD_name() {
        return D_name;
    }

    public void setD_name(String D_name) { this.D_name = D_name; }


    public String getD_sav() {
        return D_sav;
    }


    public void setD_sav(String P_image) {
        this.D_sav = D_sav;
    }


    public String getD_sex() {
        return D_sex;
    }

    public void setD_sex(int Dog_id) {
        this.D_sex = D_sex;
    }


    public String getD_kg() {
        return D_kg;
    }
//
//    public void setD_kg(float D_kg) {
//        this.D_kg = D_kg;
//    }



}
