package com.hwt.babybag.ui.frag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.hwt.babybag.utils.ChooseChildDialog;

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
    private LinearLayout ll_noData;
    private List<VideoItem> list;
    private VideoAdapter myAdapter;

    TextView childName,age,characterInstructe,childTag;

    private Button child_change;

    private ChooseChildDialog chooseChildDialog = null;

    private UserInfo user;
    private ChildInfoBean childs;
    private List<ChildInfoBean> childList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main_baby,container,false);
        IntentFilter filter = new IntentFilter();
        filter.addAction("action.refreshChilds");
        container.getContext().registerReceiver(refreshReceiver,filter);
        initView(view);
        getUserInfo();
        initData();
        myAdapter = new VideoAdapter(R.layout.baby_recommend_list_item,list);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(),2);
        rv_video.setLayoutManager(layoutManager);
        rv_video.setAdapter(myAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView(View view){
        rv_video = view.findViewById(R.id.baby_rv);
        ll_noData = view.findViewById(R.id.ll_no_data_view);
        child_change = view.findViewById(R.id.child_change);
        childName = view.findViewById(R.id.child_name_value);
        age = view.findViewById(R.id.child_age_value);
        characterInstructe = view.findViewById(R.id.child_introduce_value);
        childTag = view.findViewById(R.id.child_tag);
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


    /**
     * 添加数据源
     */
    private void initData(){
        list = new ArrayList<>();
        list.add(new VideoItem(
                BitmapFactory.decodeResource(getResources(),R.drawable.icon_header),
                "学前222班","很厉害的一个班"
        ));
        list.add(new VideoItem(
                BitmapFactory.decodeResource(getResources(),R.drawable.icon_header),
                "学前333班","很厉害的一个班"
        ));
        list.add(new VideoItem(
                BitmapFactory.decodeResource(getResources(),R.drawable.icon_header),
                "学前1111班","很厉害的一个班"
        ));
        list.add(new VideoItem(
                BitmapFactory.decodeResource(getResources(),R.drawable.icon_header),
                "学前444班","很厉害的一个班"
        ));

        if(list.size() == 0){
            ll_noData.setVisibility(View.VISIBLE);
        }else {
            ll_noData.setVisibility(View.GONE);
        }
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
