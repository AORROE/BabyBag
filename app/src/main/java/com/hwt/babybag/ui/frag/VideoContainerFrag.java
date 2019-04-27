package com.hwt.babybag.ui.frag;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hwt.babybag.R;
import com.hwt.babybag.adapter.VideoAdapter;
import com.hwt.babybag.adapter.VideoItem;
import com.hwt.babybag.ui.act.Video2Act;
import com.hwt.babybag.ui.act.VideoAct;

import java.util.ArrayList;
import java.util.List;

public class VideoContainerFrag extends Fragment {

    private int pageType;

    private RecyclerView rv_video;
    private List<VideoItem> list;
    private VideoAdapter myAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if(getPageType() == 0){
           view = inflater.inflate(R.layout.fragment_tab,container,false);
       }else {
           view = inflater.inflate(R.layout.fragment_tab2,container,false);
       }

        rv_video = view.findViewById(R.id.video_rv);
        initData();
        initAdapter(view);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(),1);
        rv_video.setLayoutManager(layoutManager);
        rv_video.setAdapter(myAdapter);
        Log.i("arrow", "getPageType: "+ getPageType());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 添加数据源
     */
    private void initData(){
        list = new ArrayList<>();
        for (int i = 0; i<20;i++){
            list.add(new VideoItem(
                    BitmapFactory.decodeResource(getResources(),R.drawable.icon_header),
                    "学前"+i+i+i+"班","很厉害的一个班"
            ));
        }
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
                if(getPageType() == 0){
                    videoIntent = new Intent(view.getContext(), Video2Act.class);
                }else {
                    videoIntent = new Intent(view.getContext(), VideoAct.class);
                }
                startActivity(videoIntent);
            }
        });
        myAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        myAdapter.isFirstOnly(false);
    }

    public int getPageType() {
        return pageType;
    }

    public void setPageType(int pageType) {
        this.pageType = pageType;
    }
}
