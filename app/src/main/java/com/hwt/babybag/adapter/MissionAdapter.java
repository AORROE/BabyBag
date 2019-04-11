package com.hwt.babybag.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hwt.babybag.R;

import java.util.List;

public class MissionAdapter extends BaseQuickAdapter<MissionItem, BaseViewHolder> {

    public MissionAdapter(int layoutResId, @Nullable List<MissionItem> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, MissionItem item) {
        helper.setText(R.id.user_name,item.getUserName());
        helper.setText(R.id.add_time,item.getAddtime());
        helper.setText(R.id.mission_content,item.getMissionContent());
        helper.setImageBitmap(R.id.user_header,item.getUserHeader());
        helper.addOnClickListener(R.id.complete);
        helper.addOnClickListener(R.id.mission_ll);
    }
}
