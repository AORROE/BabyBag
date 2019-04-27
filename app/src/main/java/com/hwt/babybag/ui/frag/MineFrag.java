package com.hwt.babybag.ui.frag;

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

import java.util.ArrayList;
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
public class MineFrag extends Fragment implements View.OnClickListener {

    private RecyclerView rv_mine;
    private List<MineItem> mineData;
    private MineAdapter mineAdapter;
    private FloatingActionButton publish_fab;

    RecyclerView.LayoutManager layoutManager;
    LinearLayout mine_ll;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_mine,container,false);
        rv_mine = view.findViewById(R.id.mine_rv);
        publish_fab = view.findViewById(R.id.publish_fab);
        publish_fab.setOnClickListener(this);
        layoutManager = new LinearLayoutManager(view.getContext());
        initData(view);
//        initAdapter(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                        Toast.makeText(getContext(),"test",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void initData(View view){
        mineData = new ArrayList<>();
//        for (int i = 0;i<10;i++){
//            mineData.add(new MineItem());
//        }
        RetrofitFactory.getRetrofiInstace().Api()
                .getAllFound()
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
                Toast.makeText(getContext(),"testFab",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
