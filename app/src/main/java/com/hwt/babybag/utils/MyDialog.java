package com.hwt.babybag.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.hwt.babybag.R;

public class MyDialog extends Dialog {
    private String content;
    private String confirmText;
    private String cancelText;
    private View.OnClickListener confirmClickListener;
    private View.OnClickListener cancelClickListener;
    private static final int SHOW_ONE = 1;
    private int show = 2;


    public MyDialog(Context context, String content, String confirmText,
                    View.OnClickListener confirmClickListener) {
        super(context, R.style.Dialog);
        this.content = content;
        this.confirmText = confirmText;
        this.confirmClickListener = confirmClickListener;
    }

    public MyDialog(Context context, String content, String confirmText,
                    String cancelText,
                    View.OnClickListener confirmClickListener) {
        super(context, R.style.Dialog);
        this.content = content;
        this.confirmText = confirmText;
        this.cancelText = cancelText;
        this.confirmClickListener = confirmClickListener;
    }

    public MyDialog(Context context,
                    String content, String confirmText, String cancelText,
                    View.OnClickListener confirmClickListener,
                    View.OnClickListener cancelClickListener) {
        super(context, R.style.Dialog);
        this.content = content;
        this.confirmText = confirmText;
        this.cancelText = cancelText;
        this.confirmClickListener = confirmClickListener;
        this.cancelClickListener = cancelClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_layout);
        TextView dialog_content = findViewById(R.id.dialog_content);
        TextView dialog_confirm = findViewById(R.id.dialog_confirm);
        TextView dialog_cancel = findViewById(R.id.dialog_cancel);
        View dialog_line = findViewById(R.id.dialog_line);

        if (!TextUtils.isEmpty(content)) {
            dialog_content.setText(content);
        } else {
            dialog_content.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(confirmText))
            dialog_confirm.setText(confirmText);
        if (!TextUtils.isEmpty(cancelText))
            dialog_cancel.setText(cancelText);

        if(SHOW_ONE == show){
            dialog_line.setVisibility(View.GONE);
            dialog_cancel.setVisibility(View.GONE);
          if(confirmClickListener != null){
              dialog_confirm.setOnClickListener(confirmClickListener);
          }
          dialog_confirm.setBackgroundResource(R.drawable.back_text_selector_left);
        }else {
            if(confirmClickListener != null){
                dialog_confirm.setOnClickListener(confirmClickListener);
            }
            if(cancelClickListener != null){
                dialog_cancel.setOnClickListener(cancelClickListener);
            }else {
                dialog_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyDialog.this.dismiss();
                    }
                });
            }
            dialog_confirm.setBackgroundResource(R.drawable.back_text_selector_left);
            dialog_cancel.setBackgroundResource(R.drawable.back_text_selector_right);
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
}
