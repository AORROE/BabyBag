package com.hwt.babybag.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hwt.babybag.MyApplication;
import com.hwt.babybag.R;

import java.util.List;

public class VideoAdapter extends BaseQuickAdapter<VideoItem, BaseViewHolder> {

    public VideoAdapter(int layoutResId, @Nullable List<VideoItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoItem item) {
        helper.setText(R.id.user_name,item.getUserName());
        helper.setText(R.id.video_introduce,item.getVideoTitle());
//        helper.setImageBitmap(R.id.video_cover,item.getCoverUrl());
        helper.setImageResource(R.id.video_cover,R.drawable.icon_zhangweitu);
        helper.addOnClickListener(R.id.baby_ll_item);

        ImageView userHeader = helper.getView(R.id.user_photo);
        ImageView coverImag = helper.getView(R.id.video_cover);
        if(item.getUserHeader() != null){
            Glide.with(MyApplication.getContextObj())
                    .load(item.getUserHeader())
                    .into(userHeader);
            item.setChoose(true);
        }else {
            userHeader.setImageResource(R.drawable.icon_zhangweitu);
            item.setChoose(false);
        }

        if(item.getCoverUrl() != null){
            Glide.with(MyApplication.getContextObj())
                    .load(item.getCoverUrl())
                    .into(coverImag);
        }else {
            coverImag.setImageResource(R.drawable.icon_zhangweitu);
        }
    }
}
