package com.hwt.babybag.ui.frag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hwt.babybag.MyApplication;
import com.hwt.babybag.R;
import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.bean.UserInfo;
import com.hwt.babybag.network.RetrofitFactory;
import com.hwt.babybag.ui.act.PersonInfoAct;
import com.hwt.babybag.ui.act.RegisterAct;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChangeNicknameFrag extends Fragment implements View.OnClickListener{
    private EditText nickName;
    private ImageView clearText;
    public int userId = 0;
    private String userNickName;

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(MyApplication.TAG, "onActivityCreated: "+ userId);
    }

    public void autoShowInput(Context context, EditText editText){
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

    public void updateUser(){
        Log.i(MyApplication.TAG, "updateUser: "+nickName.getText().toString());
        UserInfo params = new UserInfo();
        params.setUserId(userId);
        params.setNickName(nickName.getText().toString());
        setUserNickName(nickName.getText().toString());
        RetrofitFactory.getRetrofiInstace().Api()
                .updateUser(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(BaseEntity baseEntity) {
                        Log.i(MyApplication.TAG, baseEntity.getMessage());
                        if(baseEntity.getStatus() == 1){
                            Toast.makeText(MyApplication.getContextObj(),baseEntity.getMessage(),Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MyApplication.getContextObj(),baseEntity.getMessage(),Toast.LENGTH_SHORT).show();
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

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }
}
