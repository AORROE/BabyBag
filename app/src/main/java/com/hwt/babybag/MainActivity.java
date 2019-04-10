package com.hwt.babybag;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwt.babybag.ui.act.LoginAct;
import com.hwt.babybag.ui.frag.BabyFrag;
import com.hwt.babybag.ui.frag.MineFrag;
import com.hwt.babybag.ui.frag.MissionFrag;
import com.hwt.babybag.ui.frag.VideoFrag;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.dl_main)
    public DrawerLayout drawerLayout;
    @BindView(R.id.fl_main_body)
    public FrameLayout bodyLayout;
    @BindView(R.id.ll_bottomNav)
    public LinearLayout bottomBar;

    private Fragment babyFrag;
    private Fragment missionFrag;
    private Fragment videoFrag;
    private Fragment mineFrag;

    //底部按钮
    @BindView(R.id.rl_childInfoTab)
    public View childInfoTab;
    @BindView(R.id.rl_missionTab)
    public View missionTab;
    @BindView(R.id.rl_videoTab)
    public View videoTab;
    @BindView(R.id.rl_mineTab)
    public View mineTab;
    @BindView(R.id.bottom_bar_baby)
    public TextView tv_baby;
    @BindView(R.id.bottom_bar_mission)
    public TextView tv_mission;
    @BindView(R.id.bottom_bar_video)
    public TextView tv_video;
    @BindView(R.id.bottom_bar_mine)
    public TextView tv_mine;
    @BindView(R.id.bottom_bar_babyImg)
    public ImageView iv_baby;
    @BindView(R.id.bottom_bar_missionImg)
    public ImageView iv_mission;
    @BindView(R.id.bottom_bar_videoImg)
    public ImageView iv_video;
    @BindView(R.id.bottom_bar_mineImg)
    public ImageView iv_mine;
    @BindView(R.id.toolsbar_menu)
    public ImageView icon_menu;

    //标题栏
    @BindView(R.id.tv_title_bar)
    public TextView tv_main_title;
    @BindView(R.id.ll_tools_bar_layout)
    public LinearLayout ll_tools_bar_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        Explode explode = new Explode();
//        explode.setDuration(500);
//        getWindow().setExitTransition(explode);
//        getWindow().setEnterTransition(explode);
        setHalfTransparent();
        setStatusBarFullTransparent();
        init();
        setListener();
        setInitStatus();
        icon_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    /**
     * 获取界面上的UI控件
     */
    private void init() {
        tv_main_title.setText("首页");
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
                iv_baby.setImageResource(R.drawable.icon_baby_select);
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
                iv_video.setImageResource(R.drawable.icon_video_select);
                tv_video.setTextColor(Color.parseColor("#008577"));
                ll_tools_bar_layout.setVisibility(View.VISIBLE);
                tv_main_title.setText("视频");
                break;
            case 3:
                mineTab.setSelected(true);
                iv_mine.setImageResource(R.drawable.icon_mine_select);
                tv_mine.setTextColor(Color.parseColor("#008577"));
                ll_tools_bar_layout.setVisibility(View.VISIBLE);
                tv_main_title.setText("发现");
                break;
        }
    }
    /**
     * 选择视图
     * @param viewIndex
     */
    private void createView(int viewIndex) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (viewIndex){
            case 0:
                //首页界面
                Log.i("TAG", "createView: ");
                if(babyFrag == null){
                    babyFrag = new BabyFrag();
                }
                transaction.replace(R.id.fl_main_body,babyFrag);
                break;
            case 1:
                //任务卡界面
                if(missionFrag == null){
                    missionFrag = new MissionFrag();
                }
                transaction.replace(R.id.fl_main_body,missionFrag);
                break;
            case 2:
                //视频界面
                if(videoFrag == null){
                    videoFrag = new VideoFrag();
                }
                transaction.replace(R.id.fl_main_body,videoFrag);
                break;
            case 3:
                //个人中心界面
                if(mineFrag == null){
                    mineFrag = new MineFrag();
                }
                transaction.replace(R.id.fl_main_body,mineFrag);
                break;
        }
        transaction.commit();
    }

    /**
     * 清除底部按钮的选中状态
     */
    private void clearBottomImageState() {
        tv_baby.setTextColor(Color.parseColor("#bfbfbf"));
        tv_mission.setTextColor(Color.parseColor("#bfbfbf"));
        tv_video.setTextColor(Color.parseColor("#bfbfbf"));
        tv_mine.setTextColor(Color.parseColor("#bfbfbf"));

        iv_baby.setImageResource(R.drawable.icon_baby);
        iv_mission.setImageResource(R.drawable.icon_mission);
        iv_video.setImageResource(R.drawable.icon_video);
        iv_mine.setImageResource(R.drawable.icon_mine);

        for (int i = 0; i < bottomBar.getChildCount(); i++) {
            bottomBar.getChildAt(i).setSelected(false);
        }
    }

    /**
     * 全透明
     */
    protected void setStatusBarFullTransparent(){
        if(Build.VERSION.SDK_INT >= 21){
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }else if(Build.VERSION.SDK_INT >= 19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 半透明
     */
    protected void setHalfTransparent(){
        if(Build.VERSION.SDK_INT >= 21){
            Window window = getWindow();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            window.getDecorView()
                    .setSystemUiVisibility(option);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }else if(Build.VERSION.SDK_INT >= 19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}
