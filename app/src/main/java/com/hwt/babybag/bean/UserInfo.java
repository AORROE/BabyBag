package com.hwt.babybag.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserInfo implements Parcelable {

    private Integer userId;
    private String nickName;
    private Integer sex;
    private Integer age;
    private String mobile;
    private String individualitySign;
    private Integer isLive;
    private Integer isParents;
//    private Date addTime;
    private String photo;
    private List<ChildInfoBean> childInfoList;
//
//    @Override
//    public String toString() {
//        return "UserInfo{" +
//                "userId=" + userId +
//                ", nickName='" + nickName + '\'' +
//                ", sex=" + sex +
//                ", age=" + age +
//                ", mobile='" + mobile + '\'' +
//                ", individualitySign='" + individualitySign + '\'' +
//                ", isLive=" + isLive +
//                ", isParents=" + isParents +
//                ", addTime=" + addTime +
//                ", photo='" + photo + '\'' +
//                '}';
//    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIndividualitySign() {
        return individualitySign;
    }

    public void setIndividualitySign(String individualitySign) {
        this.individualitySign = individualitySign;
    }

    public Integer getIsLive() {
        return isLive;
    }

    public void setIsLive(Integer isLive) {
        this.isLive = isLive;
    }

    public Integer getIsParents() {
        return isParents;
    }

    public void setIsParents(Integer isParents) {
        this.isParents = isParents;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<ChildInfoBean> getChildInfoBeanList() {
        return childInfoList;
    }

    public void setChildInfoBeanList(List<ChildInfoBean> childInfoList) {
        this.childInfoList = childInfoList;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", mobile='" + mobile + '\'' +
                ", individualitySign='" + individualitySign + '\'' +
                ", isLive=" + isLive +
                ", isParents=" + isParents +
                ", photo='" + photo + '\'' +
                ", childInfoList=" + childInfoList +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.userId);
        dest.writeString(this.nickName);
        dest.writeValue(this.sex);
        dest.writeValue(this.age);
        dest.writeString(this.mobile);
        dest.writeString(this.individualitySign);
        dest.writeValue(this.isLive);
        dest.writeValue(this.isParents);
        dest.writeString(this.photo);
        dest.writeList(this.childInfoList);
    }

    public UserInfo() {
    }

    protected UserInfo(Parcel in) {
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nickName = in.readString();
        this.sex = (Integer) in.readValue(Integer.class.getClassLoader());
        this.age = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mobile = in.readString();
        this.individualitySign = in.readString();
        this.isLive = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isParents = (Integer) in.readValue(Integer.class.getClassLoader());
        this.photo = in.readString();
        this.childInfoList = new ArrayList<ChildInfoBean>();
        in.readList(this.childInfoList, ChildInfoBean.class.getClassLoader());
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}
