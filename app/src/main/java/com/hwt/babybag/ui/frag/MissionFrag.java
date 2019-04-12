package com.hwt.babybag.ui.frag;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hwt.babybag.R;
import com.hwt.babybag.adapter.MissionAdapter;
import com.hwt.babybag.adapter.MissionItem;
import com.hwt.babybag.utils.MyDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Time: 2018/12/25  9:11 PM
 * Author mac
 * Decription:
 */
public class MissionFrag extends Fragment {
    private RecyclerView rv_mission;
    private List<MissionItem> missionData;
    private MissionAdapter missionAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main_mission,container,false);
        rv_mission = view.findViewById(R.id.mission_rv);
        //初始化数据源
        initData();
        //初始化适配器
        initAdapter(view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        rv_mission.setLayoutManager(layoutManager);
        rv_mission.setAdapter(missionAdapter);
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

        missionData = new ArrayList<>();
        for (int i = 0; i<20;i++){
            missionData.add(new MissionItem(
                    BitmapFactory.decodeResource(getResources(),R.drawable.icon_zhangweitu),
                    "Arrow老师"+ i,"2019-04-11","中西部那就卡",0,0
            ));
        }
    }
    MyDialog myDialog = null;
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
                        myDialog= new MyDialog(view.getContext(), "是否确定完成任务",
                                "确定",
                                "取消",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(view.getContext(),"完成:"+position,Toast.LENGTH_SHORT).show();
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
    }
}
