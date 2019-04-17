package com.hwt.babybag.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hwt.babybag.R;

import java.util.List;

public class VideoAdapter extends BaseQuickAdapter<VideoItem, BaseViewHolder> {

    public VideoAdapter(int layoutResId, @Nullable List<VideoItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoItem item) {
        helper.setText(R.id.video_title,item.getVideoTitle());
        helper.setText(R.id.video_introduce,item.getVideoInstroduce());
        helper.setImageBitmap(R.id.video_cover,item.getVideoCover());
        helper.addOnClickListener(R.id.baby_ll_item);
    }
}
