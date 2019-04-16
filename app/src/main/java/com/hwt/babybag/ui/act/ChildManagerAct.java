package com.hwt.babybag.ui.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hwt.babybag.R;
import com.hwt.babybag.adapter.ChildAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChildManagerAct extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.toolsbar_menu)
    public ImageView icon_menu;
    @BindView(R.id.tv_title_bar)
    public TextView tv_main_title;
    @BindView(R.id.child_rv)
    public RecyclerView child_rv;
    private ChildAdapter adapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_manager);
        ButterKnife.bind(this);
        tv_main_title.setText("孩子管理");
        icon_menu.setImageResource(R.drawable.icon_left_arrow);
        icon_menu.setOnClickListener(this);
        initDat();
        adapter = new ChildAdapter(R.layout.child_manager_item,list);
        adapter.addFooterView(getView());
        jumpToChildInfo();
        child_rv.setLayoutManager(new LinearLayoutManager(this));
        child_rv.setAdapter(adapter);

    }
    //数据源
    private void initDat(){
        list = new ArrayList<>();
        for(int i = 0; i <10;i++){
            list.add("ARROW"+i);
        }
    }

    /**
     * 添加孩子
     */
    private void addChild(){

    }

    private void jumpToChildInfo(){
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ChildManagerAct.this,ChildInfoAct.class);
                intent.putExtra("childName",list.get(position));
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolsbar_menu:
                setResult(0);
                finish();
                break;
        }
    }

    /**
     * 获取列表尾部布局
     * @return
     */
    private View getView(){
        View view = LayoutInflater.from(this).inflate(R.layout.child_manager_footer,null);
        LinearLayout addChild = view.findViewById(R.id.child_add_ll);
        addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChildManagerAct.this,"ADD",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
