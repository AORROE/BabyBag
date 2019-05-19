package com.hwt.babybag.adapter;

import android.os.Parcel;
import android.os.Parcelable;

public class MineItem implements Parcelable {

    private Integer foundId;
    private Integer userId;
    private Integer commentId;
    private String userName;
    private String userPhoto;
    private String foundContent;
    private String foundImg;
    private String addTime;

    private int isPraise;
    Boolean isCheck = false;

    public MineItem() {
    }

    public Integer getFoundId() {
        return foundId;
    }

    public void setFoundId(Integer foundId) {
        this.foundId = foundId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getFoundContent() {
        return foundContent;
    }

    public void setFoundContent(String foundContent) {
        this.foundContent = foundContent;
    }

    public String getFoundImg() {
        return foundImg;
    }

    public void setFoundImg(String foundImg) {
        this.foundImg = foundImg;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public int getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(int isPraise) {
        this.isPraise = isPraise;
    }

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }

    @Override
    public String toString() {
        return "MineItem{" +
                "foundId=" + foundId +
                ", userId=" + userId +
                ", commentId=" + commentId +
                ", userName='" + userName + '\'' +
                ", userPhoto='" + userPhoto + '\'' +
                ", foundContent='" + foundContent + '\'' +
                ", foundImg='" + foundImg + '\'' +
                ", addTime='" + addTime + '\'' +
                ", isPraise=" + isPraise +
                ", isCheck=" + isCheck +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.foundId);
        dest.writeValue(this.userId);
        dest.writeValue(this.commentId);
        dest.writeString(this.userName);
        dest.writeString(this.userPhoto);
        dest.writeString(this.foundContent);
        dest.writeString(this.foundImg);
        dest.writeString(this.addTime);
        dest.writeInt(this.isPraise);
        dest.writeValue(this.isCheck);
    }

    protected MineItem(Parcel in) {
        this.foundId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.commentId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userName = in.readString();
        this.userPhoto = in.readString();
        this.foundContent = in.readString();
        this.foundImg = in.readString();
        this.addTime = in.readString();
        this.isPraise = in.readInt();
        this.isCheck = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<MineItem> CREATOR = new Creator<MineItem>() {
        @Override
        public MineItem createFromParcel(Parcel source) {
            return new MineItem(source);
        }

        @Override
        public MineItem[] newArray(int size) {
            return new MineItem[size];
        }
    };
}
