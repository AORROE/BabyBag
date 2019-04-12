package com.hwt.babybag.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hwt.babybag.R;

import java.util.List;

public class MissionAdapter extends BaseQuickAdapter<MissionItem, BaseViewHolder> {

    private OnItemPraiseListener listener;

    public void setListener(OnItemPraiseListener listener){
        this.listener = listener;
    }



    public MissionAdapter(int layoutResId, @Nullable List<MissionItem> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final MissionItem item) {
        helper.setText(R.id.user_name,item.getUserName());
        helper.setText(R.id.add_time,item.getAddtime());
        helper.setText(R.id.mission_content,item.getMissionContent());
        helper.setImageBitmap(R.id.user_header,item.getUserHeader());
        helper.addOnClickListener(R.id.complete);
        helper.addOnClickListener(R.id.mission_ll);
        helper.addOnClickListener(R.id.icon_praise);
        final ImageView praise = helper.getView(R.id.icon_praise);
        if(item.isCheck){
            praise.setSelected(true);
        }else {
            praise.setSelected(false);
        }
        praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!item.isCheck){
                    praise.setSelected(true);
                    item.isCheck = true;
                }else {
                    praise.setSelected(false);
                    item.isCheck = false;
                }
            }
        });
    }

    public interface OnItemPraiseListener{
        void onPraiseClick(ImageView praise);
    }
}
