package com.hwt.babybag.ui.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hwt.babybag.R;
import com.hwt.babybag.adapter.RVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Time: 2018/12/25  9:11 PM
 * Author mac
 * Decription:
 */
public class MissionFrag extends Fragment {
    private RecyclerView rv_mission;
    private String[] videoContent = {"何老师","何老师","何老师","何老师"};
    private List<String> dataList;
    private RVAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_mission,container,false);
        rv_mission = view.findViewById(R.id.mission_rv);
        initData();
        adapter = new RVAdapter(view.getContext(),dataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        rv_mission.setLayoutManager(layoutManager);
        rv_mission.setAdapter(adapter);
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

    }
}
