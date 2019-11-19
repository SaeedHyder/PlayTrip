package com.app.playtrip.entities;

import com.app.playtrip.entities.User.Details;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FollowingEnt {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("details")
    @Expose
    private Details details;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}
