package com.hwt.babybag.ui.frag;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hwt.babybag.R;
import com.hwt.babybag.adapter.VideoAdapter;
import com.hwt.babybag.adapter.VideoFragAdapter;
import com.hwt.babybag.adapter.VideoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Time: 2018/12/25  9:11 PM
 * Author mac
 * Decription:
 */
public class VideoFrag extends Fragment {

    private TabLayout tabLayout;
    private ViewPager videoPager;
    private VideoFragAdapter adapter;

    private List<String> fragTitles;
    private List<Fragment> fragments;
    private VideoContainerFrag videoFrag1,liveFrag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_video,container,false);
        tabLayout = view.findViewById(R.id.tab_layout);
        videoPager = view.findViewById(R.id.video_view_pager);
        Log.i("arrow", "onCreateView: 1");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("arrow", "onCreateView:2");
        initView();
    }

    /**
     * 初始化界面
     */
    public void initView(){
        Log.i("arrow", "onCreateView:3");
        //实例化
        videoFrag1 = new VideoContainerFrag();
        liveFrag = new VideoContainerFrag();
        fragTitles = new ArrayList<>();
        fragments = new ArrayList<>();
        //赋值
        fragTitles.add("直播");
        fragTitles.add("视频");
        videoFrag1.setPageType(0);
        liveFrag.setPageType(1);
        fragments.add(videoFrag1);
        fragments.add(liveFrag);
        //实例化适配器
        adapter = new VideoFragAdapter(getChildFragmentManager(),fragments,fragTitles);
        videoPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(videoPager);
    }

}
