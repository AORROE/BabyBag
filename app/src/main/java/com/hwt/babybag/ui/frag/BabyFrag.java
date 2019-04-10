package com.hwt.babybag.ui.frag;

import android.graphics.Bitmap;
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
import com.hwt.babybag.adapter.RVAdapter;
import com.hwt.babybag.test.ChildInfoItem;
import com.hwt.babybag.test.Item;
import com.hwt.babybag.test.MainAbilityItem;
import com.hwt.babybag.test.MultiRVAdapter;
import com.hwt.babybag.test.RecommendVideoItem;

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
    private String[] videoContent = {"学前1班","学前2班","学前3班","学前4班"};
    private List<String> dataList;
    private RVAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_baby,container,false);
        rv_video = view.findViewById(R.id.baby_rv);
        ll_noData = view.findViewById(R.id.ll_no_data_view);
        initData();
        adapter = new RVAdapter(view.getContext(),dataList);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(),2);

        rv_video.setLayoutManager(layoutManager);
        rv_video.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initData(){
        dataList = new ArrayList<>();
        for (int i = 0; i < videoContent.length; i++){
            dataList.add(videoContent[i]);
        }
        if(dataList.size() == 0){
            ll_noData.setVisibility(View.VISIBLE);
        }else {
            ll_noData.setVisibility(View.GONE);
        }

    }
}
