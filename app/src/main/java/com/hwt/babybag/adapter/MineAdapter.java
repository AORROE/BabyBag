package com.hwt.babybag.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

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
        helper.setText(R.id.add_time,item.getAddTime());
        helper.addOnClickListener(R.id.mine_ll);
        final ImageView praise = helper.getView(R.id.icon_praise);
        final ImageView mine_user_header = helper.getView(R.id.mine_user_header);

        if(item.getIsPraise() == 1){
            praise.setSelected(true);
        }else {
            praise.setSelected(false);
        }
        praise.setOnClickListener(new View.OnClickListener() {
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
    }
}
