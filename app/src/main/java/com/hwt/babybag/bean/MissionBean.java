package com.hwt.babybag.bean;


public class MissionBean {
    private Integer id;
    private Integer userId;
    private String userName;
    private String userHeader;
    private String content;
    private Integer isComplete;
    private Integer isParise;
    private String addtime;

   private Boolean ischeck = false;

    public MissionBean(Integer userId, String userName, String userHeader, String content, Integer isComplete, Integer isParise) {
        this.userId = userId;
        this.userName = userName;
        this.userHeader = userHeader;
        this.content = content;
        this.isComplete = isComplete;
        this.isParise = isParise;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    public Integer getIsParise() {
        return isParise;
    }

    public void setIsParise(Integer isParise) {
        this.isParise = isParise;
    }

    public String getAddTime() {
        return addtime;
    }

    public void setAddTime(String addtime) {
        this.addtime = addtime;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public Boolean getIscheck() {
        return ischeck;
    }

    public void setIscheck(Boolean ischeck) {
        this.ischeck = ischeck;
    }

    @Override
    public String toString() {
        return "MissionBean{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userHeader='" + userHeader + '\'' +
                ", content='" + content + '\'' +
                ", isComplete=" + isComplete +
                ", isParise=" + isParise +
                "addTime=" + addtime +
                '}';
    }
}
