package com.hwt.babybag.bean;

public class FoundBean {

    private Integer foundId;
    private Integer userId;
    private Integer commentId;
    private String userName;
    private String userPhoto;
    private String foundContent;
    private String foundImg;
    private String addTime;

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

    @Override
    public String toString() {
        return "FoundBean{" +
                "foundId=" + foundId +
                ", userId=" + userId +
                ", commentId=" + commentId +
                ", userName='" + userName + '\'' +
                ", userPhoto='" + userPhoto + '\'' +
                ", foundContent='" + foundContent + '\'' +
                ", foundImg='" + foundImg + '\'' +
                ", addTime='" + addTime + '\'' +
                '}';
    }
}
