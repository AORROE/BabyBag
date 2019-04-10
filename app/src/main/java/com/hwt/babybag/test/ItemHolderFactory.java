package com.hwt.babybag.test;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hwt.babybag.R;

public class ItemHolderFactory {
    private int itemType;
    private ViewGroup viewGroup;

    public ItemHolderFactory(int itemType, ViewGroup viewGroup) {
        itemType = itemType;
        this.viewGroup = viewGroup;
    }

    public static ItemHolder getItemHolder(int itemType,ViewGroup viewGroup){
        switch (itemType){
            default:
            case 0:
                return new ChildInfoHolder(
                        LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.baby_card,viewGroup,false));
            case 1:
                return new MainabilityHolder(
                        LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.baby_item_title,viewGroup,false));
            case 2:
                return new RecommendVideoHolder(
                        LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.baby_recommend_list_item,viewGroup,false));
        }
    }
}
