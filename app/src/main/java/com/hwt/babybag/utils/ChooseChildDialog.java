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

public class ChooseChildDialog extends Dialog{
    private Bitmap childAvatar;
    private String childName;
    private View.OnClickListener childClickListener;

    public ChooseChildDialog(Context context, Bitmap childAvatar, String childName,
                             View.OnClickListener childClickListener) {
        super(context, R.style.ChooseDialog);
        this.childAvatar = childAvatar;
        this.childName = childName;
        this.childClickListener = childClickListener;
    }

    public ChooseChildDialog(Context context, int themeResId, Bitmap childAvatar, String childName,
                             View.OnClickListener childClickListener) {
        super(context, themeResId);
        this.childAvatar = childAvatar;
        this.childName = childName;
        this.childClickListener = childClickListener;
    }

    public ChooseChildDialog(Context context, boolean cancelable, OnCancelListener cancelListener,
                             Bitmap childAvatar, String childName, View.OnClickListener childClickListener) {
        super(context, cancelable, cancelListener);
        this.childAvatar = childAvatar;
        this.childName = childName;
        this.childClickListener = childClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_form_bottom);
        initView();
    }

    private void initView(){
        LinearLayout child_choose_ll = findViewById(R.id.child_choose_ll);
        LinearLayout bottom_dialog = findViewById(R.id.bottom_dialog);
        ImageView child_avatar = findViewById(R.id.child_avatar_check);
        TextView child_name = findViewById(R.id.child_name_check);

        if(childAvatar != null){
            child_avatar.setImageBitmap(childAvatar);
        }
        if(!TextUtils.isEmpty(childName)){
            child_name.setText(childName);
        }
        child_choose_ll.setOnClickListener(childClickListener);
        View view = LayoutInflater.from(this.getContext()).inflate(R.layout.child_manager_footer,null);
        bottom_dialog.addView(view);
    }

    public Bitmap getChildAvatar() {
        return childAvatar;
    }

    public void setChildAvatar(Bitmap childAvatar) {
        this.childAvatar = childAvatar;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public View.OnClickListener getChildClickListener() {
        return childClickListener;
    }

    public void setChildClickListener(View.OnClickListener childClickListener) {
        this.childClickListener = childClickListener;
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

}
