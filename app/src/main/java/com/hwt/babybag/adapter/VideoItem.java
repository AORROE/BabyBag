package com.hwt.babybag.adapter;

import android.graphics.Bitmap;


public class VideoItem{

    private Integer videoId;
    private Integer userId;
    private String userName;
    private String userHeader;
    private String coverUrl;
    private String videoUrl;
    private String videoTitle;

    private Boolean isChoose = false;

    @Override
    public String toString() {
        return "VideoItem{" +
                "videoId=" + videoId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userHeader='" + userHeader + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", isChoose=" + isChoose +
                '}';
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHeader() {
        return userHeader;
    }

    public void setUserHeader(String userHeader) {
        this.userHeader = userHeader;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Boolean getChoose() {
        return isChoose;
    }

    public void setChoose(Boolean choose) {
        isChoose = choose;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }
}
