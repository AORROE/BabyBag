package com.hwt.babybag.ui.act;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hwt.babybag.MyApplication;
import com.hwt.babybag.R;
import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.bean.UserInfo;
import com.hwt.babybag.network.RetrofitFactory;
import com.hwt.babybag.ui.frag.ChangeNicknameFrag;
import com.hwt.babybag.utils.ChooseImg;
import com.hwt.babybag.utils.ChooseSexDialog;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PersonInfoAct extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.tv_title_bar)
    public TextView tv_main_title;
    @BindView(R.id.right_text)
    public TextView saveText;
    @BindView(R.id.toolsbar_menu)
    public ImageView icon_menu;
    @BindView(R.id.user_avatar)
    public ImageView user_avatar;
    @BindView(R.id.user_nickname)
    public TextView user_nickname;

    @BindView(R.id.user_sex)
    public TextView user_sex;

    @BindView(R.id.user_sign)
    public TextView user_sign;

    @BindView(R.id.person_info_fl)
    public FrameLayout personInfo_fl;
    //
    @BindView(R.id.user_avatar_ll)
    public LinearLayout user_avatar_ll;
    @BindView(R.id.user_nickname_ll)
    public LinearLayout user_nickname_ll;
    @BindView(R.id.user_sex_ll)
    public LinearLayout user_sex_ll;
//    @BindView(R.id.user_birthday_ll)
//    public LinearLayout user_birthday_ll;
    @BindView(R.id.user_sign_ll)
    public LinearLayout user_sign_ll;
    @BindView(R.id.save_user_info)
    public Button save_user_info;

    private ChangeNicknameFrag changeNicknameFrag;
    private Fragment flagFrag;
    FragmentTransaction fragmentManager;
    private int flag = 0;

    private int userId;
    UserInfo userInfo;

    private ChooseSexDialog dialog;

    private String imageUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        ButterKnife.bind(this);
        tv_main_title.setText("个人信息");
        icon_menu.setImageResource(R.drawable.icon_left_arrow);
        setListener();
        setData();
    }

    /**
     * 设置数据
     */
    private void setData(){
        Intent intent = getIntent();
        userInfo = intent.getParcelableExtra("USERINFO");
        Log.i("arrow", "onCreate: "+userInfo.toString());
        if(userInfo.getPhoto()!= null){
            Uri uri = Uri.parse(userInfo.getPhoto());
            Glide.with(this).load(uri).into(user_avatar);
        }
        userId = userInfo.getUserId();
        onRefreshUser(userId);
    }

    private void setListener(){
        icon_menu.setOnClickListener(this);
        user_avatar_ll.setOnClickListener(this);
        user_nickname_ll.setOnClickListener(this);
        user_sex_ll.setOnClickListener(this);
//        user_birthday_ll.setOnClickListener(this);
        user_sign_ll.setOnClickListener(this);
        saveText.setOnClickListener(this);
        save_user_info.setOnClickListener(this);
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
                ChooseImg.getInstance().chooserImg(user_avatar, v.getContext(), new ChooseImg.MyCallBack() {
                    @Override
                    public void SuccessCallBack(String imgUrl) {
                        imageUrl = imgUrl;
                    }

                    @Override
                    public void failCallBack(int code) {

                    }
                });
                break;
            case R.id.user_nickname_ll:
                if(changeNicknameFrag == null){
                    flagFrag = new ChangeNicknameFrag();
                    ((ChangeNicknameFrag) flagFrag).userId = userId;
                }
                fragmentManager.replace(R.id.person_info_fl,flagFrag);
                flag = 1;
                saveText.setVisibility(View.VISIBLE);

                break;
            case R.id.user_sex_ll:
                dialog = new ChooseSexDialog(this, new ChooseSexDialog.SexCallBack() {
                    @Override
                    public void getSex(int id) {
                        switch (id){
                            case 0:
                                user_sex.setText("男");
                                break;
                            case 1:
                                user_sex.setText("女");
                                break;
                            case 2:
                                break;
                        }
                        dialog.dismiss();
                    }
                });
                Window dialogWindow = dialog.getWindow();
                if (dialogWindow != null) {
                    WindowManager.LayoutParams attr = dialogWindow.getAttributes();
                    if (attr != null) {
                        attr.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        attr.width = WindowManager.LayoutParams.MATCH_PARENT;
                        attr.gravity = Gravity.BOTTOM;
                        dialogWindow.setAttributes(attr);
                        dialogWindow.setWindowAnimations(R.style.dialog_animation);
                    }
                }
                dialog.setCannotBackPress();
                dialog.show();
                break;
//            case R.id.user_birthday_ll:
//                break;
            case R.id.user_sign_ll:
                break;
            case R.id.right_text:
                if(flagFrag instanceof ChangeNicknameFrag){
                    ((ChangeNicknameFrag) flagFrag).updateUser();
                    user_nickname.setText(((ChangeNicknameFrag) flagFrag).getUserNickName());
                    if(flag != 0){
                        fragmentManager.remove(flagFrag);
                        saveText.setVisibility(View.INVISIBLE);
                        flag = 0;
                    }else {
//                        this.setResult(0);
//                        this.finish();
                    }
                }
                break;
            case R.id.save_user_info:
                updateUser(imageUrl,user_sex.getText().toString(),user_nickname.getText().toString());
                break;
        }
        fragmentManager.commit();
    }

    private void onRefreshUser(int userId){
//        UserInfo params = new UserInfo();
//        params.setUserId(userId);
        HashMap<String,Object> params = new HashMap<>();
        params.put("userId",userId);
        RetrofitFactory.getRetrofiInstace().Api()
                .findOne(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity<UserInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity<UserInfo> userInfoBaseEntity) {
                        if(userInfoBaseEntity.getStatus() == 1){
                            UserInfo info = userInfoBaseEntity.getResult();
                            user_nickname.setText(info.getNickName());
                            user_sign.setText(info.getIndividualitySign());
                            if(info.getSex() == 0){
                                user_sex.setText("男");
                            }else {
                                user_sex.setText("女");
                            }
                            if(info.getPhoto()!= null){
                                Uri uri = Uri.parse(info.getPhoto());
                                Glide.with(PersonInfoAct.this).load(uri).into(user_avatar);
                            }
//                            Log.i(MyApplication.TAG, "onNext: "+user_nickname.getText());
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

    /**
     * 更新
     * @param imgUrl
     */
    public void updateUser(String imgUrl,String sexId,String userNickName){
        UserInfo params = new UserInfo();
        params.setUserId(userInfo.getUserId());
        if(imgUrl != null){
            params.setPhoto(imgUrl);
        }
        if(sexId != null){
            if(sexId.equals("男")){
                params.setSex(0);
            }else {
                params.setSex(1);
            }
        }
        if(userNickName != null){
            params.setNickName(userNickName);
        }
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
                            setResult(0);
                            finish();
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
}
