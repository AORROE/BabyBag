package com.hwt.babybag.ui.frag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hwt.babybag.MyApplication;
import com.hwt.babybag.R;
import com.hwt.babybag.adapter.VideoAdapter;
import com.hwt.babybag.adapter.VideoItem;
import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.bean.ChildInfoBean;
import com.hwt.babybag.bean.UserInfo;
import com.hwt.babybag.network.RetrofitFactory;
import com.hwt.babybag.ui.act.VideoAct;
import com.hwt.babybag.utils.ChooseChildDialog;
import com.hwt.babybag.view.radar.RadarMapData;
import com.hwt.babybag.view.radar.RadarMapView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Time: 2018/12/25  9:11 PM
 * Author mac
 * Decription:
 */
public class BabyFrag extends Fragment {

    private RecyclerView rv_video;
    private LinearLayout ll_noData,ability_ll;
    private List<VideoItem> list;
    private VideoAdapter myAdapter;

    private TextView childName,age,characterInstructe,childTag;
    private ImageView child_header_img;

    private Button child_change;

    private ChooseChildDialog chooseChildDialog = null;

    private UserInfo user;
    private ChildInfoBean childs;
    private List<ChildInfoBean> childList;
    private View view;

    private RadarMapView radarMapView;
    private RadarMapView radarMapView2;
    private RadarMapData radarMapData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_baby,container,false);
        IntentFilter filter = new IntentFilter();
        filter.addAction("action.refreshChilds");
        container.getContext().registerReceiver(refreshReceiver,filter);
        initView(view);
        getUserInfo();
        initData();
        initAbility();
        initStudy();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView(View view){
        rv_video = view.findViewById(R.id.baby_rv);
        ll_noData = view.findViewById(R.id.ll_no_data_view);
        ability_ll = view.findViewById(R.id.ability_ll);
        child_change = view.findViewById(R.id.child_change);
        childName = view.findViewById(R.id.child_name_value);
        age = view.findViewById(R.id.child_age_value);
        characterInstructe = view.findViewById(R.id.child_introduce_value);
        childTag = view.findViewById(R.id.child_tag);
        child_header_img = view.findViewById(R.id.child_header_img);
        child_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(childs != null){
                    chooseChild(v);
                }else {
                    Toast.makeText(MyApplication.getContextObj(),"您还没有绑定孩子",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initAbility(){
        radarMapView = view.findViewById(R.id.radar_map);
        radarMapData=new RadarMapData();
        ability_ll.setVisibility(View.GONE);
        radarMapData.setCount(6);
        radarMapData.setMainPaintColor(Color.parseColor("#009688"));
        radarMapData.setTitles(new String[]{"书法","身体素质", "语言" , "数学能力","艺术设计" , "交流"});
        radarMapData.setValuse(new Double[]{21.0,30.5,50.4,9.0,36.9,60.0});
        radarMapData.setTextSize(30);
        radarMapView.setData(radarMapData);
    }
    private void initStudy(){
        radarMapView2 = view.findViewById(R.id.radar_map2);
        radarMapData=new RadarMapData();
        radarMapData.setCount(4);
        radarMapData.setMainPaintColor(Color.parseColor("#009688"));
        radarMapData.setTitles(new String[]{"玩耍与探索","自主学习", "个性创作","认真思考" });
        radarMapData.setValuse(new Double[]{4.0,70.0,40.8,20.0});
        radarMapData.setTextSize(30);
        radarMapView2.setData(radarMapData);
    }

    /**
     * 添加数据源
     */
    private void initData(){
        list = new ArrayList<>();
        HashMap<String,Object> params = new HashMap<>();
        params.put("userId",user.getUserId());
        RetrofitFactory.getRetrofiInstace().Api()
                .getAllVideo(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity<List<VideoItem>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity<List<VideoItem>> listBaseEntity) {
                        if(listBaseEntity.getStatus() == 1){

                            List<VideoItem> data = new ArrayList<>();
                            for (VideoItem item : list){
                                Log.i(MyApplication.TAG, "item :"+item.toString());
                            }
                            for (int i = 0 ; i <listBaseEntity.getResult().size();i++){
                                if(i < 4){
                                    data.add(listBaseEntity.getResult().get(i));
                                }
                            }
                            list = data;
                            initAdapter();
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

    private void initAdapter(){
        myAdapter = new VideoAdapter(R.layout.baby_recommend_list_item,list);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(),2);
        rv_video.setLayoutManager(layoutManager);
        rv_video.setAdapter(myAdapter);
        if(list.size() == 0){
            ll_noData.setVisibility(View.VISIBLE);
        }else {
            ll_noData.setVisibility(View.GONE);
        }
        myAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent videoIntent;
                videoIntent = new Intent(view.getContext(), VideoAct.class);
                videoIntent.putExtra("videoUrl",list.get(position).getVideoUrl());
                videoIntent.putExtra("videoTitle",list.get(position).getVideoTitle());
                startActivity(videoIntent);
            }
        });
    }
    /**
     * 选择孩子弹框
     * @param view
     */
    private void chooseChild(final View view){
        chooseChildDialog = new ChooseChildDialog(view.getContext(), childList,
                new ChooseChildDialog.MyCallBack() {
            @Override
            public void getChildId(int id) {
                Log.i(MyApplication.TAG, "getChildId: "+ id);
                onRefreshChild(id);
                chooseChildDialog.dismiss();
            }
        });
        Window dialogWindow = chooseChildDialog.getWindow();
//                //设置弹出位置
//                dialogWindow.setGravity(Gravity.BOTTOM);
////                //设置弹出动画
////                dialogWindow.setWindowAnimations(R.style.main_menu_animStyle);
//                //设置对话框大小
//                dialogWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
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
        chooseChildDialog.setCannotBackPress();
        chooseChildDialog.show();
        radarMapData.setValuse(new Double[]{0.0,0.0,0.0,0.0});
        radarMapView2.setData(radarMapData);
        radarMapView2.start();
    }

    private void getUserInfo(){
        SharedPreferences sp = getContext().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userInfo = sp.getString("USERINFO",null);
        Type type = new TypeToken<UserInfo>(){}.getType();
        user = gson.fromJson(userInfo,type);
        if(user.getChildInfoBeanList().size() > 0){
            childList = user.getChildInfoBeanList();
            childs = user.getChildInfoBeanList().get(0);
            childName.setText(childs.getChildName());
            age.setText(String.valueOf(childs.getAge()));
            characterInstructe.setText(childs.getCharacterInstructe());
            if(childs.getPhoto() != null){
                Glide.with(getContext()).load(childs.getPhoto()).into(child_header_img);
            }
            if(childs.getPersonLabel() != null){
                childTag.setVisibility(View.VISIBLE);
                childTag.setText(childs.getPersonLabel());
            }else {
                childTag.setVisibility(View.INVISIBLE);
            }
        }else {
            Toast.makeText(this.getContext(),"您还没有绑定孩子",Toast.LENGTH_SHORT).show();
        }
        Log.i("arrow", "getUserInfo: "+ user.toString());
    }

    /**
     * 刷新
     *
     */
    private void onRefreshChild(int childId){
        ChildInfoBean params = new ChildInfoBean();
        params.setChildId(childId);
        RetrofitFactory.getRetrofiInstace().Api()
                .findChildById(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity<ChildInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity<ChildInfoBean> childInfoBeanBaseEntity) {
                        if(childInfoBeanBaseEntity.getStatus() == 1){
                            ChildInfoBean childInfoBean = childInfoBeanBaseEntity.getResult();
                            childName.setText(childInfoBean.getChildName());
                            age.setText(String.valueOf(childInfoBean.getAge()));
                            characterInstructe.setText(childInfoBean.getCharacterInstructe());
                            if(childInfoBean.getPersonLabel() != null){
                                childTag.setVisibility(View.VISIBLE);
                                childTag.setText(childInfoBean.getPersonLabel());
                            }else {
                                childTag.setVisibility(View.INVISIBLE);
                            }
                            if(childInfoBean.getPhoto() != null){
                                Glide.with(getContext()).load(childInfoBean.getPhoto()).into(child_header_img);
                            }else {
                                child_header_img.setImageResource(R.drawable.icon_zhangweitu);
                            }
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

    private BroadcastReceiver refreshReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
            Log.i(MyApplication.TAG, "onReceive: "+user.toString());
                if(action.equals("action.refreshChilds")){
                    onRefreshUser(user.getUserId());
                }
        }
    };



    private void onRefreshUser(int userId){
//        UserInfo params = new UserInfo();
//        params.setUserId(userId);
        HashMap<String,Object> params = new HashMap<>();
        params.put("userId",userId);
        Log.i(MyApplication.TAG, "onNext: "+userId);
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
                            Log.i(MyApplication.TAG, "onNext: "+info.toString());
                            if(info.getChildInfoBeanList().size() > 0){
                                childList = info.getChildInfoBeanList();
                                childs = info.getChildInfoBeanList().get(0);
                                childName.setText(childs.getChildName());
                                age.setText(String.valueOf(childs.getAge()));
                                characterInstructe.setText(childs.getCharacterInstructe());
                                if(childs.getPersonLabel() != null){
                                    childTag.setVisibility(View.VISIBLE);
                                    childTag.setText(childs.getPersonLabel());
                                }else {
                                    childTag.setVisibility(View.INVISIBLE);
                                }
                                if(childs.getPhoto() != null){
                                    Glide.with(getContext()).load(childs.getPhoto()).into(child_header_img);
                                }else {
                                    child_header_img.setImageResource(R.drawable.icon_zhangweitu);
                                }
                            }else {
                                Toast.makeText(getContext(),"您还没有绑定孩子",Toast.LENGTH_SHORT).show();
                            }
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
    public void onDestroy() {
        getContext().unregisterReceiver(refreshReceiver);
        super.onDestroy();
    }
}
