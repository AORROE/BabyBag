package com.hwt.babybag.test;

import android.graphics.Bitmap;

public class ChildInfoItem implements Item{
    private Bitmap childHeader;
    private String childName;
    private int age;
    private String groupClass;
    private String introduce;

    public ChildInfoItem() {
    }

    public ChildInfoItem(Bitmap childHeader, String childName, int age, String groupClass, String introduce) {
        this.childHeader = childHeader;
        this.childName = childName;
        this.age = age;
        this.groupClass = groupClass;
        this.introduce = introduce;
    }

    public Bitmap getChildHeader() {
        return childHeader;
    }

    public void setChildHeader(Bitmap childHeader) {
        this.childHeader = childHeader;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGroupClass() {
        return groupClass;
    }

    public void setGroupClass(String groupClass) {
        this.groupClass = groupClass;
    }

    public String getIntrouce() {
        return introduce;
    }

    public void setIntrouce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * 返回所属的类型
     * @return
     */
    @Override
    public int getItemType() {
        return 0;
    }
}
