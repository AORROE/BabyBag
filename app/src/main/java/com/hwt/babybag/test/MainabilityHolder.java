package com.hwt.babybag.test;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwt.babybag.R;

public class MainabilityHolder extends ItemHolder{
    private ImageView icon;
    private TextView title;
    public MainabilityHolder(@NonNull View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.baby_icon);
        title = itemView.findViewById(R.id.baby_icon_title);
    }

    @Override
    public void setItemData(Item itemData) {
        MainAbilityItem mainAbilityItem = (MainAbilityItem) itemData;
        icon.setImageResource(mainAbilityItem.getIcon());
        title.setText(mainAbilityItem.getIconTitle());
    }
}
