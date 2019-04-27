package com.hwt.babybag.ui.act;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hwt.babybag.R;
import com.hwt.babybag.utils.ChooseImg;
import com.hwt.babybag.view.IJklayerVideoView;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayConfig;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class Video2Act extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolsbar_menu)
    public ImageView icon_menu;
    @BindView(R.id.tv_title_bar)
    public TextView tv_main_title;

    private TXCloudVideoView txCloudVideoView;
    private TXLivePlayer mLivePlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setStatusBarFullTransparent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video2);
        ButterKnife.bind(this);
        tv_main_title.setText("直播");
        icon_menu.setImageResource(R.drawable.icon_left_arrow);
        icon_menu.setOnClickListener(this);

        ImageView noCompleteImg = findViewById(R.id.nocomplete_img);
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(Video2Act.this).load(R.drawable.no_complete)
                .apply(options).into(noCompleteImg);
        txCloudVideoView = findViewById(R.id.video_play);
        playCline();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLivePlayer.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLivePlayer.pause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 全透明
     */
//    protected void setStatusBarFullTransparent(){
//        if(Build.VERSION.SDK_INT >= 21){
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView()
//                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }else if(Build.VERSION.SDK_INT >= 19){
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//    }

    private void playCline(){
        TXLivePlayConfig mPlayConfig = new TXLivePlayConfig();
        //极速模式
        mPlayConfig.setAutoAdjustCacheTime(true);
        mPlayConfig.setMinAutoAdjustCacheTime(1);
        mPlayConfig.setMaxAutoAdjustCacheTime(1);

        mPlayConfig.setConnectRetryCount(3);

        //创建 player 对象
        mLivePlayer = new TXLivePlayer(this);
        mLivePlayer.setConfig(mPlayConfig);
        // 设置画面渲染方向
        mLivePlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_LANDSCAPE);
        //关键 player 对象与界面 view
        mLivePlayer.setPlayerView(txCloudVideoView);

        mLivePlayer.setPlayListener(new ITXLivePlayListener() {
            @Override
            public void onPlayEvent(int i, Bundle bundle) {

            }

            @Override
            public void onNetStatus(Bundle bundle) {

            }
        });


        String flvUrl = "https://47170.liveplay.myqcloud.com/live/12351238.flv";

        int result = mLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_LIVE_FLV); //推荐 FLV
        mLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
        switch (result){
            case 0:
                break;
            case -1:
                Toast.makeText(Video2Act.this,"播放失败，链接不存在",Toast.LENGTH_SHORT).show();
                break;
            case -2:
                Toast.makeText(Video2Act.this,"播放失败，链接格式不正确",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLivePlayer.stopPlay(true); // true 代表清除最后一帧画面
        txCloudVideoView.onDestroy();
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
