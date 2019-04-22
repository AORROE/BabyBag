package com.hwt.babybag.ui.act;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hwt.babybag.MainActivity;
import com.hwt.babybag.R;
import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.bean.UserInfo;
import com.hwt.babybag.network.RetrofitFactory;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time: 2019/3/12  9:26 PM
 * Author mac
 * Decription:
 */
public class LoginAct extends AppCompatActivity {
    private static final String TAG = "arrow_login";

    @BindView(R.id.login_userName_et)
    EditText userName;
    @BindView(R.id.login_password_et)
    EditText password;
    @BindView(R.id.login_gologin)
    Button goLogin;
    @BindView(R.id.login_cv)
    CardView bgLayout;
    @BindView(R.id.login_fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        fab.setImageResource(R.drawable.icon_child_info);
    }

    @OnClick({R.id.login_gologin,R.id.login_fab})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.login_fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);
                ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation(this,fab,fab.getTransitionName());
                startActivity(new Intent(this, RegisterAct.class),options.toBundle());
                break;
            case R.id.login_gologin:
                if(TextUtils.isEmpty(userName.getText().toString()) ||
                        TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(LoginAct.this,"请输入账号或密码",Toast.LENGTH_SHORT).show();
                }else {
                    login();
                }

                break;
        }
    }

    /**
     * 用户登录
     */
    public void login(){
        HashMap<String,Object> params = new HashMap<>();
        params.put("nickName",userName.getText().toString());
        params.put("password",password.getText().toString());
        RetrofitFactory.getRetrofiInstace().Api()
                .login2(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity<UserInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(BaseEntity<UserInfo> userInfoBaseEntity) {
                        if(userInfoBaseEntity.getStatus() == 1){
                            SharedPreferences preferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            Gson gson = new Gson();
                            String userinfo = gson.toJson(userInfoBaseEntity.getResult());
                            editor.putString("USERINFO",userinfo);
                            editor.commit();
                            //跳转到首页
                            Intent i2 = new Intent(LoginAct.this,MainActivity.class);
                            startActivity(i2);
                            finish();
                        }else {
                            Toast.makeText(LoginAct.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: "+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
