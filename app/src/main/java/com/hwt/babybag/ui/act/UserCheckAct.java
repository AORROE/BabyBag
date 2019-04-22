package com.hwt.babybag.ui.act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hwt.babybag.MyApplication;
import com.hwt.babybag.R;
import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.bean.UserInfo;
import com.hwt.babybag.network.RetrofitFactory;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserCheckAct extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.toolsbar_menu)
    public ImageView icon_menu;
    @BindView(R.id.tv_title_bar)
    public TextView tv_main_title;
    @BindView(R.id.user_check_et)
    public TextView user_check_et;
    @BindView(R.id.mobile_et)
    public TextView mobile_et;
    @BindView(R.id.confirm_submit)
    public Button confirm_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_check);
        ButterKnife.bind(this);
        tv_main_title.setText("直播认证");
        icon_menu.setImageResource(R.drawable.icon_left_arrow);
        icon_menu.setOnClickListener(this);
        confirm_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolsbar_menu:
                setResult(0);
                finish();
                break;
            case R.id.confirm_submit:
                applicationUser();
                break;
        }
    }

    /**
     * 提交认证
     */
    private void applicationUser(){
        if(TextUtils.isEmpty(user_check_et.getText().toString()) ||
            TextUtils.isEmpty(mobile_et.getText().toString())){
            Toast.makeText(MyApplication.getContextObj(),"姓名和手机号不能为空",Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = getIntent();
            UserInfo userInfo = intent.getParcelableExtra("USERINFO");
            HashMap<String,Object> params = new HashMap<>();
            params.put("userId",userInfo.getUserId());
            RetrofitFactory.getRetrofiInstace().Api()
                    .commitApplication(params)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BaseEntity>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BaseEntity baseEntity) {
                            if(baseEntity.getStatus() == 1){
                                Toast.makeText(MyApplication.getContextObj(),baseEntity.getMessage(),Toast.LENGTH_SHORT).show();
                                setResult(0);
                                finish();
                            }else {
                                Toast.makeText(MyApplication.getContextObj(),baseEntity.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i(MyApplication.TAG, "onError: "+e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}
