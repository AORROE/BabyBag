package com.hwt.babybag.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hwt.babybag.R;
import com.hwt.babybag.bean.ChildInfoBean;

import java.util.List;

public class ChooseSexDialog extends Dialog implements View.OnClickListener {
//    private Bitmap childAvatar;
//    private String childName;
    private SexCallBack myCallBack;

    private Button manBtn,wommentBtn,cancelBtn;

    public ChooseSexDialog(Context context,SexCallBack myCallBack) {
        super(context, R.style.ChooseDialog);
        this.myCallBack = myCallBack;
    }

    public ChooseSexDialog(Context context, int themeResId, SexCallBack myCallBack) {
        super(context, themeResId);
        this.myCallBack = myCallBack;
    }

    public ChooseSexDialog(Context context, boolean cancelable, OnCancelListener cancelListener, SexCallBack myCallBack) {
        super(context, cancelable, cancelListener);
        this.myCallBack = myCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_form_bottom_sex);
        initView();
    }

    private void initView(){
        LinearLayout bottom_dialog = findViewById(R.id.sex_bottom_dialog_sex);
        manBtn = findViewById(R.id.man_btn);
        wommentBtn = findViewById(R.id.woment_btn);
        cancelBtn = findViewById(R.id.cancel_btn);

        manBtn.setOnClickListener(this);
        wommentBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

    }

    public void setCannotBackPress() {
        this.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.man_btn:
                myCallBack.getSex(0);
                break;
            case R.id.woment_btn:
                myCallBack.getSex(1);
                break;
            case R.id.cancel_btn:
                myCallBack.getSex(2);
                break;
        }
    }

    public interface SexCallBack{
        void getSex(int id);
    }

}
