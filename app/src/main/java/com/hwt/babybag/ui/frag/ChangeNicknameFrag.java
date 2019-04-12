package com.hwt.babybag.ui.frag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hwt.babybag.R;

public class ChangeNicknameFrag extends Fragment implements View.OnClickListener {
    private EditText nickName;
    private ImageView clearText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_info,container,false);
        nickName = view.findViewById(R.id.user_nickname_et);
        clearText = view.findViewById(R.id.clear_text);
        autoShowInput(view.getContext(),nickName);
        clearText.setOnClickListener(this);
        return view;
    }


    public void autoShowInput(Context context,EditText editText){
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        //打开软键盘
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);    //InputMethodManager.SHOW_FORCED
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clear_text:
                nickName.setText("");
                break;
        }
    }
}
