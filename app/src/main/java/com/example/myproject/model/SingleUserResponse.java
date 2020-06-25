package com.example.myproject.model;

public class SingleUserResponse {
    private User data;
    private UserDetail ad;

    public SingleUserResponse(User data, UserDetail ad) {
        this.data = data;
        this.ad = ad;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public UserDetail getAd() {
        return ad;
    }

    public void setAd(UserDetail ad) {
        this.ad = ad;
    }
}
