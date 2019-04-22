package com.hwt.babybag.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ChildInfoBean implements Parcelable {
    private Integer childId;
    private Integer classId;
    private String childName;
    private Integer sex;
    private Integer age;
    private String characterInstructe;
    private String personLabel;
    private String photo;
    private Integer parentsId;

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
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

    public String getCharacterInstructe() {
        return characterInstructe;
    }

    public void setCharacterInstructe(String characterInstructe) {
        this.characterInstructe = characterInstructe;
    }

    public String getPersonLabel() {
        return personLabel;
    }

    public void setPersonLabel(String personLabel) {
        this.personLabel = personLabel;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getParentsId() {
        return parentsId;
    }

    public void setParentsId(Integer parentsId) {
        this.parentsId = parentsId;
    }

    @Override
    public String toString() {
        return "ChildInfoBean{" +
                "childId=" + childId +
                ", classId=" + classId +
                ", childName='" + childName + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", characterInstructe='" + characterInstructe + '\'' +
                ", personLabel='" + personLabel + '\'' +
                ", photo='" + photo + '\'' +
                ", parentsId=" + parentsId +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.childId);
        dest.writeValue(this.classId);
        dest.writeString(this.childName);
        dest.writeValue(this.sex);
        dest.writeValue(this.age);
        dest.writeString(this.characterInstructe);
        dest.writeString(this.personLabel);
        dest.writeString(this.photo);
        dest.writeValue(this.parentsId);
    }

    public ChildInfoBean() {
    }

    protected ChildInfoBean(Parcel in) {
        this.childId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.classId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.childName = in.readString();
        this.sex = (Integer) in.readValue(Integer.class.getClassLoader());
        this.age = (Integer) in.readValue(Integer.class.getClassLoader());
        this.characterInstructe = in.readString();
        this.personLabel = in.readString();
        this.photo = in.readString();
        this.parentsId = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<ChildInfoBean> CREATOR = new Parcelable.Creator<ChildInfoBean>() {
        @Override
        public ChildInfoBean createFromParcel(Parcel source) {
            return new ChildInfoBean(source);
        }

        @Override
        public ChildInfoBean[] newArray(int size) {
            return new ChildInfoBean[size];
        }
    };
}
