package com.hwt.babybag.ui.act;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hwt.babybag.MainActivity;
import com.hwt.babybag.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Time: 2019/3/12  9:26 PM
 * Author mac
 * Decription:
 */
public class LoginAct extends AppCompatActivity {

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
//                this.finish();
                break;
            case R.id.login_gologin:
                Explode explode = new Explode();
                explode.setDuration(500);
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);
                ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                Intent i2 = new Intent(this,MainActivity.class);
                startActivity(i2, oc2.toBundle());
                break;
        }
    }
}
