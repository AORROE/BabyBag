package com.hwt.babybag.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hwt.babybag.R;
import com.hwt.babybag.bean.ChildInfoBean;

import java.util.List;

public class ChooseChildDialog extends Dialog{
//    private Bitmap childAvatar;
//    private String childName;
    private List<ChildInfoBean> childs;
    private MyCallBack myCallBack;

    public ChooseChildDialog(Context context, List<ChildInfoBean> childs,MyCallBack myCallBack) {
        super(context, R.style.ChooseDialog);
        this.childs = childs;
        this.myCallBack = myCallBack;
    }

    public ChooseChildDialog(Context context, int themeResId, List<ChildInfoBean> childs,MyCallBack myCallBack) {
        super(context, themeResId);
        this.childs = childs;
        this.myCallBack = myCallBack;
    }

    public ChooseChildDialog(Context context, boolean cancelable, OnCancelListener cancelListener,List<ChildInfoBean> childs,MyCallBack myCallBack) {
        super(context, cancelable, cancelListener);
        this.childs = childs;
        this.myCallBack = myCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_form_bottom);
        initView();
    }

    private void initView(){
        LinearLayout bottom_dialog = findViewById(R.id.bottom_dialog);
        Log.i("arrow", "initView: "+childs.size());
        if(childs.size() > 0){
            for (ChildInfoBean child : childs){
                View view = LayoutInflater.from(this.getContext()).inflate(R.layout.childs_dialog_layout,null);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.weight=1;
                view.setLayoutParams(params);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myCallBack.getChildId(child.getChildId());
                    }
                });
                TextView child_name_check = view.findViewById(R.id.child_name_check);
                child_name_check.setText(child.getChildName());
                bottom_dialog.addView(view);
            }
        }
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

    public interface MyCallBack{
        void getChildId(int id);
    }

}
