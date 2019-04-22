package com.hwt.babybag.ui.act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hwt.babybag.MyApplication;
import com.hwt.babybag.R;
import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.bean.ChildInfoBean;
import com.hwt.babybag.network.RetrofitFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddChildAct extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.toolsbar_menu)
    public ImageView icon_menu;
    @BindView(R.id.tv_title_bar)
    public TextView tv_main_title;

    @BindView(R.id.child_avatar_ll)
    public LinearLayout child_avatar_ll;
    @BindView(R.id.child_sex_ll)
    public LinearLayout child_sex_ll;

    @BindView(R.id.child_name_et)
    public EditText child_name;
    @BindView(R.id.child_age_et)
    public EditText child_age;
    @BindView(R.id.child_instruction_et)
    public EditText child_instruction;

    @BindView(R.id.child_sex)
    public TextView child_sex;

    @BindView(R.id.child_avatar)
    public ImageView child_avatar;

    @BindView(R.id.bind_child)
    public Button bindChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        tv_main_title.setText("添加孩子");
        icon_menu.setImageResource(R.drawable.icon_left_arrow);
        icon_menu.setOnClickListener(this);
        bindChild.setOnClickListener(this);
        child_name.setSelection(child_name.getText().toString().trim().length());
        child_age.setSelection(child_age.getText().toString().trim().length());
        child_instruction.setSelection(child_instruction.getText().toString().trim().length());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolsbar_menu:
                finish();
                break;
            case R.id.child_avatar_ll:
                break;
            case R.id.child_sex_ll:
                break;
            case R.id.bind_child:
                addChild();
                break;
        }
    }

    private void addChild(){
        ChildInfoBean params = new ChildInfoBean();
        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId",0);
        Log.i(MyApplication.TAG, "addChild: "+ userId);
        params.setParentsId(userId);
        params.setChildName(child_name.getText().toString());
        params.setAge(Integer.valueOf(child_age.getText().toString()));
        if(child_sex.getText().equals("男")){
            params.setSex(0);
        }else {
            params.setSex(1);
        }
        params.setCharacterInstructe(child_instruction.getText().toString());
        RetrofitFactory.getRetrofiInstace().Api()
                .addChild(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity baseEntity) {
                        if(baseEntity.getStatus() == 1){
                            Toast.makeText(MyApplication.getContextObj(),baseEntity.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setAction("action.refreshChilds");
                            sendBroadcast(intent);
                            finish();
                        }else {
                            Toast.makeText(MyApplication.getContextObj(),baseEntity.getMessage(),
                                    Toast.LENGTH_SHORT).show();
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
