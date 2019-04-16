package com.hwt.babybag.ui.frag;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hwt.babybag.R;
import com.hwt.babybag.adapter.VideoAdapter;
import com.hwt.babybag.adapter.VideoItem;
import com.hwt.babybag.utils.ChooseChildDialog;

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

    private Button child_change;

    private ChooseChildDialog chooseChildDialog = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main_baby,container,false);
        rv_video = view.findViewById(R.id.baby_rv);
        ll_noData = view.findViewById(R.id.ll_no_data_view);
        child_change = view.findViewById(R.id.child_change);
        initData();
        myAdapter = new VideoAdapter(R.layout.baby_recommend_list_item,list);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(),2);
        rv_video.setLayoutManager(layoutManager);
        rv_video.setAdapter(myAdapter);

        child_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseChild(v);
            }
        });

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

    private void chooseChild(final View view){
        chooseChildDialog = new ChooseChildDialog(view.getContext(),
                R.style.ChooseDialog, BitmapFactory.decodeResource(getResources(), R.drawable.icon_header
        ), "ARROWLL", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),"1111",Toast.LENGTH_SHORT).show();
                chooseChildDialog.dismiss();
            }
        });
        Window dialogWindow = chooseChildDialog.getWindow();
//                //设置弹出位置
//                dialogWindow.setGravity(Gravity.BOTTOM);
////                //设置弹出动画
////                dialogWindow.setWindowAnimations(R.style.main_menu_animStyle);
//                //设置对话框大小
//                dialogWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        if (dialogWindow != null) {
            WindowManager.LayoutParams attr = dialogWindow.getAttributes();
            if (attr != null) {
                attr.height = WindowManager.LayoutParams.WRAP_CONTENT;
                attr.width = WindowManager.LayoutParams.MATCH_PARENT;
                attr.gravity = Gravity.BOTTOM;
                dialogWindow.setAttributes(attr);
                dialogWindow.setWindowAnimations(R.style.dialog_animation);
            }
        }
        chooseChildDialog.setCannotBackPress();
        chooseChildDialog.show();
    }
}
