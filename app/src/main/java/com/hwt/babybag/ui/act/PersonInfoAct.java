package com.hwt.babybag.ui.act;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hwt.babybag.R;
import com.hwt.babybag.ui.frag.ChangeNicknameFrag;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonInfoAct extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.tv_title_bar)
    public TextView tv_main_title;
    @BindView(R.id.right_text)
    public TextView saveText;
    @BindView(R.id.toolsbar_menu)
    public ImageView icon_menu;
    @BindView(R.id.person_info_fl)
    public FrameLayout personInfo_fl;
    //
    @BindView(R.id.user_avatar_ll)
    public LinearLayout user_avatar_ll;
    @BindView(R.id.user_nickname_ll)
    public LinearLayout user_nickname_ll;
    @BindView(R.id.user_sex_ll)
    public LinearLayout user_sex_ll;
    @BindView(R.id.user_birthday_ll)
    public LinearLayout user_birthday_ll;
    @BindView(R.id.user_sign_ll)
    public LinearLayout user_sign_ll;

    private ChangeNicknameFrag changeNicknameFrag;
    private Fragment flagFrag;
    FragmentTransaction fragmentManager;
    private int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        ButterKnife.bind(this);
        tv_main_title.setText("个人信息");
        icon_menu.setImageResource(R.drawable.icon_left_arrow);
        setListener();
    }

    private void setListener(){
        icon_menu.setOnClickListener(this);
        user_avatar_ll.setOnClickListener(this);
        user_nickname_ll.setOnClickListener(this);
        user_sex_ll.setOnClickListener(this);
        user_birthday_ll.setOnClickListener(this);
        user_sign_ll.setOnClickListener(this);
        saveText.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        fragmentManager= getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.toolsbar_menu:
                if(flag != 0){
                    fragmentManager.remove(flagFrag);
                    saveText.setVisibility(View.INVISIBLE);
                    flag = 0;
                }else {
                    this.setResult(0);
                    this.finish();
                }
                break;
            case R.id.user_avatar_ll:
                break;
            case R.id.user_nickname_ll:
                if(changeNicknameFrag == null){
                    flagFrag = new ChangeNicknameFrag();
                }
                fragmentManager.replace(R.id.person_info_fl,flagFrag);
                flag = 1;
                saveText.setVisibility(View.VISIBLE);

                break;
            case R.id.user_sex_ll:
                break;
            case R.id.user_birthday_ll:
                break;
            case R.id.user_sign_ll:
                break;
            case R.id.right_text:
                Toast.makeText(PersonInfoAct.this,"保存",Toast.LENGTH_SHORT).show();
                break;
        }
        fragmentManager.commit();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        fragmentManager= getSupportFragmentManager().beginTransaction();
        if(flag != 0){
            fragmentManager.remove(flagFrag);
            saveText.setVisibility(View.INVISIBLE);
            flag = 0;
        }else {
            this.setResult(0);
            this.finish();
        }
        Log.i("arrow", "onBackPressed: ");
        fragmentManager.commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText.setVisibility(View.INVISIBLE);
    }
}
