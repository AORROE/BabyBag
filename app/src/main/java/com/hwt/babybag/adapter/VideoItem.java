package com.hwt.babybag.adapter;

import android.graphics.Bitmap;

import com.hwt.babybag.test.Item;

public class VideoItem{
    private Bitmap videoCover;
    private String videoTitle;
    private String videoInstroduce;

    public VideoItem(Bitmap videoCover, String videoTitle, String videoInstroduce) {
        this.videoCover = videoCover;
        this.videoTitle = videoTitle;
        this.videoInstroduce = videoInstroduce;
    }

    public Bitmap getVideoCover() {
        return videoCover;
    }

    public void setVideoCover(Bitmap videoCover) {
        this.videoCover = videoCover;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoInstroduce() {
        return videoInstroduce;
    }

    public void setVideoInstroduce(String videoInstroduce) {
        this.videoInstroduce = videoInstroduce;
    }

}
