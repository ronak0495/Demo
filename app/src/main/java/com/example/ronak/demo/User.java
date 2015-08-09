package com.example.ronak.demo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ronak on 7/23/2015.
 */

public class User implements Serializable {

    String name,phone,email,delivery_address;
    List<String> home_array= new ArrayList<>();
    List<String> work_array= new ArrayList<>();

    public User(String name, String phone,String email,List<String> home_array,List<String> work_array,String delivery_address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.home_array = home_array;
        this.work_array=work_array;
        this.delivery_address=delivery_address;
    }

}
