package com.example.administrator.kdsdemo01.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/12.
 */
public class Gym implements Serializable {

    public int id;
    public String address;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }
}
