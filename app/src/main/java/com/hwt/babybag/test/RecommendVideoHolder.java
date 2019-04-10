package com.hwt.babybag.test;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwt.babybag.R;

public class RecommendVideoHolder extends ItemHolder{
    private ImageView viderCover;
    private TextView videoTitle,videoIntroduce;

    public RecommendVideoHolder(@NonNull View itemView) {
        super(itemView);
        viderCover = itemView.findViewById(R.id.video_cover);
        videoTitle = itemView.findViewById(R.id.video_title);
        videoIntroduce = itemView.findViewById(R.id.video_introduce);
    }

    @Override
    public void setItemData(Item itemData) {
        RecommendVideoItem item = (RecommendVideoItem) itemData;
        viderCover.setImageBitmap(item.getVideoCover());
        videoTitle.setText(item.getVideoTitle());
        videoIntroduce.setText(item.getVideoIntrouce());
    }
}
