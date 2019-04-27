package com.hwt.babybag.ui.act;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hwt.babybag.MainActivity;
import com.hwt.babybag.MyApplication;
import com.hwt.babybag.R;
import com.hwt.babybag.adapter.MineItem;
import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.bean.UserInfo;
import com.hwt.babybag.network.RetrofitFactory;
import com.hwt.babybag.utils.ChooseImg;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddFoundAct extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolsbar_menu)
    public ImageView icon_menu;
    @BindView(R.id.tv_title_bar)
    public TextView tv_main_title;
    @BindView(R.id.found_content)
    public EditText found_content;
    @BindView(R.id.found_img)
    public ImageView found_img;
    @BindView(R.id.add_img)
    public ImageView add_img;
    @BindView(R.id.add_img_ll)
    public LinearLayout add_img_ll;

    @BindView(R.id.publish_btn)
    public Button publish_btn;

    private String imagUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_found);
        ButterKnife.bind(this);
        tv_main_title.setText("发布");
        icon_menu.setImageResource(R.drawable.icon_left_arrow);
        icon_menu.setOnClickListener(this);
        publish_btn.setOnClickListener(this);
        add_img.setOnClickListener(this);
        add_img_ll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolsbar_menu:
                finish();
                break;
            case R.id.publish_btn:
                publishFound();
                break;
            case R.id.add_img_ll:
            case R.id.add_img:
                ChooseImg.getInstance().chooserImg(found_img, AddFoundAct.this, new ChooseImg.MyCallBack() {
                    @Override
                    public void SuccessCallBack(String imgUrl) {
                        Log.i(MyApplication.TAG, "SuccessCallBack: "+ imgUrl);
                        add_img_ll.setVisibility(View.GONE);
                        add_img.setVisibility(View.GONE);
                        found_img.setVisibility(View.VISIBLE);
                        Glide.with(AddFoundAct.this).load(imgUrl).into(found_img);
                        setImagUrl(imgUrl);
                    }

                    @Override
                    public void failCallBack(int code) {

                    }
                });
                break;
        }
    }

    /**
     * 发布
     */
    public void publishFound(){
        SharedPreferences sp = getSharedPreferences("UserPreferences",MODE_PRIVATE);
        int userId = sp.getInt("userId",0);
        Log.i(MyApplication.TAG, "publishFound: "+userId);
        if(!TextUtils.isEmpty(found_content.getText().toString())){
            getUserInfo(userId);
        }else {
            Toast.makeText(AddFoundAct.this,"内容不能为空",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取用户信息
     * @param userId
     */
    private void getUserInfo(int userId){
        HashMap<String,Object> params = new HashMap<>();
        params.put("userId",userId);
        RetrofitFactory.getRetrofiInstace().Api()
                .findOne(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity<UserInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity<UserInfo> userInfoBaseEntity) {
                        if(userInfoBaseEntity.getStatus() == 1){
                            publish(userInfoBaseEntity.getResult());
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

    /**
     * 添加发现
     * @param userInfo
     */
    private void publish(UserInfo userInfo){
        MineItem params = new MineItem();
        params.setUserId(userInfo.getUserId());
        params.setUserName(userInfo.getNickName());
        params.setUserPhoto(userInfo.getPhoto());
        params.setFoundContent(found_content.getText().toString());
        params.setFoundImg(getImagUrl());

        RetrofitFactory.getRetrofiInstace().Api()
                .addFound(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity baseEntity) {
                        if(baseEntity.getStatus() == 1){
                            Toast.makeText(AddFoundAct.this,"发布成功",Toast.LENGTH_SHORT).show();
                            finish();
                            setResult(0);
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

    public String getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(String imagUrl) {
        this.imagUrl = imagUrl;
    }
}
