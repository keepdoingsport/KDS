package com.example.administrator.kdsdemo01.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/12.
 */
public class Gym implements Serializable {

    public int id;
    public String address;
    public String photos;
    public String photos1;
    public String photos2;
    public String info;
    public String phone;



    public void setId(int id) {
        this.id = id;
    }

    public void setPhotos(String photos){
        this.photos=photos;
    }

    public void setPhoto1(String photo1){
        this.photos1=photo1;
    }

    public void setPhoto2(String photo2){
        this.photos2=photo2;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getPhotos() {
        return photos;
    }

    public String getPhoto1() {
        return photos1;
    }

    public String getPhoto2() {
        return photos2;
    }

    public String getInfo() {
        return info;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }


}
