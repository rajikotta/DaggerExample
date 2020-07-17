package com.raji.daggerexample.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Raji on 7/16/20.
 * Golden Scent
 */
public class User {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String emailID;
    @SerializedName("website")
    @Expose
    private String website;


    public User(String id) {
        this.id = id;
    }

    public User(String id, String name, String emailID, String website) {
        this.id = id;
        this.name = name;
        this.emailID = emailID;
        this.website = website;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}