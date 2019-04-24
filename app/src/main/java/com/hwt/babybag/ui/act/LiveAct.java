package com.hwt.babybag.ui.act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwt.babybag.R;
import com.hwt.babybag.utils.ChooseImg;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveAct extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolsbar_menu)
    public ImageView icon_menu;
    @BindView(R.id.tv_title_bar)
    public TextView tv_main_title;
    @BindView(R.id.cover_img)
    public ImageView cover_img;
    @BindView(R.id.tip_text)
    public TextView tip_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        ButterKnife.bind(this);
        tv_main_title.setText("发布直播");
        icon_menu.setImageResource(R.drawable.icon_left_arrow);
        icon_menu.setOnClickListener(this);
        cover_img.setOnClickListener(this);
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
                    public void SuccessCallBack(int code) {
                        tip_text.setVisibility(View.GONE);
                    }

                    @Override
                    public void failCallBack(int code) {
                    }
                });
                break;
        }
    }
}
