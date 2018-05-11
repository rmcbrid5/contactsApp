package com.example.rianamcbride.contacts;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.Random;
import java.util.regex.Pattern;

public class Contact {
    private String fName, lName, address, phone, email;
    private int id;
    private Bitmap image;
    public Contact(){

    }
    public Contact(Bitmap image, String fName, String lName, String address, String phone, String email){
        Random rand = new Random();
        int n = rand.nextInt(10000000) + 1;
        this.id = n;
        this.image = image;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
    public String getfName(){
        return fName;
    }
    public String getEmail(){
        return email;
    }
    public void setValues(Bitmap image, String fName, String lName, String address, String phone, String email){
        this.image = image;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
    public String getlName(){
        return lName;
    }
    public String getAddress(){
        return address;
    }
    public String getPhone(){
        return phone;
    }
    public Bitmap getImage(){
        return image;
    }
    public int getId(){
        return id;
    }
    public void setlName(String lName){
        this.lName = lName;
    }
    public boolean isValidContact(){
        return lName != null || fName != null || phone != null;
    }
    public boolean isValidPhone(){
        return !Pattern.matches("[a-zA-Z]+", phone) && phone.length() > 9;
    }

}
