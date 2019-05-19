package com.hwt.babybag.ui.frag;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hwt.babybag.MyApplication;
import com.hwt.babybag.R;
import com.hwt.babybag.adapter.MineAdapter;
import com.hwt.babybag.adapter.MineItem;
import com.hwt.babybag.adapter.MissionAdapter;
import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.bean.FoundBean;
import com.hwt.babybag.bean.MissionBean;
import com.hwt.babybag.network.RetrofitFactory;
import com.hwt.babybag.ui.act.AddFoundAct;
import com.hwt.babybag.ui.act.MineDetailAct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time: 2018/12/25  9:11 PM
 * Author mac
 * Decription:
 */
public class MineFrag extends Fragment implements View.OnClickListener {

    private RecyclerView rv_mine;
    private List<MineItem> mineData;
    private MineAdapter mineAdapter;
    private FloatingActionButton publish_fab;

    private RecyclerView.LayoutManager layoutManager;
    private LinearLayout mine_ll;
    private View view;
    private SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_mine,container,false);
        rv_mine = view.findViewById(R.id.mine_rv);
        publish_fab = view.findViewById(R.id.publish_fab);
        publish_fab.setOnClickListener(this);
        layoutManager = new LinearLayoutManager(view.getContext());
        refreshLayout = view.findViewById(R.id.mine_srl);
        initData(view);
//        initAdapter(view);
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
                rv_mine.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshList();
                        mineAdapter.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);
                    }
                },2500);
            }
        });
    }

    private void initAdapter(View view){
        mineAdapter = new MineAdapter(R.layout.mine_item_layout,mineData);
        mineAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mineAdapter.isFirstOnly(false);
        rv_mine.setLayoutManager(layoutManager);
        rv_mine.setAdapter(mineAdapter);
        mineAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View v, int position) {
                switch (v.getId()){
                    case R.id.mine_ll:
                        Intent intent = new Intent(getActivity(), MineDetailAct.class);
                        intent.putExtra("MineDetail",mineData.get(position));
                        startActivity(intent);
                        break;
                    case R.id.icon_comment:
                    case R.id.comment_ll:
                        Toast.makeText(getContext(),"icon_comment" + position,Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void initData(View view){
        mineData = new ArrayList<>();
        SharedPreferences sp = getContext().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        int userId = sp.getInt("userId",0);
        HashMap<String ,Object> params = new HashMap<>();
        params.put("userId",userId);
        RetrofitFactory.getRetrofiInstace().Api()
                .getAllFound(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity<List<MineItem>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity<List<MineItem>> listBaseEntity) {
                        if(listBaseEntity.getStatus() == 1){
                            mineData = listBaseEntity.getResult();
                            initAdapter(view);
                        }

//                        for (MineItem item : listBaseEntity.getResult()){
//                            Log.i(MyApplication.TAG, "onNext: "+item.toString());
//                        }
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.publish_fab:
                Intent intent = new Intent(getContext(), AddFoundAct.class);
                startActivityForResult(intent,0);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(MyApplication.TAG, "onActivityResult: "+ resultCode);
        Log.i(MyApplication.TAG, "onActivityResult: "+ requestCode);
        switch (resultCode){
            case 1:
                refreshList();
                break;
        }
    }

    private void refreshList(){
        initData(view);
    }
}
