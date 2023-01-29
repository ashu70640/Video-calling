package com.example.vediocalling.models;

public class User {
    private String uId, name, profile, city;
    private long coins;

    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }

    public User() {

    }

    public User(String uId, String name, String profile, String city,long coins) {
        this.uId = uId;
        this.name = name;
        this.profile = profile;
        this.city = city;
        this.coins=coins;
    }

    public String getuId() {
        return uId;
    }

    public String getName() {
        return name;
    }

    public String getProfile() {
        return profile;
    }

    public String getCity() {
        return city;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
