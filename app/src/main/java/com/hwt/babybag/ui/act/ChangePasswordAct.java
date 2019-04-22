package com.hwt.babybag.ui.act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ChangePasswordAct extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolsbar_menu)
    public ImageView icon_menu;
    @BindView(R.id.tv_title_bar)
    public TextView tv_main_title;
    @BindView(R.id.new_pwd_et)
    public EditText new_pwd_et;
    @BindView(R.id.mobile_et)
    public EditText mobile_et;
    @BindView(R.id.submit_change)
    public Button submit_change;

    private UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        ButterKnife.bind(this);
        tv_main_title.setText("修改密码");
        icon_menu.setImageResource(R.drawable.icon_left_arrow);
        icon_menu.setOnClickListener(this);
        Intent intent = getIntent();
        userInfo = intent.getParcelableExtra("USERINFO");
        submit_change.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolsbar_menu:
                setResult(0);
                finish();
                break;
            case R.id.submit_change:
                if(!new_pwd_et.getText().toString().equals(mobile_et.getText().toString())){
                    Toast.makeText(MyApplication.getContextObj(),"两次密码输入不一致",Toast.LENGTH_SHORT).show();
                }else {
                    modifyPassword();
                }
                break;
        }
    }

    private void modifyPassword(){
        HashMap<String,Object> params = new HashMap<>();
        params.put("userId",userInfo.getUserId());
        params.put("password",new_pwd_et.getText().toString());
        RetrofitFactory.getRetrofiInstace().Api()
                .modifyPassword(params)
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
}
