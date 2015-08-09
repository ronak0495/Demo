package com.example.ronak.demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;


public class UserLocalStore {

    public static final String NAME = "userDetails";

    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(NAME, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putString("name", user.name);
        userLocalDatabaseEditor.putString("phone", user.phone);
        userLocalDatabaseEditor.putString("email", user.email);
        userLocalDatabaseEditor.putString("home_address_line_1", user.home_array.get(0));
        userLocalDatabaseEditor.putString("home_address_line_2", user.home_array.get(1));
        userLocalDatabaseEditor.putString("home_address_locality", user.home_array.get(2));
        userLocalDatabaseEditor.putString("home_address_state", user.home_array.get(3));
        userLocalDatabaseEditor.putString("home_address_city", user.home_array.get(4));
        userLocalDatabaseEditor.putString("home_address_pincode", user.home_array.get(5));
        userLocalDatabaseEditor.putString("work_address_line_1",user.work_array.get(0));
        userLocalDatabaseEditor.putString("work_address_line_2",user.work_array.get(1));
        userLocalDatabaseEditor.putString("work_address_locality",user.work_array.get(2));
        userLocalDatabaseEditor.putString("work_address_state",user.work_array.get(3));
        userLocalDatabaseEditor.putString("work_address_city",user.work_array.get(4));
        userLocalDatabaseEditor.putString("work_address_pincode",user.work_array.get(5));
        userLocalDatabaseEditor.putString("delivery_address", user.delivery_address);
        userLocalDatabaseEditor.commit();
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        userLocalDatabaseEditor.commit();
    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.commit();
    }

    public User getRegisteredUser() {
        String name = userLocalDatabase.getString("name","");
        String phone = userLocalDatabase.getString("phone", "");
        String email = userLocalDatabase.getString("email", "");
        List home_array = new ArrayList();
        List work_array = new ArrayList();

        home_array.add(0,userLocalDatabase.getString("home_address_line_1",""));
        home_array.add(1,userLocalDatabase.getString("home_address_line_2",""));
        home_array.add(2,userLocalDatabase.getString("home_address_locality",""));
        home_array.add(3,userLocalDatabase.getString("home_address_state",""));
        home_array.add(4,userLocalDatabase.getString("home_address_city",""));
        home_array.add(5,userLocalDatabase.getString("home_address_pincode",""));
        work_array.add(0,userLocalDatabase.getString("work_address_line_1",""));
        work_array.add(1,userLocalDatabase.getString("work_address_line_2",""));
        work_array.add(2,userLocalDatabase.getString("work_address_locality",""));
        work_array.add(3,userLocalDatabase.getString("work_address_state",""));
        work_array.add(4,userLocalDatabase.getString("work_address_city",""));
        work_array.add(5,userLocalDatabase.getString("work_address_pincode",""));

        String del_add=userLocalDatabase.getString("delivery_address","");
        User u= new User(name,phone,email,home_array,work_array,del_add);
        return u;
    }
}
