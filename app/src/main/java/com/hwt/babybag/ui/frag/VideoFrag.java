package com.hwt.babybag.ui.frag;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hwt.babybag.R;
import com.hwt.babybag.adapter.VideoAdapter;
import com.hwt.babybag.adapter.VideoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Time: 2018/12/25  9:11 PM
 * Author mac
 * Decription:
 */
public class VideoFrag extends Fragment {
    private RecyclerView rv_video;
    private List<VideoItem> list;
    private VideoAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_video,container,false);
        rv_video = view.findViewById(R.id.video_rv);
        initData();
        initAdapter(view);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(),2);
        rv_video.setLayoutManager(layoutManager);
        rv_video.setAdapter(myAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

            }
        });
        myAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        myAdapter.isFirstOnly(false);
    }
}
