package com.hwt.babybag.ui.act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hwt.babybag.R;
import com.hwt.babybag.adapter.MineCommentAdapter;
import com.hwt.babybag.adapter.MineItem;
import com.hwt.babybag.bean.MinedetailBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineDetailAct extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolsbar_menu)
    public ImageView icon_menu;
    @BindView(R.id.tv_title_bar)
    public TextView tv_main_title;
    @BindView(R.id.detail_rv)
    public RecyclerView rv_detail;

    private ImageView user_photo;
    private TextView user_name;
    private TextView detail_time;
    private TextView detail_content;
    private ImageView mine_img;

    private MineCommentAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<MinedetailBean> list;

    private Intent intent;
    private MineItem item;

    private View emptyView;
    private ImageView noDataImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_detail);
        ButterKnife.bind(this);
        tv_main_title.setText("详情");
        icon_menu.setImageResource(R.drawable.icon_left_arrow);
        icon_menu.setOnClickListener(this);

        initData();

        adapter = new MineCommentAdapter(R.layout.activity_detail_item,list);
        View headerView = LayoutInflater.from(this).inflate(R.layout.activity_detail_layout,null);
        emptyView = LayoutInflater.from(this).inflate(R.layout.null_data_layout,null);
        noDataImg = emptyView.findViewById(R.id.no_data_img);

        user_photo = headerView.findViewById(R.id.user_photo);
        user_name = headerView.findViewById(R.id.user_name);
        detail_time = headerView.findViewById(R.id.detail_time);
        detail_content = headerView.findViewById(R.id.detail_content);
        mine_img = headerView.findViewById(R.id.mine_img);

        item = intent.getParcelableExtra("MineDetail");
        if(item.getUserPhoto() != null){
            Glide.with(headerView).load(item.getUserPhoto()).into(user_photo);
        }else {
            user_photo.setImageResource(R.drawable.icon_zhangweitu);
        }
        if(item.getFoundImg() != null){
            Glide.with(headerView).load(item.getFoundImg()).into(mine_img);
        }else {
            mine_img.setVisibility(View.GONE);
        }
        user_name.setText(item.getUserName());
        detail_time.setText(item.getAddTime());
        detail_content.setText(item.getFoundContent());


        adapter.addHeaderView(headerView);
        layoutManager = new LinearLayoutManager(this);
        rv_detail.setLayoutManager(layoutManager);
        rv_detail.setAdapter(adapter);

        if(list.size() == 0){
            adapter.addFooterView(emptyView);

            RequestOptions options = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            Glide.with(MineDetailAct.this).load(R.drawable.no_complete)
                    .apply(options).into(noDataImg);
        }else {
            adapter.removeAllFooterView();
        }
    }

    private void initData(){
        list = new ArrayList<>();
        intent = getIntent();
//        for (int i =0 ; i < 10 ;i++){
//            list.add(new MinedetailBean("i"+i));
//        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolsbar_menu:
                finish();
                break;
        }
    }
}
