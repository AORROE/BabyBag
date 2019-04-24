package com.hwt.babybag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.bean.ChildInfoBean;
import com.hwt.babybag.bean.UserInfo;
import com.hwt.babybag.network.RetrofitFactory;
import com.hwt.babybag.ui.IImagePicker;
import com.hwt.babybag.ui.act.ChangePasswordAct;
import com.hwt.babybag.ui.act.ChildManagerAct;
import com.hwt.babybag.ui.act.LiveAct;
import com.hwt.babybag.ui.act.LoginAct;
import com.hwt.babybag.ui.act.PersonInfoAct;
import com.hwt.babybag.ui.act.UserCheckAct;
import com.hwt.babybag.ui.frag.BabyFrag;
import com.hwt.babybag.ui.frag.MineFrag;
import com.hwt.babybag.ui.frag.MissionFrag;
import com.hwt.babybag.ui.frag.VideoFrag;
import com.hwt.babybag.utils.ChooseImg;
import com.qingmei2.rximagepicker.core.RxImagePicker;
import com.qingmei2.rximagepicker.entity.Result;

import java.lang.reflect.Type;
import java.util.HashMap;


import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.dl_main)
    public DrawerLayout drawerLayout;
    @BindView(R.id.fl_main_body)
    public FrameLayout bodyLayout;
    @BindView(R.id.ll_bottomNav)
    public LinearLayout bottomBar;

    private Fragment babyFrag;
    private Fragment missionFrag;
    private Fragment videoFrag;
    private Fragment mineFrag;

    //底部按钮
    @BindView(R.id.rl_childInfoTab)
    public View childInfoTab;
    @BindView(R.id.rl_missionTab)
    public View missionTab;
    @BindView(R.id.rl_videoTab)
    public View videoTab;
    @BindView(R.id.rl_mineTab)
    public View mineTab;
    @BindView(R.id.bottom_bar_baby)
    public TextView tv_baby;
    @BindView(R.id.bottom_bar_mission)
    public TextView tv_mission;
    @BindView(R.id.bottom_bar_video)
    public TextView tv_video;
    @BindView(R.id.bottom_bar_mine)
    public TextView tv_mine;
    @BindView(R.id.bottom_bar_babyImg)
    public ImageView iv_baby;
    @BindView(R.id.bottom_bar_missionImg)
    public ImageView iv_mission;
    @BindView(R.id.bottom_bar_videoImg)
    public ImageView iv_video;
    @BindView(R.id.bottom_bar_mineImg)
    public ImageView iv_mine;
    @BindView(R.id.toolsbar_menu)
    public ImageView icon_menu;
    @BindView(R.id.icon_live)
    public ImageView icon_live;
    public ImageView header_img;
    public TextView header_title;
    public LinearLayout header_back;

    //标题栏
    @BindView(R.id.tv_title_bar)
    public TextView tv_main_title;
    @BindView(R.id.ll_tools_bar_layout)
    public LinearLayout ll_tools_bar_layout;

    //侧滑栏
    @BindView(R.id.nav_view)
    public NavigationView nav_view;

    private UserInfo user;

    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setHalfTransparent();
        setStatusBarFullTransparent();
        init();
        getUserInfo();
        setListener();
        setInitStatus();
        icon_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        nav_view.setCheckedItem(R.id.main_item);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setCheckable(true);//设置选项可选
                menuItem.setChecked(true);//设置选型被选中
                Intent intent = null;
                switch (menuItem.getItemId()){
                    case R.id.person_info_item:
                        intent = new Intent(MainActivity.this, PersonInfoAct.class);
                        intent.putExtra("USERINFO",user);
                        startActivityForResult(intent,1);
                        break;
                    case R.id.change_password_item:
                        intent = new Intent(MainActivity.this, ChangePasswordAct.class);
                        intent.putExtra("USERINFO",user);
                        startActivityForResult(intent,2);
                        break;
                    case R.id.child_info_item:
                        intent = new Intent(MainActivity.this, ChildManagerAct.class);
                        intent.putExtra("USERINFO",user);
                        startActivityForResult(intent,3);
                        break;
                    case R.id.open_live_item:
                        intent = new Intent(MainActivity.this, UserCheckAct.class);
                        intent.putExtra("USERINFO",user);
                        startActivityForResult(intent,4);
                        break;
                    case R.id.login_out_item:
                        sp.edit().clear().commit();
                        intent = new Intent(MainActivity.this, LoginAct.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                drawerLayout.closeDrawers();//关闭侧边菜单栏
                return true;
            }
        });
    }

    /**
     * 获取界面上的UI控件
     */
    private void init() {
        tv_main_title.setText("首页");
        View view = nav_view.getHeaderView(0);
        header_title = view.findViewById(R.id.header_title);
        header_img = view.findViewById(R.id.header_img);
        header_back = view.findViewById(R.id.header_back);
        header_img.setOnClickListener(this);
        header_back.setOnClickListener(this);

    }

    /**
     * 为每个按钮设置监听方法
     */
    private void setListener() {
        for (int i = 0; i < bottomBar.getChildCount(); i++) {
            bottomBar.getChildAt(i).setOnClickListener(this);
        }
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_childInfoTab:
                clearBottomImageState();
                selectDisplayView(0);
                break;
            case R.id.rl_missionTab:
                clearBottomImageState();
                selectDisplayView(1);
                break;
            case R.id.rl_videoTab:
                clearBottomImageState();
                selectDisplayView(2);
                break;
            case R.id.rl_mineTab:
                clearBottomImageState();
                selectDisplayView(3);
                break;
            case R.id.icon_live:
                Intent intent = null;
                if(user.getIsLive() == 0){
                    intent = new Intent(MainActivity.this,UserCheckAct.class);
                }else {
                    intent = new Intent(this, LiveAct.class);
                }
                startActivity(intent);
                break;
            case R.id.header_img:
                ChooseImg.getInstance().chooserImg(header_img, MainActivity.this, new ChooseImg.MyCallBack() {
                    @Override
                    public void SuccessCallBack(String imgUrl) {
                        updateUser(imgUrl);
                    }

                    @Override
                    public void failCallBack(int code) {

                    }
                });
                Toast.makeText(MainActivity.this,"test",Toast.LENGTH_SHORT).show();
                break;
            case R.id.header_back:
                ChooseImg.getInstance().chooserImg(header_back, MainActivity.this, new ChooseImg.MyCallBack() {
                    @Override
                    public void SuccessCallBack(String imgUrl) {

                    }

                    @Override
                    public void failCallBack(int code) {

                    }
                });
                Toast.makeText(MainActivity.this,"test",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    /**
     * 设置初始选择
     */
    private void setInitStatus() {
        clearBottomImageState();
        setSelectedStatus(0);
        createView(0);
    }
    /**
     * 显示对应的页面
     * @param i
     */
    private void selectDisplayView(int i) {
//        removeAllView();
        createView(i);
        setSelectedStatus(i);
    }
    /**
     * 选择底部Tab，改变组件状态
     * @param i
     */
    private void setSelectedStatus(int i){
        if(i == 2){
            icon_live.setVisibility(View.VISIBLE);
            icon_live.setOnClickListener(this);

        }else {
            icon_live.setVisibility(View.INVISIBLE);
        }
        Log.i("arrow", "setSelectedStatus: ");
        switch (i){
            case 0:
                childInfoTab.setSelected(true);
                iv_baby.setImageResource(R.drawable.icon_baby_select);
                tv_baby.setTextColor(Color.parseColor("#008577"));
                ll_tools_bar_layout.setVisibility(View.VISIBLE);
                tv_main_title.setText("首页");
                break;
            case 1:
                missionTab.setSelected(true);
                iv_mission.setImageResource(R.drawable.icon_misson_select);
                tv_mission.setTextColor(Color.parseColor("#008577"));
                ll_tools_bar_layout.setVisibility(View.VISIBLE);
                tv_main_title.setText("任务卡");
                break;
            case 2:
                videoTab.setSelected(true);
                iv_video.setImageResource(R.drawable.icon_video_select);
                tv_video.setTextColor(Color.parseColor("#008577"));
                ll_tools_bar_layout.setVisibility(View.VISIBLE);
                tv_main_title.setText("视频");
                break;
            case 3:
                mineTab.setSelected(true);
                iv_mine.setImageResource(R.drawable.icon_mine_select);
                tv_mine.setTextColor(Color.parseColor("#008577"));
                ll_tools_bar_layout.setVisibility(View.VISIBLE);
                tv_main_title.setText("发现");
                break;
        }
    }
    /**
     * 选择视图
     * @param viewIndex
     */
    private void createView(int viewIndex) {
        Log.i("arrow", "createView: ");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (viewIndex){
            case 0:
                //首页界面
                Log.i("TAG", "createView: ");
                if(babyFrag == null){
                    babyFrag = new BabyFrag();
                }
                transaction.replace(R.id.fl_main_body,babyFrag);
                break;
            case 1:
                //任务卡界面
                if(missionFrag == null){
                    missionFrag = new MissionFrag();
                }
                transaction.replace(R.id.fl_main_body,missionFrag);
                break;
            case 2:
                //视频界面
                if(videoFrag == null){
                    videoFrag = new VideoFrag();
                }
                transaction.replace(R.id.fl_main_body,videoFrag);
                break;
            case 3:
                //个人中心界面
                if(mineFrag == null){
                    mineFrag = new MineFrag();
                }
                transaction.replace(R.id.fl_main_body,mineFrag);
                break;
        }
        transaction.commit();
    }

    /**
     * 清除底部按钮的选中状态
     */
    private void clearBottomImageState() {
        tv_baby.setTextColor(Color.parseColor("#bfbfbf"));
        tv_mission.setTextColor(Color.parseColor("#bfbfbf"));
        tv_video.setTextColor(Color.parseColor("#bfbfbf"));
        tv_mine.setTextColor(Color.parseColor("#bfbfbf"));

        iv_baby.setImageResource(R.drawable.icon_baby);
        iv_mission.setImageResource(R.drawable.icon_mission);
        iv_video.setImageResource(R.drawable.icon_video);
        iv_mine.setImageResource(R.drawable.icon_mine);

        for (int i = 0; i < bottomBar.getChildCount(); i++) {
            bottomBar.getChildAt(i).setSelected(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Arrow", "requestCode: "+requestCode);
        Log.i("Arrow", "resultCode: "+resultCode);
        switch (resultCode){
            case 0:
                nav_view.setCheckedItem(R.id.main_item);
                onRefresh(user.getUserId());
                break;
        }
    }

    /**
     * 获取用户信息
     */
    private void getUserInfo(){
        sp = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userInfo = sp.getString("USERINFO",null);
        String imageUrl = sp.getString("imageUrl",null);

        Type type = new TypeToken<UserInfo>(){}.getType();
        user = gson.fromJson(userInfo,type);
        Log.i("arrow", "getUserInfo: "+ user.toString());
        header_title.setText(user.getNickName());
        if(user.getPhoto() != null){
            Log.i(MyApplication.TAG, "getUserInfo: "+user.getPhoto());
            Uri uri = Uri.parse(user.getPhoto());
            Glide.with(this).load(uri).into(header_img);
        }
        if(imageUrl != null){
            Uri imgUrl = Uri.parse(imageUrl);
            Glide.with(MainActivity.this).load(imgUrl)
                    .into(new ViewTarget<View,Drawable>(header_back) {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            this.view.setBackground(resource.getCurrent());
                        }
                    });
        }

    }

    /**
     * 刷新用户信息
     * @param userId
     */
    private void onRefresh(int userId){
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
                            header_title.setText(info.getNickName());
//                            Log.i(MyApplication.TAG, "onNext: "+info.getNickName());
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



    /**
     * 全透明
     */
    protected void setStatusBarFullTransparent(){
        if(Build.VERSION.SDK_INT >= 21){
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }else if(Build.VERSION.SDK_INT >= 19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 半透明
     */
    protected void setHalfTransparent(){
        if(Build.VERSION.SDK_INT >= 21){
            Window window = getWindow();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            window.getDecorView()
                    .setSystemUiVisibility(option);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }else if(Build.VERSION.SDK_INT >= 19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    /**
     * 移除不需要的视图
     */
//    private void removeAllView() {
//        Log.i("arrow", "removeAllView: ");
//        for (int i = 0;i< bodyLayout.getChildCount();i++){
//            bodyLayout.getChildAt(i).setVisibility(View.GONE);
//        }
//    }


    public void updateUser(String imgUrl){
        UserInfo params = new UserInfo();
        params.setUserId(user.getUserId());
        params.setPhoto(imgUrl);
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
}
