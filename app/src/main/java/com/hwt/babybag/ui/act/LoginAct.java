package com.hwt.babybag.ui.act;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hwt.babybag.MainActivity;
import com.hwt.babybag.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

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

    //    public void login(){
//        if(!TextUtils.isEmpty(userName.getText().toString())
//          &&!TextUtils.isEmpty(password.getText().toString())){
//            OkHttpClient client = new OkHttpClient();
//            RequestBody body = new FormBody.Builder()
//                    .add("appid","1")
//                    .add("token","11111111111111111111111111111111")
//                    .add("api_name","dst.user.login")
//                    .add("mobile",userName.getText().toString())
//                    .add("password","e10adc3949ba59abbe56e057f20f883e")
//                    .add("jpush_reg_id","1111")
//                    .build();
//            Request request = new Request.Builder()
//                    .url("http://dst_test.118.easysoft168.com/api/api")
//                    .post(body)
//                    .build();
//            Call call = client.newCall(request);
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    Log.i(TAG, "onFailure: ");
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
////                Log.i(TAG, "onResponse: " + response.headers().toString());
//                    assert response.body() != null;
//                    Log.i(TAG, "onResponse: " + response.body().string());
////                Headers headers = response.headers();
////                for (int i = 0; i < headers.size(); i++) {
////                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
////                }
//                }
//            });
//        }else {
//            Snackbar.make(goLogin,"账号或密码不能为空",Snackbar.LENGTH_SHORT).show();
//        }
//
//    }

    @OnClick({R.id.login_gologin,R.id.login_fab})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.login_fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);

                ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation(this,fab,fab.getTransitionName());
                startActivity(new Intent(this, RegisterAct.class),options.toBundle());
//                this.finish();
                break;
            case R.id.login_gologin:
//                Explode explode = new Explode();
//                explode.setDuration(500);
//                getWindow().setExitTransition(null);
//                getWindow().setEnterTransition(null);
//                ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
//                startActivity(i2, oc2.toBundle());
                Intent i2 = new Intent(this,MainActivity.class);
                startActivity(i2);
                finish();
//                login();
                break;
        }
    }

}
