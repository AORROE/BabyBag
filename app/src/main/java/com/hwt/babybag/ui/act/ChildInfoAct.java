package com.hwt.babybag.ui.act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwt.babybag.R;
import com.hwt.babybag.bean.ChildInfoBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChildInfoAct extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.toolsbar_menu)
    public ImageView icon_menu;
    @BindView(R.id.tv_title_bar)
    public TextView tv_main_title;

    @BindView(R.id.child_avatar)
    public ImageView child_avatar;
    @BindView(R.id.child_nickname)
    public TextView child_name;
    @BindView(R.id.child_sex)
    public TextView child_sex;
    @BindView(R.id.child_birthday)
    public TextView child_birthday;
    @BindView(R.id.child_class)
    public TextView child_group_class;
    @BindView(R.id.child_instruction)
    public TextView child_instruction;
    @BindView(R.id.unbind_child)
    public Button unbind_child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_info);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        tv_main_title.setText(intent.getStringExtra("childName"));
        setData(intent);
        icon_menu.setImageResource(R.drawable.icon_left_arrow);
        icon_menu.setOnClickListener(this);
    }

    /**
     * 设置孩子信息
     * @param intent
     */
    public void setData(Intent intent){
        ChildInfoBean childInfo = intent.getParcelableExtra("childInfo");
        child_name.setText(intent.getStringExtra("childName"));
        child_instruction.setText(childInfo.getCharacterInstructe());
        if(childInfo.getSex() == 0){
            child_sex.setText("男");
        }else {
            child_sex.setText("女");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolsbar_menu:
                setResult(0);
                finish();
                break;
        }
    }
}
