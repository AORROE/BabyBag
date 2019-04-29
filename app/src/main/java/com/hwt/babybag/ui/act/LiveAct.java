package com.hwt.babybag.ui.act;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwt.babybag.MyApplication;
import com.hwt.babybag.R;
import com.hwt.babybag.adapter.VideoItem;
import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.network.RetrofitFactory;
import com.hwt.babybag.utils.ChooseImg;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LiveAct extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolsbar_menu)
    public ImageView icon_menu;
    @BindView(R.id.tv_title_bar)
    public TextView tv_main_title;
    @BindView(R.id.cover_img)
    public ImageView cover_img;
    @BindView(R.id.tip_text)
    public TextView tip_text;
    @BindView(R.id.start_live)
    public Button start_live;
    @BindView(R.id.live_title_et)
    public EditText live_title_et;

    private String imageUrl;
    String rtmpUrl2;

    public String getRtmpUrl2() {
        return rtmpUrl2;
    }

    public void setRtmpUrl2(String rtmpUrl2) {
        this.rtmpUrl2 = rtmpUrl2;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        ButterKnife.bind(this);
        tv_main_title.setText("发布直播");
        icon_menu.setImageResource(R.drawable.icon_left_arrow);
        icon_menu.setOnClickListener(this);
        cover_img.setOnClickListener(this);
        start_live.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolsbar_menu:
                finish();
                break;
            case R.id.cover_img:
                ChooseImg.getInstance().chooserImg(cover_img, this, new ChooseImg.MyCallBack() {
                    @Override
                    public void SuccessCallBack(String imgUrl) {
                        tip_text.setVisibility(View.GONE);
                        setImageUrl(imgUrl);
                    }

                    @Override
                    public void failCallBack(int code) {
                    }
                });
                break;
            case R.id.start_live:
                getRtmpUrl();
                break;
        }
    }

    private void addLive(String rtmpUrl,String streamId){

        VideoItem params = new VideoItem();
        String coverUrl = getImageUrl();
        String videoUrl = "http://video.xslease.com/live/"+streamId + ".flv";
        SharedPreferences sp = this.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        int userId = sp.getInt("userId",0);

        params.setUserId(userId);
        params.setCoverUrl(coverUrl);
        params.setVideoUrl(videoUrl);
        params.setVideoTitle(live_title_et.getText().toString());
        RetrofitFactory.getRetrofiInstace().Api()
                .addVideo(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity baseEntity) {
                        if(baseEntity.getStatus() == 1){
                            Intent intent = new Intent(LiveAct.this,LivePlayAct.class);
                            intent.putExtra("rtmpUrl",rtmpUrl);
                            startActivity(intent);
                            finish();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void getRtmpUrl(){
        Map<String ,Object> params = new HashMap<>();
        params.put("streamId","66661234");
        params.put("endTime",1556553033);

        RetrofitFactory.getRetrofiInstace().Api()
                .getRtmpUrl(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity<String> stringBaseEntity) {
                        if(stringBaseEntity.getStatus() == 1){
                            setRtmpUrl2(stringBaseEntity.getResult());
                            Log.i(MyApplication.TAG, "onNext:111 "+stringBaseEntity.getResult());
                        }
                        addLive(stringBaseEntity.getResult(),"66661234");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
