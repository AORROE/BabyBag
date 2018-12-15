package com.hwt.babybag;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout bodyLayout;

    private LinearLayout bottomBar;

    //底部按钮
    private View childInfoTab;
    private View missionTab;
    private View videoTab;
    private View mineTab;
    private TextView tv_baby;
    private TextView tv_mission;
    private TextView tv_video;
    private TextView tv_mine;
    private ImageView iv_baby;
    private ImageView iv_mission;
    private ImageView iv_video;
    private ImageView iv_mine;

    //标题栏
    private TextView tv_main_title;
    private LinearLayout ll_tools_bar_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initBottomBar();
        setListener();
        setInitStatus();

    }

    private void initBottomBar(){
        childInfoTab = findViewById(R.id.rl_childInfoTab);
        missionTab = findViewById(R.id.rl_missionTab);
        videoTab = findViewById(R.id.rl_videoTab);
        mineTab = findViewById(R.id.rl_mineTab);
        tv_baby = findViewById(R.id.bottom_bar_baby);
        tv_mission = findViewById(R.id.bottom_bar_mission);
        tv_video = findViewById(R.id.bottom_bar_video);
        tv_mine = findViewById(R.id.bottom_bar_mine);
        iv_baby = findViewById(R.id.bottom_bar_babyImg);
        iv_mission = findViewById(R.id.bottom_bar_missionImg);
        iv_video = findViewById(R.id.bottom_bar_videoImg);
        iv_mine = findViewById(R.id.bottom_bar_mineImg);
    }
    /**
     * 获取界面上的UI控件
     */
    private void init() {
        bodyLayout = findViewById(R.id.fl_main_body);
        bottomBar = findViewById(R.id.ll_bottomNav);
        tv_main_title = findViewById(R.id.tv_title_bar);
        tv_main_title.setText("首页");
        ll_tools_bar_layout = findViewById(R.id.ll_tools_bar_layout);
        initBodyLayout();
    }
    /**
     * 界面内容
     */
    private void initBodyLayout() {
        bodyLayout = findViewById(R.id.fl_main_body);
    }

    /**
     * 为每个按钮设置监听方法
     */
    private void setListener() {
        for (int i = 0; i < bottomBar.getChildCount(); i++) {
            bottomBar.getChildAt(i).setOnClickListener(this);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_childInfoTab:
                clearBottomImageState();
                selectDisplayView(0);
                break;
            case R.id.rl_missionTab:
                clearBottomImageState();
                selectDisplayView(1);
                break;
            case R.id.rl_videoTab:
                clearBottomImageState();
                selectDisplayView(2);
                break;
            case R.id.rl_mineTab:
                clearBottomImageState();
                selectDisplayView(3);
                break;
        }
    }
    /**
     * 设置初始选择
     */
    private void setInitStatus() {
        clearBottomImageState();
        setSelectedStatus(0);
        createView(0);
    }
    /**
     * 显示对应的页面
     * @param i
     */
    private void selectDisplayView(int i) {
        removeAllView();
        createView(i);
        setSelectedStatus(i);
    }

    /**
     * 移除不需要的视图
     */
    private void removeAllView() {
        for (int i = 0;i< bodyLayout.getChildCount();i++){
            bodyLayout.getChildAt(i).setVisibility(View.GONE);
        }
    }

    private void setSelectedStatus(int i){
        switch (i){
            case 0:
                childInfoTab.setSelected(true);
                iv_baby.setImageResource(R.drawable.ic_launcher_background);
                tv_baby.setTextColor(Color.parseColor("#008577"));
                ll_tools_bar_layout.setVisibility(View.VISIBLE);
                tv_main_title.setText("首页");
                break;
            case 1:
                missionTab.setSelected(true);
                iv_mission.setImageResource(R.drawable.icon_misson_select);
                tv_mission.setTextColor(Color.parseColor("#008577"));
                ll_tools_bar_layout.setVisibility(View.VISIBLE);
                tv_main_title.setText("任务卡");
                break;
            case 2:
                videoTab.setSelected(true);
                iv_video.setImageResource(R.drawable.ic_launcher_background);
                tv_video.setTextColor(Color.parseColor("#008577"));
                ll_tools_bar_layout.setVisibility(View.VISIBLE);
                tv_main_title.setText("视频");
                break;
            case 3:
                mineTab.setSelected(true);
                iv_mine.setImageResource(R.drawable.ic_launcher_background);
                tv_mine.setTextColor(Color.parseColor("#008577"));
                ll_tools_bar_layout.setVisibility(View.VISIBLE);
                tv_main_title.setText("我的");
                break;
        }
    }
    /**
     * 选择视图
     * @param viewIndex
     */
    private void createView(int viewIndex) {
        switch (viewIndex){
            case 0:
                //首页界面
                break;
            case 1:
                //任务卡界面
                break;
            case 2:
                //视频界面
                break;
            case 3:
                //个人中心界面
                break;
        }
    }

    /**
     * 清除底部按钮的选中状态
     */
    private void clearBottomImageState() {
        tv_baby.setTextColor(Color.parseColor("#303030"));
        tv_mission.setTextColor(Color.parseColor("#303030"));
        tv_video.setTextColor(Color.parseColor("#303030"));
        tv_mine.setTextColor(Color.parseColor("#303030"));

        iv_baby.setImageResource(R.drawable.icon_home);
        iv_mission.setImageResource(R.drawable.icon_mission);
        iv_video.setImageResource(R.drawable.icon_video);
        iv_mine.setImageResource(R.drawable.icon_mine);

        for (int i = 0; i < bottomBar.getChildCount(); i++) {
            bottomBar.getChildAt(i).setSelected(false);
        }
    }

}
