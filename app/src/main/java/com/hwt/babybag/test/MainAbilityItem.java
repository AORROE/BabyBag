package com.hwt.babybag.test;

public class MainAbilityItem implements Item{
    private int icon;
    private String iconTitle;

    public MainAbilityItem(int icon, String iconTitle) {
        this.icon = icon;
        this.iconTitle = iconTitle;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getIconTitle() {
        return iconTitle;
    }

    public void setIconTitle(String iconTitle) {
        this.iconTitle = iconTitle;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
