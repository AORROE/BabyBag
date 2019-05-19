package com.hwt.babybag.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hwt.babybag.bean.MinedetailBean;

import java.util.List;

public class MineCommentAdapter extends BaseQuickAdapter<MinedetailBean, BaseViewHolder> {

    public MineCommentAdapter(int layoutResId, @Nullable List<MinedetailBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MinedetailBean item) {

    }
}
