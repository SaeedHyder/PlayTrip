package com.app.playtrip.entities.video;

import java.util.ArrayList;

public class VideoInnerData {
    private long id;
    private int user_id;
    private int location_id;
    private String video_url;
    private String thumbnail_image;
    private String video_length;
    private int status;
    private String locale;
    private String title;
    private String caption;
    private String  video_like_count;
    private String  video_view_count;
    private String  video_share_count ;
    private String   video_comment_count;
    private String video_url_path;
    private String thumbnail_image_url;
    private ArrayList<VideoTranslations> translations ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getThumbnail_image() {
        return thumbnail_image;
    }

    public void setThumbnail_image(String thumbnail_image) {
        this.thumbnail_image = thumbnail_image;
    }

    public String getVideo_length() {
        return video_length;
    }

    public void setVideo_length(String video_length) {
        this.video_length = video_length;
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getVideo_like_count() {
        return video_like_count;
    }

    public void setVideo_like_count(String video_like_count) {
        this.video_like_count = video_like_count;
    }

    public String getVideo_view_count() {
        return video_view_count;
    }

    public void setVideo_view_count(String video_view_count) {
        this.video_view_count = video_view_count;
    }

    public String getVideo_share_count() {
        return video_share_count;
    }

    public void setVideo_share_count(String video_share_count) {
        this.video_share_count = video_share_count;
    }

    public String getVideo_comment_count() {
        return video_comment_count;
    }

    public void setVideo_comment_count(String video_comment_count) {
        this.video_comment_count = video_comment_count;
    }

    public String getVideo_url_path() {
        return video_url_path;
    }

    public void setVideo_url_path(String video_url_path) {
        this.video_url_path = video_url_path;
    }

    public String getThumbnail_image_url() {
        return thumbnail_image_url;
    }

    public void setThumbnail_image_url(String thumbnail_image_url) {
        this.thumbnail_image_url = thumbnail_image_url;
    }

    public ArrayList<VideoTranslations> getTranslations() {
        return translations;
    }

    public void setTranslations(ArrayList<VideoTranslations> translations) {
        this.translations = translations;
    }

    @Override
    public String toString() {
        return "VideoInnerData{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", location_id=" + location_id +
                ", video_url='" + video_url + '\'' +
                ", thumbnail_image='" + thumbnail_image + '\'' +
                ", video_length='" + video_length + '\'' +
                ", status=" + status +
                ", locale='" + locale + '\'' +
                ", title='" + title + '\'' +
                ", caption='" + caption + '\'' +
                ", video_like_count='" + video_like_count + '\'' +
                ", video_view_count='" + video_view_count + '\'' +
                ", video_share_count='" + video_share_count + '\'' +
                ", video_comment_count='" + video_comment_count + '\'' +
                ", video_url_path='" + video_url_path + '\'' +
                ", thumbnail_image_url='" + thumbnail_image_url + '\'' +
                ", translations=" + translations +
                '}';
    }
}
