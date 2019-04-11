package com.hwt.babybag.adapter;

import android.graphics.Bitmap;

import com.hwt.babybag.test.Item;

public class MissionItem {
    private Bitmap userHeader;
    private String userName;
    private String addtime;
    private String missionContent;
    private int isComplete;
    private int isPraise;

    public MissionItem() {
    }

    public MissionItem(Bitmap userHeader, String userName, String addtime, String missionContent, int isComplete, int isPraise) {
        this.userHeader = userHeader;
        this.userName = userName;
        this.addtime = addtime;
        this.missionContent = missionContent;
        this.isComplete = isComplete;
        this.isPraise = isPraise;
    }

    public Bitmap getUserHeader() {
        return userHeader;
    }

    public void setUserHeader(Bitmap userHeader) {
        this.userHeader = userHeader;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getMissionContent() {
        return missionContent;
    }

    public void setMissionContent(String missionContent) {
        this.missionContent = missionContent;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }

    public int getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(int isPraise) {
        this.isPraise = isPraise;
    }
}
