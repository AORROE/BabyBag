package com.hwt.babybag.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hwt.babybag.MyApplication;
import com.hwt.babybag.R;

import java.util.List;

public class MineAdapter extends BaseQuickAdapter<MineItem, BaseViewHolder> {


    public MineAdapter(int layoutResId, @Nullable List<MineItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MineItem item) {
        helper.setText(R.id.mine_user_name,item.getUserName());
        helper.setText(R.id.mine_content,item.getFoundContent());
        helper.setText(R.id.mine_add_time,item.getAddTime());
        helper.addOnClickListener(R.id.mine_ll);
        helper.addOnClickListener(R.id.comment_ll);
        helper.addOnClickListener(R.id.icon_comment);
        final ImageView praise = helper.getView(R.id.icon_praise);
        final LinearLayout content_img = helper.getView(R.id.content_img);
        final LinearLayout praise_ll = helper.getView(R.id.praise_ll);
        final ImageView mine_user_header = helper.getView(R.id.mine_user_header);
        final ImageView img = helper.getView(R.id.img);

        if(item.getIsPraise() == 1){
            praise.setSelected(true);
        }else {
            praise.setSelected(false);
        }
        praise_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getIsPraise() == 0){
                    praise.setSelected(true);
                    item.setCheck(true);
                    item.setIsPraise(1);
                }else {
                    praise.setSelected(false);
                    item.setCheck(false);
                    item.setIsPraise(0);
                }
            }
        });
        if(item.getUserPhoto() != null){
            Glide.with(MyApplication.getContextObj()).load(Uri.parse(item.getUserPhoto())).into(mine_user_header);
        }else {
            mine_user_header.setImageResource(R.drawable.icon_zhangweitu);
        }

        if(item.getFoundImg() != null){
            img.setVisibility(View.VISIBLE);
            item.setCheck(true);
            Glide.with(MyApplication.getContextObj()).load(item.getFoundImg()).into(img);
        }else {
            item.setCheck(false);
            img.setVisibility(View.GONE);
        }


    }
}
