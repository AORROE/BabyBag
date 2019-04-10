package com.hwt.babybag.test;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwt.babybag.R;

public class ChildInfoHolder extends ItemHolder{
    private ImageView childHeader;
    private TextView childName,age,groupClass,introduce;


    public ChildInfoHolder(@NonNull View itemView) {
        super(itemView);
        childHeader = itemView.findViewById(R.id.child_header_img);
        childName = itemView.findViewById(R.id.child_name_value);
        age = itemView.findViewById(R.id.child_age_value);
        groupClass = itemView.findViewById(R.id.child_class_value);
        introduce = itemView.findViewById(R.id.child_introduce_value);
    }

    @Override
    public void setItemData(Item itemData) {
        ChildInfoItem childInfoItem = (ChildInfoItem) itemData;
        childHeader.setImageBitmap(childInfoItem.getChildHeader());
        childName.setText(childInfoItem.getChildName());
        age.setText(String.valueOf(childInfoItem.getAge()));
        groupClass.setText(childInfoItem.getGroupClass());
        introduce.setText(childInfoItem.getIntrouce());
    }
}
