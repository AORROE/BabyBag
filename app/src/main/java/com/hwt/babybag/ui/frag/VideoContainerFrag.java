package com.hwt.babybag.ui.frag;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hwt.babybag.MyApplication;
import com.hwt.babybag.R;
import com.hwt.babybag.adapter.VideoAdapter;
import com.hwt.babybag.adapter.VideoItem;
import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.network.RetrofitFactory;
import com.hwt.babybag.ui.act.VideoAct;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VideoContainerFrag extends Fragment {

    private int pageType;

    private RecyclerView rv_video;
    private List<VideoItem> list;
    private VideoAdapter myAdapter;
    private View view;
    private GridLayoutManager layoutManager;
    private SwipeRefreshLayout video_sfl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if(getPageType() == 0){
           view = inflater.inflate(R.layout.fragment_tab,container,false);
       }else {
           view = inflater.inflate(R.layout.fragment_tab2,container,false);
       }
        rv_video = view.findViewById(R.id.video_rv);
        video_sfl = view.findViewById(R.id.video_sfl);
        layoutManager = new GridLayoutManager(view.getContext(),1);
        initAdapter(view);
        initData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        video_sfl.setColorSchemeResources(R.color.colorBCD42,R.color.colorPrimary,R.color.colorC34A);
        video_sfl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rv_video.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        myAdapter.notifyDataSetChanged();
                        video_sfl.setRefreshing(false);
                    }
                },2500);
            }
        });
    }

    /**
     * 添加数据源
     */
    private void initData(){
        list = new ArrayList<>();
        RetrofitFactory.getRetrofiInstace().Api()
                .getAllVideo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity<List<VideoItem>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity<List<VideoItem>> listBaseEntity) {
                        if(listBaseEntity.getStatus() == 1){
                            list = listBaseEntity.getResult();
                            for (VideoItem item : list){
                                Log.i(MyApplication.TAG, "item :"+item.toString());
                            }
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

    /**
     * 初始化Adapter
     * @param view
     */
    private void initAdapter(final View view){
        myAdapter = new VideoAdapter(R.layout.baby_recommend_list_item,list);
        myAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view1, int position) {
                Intent videoIntent;
//                if(getPageType() == 0){
//                    videoIntent = new Intent(view.getContext(), Video2Act.class);
//                }else {
//                    videoIntent = new Intent(view.getContext(), VideoAct.class);
//                }
                videoIntent = new Intent(view.getContext(), VideoAct.class);
                startActivity(videoIntent);
            }
        });
        myAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        myAdapter.isFirstOnly(false);
        rv_video.setLayoutManager(layoutManager);
        rv_video.setAdapter(myAdapter);
    }

    public int getPageType() {
        return pageType;
    }

    public void setPageType(int pageType) {
        this.pageType = pageType;
    }
}
