package com.hwt.babybag.ui.frag;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hwt.babybag.MyApplication;
import com.hwt.babybag.R;
import com.hwt.babybag.adapter.MissionAdapter;
import com.hwt.babybag.adapter.MissionItem;
import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.bean.MissionBean;
import com.hwt.babybag.network.RetrofitFactory;
import com.hwt.babybag.utils.MyDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time: 2018/12/25  9:11 PM
 * Author mac
 * Decription:
 */
public class MissionFrag extends Fragment {
    private RecyclerView rv_mission;
//    private List<MissionItem> missionData;
    private List<MissionBean> missionData;
    private MissionAdapter missionAdapter;
    private SwipeRefreshLayout refreshLayout;

    RecyclerView.LayoutManager layoutManager;
    View view;
    MyDialog myDialog = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_mission,container,false);
        rv_mission = view.findViewById(R.id.mission_rv);
        layoutManager = new LinearLayoutManager(view.getContext());
        refreshLayout = view.findViewById(R.id.refresh_srl);
        //初始化数据源
        initData(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refreshLayout.setColorSchemeResources(R.color.colorBCD42,R.color.colorPrimary,R.color.colorC34A);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rv_mission.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onRefreshData();
                        missionAdapter.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);
                    }
                },2500);
            }
        });
    }

    /**
     * 添加数据源
     */
    private void initData(final View view){
        missionData = new ArrayList<>();
        RetrofitFactory.getRetrofiInstace().Api()
                .getAllMission()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity<List<MissionBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(MyApplication.TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(BaseEntity<List<MissionBean>> listBaseEntity) {
                        if(listBaseEntity.getStatus() == 1){
                            missionData = listBaseEntity.getResult();
                            initAdapter(view);
                            for(int i = 0;i<missionData.size();i++){
                                Log.i(MyApplication.TAG, "onNext: "+missionData.get(i).toString());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(MyApplication.TAG, "onError"+e.toString());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
    /**
     * 初始化Adapter
     * @param view
     */
    private void initAdapter(final View view){
        missionAdapter = new MissionAdapter(R.layout.mission_item,missionData);
        missionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view1, final int position) {
                switch (view1.getId()){
                    case R.id.complete:
                        Log.i("arrow--onClick", "onItemChildClick: "+ position);
                        Button complete = view1.findViewById(R.id.complete);
                        myDialog= new MyDialog(view.getContext(), "是否确定完成任务",
                                "确定",
                                "取消",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(view.getContext(),"完成:"+position,Toast.LENGTH_SHORT).show();
                                        modifyComplete(missionData.get(position).getId());
                                        if(missionData.get(position).getIsComplete() == 0){
                                            complete.setText("已完成");
                                            complete.setEnabled(false);
                                            complete.setBackgroundResource(R.drawable.complete_shap_gray);
                                            missionData.get(position).setIsComplete(1);
                                        }else {
//                                            missionData.get(position).setIsComplete(0);
                                        }
                                        myDialog.dismiss();
                                    }
                                });
                        myDialog.setCannotBackPress();
                        myDialog.setCancelable(false);
                        myDialog.show();
                        break;
                    case R.id.mission_ll:
                        Log.i("arrow--onClick", "onItemChildClick: "+ position+1);
                        Toast.makeText(view.getContext(),"Item:"+(position +1),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.icon_praise:
                        Toast.makeText(view.getContext(),"点赞成功:"+(position +1),Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        missionAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        missionAdapter.isFirstOnly(false);
        rv_mission.setLayoutManager(layoutManager);
        rv_mission.setAdapter(missionAdapter);
    }

    private void onRefreshData(){
        RetrofitFactory.getRetrofiInstace().Api()
                .getAllMission()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity<List<MissionBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(MyApplication.TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(BaseEntity<List<MissionBean>> listBaseEntity) {
                        if(listBaseEntity.getStatus() == 1){
                            missionData.clear();
                            missionData = listBaseEntity.getResult();
                            initAdapter(view);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(MyApplication.TAG, "onError"+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void modifyComplete(int missionId){
        HashMap<String,Object> params = new HashMap<>();
        params.put("id",missionId);
        RetrofitFactory.getRetrofiInstace().Api()
                .modifyComplete(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity baseEntity) {

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
