package com.fes.ui_exercise1.model;

import android.graphics.drawable.Drawable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "information")
public class Info {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String ni;
    String passport;
    String password;
    String gender;
    String bdate;
    String country;
    String photo;



    public Info(int id, String name, String ni, String passport, String password, String gender, String bdate, String country, String photo) {
        this.id = id;
        this.name = name;
        this.ni = ni;
        this.passport = passport;
        this.password = password;
        this.gender = gender;
        this.bdate = bdate;
        this.country = country;
        this.photo = photo;
    }

    @Ignore
    public Info(String name, String ni, String passport, String password, String gender, String bdate, String country, String photo) {
        this.name = name;
        this.ni = ni;
        this.passport = passport;
        this.password = password;
        this.gender = gender;
        this.bdate = bdate;
        this.country = country;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNi() {
        return ni;
    }

    public void setNi(String ni) {
        this.ni = ni;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
