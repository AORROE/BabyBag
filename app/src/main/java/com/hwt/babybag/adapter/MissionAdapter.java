package com.hwt.babybag.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hwt.babybag.MyApplication;
import com.hwt.babybag.R;
import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.bean.MissionBean;
import com.hwt.babybag.network.RetrofitFactory;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MissionAdapter extends BaseQuickAdapter<MissionBean, BaseViewHolder> {

    private OnItemPraiseListener listener;

    public void setListener(OnItemPraiseListener listener){
        this.listener = listener;
    }



    public MissionAdapter(int layoutResId, @Nullable List<MissionBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final MissionBean item) {
        helper.setText(R.id.user_name,item.getUserName());
        helper.setText(R.id.add_time,item.getAddtime());
        helper.setText(R.id.mission_content,item.getContent());
//        helper.setImageBitmap(R.id.user_header,item.getUserHeader());
        helper.addOnClickListener(R.id.complete);
        helper.addOnClickListener(R.id.mission_ll);
        helper.addOnClickListener(R.id.icon_praise);
        final ImageView praise = helper.getView(R.id.icon_praise);
        final ImageView user_header = helper.getView(R.id.user_header);
        Button complete = helper.getView(R.id.complete);
        if(item.getIsParise() == 1){
            praise.setSelected(true);
        }else {
            praise.setSelected(false);
        }
        if(item.getIsComplete() == 1){
            complete.setText("已完成");
            complete.setBackgroundResource(R.drawable.complete_shap_gray);
            complete.setEnabled(false);
            item.setIsComplete(1);
        }else {
            complete.setText("完成");
            complete.setBackgroundResource(R.drawable.complete_shap);
            complete.setEnabled(true);
            item.setIsComplete(0);
        }
        praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getIsParise() == 0){
                    praise.setSelected(true);
                    item.setIscheck(true);
                    item.setIsParise(1);
                }else {
                    praise.setSelected(false);
                    item.setIscheck(false);
                    item.setIsParise(0);
                }
                modifyParise(item.getId());
            }
        });

        if(item.getUserHeader() != null){
            item.setIscheck(true);
            Glide.with(MyApplication.getContextObj()).load(item.getUserHeader()).into(user_header);
        }else {
            item.setIscheck(false);
            user_header.setImageResource(R.drawable.icon_zhangweitu);
        }
    }

    public interface OnItemPraiseListener{
        void onPraiseClick(ImageView praise);
    }

    private void modifyParise(int id){
        HashMap<String,Object> params = new HashMap<>();
        params.put("id",id);
        RetrofitFactory.getRetrofiInstace().Api()
                .modifyParise(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
