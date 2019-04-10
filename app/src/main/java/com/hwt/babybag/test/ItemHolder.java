package com.hwt.babybag.test;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ItemHolder extends RecyclerView.ViewHolder {
    public ItemHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void setItemData(Item itemData);
}
