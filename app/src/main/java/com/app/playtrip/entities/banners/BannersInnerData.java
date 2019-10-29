package com.app.playtrip.entities.banners;

import com.app.playtrip.entities.video.VideoTranslations;

import java.util.ArrayList;

public class BannersInnerData {
    private long id;
    private int type;
    private String image;
    private int status;
    private String locale;
    private String title;
    private String description;
    private String image_url;
    private String type_text;
    private ArrayList<BannersTranslations> translations = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getType_text() {
        return type_text;
    }

    public void setType_text(String type_text) {
        this.type_text = type_text;
    }

    public ArrayList<BannersTranslations> getTranslations() {
        return translations;
    }

    public void setTranslations(ArrayList<BannersTranslations> translations) {
        this.translations = translations;
    }

    @Override
    public String toString() {
        return "BannersInnerData{" +
                "id=" + id +
                ", type=" + type +
                ", image='" + image + '\'' +
                ", status=" + status +
                ", locale='" + locale + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image_url='" + image_url + '\'' +
                ", type_text='" + type_text + '\'' +
                ", translations=" + translations +
                '}';
    }
}
