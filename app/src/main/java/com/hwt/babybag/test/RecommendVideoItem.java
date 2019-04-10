package com.hwt.babybag.test;

import android.graphics.Bitmap;

public class RecommendVideoItem implements Item{
    private Bitmap videoCover;
    private String videoTitle;
    private String videoviderCover;

    public RecommendVideoItem(Bitmap videoCover, String videoTitle, String videoviderCover) {
        this.videoCover = videoCover;
        this.videoTitle = videoTitle;
        this.videoviderCover = videoviderCover;
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

    public String getVideoIntrouce() {
        return videoviderCover;
    }

    public void setVideoIntrouce(String videoIntrouce) {
        this.videoviderCover = videoIntrouce;
    }

    @Override
    public int getItemType() {
        return 2;
    }
}
