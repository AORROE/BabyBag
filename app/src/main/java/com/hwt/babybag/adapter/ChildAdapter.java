package com.hwt.babybag.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hwt.babybag.R;

import java.util.List;

public class ChildAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ChildAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.child_name,item);
    }
}
