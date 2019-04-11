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
public class BabyFrag extends Fragment {

    private RecyclerView rv_video;
    private LinearLayout ll_noData;
    private List<VideoItem> list;
    private VideoAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_baby,container,false);
        rv_video = view.findViewById(R.id.baby_rv);
        ll_noData = view.findViewById(R.id.ll_no_data_view);
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
}
