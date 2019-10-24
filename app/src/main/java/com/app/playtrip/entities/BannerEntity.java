package com.app.playtrip.entities;



import android.os.Parcel;
import android.os.Parcelable;

public class BannerEntity {

    private int TabPosterPath;
    private int VideoEntityId;
    private String imagDisc;


    public int getTabPosterPath() {
        return TabPosterPath;
    }

    public void setTabPosterPath(int tabPosterPath) {
        TabPosterPath = tabPosterPath;
    }

    public int getVideoEntityId() {
        return VideoEntityId;
    }

    public void setVideoEntityId(int videoEntityId) {
        VideoEntityId = videoEntityId;
    }

    public String getImagDisc() {
        return imagDisc;
    }

    public void setImagDisc(String imagDisc) {
        this.imagDisc = imagDisc;
    }
}

