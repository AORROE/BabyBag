package com.hwt.babybag.ui.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hwt.babybag.MyApplication;
import com.hwt.babybag.R;
import com.hwt.babybag.adapter.ChildAdapter;
import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.bean.ChildInfoBean;
import com.hwt.babybag.bean.UserInfo;
import com.hwt.babybag.network.RetrofitFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChildManagerAct extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.toolsbar_menu)
    public ImageView icon_menu;
    @BindView(R.id.tv_title_bar)
    public TextView tv_main_title;
    @BindView(R.id.child_rv)
    public RecyclerView child_rv;
    private ChildAdapter adapter;
    private List<String> list;

    private List<ChildInfoBean> childsList;
    private  UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_manager);
        ButterKnife.bind(this);
        tv_main_title.setText("孩子管理");
        icon_menu.setImageResource(R.drawable.icon_left_arrow);
        icon_menu.setOnClickListener(this);
        Intent intent = getIntent();
        userInfo = intent.getParcelableExtra("USERINFO");
        initData();
//        onRefreshUser(userInfo.getUserId());
        adapter = new ChildAdapter(R.layout.child_manager_item,list);
        adapter.addFooterView(getView());
        jumpToChildInfo();
        child_rv.setLayoutManager(new LinearLayoutManager(this));
        child_rv.setAdapter(adapter);

    }
    //数据源
    private void initData(){
        if(userInfo != null){
            if(userInfo.getChildInfoBeanList().size() > 0){
                childsList = userInfo.getChildInfoBeanList();
                list = new ArrayList<>();
                for(int i = 0; i <childsList.size();i++){
                    list.add(childsList.get(i).getChildName());
                }
            }else {
                childsList = new ArrayList<>();
            }
        }

    }

    /**
     * 添加孩子
     */
    private void bindChild(){
        Intent intent = new Intent(ChildManagerAct.this,AddChildAct.class);
        intent.putExtra("userId",userInfo.getUserId());
        startActivity(intent);
    }

    private void jumpToChildInfo(){
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ChildManagerAct.this,ChildInfoAct.class);
                intent.putExtra("childName",list.get(position));
                intent.putExtra("childInfo",childsList.get(position));
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolsbar_menu:
                setResult(0);
                finish();
                break;
        }
    }

    /**
     * 获取列表尾部布局
     * @return
     */
    private View getView(){
        View view = LayoutInflater.from(this).inflate(R.layout.child_manager_footer,null);
        LinearLayout addChild = view.findViewById(R.id.child_add_ll);
        addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChildManagerAct.this,"ADD",Toast.LENGTH_SHORT).show();
                bindChild();
            }
        });
        return view;
    }


//    private void onRefreshUser(int userId){
//        list = new ArrayList<>();
//        HashMap<String,Object> params = new HashMap<>();
//        params.put("userId",userId);
//        Log.i(MyApplication.TAG, "onNext: "+userId);
//        RetrofitFactory.getRetrofiInstace().Api()
//                .findOne(params)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<BaseEntity<UserInfo>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//                    @Override
//                    public void onNext(BaseEntity<UserInfo> userInfoBaseEntity) {
//
//                        if(userInfoBaseEntity.getStatus() == 1){
//                            UserInfo info = userInfoBaseEntity.getResult();
//                            Log.i(MyApplication.TAG, "onNext: "+info.toString());
////                            if(info.getChildInfoBeanList().size() > 0){
////                                childsList = info.getChildInfoBeanList();
////                                list = new ArrayList<>();
////                                for(int i = 0; i <childsList.size();i++){
////                                    list.add(childsList.get(i).getChildName());
////                                }
////                            }else {
////                                childsList = new ArrayList<>();
////                            }
//                            for(int i = 0; i <info.getChildInfoBeanList().size();i++){
//                                list.add("111");
//                                Log.i(MyApplication.TAG, "111: "+info.getChildInfoBeanList().get(i).toString());
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
}
